package grasspow.extrabotany.common.block.block_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.botania.api.mana.ManaPool;
import vazkii.botania.common.block.block_entity.SimpleInventoryBlockEntity;
import vazkii.botania.common.block.block_entity.mana.BellowsBlockEntity;
import vazkii.botania.xplat.XplatAbstractions;

public class PowerFrameBlockEntity extends SimpleInventoryBlockEntity {
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


    public boolean isEmpty() {
        return getItemHandler().getItem(0).isEmpty();
    }

    public ItemStack getItem() {
        return getItemHandler().getItem(0);
    }

    public boolean setItem(ItemStack stack) {
        if (isEmpty() && !stack.isEmpty()) {
            var mana = XplatAbstractions.INSTANCE.findManaItem(stack);
            if (mana == null) {
                return false;
            }
            getItemHandler().setItem(0, stack.split(1));
            return true;
        }
        return false;
    }

    public ItemStack removeItem() {
        if (!isEmpty()) {
            ItemStack item = getItem();
            setItem(ItemStack.EMPTY);
            return item.copy();
        }
        return ItemStack.EMPTY;
    }

    public static void tick(Level level, BlockPos pos, BlockState state, PowerFrameBlockEntity powerFrame) {
        if (!level.isClientSide()) {
            var stack = powerFrame.getItem();
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
