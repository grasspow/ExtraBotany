package grasspow.extrabotany.common.block.flower.functional;

import grasspow.extrabotany.common.block.block_entity.PedestalBlockEntity;
import grasspow.extrabotany.common.item.ExtraBotanyItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import vazkii.botania.api.block_entity.FunctionalFlowerBlockEntity;
import vazkii.botania.api.block_entity.RadiusDescriptor;

import static grasspow.extrabotany.common.block.block_entity.ExtraBotanyBlockEntities.ANNOYING_FLOWER;

public class AnnoyingFlowerBlockEntity extends FunctionalFlowerBlockEntity {
    private static final int COST = 300;
    private static final int RANGE = 3;
    private static final String TAG_TIME = "times";
    int times = 0;

    public AnnoyingFlowerBlockEntity(BlockPos pos, BlockState state) {
        super(ANNOYING_FLOWER, pos, state);
    }

    @Override
    public void tickFlower() {
        super.tickFlower();

        boolean hasWater = false;
        for (int x = -RANGE; x <= RANGE; x++) {
            for (int z = -RANGE; z <= RANGE; z++) {
                BlockPos posi = getEffectivePos().offset(x, 0, z);
                if (getLevel().getBlockEntity(posi) instanceof PedestalBlockEntity pedestal) {
                    hasWater = pedestal.getItem(0).is(Items.WATER_BUCKET);
                    if (hasWater)
                        break;
                }
            }
        }

        for (ItemEntity item : getLevel().getEntitiesOfClass(ItemEntity.class, (new AABB(getEffectivePos())).inflate(RANGE))) {
            if (item.getItem().getItem() == ExtraBotanyItems.friedChicken && item.getItem().getCount() > 0) {
                item.getItem().shrink(1);
                times += 3;
            }
        }

        int cd = times > 0 ? (900 * 2 / 5) : 900;

        if ( getMana() >= COST && hasWater && !this.getLevel().isClientSide()) {
            RandomSource rand = getLevel().random;
            ItemStack stack;
//            do {
//                LootParams lootParams = new LootParams.Builder((ServerLevel) getLevel()).create(LootContextParamSets.EMPTY);
//                List<ItemStack> stacks = getLevel().getServer().getLootData()
//                        .getLootTable(BuiltInLootTables.FISHING).getRandomItems(lootParams);
//                if (times > 0) {
//                    stacks = getLevel().getServer().getLootData()
//                            .getLootTable(BuiltInLootTables.FISHING_TREASURE).getRandomItems(lootParams);
//                }
//                if (stacks.isEmpty()) {
//                    return;
//                } else {
//                    Collections.shuffle(stacks);
//                    stack = stacks.get(0);
//                }
//            } while (stack.isEmpty());

//            int bound = RANGE * 2 + 1;
//            ItemEntity entity = new ItemEntity(getLevel(), getEffectivePos().getX() - RANGE + rand.nextInt(bound), getEffectivePos().getY() + 2, getEffectivePos().getZ() - RANGE + rand.nextInt(bound), stack);
//
//            if (!getLevel().isClientSide())
//                getLevel().addFreshEntity(entity);
            addMana(-COST);
            sync();
        }
    }

    @Override
    public void writeToPacketNBT(CompoundTag cmp, HolderLookup.Provider registries) {
        super.writeToPacketNBT(cmp,registries);
        cmp.putInt(TAG_TIME, times);
    }

    @Override
    public void readFromPacketNBT(CompoundTag cmp, HolderLookup.Provider registries) {
        super.readFromPacketNBT(cmp,registries);
        times = cmp.getInt(TAG_TIME);
    }

    @Override
    public int getColor() {
        return 0x000000;
    }

    @Override
    public int getMaxMana() {
        return 1000;
    }

    @Override
    public RadiusDescriptor getRadius() {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), RANGE);
    }

}
