package com.gizmo.music.api;

import com.gizmo.music.MusicManager;
import com.gizmo.music.MusicResources;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.language.I18n;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.TreeMap;

public class ToastUtil {

	private static final Map<ResourceLocation, RecordItem> CACHED_RECORDS = new TreeMap<>();

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
	 * Fetches a {@link RecordItem} associated to a specific sound, if any. <br>
	 * Use this instead of RecordItem#BY_NAME as that map only contains vanilla music discs. Forge's constructor doesn't add to the map.
	 * @param instance the sound to check if any records contain
	 * @return The RecordItem tied to the SoundInstance provided, if any
	 */
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

	/**
	 * Attempts to grab Biome and Dimension icon overrides first, then grabs a random music disc if neither exists
	 * @see MusicResources Icon override registration
	 */
	public static ItemStack getItemOverrides(ClientLevel level) {
		ResourceLocation biomeItem = MusicResources.getBiomeIcons().get(level.getBiome(Minecraft.getInstance().player.blockPosition()).unwrapKey().get().location());
		if (biomeItem != null) {
			return new ItemStack(ForgeRegistries.ITEMS.getValue(biomeItem));
		}
		ResourceLocation dimItem = MusicResources.getDimensionIcons().get(level.dimension().location());
		return dimItem != null ? new ItemStack(ForgeRegistries.ITEMS.getValue(dimItem)) : fetchRandomDisc(level);
	}

	/**
	 * Grabs a random music disc from the {@link ItemTags#MUSIC_DISCS Music Discs} tag, returns {@link Items#MUSIC_DISC_CAT C418 - Cat} by default
	 */
	public static ItemStack fetchRandomDisc(ClientLevel level) {
		ItemStack defaultItem = new ItemStack(Items.MUSIC_DISC_CAT);
		//if tags are populated, grab a random music disc to spice things up!
		if (ForgeRegistries.ITEMS.tags() != null && !Objects.requireNonNull(ForgeRegistries.ITEMS.tags()).getTag(ItemTags.MUSIC_DISCS).isEmpty()) {
			Optional<Item> disc = Objects.requireNonNull(ForgeRegistries.ITEMS.tags()).getTag(ItemTags.MUSIC_DISCS).getRandomElement(level.getRandom());
			if (disc.isPresent()) {
				defaultItem = new ItemStack(disc.get());
			}
		}
		return defaultItem;
	}
}
