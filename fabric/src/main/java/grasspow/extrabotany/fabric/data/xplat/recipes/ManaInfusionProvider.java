package grasspow.extrabotany.fabric.data.xplat.recipes;

import grasspow.extrabotany.common.block.ExtraBotanyBlocks;
import grasspow.extrabotany.common.item.ExtraBotanyItems;
import grasspow.extrabotany.common.lib.LibItemNames;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.recipe.StateIngredient;
import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.crafting.ManaInfusionRecipe;
import vazkii.botania.common.crafting.StateIngredients;

import java.util.concurrent.CompletableFuture;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;

public class ManaInfusionProvider extends vazkii.botania.fabric.data.xplat.ManaInfusionProvider {
	public ManaInfusionProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, lookupProvider);
	}

    private static final StateIngredient CONJURATION = StateIngredients.of(BotaniaBlocks.conjurationCatalyst);
    private static final StateIngredient ALCHEMY = StateIngredients.of(BotaniaBlocks.alchemyCatalyst);
    private static final StateIngredient DIMENSION = StateIngredients.of(ExtraBotanyBlocks.dimensionCatalyst);
    @Override
    public void buildRecipes(RecipeOutput consumer) {
         normal(consumer,id(LibItemNames.NIGHTMARE_FUEL), new ItemStack(ExtraBotanyItems.nightmareFuel), Ingredient.of(Items.COAL), 2000);
         normal(consumer,id(LibItemNames.MANA_DRINK), new ItemStack(ExtraBotanyItems.manaDrink), Ingredient.of(ExtraBotanyItems.emptyBottle), 10000);
         normal(consumer,id(LibItemNames.FRIED_CHICKEN), new ItemStack(ExtraBotanyItems.friedChicken), Ingredient.of(Items.COOKED_CHICKEN), 600);
         dimension(consumer,id("enderpearl"), new ItemStack(Items.ENDER_PEARL), ingr(Items.DIAMOND), 20000);
         dimension(consumer,id("shulker_shell"), new ItemStack(Items.SHULKER_SHELL), ingr(Items.DIAMOND_HORSE_ARMOR), 20000);
         dimension(consumer,id("chorus_fruit"), new ItemStack(Items.CHORUS_FRUIT), ingr(Items.APPLE), 500);
         dimension(consumer,id("end_stone"), new ItemStack(Items.END_STONE), ingr(Items.STONE), 500);
         dimension(consumer,id("nether_rack"), new ItemStack(Items.NETHERRACK), ingr(Items.COBBLESTONE), 500);
         dimension(consumer,id("soul_sand"), new ItemStack(Items.SOUL_SAND), ingr(Items.SAND), 500);
         dimension(consumer,id("quartz_ore"), new ItemStack(Items.NETHER_QUARTZ_ORE), ingr(Items.IRON_ORE), 2000);
         dimension(consumer,id("blaze_rod"), new ItemStack(Items.BLAZE_ROD, 2), ingr(Items.BLAZE_ROD), 20000);
         dimension(consumer,id("totem_of_undying"), new ItemStack(Items.TOTEM_OF_UNDYING), ingr(Items.NETHER_STAR), 50000);
//         dimension(id("elytra"), new ItemStack(Items.ELYTRA), ingr(ModItems.theorigin), 50000));
    }

    private static void normal(RecipeOutput consumer, ResourceLocation id, ItemStack output, Ingredient input, int mana) {
        consumer.accept(id, new ManaInfusionRecipe(output, input, mana, null, null), null);
    }

    private static void alchemy(RecipeOutput consumer, ResourceLocation id, ItemStack output, Ingredient input, int mana, @Nullable String group) {
        consumer.accept(id, new ManaInfusionRecipe(output, input, mana, group, ALCHEMY), null);
    }

    private static void dimension(RecipeOutput consumer, ResourceLocation id, ItemStack output, Ingredient input, int mana) {
        consumer.accept(id, new ManaInfusionRecipe(output, input, mana, "", DIMENSION), null);
    }

    @Override
    public String getName() {
        return "ExtraBotany mana pool recipes";
    }

    @Override
    protected ResourceLocation id(String s) {
        return exbotRL("mana_infusion/" + s);
    }
}
