package com.gizmo.music;

import com.google.common.collect.ImmutableList;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.Objects;

//Heavily modified SystemToast
public final class MusicToast implements Toast {

	private static final ResourceLocation TEXTURE = new ResourceLocation("toast/advancement");
	public static final int TEXT_LEFT_MARGIN = 30;
	public static final int TEXT_RIGHT_MARGIN = 7;
	private Component rawDesc;
	private List<FormattedCharSequence> description;
	private ItemStack icon;
	private long lastChanged;
	private boolean changed;

	private MusicToast(Component name, ItemStack icon) {
		this(Minecraft.getInstance().font, name, icon);
	}

	private MusicToast(Font font, Component name, ItemStack icon) {
		this.rawDesc = name;
		this.description = font.split(name, 160 - MusicToast.TEXT_LEFT_MARGIN - MusicToast.TEXT_RIGHT_MARGIN);
		this.icon = icon;
	}

	public static void addOrReplace(ToastComponent component, Component description, ItemStack icon) {
		MusicToast toast = component.getToast(MusicToast.class, description);
		if (toast == null) {
			component.addToast(new MusicToast(description, icon));
		} else {
			toast.reset(description, icon);
		}
	}

	public void reset(Component description, ItemStack icon) {
		this.icon = icon;
		this.rawDesc = description;
		this.description = ImmutableList.of(description.getVisualOrderText());
		this.changed = true;
	}

	@Override
	public Visibility render(@NotNull GuiGraphics graphics, @NotNull ToastComponent component, long startTime) {
		if (this.changed) {
			this.lastChanged = startTime;
			this.changed = false;
		}
		if (this.width() == 160 && this.getDescription().size() <= 1) {
			graphics.blitSprite(TEXTURE, 0, 0, this.width(), this.height());
		} else {
			int m = Math.min(4, this.height() - 28);
			this.renderBackgroundRow(graphics, 0, 0, 28);

			for (int n = 28; n < this.height() - m; n += 10) {
				this.renderBackgroundRow(graphics, 16, n, Math.min(16, this.height() - n - m));
			}

			this.renderBackgroundRow(graphics, 32 - m, this.height() - m, m);
		}
		graphics.drawString(component.getMinecraft().font, Component.translatable("sounds.musicmanager.now_playing"), TEXT_LEFT_MARGIN, 7, 5046016, false);

		for (int i = 0; i < this.getDescription().size(); ++i) {
			graphics.drawString(component.getMinecraft().font, this.getDescription().get(i), TEXT_LEFT_MARGIN, (18 + i * 12), -1, false);
		}

		graphics.renderItem(this.getIcon(), 9, (this.height() / 2) - (16 / 2));

		return (startTime - this.lastChanged) < (double)5000L * component.getNotificationDisplayTimeMultiplier() ? Toast.Visibility.SHOW : Toast.Visibility.HIDE;
	}

	private void renderBackgroundRow(GuiGraphics graphics, int p_282371_, int p_283613_, int p_282880_) {
		int i = p_282371_ == 0 ? 20 : 5;
		int j = Math.min(60, this.width() - i);
		graphics.blitSprite(TEXTURE, 160, 32, 0, p_282371_, 0, p_283613_, i, p_282880_);

		for(int k = i; k < this.width() - j; k += 64) {
			graphics.blitSprite(TEXTURE, 160, 32, 32, p_282371_, k, p_283613_, Math.min(64, this.width() - k - j), p_282880_);
		}

		graphics.blitSprite(TEXTURE, 160, 32, 160 - j, p_282371_, this.width() - j, p_283613_, j, p_282880_);
	}

	@Override
	public int height() {
		return 20 + Math.max(this.description.size(), 1) * 12;
	}

	@Override
	public Component getToken() {
		return this.rawDesc;
	}

	public List<FormattedCharSequence> getDescription() {
		return this.description;
	}

	public ItemStack getIcon() {
		return this.icon;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) return true;
		if (obj == null || obj.getClass() != this.getClass()) return false;
		MusicToast that = (MusicToast) obj;
		return Objects.equals(this.description, that.description) &&
				Objects.equals(this.icon, that.icon);
	}

	@Override
	public int hashCode() {
		return Objects.hash(this.description, this.icon);
	}

	@Override
	public String toString() {
		return String.format("MusicToast[description=%s, icon=%s]", this.description, this.icon);
	}

}
