package com.gizmo.music;

import com.mojang.logging.LogUtils;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;

@Mod(MusicManager.MODID)
public class MusicManager {
	public static final String MODID = "musicmanager";
	public static final Logger LOGGER = LogUtils.getLogger();

	public static ForgeConfigSpec CLIENT_SPEC;
	public static MusicConfig CLIENT;
	public static boolean displayRecordToast = true;
	public static boolean silentMusicToasts = true;
	public static int minSongDelay = 0;
	public static int maxSongDelay = 0;

	public MusicManager() {
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
