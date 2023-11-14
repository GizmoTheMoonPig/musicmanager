package com.gizmo.music.client;

import com.gizmo.music.ToastUtil;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEventListener;
import net.minecraft.client.sounds.WeighedSoundEvents;

public class MusicToastListener implements SoundEventListener {

	@Override
	public void onPlaySound(SoundInstance sound, WeighedSoundEvents events) {
		ToastUtil.processToast(sound);
	}
}
