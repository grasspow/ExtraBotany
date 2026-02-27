package grasspow.extrabotany.client.render.entity.ego;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.mojang.authlib.GameProfile;
import grasspow.extrabotany.client.model.armor.ArmorModels;
import grasspow.extrabotany.common.entity.ego.EGO;
import grasspow.extrabotany.common.entity.ego.EGOMinion;
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
import net.minecraft.world.entity.monster.Monster;
import org.jetbrains.annotations.NotNull;

import javax.annotation.Nonnull;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class EGOMinionRender extends HumanoidMobRenderer<EGOMinion, HumanoidModel<EGOMinion>> {
    private static final Cache<String, GameProfile> GAME_PROFILE_CACHE = CacheBuilder.newBuilder().expireAfterAccess(30, TimeUnit.MINUTES).build();
    private static final ExecutorService THREAD_POOL = new ThreadPoolExecutor(0, 2, 1, TimeUnit.MINUTES, new LinkedBlockingQueue());
    private static final GameProfile EMPTY_GAME_PROFILE = new GameProfile(null, "EMPTY");

    private static final ResourceLocation TEXTURE_ALEX = ResourceLocation.parse("textures/entity/alex.png");


    public EGOMinionRender(EntityRendererProvider.Context ctx) {
        super(ctx, new Model(ctx.bakeLayer(ModelLayers.PLAYER)), 0F);
        ArmorModels.init(ctx);
    }

    @Override
    public ResourceLocation getTextureLocation(EGOMinion entity) {
        Minecraft mc = Minecraft.getInstance();

        if (!(mc.getCameraEntity() instanceof AbstractClientPlayer clientPlayer)) {
            return DefaultPlayerSkin.get(entity.getUUID()).texture();
        }

        return clientPlayer.getSkin().texture();
    }

    private static class Model extends HumanoidModel<EGOMinion> {
        Model(ModelPart root) {
            super(root, RenderType::entityCutoutNoCull);
        }
    }
}
