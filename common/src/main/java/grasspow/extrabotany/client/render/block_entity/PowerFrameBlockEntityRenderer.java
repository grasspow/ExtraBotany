package grasspow.extrabotany.client.render.block_entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import grasspow.extrabotany.common.block.block_entity.PowerFrameBlockEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderer;
import net.minecraft.client.renderer.blockentity.BlockEntityRendererProvider;
import net.minecraft.world.item.ItemDisplayContext;

public class PowerFrameBlockEntityRenderer implements BlockEntityRenderer<PowerFrameBlockEntity> {
    private int rot = 0;

    public PowerFrameBlockEntityRenderer(BlockEntityRendererProvider.Context ctx) {
    }

    @Override
    public void render(PowerFrameBlockEntity powerFrame, float v, PoseStack matrixStack, MultiBufferSource iRenderTypeBuffer, int combinedLightIn, int combinedOverlayIn) {
        if (rot > 360 * 8) rot = 0;
        rot++;
        var stack = powerFrame.getItem();
        if (!stack.isEmpty()) {
            matrixStack.pushPose();
            matrixStack.translate(0.5F, 0.4F, 0.5F);
            matrixStack.mulPose(Axis.YP.rotationDegrees(rot * 0.125f));
            Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.GROUND, combinedLightIn, combinedOverlayIn, matrixStack, iRenderTypeBuffer, powerFrame.getLevel(), 0);
            matrixStack.popPose();
        }
    }
}
