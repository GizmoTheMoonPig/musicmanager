package com.gizmo.music.api;

import com.gizmo.music.MusicManager;
import com.google.gson.JsonObject;
import net.minecraft.Util;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

public abstract class IconOverrideProvider implements DataProvider {

	private final PackOutput.PathProvider particlesPath;
	protected final ExistingFileHelper fileHelper;
	protected final Map<String, ResourceLocation> iconOverrides;

	protected IconOverrideProvider(PackOutput output, ExistingFileHelper helper) {
		this.particlesPath = output.createPathProvider(PackOutput.Target.RESOURCE_PACK, "musicmanager");
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
		this.createDimensionIcon(dimension, BuiltInRegistries.ITEM.getKey(item.asItem()));
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
		this.createBiomeIcon(biome, BuiltInRegistries.ITEM.getKey(item.asItem()));
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
	public CompletableFuture<?> run(CachedOutput output) {
		this.iconOverrides.clear();
		this.createOverrides();
		return CompletableFuture.allOf(
				this.iconOverrides.entrySet().stream().map(entry ->
						DataProvider.saveStable(output,
								Util.make(new JsonObject(), obj -> obj.addProperty("icon", entry.getValue().toString())),
								this.particlesPath.json(new ResourceLocation(MusicManager.MODID, entry.getKey())))
				).toArray(CompletableFuture[]::new)
		);
	}

	@Override
	public final String getName() {
		return "Music Manager Toast Icons";
	}
}
