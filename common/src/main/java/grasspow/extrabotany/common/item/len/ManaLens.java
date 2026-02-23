package grasspow.extrabotany.common.item.len;

import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.AABB;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.api.internal.ManaBurst;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.recipe.ManaInfusionRecipe;
import vazkii.botania.common.item.lens.Lens;

import java.util.ArrayList;
import java.util.List;

public class ManaLens extends Lens {
    @Override
    public void apply(ItemStack stack, BurstProperties props) {
        props.maxMana = 1000;
        props.motionModifier *= 0.5F;
        props.manaLossPerTick *= 2F;
    }

    @Override
    public void updateBurst(ManaBurst burst, ItemStack stack) {
        ThrowableProjectile entity = burst.entity();
        Level level = entity.level();
        if (level.isClientSide()) return;
        int mana = burst.getMana();
        BlockState state = level.getBlockState(burst.getBurstSourcePosition().get().pos().offset(0, -1, 0));
        AABB axis = new AABB(entity.getX(), entity.getY(), entity.getZ(), entity.xOld, entity.yOld, entity.zOld).inflate(1);
        List<ItemEntity> entities = level.getEntitiesOfClass(ItemEntity.class, axis);
        if (!burst.isFake()) {
            for (ItemEntity items : entities) {
                if (items.hasPickUpDelay())
                    continue;
                ItemStack itemstack = items.getItem();
                ManaInfusionRecipe recipe = getMatchingRecipe(level, itemstack, state);
                if (recipe != null) {
                    int manaToConsume = recipe.getManaToConsume();
                    if (mana >= manaToConsume) {
                        burst.setMana(mana - manaToConsume);
                        itemstack.shrink(1);
                        ItemStack output = recipe.getRecipeOutput(level.registryAccess(), itemstack).copy();
                        ItemEntity outputItem = new ItemEntity(level, items.getX(), items.getY() + 0.5, items.getZ() + 0.5, output);
                        outputItem.setPickUpDelay(50);
                        level.addFreshEntity(outputItem);
                    }
                }
            }
        }
    }

    public ManaInfusionRecipe getMatchingRecipe(@NotNull Level level, @NotNull ItemStack stack, @NotNull BlockState state) {
        List<ManaInfusionRecipe> matchingNonCatRecipes = new ArrayList<>();
        List<ManaInfusionRecipe> matchingCatRecipes = new ArrayList<>();

//        for (var r : BotaniaRecipeTypes.getRecipes(level, BotaniaRecipeTypes.MANA_INFUSION_TYPE).values()) {
//            if (r instanceof ManaInfusionRecipe) {
//                if (r.matches(stack)){
//                    if (r.getRecipeCatalyst() == null) {
//                        matchingNonCatRecipes.add(r);
//                    } else if (r.getRecipeCatalyst().test(state)) {
//                        matchingCatRecipes.add(r);
//                    }
//                }
//            }
//        }
//
//        // Recipes with matching catalyst take priority above recipes with no catalyst specified
//        return !matchingCatRecipes.isEmpty() ? matchingCatRecipes.get(0) : !matchingNonCatRecipes.isEmpty() ? matchingNonCatRecipes.get(0) : null;
        return null;
    }
}
