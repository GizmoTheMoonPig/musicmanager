package com.gizmo.music.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.Font;
import net.minecraft.client.gui.GuiComponent;
import net.minecraft.client.gui.components.toasts.Toast;
import net.minecraft.client.gui.components.toasts.ToastComponent;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.network.chat.Component;
import net.minecraft.util.FormattedCharSequence;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

import java.util.List;

//Heavily modified SystemToast
public record MusicToast(Component description, ItemStack stack) implements Toast {

	private static final int TEXT_LEFT_MARGIN = 30;
	private static final int TEXT_RIGHT_MARGIN = 7;

	@Override
	public Visibility render(@NotNull PoseStack stack, @NotNull ToastComponent manager, long startTime) {
		RenderSystem.setShader(GameRenderer::getPositionTexShader);
		RenderSystem.setShaderTexture(0, TEXTURE);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		
		int height = this.height();
		Font font = Minecraft.getInstance().gui.getFont();
		List<FormattedCharSequence> textLines = font.split(this.description(), this.width() - TEXT_LEFT_MARGIN - TEXT_RIGHT_MARGIN);

		if (this.width() == 160 && textLines.size() <= 1) {
			GuiComponent.blit(stack, 0, 0, 0, 0, this.width(), height);
		} else {
			height = this.height() + Math.max(0, textLines.size() - 1) * 12;
			int m = Math.min(4, height - 28);
			this.renderBackgroundRow(stack, this.width(), 0, 0, 28);

			for (int n = 28; n < height - m; n += 10) {
				this.renderBackgroundRow(stack, this.width(), 16, n, Math.min(16, height - n - m));
			}

			this.renderBackgroundRow(stack, this.width(), 32 - m, height - m, m);
		}
		manager.getMinecraft().font.draw(stack, Component.translatable("sounds.musicmanager.now_playing"), TEXT_LEFT_MARGIN, 7.0F, 5046016);

		for (int i = 0; i < textLines.size(); ++i) {
			manager.getMinecraft().font.draw(stack, textLines.get(i), TEXT_LEFT_MARGIN, (float) (18 + i * 12), -1);
		}

		stack.pushPose();
		manager.getMinecraft().getItemRenderer().renderAndDecorateFakeItem(stack, this.stack(), 9, (height / 2) - (16 / 2));
		stack.popPose();

		return startTime >= 5000L ? Toast.Visibility.HIDE : Toast.Visibility.SHOW;
	}

	private void renderBackgroundRow(PoseStack stack, int i, int vOffset, int y, int vHeight) {
		int uWidth = vOffset == 0 ? 20 : 5;
		int n = Math.min(60, i - uWidth);
		GuiComponent.blit(stack, 0, y, 0, vOffset, uWidth, vHeight);

		for (int o = uWidth; o < i - n; o += 64) {
			GuiComponent.blit(stack, o, y, 32, vOffset, Math.min(64, i - o - n), vHeight);
		}

		GuiComponent.blit(stack, i - n, y, 160 - n, vOffset, n, vHeight);
	}
}
