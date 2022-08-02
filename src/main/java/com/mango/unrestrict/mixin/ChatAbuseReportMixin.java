package com.mango.unrestrict.mixin;

import com.mojang.authlib.minecraft.report.AbuseReport;
import com.mojang.authlib.minecraft.report.ReportEvidence;
import com.mojang.authlib.minecraft.report.ReportedEntity;
import com.mojang.datafixers.util.Either;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.report.AbuseReportContext;
import net.minecraft.client.report.AbuseReportReason;
import net.minecraft.client.report.ChatAbuseReport;
import net.minecraft.client.report.log.ChatLog;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.time.Instant;
import java.util.Objects;
import java.util.UUID;

@Mixin(ChatAbuseReport.class)
@Environment(value= EnvType.CLIENT)
public class ChatAbuseReportMixin {

    @Final
    @Shadow
    private UUID reportedPlayerUuid;
    @Shadow
    private String opinionComments;
    @Final
    @Shadow
    private Instant timestamp;
    @Final
    @Shadow
    private UUID id;


    @Shadow
    @Nullable
    private AbuseReportReason reason;

    /**
     * @author Just_a_Mango
     * @reason Invalidate all reports
     */
    @Overwrite
    public Either<ChatAbuseReport.ReportWithId, ChatAbuseReport.ValidationError> finalizeReport(AbuseReportContext reporter) {
        ChatAbuseReport.ValidationError validationError = this.validate();
        if (validationError != null) {
            return Either.right(validationError);
        }
        String string = Objects.requireNonNull(this.reason).getId();
        ReportEvidence reportEvidence = this.collectEvidence(reporter.chatLog());
        ReportedEntity reportedEntity = new ReportedEntity(this.reportedPlayerUuid);
        AbuseReport abuseReport = new AbuseReport(this.opinionComments, string, reportEvidence, reportedEntity, this.timestamp);
        return Either.left(new ChatAbuseReport.ReportWithId(this.id, abuseReport));
    }

    @Shadow
    private ReportEvidence collectEvidence(ChatLog chatLog) {
        return null;
    }

    @Shadow
    public ChatAbuseReport.ValidationError validate() {
        return null;
    }
}
