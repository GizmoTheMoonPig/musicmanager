package com.gizmo.music;

import com.gizmo.music.api.MusicToastEvent;
import com.gizmo.music.api.ToastUtil;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.logging.LogUtils;
import net.minecraft.SharedConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.RecordItem;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.config.ModConfigEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLLoader;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;

import java.util.UUID;

@Mod(MusicManager.MODID)
public class MusicManager {
	public static final String MODID = "musicmanager";
	public static final Logger LOGGER = LogUtils.getLogger();
	public static boolean DEBUG_MODE = false;
	@Nullable
	public SoundInstance lastPlayedSound;

	//config things
	public static ForgeConfigSpec CLIENT_SPEC;
	public static MusicConfig CLIENT;
	public static boolean displayRecordToast = true;
	public static boolean silentMusicToasts = true;
	public static int minSongDelay = 10;
	public static int maxSongDelay = 30;

	//keybinds
	private final KeyMapping openScreen = new KeyMapping(
				"keybind.musicmanager.open",
				KeyConflictContext.IN_GAME,
				InputConstants.Type.KEYSYM,
				GLFW.GLFW_KEY_M,
				"key.categories.gameplay");
	private final KeyMapping showToastAgain = new KeyMapping(
			"keybind.musicmanager.show_toast",
			KeyConflictContext.IN_GAME,
			InputConstants.Type.KEYSYM,
			GLFW.GLFW_KEY_N,
			"key.categories.gameplay");


	public MusicManager() {
		{
			final Pair<MusicConfig, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(MusicConfig::new);
			CLIENT = specPair.getLeft();
			CLIENT_SPEC = specPair.getRight();
		}
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CLIENT_SPEC);

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::reloadConfig);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerListeners);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::registerKeyMappings);
		MinecraftForge.EVENT_BUS.addListener(this::openManagerScreen);
		MinecraftForge.EVENT_BUS.addListener(this::showAnotherToast);

		DEBUG_MODE = !FMLLoader.isProduction() || SharedConstants.IS_RUNNING_IN_IDE || Minecraft.getInstance().getUser().getGameProfile().getId().equals(UUID.fromString("c47fe203-99e7-45c4-9c19-2c9281b74364"));
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

	private void registerListeners(RegisterClientReloadListenersEvent event) {
		Minecraft.getInstance().getSoundManager().addListener((sound, events) -> this.processToast(sound));
		event.registerReloadListener(MusicResources.LISTENER);
	}

	private void registerKeyMappings(RegisterKeyMappingsEvent event) {
		event.register(this.openScreen);
		event.register(this.showToastAgain);
	}

	private void openManagerScreen(InputEvent.Key event) {
		if (event.getAction() == GLFW.GLFW_PRESS && Minecraft.getInstance().player != null && Minecraft.getInstance().screen == null) {
			if (Minecraft.getInstance().getOverlay() == null && Minecraft.getInstance().screen == null) {
				if (event.getKey() == this.openScreen.getKey().getValue() && this.openScreen.consumeClick()) {
					Minecraft.getInstance().setScreen(new MusicManagerScreen());
				}
			}
		}
	}

	private void showAnotherToast(InputEvent.Key event) {
		if (this.lastPlayedSound == null) return;
		if (event.getAction() == GLFW.GLFW_PRESS && Minecraft.getInstance().player != null && Minecraft.getInstance().screen == null) {
			if (Minecraft.getInstance().getOverlay() == null && Minecraft.getInstance().screen == null) {
				if (event.getKey() == this.showToastAgain.getKey().getValue() && this.showToastAgain.consumeClick()) {
					this.processToast(this.lastPlayedSound);
				}
			}
		}
	}

	private void processToast(SoundInstance sound) {
		Minecraft mc = Minecraft.getInstance();
		if (sound.getSource() == SoundSource.MUSIC) {
			Component name = ToastUtil.getSoundName(sound);
			if (name == null) return;

			ItemStack item = mc.level != null ? ToastUtil.getItemOverrides(mc.level) : new ItemStack(Items.EMERALD);
			if (this.addMusicToast(name, item)) {
				this.lastPlayedSound = sound;
			}
		} else if (sound.getSource() == SoundSource.RECORDS && MusicManager.displayRecordToast) {
			RecordItem disc = ToastUtil.getDiscFromSound(sound);
			if (disc == null) return;
			if (this.addMusicToast(disc.getDisplayName(), new ItemStack(disc))) {
				this.lastPlayedSound = sound;
			}
		}
	}

	public boolean addMusicToast(Component text, ItemStack icon) {
		MusicToastEvent event = new MusicToastEvent(text, icon);
		if (!MinecraftForge.EVENT_BUS.post(event)) {
			MusicToast.addOrReplace(Minecraft.getInstance().getToasts(), text, icon);
			return true;
		}
		return false;
	}
}
