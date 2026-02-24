package grasspow.extrabotany.common.crafting.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import vazkii.botania.api.brew.BrewItem;
import vazkii.botania.common.item.brew.BaseBrewItem;

import static grasspow.extrabotany.common.item.ExtraBotanyItems.infiniteWine;

public class InfiniteWineSwitchBrewRecipe extends CustomRecipe {
    public static final RecipeSerializer<InfiniteWineSwitchBrewRecipe> SERIALIZER = new SimpleCraftingRecipeSerializer<>(InfiniteWineSwitchBrewRecipe::new);

    public InfiniteWineSwitchBrewRecipe(CraftingBookCategory craftingBookCategory) {
        super(craftingBookCategory);
    }

    @Override
    public boolean matches(CraftingInput inv, Level level) {
        boolean foundBrew = false;
        boolean foundWine = false;

        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof BaseBrewItem && stack.getItem() != infiniteWine && !foundBrew) {
                    foundBrew = true;
                } else if (stack.getItem() == infiniteWine && !foundWine) {
                    foundWine = true;
                } else {
                    return false;
                }
            }
        }
        return foundBrew && foundWine;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(CraftingInput inv) {
        NonNullList<ItemStack> nonnulllist = NonNullList.withSize(inv.size(), ItemStack.EMPTY);
        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty() && stack.getItem() instanceof BaseBrewItem && stack.getItem() != infiniteWine) {
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
            if (!stack.isEmpty() && stack.getItem() instanceof BaseBrewItem && stack.getItem() != infiniteWine) {
                brewstack = stack;
                break;
            }
        }
        ItemStack winestack = ItemStack.EMPTY;
        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty() && stack.getItem() == infiniteWine) {
                winestack = stack.copy();
                break;
            }
        }

        BrewItem brew = (BrewItem) brewstack.getItem();
        BaseBrewItem.setBrew(winestack, brew.getBrew(brewstack));
        int left = ((BaseBrewItem) brewstack.getItem()).getSwigsLeft(brewstack);
        ((BaseBrewItem) brewstack.getItem()).setSwigsLeft(winestack, left == 6 ? 8 : ((int) (((float) left / (float) 6)) * 8));
        return winestack;
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
