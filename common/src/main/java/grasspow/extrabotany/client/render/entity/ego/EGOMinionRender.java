package grasspow.extrabotany.client.render.entity.ego;

import com.mojang.authlib.GameProfile;
import grasspow.extrabotany.client.model.armor.ArmorModels;
import grasspow.extrabotany.common.entity.ego.EGOMinion;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.model.geom.ModelLayers;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.HumanoidMobRenderer;
import net.minecraft.client.resources.PlayerSkin;
import net.minecraft.resources.ResourceLocation;

public class EGOMinionRender extends HumanoidMobRenderer<EGOMinion, HumanoidModel<EGOMinion>> {

    public EGOMinionRender(EntityRendererProvider.Context ctx) {
        super(ctx, new Model(ctx.bakeLayer(ModelLayers.PLAYER)), 0F);
        ArmorModels.init(ctx);
    }

    @Override
    public ResourceLocation getTextureLocation(EGOMinion entity) {
        if (entity.getCustomName() != null)
            return getPlayerSkin(entity.getCustomName().getString());
        else
            return getPlayerSkin("Notch");
    }


    public static ResourceLocation getPlayerSkin(String name) {
        GameProfile profile = new GameProfile(null, name);
        PlayerSkin insecureSkin =  Minecraft.getInstance().getSkinManager().getInsecureSkin(profile);
        return insecureSkin.texture();
    }

    private static class Model extends HumanoidModel<EGOMinion> {
        Model(ModelPart root) {
            super(root, RenderType::entityCutoutNoCull);
        }
    }
}
