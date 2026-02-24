package grasspow.extrabotany.common.crafting.recipe;

import grasspow.extrabotany.common.item.ExtraBotanyItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import vazkii.botania.api.brew.BrewItem;
import vazkii.botania.common.item.brew.BaseBrewItem;

import static grasspow.extrabotany.common.item.ExtraBotanyItems.cocktail;
import static grasspow.extrabotany.common.item.ExtraBotanyItems.heroMedal;

public class InfiniteWineUpgradeRecipe extends CustomRecipe {
    public static final RecipeSerializer<InfiniteWineUpgradeRecipe> SERIALIZER = new SimpleCraftingRecipeSerializer<>(InfiniteWineUpgradeRecipe::new);

    public InfiniteWineUpgradeRecipe(CraftingBookCategory craftingBookCategory) {
        super(craftingBookCategory);
    }

    @Override
    public boolean matches(CraftingInput inv, Level level) {
        boolean foundBrew = false;
        boolean foundItem = false;

        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() == cocktail && !foundBrew) {
                    foundBrew = true;
                } else if (stack.getItem() == heroMedal && !foundItem) {
                    foundItem = true;
                } else {
                    return false;
                }
            }
        }
        return foundBrew && foundItem;
    }


    @Override
    public ItemStack assemble(CraftingInput inv, HolderLookup.Provider registries) {
        ItemStack brewstack = ItemStack.EMPTY;
        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty() && stack.getItem() == cocktail) {
                brewstack = stack;
                break;
            }
        }

        BrewItem brew = (BrewItem) brewstack.getItem();
        ItemStack infiniteWine = new ItemStack(ExtraBotanyItems.infiniteWine);
        BaseBrewItem.setBrew(infiniteWine, brew.getBrew(brewstack));
        int left = ((BaseBrewItem) brewstack.getItem()).getSwigsLeft(brewstack);
        ((BaseBrewItem) brewstack.getItem()).setSwigsLeft(infiniteWine, left == 6 ? 8 : ((int) (((float) left / (float) 6)) * 8));
        return infiniteWine;
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
