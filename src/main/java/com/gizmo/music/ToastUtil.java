package com.gizmo.music;

import com.gizmo.music.client.MusicResources;
import com.gizmo.music.client.MusicToast;
import com.gizmo.music.client.MusicToastEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.RecordItem;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;

public class ToastUtil {

	private static final Map<ResourceLocation, RecordItem> CACHED_RECORDS = new TreeMap<>();
	@Nullable
	public static SoundInstance lastPlayedSound;

	public static void processToast(SoundInstance sound) {
		Minecraft mc = Minecraft.getInstance();
		if (sound.getSource() == SoundSource.MUSIC) {
			Component name = ToastUtil.getSoundName(sound);
			if (name == null) return;

			var item = mc.level != null ? ToastUtil.getItemByDimension(mc.level) : new ItemStack(Items.EMERALD);
			lastPlayedSound = sound;
			addMusicToast(name, item);
		} else if (sound.getSource() == SoundSource.RECORDS && MusicManager.displayRecordToast) {
			RecordItem disc = ToastUtil.getDiscFromSound(sound);
			if (disc == null) return;
			lastPlayedSound = sound;
			addMusicToast(disc.getDisplayName(), new ItemStack(disc));
		}
	}

	public static void addMusicToast(Component text, ItemStack icon) {
		var event = new MusicToastEvent(text, icon);
		if (!MinecraftForge.EVENT_BUS.post(event)) {
			var toast = new MusicToast(event.getText(), event.getIcon());
			Minecraft.getInstance().getToasts().addToast(toast);
		}
	}

	@Nullable
	public static Component getSoundName(SoundInstance instance) {
		String soundLocation = instance.getSound().getLocation().toString().replace('/', '.').replace(':', '.');
		if (I18n.exists("sounds.musicmanager." + soundLocation)) {
			return Component.translatable("sounds.musicmanager." + soundLocation);
		}
		return null;
	}

	//I was under the impression I could use RecordItem.BY_NAME for this, but the forge constructor doesn't add to the map.
	//This means modded discs won't be in the map! I hate this!
	//oh well, this solution seems ok. At least I'm sane enough to cache the discs.
	@Nullable
	public static RecordItem getDiscFromSound(SoundInstance instance) {
		if (CACHED_RECORDS.isEmpty()) {
			for (RecordItem item : ForgeRegistries.ITEMS.getValues().stream().filter(item -> item instanceof RecordItem).map(RecordItem.class::cast).toList()) {
				CACHED_RECORDS.put(item.getSound().getLocation(), item);
			}
		}

		if (CACHED_RECORDS.containsKey(instance.getLocation())) {
			return CACHED_RECORDS.get(instance.getLocation());
		}
		return null;
	}

	public static ItemStack getItemByDimension(Level level) {
		ItemStack defaultItem = new ItemStack(Items.MUSIC_DISC_CAT);
		//if tags are populated, grab a random music disc to spice things up!
		if (ForgeRegistries.ITEMS.tags() != null && !Objects.requireNonNull(ForgeRegistries.ITEMS.tags()).getTag(ItemTags.MUSIC_DISCS).isEmpty()) {
			Optional<Item> disc = Objects.requireNonNull(ForgeRegistries.ITEMS.tags()).getTag(ItemTags.MUSIC_DISCS).getRandomElement(level.getRandom());
			if (disc.isPresent()) {
				defaultItem = new ItemStack(disc.get());
			}
		}

		var item = MusicResources.getDimensionIcons().get(level.dimension().location());
		return item != null ? new ItemStack(ForgeRegistries.ITEMS.getValue(item)) : defaultItem;
	}
}
