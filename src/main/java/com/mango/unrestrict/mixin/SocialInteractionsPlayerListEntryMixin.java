package com.mango.unrestrict.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.multiplayer.SocialInteractionsPlayerListEntry;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.OrderedText;
import net.minecraft.text.Text;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Environment(value= EnvType.CLIENT)
@Mixin(SocialInteractionsPlayerListEntry.class)
public class SocialInteractionsPlayerListEntryMixin {
    @Shadow
    @Nullable
    private ButtonWidget reportButton;

    @Shadow
    List<OrderedText> reportTooltip;

    @Final
    @Shadow
    private MinecraftClient client;

    @Shadow
    Text getReportText(boolean narrated) {
        return null;
    }

    /**
     * @author Just_a_Mango
     * @reason Disable the reportButton
     */
    @Overwrite
    public void setSentMessage(boolean sentMessage) {
        if (this.reportButton != null) {
            this.reportButton.active = false;
        }
        this.reportTooltip = this.client.textRenderer.wrapLines(this.getReportText(false), 150);
    }
}
