package grasspow.extrabotany.common.block.block_entity;

import com.google.common.base.Suppliers;
import grasspow.extrabotany.common.block.ExtraBotanyBlocks;
import grasspow.extrabotany.common.item.ExtraBotanyItems;
import grasspow.extrabotany.common.item.equipment.bauble.NatureOrbItem;
import grasspow.extrabotany.common.item.equipment.tool.hammer.UltimateHammer;
import net.minecraft.core.BlockPos;
import net.minecraft.core.HolderLookup;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.Vec3;
import vazkii.botania.api.mana.ManaPool;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.client.fx.WispParticleData;
import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.block.block_entity.ExposedSimpleInventoryBlockEntity;
import vazkii.botania.common.block.mana.ManaPoolBlock;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.patchouli.api.IMultiblock;
import vazkii.patchouli.api.IStateMatcher;
import vazkii.patchouli.api.PatchouliAPI;

import java.util.function.Supplier;

public class PedestalBlockEntity extends ExposedSimpleInventoryBlockEntity {
    private static final String TAG_SPEED = "speed";
    private static final String TAG_ENERGY = "energy";
    private static final String TAG_PROGRESS = "progress";
    private int speed = 0;
    private int energy = 0;
    private int progress = 0;
    public static final BlockPos[] POOL_LOCATIONS = {
            new BlockPos(3, 0, 3), new BlockPos(-3, 0, 3), new BlockPos(3, 0, -3), new BlockPos(-3, 0, -3)
    };
    public static final BlockPos[] PYLON_LOCATIONS = {
            new BlockPos(2, 0, 2), new BlockPos(-2, 0, 2), new BlockPos(2, 0, -2), new BlockPos(-2, 0, -2),
            new BlockPos(3, 1, 3), new BlockPos(-3, 1, 3), new BlockPos(3, 1, -3), new BlockPos(-3, 1, -3)
    };
    public static final Supplier<IMultiblock> MULTIBLOCK = Suppliers.memoize(() -> PatchouliAPI.get().makeMultiblock(
            new String[][]{
                    {
                            "N___N",
                            "_____",
                            "__0__",
                            "_____",
                            "N___N"
                    }
            },
            '0', ExtraBotanyBlocks.pedestal,
            'N', BotaniaBlocks.naturaPylon
    ));
    private static final Supplier<IStateMatcher> MANAPOOL_MATCHER = Suppliers.memoize(() -> PatchouliAPI.get().predicateMatcher(
            BotaniaBlocks.manaPool,
            state -> state.getBlock() instanceof ManaPoolBlock
    ));
    public static final Supplier<IMultiblock> MULTIBLOCK2 = Suppliers.memoize(() -> PatchouliAPI.get().makeMultiblock(
            new String[][]{
                    {
                            "N_____N",
                            "_______",
                            "_______",
                            "_______",
                            "_______",
                            "_______",
                            "N_____N",
                    },
                    {
                            "M_____M",
                            "_N___N_",
                            "_______",
                            "___0___",
                            "_______",
                            "_N___N_",
                            "M_____M"
                    },
                    {
                            "_______",
                            "_BBBBB_",
                            "_B___B_",
                            "_B___B_",
                            "_B___B_",
                            "_BBBBB_",
                            "_______",
                    }
            },
            '0', ExtraBotanyBlocks.pedestal,
            'M', MANAPOOL_MATCHER.get(),
            'B', BotaniaBlocks.shimmerrock,
            'N', BotaniaBlocks.naturaPylon
    ));

    public PedestalBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ExtraBotanyBlockEntities.PEDESTAL, pPos, pBlockState);
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

    public boolean processContainItem(ItemStack stack, Player player) {
        if (level == null) {
            return false;
        }
        ItemStack origin = getItem(0);
        if (origin.getItem() instanceof UltimateHammer) {
            if (stack.is(ExtraBotanyItems.gildedMashedPotato) && UltimateHammer.getRepair(origin) < 3 && stack.getCount() >= 5) {
                UltimateHammer.setRepair(origin, UltimateHammer.getRepair(origin) + 1);
                stack.shrink(5);
                return true;
            } else if (stack.is(BotaniaItems.elementiumSword) && UltimateHammer.getAttack(origin) < 10) {
                UltimateHammer.setAttack(origin, UltimateHammer.getAttack(origin) + 1);
                stack.shrink(1);
                return true;
            } else if (stack.is(ExtraBotanyItems.terrasteelHammer) && !UltimateHammer.hasRange(origin)) {
                UltimateHammer.setRange(origin, true);
                stack.shrink(1);
                return true;
            }
        }
        SimpleContainer itemHandler = new SimpleContainer(2) {
            @Override
            public int getMaxStackSize() {
                return 1;
            }
        };
        itemHandler.setItem(0, getItemHandler().getItem(0));
        itemHandler.setItem(1, stack.copy());
//        Optional<PedestalClickRecipe> matchingRecipe = getMatchingRecipe(itemHandler, stack, player);
//        matchingRecipe.ifPresent(recipe -> {
//            if (!recipe.containClickTool(stack)) return;
//            if (player != null) {
//                stack.hurtAndBreak(1, player, (user) -> user.broadcastBreakEvent(EquipmentSlot.MAINHAND));
//            } else {
//                if (stack.hurtAndBreak(1, player, s);) {
//                    stack.setCount(0);
//                }
//            }
//            if (progress > 5) {
//                ItemStack result = recipe.assemble(itemHandler, level.registryAccess());
//                player.getInventory().placeItemBackInInventory(result.copy());
//                removeItem();
//                progress = 0;
//            } else {
//                progress++;
//                level.playSound(null, worldPosition.getX(), worldPosition.getY(), worldPosition.getZ(), SoundEvents.ANVIL_HIT, SoundSource.BLOCKS, 1f, 1f);
//            }
//        });
//        return matchingRecipe.isPresent();
        return false;
    }

//    private Optional<PedestalClickRecipe> getMatchingRecipe(SimpleContainer container, ItemStack toolStack, @Nullable Player player) {
//        if (level == null) return Optional.empty();
//
//        if (lastRecipeID != null) {
//            PedestalClickRecipe recipe = level.getRecipeManager()
//                    .byType(ExtraBotanyRecipeTypes.PEDESTAL_CLICK.get())
//                    .get(lastRecipeID);
//            if (recipe.matches(container, level) && recipe.getClickTools().test(toolStack)) {
//                return Optional.of(recipe);
//            }
//        }
//
//        List<PedestalClickRecipe> recipeList = level.getRecipeManager().getRecipesFor(ExtraBotanyRecipeTypes.PEDESTAL_CLICK.get(), container, level);
//        if (recipeList.isEmpty() && player != null) {
//            return Optional.empty();
//        }
//        Optional<PedestalClickRecipe> recipe = recipeList.stream().filter(recipe1 -> recipe1.getClickTools().test(toolStack)).findFirst();
//        if (recipe.isEmpty() && player != null) {
//            return Optional.empty();
//        }
//        lastRecipeID = recipe.get().getId();
//        return recipe;
//        return Optional.empty();
//    }

    public static void commonTick(Level level, BlockPos pos, BlockState state, PedestalBlockEntity self) {
        ItemStack stack = self.getItem(0);
        if (stack.getItem() instanceof NatureOrbItem) {
//            if (!level.isClientSide) {
//                self.freshSpeed();
//                var orb = ClientXplatAbstractions.INSTANCE.findNatureOrbItem(self.getItem());
//                orb.addNature(self.speed);
//            } else {
//                Vec3 loc = self.getBlockPos().getCenter();
//                for (int i = 0, pylonLocationsLength = PYLON_LOCATIONS.length; i < pylonLocationsLength; i++) {
//                    BlockPos arr = PYLON_LOCATIONS[i];
//                    BlockPos pylonPos = self.getBlockPos().offset(arr);
//                    if (!(level.getBlockEntity(pylonPos) instanceof PylonBlockEntity)) {
//                        continue;
//                    }
//                    if (MULTIBLOCK.get().validate(level, self.getBlockPos()) != null && i < 4) {
//                        renderWisp(level, loc, pylonPos);
//                    }
//                    if (MULTIBLOCK2.get().validate(level, self.getBlockPos()) != null && i >= 4) {
//                        renderWisp(level, loc, pylonPos);
//                    }
//                }
//            }
        } else if (stack.getItem() == ExtraBotanyItems.nightmareFuel) {
            if (!level.isClientSide && level.isDay()) {
                self.setEnergy(self.getEnergy() + 1);
                if (self.getEnergy() >= 6000) {
                    self.removeItem(0,1);
                    self.setItem(0,new ItemStack(ExtraBotanyItems.spiritFuel));
                }
                self.setEnergy(0);
            }
        } else {
            if (!level.isClientSide) {
                self.setEnergy(0);
                self.speed = 0;
            }
        }

    }

    private static void renderWisp(Level level, Vec3 loc, BlockPos pylonPos) {
        double worldTime = ClientTickHandler.getEntityTicksInGame();
        worldTime /= 5;

        float rad = 0.75F + (float) Math.random() * 0.05F;
        double xp = pylonPos.getX() + 0.5 + Math.cos(worldTime) * rad;
        double zp = pylonPos.getZ() + 0.5 + Math.sin(worldTime) * rad;

        Vec3 partPos = new Vec3(xp, pylonPos.getY(), zp);
        Vec3 mot = loc.subtract(partPos).multiply(0.04, 0.04, 0.04);

        float r = (float) Math.random() * 0.3F;
        float g = 0.75F + (float) Math.random() * 0.2F;
        float b = (float) Math.random() * 0.3F;

        WispParticleData data = WispParticleData.wisp(0.25F + (float) Math.random() * 0.1F, r, g, b, 1);
        level.addParticle(data, partPos.x, partPos.y, partPos.z, 0, -(-0.075F - (float) Math.random() * 0.015F), 0);
        WispParticleData data1 = WispParticleData.wisp(0.4F, r, g, b);
        level.addParticle(data1, partPos.x, partPos.y, partPos.z, (float) mot.x, (float) mot.y, (float) mot.z);
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    private void freshSpeed() {
        if (MULTIBLOCK2.get().validate(level, getBlockPos()) != null) {
            speed = 9;
            for (BlockPos poolPos : POOL_LOCATIONS) {
                if (level.getBlockEntity(getBlockPos().offset(poolPos)) instanceof ManaPool pool) {
                    if (pool.getCurrentMana() >= 10) {
                        pool.receiveMana(-10);
                        speed += 2;
                    }
                }
            }
        } else if (MULTIBLOCK.get().validate(level, getBlockPos()) != null) {
            speed = 4;
        } else {
            speed = 0;
        }
    }

    @Override
    public void readPacketNBT(CompoundTag tag, HolderLookup.Provider registries) {
        super.readPacketNBT(tag,registries);
        speed = tag.getInt(TAG_SPEED);
        energy = tag.getInt(TAG_ENERGY);
        progress = tag.getInt(TAG_PROGRESS);
    }

    @Override
    public void writePacketNBT(CompoundTag tag, HolderLookup.Provider registries) {
        super.writePacketNBT(tag,registries);
        tag.putInt(TAG_SPEED, speed);
        tag.putInt(TAG_ENERGY, energy);
        tag.putInt(TAG_PROGRESS, progress);
    }
}
