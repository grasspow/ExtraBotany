package grasspow.extrabotany.client.render.entity;

import grasspow.extrabotany.client.render.block_entity.LivingrockBarrelBlockEntityRenderer;
import grasspow.extrabotany.client.render.block_entity.PedestalBlockEntityRenderer;
import grasspow.extrabotany.client.render.block_entity.PowerFrameBlockEntityRenderer;
import grasspow.extrabotany.client.render.entity.ego.EGOLandmineRender;
import grasspow.extrabotany.client.render.entity.ego.EGOMinionRender;
import grasspow.extrabotany.client.render.entity.ego.EGORender;
import grasspow.extrabotany.common.block.block_entity.ExtraBotanyBlockEntities;
import grasspow.extrabotany.common.entity.ExtraBotanyEntities;
import net.minecraft.client.renderer.entity.NoopRenderer;
import vazkii.botania.client.render.block_entity.SpecialFlowerBlockEntityRenderer;
import vazkii.botania.client.render.entity.EntityRenderers;

public final class ExtraBotanyEntityRenderers {

    public static void registerEntityRenderers(EntityRenderers.EntityRendererConsumer consumer) {
        consumer.accept(ExtraBotanyEntities.SPLASH_GRENADE, NoopRenderer::new);
        consumer.accept(ExtraBotanyEntities.AURA_FIRE, DummyRender::new);
        consumer.accept(ExtraBotanyEntities.INFLUX_WAVER, BaseProjectileRender::new);
        consumer.accept(ExtraBotanyEntities.TRUE_SHADOW_KATANA, BaseProjectileRender::new);
        consumer.accept(ExtraBotanyEntities.TRUE_TERRA_BLADE, BaseProjectileRender::new);
        consumer.accept(ExtraBotanyEntities.PHANTOM_SWORD, BaseProjectileRender::new);
        consumer.accept(ExtraBotanyEntities.MAGIC_ARROW, DummyRender::new);

        //ego
        consumer.accept( ExtraBotanyEntities.EGO, EGORender::new);
        consumer.accept( ExtraBotanyEntities.EGO_MINION, EGOMinionRender::new);
        consumer.accept( ExtraBotanyEntities.EGO_LANDMINE, EGOLandmineRender::new);
    }

    public static void registerBlockEntityRenderers(EntityRenderers.BERConsumer consumer) {
        consumer.register(ExtraBotanyBlockEntities.PEDESTAL, PedestalBlockEntityRenderer::new);
        consumer.register(ExtraBotanyBlockEntities.LIVINGROCK_BARREL, LivingrockBarrelBlockEntityRenderer::new);
        consumer.register(ExtraBotanyBlockEntities.POWER_FRAME, PowerFrameBlockEntityRenderer::new);

        consumer.register(ExtraBotanyBlockEntities.ANNOYING_FLOWER, SpecialFlowerBlockEntityRenderer::new);
        consumer.register(ExtraBotanyBlockEntities.SERENITIAN, SpecialFlowerBlockEntityRenderer::new);
        consumer.register(ExtraBotanyBlockEntities.BELL_FLOWER, SpecialFlowerBlockEntityRenderer::new);
        consumer.register(ExtraBotanyBlockEntities.EDELWEISS, SpecialFlowerBlockEntityRenderer::new);
        consumer.register(ExtraBotanyBlockEntities.GEMINI_ORCHID, SpecialFlowerBlockEntityRenderer::new);
        consumer.register(ExtraBotanyBlockEntities.SUN_BLESS, SpecialFlowerBlockEntityRenderer::new);
        consumer.register(ExtraBotanyBlockEntities.MOON_BLESS, SpecialFlowerBlockEntityRenderer::new);
        consumer.register(ExtraBotanyBlockEntities.OMNI_VIOLET, SpecialFlowerBlockEntityRenderer::new);
        consumer.register(ExtraBotanyBlockEntities.REIKAR_LILY, SpecialFlowerBlockEntityRenderer::new);
        consumer.register(ExtraBotanyBlockEntities.TINKLE_FLOWER, SpecialFlowerBlockEntityRenderer::new);
    }

    private ExtraBotanyEntityRenderers() {
    }
}
