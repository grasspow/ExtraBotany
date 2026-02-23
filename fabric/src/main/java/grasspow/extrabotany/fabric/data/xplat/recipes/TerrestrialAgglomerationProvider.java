package grasspow.extrabotany.fabric.data.xplat.recipes;

import grasspow.extrabotany.common.item.ExtraBotanyItems;
import grasspow.extrabotany.common.lib.LibItemNames;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import vazkii.botania.common.block.mana.ManaPoolBlock;
import vazkii.botania.common.crafting.TerrestrialAgglomerationRecipe;
import vazkii.botania.common.item.BotaniaItems;

import java.util.concurrent.CompletableFuture;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;


public class TerrestrialAgglomerationProvider extends vazkii.botania.fabric.data.xplat.TerrestrialAgglomerationProvider {
    public TerrestrialAgglomerationProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    public String getName() {
        return "ExtraBotany Terra Plate recipes";
    }

    @Override
    public void buildRecipes(RecipeOutput consumer) {
        consumer.accept(idFor(LibItemNames.ORICHALCOS),new TerrestrialAgglomerationRecipe( ManaPoolBlock.MAX_MANA / 2, new ItemStack(ExtraBotanyItems.orichalcos),
                        Ingredient.of(BotaniaItems.gaiaIngot),
                        Ingredient.of(ExtraBotanyItems.heroMedal),
                        Ingredient.of(ExtraBotanyItems.gildedMashedPotato)
                ),null);
        consumer.accept(idFor(LibItemNames.AERIALITE), new TerrestrialAgglomerationRecipe(ManaPoolBlock.MAX_MANA / 2, new ItemStack(ExtraBotanyItems.aerialite),
                        Ingredient.of(BotaniaItems.dragonstone),
                        Ingredient.of(BotaniaItems.enderAirBottle),
                        Ingredient.of(Items.PHANTOM_MEMBRANE)
                ),null);
        consumer.accept(idFor(LibItemNames.THE_UNIVERSE), new TerrestrialAgglomerationRecipe(ManaPoolBlock.MAX_MANA, new ItemStack(ExtraBotanyItems.theUniverse),
                        Ingredient.of(ExtraBotanyItems.theChaos),
                        Ingredient.of(ExtraBotanyItems.theEnd),
                        Ingredient.of(ExtraBotanyItems.theOrigin)
                ),null);
    }

    private static ResourceLocation idFor(String s) {
        return exbotRL("terra_plate/" + s);
    }
}
