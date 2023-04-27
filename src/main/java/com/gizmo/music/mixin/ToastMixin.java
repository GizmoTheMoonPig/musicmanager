package com.gizmo.music.mixin;

import com.gizmo.music.MusicManager;
import com.gizmo.music.client.MusicToast;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.resources.sounds.SimpleSoundInstance;
import net.minecraft.client.sounds.SoundManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(targets = "net.minecraft.client.gui.components.toasts.ToastComponent$ToastInstance")
public class ToastMixin<T extends Toast> {

	@Final
	@Shadow
	private T toast;

	@Redirect(method = "render", require = 2, at = @At(value = "INVOKE",target = "Lnet/minecraft/client/gui/components/toasts/Toast$Visibility;playSound(Lnet/minecraft/client/sounds/SoundManager;)V"))
	public void musicManager$silenceWooshSound(Toast.Visibility visibility, SoundManager soundManager) {
		if (!(this.toast instanceof MusicToast) || !MusicManager.silentMusicToasts) {
			soundManager.play(SimpleSoundInstance.forUI(visibility.soundEvent, 1.0f, 1.0f));
		}
	}
}
