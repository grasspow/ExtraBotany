package grasspow.extrabotany.common.crafting;

import grasspow.extrabotany.api.recipe.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import vazkii.botania.mixin.RecipeManagerAccessor;

import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

public class ExtraBotanyRecipeTypes {
    private static final Map<ResourceLocation, RecipeType<?>> TYPES = new LinkedHashMap<>();
    
    public static final RecipeType<grasspow.extrabotany.api.recipe.PedestalClickRecipe> PEDESTAL_CLICK = register(grasspow.extrabotany.api.recipe.PedestalClickRecipe.TYPE_ID);
//    public static final RecipeType<PedestalClickRecipe> PEDESTAL_CLICK_SERIALIZER = RECIPE_SERIALIZERS.register(LibRecipeNames.PEDESTAL_CLICK, PedestalClickRecipe.Serializer::new);

    public static final RecipeType<CocktailUpgradeRecipe> COCKTAIL_UPGRADE = register(CocktailUpgradeRecipe.TYPE_ID);
//    public static final RecipeType<RecipeSerializer<CocktailUpgradeRecipe>> COCKTAIL_UPGRADE_SERIALIZER = RECIPE_SERIALIZERS.register(LibRecipeNames.COCKTAIL_UPGRADE, () -> CocktailUpgradeRecipe.SERIALIZER);

    public static final RecipeType<SplashGrenadeRecipe> SPLASH_GRENADE_UPGRADE = register(SplashGrenadeRecipe.TYPE_ID);
//    public static final RecipeType<RecipeSerializer<SplashGrenadeRecipe>> SPLASH_GRENADE_UPGRADE_SERIALIZER = RECIPE_SERIALIZERS.register(LibRecipeNames.SPLASH_GRENADE_UPGRADE, () -> SplashGrenadeRecipe.SERIALIZER);

    public static final RecipeType<InfiniteWineUpgradeRecipe> INFINITE_WINE_UPGRADE = register(InfiniteWineUpgradeRecipe.TYPE_ID);
//    public static final RecipeType<RecipeSerializer<InfiniteWineUpgradeRecipe>> INFINITE_WINE_UPGRADE_SERIALIZER = RECIPE_SERIALIZERS.register(LibRecipeNames.INFINITE_WINE_UPGRADE, () -> InfiniteWineUpgradeRecipe.SERIALIZER);

    public static final RecipeType<GoldClothWipeRelicRecipe> GOLD_CLOTH_WIPE_RELIC = register(GoldClothWipeRelicRecipe.TYPE_ID);
//    public static final RecipeType<RecipeSerializer<GoldClothWipeRelicRecipe>> GOLD_CLOTH_WIPE_RELIC_SERIALIZER = RECIPE_SERIALIZERS.register(LibRecipeNames.GOLD_CLOTH_WIPE_RELIC, () -> GoldClothWipeRelicRecipe.SERIALIZER);

    public static final RecipeType<PotionLensBindBrewRecipe> POTION_LENS_BIND_BREW = register(PotionLensBindBrewRecipe.TYPE_ID);
//    public static final RecipeType<RecipeSerializer<PotionLensBindBrewRecipe>> MANA_LENS_BIND_FLASK_SERIALIZER = RECIPE_SERIALIZERS.register(LibRecipeNames.MANA_LENS_BIND_FLASK, () -> PotionLensBindBrewRecipe.SERIALIZER);

    private static <T extends Recipe<?>> RecipeType<T> register(ResourceLocation id) {
        RecipeType<T> type = new BotaniaRecipeType<>(id.getPath());
        if (TYPES.put(id, type) != null) {
            throw new IllegalArgumentException("Multiple recipe types with ID " + id);
        }
        return type;
	}

	private record BotaniaRecipeType<T extends Recipe<?>>(String name) implements RecipeType<T> {
		@Override
		public String toString() {
			return name;
		}
	}

	public static void submitRecipeTypes(BiConsumer<RecipeType<?>, ResourceLocation> r) {
		TYPES.forEach((resourceLocation, recipeType) -> r.accept(recipeType, resourceLocation));
	}

	public static void submitRecipeSerializers(BiConsumer<RecipeSerializer<?>, ResourceLocation> r) {
		// serializers for our custom recipe types
		r.accept(grasspow.extrabotany.common.crafting.PedestalClickRecipe.SERIALIZER, grasspow.extrabotany.api.recipe.PedestalClickRecipe.TYPE_ID);

		// serializers for crafting recipe variants
		r.accept(grasspow.extrabotany.common.crafting.recipe.CocktailUpgradeRecipe.SERIALIZER, CocktailUpgradeRecipe.TYPE_ID);
		r.accept(grasspow.extrabotany.common.crafting.recipe.SplashGrenadeRecipe.SERIALIZER, SplashGrenadeRecipe.TYPE_ID);
		r.accept(grasspow.extrabotany.common.crafting.recipe.InfiniteWineUpgradeRecipe.SERIALIZER, InfiniteWineUpgradeRecipe.TYPE_ID);
		r.accept(grasspow.extrabotany.common.crafting.recipe.GoldClothWipeRelicRecipe.SERIALIZER, GoldClothWipeRelicRecipe.TYPE_ID);
		r.accept(grasspow.extrabotany.common.crafting.recipe.PotionLensBindBrewRecipe.SERIALIZER, PotionLensBindBrewRecipe.TYPE_ID);
	}

	public static <C extends RecipeInput, T extends Recipe<C>> Collection<RecipeHolder<T>> getRecipes(Level world, RecipeType<T> type) {
		return ((RecipeManagerAccessor) world.getRecipeManager()).botania_getAll(type);
	}

	@SuppressWarnings("unchecked")
	public static <C extends RecipeInput, T extends Recipe<C>> Optional<RecipeHolder<T>> getRecipe(Level world, ResourceLocation id, RecipeType<T> expectedType) {
		var holder = world.getRecipeManager().byKey(id);
		return holder.isPresent() && holder.get().value().getType() == expectedType
				? holder.map(h -> (RecipeHolder<T>) h)
				: Optional.empty();
	}
}
