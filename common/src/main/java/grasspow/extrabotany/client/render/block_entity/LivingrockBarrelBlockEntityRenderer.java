/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
package grasspow.extrabotany.client.render.block_entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import grasspow.extrabotany.common.block.block_entity.LivingrockBarrelBlockEntity;
import net.minecraft.client.renderer.BiomeColors;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.Sheets;
import net.minecraft.client.renderer.block.BlockRenderDispatcher;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.world.level.block.Blocks;
import org.jetbrains.annotations.NotNull;
import org.joml.Matrix4f;
import vazkii.botania.common.helper.VecHelper;

public class LivingrockBarrelBlockEntityRenderer implements BlockEntityRenderer<LivingrockBarrelBlockEntity> {
    private final BlockRenderDispatcher blockRenderDispatcher;

    public LivingrockBarrelBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
        this.blockRenderDispatcher = ctx.getBlockRenderDispatcher();
    }

    @Override
    public void render(@NotNull LivingrockBarrelBlockEntity barrel, float f, PoseStack ms, MultiBufferSource buffers, int light, int overlay) {
//        if (barrel.getFluidAmount() > 0) {
//            float fillAmount = barrel.getFluidProportion();
//            ms.pushPose();
//            ms.translate(0, fillAmount * 0.9F, 0);
//            ms.mulPose(VecHelper.rotateX(90F));
//            ms.scale(1 / 16F, 1 / 16F, 1 / 16F);
//
//            TextureAtlasSprite sprite = this.blockRenderDispatcher.getBlockModel(Blocks.WATER.defaultBlockState()).getParticleIcon();
//            int color = BiomeColors.getAverageWaterColor(barrel.getLevel(), barrel.getBlockPos());
//            VertexConsumer buffer = buffers.getBuffer(Sheets.translucentCullBlockSheet());
//            renderIcon(ms, buffer, sprite, color, 0.7F, overlay, light);
//            ms.popPose();
//        }

        float fillAmount = 2000;
        ms.pushPose();
        ms.translate(0, fillAmount * 0.9F, 0);
        ms.mulPose(VecHelper.rotateX(90F));
        ms.scale(1 / 16F, 1 / 16F, 1 / 16F);

        TextureAtlasSprite sprite = this.blockRenderDispatcher.getBlockModel(Blocks.WATER.defaultBlockState()).getParticleIcon();
        int color = BiomeColors.getAverageWaterColor(barrel.getLevel(), barrel.getBlockPos());
        VertexConsumer buffer = buffers.getBuffer(Sheets.translucentCullBlockSheet());
        renderIcon(ms, buffer, sprite, color, 0.7F, overlay, light);
        ms.popPose();
    }

    //copy from botania PetalApothecaryBlockEntityRenderer
    private void renderIcon(PoseStack ms, VertexConsumer builder, TextureAtlasSprite sprite, int color, float alpha, int overlay, int light) {
        int red = ((color >> 16) & 0xFF);
        int green = ((color >> 8) & 0xFF);
        int blue = (color & 0xFF);
        Matrix4f mat = ms.last().pose();
        int start = 3;
        int end = 13;
        builder.addVertex(mat, start, end, 0).setColor(red, green, blue, (int) (alpha * 255F))
                .setUv(sprite.getU(start), sprite.getV(end)).setOverlay(overlay).setLight(light).setNormal(0, 0, 1);
        builder.addVertex(mat, end, end, 0).setColor(red, green, blue, (int) (alpha * 255F))
                .setUv(sprite.getU(end), sprite.getV(end)).setOverlay(overlay).setLight(light).setNormal(0, 0, 1);
        builder.addVertex(mat, end, start, 0).setColor(red, green, blue, (int) (alpha * 255F))
                .setUv(sprite.getU(end), sprite.getV(start)).setOverlay(overlay).setLight(light).setNormal(0, 0, 1);
        builder.addVertex(mat, start, start, 0).setColor(red, green, blue, (int) (alpha * 255F))
                .setUv(sprite.getU(start), sprite.getV(start)).setOverlay(overlay).setLight(light).setNormal(0, 0, 1);
    }
}
