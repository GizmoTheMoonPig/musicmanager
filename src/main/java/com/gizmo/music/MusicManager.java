package com.gizmo.music;

import com.gizmo.music.api.MusicToastEvent;
import com.gizmo.music.api.ToastUtil;
import com.gizmo.music.data.IconOverrideGenerator;
import com.gizmo.music.data.LangGenerator;
import com.mojang.blaze3d.platform.InputConstants;
import com.mojang.logging.LogUtils;
import net.minecraft.DetectedVersion;
import net.minecraft.SharedConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.data.metadata.PackMetadataGenerator;
import net.minecraft.network.chat.Component;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.pack.PackMetadataSection;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.InclusiveRange;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.RecordItem;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.fml.loading.FMLLoader;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.client.event.RegisterClientReloadListenersEvent;
import net.neoforged.neoforge.client.event.RegisterKeyMappingsEvent;
import net.neoforged.neoforge.client.settings.KeyConflictContext;
import net.neoforged.neoforge.common.ModConfigSpec;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.common.NeoForgeConfig;
import net.neoforged.neoforge.data.event.GatherDataEvent;
import org.apache.commons.lang3.tuple.Pair;
import org.jetbrains.annotations.Nullable;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;

import java.util.Optional;
import java.util.UUID;

@Mod(MusicManager.MODID)
public class MusicManager {
	public static final String MODID = "musicmanager";
	public static final Logger LOGGER = LogUtils.getLogger();
	public static boolean DEBUG_MODE = false;
	@Nullable
	public SoundInstance lastPlayedSound;

	//config things
	public static ModConfigSpec CLIENT_SPEC;
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


	public MusicManager(IEventBus bus) {
		{
			final Pair<MusicConfig, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(MusicConfig::new);
			CLIENT = specPair.getLeft();
			CLIENT_SPEC = specPair.getRight();
		}
		ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, CLIENT_SPEC);

		bus.addListener(this::gatherData);
		bus.addListener(this::reloadConfig);
		bus.addListener(this::registerListeners);
		bus.addListener(this::registerKeyMappings);
		NeoForge.EVENT_BUS.addListener(this::showAnotherToast);
		NeoForge.EVENT_BUS.addListener(this::openManagerScreen);

		DEBUG_MODE = !FMLLoader.isProduction() || SharedConstants.IS_RUNNING_IN_IDE || Minecraft.getInstance().getUser().getProfileId().equals(UUID.fromString("c47fe203-99e7-45c4-9c19-2c9281b74364"));
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
		Minecraft.getInstance().getSoundManager().addListener((instance, sound, var) -> this.processToast(instance));
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
		if (!NeoForge.EVENT_BUS.post(event).isCanceled()) {
			MusicToast.addOrReplace(Minecraft.getInstance().getToasts(), text, icon);
			return true;
		}
		return false;
	}

	public void gatherData(GatherDataEvent event) {
		event.getGenerator().addProvider(event.includeClient(), new LangGenerator(event.getGenerator().getPackOutput()));
		event.getGenerator().addProvider(event.includeClient(), new IconOverrideGenerator(event.getGenerator().getPackOutput(), event.getExistingFileHelper()));
		event.getGenerator().addProvider(true, new PackMetadataGenerator(event.getGenerator().getPackOutput()).add(PackMetadataSection.TYPE, new PackMetadataSection(
				Component.literal("Music Manager Resources"),
				DetectedVersion.BUILT_IN.getPackVersion(PackType.CLIENT_RESOURCES),
				Optional.of(new InclusiveRange<>(0, Integer.MAX_VALUE)))));
	}
}
