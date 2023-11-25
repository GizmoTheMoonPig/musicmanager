package com.gizmo.music.client;

import com.gizmo.music.FileToIdConverter;
import com.gizmo.music.MusicManager;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.util.GsonHelper;

import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class MusicResources {
    private static final Map<ResourceLocation, ResourceLocation> DIMENSION_ICONS = new HashMap<>();

    public static Map<ResourceLocation, ResourceLocation> getDimensionIcons() {
        return Collections.unmodifiableMap(DIMENSION_ICONS);
    }


    /* RELOAD LISTENER */

    static final ResourceManagerReloadListener LISTENER = MusicResources::reloadDimensionIcons;

    private static void reloadDimensionIcons(ResourceManager resourceManager) {
        // clear existing icons
        DIMENSION_ICONS.clear();

        // load up JSON files
        var converter = FileToIdConverter.json("musicmanager/dimensions");
        converter.listMatchingResources(resourceManager).forEach((k, v) -> {
            var split = k.getPath().split("/");
            if (split.length != 4) {
                MusicManager.LOGGER.error("The path for a music manager dimension resource is incorrect! Expected 'musicmanager/dimensions/<dimension namespace>/<dimension path>.json', found '{}'", k.getPath());
                return;
            }

            // extract the expected dimension from the path we split earlier. remove the file extension.
            var dimension = new ResourceLocation(split[2], split[3].replace(".json", ""));

            try (var reader = v.openAsReader()) {
                var json = GsonHelper.parse(reader);
                var item = json.get("item").getAsString();
                //MusicManager.LOGGER.debug("Adding '{}' as the icon for '{}'", item, dimension);
                DIMENSION_ICONS.put(dimension, new ResourceLocation(item));
            } catch (IOException e) {
                MusicManager.LOGGER.error("Failed to read a music manager dimension resource! Are you missing a property? File: '{}'", k.getPath());
            }
        });
    }
}
