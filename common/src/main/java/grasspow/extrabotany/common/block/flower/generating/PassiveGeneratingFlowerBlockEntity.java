package grasspow.extrabotany.common.block.flower.generating;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.botania.api.block_entity.GeneratingFlowerBlockEntity;

public abstract class PassiveGeneratingFlowerBlockEntity extends GeneratingFlowerBlockEntity {
    public PassiveGeneratingFlowerBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void tickFlower() {
        super.tickFlower();
        if (!getLevel().isClientSide() && canGeneratePassively()) {
            int delay = getDelayBetweenPassiveGeneration();
            if (delay > 0 && this.level.getRandom().nextInt(delay) == 0) {
                addMana(getValueForPassiveGeneration());
            }
        }
        emptyManaIntoCollector();
    }

    protected abstract boolean canGeneratePassively();

    protected abstract int getDelayBetweenPassiveGeneration();

    protected abstract int getValueForPassiveGeneration();
}
