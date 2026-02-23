package grasspow.extrabotany.common.entity.projectile;

import grasspow.extrabotany.client.core.handler.MiscellaneousModels;
import grasspow.extrabotany.common.entity.ExtraBotanyEntities;
import grasspow.extrabotany.common.handler.DamageHandler;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;

import static grasspow.extrabotany.common.lib.CommonHelper.getFilteredEntities;

public class TrueShadowKatanaProjectile extends BaseSwordProjectile {

    public TrueShadowKatanaProjectile(EntityType<? extends ThrowableProjectile> type, Level level) {
        super(type, level);
    }

    @Override
    public int getLifeTicks() {
        return 40;
    }

    @Override
    public int getTickPerBlock() {
        return 20;
    }

    @Override
    public int getTickBreakBlockCap() {
        return 35;
    }

    public TrueShadowKatanaProjectile(Level level, LivingEntity thrower, float damageTime) {
        super(ExtraBotanyEntities.TRUE_SHADOW_KATANA, level, thrower, damageTime);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {

    }

    @Override
    public void tick() {
        super.tick();
        if (getDeltaMovement().equals(Vec3.ZERO) || this.tickCount <= 3)
            return;
        if (level().isClientSide && tickCount % 2 == 0) {
            level().addParticle(ParticleTypes.END_ROD, getX(), getY(), getZ(), 0D, 0D, 0D);
        }
        if (!level().isClientSide) {
            AABB axis = new AABB(getX(), getY(), getZ(), xOld, yOld, zOld).inflate(1);
            List<LivingEntity> entities = level().getEntitiesOfClass(LivingEntity.class, axis);
            List<LivingEntity> list = getFilteredEntities(entities, getOwner());
            for (LivingEntity living : list) {
                if (getOwner() != null) {
                    DamageHandler.INSTANCE.dmg(living, getOwner(), 5F, DamageHandler.INSTANCE.GENERAL);
                } else {
                    if (living.invulnerableTime == 0)
                        DamageHandler.INSTANCE.dmg(living, getOwner(), 2F, DamageHandler.INSTANCE.LIFE_LOSING);
                    DamageHandler.INSTANCE.dmg(living, getOwner(), 5.5F, DamageHandler.INSTANCE.MAGIC);
                }
                discard();
                break;
            }
        }
    }

    public BakedModel getIcon() {
        return MiscellaneousModels.INSTANCE.trueShadowKatanaProjectileModel;
    }
}
