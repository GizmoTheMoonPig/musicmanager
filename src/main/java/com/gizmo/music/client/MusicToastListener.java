package com.gizmo.music.client;

import com.gizmo.music.MusicManager;
import com.gizmo.music.ToastUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.client.sounds.SoundEventListener;
import net.minecraft.client.sounds.WeighedSoundEvents;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.RecordItem;

import java.util.List;

public class MusicToastListener implements SoundEventListener {

	@Override
	public void onPlaySound(SoundInstance sound, WeighedSoundEvents events) {
		Font font = Minecraft.getInstance().font;
		if (sound.getSource() == SoundSource.MUSIC) {
			Component name = ToastUtil.getSoundName(sound);
			if (name == null) return;
			List<FormattedCharSequence> textLines = font.split(name, 160 - MusicToast.TEXT_LEFT_MARGIN - MusicToast.TEXT_RIGHT_MARGIN);
			if (Minecraft.getInstance().level != null) {
				Minecraft.getInstance().getToasts().addToast(new MusicToast(textLines, ToastUtil.getItemByDimension(Minecraft.getInstance().level)));
			} else {
				//im gonna assume if the level is null, then its the menu
				Minecraft.getInstance().getToasts().addToast(new MusicToast(textLines, new ItemStack(Items.EMERALD)));
			}
		} else if (sound.getSource() == SoundSource.RECORDS && MusicManager.displayRecordToast) {
			RecordItem disc = ToastUtil.getDiscFromSound(sound);
			if (disc == null) return;
			List<FormattedCharSequence> textLines = font.split(disc.getDisplayName(), 160 - MusicToast.TEXT_LEFT_MARGIN - MusicToast.TEXT_RIGHT_MARGIN);

			Minecraft.getInstance().getToasts().addToast(new MusicToast(textLines, new ItemStack(disc)));
		}
	}
}
