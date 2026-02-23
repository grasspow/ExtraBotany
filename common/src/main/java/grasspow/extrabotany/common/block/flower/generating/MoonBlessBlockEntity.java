package grasspow.extrabotany.common.block.flower.generating;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;

import static grasspow.extrabotany.common.block.block_entity.ExtraBotanyBlockEntities.MOON_BLESS;

public class MoonBlessBlockEntity extends SunBlessBlockEntity {


    public MoonBlessBlockEntity(BlockPos pos, BlockState state) {
        super(MOON_BLESS, pos, state);
    }

    @Override
    public int getColor() {
        return 0xFFFF00;
    }

    @Override
    protected boolean canGeneratePassively() {
        return this.getLevel().isNight() && this.level.getRandom().nextInt(4) == 0;
    }

}
