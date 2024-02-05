package com.gizmo.music.api;

import com.gizmo.music.MusicManager;
import com.google.gson.JsonObject;
import net.minecraft.Util;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.DataProvider;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.ForgeRegistries;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public abstract class IconOverrideProvider implements DataProvider {

	private final DataGenerator.PathProvider path;
	protected final ExistingFileHelper fileHelper;
	protected final Map<String, ResourceLocation> iconOverrides;

	protected IconOverrideProvider(DataGenerator generator, ExistingFileHelper helper) {
		this.path = generator.createPathProvider(DataGenerator.Target.RESOURCE_PACK, "musicmanager");
		this.fileHelper = helper;
		this.iconOverrides = new HashMap<>();
	}

	protected abstract void createOverrides();

	/**
	 * Registers an icon override to use if the player is in the given dimension
	 * @param dimension the dimension the icon should be registered for
	 * @param item The Item to use as an icon
	 */
	protected void createDimensionIcon(ResourceLocation dimension, ItemLike item) {
		this.createDimensionIcon(dimension, ForgeRegistries.ITEMS.getKey(item.asItem()));
	}

	/**
	 * Registers an icon override to use if the player is in the given dimension
	 * @param dimension the dimension the icon should be registered for
	 * @param item The Item to use as an icon
	 */
	protected void createDimensionIcon(ResourceLocation dimension, ResourceLocation item) {
		this.iconOverrides.put("dimensions/" + dimension.getNamespace() + "/" + dimension.getPath(), item);
	}

	/**
	 * Registers an icon override to use if the player is in the given biome
	 * @param biome the biome the icon should be registered for
	 * @param item The Item to use as an icon
	 */
	protected void createBiomeIcon(ResourceLocation biome, ItemLike item) {
		this.createBiomeIcon(biome, ForgeRegistries.ITEMS.getKey(item.asItem()));
	}

	/**
	 * Registers an icon override to use if the player is in the given biome
	 * @param biome the biome the icon should be registered for
	 * @param item The Item to use as an icon
	 */
	protected void createBiomeIcon(ResourceLocation biome, ResourceLocation item) {
		this.iconOverrides.put("biomes/" + biome.getNamespace() + "/" + biome.getPath(), item);
	}

	@Override
	public void run(CachedOutput output) {
		this.iconOverrides.clear();
		this.createOverrides();
		for (Map.Entry<String, ResourceLocation> override : this.iconOverrides.entrySet()) {
			try {
				DataProvider.saveStable(output,
						Util.make(new JsonObject(), obj -> obj.addProperty("icon", override.getValue().toString())),
						this.path.json(new ResourceLocation(MusicManager.MODID, override.getKey())));
			} catch (IOException e) {
				MusicManager.LOGGER.error("Couldn't save Icon Override to {}", this.path, e);
			}
		}
	}

	@Override
	public final String getName() {
		return "Music Manager Toast Icons";
	}
}
