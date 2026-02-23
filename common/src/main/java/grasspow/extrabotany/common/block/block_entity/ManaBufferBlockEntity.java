package grasspow.extrabotany.common.block.block_entity;

import grasspow.extrabotany.common.block.ManaBufferBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import org.jetbrains.annotations.UnknownNullability;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.api.mana.KeyLocked;
import vazkii.botania.api.mana.ManaPool;
import vazkii.botania.api.mana.spark.SparkAttachable;
import vazkii.botania.common.block.block_entity.BotaniaBlockEntity;
import vazkii.botania.common.block.block_entity.mana.ThrottledPacket;

public class ManaBufferBlockEntity extends BotaniaBlockEntity implements ManaPool, KeyLocked, SparkAttachable, ThrottledPacket {

    private static final BlockPos[] INPUTS = {new BlockPos(1, 0, 0), new BlockPos(0, 0, 1),
            new BlockPos(-1, 0, 0), new BlockPos(0, 0, -1), new BlockPos(0, -1, 0)};

    private static final BlockPos[] OUTPUT = {new BlockPos(0, 1, 0)};

    private static final String TAG_MANA = "mana";
    private static final String TAG_SPEED = "speed";
    private static final String TAG_MANA_CAP = "manaCap";
    private static final String TAG_INPUT_KEY = "inputKey";
    private static final String TAG_OUTPUT_KEY = "outputKey";
    private static final int MAX_MANA = 64000000;
    private static final int MAX_MANA_QUANTUM = 1024000000;
    private static final int SPEED = 400;
    private static final int SPEED_QUANTUM = 5000;

    private boolean sendPacket = false;
    private int ticks = 0;

    private int manaCap = -1;
    private int mana;
    private int speed = 0;
    private String inputKey = "";
    private final String outputKey = "";

    public ManaBufferBlockEntity(BlockPos pos, BlockState state) {
        super(ExtraBotanyBlockEntities.MANA_BUFFER, pos, state);
    }

    public int getSpeed() {
        return speed;
    }

    private void initManaCapAndSpeed() {
        if (getMaxMana() == -1) {
            manaCap = ((ManaBufferBlock) getBlockState().getBlock()).variant.equals(ManaBufferBlock.Variant.DEFAULT) ? MAX_MANA : MAX_MANA_QUANTUM;
        }
        if (getSpeed() == 0) {
            speed = ((ManaBufferBlock) getBlockState().getBlock()).variant.equals(ManaBufferBlock.Variant.DEFAULT) ? SPEED : SPEED_QUANTUM;
        }
    }

    public static void serverTick(Level level, BlockPos pos, BlockState state, ManaBufferBlockEntity self) {
        self.initManaCapAndSpeed();
        int speed = self.getSpeed();
        if (self.sendPacket && self.ticks % 10 == 0) {
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(self);
            self.sendPacket = false;
        }
        if (!self.isFull()) {
            for (BlockPos o : INPUTS) {
                if (level.getBlockEntity(pos.offset(o)) instanceof ManaPool pool && !(pool instanceof ManaBufferBlockEntity) && pool.getCurrentMana() >= 0) {
                    int manaToGet = Math.min(speed, pool.getCurrentMana());
                    int space = Math.max(0, self.getMaxMana() - self.getCurrentMana());
                    int current = Math.min(space, manaToGet);
                    pool.receiveMana(-current);
                    self.receiveMana(current);
                }
            }
        }

        for (BlockPos o : OUTPUT) {
            if (level.getBlockEntity(pos.offset(o)) instanceof ManaPool pool && !pool.isFull()) {
                int manaToGet = Math.min(speed, self.getCurrentMana());
                int space = Math.max(0, pool.getMaxMana() - pool.getCurrentMana());
                int current = Math.min(space, manaToGet);
                pool.receiveMana(current);
                self.receiveMana(-current);
            }
        }
        self.ticks++;
    }

    @Override
    public void receiveMana(int mana) {
        int old = this.mana;
        this.mana = Math.max(0, Math.min(getCurrentMana() + mana, getMaxMana()));
        if (old != this.mana) {
            setChanged();
            markDispatchable();
        }
    }

    @Override
    public void writePacketNBT(CompoundTag cmp, HolderLookup.Provider registries) {
        cmp.putInt(TAG_MANA, getCurrentMana());
        cmp.putInt(TAG_SPEED, getSpeed());
        cmp.putInt(TAG_MANA_CAP, getMaxMana());
        cmp.putString(TAG_INPUT_KEY, inputKey);
        cmp.putString(TAG_OUTPUT_KEY, outputKey);
    }

    @Override
    public void readPacketNBT(CompoundTag cmp, HolderLookup.Provider registries) {
        mana = cmp.getInt(TAG_MANA);
        speed = cmp.getInt(TAG_SPEED);
        if (cmp.contains(TAG_MANA_CAP)) {
            manaCap = cmp.getInt(TAG_MANA_CAP);
        }
        if (cmp.contains(TAG_INPUT_KEY)) {
            inputKey = cmp.getString(TAG_INPUT_KEY);
        }
        if (cmp.contains(TAG_OUTPUT_KEY)) {
            inputKey = cmp.getString(TAG_OUTPUT_KEY);
        }
    }

    @Override
    public boolean canReceiveManaFromBursts() {
        return true;
    }

    @Override
    public boolean isFull() {
        return getCurrentMana() >= getMaxMana();
    }

    @Override
    public boolean isOutputtingPower() {
        return false;
    }

    @Override
    @UnknownNullability
    public Level getManaReceiverLevel() {
        return getLevel();
    }

    @Override
    public BlockPos getManaReceiverPos() {
        return getBlockPos();
    }

    @Override
    public int getCurrentMana() {
        return getBlockState().getBlock() instanceof ManaBufferBlock ? mana : 0;
    }

    @Override
    public int getMaxMana() {
        return manaCap;
    }

    @Override
    public String getInputKey() {
        return inputKey;
    }

    @Override
    public String getOutputKey() {
        return outputKey;
    }

    @Override
    public boolean canAttachSpark(ItemStack stack) {
        return true;
    }

    @Override
    public boolean areIncomingTransfersDone() {
        return false;
    }

    @Override
    public int getAvailableSpaceForMana() {
        return Math.max(0, getMaxMana() - getCurrentMana());
    }

    @Override
    public void markDispatchable() {
        sendPacket = true;
    }

}
