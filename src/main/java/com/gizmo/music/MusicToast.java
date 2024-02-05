package com.gizmo.music;

import com.google.common.collect.ImmutableList;
import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Objects;

//Heavily modified SystemToast
public final class MusicToast implements Toast {

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
		this.description = Minecraft.getInstance().font.split(description, 160 - MusicToast.TEXT_LEFT_MARGIN - MusicToast.TEXT_RIGHT_MARGIN);
		this.changed = true;
	}

	@Override
	public Visibility render(PoseStack stack, ToastComponent component, long startTime) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderTexture(0, TEXTURE);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		if (this.changed) {
			this.lastChanged = startTime;
			this.changed = false;
		}
		if (this.getDescription().size() <= 1) {
			component.blit(stack, 0, 0, 0, 0, this.width(), this.height());
		} else {
			int m = Math.min(4, this.height() - 28);
			this.renderBackgroundRow(stack, component, 0, 0, 28);

			for (int n = 28; n < this.height() - m; n += 10) {
				this.renderBackgroundRow(stack, component, 16, n, Math.min(16, this.height() - n - m));
			}

			this.renderBackgroundRow(stack, component, 32 - m, this.height() - m, m);
		}
		component.getMinecraft().font.draw(stack, Component.translatable("sounds.musicmanager.now_playing"), TEXT_LEFT_MARGIN, 7, 5046016);

		for (int i = 0; i < this.getDescription().size(); ++i) {
			component.getMinecraft().font.draw(stack, this.getDescription().get(i), TEXT_LEFT_MARGIN, (18 + i * 12), -1);
		}

		component.getMinecraft().getItemRenderer().renderGuiItem(this.getIcon(), 9, (this.height() / 2) - (16 / 2));

		return (startTime - this.lastChanged) < (double)5000L ? Toast.Visibility.SHOW : Toast.Visibility.HIDE;
	}

	private void renderBackgroundRow(PoseStack stack, ToastComponent component, int vOffset, int y, int vHeight) {
		int uWidth = vOffset == 0 ? 20 : 5;
		int n = Math.min(60, this.width() - uWidth);
		component.blit(stack, 0, y, 0, vOffset, uWidth, vHeight);

		for (int o = uWidth; o < this.width() - n; o += 64) {
			component.blit(stack, o, y, 32, vOffset, Math.min(64, this.width() - o - n), vHeight);
		}

		component.blit(stack, this.width() - n, y, 160 - n, vOffset, n, vHeight);
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
