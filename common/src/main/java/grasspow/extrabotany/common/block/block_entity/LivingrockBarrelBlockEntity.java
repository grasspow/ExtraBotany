package grasspow.extrabotany.common.block.block_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.botania.common.block.block_entity.BotaniaBlockEntity;

public class LivingrockBarrelBlockEntity extends BotaniaBlockEntity {
    private static final BlockPos[] OUTPUTS = {new BlockPos(1, 0, 0), new BlockPos(0, 0, 1),
            new BlockPos(-1, 0, 0), new BlockPos(0, 0, -1)};
    public static final int MAX_FLUID_AMOUNT = 16000;

    public LivingrockBarrelBlockEntity(BlockPos pos, BlockState state) {
        super(ExtraBotanyBlockEntities.LIVINGROCK_BARREL, pos, state);
    }
}
