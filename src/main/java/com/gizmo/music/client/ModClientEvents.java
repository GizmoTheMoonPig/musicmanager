package com.gizmo.music.client;

import com.gizmo.music.MusicManager;
import com.gizmo.music.ToastUtil;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.Minecraft;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RegisterClientReloadListenersEvent;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, modid = MusicManager.MODID, value = Dist.CLIENT)
public class ModClientEvents {

	private static KeyMapping openScreen;
	private static KeyMapping showToastAgain;

	@SubscribeEvent
	public static void registerNowPlayingListener(RegisterClientReloadListenersEvent event) {
		Minecraft.getInstance().getSoundManager().addListener(new MusicToastListener());
		event.registerReloadListener(MusicResources.LISTENER);
	}

	@SubscribeEvent
	public static void registerKeyBinding(RegisterKeyMappingsEvent event) {
		openScreen = new KeyMapping(
				"keybind.musicmanager.open",
				KeyConflictContext.IN_GAME,
				InputConstants.Type.KEYSYM,
				GLFW.GLFW_KEY_M,
				"key.categories.gameplay");
		showToastAgain = new KeyMapping(
				"keybind.musicmanager.show_toast",
				KeyConflictContext.IN_GAME,
				InputConstants.Type.KEYSYM,
				GLFW.GLFW_KEY_N,
				"key.categories.gameplay");
		event.register(openScreen);
		event.register(showToastAgain);
	}

	@Mod.EventBusSubscriber(modid = MusicManager.MODID, value = Dist.CLIENT, bus = Mod.EventBusSubscriber.Bus.FORGE)
	public static class ClientForgeEvents {

		@SubscribeEvent
		public static void openManagerScreen(InputEvent.Key event) {
			if (event.getAction() == GLFW.GLFW_PRESS && Minecraft.getInstance().player != null && Minecraft.getInstance().screen == null) {
				if (Minecraft.getInstance().getOverlay() == null && Minecraft.getInstance().screen == null) {
					if (event.getKey() == openScreen.getKey().getValue() && openScreen.consumeClick()) {
						Minecraft.getInstance().setScreen(new MusicManagerScreen());
					}
				}
			}
		}

		@SubscribeEvent
		public static void showAnotherToast(InputEvent.Key event) {
			if (ToastUtil.lastPlayedSound == null) return;
			if (event.getAction() == GLFW.GLFW_PRESS && Minecraft.getInstance().player != null && Minecraft.getInstance().screen == null) {
				if (Minecraft.getInstance().getOverlay() == null && Minecraft.getInstance().screen == null) {
					if (event.getKey() == showToastAgain.getKey().getValue() && showToastAgain.consumeClick()) {
						ToastUtil.processToast(ToastUtil.lastPlayedSound);
					}
				}
			}
		}
	}

}
