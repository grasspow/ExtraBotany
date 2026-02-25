package grasspow.extrabotany.common.entity.projectile;

import grasspow.extrabotany.client.core.handler.MiscellaneousModels;
import grasspow.extrabotany.common.entity.ExtraBotanyEntities;
import grasspow.extrabotany.common.handler.DamageHandler;
import grasspow.extrabotany.common.item.ExtraBotanyItems;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.List;
import java.util.Random;

import static grasspow.extrabotany.common.lib.CommonHelper.getFilteredEntities;

public class PhantomSwordProjectile extends BaseSwordProjectile {
    private static final String TAG_VARIETY = "variety";
    private static final String TAG_DELAY = "delay";
    private static final String TAG_FAKE = "fake";

    private static final EntityDataAccessor<Integer> VARIETY = SynchedEntityData.defineId(PhantomSwordProjectile.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Integer> DELAY = SynchedEntityData.defineId(PhantomSwordProjectile.class, EntityDataSerializers.INT);
    private static final EntityDataAccessor<Boolean> FAKE = SynchedEntityData.defineId(PhantomSwordProjectile.class, EntityDataSerializers.BOOLEAN);

    private static final float[][] rgb = {{0.82F, 0.2F, 0.58F}, {0F, 0.71F, 0.10F}, {0.74F, 0.07F, 0.32F},
            {0.01F, 0.45F, 0.8F}, {0.05F, 0.39F, 0.9F}, {0.38F, 0.34F, 0.42F}, {0.41F, 0.31F, 0.14F},
            {0.92F, 0.92F, 0.21F}, {0.61F, 0.92F, 0.98F}, {0.18F, 0.45F, 0.43F}};

    public PhantomSwordProjectile(EntityType<? extends ThrowableProjectile> type, Level level) {
        super(type, level);
    }

    public PhantomSwordProjectile(Level level) {
        super(ExtraBotanyEntities.PHANTOM_SWORD, level);
    }

    public PhantomSwordProjectile(Level level, LivingEntity thrower, BlockPos targetPos, float damageTime) {
        super(ExtraBotanyEntities.PHANTOM_SWORD, level, thrower, damageTime);
        setTargetPos(targetPos);
        setVariety(new Random().nextInt(10));
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(VARIETY, 0);
        builder.define(DELAY, 0);
        builder.define(FAKE, false);
    }

    @Override
    public void tick() {
        super.tick();

        if (getDelay() > 0) {
            setDelay(getDelay() - 1);
            return;
        }

        if (getFake()) {
            this.setDeltaMovement(0D, 0D, 0D);
            return;
        }

        super.tick();
        if (getDeltaMovement().equals(Vec3.ZERO))
            return;
        if (!level().isClientSide && !getFake() && this.tickCount % 6 == 0) {
            PhantomSwordProjectile illusion = new PhantomSwordProjectile(level());
            illusion.setFake(true);
            illusion.setRotation(this.getRotation());
            illusion.setPitch(this.getPitch());
            illusion.setPos(getX(), getY(), getTargetPosZ());
            illusion.setVariety(getVariety());
            level().addFreshEntity(illusion);
        }

        if (!level().isClientSide) {
            AABB axis = new AABB(getX(), getY(), getZ(), xOld, yOld, zOld).inflate(2);
            List<LivingEntity> entities = level().getEntitiesOfClass(LivingEntity.class, axis);
            List<LivingEntity> list = getFilteredEntities(entities, getOwner());
            for (LivingEntity living : list) {
                if (getOwner() != null) {
                    DamageHandler.INSTANCE.dmg(living, getOwner(), 7F, DamageHandler.INSTANCE.MAGIC_PIERCING);
                } else {
                    if (living.invulnerableTime == 0)
                        DamageHandler.INSTANCE.dmg(living, getOwner(), 2.5F, DamageHandler.INSTANCE.LIFE_LOSING);
                    DamageHandler.INSTANCE.dmg(living, getOwner(), 7.5F, DamageHandler.INSTANCE.MAGIC);
                }
            }
        }
    }

    @Override
    public int getLifeTicks() {
        return 30;
    }

    @Override
    public int getTickPerBlock() {
        return -10;
    }

    @Override
    public int getTickBreakBlockCap() {
        return 0;
    }

    @Override
    public BakedModel getIcon() {
        return MiscellaneousModels.INSTANCE.firstFractalWeaponModels[getVariety()];
    }

    @Override
    public ItemStack getWeaponItem() {
        return new ItemStack(ExtraBotanyItems.firstFractal);
    }

    @Override
    protected void addAdditionalSaveData(CompoundTag cmp) {
        super.addAdditionalSaveData(cmp);
        cmp.putInt(TAG_VARIETY, getVariety());
        cmp.putInt(TAG_DELAY, getDelay());
        cmp.putBoolean(TAG_FAKE, getFake());
    }

    @Override
    protected void readAdditionalSaveData(CompoundTag cmp) {
        super.readAdditionalSaveData(cmp);
        setVariety(cmp.getInt(TAG_VARIETY));
        setDelay(cmp.getInt(TAG_DELAY));
        setFake(cmp.getBoolean(TAG_FAKE));
    }

    public int getVariety() {
        return entityData.get(VARIETY);
    }

    public void setVariety(int var) {
        entityData.set(VARIETY, var);
    }

    public int getDelay() {
        return entityData.get(DELAY);
    }

    public void setDelay(int delay) {
        entityData.set(DELAY, delay);
    }

    public boolean getFake() {
        return entityData.get(FAKE);
    }

    public void setFake(boolean rot) {
        entityData.set(FAKE, rot);
    }
}
