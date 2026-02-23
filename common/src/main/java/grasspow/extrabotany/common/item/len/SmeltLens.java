package grasspow.extrabotany.common.item.len;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.RecipeHolder;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmithingRecipe;
import net.minecraft.world.item.crafting.SmithingRecipeInput;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import vazkii.botania.api.internal.ManaBurst;
import vazkii.botania.api.mana.ManaReceiver;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.lens.Lens;

import java.util.List;
import java.util.stream.Stream;


public class SmeltLens extends Lens {

    @Override
    public boolean collideBurst(ManaBurst burst, HitResult rtr, boolean isManaBlock, boolean shouldKill, ItemStack stack) {
        ThrowableProjectile entity = burst.entity();
        Level world = entity.level();

        if (world.isClientSide() || rtr.getType() != HitResult.Type.BLOCK) {
            return false;
        }

        BlockPos collidePos = ((BlockHitResult) rtr).getBlockPos();
        BlockState state = world.getBlockState(collidePos);
        Block block = state.getBlock();

        ItemStack composite = ((BrewLensItem) stack.getItem()).getCompositeLens(stack);
        boolean warp = !composite.isEmpty() && composite.getItem() == BotaniaItems.lensWarp;

        int harvestLevel = 3;
        BlockEntity tile = world.getBlockEntity(collidePos);

        float hardness = state.getDestroySpeed(world, collidePos);
        int mana = burst.getMana();

        BlockPos source = burst.getBurstSourcePosition().get().pos();
        if (!source.equals(collidePos)
                && !(tile instanceof ManaReceiver)
                && canHarvest(harvestLevel, state)
                && hardness != -1 && hardness < 50F
                && (burst.isFake() || mana >= 24)) {
            if (!burst.hasAlreadyCollidedAt(collidePos)) {
                if (!burst.isFake()) {
                    List<RecipeHolder<SmithingRecipe>> list = world.getRecipeManager().getRecipesFor(RecipeType.SMITHING, new SmithingRecipeInput(new ItemStack(block),new ItemStack(Items.COAL),ItemStack.EMPTY), world);
                    if (!list.isEmpty()) {
                        world.removeBlock(collidePos, false);
                        if (true) {
                            world.levelEvent(2001, collidePos, Block.getId(state));
                        }
                        boolean offBounds = source.getY() < 0;
                        boolean doWarp = warp && !offBounds;
                        BlockPos dropCoord = doWarp ? source : collidePos;
                        world.addFreshEntity(new ItemEntity(world, dropCoord.getX(), dropCoord.getY(), dropCoord.getZ(), list.get(0).value().getResultItem(world.registryAccess()).copy()));
                        burst.setMana(mana - 40);
                    }
                }
            }
            shouldKill = false;
        }
        return shouldKill;
    }

    private static List<ItemStack> stacks(Item... items) {
        return Stream.of(items).map(ItemStack::new).toList();
    }

    private static final List<List<ItemStack>> HARVEST_TOOLS_BY_LEVEL = List.of(
            stacks(Items.WOODEN_PICKAXE, Items.WOODEN_AXE, Items.WOODEN_HOE, Items.WOODEN_SHOVEL),
            stacks(Items.STONE_PICKAXE, Items.STONE_AXE, Items.STONE_HOE, Items.STONE_SHOVEL),
            stacks(Items.IRON_PICKAXE, Items.IRON_AXE, Items.IRON_HOE, Items.IRON_SHOVEL),
            stacks(Items.DIAMOND_PICKAXE, Items.DIAMOND_AXE, Items.DIAMOND_HOE, Items.DIAMOND_SHOVEL),
            stacks(Items.NETHERITE_PICKAXE, Items.NETHERITE_AXE, Items.NETHERITE_HOE, Items.NETHERITE_SHOVEL)
    );

    public static boolean canHarvest(int harvestLevel, BlockState state) {
        return !getTool(harvestLevel, state).isEmpty();
    }

    public static ItemStack getHarvestToolStack(int harvestLevel, BlockState state) {
        return getTool(harvestLevel, state).copy();
    }

    private static ItemStack getTool(int harvestLevel, BlockState state) {
        if (!state.requiresCorrectToolForDrops()) {
            return HARVEST_TOOLS_BY_LEVEL.get(0).get(0);
        }

        int idx = Math.min(harvestLevel, HARVEST_TOOLS_BY_LEVEL.size() - 1);
        for (var tool : HARVEST_TOOLS_BY_LEVEL.get(idx)) {
            if (tool.isCorrectToolForDrops(state)) {
                return tool;
            }
        }

        return ItemStack.EMPTY;
    }
}
