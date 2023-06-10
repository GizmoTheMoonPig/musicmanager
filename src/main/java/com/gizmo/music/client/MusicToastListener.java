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
import net.minecraftforge.common.MinecraftForge;

public class MusicToastListener implements SoundEventListener {

	@Override
	public void onPlaySound(SoundInstance sound, WeighedSoundEvents events) {
		Minecraft mc = Minecraft.getInstance();
		if (sound.getSource() == SoundSource.MUSIC) {
			Component name = ToastUtil.getSoundName(sound);
			if (name == null) return;

			var item = mc.level != null ? ToastUtil.getItemByDimension(mc.level) : new ItemStack(Items.EMERALD);
			this.addMusicToast(name, item);
		} else if (sound.getSource() == SoundSource.RECORDS && MusicManager.displayRecordToast) {
			RecordItem disc = ToastUtil.getDiscFromSound(sound);
			if (disc == null) return;

			this.addMusicToast(disc.getDisplayName(), new ItemStack(disc));
		}
	}

	private void addMusicToast(Component text, ItemStack icon) {
		var event = new MusicToastEvent(text, icon);
		if (!MinecraftForge.EVENT_BUS.post(event)) {
			var toast = new MusicToast(event.getText(), event.getIcon());
			Minecraft.getInstance().getToasts().addToast(toast);
		}
	}
}
