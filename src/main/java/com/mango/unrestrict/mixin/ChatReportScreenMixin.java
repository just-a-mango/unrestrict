package com.mango.unrestrict.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.report.ChatReportScreen;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Environment(value=EnvType.CLIENT)
@Mixin(ChatReportScreen.class)
public class ChatReportScreenMixin {
	@Shadow
	public void close() {

	}
	/**
	 * @author Just_a_Mango
	 * @reason Remove all of Mojang's shit
	 */
	@Overwrite
	protected void init() {
		this.close();
	}
}
