package com.gizmo.music.api;

import com.gizmo.music.MusicManager;
import com.gizmo.music.MusicResources;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.JukeboxSong;
import net.neoforged.neoforge.common.Tags;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class ToastUtil {

	private static final Map<ResourceLocation, Pair<Item, JukeboxSong>> CACHED_RECORDS = new TreeMap<>();

	/**
	 * Formats a SoundInstance's name to an easy-to-read format. Entries are formatted as such: <br>
	 * "sounds.musicmanager.{Modid}.{Sound Folder Structure}.{File Name}" <br>
	 * @param instance the sound to create a Component based around
	 * @return A {@link Component} if a translation for the entry exists, otherwise returns null
	 */
	@Nullable
	public static Component getSoundName(SoundInstance instance) {
		String soundLocation = instance.getSound().getLocation().toString().replace('/', '.').replace(':', '.');
		String unlocalizedSound = "sounds.musicmanager." + soundLocation;
		if (I18n.exists(unlocalizedSound)) {
			return Component.translatable(unlocalizedSound);
		}
		if (MusicManager.DEBUG_MODE) {
			MusicManager.LOGGER.info("Music Manager attempted to create a toast for the track {}", unlocalizedSound);
			return Component.translatable(unlocalizedSound);
		}
		return null;
	}

	/**
	 * Fetches a {@link Item} associated to a specific sound, if any. <br>
	 * This checks for items that have a JukeboxPlayable component attached by default.
	 * @param instance the sound to check if any records contain
	 * @return The RecordItem tied to the SoundInstance provided, if any
	 */
	@Nullable
	public static Pair<Item, JukeboxSong> getDiscFromSound(SoundInstance instance) {
		if (CACHED_RECORDS.isEmpty()) {
			for (Item item : BuiltInRegistries.ITEM.stream().filter(item -> item.components().has(DataComponents.JUKEBOX_PLAYABLE)).toList()) {
				JukeboxSong song = item.components().get(DataComponents.JUKEBOX_PLAYABLE).song().unwrap(Minecraft.getInstance().level.registryAccess()).get().value();
				CACHED_RECORDS.put(song.soundEvent().value().getLocation(), Pair.of(item, song));
			}
		}

		if (CACHED_RECORDS.containsKey(instance.getLocation())) {
			return CACHED_RECORDS.get(instance.getLocation());
		}
		return null;
	}

	public static void clearCachedRecords() {
		CACHED_RECORDS.clear();
	}

	public static Component tryGetDiscTranslation(ResourceLocation location) {
		String unlocalizedSound = "jukebox_song." + location.getNamespace() + "." + location.getPath();
		if (I18n.exists(unlocalizedSound)) {
			return Component.translatable(unlocalizedSound);
		}
		return Component.literal("Unknown Song");
	}
	/**
	 * Attempts to grab Biome and Dimension icon overrides first, then grabs a random music disc if neither exists
	 * @see MusicResources Icon override registration
	 */
	public static ItemStack getItemOverrides(ClientLevel level) {
		ResourceLocation biomeItem = MusicResources.getBiomeIcons().get(level.getBiome(Minecraft.getInstance().player.blockPosition()).unwrapKey().get().location());
		if (biomeItem != null) {
			return new ItemStack(BuiltInRegistries.ITEM.get(biomeItem));
		}
		ResourceLocation dimItem = MusicResources.getDimensionIcons().get(level.dimension().location());
		return dimItem != null ? new ItemStack(BuiltInRegistries.ITEM.get(dimItem)) : fetchRandomDisc(level);
	}

	/**
	 * Grabs a random music disc from the {@link Tags.Items#MUSIC_DISCS Music Discs} tag, returns {@link Items#MUSIC_DISC_CAT C418 - Cat} by default
	 */
	public static ItemStack fetchRandomDisc(ClientLevel level) {
		ItemStack defaultItem = new ItemStack(Items.MUSIC_DISC_CAT);
		//if tags are populated, grab a random music disc to spice things up!
		if (BuiltInRegistries.ITEM.getTag(Tags.Items.MUSIC_DISCS).isPresent()) {
			Optional<Holder<Item>> disc = BuiltInRegistries.ITEM.getTag(Tags.Items.MUSIC_DISCS).get().getRandomElement(level.getRandom());
			if (disc.isPresent()) {
				defaultItem = new ItemStack(disc.get());
			}
		}
		return defaultItem;
	}
}
