package grasspow.extrabotany.fabric.data.xplat.recipes;

import grasspow.extrabotany.common.effect.brew.ExtraBotanyBrews;
import grasspow.extrabotany.common.lib.LibBrewNames;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import vazkii.botania.common.crafting.BotanicalBreweryRecipe;
import vazkii.botania.data.recipes.BotaniaRecipeProvider;

import java.util.concurrent.CompletableFuture;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;


public class BrewProvider extends BotaniaRecipeProvider {
    public BrewProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    public String getName() {
        return "ExtraBotany Brew recipes";
    }

    @Override
    public void buildRecipes(RecipeOutput consumer) {
        consumer.accept(idFor(LibBrewNames.ALL_MIGHTY), new BotanicalBreweryRecipe(ExtraBotanyBrews.all_mighty, Ingredient.of(Items.NETHER_WART), Ingredient.of(Items.GOLDEN_CARROT), Ingredient.of(Items.GHAST_TEAR), Ingredient.of(Items.GLOWSTONE_DUST)), null);
        consumer.accept(idFor(LibBrewNames.SHELL), new BotanicalBreweryRecipe(ExtraBotanyBrews.shell, Ingredient.of(Items.NETHER_WART), Ingredient.of(Items.GOLDEN_APPLE), Ingredient.of(Items.TURTLE_SCUTE), Ingredient.of(Items.OBSIDIAN)), null);
        consumer.accept(idFor(LibBrewNames.REVOLUTION), new BotanicalBreweryRecipe(ExtraBotanyBrews.revolution, Ingredient.of(Items.NETHER_WART), Ingredient.of(Items.SUGAR), Ingredient.of(Items.IRON_PICKAXE)), null);
        consumer.accept(idFor(LibBrewNames.DEADPOOL), new BotanicalBreweryRecipe(ExtraBotanyBrews.deadpool, Ingredient.of(Items.NETHER_WART), Ingredient.of(Items.ROTTEN_FLESH), Ingredient.of(Items.BONE), Ingredient.of(Items.BLAZE_POWDER)), null);
        consumer.accept(idFor(LibBrewNames.FLOATING), new BotanicalBreweryRecipe(ExtraBotanyBrews.floating, Ingredient.of(Items.NETHER_WART), Ingredient.of(Items.SUGAR), Ingredient.of(Items.CHORUS_FRUIT)), null);
    }

    private static ResourceLocation idFor(String s) {
        return exbotRL("brew/" + s);
    }
}
