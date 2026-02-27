package grasspow.extrabotany.common.entity;

import grasspow.extrabotany.common.entity.ego.EGO;
import grasspow.extrabotany.common.entity.ego.EGOLandmine;
import grasspow.extrabotany.common.entity.ego.EGOMinion;
import grasspow.extrabotany.common.entity.item.brew.SplashGrenadeEntity;
import grasspow.extrabotany.common.entity.projectile.*;
import grasspow.extrabotany.common.lib.LibEntityNames;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import java.util.function.BiConsumer;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;

public final class ExtraBotanyEntities {

    public static final EntityType<SplashGrenadeEntity> SPLASH_GRENADE = EntityType.Builder
            .<SplashGrenadeEntity>of(SplashGrenadeEntity::new, MobCategory.MISC)
            .sized(0.05F, 0.05F)
            .updateInterval(10)
            .clientTrackingRange(64)
            .build(exbotRL(LibEntityNames.SPLASH_GRENADE).toString());

    public static final EntityType<AuraFireProjectile> AURA_FIRE = EntityType.Builder
            .<AuraFireProjectile>of(AuraFireProjectile::new, MobCategory.MISC)
            .sized(0.1F, 0.1F)
            .updateInterval(10)
            .clientTrackingRange(64)
            .build(exbotRL(LibEntityNames.AURA_FIRE).toString());

    public static final EntityType<InfluxWaverProjectile> INFLUX_WAVER = EntityType.Builder
            .<InfluxWaverProjectile>of(InfluxWaverProjectile::new, MobCategory.MISC)
            .sized(0.05F, 0.05F)
            .updateInterval(10)
            .clientTrackingRange(64)
            .build(exbotRL(LibEntityNames.INFLUX_WAVER).toString());

    public static final EntityType<TrueShadowKatanaProjectile> TRUE_SHADOW_KATANA = EntityType.Builder
            .<TrueShadowKatanaProjectile>of(TrueShadowKatanaProjectile::new, MobCategory.MISC)
            .sized(0.05F, 0.05F)
            .updateInterval(10)
            .clientTrackingRange(64)
            .build(exbotRL(LibEntityNames.TRUE_SHADOW_KATANA).toString());

    public static final EntityType<TrueTerraBladeProjectile> TRUE_TERRA_BLADE = EntityType.Builder
            .<TrueTerraBladeProjectile>of(TrueTerraBladeProjectile::new, MobCategory.MISC)
            .sized(0.05F, 0.05F)
            .updateInterval(10)
            .clientTrackingRange(64)
            .build(exbotRL(LibEntityNames.TRUE_TERRA_BLADE).toString());

    public static final EntityType<PhantomSwordProjectile> PHANTOM_SWORD = EntityType.Builder
            .<PhantomSwordProjectile>of(PhantomSwordProjectile::new, MobCategory.MISC)
            .sized(0.05F, 0.05F)
            .updateInterval(10)
            .clientTrackingRange(64)
            .build(exbotRL(LibEntityNames.PHANTOM_SWORD).toString());

    public static final EntityType<MagicArrowProjectile> MAGIC_ARROW = EntityType.Builder
            .<MagicArrowProjectile>of(MagicArrowProjectile::new, MobCategory.MISC)
            .sized(0.05F, 0.05F)
            .updateInterval(10)
            .clientTrackingRange(64)
            .build(exbotRL(LibEntityNames.MAGIC_ARROW).toString());
    //ego
    public static final EntityType<EGO> EGO = EntityType.Builder
            .<EGO>of(EGO::new, MobCategory.MONSTER)
            .sized(0.6F, 1.8F)
            .fireImmune()
            .clientTrackingRange(10)
            .updateInterval(10)
            .build(exbotRL(LibEntityNames.EGO).toString());
    public static final EntityType<EGOMinion> EGO_MINION = EntityType.Builder
            .<EGOMinion>of(EGOMinion::new, MobCategory.MONSTER)
            .sized(0.6F, 1.8F)
            .fireImmune()
            .clientTrackingRange(10)
            .updateInterval(2)
            .clientTrackingRange(128)
            .build(exbotRL(LibEntityNames.EGO_MINION).toString());
    public static final EntityType<EGOLandmine> EGO_LANDMINE = EntityType.Builder
            .<EGOLandmine>of(EGOLandmine::new, MobCategory.MISC)
            .sized(3F, 0.1F)
            .updateInterval(2)
            .clientTrackingRange(128)
            .build(exbotRL(LibEntityNames.EGO_LANDMINE).toString());

    public static void registerEntities(BiConsumer<EntityType<?>, ResourceLocation> r) {
        r.accept(SPLASH_GRENADE, exbotRL(LibEntityNames.SPLASH_GRENADE));
        r.accept(AURA_FIRE, exbotRL(LibEntityNames.AURA_FIRE));
        r.accept(INFLUX_WAVER, exbotRL(LibEntityNames.INFLUX_WAVER));
        r.accept(TRUE_SHADOW_KATANA, exbotRL(LibEntityNames.TRUE_SHADOW_KATANA));
        r.accept(TRUE_TERRA_BLADE, exbotRL(LibEntityNames.TRUE_TERRA_BLADE));
        r.accept(PHANTOM_SWORD, exbotRL(LibEntityNames.PHANTOM_SWORD));
        r.accept(MAGIC_ARROW, exbotRL(LibEntityNames.MAGIC_ARROW));

        r.accept(EGO,exbotRL(LibEntityNames.EGO));
        r.accept(EGO_MINION,exbotRL(LibEntityNames.EGO_MINION));
        r.accept(EGO_LANDMINE,exbotRL(LibEntityNames.EGO_LANDMINE));
    }

    private ExtraBotanyEntities() {
    }
}
