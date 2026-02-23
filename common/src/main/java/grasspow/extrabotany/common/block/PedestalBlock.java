package grasspow.extrabotany.common.block;

import grasspow.extrabotany.common.block.block_entity.ExtraBotanyBlockEntities;
import grasspow.extrabotany.common.block.block_entity.PedestalBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.EntityBlock;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.common.block.BotaniaWaterloggedBlock;

public class PedestalBlock extends BotaniaWaterloggedBlock implements EntityBlock {
    public PedestalBlock(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public VoxelShape getShape(BlockState pState, BlockGetter pLevel, BlockPos pPos, CollisionContext pContext) {
        return Shapes.PEDESTAL;
    }

    @Override
    public @Nullable BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new PedestalBlockEntity(pPos, pState);
    }

    // edit from cutting board in Farmer's delight
//    @Override
//    public InteractionResult use(BlockState state, Level level, BlockPos pos, Player player, InteractionHand hand, BlockHitResult hitResult) {
//        BlockEntity tileEntity = level.getBlockEntity(pos);
//        if (tileEntity instanceof PedestalBlockEntity pedestal) {
//            ItemStack heldStack = player.getItemInHand(hand);
//            ItemStack offhandStack = player.getOffhandItem();
//
//            if (pedestal.isEmpty()) {
//                if (!offhandStack.isEmpty()) {
//                    if (hand.equals(InteractionHand.MAIN_HAND) && !offhandStack.is(ExtraBotanyTags.Items.PEDESTAL_DENY) && !(heldStack.getItem() instanceof BlockItem)) {
//                        return InteractionResult.PASS; // Pass to off-hand if that item is placeable
//                    }
//                    if (hand.equals(InteractionHand.OFF_HAND) && offhandStack.is(ExtraBotanyTags.Items.PEDESTAL_DENY)) {
//                        return InteractionResult.PASS; // Items in this tag should not be placed from the off-hand
//                    }
//                }
//                if (heldStack.isEmpty()) {
//                    return InteractionResult.PASS;
//                } else if (pedestal.addItem(player.getAbilities().instabuild ? heldStack.copy() : heldStack)) {
//                    level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1.0F, 0.8F);
//                    return InteractionResult.SUCCESS;
//                }
//
//            } else if (!heldStack.isEmpty()) {
//                if (pedestal.processContainItem(heldStack, player)) {
//                    return InteractionResult.SUCCESS;
//                }
//                return InteractionResult.CONSUME;
//            } else if (hand.equals(InteractionHand.MAIN_HAND)) {
//                if (!player.isCreative()) {
//                    if (!player.getInventory().add(pedestal.removeItem())) {
//                        Containers.dropItemStack(level, pos.getX(), pos.getY(), pos.getZ(), pedestal.removeItem());
//                    }
//                } else {
//                    pedestal.removeItem();
//                }
//                level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 0.25F, 0.5F);
//                return InteractionResult.SUCCESS;
//            }
//        }
//        return InteractionResult.PASS;
//    }

    @Override
    public void destroy(LevelAccessor level, BlockPos pos, BlockState state) {
        if ((level.getBlockEntity(pos) instanceof PedestalBlockEntity pedestalBlockEntity && !pedestalBlockEntity.isEmpty())){
            ItemEntity item = new ItemEntity(pedestalBlockEntity.getLevel(), pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, pedestalBlockEntity.getItem(0));
            level.addFreshEntity(item);
        }
        super.destroy(level, pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level level, BlockState state, BlockEntityType<T> type) {
        return createTickerHelper(type, ExtraBotanyBlockEntities.PEDESTAL, PedestalBlockEntity::commonTick);
    }
}