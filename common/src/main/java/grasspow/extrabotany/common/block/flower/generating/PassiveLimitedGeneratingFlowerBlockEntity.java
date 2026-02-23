package grasspow.extrabotany.common.block.flower.generating;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;

public abstract class PassiveLimitedGeneratingFlowerBlockEntity extends PassiveGeneratingFlowerBlockEntity {
    private int decayTime = 75000;
    private int passiveDecayTicks = 0;

    public void setDecayTime(int decayTime) {
        this.decayTime = decayTime;
    }

    public PassiveLimitedGeneratingFlowerBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Override
    public void tickFlower() {
        super.tickFlower();
        if (!getLevel().isClientSide()) {
            if (canGeneratePassively()) {
                int delay = getDelayBetweenPassiveGeneration();
                if (delay > 0 && this.level.getRandom().nextInt(delay) == 0) {
                    addMana(getValueForPassiveGeneration());
                }
            }
            if (++passiveDecayTicks > decayTime) {
                getLevel().destroyBlock(getBlockPos(), false);
                if (Blocks.DEAD_BUSH.defaultBlockState().canSurvive(getLevel(), getBlockPos())) {
                    getLevel().setBlockAndUpdate(getBlockPos(), Blocks.DEAD_BUSH.defaultBlockState());
                }
            }
        }
    }
}
