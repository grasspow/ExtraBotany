package grasspow.extrabotany.common.crafting.recipe;


import grasspow.extrabotany.common.item.ExtraBotanyItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import vazkii.botania.api.brew.BrewItem;
import vazkii.botania.common.item.brew.BaseBrewItem;

import static grasspow.extrabotany.common.item.ExtraBotanyItems.potionLens;

public class PotionLensBindBrewRecipe extends CustomRecipe {
    public static final RecipeSerializer<PotionLensBindBrewRecipe> SERIALIZER = new SimpleCraftingRecipeSerializer<>(PotionLensBindBrewRecipe::new);

    public PotionLensBindBrewRecipe(CraftingBookCategory craftingBookCategory) {
        super(craftingBookCategory);
    }

    @Override
    public boolean  matches(CraftingInput inv, Level level) {
        boolean foundBrew = false;
        boolean foundItem = false;

        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof BaseBrewItem && !foundBrew) {
                    foundBrew = true;
                } else if (stack.getItem() == potionLens && !foundItem) {
                    foundItem = true;
                } else {
                    return false;
                }
            }
        }
        return foundBrew && foundItem;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput inv) {
        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(inv.size(), ItemStack.EMPTY);
        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty() && stack.getItem() instanceof BaseBrewItem) {
                nonnulllist.set(i, stack.copy());
                break;
            }
        }
        return nonnulllist;
    }

    @Override
    public ItemStack assemble(CraftingInput inv, HolderLookup.Provider registries) {
        ItemStack brewstack = ItemStack.EMPTY;
        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty() && stack.getItem() instanceof BaseBrewItem) {
                brewstack = stack;
                break;
            }
        }

        BrewItem brew = (BrewItem) brewstack.getItem();
        ItemStack potionLens = new ItemStack(ExtraBotanyItems.potionLens);
        BaseBrewItem.setBrew(potionLens, brew.getBrew(brewstack));
        return potionLens;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width > 1 || height > 1;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }
}
