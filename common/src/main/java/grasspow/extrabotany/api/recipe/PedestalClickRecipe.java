package grasspow.extrabotany.api.recipe;

import grasspow.extrabotany.common.lib.LibRecipeNames;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

import java.util.Objects;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;

public interface PedestalClickRecipe extends Recipe<RecipeInput> {

    ResourceLocation TYPE_ID = exbotRL(LibRecipeNames.PEDESTAL_CLICK);

    @Override
    ItemStack getResultItem(HolderLookup.Provider registries);

    default ItemStack getRecipeOutput(RegistryAccess registries, ItemStack input) {
        return getResultItem(registries).copy();
    }

    @Override
    default RecipeType<?> getType() {
        return Objects.requireNonNull(BuiltInRegistries.RECIPE_TYPE.get(TYPE_ID));
    }

    @Override
    default ItemStack assemble(RecipeInput inv, HolderLookup.Provider registries) {
        return ItemStack.EMPTY;
    }

    @Override
    default boolean matches(RecipeInput inv, Level world) {
        return false;
    }

    @Override
    default boolean canCraftInDimensions(int width, int height) {
        return false;
    }

    @Override
    default boolean isSpecial() {
        return true;
    }
}
