package grasspow.extrabotany.common.block.flower.generating;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import vazkii.botania.api.block_entity.GeneratingFlowerBlockEntity;
import vazkii.botania.api.block_entity.RadiusDescriptor;

import static grasspow.extrabotany.common.block.block_entity.ExtraBotanyBlockEntities.TINKLE_FLOWER;

public class TinkleFlowerBlockEntity extends GeneratingFlowerBlockEntity {

    private static final int RANGE = 8;
    private static final String TAG_TIME = "time";
    private int time = 0;

    public TinkleFlowerBlockEntity(BlockPos pos, BlockState state) {
        super(TINKLE_FLOWER, pos, state);
    }

    @Override
    public void tickFlower() {
        super.tickFlower();

        if (!getLevel().isClientSide() && this.level.getRandom().nextInt(20) % 20L == 0) {
            for (Player player : getLevel().getEntitiesOfClass(Player.class, (new AABB(getEffectivePos())).inflate(RANGE))) {
                double vx = player.getX() - player.xCloakO;
                double vy = player.getY() - player.yCloakO;
                double vz = player.getZ() - player.zCloakO;
                double vel = Math.sqrt(vx * vx + vy * vy + vz * vz);
                if (player.hasEffect(MobEffects.MOVEMENT_SPEED))
                    vel *= 1.2;

                time += Mth.clamp((int) (vel * 10.0), 0, 8);

                final int limit = 10;

                if (time >= limit) {
                    if (getMana() < getMaxMana())
                        addMana(30);
                    player.addEffect(new MobEffectInstance(MobEffects.HUNGER, 0, 1));
                    if (player instanceof ServerPlayer serverPlayer && getLevel() instanceof ServerLevel serverLevel) {
//                        TinkleUseTrigger.INSTANCE.trigger(serverPlayer, serverLevel, getBlockPos());
                    }
                    time %= limit;
                }
            }
        }

    }

    @Override
    public int getMaxMana() {
        return 1000;
    }

    @Override
    public int getColor() {
        return 0xCCFF00;
    }

    @Override
    public RadiusDescriptor getRadius() {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), RANGE);
    }

    @Override
    public void writeToPacketNBT(CompoundTag cmp, HolderLookup.Provider registries) {
        super.writeToPacketNBT(cmp, registries);
        cmp.putInt(TAG_TIME, time);
    }

    @Override
    public void readFromPacketNBT(CompoundTag cmp, HolderLookup.Provider registries) {
        super.readFromPacketNBT(cmp, registries);
        time = cmp.getInt(TAG_TIME);
    }
}
