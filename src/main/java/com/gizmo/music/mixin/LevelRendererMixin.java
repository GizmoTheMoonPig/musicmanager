package com.gizmo.music.mixin;

import com.gizmo.music.MusicManager;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.Gui;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.network.chat.Component;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LevelRenderer.class)
public class LevelRendererMixin {

	@WrapOperation(method = "playJukeboxSong", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/Gui;setNowPlaying(Lnet/minecraft/network/chat/Component;)V"))
	private void musicManager$modifyRecordPlayingOverlay(Gui instance, Component displayName, Operation<Void> original) {
		if (!MusicManager.displayRecordToast) {
			original.call(instance, displayName);
		}
	}
}
