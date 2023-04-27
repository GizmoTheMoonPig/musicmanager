package com.gizmo.music.mixin;

import com.gizmo.music.MusicManager;
import net.minecraft.sounds.Music;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Music.class)
public class MusicMixin {

	@Inject(method = "getMinDelay", at = @At("RETURN"), cancellable = true)
	public void musicManager$setConfigBasedMinDelay(CallbackInfoReturnable<Integer> ci) {
		if (MusicManager.minSongDelay > -1) {
			ci.setReturnValue(Math.round((Math.min(MusicManager.minSongDelay, MusicManager.maxSongDelay) + 1) * 20.0F));
		}
	}

	@Inject(method = "getMaxDelay", at = @At("RETURN"), cancellable = true)
	public void musicManager$setConfigBasedMaxDelay(CallbackInfoReturnable<Integer> ci) {
		if (MusicManager.maxSongDelay > -1) {
			ci.setReturnValue(Math.round((Math.max(MusicManager.minSongDelay, MusicManager.maxSongDelay) + 1) * 20.0F));
		}
	}
}
