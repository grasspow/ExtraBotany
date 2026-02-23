package grasspow.extrabotany.common.item.equipment.bauble;

import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemDisplayContext;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import vazkii.botania.api.item.CosmeticBauble;
import vazkii.botania.client.render.AccessoryRenderRegistry;
import vazkii.botania.client.render.AccessoryRenderer;
import vazkii.botania.common.item.equipment.bauble.BaubleItem;
import vazkii.botania.common.proxy.Proxy;

import java.util.List;

public class CosmeticBaubleItem extends BaubleItem implements CosmeticBauble {

    public enum Variant {
        FOX_EAR(true),
        FOX_MASK(true),
        PYLON(true),
        BLACK_GLASSES(true),
        RED_SCARF,
        SUPER_CROWN(true),
        THUG_LIFE(true),
        MASK(true);

        private final boolean isHead;

        Variant(boolean isHead) {
            this.isHead = isHead;
        }

        Variant() {
            this(false);
        }
    }

    private final Variant variant;

    public CosmeticBaubleItem(Variant variant, Properties props) {
        super(props);
        this.variant = variant;
        Proxy.INSTANCE.runOnClient(() -> () -> AccessoryRenderRegistry.register(this, new Renderer()));
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flags) {
        tooltip.add(Component.translatable("botaniamisc.cosmeticBauble").withStyle(ChatFormatting.ITALIC, ChatFormatting.GRAY));
        super.appendHoverText(stack, context, tooltip, flags);
    }

    public static class Renderer implements AccessoryRenderer {
        @Override
        public void doRender(HumanoidModel<?> bipedModel, ItemStack stack, LivingEntity living, PoseStack ms, MultiBufferSource buffers, int light, float limbSwing, float limbSwingAmount, float partialTicks, float ageInTicks, float netHeadYaw, float headPitch) {
            Variant variant = ((CosmeticBaubleItem) stack.getItem()).variant;
            if (variant.isHead) {
                bipedModel.head.translateAndRotate(ms);
                switch (variant) {
                    case FOX_EAR:
                        ms.translate(0, -0.8, -0.1F);
                        ms.scale(0.8F, -0.8F, -0.8F);
                        renderItem(stack, ms, buffers, light);
                        break;
                    case FOX_MASK:
                        ms.translate(0.02F, -0.3, -0.3);
                        ms.scale(0.66F, -0.65F, -0.65F);
                        renderItem(stack, ms, buffers, light);
                        break;
                    case MASK:
                        ms.translate(0F, -0.3, -0.3);
                        ms.scale(0.65F, -0.65F, -0.65F);
                        renderItem(stack, ms, buffers, light);
                        break;
                    case BLACK_GLASSES:
                        ms.translate(0, -0.2, -0.3);
                        ms.scale(0.55F, 0.55F, -0.55F);
                        renderItem(stack, ms, buffers, light);
                        break;
                    case SUPER_CROWN:
                        ms.translate(0, -0.7, -0.1F);
                        ms.scale(0.65F, -0.65F, -0.65F);
                        renderItem(stack, ms, buffers, light);
                        break;
                    case PYLON:
                        ms.translate(0, -0.8, -0.1F);
                        ms.scale(0.5F, -0.5F, -0.5F);
                        renderItem(stack, ms, buffers, light);
                        break;
                    case THUG_LIFE:
                        ms.translate(0, -0.2, -0.3);
                        ms.scale(0.7F, -0.7F, -0.7F);
                        renderItem(stack, ms, buffers, light);
                        break;
                }
            } else { // body cosmetics
                bipedModel.body.translateAndRotate(ms);
                switch (variant) {
                    case RED_SCARF:
                        ms.translate(0, 0.16, -0.15);
                        ms.scale(0.55F, -0.55F, -0.55F);
                        renderItem(stack, ms, buffers, light);
                        break;
                    default: {
                    }
                }
            }
        }

        private static void renderItem(ItemStack stack, PoseStack ms, MultiBufferSource buffers, int light) {
            Minecraft.getInstance().getItemRenderer().renderStatic(stack, ItemDisplayContext.NONE,
                    light, OverlayTexture.NO_OVERLAY, ms, buffers, Minecraft.getInstance().level, 0);
        }
    }


}
