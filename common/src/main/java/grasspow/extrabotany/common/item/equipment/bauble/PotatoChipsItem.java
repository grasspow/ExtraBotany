package grasspow.extrabotany.common.item.equipment.bauble;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.client.render.AccessoryRenderRegistry;
import vazkii.botania.client.render.AccessoryRenderer;
import vazkii.botania.common.handler.EquipmentHandler;
import vazkii.botania.common.item.equipment.bauble.BaubleItem;
import vazkii.botania.common.proxy.Proxy;

public class PotatoChipsItem extends BaubleItem {

    public static final int MANA_PER_DAMAGE = 3000;

    public PotatoChipsItem(Properties props) {
        super(props);
        Proxy.INSTANCE.runOnClient(() -> () -> AccessoryRenderRegistry.register(this, new Renderer()));
    }

    @Override
    public boolean canEquip(ItemStack stack, LivingEntity entity) {
        return EquipmentHandler.findOrEmpty(this, entity).isEmpty();
    }

    public static class Renderer implements AccessoryRenderer {
        @Override
        public void doRender(HumanoidModel<?> bipedModel, ItemStack stack, LivingEntity living, PoseStack ms, MultiBufferSource buffers, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            bipedModel.head.translateAndRotate(ms);
            ms.translate(0, -0.3, -0.3);
            ms.scale(0.6F, -0.6F, -0.6F);
            renderItem(stack, ms, buffers, light);
        }

        public static void renderItem(ItemStack stack, PoseStack ms, MultiBufferSource buffers, int light) {
            Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.NONE,
                    light, OverlayTexture.NO_OVERLAY, ms, buffers, Minecraft.getInstance().level, 0);
        }
    }

}
