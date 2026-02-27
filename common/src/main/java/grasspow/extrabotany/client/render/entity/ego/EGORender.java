package grasspow.extrabotany.client.render.entity.ego;

import grasspow.extrabotany.client.model.armor.ArmorModels;
import grasspow.extrabotany.common.entity.ego.EGO;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.resources.DefaultPlayerSkin;
import net.minecraft.resources.ResourceLocation;

public class EGORender extends HumanoidMobRenderer<EGO, HumanoidModel<EGO>> {
    public EGORender(EntityRendererProvider.Context ctx) {
        super(ctx, new Model(ctx.bakeLayer(ModelLayers.PLAYER)), 0F);
        ArmorModels.init(ctx);
    }

    @Override
    public ResourceLocation getTextureLocation(EGO mob) {
        Minecraft mc = Minecraft.getInstance();

        if (!(mc.getCameraEntity() instanceof AbstractClientPlayer clientPlayer)) {
            return DefaultPlayerSkin.get(mob.getUUID()).texture();
        }

        return clientPlayer.getSkin().texture();
    }

    private static class Model extends HumanoidModel<EGO> {
        Model(ModelPart root) {
            super(root, RenderType::entityCutoutNoCull);
        }
    }
}
