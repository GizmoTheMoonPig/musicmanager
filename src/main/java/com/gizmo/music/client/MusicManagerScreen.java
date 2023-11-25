package com.gizmo.music.client;

import com.gizmo.music.MusicConfig;
import com.gizmo.music.MusicManager;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.narration.NarratedElementType;
import net.minecraft.client.gui.narration.NarrationElementOutput;
import net.minecraft.client.gui.screens.Screen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.apache.commons.lang3.StringUtils;
import org.lwjgl.glfw.GLFW;

public class MusicManagerScreen extends Screen {

	static final ResourceLocation TEXTURE = new ResourceLocation(MusicManager.MODID, "textures/gui/manager_screen.png");
	protected int leftPos;
	protected int topPos;

	private CheckBox displayRecordToast;
	private CheckBox playToastSound;
	private EditBox minDelay;
	private EditBox maxDelay;

	public MusicManagerScreen() {
		super(Component.translatable("gui.musicmanager.music_manager"));
	}

	@Override
	protected void init() {
		this.leftPos = (this.width - 142) / 2;
		this.topPos = (this.height - 86) / 2;
		this.displayRecordToast = new CheckBox(this.leftPos + 43, this.topPos + 23, Component.translatable("gui.musicmanager.display_record_toast"), MusicManager.displayRecordToast);
		this.addRenderableWidget(this.displayRecordToast);

		this.playToastSound = new CheckBox(this.leftPos + 100, this.topPos + 23, Component.translatable("gui.musicmanager.play_toast_sound"), !MusicManager.silentMusicToasts);
		this.addRenderableWidget(this.playToastSound);

		this.minDelay = new EditBox(this.font, this.leftPos + 26, this.topPos + 53, 26, 12, Component.translatable("gui.musicmanager.min_song_delay"));
		this.minDelay.setMaxLength(10);
		this.minDelay.setFilter(s -> StringUtils.isNumeric(s) || s.isEmpty());
		this.minDelay.setBordered(false);
		this.minDelay.setTextColor(16777215);
		this.minDelay.setFocus(false);
		this.minDelay.setValue(String.valueOf(MusicManager.minSongDelay));
		this.addRenderableWidget(this.minDelay);

		this.maxDelay = new EditBox(this.font, this.leftPos + 86, this.topPos + 53, 32, 12, Component.translatable("gui.musicmanager.max_song_delay"));
		this.maxDelay.setMaxLength(10);
		this.maxDelay.setFilter(s -> StringUtils.isNumeric(s) || s.isEmpty());
		this.maxDelay.setBordered(false);
		this.maxDelay.setTextColor(16777215);
		this.maxDelay.setFocus(false);
		this.maxDelay.setValue(String.valueOf(MusicManager.maxSongDelay));
		this.addRenderableWidget(this.maxDelay);

		this.addRenderableWidget(new Button(this.leftPos + 14, this.topPos + 69, 54, 12, Component.translatable("gui.musicmanager.save"), button -> this.onClose()));
		this.addRenderableWidget(new Button(this.leftPos + 74, this.topPos + 69, 54, 12, Component.translatable("gui.musicmanager.exit"), button -> Minecraft.getInstance().popGuiLayer()));
	}

	@Override
	public void tick() {
		super.tick();
		this.minDelay.tick();
		this.maxDelay.tick();
	}

	@Override
	public void render(PoseStack stack, int x, int y, float partialTicks) {
		RenderSystem.setShaderTexture(0, TEXTURE);
		this.blit(stack, this.leftPos, this.topPos, 0, 0, 142, 86);
		super.render(stack, x, y, partialTicks);
		this.font.draw(stack, this.title, this.leftPos + 50 - this.title.getString().length(), this.topPos + 6, 4210752);
		this.renderHoverTooltips(stack, x, y);
		Minecraft.getInstance().getItemRenderer().renderGuiItem(new ItemStack(Items.MUSIC_DISC_CAT), this.leftPos + 26, this.topPos + 22);
	}

	private void renderHoverTooltips(PoseStack stack, int x, int y) {
		if (this.isHovering(44, 24, 12, 12, x, y)) {
			this.renderTooltip(stack, this.font.split(Component.translatable("gui.musicmanager.display_record_toast.desc"), 200), x, y);
		} else if (this.isHovering(101, 24, 12, 12, x, y)) {
			this.renderTooltip(stack, this.font.split(Component.translatable("gui.musicmanager.play_toast_sound.desc"), 175), x, y);
		} else if (this.isHovering(25, 51, 32, 12, x, y) && !this.minDelay.isFocused()) {
			this.renderTooltip(stack, this.font.split(Component.translatable("gui.musicmanager.min_song_delay.desc"), 200), x, y);
		} else if (this.isHovering(85, 51, 32, 12, x, y) && !this.maxDelay.isFocused()) {
			this.renderTooltip(stack, this.font.split(Component.translatable("gui.musicmanager.max_song_delay.desc"), 175), x, y);
		}
	}

	protected boolean isHovering(int startX, int startY, int xSize, int ySize, double x, double y) {
		int i = this.leftPos;
		int j = this.topPos;
		x -= i;
		y -= j;
		return x >= (startX - 1) && x < (startX + xSize + 1) && y >= (startY - 1) && y < (startY + ySize + 1);
	}

	@Override
	public void onClose() {
		MusicManager.CLIENT.displayRecordToast.set(this.displayRecordToast.selected);
		MusicManager.CLIENT.silentMusicToasts.set(!this.playToastSound.selected);
		MusicManager.CLIENT.minSongDelay.set(Integer.valueOf(this.minDelay.getValue()));
		MusicManager.CLIENT.maxSongDelay.set(Integer.valueOf(this.maxDelay.getValue()));
		MusicManager.rebakeOptions();
		super.onClose();
	}

	@Override
	public boolean shouldCloseOnEsc() {
		return false;
	}

	@Override
	public boolean keyPressed(int key, int scanCode, int modifiers) {
		if (key == GLFW.GLFW_KEY_ESCAPE) {
			Minecraft.getInstance().popGuiLayer();
			return true;
		}
		return super.keyPressed(key, scanCode, modifiers);
	}

	@Override
	public boolean isPauseScreen() {
		return false;
	}

	public static class CheckBox extends AbstractButton {
		private boolean selected;

		public CheckBox(int x, int y, Component message, boolean selected) {
			super(x, y, 12, 12, message);
			this.selected = selected;
		}

		@Override
		public void onPress() {
			this.selected = !this.selected;
		}

		@Override
		public void updateNarration(NarrationElementOutput output) {
			output.add(NarratedElementType.TITLE, this.createNarrationMessage());
			if (this.active) {
				if (this.isFocused()) {
					output.add(NarratedElementType.USAGE, Component.translatable("narration.checkbox.usage.focused"));
				} else {
					output.add(NarratedElementType.USAGE, Component.translatable("narration.checkbox.usage.hovered"));
				}
			}
		}

		@Override
		public void render(PoseStack stack, int x, int y, float partialTicks) {
			RenderSystem.enableDepthTest();
			RenderSystem.setShaderTexture(0, TEXTURE);
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, this.alpha);
			RenderSystem.enableBlend();
			this.blit(stack, this.x, this.y, this.isHoveredOrFocused() ? 14 : 0, this.selected ? 100 : 86, 14, 14);
			RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}
