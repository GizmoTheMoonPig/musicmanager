package com.gizmo.music;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.Resource;
import net.minecraft.server.packs.resources.ResourceManager;

import java.util.List;
import java.util.Map;

public class FileToIdConverter {
	private final String prefix;
	private final String extension;

	public FileToIdConverter(String prefix, String extension) {
		this.prefix = prefix;
		this.extension = extension;
	}

	public static FileToIdConverter json(String prefix) {
		return new FileToIdConverter(prefix, ".json");
	}

	public Map<ResourceLocation, Resource> listMatchingResources(ResourceManager manager) {
		return manager.listResources(this.prefix, location -> location.getPath().endsWith(this.extension));
	}
}
