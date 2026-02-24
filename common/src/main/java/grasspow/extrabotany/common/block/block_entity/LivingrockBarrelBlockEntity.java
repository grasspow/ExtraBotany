package grasspow.extrabotany.common.block.block_entity;

import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Fluids;
import vazkii.botania.api.block.PetalApothecary;
import vazkii.botania.api.internal.VanillaPacketDispatcher;
import vazkii.botania.common.block.block_entity.BotaniaBlockEntity;
import vazkii.botania.common.block.block_entity.PetalApothecaryBlockEntity;

public class LivingrockBarrelBlockEntity extends BotaniaBlockEntity {
    private static final BlockPos[] OUTPUTS = {new BlockPos(1, 0, 0), new BlockPos(0, 0, 1),
            new BlockPos(-1, 0, 0), new BlockPos(0, 0, -1)};
    public static final int MAX_FLUID_AMOUNT = 16000;
    private static final String TAG_WATER_LEFT = "water_left";
    private int water_left = 0;
    private int ticksSinceLast = 0;

    public LivingrockBarrelBlockEntity(BlockPos pos, BlockState state) {
        super(ExtraBotanyBlockEntities.LIVINGROCK_BARREL, pos, state);
        ticksSinceLast = 0;
    }

    public int getWaterLeft() {
        return water_left;
    }

    public void add(int x) {
        water_left = Math.min(MAX_FLUID_AMOUNT, water_left + x);
    }

    public boolean isFull() {
        return MAX_FLUID_AMOUNT <= water_left;
    }

    @Override
    public void readPacketNBT(CompoundTag tag, HolderLookup.Provider registries) {
        super.readPacketNBT(tag, registries);
        water_left = tag.getInt(TAG_WATER_LEFT);
    }

    @Override
    public void writePacketNBT(CompoundTag tag, HolderLookup.Provider registries) {
        super.writePacketNBT(tag, registries);
        tag.putInt(TAG_WATER_LEFT, water_left);
    }

    public void tick(Level level, LivingrockBarrelBlockEntity barrel) {
        if (level.isClientSide) {
            return;
        }
        ticksSinceLast++;
        final BlockPos pos = barrel.getBlockPos();
        if (ticksSinceLast >= 60) {
            ticksSinceLast = 0;
            if ((level.getBlockState(pos.below()).getFluidState().is(Fluids.WATER) || level.getBlockState(pos.below()).is(Blocks.WATER)) && !isFull()) {
                add(1000);
                this.setChanged();
                VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
            }
        }
        BlockPos o = OUTPUTS[barrel.ticksSinceLast % 4];
        if (level.getBlockEntity(pos.offset(o)) instanceof PetalApothecaryBlockEntity a && a.getFluid() != PetalApothecary.State.WATER && getWaterLeft() >= 1000) {
            add(-1000);
            a.setFluid(PetalApothecary.State.WATER);
            this.setChanged();
            VanillaPacketDispatcher.dispatchTEToNearbyPlayers(this);
        }
    }
}
