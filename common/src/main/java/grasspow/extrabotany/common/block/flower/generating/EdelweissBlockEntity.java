package grasspow.extrabotany.common.block.flower.generating;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import vazkii.botania.api.block_entity.GeneratingFlowerBlockEntity;
import vazkii.botania.api.block_entity.RadiusDescriptor;

import static grasspow.extrabotany.common.block.block_entity.ExtraBotanyBlockEntities.EDELWEISS;

public class EdelweissBlockEntity extends GeneratingFlowerBlockEntity {

    private static final String TAG_BURN_TIME = "burnTime";
    private static final int RANGE = 1;
    private int burnTime = 0;

    public EdelweissBlockEntity( BlockPos pos, BlockState state) {
        super(EDELWEISS, pos, state);
    }


    @Override
    public void tickFlower() {
        super.tickFlower();

        if (burnTime > 0) {
            burnTime--;
        }

        if (getBindingPos() != null) {
            if (burnTime == 0) {
                if (getMana() < getMaxMana()) {
                    for (SnowGolem golem : getLevel().getEntitiesOfClass(SnowGolem.class, (new AABB(getEffectivePos())).inflate(RANGE))) {
                        if (golem.isAlive()) {
                            golem.discard();
                            addMana(1600);
                            burnTime += 200;
                            break;
                        }
                    }
                }
            }
        }
    }

    @Override
    public int getMaxMana() {
        return 1400;
    }

    @Override
    public void writeToPacketNBT(CompoundTag cmp, HolderLookup.Provider registries) {
        super.writeToPacketNBT(cmp, registries);
        cmp.putInt(TAG_BURN_TIME, burnTime);
    }

    @Override
    public void readFromPacketNBT(CompoundTag cmp, HolderLookup.Provider registries) {
        super.readFromPacketNBT(cmp, registries);
        burnTime = cmp.getInt(TAG_BURN_TIME);
    }

    @Override
    public RadiusDescriptor getRadius() {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), RANGE);
    }

    @Override
    public int getColor() {
        return 0X4169E1;
    }

}
