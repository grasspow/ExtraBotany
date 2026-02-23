package grasspow.extrabotany.common.crafting.recipe;


import grasspow.extrabotany.common.item.ExtraBotanyItems;
import grasspow.extrabotany.common.item.brew.BaseBrewItemEX;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.api.brew.BrewItem;
import vazkii.botania.common.item.brew.BaseBrewItem;

import static grasspow.extrabotany.common.item.ExtraBotanyItems.cocktail;
import static grasspow.extrabotany.common.item.ExtraBotanyItems.emptyBottle;

public class SplashGrenadeRecipe extends CustomRecipe {
    public static final SimpleCraftingRecipeSerializer<SplashGrenadeRecipe> SERIALIZER = new SimpleCraftingRecipeSerializer<>(SplashGrenadeRecipe::new);

    public SplashGrenadeRecipe(CraftingBookCategory craftingBookCategory) {
        super(craftingBookCategory);
    }

    @Override
    public boolean  matches(CraftingInput inv, Level level) {
        boolean foundBrew = false;
        boolean foundItem = false;

        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() == cocktail && !foundBrew) {
                    foundBrew = true;
                } else if (stack.getItem() == Items.POPPED_CHORUS_FRUIT && !foundItem) {
                    foundItem = true;
                } else {
                    return false;
                }
            }
        }
        return foundBrew && foundItem;
    }

    @NotNull
    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput inv) {
        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(inv.size(), ItemStack.EMPTY);
        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty() && stack.getItem() == cocktail) {
                nonnulllist.set(i, new ItemStack(emptyBottle));
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
            if (!stack.isEmpty() && stack.getItem() == cocktail) {
                brewstack = stack;
                break;
            }
        }

        BrewItem brew = (BrewItem) brewstack.getItem();
        ItemStack splashGrenade = new ItemStack(ExtraBotanyItems.splashGrenade);
        BaseBrewItem.setBrew(splashGrenade, brew.getBrew(brewstack));
        splashGrenade.setCount(((BaseBrewItemEX) brewstack.getItem()).getSwigsLeft(brewstack));
        return splashGrenade;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width > 1 || height > 1;
    }

    @NotNull
    @Override
    public RecipeSerializer<SplashGrenadeRecipe> getSerializer() {
        return SERIALIZER;
    }
}
