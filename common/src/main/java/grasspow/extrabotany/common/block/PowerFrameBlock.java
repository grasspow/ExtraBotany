package grasspow.extrabotany.common.block;

import grasspow.extrabotany.common.block.block_entity.ExtraBotanyBlockEntities;
import grasspow.extrabotany.common.block.block_entity.PowerFrameBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.ItemInteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.common.block.BotaniaBlock;

public class PowerFrameBlock extends BotaniaBlock implements EntityBlock {
    public PowerFrameBlock(Properties builder) {
        super(builder);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Shapes.POWER_FRAME;
    }

    @Override
    protected ItemInteractionResult useItemOn(ItemStack stack, BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        BlockEntity tile = level.getBlockEntity(pos);
        if (tile instanceof PowerFrameBlockEntity frame) {
            frame.interact(player, hand, player.getItemInHand(hand),pos);
        }
        return ItemInteractionResult.sidedSuccess(level.isClientSide());
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new PowerFrameBlockEntity(pPos, pState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) return null;
        return createTickerHelper(type, ExtraBotanyBlockEntities.POWER_FRAME, PowerFrameBlockEntity::tick);
    }


    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        if (level.getBlockEntity(pos) instanceof PowerFrameBlockEntity powerFrame && !powerFrame.isEmpty()) {
            ItemEntity item = new ItemEntity(powerFrame.getLevel(), pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, powerFrame.getItem(0));
            level.addFreshEntity(item);
        }
        super.destroy(level, pos, state);
    }

}
