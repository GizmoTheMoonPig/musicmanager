package com.gizmo.music;

import com.mojang.logging.LogUtils;
import net.minecraft.SharedConstants;
import net.minecraft.client.Minecraft;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;

import java.util.UUID;

@Mod(MusicManager.MODID)
public class MusicManager {
	public static final String MODID = "musicmanager";
	public static final Logger LOGGER = LogUtils.getLogger();
	public static boolean DEBUG_MODE = false;

	public static ForgeConfigSpec CLIENT_SPEC;
	public static MusicConfig CLIENT;
	public static boolean displayRecordToast = true;
	public static boolean silentMusicToasts = true;
	public static int minSongDelay = 10;
	public static int maxSongDelay = 30;

	public MusicManager() {
		DEBUG_MODE = !FMLLoader.isProduction() || SharedConstants.IS_RUNNING_IN_IDE || Minecraft.getInstance().getUser().getGameProfile().getId().equals(UUID.fromString("c47fe203-99e7-45c4-9c19-2c9281b74364"));
		{
			final Pair<MusicConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(MusicConfig::new);
			CLIENT = specPair.getLeft();
			CLIENT_SPEC = specPair.getRight();
		}
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CLIENT_SPEC);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::reloadConfig);
	}

	public void reloadConfig(ModConfigEvent event) {
		if (event.getConfig().getSpec() == CLIENT_SPEC) {
			rebakeOptions();
			LOGGER.debug("Reloading Music Manager Config!");
		}
	}

	public static void rebakeOptions() {
		displayRecordToast = CLIENT.displayRecordToast.get();
		silentMusicToasts = CLIENT.silentMusicToasts.get();
		minSongDelay = CLIENT.minSongDelay.get();
		maxSongDelay = CLIENT.maxSongDelay.get();
	}
}
