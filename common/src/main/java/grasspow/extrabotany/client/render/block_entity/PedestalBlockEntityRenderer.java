package grasspow.extrabotany.client.render.block_entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import grasspow.extrabotany.common.block.block_entity.PedestalBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.ItemDisplayContext;

public class PedestalBlockEntityRenderer implements BlockEntityRenderer<PedestalBlockEntity> {

    private int rot = 0;

    public PedestalBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
    }

    @Override
    public void render(PedestalBlockEntity pedestal, float v, PoseStack matrixStack, MultiBufferSource iRenderTypeBuffer, int combinedLightIn, int combinedOverlayIn) {
        var stack = pedestal.getItem(0);
        if (!stack.isEmpty()) {
            if (rot > 360 * 4) rot = 0;
            rot++;
            matrixStack.pushPose();
            var item = stack.getItem();
            float yOff;
            if (item instanceof BlockItem) {
                yOff = 0F;
            } else {
                yOff = 0.1F;
            }
            matrixStack.translate(0.5F, 0.9F + yOff, 0.5F);
            matrixStack.scale(0.8f, 0.8f, 0.8f);
            matrixStack.mulPose(Axis.YP.rotationDegrees(rot * 0.25f));
            Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.GROUND, combinedLightIn, combinedOverlayIn, matrixStack, iRenderTypeBuffer, pedestal.getLevel(), 0);
            matrixStack.popPose();
        }
    }
}
