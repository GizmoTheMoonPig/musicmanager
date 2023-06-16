package com.gizmo.music.client;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

//Heavily modified SystemToast
public final class MusicToast implements Toast {

	public static final int TEXT_LEFT_MARGIN = 30;
	public static final int TEXT_RIGHT_MARGIN = 7;
	private final List<FormattedCharSequence> description;
	private final ItemStack icon;

	public MusicToast(Component name, ItemStack icon) {
		this(Minecraft.getInstance().font, name, icon);
	}

	public MusicToast(Font font, Component name, ItemStack icon) {
		this.description = font.split(name, 160 - MusicToast.TEXT_LEFT_MARGIN - MusicToast.TEXT_RIGHT_MARGIN);
		this.icon = icon;
	}

	@Override
	public Visibility render(@NotNull GuiGraphics graphics, @NotNull ToastComponent component, long startTime) {
		int height = 20 + Math.max(1, this.getDescription().size()) * 12;
		if (this.width() == 160 && this.getDescription().size() <= 1) {
			graphics.blit(TEXTURE, 0, 0, 0, 0, this.width(), height);
		} else {
			int m = Math.min(4, height - 28);
			this.renderBackgroundRow(graphics, this.width(), 0, 0, 28);

			for (int n = 28; n < height - m; n += 10) {
				this.renderBackgroundRow(graphics, this.width(), 16, n, Math.min(16, height - n - m));
			}

			this.renderBackgroundRow(graphics, this.width(), 32 - m, height - m, m);
		}
		graphics.drawString(component.getMinecraft().font, Component.translatable("sounds.musicmanager.now_playing"), TEXT_LEFT_MARGIN, 7, 5046016, false);

		for (int i = 0; i < this.getDescription().size(); ++i) {
			graphics.drawString(component.getMinecraft().font, this.getDescription().get(i), TEXT_LEFT_MARGIN, (18 + i * 12), -1, false);
		}

		graphics.renderItem(this.getIcon(), 9, (height / 2) - (16 / 2));

		return startTime >= 5000L ? Visibility.HIDE : Visibility.SHOW;
	}

	private void renderBackgroundRow(GuiGraphics graphics, int i, int vOffset, int y, int vHeight) {
		int uWidth = vOffset == 0 ? 20 : 5;
		int n = Math.min(60, i - uWidth);
		graphics.blit(TEXTURE, 0, y, 0, vOffset, uWidth, vHeight);

		for (int o = uWidth; o < i - n; o += 64) {
			graphics.blit(TEXTURE, o, y, 32, vOffset, Math.min(64, i - o - n), vHeight);
		}

		graphics.blit(TEXTURE, i - n, y, 160 - n, vOffset, n, vHeight);
	}

	public List<FormattedCharSequence> getDescription() {
		return description;
	}

	public ItemStack getIcon() {
		return icon;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		var that = (MusicToast) obj;
		return Objects.equals(this.description, that.description) &&
				Objects.equals(this.icon, that.icon);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.description, this.icon);
	}

	@Override
	public String toString() {
		return "MusicToast[" +
				"description=" + this.description + ", " +
				"stack=" + this.icon + ']';
	}

}
