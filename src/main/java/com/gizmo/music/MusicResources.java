package com.gizmo.music;

import com.google.gson.JsonObject;
import net.minecraft.resources.FileToIdConverter;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.util.GsonHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 *
 */
public class MusicResources {
    private static final Map<ResourceLocation, ResourceLocation> DIMENSION_ICONS = new HashMap<>();
    private static final Map<ResourceLocation, ResourceLocation> BIOME_ICONS = new HashMap<>();
    public static final ResourceManagerReloadListener LISTENER = MusicResources::reloadOverrideIcons;

    /**
     * Fetches an unmodifiable map of Dimension icon overrides
     */
    public static Map<ResourceLocation, ResourceLocation> getDimensionIcons() {
        return Collections.unmodifiableMap(DIMENSION_ICONS);
    }

    /**
     * Fetches an unmodifiable map of Biome icon overrides
     */
    public static Map<ResourceLocation, ResourceLocation> getBiomeIcons() {
        return Collections.unmodifiableMap(BIOME_ICONS);
    }

    private static void reloadOverrideIcons(ResourceManager resourceManager) {
        // clear existing icons
        BIOME_ICONS.clear();
        DIMENSION_ICONS.clear();

        // load up JSON files
        FileToIdConverter converter = FileToIdConverter.json(MusicManager.MODID);
        converter.listMatchingResources(resourceManager).forEach((k, v) -> {
            String[] splitPath = k.getPath().split("/");
            if (splitPath.length != 4) {
                MusicManager.LOGGER.error("The path for a music manager resource is incorrect! Expected 'musicmanager/<dimensions/biomes>/<mod namespace>/<dimension/biome name>.json', found '{}'", k.getPath());
                return;
            }

            // extract the expected dimension from the path we split earlier. remove the file extension.
            ResourceLocation dimension = new ResourceLocation(splitPath[2], splitPath[3].replace(".json", ""));

            try (BufferedReader reader = v.openAsReader()) {
                JsonObject json = GsonHelper.parse(reader);
                String item = json.get("icon").getAsString();
                //add to appropriate list based on folder
                switch (splitPath[1]) {
                    case "dimensions" -> DIMENSION_ICONS.put(dimension, new ResourceLocation(item));
                    case "biomes" -> BIOME_ICONS.put(dimension, new ResourceLocation(item));
                    default -> MusicManager.LOGGER.error("Unknown icon path {}!", splitPath[1]);
                }

            } catch (IOException e) {
                MusicManager.LOGGER.error("Failed to read a music manager dimension resource! Are you missing a property? File: '{}'", k.getPath());
            }
        });
    }
}
