package grasspow.extrabotany.common.block.flower.generating;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.botania.api.block_entity.GeneratingFlowerBlockEntity;
import vazkii.botania.api.block_entity.RadiusDescriptor;

import static grasspow.extrabotany.common.block.block_entity.ExtraBotanyBlockEntities.GEMINI_ORCHID;

public class GeminiOrchidBlockEntity extends GeneratingFlowerBlockEntity {

    private static final BlockPos[] OFFSETS = {new BlockPos(0, 0, 1), new BlockPos(0, 0, -1), new BlockPos(1, 0, 0), new BlockPos(-1, 0, 0), new BlockPos(-1, 0, 1), new BlockPos(-1, 0, -1), new BlockPos(1, 0, 1), new BlockPos(1, 0, -1)};
    private static final int RANGE = 1;

    public GeminiOrchidBlockEntity(BlockPos pos, BlockState state) {
        super(GEMINI_ORCHID, pos, state);
    }

    @Override
    public void tickFlower() {
        super.tickFlower();

        int tempMax = 700;
        int tempMin = 700;

        for (int i = 0; i < OFFSETS.length; i++) {
            BlockPos pos = this.getEffectivePos().offset(OFFSETS[i]);
            Block block = this.getLevel().getBlockState(pos).getBlock();

//            if (block != null) {
//                if (block instanceof LiquidBlock fluid) {
//                    tempMax = Math.max(tempMax, fluid.getFluid().getSource().getFluidType().getTemperature());
//                    tempMin = Math.min(tempMin, fluid.getFluid().getSource().getFluidType().getTemperature());
//                } else if (block instanceof IFluidBlock fluid) {
//                    tempMax = Math.max(tempMax, fluid.getFluid().getFluidType().getTemperature());
//                    tempMin = Math.min(tempMin, fluid.getFluid().getFluidType().getTemperature());
//                }
//            }
        }

        if (getMana() < getMaxMana() && this.level.getRandom().nextInt(8) == 0)
            addMana((int) (Math.abs(tempMax - tempMin) / 100F));
    }

    @Override
    public int getMaxMana() {
        return 1000;
    }

    @Override
    public int getColor() {
        return 0;
    }

    @Override
    public RadiusDescriptor getRadius() {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), RANGE);
    }

}
