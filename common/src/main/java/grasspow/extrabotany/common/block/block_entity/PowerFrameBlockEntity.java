package grasspow.extrabotany.common.block.block_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.mana.ManaPool;
import vazkii.botania.common.block.block_entity.ExposedSimpleInventoryBlockEntity;
import vazkii.botania.common.block.block_entity.mana.BellowsBlockEntity;
import vazkii.botania.xplat.XplatAbstractions;

public class PowerFrameBlockEntity extends ExposedSimpleInventoryBlockEntity {
    private static final BlockPos POOL = new BlockPos(0, 1, 0);
    private static final BlockPos[] BELLOWS = {new BlockPos(1, 1, 0), new BlockPos(0, 1, 1),
            new BlockPos(-1, 1, 0), new BlockPos(0, 1, -1)};
    private static final String TAG_SPEED = "speed";
    private int speed = 0;

    protected PowerFrameBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    public int getSpeed() {
        return speed;
    }

    public PowerFrameBlockEntity(BlockPos pos, BlockState state) {
        super(ExtraBotanyBlockEntities.POWER_FRAME, pos, state);
    }

    private void activeBellows(Level level, BlockPos pos) {
        int count = 0;
        if (level.getBlockEntity(pos.offset(POOL)) instanceof ManaPool) {
            count++;
            for (BlockPos o : BELLOWS) {
                if (level.getBlockEntity(pos.offset(o)) instanceof BellowsBlockEntity bellow) {
                    count++;
                    bellow.interact();
                }
            }
        }
        speed = count * 800;
        setChanged();
    }

    @Override
    protected SimpleContainer createItemHandler() {
        return new SimpleContainer(1) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
        };
    }

    public void interact(Player player, InteractionHand hand, ItemStack stack, BlockPos pos) {
        if (!level.isClientSide) {
            ItemStack stackAt = getItemHandler().getItem(0);
            if (!stackAt.isEmpty() && stack.isEmpty()) {
                player.setItemInHand(hand, stackAt);
                getItemHandler().setItem(0, ItemStack.EMPTY);
                level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1F, 1F);
            } else if (!stack.isEmpty()) {
                ItemStack copy = stack.split(1);
                if (stack.isEmpty()) {
                    player.setItemInHand(hand, stackAt);
                } else if (!stackAt.isEmpty()) {
                    player.getInventory().placeItemBackInInventory(stackAt);
                }
                getItemHandler().setItem(0, copy);
                level.playSound(null, pos.getX(), pos.getY(), pos.getZ(), SoundEvents.ITEM_PICKUP, SoundSource.BLOCKS, 1F, 1F);
            }
        }
    }

    public static void tick(Level level, BlockPos pos, BlockState state, PowerFrameBlockEntity powerFrame) {
        if (!level.isClientSide()) {
            var stack = powerFrame.getItem(0);
            var mana = XplatAbstractions.INSTANCE.findManaItem(stack);
            if (stack.isEmpty() || mana == null) return;
            if (level.getBlockEntity(pos.offset(POOL)) instanceof ManaPool pool && pool.getCurrentMana() > 0 && mana.getMaxMana() != mana.getMana()) {
                powerFrame.activeBellows(level, pos);
                int manaToGet = Math.min(powerFrame.getSpeed(), pool.getCurrentMana());
                int space = Math.max(0, mana.getMaxMana() - mana.getMana());
                int current = Math.min(space, manaToGet);
                pool.receiveMana(-current);
                mana.addMana(current);
            }
        }
    }

    @Override
    public void setChanged() {
        super.setChanged();
        if (level != null && !level.isClientSide) {
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
        }
    }

    @Override
    public void readPacketNBT(CompoundTag tag, HolderLookup.Provider registries) {
        super.readPacketNBT(tag, registries);
        speed = tag.getInt(TAG_SPEED);
    }

    @Override
    public void writePacketNBT(CompoundTag tag, HolderLookup.Provider registries) {
        super.writePacketNBT(tag, registries);
        tag.putInt(TAG_SPEED, getSpeed());
    }
}
