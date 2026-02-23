package grasspow.extrabotany.common.crafting.recipe;

import grasspow.extrabotany.common.item.ExtraBotanyItems;
import grasspow.extrabotany.common.item.brew.BaseBrewItemEX;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.api.brew.BrewItem;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.brew.BaseBrewItem;

import static grasspow.extrabotany.common.item.ExtraBotanyItems.manaDrink;

public class CocktailUpgradeRecipe extends CustomRecipe {
    public static final SimpleCraftingRecipeSerializer<CocktailUpgradeRecipe> SERIALIZER = new SimpleCraftingRecipeSerializer<>(CocktailUpgradeRecipe::new);

    public CocktailUpgradeRecipe(CraftingBookCategory craftingBookCategory) {
        super(craftingBookCategory);
    }

    @Override
    public boolean matches(CraftingInput inv, Level level) {
        boolean foundBrew = false;
        boolean foundItem = false;

        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() == BotaniaItems.brewFlask && !foundBrew) {
                    foundBrew = true;
                } else if (stack.getItem() == manaDrink && !foundItem) {
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
            if (!stack.isEmpty() && stack.getItem() == BotaniaItems.brewFlask) {
                nonnulllist.set(i, new ItemStack(BotaniaItems.flask));
                break;
            }
        }
        return nonnulllist;
    }

    @NotNull
    @Override
    public ItemStack assemble(CraftingInput inv, HolderLookup.Provider registries) {
        ItemStack brewstack = ItemStack.EMPTY;
        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty() && stack.getItem() == BotaniaItems.brewFlask) {
                brewstack = stack;
                break;
            }
        }

        BrewItem brew = (BrewItem) brewstack.getItem();
        ItemStack cocktail = new ItemStack(ExtraBotanyItems.cocktail);
        BaseBrewItem.setBrew(cocktail, brew.getBrew(brewstack));
        int left = ((BaseBrewItemEX) brewstack.getItem()).getSwigsLeft(brewstack);
        ((BaseBrewItemEX) brewstack.getItem()).setSwigsLeft(cocktail, left == 6 ? 8 : ((int) (((float) left / (float) 6)) * 8));
        return cocktail;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width > 1 || height > 1;
    }

    @NotNull
    @Override
    public RecipeSerializer<CocktailUpgradeRecipe> getSerializer() {
        return SERIALIZER;
    }
}
