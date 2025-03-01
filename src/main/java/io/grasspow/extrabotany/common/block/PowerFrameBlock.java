package io.grasspow.extrabotany.common.block;

import io.grasspow.extrabotany.common.entity.block.PowerFrameBlockEntity;
import io.grasspow.extrabotany.common.registry.ExtraBotanyEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
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
    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
        BlockEntity blockEntity = level.getBlockEntity(pos);
        if (blockEntity instanceof PowerFrameBlockEntity powerFrame) {
            ItemStack heldStack = player.getItemInHand(hand);
            ItemStack offhandStack = player.getOffhandItem();
            if (powerFrame.isEmpty()) {
                if (!offhandStack.isEmpty()) {
                    return InteractionResult.PASS;
                }
                if (heldStack.isEmpty()) {
                    return InteractionResult.PASS;
                } else if (powerFrame.addItem(player.getAbilities().instabuild ? heldStack.copy() : heldStack)) {
                    level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 1F);
                    return InteractionResult.SUCCESS;
                }
            } else if (!heldStack.isEmpty()) {
                return InteractionResult.CONSUME;
            } else if (hand.equals(InteractionHand.MAIN_HAND)) {
                player.getInventory().placeItemBackInInventory(powerFrame.removeItem());
                level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1F, 1F);
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new PowerFrameBlockEntity(pPos, pState);
    }

    @Override
    public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        if (level.isClientSide()) return null;
        return createTickerHelper(type, ExtraBotanyEntities.Blocks.POWER_FRAME_BLOCK_ENTITY.get(), PowerFrameBlockEntity::tick);
    }

    @Override
    public boolean onDestroyedByPlayer(BlockState state, Level level, BlockPos pos, Player player, boolean willHarvest, FluidState fluid) {
        if (!(level.getBlockEntity(pos) instanceof PowerFrameBlockEntity powerFrame)) {
            return true;
        }
        if (!powerFrame.isEmpty() && !player.getAbilities().instabuild) {
            ItemEntity item = new ItemEntity(level, pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, powerFrame.getItem());
            level.addFreshEntity(item);
        }
        return super.onDestroyedByPlayer(state, level, pos, player, willHarvest, fluid);
    }

}
