package grasspow.extrabotany.common.entity.projectile;

import grasspow.extrabotany.client.core.handler.MiscellaneousModels;
import grasspow.extrabotany.common.entity.ExtraBotanyEntities;
import grasspow.extrabotany.common.handler.DamageHandler;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import vazkii.botania.client.core.proxy.ClientProxy;
import vazkii.botania.client.fx.WispParticleData;

import java.util.List;

import static grasspow.extrabotany.common.lib.CommonHelper.getFilteredEntities;

public class InfluxWaverProjectile extends BaseSwordProjectile {

    private static final String TAG_STRIKE_TIMES = "strike_times";
    private static final String TAG_NEXT = "next";

    private static final EntityDataAccessor<Integer> STRIKE_TIMES = SynchedEntityData.defineId(InfluxWaverProjectile.class,
            EntityDataSerializers.INT);
    private static final EntityDataAccessor<BlockPos> NEXT = SynchedEntityData.defineId(InfluxWaverProjectile.class,
            EntityDataSerializers.BLOCK_POS);

    private int removeFlag = -1;

    public InfluxWaverProjectile(EntityType<? extends ThrowableProjectile> type, Level level) {
        super(type, level);
    }

    public InfluxWaverProjectile(Level level, LivingEntity owner, float damageTime) {
        super(ExtraBotanyEntities.INFLUX_WAVER, level, owner, damageTime);
    }

    @Override
    public void tick() {
        if (this.removeFlag != -1 && this.tickCount >= this.removeFlag + 4) {
            if (!level().isClientSide && !getNext().equals(BlockPos.ZERO)) {
                InfluxWaverProjectile proj = make(getNext());
                level().addFreshEntity(proj);
                discard();
            }
        }

        super.tick();

        if (getDeltaMovement().equals(Vec3.ZERO) || this.removeFlag != -1)
            return;

        if (level().isClientSide && tickCount % 2 == 0) {
            WispParticleData data = WispParticleData.wisp(0.3F, 0.1F, 0.1F, 0.85F, 1F);

            ClientProxy.INSTANCE.addParticleForceNear(level(), data, getX(), getY(), getZ(), 0, 0, 0);
        }

        if (!level().isClientSide) {
            AABB axis = new AABB(getX(), getY(), getZ(), xOld, yOld, zOld).inflate(2);
            List<LivingEntity> entities = level().getEntitiesOfClass(LivingEntity.class, axis);
            boolean flag = false;
            List<LivingEntity> list = getFilteredEntities(entities, getOwner());
            for (LivingEntity living : list) {
                if (!living.isRemoved()) {
                    living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 60, 1));
                    if (getOwner() instanceof Player) {
                        DamageHandler.INSTANCE.dmg(living, getOwner(), 12F, DamageHandler.INSTANCE.GENERAL_PIERCING);
                    } else {
                        if (living.invulnerableTime == 0)
                            DamageHandler.INSTANCE.dmg(living, getOwner(), 2.5F, DamageHandler.INSTANCE.LIFE_LOSING);
                        DamageHandler.INSTANCE.dmg(living, getOwner(), 7F, DamageHandler.INSTANCE.MAGIC);
                    }
                    flag = living.isRemoved();
                    if (getStrikeTimes() > 0 && !flag) {
                        setNext(living.blockPosition().offset(0, 1, 0));
                        removeFlag = this.tickCount;
                    }
                    break;
                }
            }

            if (getStrikeTimes() > 0 && flag) {
                axis = axis.inflate(5D);
                List<LivingEntity> others = level().getEntitiesOfClass(LivingEntity.class, axis);
                List<LivingEntity> olist = getFilteredEntities(others, getOwner());
                for (LivingEntity living : olist) {
                    setNext(living.blockPosition().offset(0, 1, 0));
                    removeFlag = this.tickCount;
                    break;
                }
            }
        }
    }

    public InfluxWaverProjectile make(BlockPos targetpos) {
        InfluxWaverProjectile proj = new InfluxWaverProjectile(level(), (LivingEntity) getOwner(), damageTime);
        float range = 6F;
        double j = -Math.PI + 2 * Math.PI * Math.random();
        double k;
        double x, y, z;
        k = 0.12F * Math.PI * Math.random() + 0.28F * Math.PI;
        x = targetpos.getX() + range * Math.sin(k) * Math.cos(j);
        y = targetpos.getY() + range * Math.cos(k);
        z = targetpos.getZ() + range * Math.sin(k) * Math.sin(j);
        proj.setPos(x, y, z);
        proj.setTargetPos(targetpos);
        proj.faceTarget(0.8F);
        proj.setStrikeTimes(getStrikeTimes() - 1);
        return proj;
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        builder.define(STRIKE_TIMES, 0);
        builder.define(NEXT, BlockPos.ZERO);
    }

    @Override
    public void addAdditionalSaveData(CompoundTag cmp) {
        super.addAdditionalSaveData(cmp);
        cmp.putInt(TAG_STRIKE_TIMES, getStrikeTimes());
        cmp.putLong(TAG_NEXT, getNext().asLong());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag cmp) {
        super.readAdditionalSaveData(cmp);
        setStrikeTimes(cmp.getInt(TAG_STRIKE_TIMES));
        setNext(BlockPos.of(cmp.getLong(TAG_NEXT)));
    }

    public int getStrikeTimes() {
        return getEntityData().get(STRIKE_TIMES);
    }

    public void setStrikeTimes(int i) {
        getEntityData().set(STRIKE_TIMES, i);
    }

    public BlockPos getNext() {
        return getEntityData().get(NEXT);
    }

    public void setNext(BlockPos pos) {
        getEntityData().set(NEXT, pos);
    }

    @Override
    public int getLifeTicks() {
        return 60;
    }

    @Override
    public int getTickPerBlock() {
        return 10;
    }

    @Override
    public int getTickBreakBlockCap() {
        return 30;
    }

    public BakedModel getIcon() {
        return MiscellaneousModels.INSTANCE.influxWaverProjectileModel;
    }
}
