package grasspow.extrabotany.client.render.entity;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.math.Axis;
import grasspow.extrabotany.common.entity.projectile.BaseSwordProjectile;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.inventory.InventoryMenu;
import vazkii.botania.client.core.helper.RenderHelper;

import java.util.Objects;

public class BaseProjectileRender extends EntityRenderer<BaseSwordProjectile> {

    private final ItemRenderer itemRenderer;
    public BaseProjectileRender(EntityRendererProvider.Context context) {
        super(context);
        this.itemRenderer = context.getItemRenderer();
    }

    @Override
    public void render(BaseSwordProjectile weapon, float entityYaw, float partialTicks, PoseStack matrixStack, MultiBufferSource bufferIn, int packedLightIn) {
        Objects.requireNonNull(weapon.getWeaponItem());
        Minecraft mc = Minecraft.getInstance();
        matrixStack.pushPose();
        float s = 1.2F;
        matrixStack.scale(s, s, s);
        matrixStack.mulPose(Axis.YP.rotationDegrees(weapon.getRotation() + 90F));
        matrixStack.mulPose(Axis.ZP.rotationDegrees(weapon.getPitch()));
        matrixStack.mulPose(Axis.ZP.rotationDegrees(-45));

        float alpha = 0.9F;
        int color = 0xFFFFFF | ((int) (alpha * 255F)) << 24;
//        BakedModel model = this.itemRenderer.getModel(weapon.getWeaponItem(), weapon.level(), null, weapon.getId());
        BakedModel model = weapon.getIcon();
        RenderHelper.renderItemCustomColor(mc.player, weapon.getWeaponItem(), color, matrixStack, bufferIn, 0xF000F0, OverlayTexture.NO_OVERLAY, model);

        matrixStack.scale(1 / s, 1 / s, 1 / s);
        matrixStack.popPose();
        super.render(weapon, entityYaw, partialTicks, matrixStack, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(BaseSwordProjectile pEntity) {
        return InventoryMenu.BLOCK_ATLAS;
    }
}
