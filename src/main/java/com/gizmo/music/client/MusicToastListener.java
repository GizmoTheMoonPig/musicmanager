package com.gizmo.music.client;

import com.gizmo.music.MusicManager;
import com.gizmo.music.ToastUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEventListener;
import net.minecraft.client.sounds.WeighedSoundEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.RecordItem;

public class MusicToastListener implements SoundEventListener {

	@Override
	public void onPlaySound(SoundInstance sound, WeighedSoundEvents events) {
		if (sound.getSource() == SoundSource.MUSIC) {
			Component name = ToastUtil.getSoundName(sound);
			if (name == null) return;

			if (Minecraft.getInstance().level != null) {
				Minecraft.getInstance().getToasts().addToast(new MusicToast(name, ToastUtil.getItemByDimension(Minecraft.getInstance().level)));
			} else {
				//im gonna assume if the level is null, then its the menu
				Minecraft.getInstance().getToasts().addToast(new MusicToast(name, new ItemStack(Items.EMERALD)));
			}
		} else if (sound.getSource() == SoundSource.RECORDS && MusicManager.displayRecordToast) {
			RecordItem disc = ToastUtil.getDiscFromSound(sound);
			if (disc == null) return;

			Minecraft.getInstance().getToasts().addToast(new MusicToast(disc.getDisplayName(), new ItemStack(disc)));
		}
	}
}
