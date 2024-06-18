package com.gizmo.music.mixin;

import com.gizmo.music.MusicManager;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {

	@Redirect(method = "playJukeboxSong", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;setNowPlaying(Lnet/minecraft/network/chat/Component;)V", remap = false), remap = false)
	private void modifyRecordPlayingOverlay(Gui gui, Component component) {
		if (!MusicManager.displayRecordToast) {
			gui.setNowPlaying(component);
		}
	}
}
