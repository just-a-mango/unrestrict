package com.mango.unrestrict.mixin;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.screen.report.ChatReportScreen;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Environment(value=EnvType.CLIENT)
@Mixin(ChatReportScreen.class)
public class ChatReportScreenMixin {
	@Final
	@Shadow
	@Nullable
	Screen parent;

	private MinecraftClient client;

	public void close() {
		this.client.setScreen(this.parent);
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
