package grasspow.extrabotany.common.entity.ego;

import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.world.Difficulty;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.target.HurtByTargetGoal;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class EGOMinion extends Monster {
    public Entity summoner;

    private static final List<BlockPos> SPAWN_LOCATIONS = ImmutableList.of(
            new BlockPos(6, 1, 6),
            new BlockPos(6, 1, -6),
            new BlockPos(-6, 1, 6),
            new BlockPos(-6, 1, -6)
    );

    private static final String TAG_TYPE = "type";

    private static final EntityDataAccessor<Integer> TYPE = SynchedEntityData.defineId(EGOMinion.class, EntityDataSerializers.INT);

    private int attackCooldown = 0;
    private int tp = 0;

    public EGOMinion(EntityType<EGOMinion> type, Level level) {
        super(type, level);
    }


    @Override
    public void registerGoals() {
        super.registerGoals();
        this.goalSelector.addGoal(4, new HurtByTargetGoal(this, Player.class));
        this.goalSelector.addGoal(8, new LookAtPlayerGoal(this, Player.class, 64.0F));
    }

    @Override
    public void aiStep() {
        super.aiStep();

        if (!level().isClientSide) {
            clearPotions(this);
            if (level().getDifficulty() == Difficulty.PEACEFUL || summoner == null || summoner.isRemoved()) {
                discard();
            }
        }

        float RANGE = 16F;
        AABB axis = new AABB(position().add(-RANGE, -RANGE, -RANGE), position().add(RANGE + 1, RANGE + 1, RANGE + 1));
        if (getTarget() == null || !(getTarget() instanceof Player)) {
            List<Player> players = level().getEntitiesOfClass(Player.class, axis);
            if (players.size() > 0) {
                setTarget(players.get(0));
            }
        }

        if (getTarget() != null) {
            Vec3 lookVec = getTarget().getLookAngle().multiply(1, 0, 1);

            double playerRot = Math.toRadians(getTarget().getYRot() + 90);
            if (lookVec.x == 0 && lookVec.z == 0) {
                lookVec = new Vec3(Math.cos(playerRot), 0, Math.sin(playerRot));
            }

            lookVec = lookVec.normalize().multiply(3.5F, 0, 3.5F);

            if (this.getHealth() <= this.getMaxHealth() * 0.5F) {
                lookVec = lookVec.multiply(-2F, 0, -2F);
                if (tickCount % 40 == 0)
                    this.heal(2F);
            }

            lookVec = lookVec.yRot((float) (Math.PI / 2F * getMinionType() + Math.floor((double) tickCount / 100) * Math.PI / 4F));

            Vec3 targetPos = getTarget().position().add(lookVec);

            if (!level().isClientSide) {
                if (this.position().distanceTo(targetPos) >= 0.5F) {
                    this.getMoveControl().setWantedPosition(targetPos.x, targetPos.y, targetPos.z, 0.7F);
                    //dont know if it works
                    //this.getMoveHelper().setMoveTo(targetPos.x, targetPos.y, targetPos.z, 0.7F);
                    tp++;
                } else {
                    tp = 0;
                }

                if (tp >= 60) {
                    this.setPos(targetPos.x, targetPos.y, targetPos.z);
                    tp = 0;
                }
            }
        }

        if (this.attackCooldown > 0)
            this.attackCooldown--;

        if (this.attackCooldown == 0) {
            this.setItemInHand(InteractionHand.MAIN_HAND, getWeapon());
            if (tryAttack())
                this.attackCooldown = 90 + level().random.nextInt(40);
        }

    }


    public boolean tryAttack() {
        if (getTarget() == null)
            return false;

        this.swing(InteractionHand.MAIN_HAND);
        if (!level().isClientSide) {
            switch (getMinionType()) {
//                case 0 -> ((TrueShadowKatanaItem) ExtraBotanyItems.trueShadowKatana).attack(this, getTarget());
//                case 1 -> ((TrueTerraBladeItem) ExtraBotanyItems.trueTerraBlade).attack(this, getTarget());
//                case 2 -> ((StarWrathItem) ExtraBotanyItems.starWrath).attack(this, getTarget());
//                case 3 -> ((InfluxWaverItem) ExtraBotanyItems.influxWaver).attack(this, getTarget());
//                default -> ((ExcaliberItem) ExtraBotanyItems.excaliber).attack(this, getTarget());
            }
        }
        return true;
    }

    public void clearPotions(LivingEntity player) {
//        List<MobEffect> potionsToRemove = player.getActiveEffects().stream()
//                .map(MobEffectInstance::getEffect)
//                .filter(effect -> !effect.isBeneficial())
//                .distinct().toList();
//
//        potionsToRemove.forEach(potion -> {
//            player.removeEffect(potion);
//            ((ServerLevel) player.level()).getChunkSource().broadcastAndSend(player, new ClientboundRemoveMobEffectPacket(player.getId(), potion));
//        });
    }

    @Override
    public boolean hurt(DamageSource source, float amount) {
        float RANGE = 8F;
        AABB axis = new AABB(position().add(-RANGE, -RANGE, -RANGE), position().add(RANGE + 1, RANGE + 1, RANGE + 1));
        List<EGOMinion> minions = level().getEntitiesOfClass(EGOMinion.class, axis);
        float resistance = Math.min(0.6F, minions.size() * 0.15F);
        int cap = 20;
        return super.hurt(source, Math.min(cap, amount * (1F - resistance)));
    }

//    @Override
//    protected void defineSynchedData() {
//        super.defineSynchedData();
//        entityData.define(TYPE, 0);
//    }

    @Override
    public void addAdditionalSaveData(CompoundTag cmp) {
        super.addAdditionalSaveData(cmp);
        cmp.putInt(TAG_TYPE, getMinionType());
    }

    @Override
    public void readAdditionalSaveData(CompoundTag cmp) {
        super.readAdditionalSaveData(cmp);
        setMinionType(cmp.getInt(TAG_TYPE));
    }

    public static void spawn(Entity summoner, Level level, BlockPos pos, float health) {
        List<String> names = new ArrayList<>();

        //This is for Contributors for the mod
        //it is diabled caz nobody has done it lol

        //(ContributorListHandler.contributorsMap.keySet());
        //Collections.shuffle(names);
        names.add("ExtraMeteorP");
        names.add("Vazkii");
        names.add("Notch");
        names.add("LexManos");
        names.add("Gold_Chick");
        names.add("grasspow");
        Collections.shuffle(names);
        if (!level.isClientSide) {
            int type = 0;
//            for (BlockPos spawnpos : SPAWN_LOCATIONS) {
//                EGOMinion minion = new EGOMinion(level);
//                minion.summoner = summoner;
//                BlockPos mpos = pos.offset(spawnpos.getX(), spawnpos.getY(), spawnpos.getZ());
//                minion.setPos(mpos.getX(), mpos.getY(), mpos.getZ());
//                minion.setCustomName(Component.literal(names.get(type)));
//                minion.setMinionType(type++);
//                minion.getAttribute(Attributes.MAX_HEALTH).setBaseValue(health);
//                minion.getAttribute(Attributes.ARMOR).setBaseValue(10);
//                minion.finalizeSpawn((ServerLevel) level, level.getCurrentDifficultyAt(minion.blockPosition()), MobSpawnType.EVENT, null, null);
//                level.addFreshEntity(minion);
        }

    }

    public ItemStack getWeapon() {
//        return switch (getMinionType()) {
//            case 0 -> new ItemStack(ExtraBotanyItems.trueShadowKatana);
//            case 1 -> new ItemStack(ExtraBotanyItems.trueTerraBlade);
//            case 2 -> new ItemStack(ExtraBotanyItems.starWrath);
//            case 3 -> new ItemStack(ExtraBotanyItems.influxWaver);
//            default -> new ItemStack(ExtraBotanyItems.excaliber);
//        };
        return null;
    }

    public int getMinionType() {
        return entityData.get(TYPE);
    }

    public void setMinionType(int i) {
        entityData.set(TYPE, i);
    }
}
