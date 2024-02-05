package com.gizmo.music;

import com.mojang.blaze3d.systems.RenderSystem;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.AbstractButton;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.components.Renderable;
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
		this.minDelay.setFocused(false);
		this.minDelay.setValue(String.valueOf(MusicManager.minSongDelay));
		this.addRenderableWidget(this.minDelay);

		this.maxDelay = new EditBox(this.font, this.leftPos + 86, this.topPos + 53, 32, 12, Component.translatable("gui.musicmanager.max_song_delay"));
		this.maxDelay.setMaxLength(10);
		this.maxDelay.setFilter(s -> StringUtils.isNumeric(s) || s.isEmpty());
		this.maxDelay.setBordered(false);
		this.maxDelay.setTextColor(16777215);
		this.maxDelay.setFocused(false);
		this.maxDelay.setValue(String.valueOf(MusicManager.maxSongDelay));
		this.addRenderableWidget(this.maxDelay);

		this.addRenderableWidget(Button.builder(Component.translatable("gui.musicmanager.save"), button -> this.onClose()).bounds(this.leftPos + 14, this.topPos + 69, 54, 12).build());
		this.addRenderableWidget(Button.builder(Component.translatable("gui.musicmanager.exit"), button -> Minecraft.getInstance().popGuiLayer()).bounds(this.leftPos + 74, this.topPos + 69, 54, 12).build());
	}

	@Override
	public void render(GuiGraphics graphics, int x, int y, float partialTicks) {
		super.render(graphics, x, y, partialTicks);
		graphics.drawString(this.font, this.title, this.leftPos + 50 - this.title.getString().length(), this.topPos + 6, 4210752, false);
		this.renderHoverTooltips(graphics, x, y);
		graphics.renderItem(new ItemStack(Items.MUSIC_DISC_CAT), this.leftPos + 26, this.topPos + 22);
	}

	@Override
	public void renderBackground(GuiGraphics graphics, int x, int y, float partialTicks) {
		super.renderBackground(graphics, x, y, partialTicks);
		graphics.blit(TEXTURE, this.leftPos, this.topPos, 0, 0, 142, 86);
	}

	private void renderHoverTooltips(GuiGraphics graphics, int x, int y) {
		if (this.isHovering(44, 24, 12, 12, x, y)) {
			graphics.renderTooltip(this.font, this.font.split(Component.translatable("gui.musicmanager.display_record_toast.desc"), 200), x, y);
		} else if (this.isHovering(101, 24, 12, 12, x, y)) {
			graphics.renderTooltip(this.font, this.font.split(Component.translatable("gui.musicmanager.play_toast_sound.desc"), 175), x, y);
		} else if (this.isHovering(25, 51, 32, 12, x, y) && !this.minDelay.isFocused()) {
			graphics.renderTooltip(this.font, this.font.split(Component.translatable("gui.musicmanager.min_song_delay.desc"), 200), x, y);
		} else if (this.isHovering(85, 51, 32, 12, x, y) && !this.maxDelay.isFocused()) {
			graphics.renderTooltip(this.font, this.font.split(Component.translatable("gui.musicmanager.max_song_delay.desc"), 175), x, y);
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
		private static final ResourceLocation CHECKBOX_SELECTED_HOVERED_SPRITE = new ResourceLocation(MusicManager.MODID, "check_box_selected_hovered");
		private static final ResourceLocation CHECKBOX_SELECTED_SPRITE = new ResourceLocation(MusicManager.MODID, "check_box_selected");
		private static final ResourceLocation CHECKBOX_HOVERED_SPRITE = new ResourceLocation(MusicManager.MODID, "check_box_hovered");
		private static final ResourceLocation CHECKBOX_SPRITE = new ResourceLocation(MusicManager.MODID, "check_box");

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
		protected void updateWidgetNarration(NarrationElementOutput output) {
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
		public void renderWidget(GuiGraphics graphics, int x, int y, float partialTicks) {
			RenderSystem.enableDepthTest();
			graphics.setColor(1.0F, 1.0F, 1.0F, this.alpha);
			RenderSystem.enableBlend();
			ResourceLocation resourcelocation;
			if (this.selected) {
				resourcelocation = this.isHoveredOrFocused() ? CHECKBOX_SELECTED_HOVERED_SPRITE : CHECKBOX_SELECTED_SPRITE;
			} else {
				resourcelocation = this.isHoveredOrFocused() ? CHECKBOX_HOVERED_SPRITE : CHECKBOX_SPRITE;
			}
			graphics.blitSprite(resourcelocation, this.getX(), this.getY(), 14, 14);
			graphics.setColor(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}
