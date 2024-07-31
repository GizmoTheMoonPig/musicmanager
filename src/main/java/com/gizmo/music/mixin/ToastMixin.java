package com.gizmo.music.mixin;

import com.gizmo.music.MusicManager;
import com.gizmo.music.MusicToast;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.sounds.SoundManager;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(targets = "net.minecraft.client.gui.components.toasts.ToastComponent$ToastInstance")
public class ToastMixin<T extends Toast> {

	@Shadow
	@Final
	private T toast;

	@WrapOperation(method = "render", require = 2, at = @At(value = "INVOKE", target = "Lnet/minecraft/client/gui/components/toasts/Toast$Visibility;playSound(Lnet/minecraft/client/sounds/SoundManager;)V"))
	public void musicManager$silenceWhooshSound(Toast.Visibility visibility, SoundManager manager, Operation<Void> original) {
		if (!(this.toast instanceof MusicToast) || !MusicManager.silentMusicToasts) {
			original.call(visibility, manager);
		}
	}
}
