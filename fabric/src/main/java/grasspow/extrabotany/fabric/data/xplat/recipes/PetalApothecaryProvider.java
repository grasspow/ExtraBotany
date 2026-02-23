package grasspow.extrabotany.fabric.data.xplat.recipes;

import grasspow.extrabotany.common.block.ExtraBotanyBlocks;
import grasspow.extrabotany.common.item.ExtraBotanyItems;
import grasspow.extrabotany.common.lib.ExtraBotanyTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import vazkii.botania.common.item.BotaniaItems;

import java.util.concurrent.CompletableFuture;

public class PetalApothecaryProvider extends vazkii.botania.fabric.data.xplat.PetalApothecaryProvider {
    public PetalApothecaryProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    public void buildRecipes(RecipeOutput consumer) {
        Ingredient white = tagIngr("petals/white");
        Ingredient orange = tagIngr("petals/orange");
        Ingredient magenta = tagIngr("petals/magenta");
        Ingredient lightBlue = tagIngr("petals/light_blue");
        Ingredient yellow = tagIngr("petals/yellow");
        Ingredient lime = tagIngr("petals/lime");
        Ingredient pink = tagIngr("petals/pink");
        Ingredient gray = tagIngr("petals/gray");
        Ingredient lightGray = tagIngr("petals/light_gray");
        Ingredient cyan = tagIngr("petals/cyan");
        Ingredient purple = tagIngr("petals/purple");
        Ingredient blue = tagIngr("petals/blue");
        Ingredient brown = tagIngr("petals/brown");
        Ingredient green = tagIngr("petals/green");
        Ingredient red = tagIngr("petals/red");
        Ingredient black = tagIngr("petals/black");
        Ingredient runeWater = Ingredient.of(ExtraBotanyTags.Items.RUNES_WATER);
        Ingredient runeFire = Ingredient.of(ExtraBotanyTags.Items.RUNES_FIRE);
        Ingredient runeEarth = Ingredient.of(ExtraBotanyTags.Items.RUNES_EARTH);
        Ingredient runeAir = Ingredient.of(ExtraBotanyTags.Items.RUNES_AIR);
        Ingredient runeSpring = Ingredient.of(ExtraBotanyTags.Items.RUNES_SPRING);
        Ingredient runeSummer = Ingredient.of(ExtraBotanyTags.Items.RUNES_SUMMER);
        Ingredient runeAutumn = Ingredient.of(ExtraBotanyTags.Items.RUNES_AUTUMN);
        Ingredient runeWinter = Ingredient.of(ExtraBotanyTags.Items.RUNES_WINTER);
        Ingredient runeMana = Ingredient.of(ExtraBotanyTags.Items.RUNES_MANA);
        Ingredient runeLust = Ingredient.of(ExtraBotanyTags.Items.RUNES_LUST);
        Ingredient runeGluttony = Ingredient.of(ExtraBotanyTags.Items.RUNES_GLUTTONY);
        Ingredient runeGreed = Ingredient.of(ExtraBotanyTags.Items.RUNES_GREED);
        Ingredient runeSloth = Ingredient.of(ExtraBotanyTags.Items.RUNES_SLOTH);
        Ingredient runeWrath = Ingredient.of(ExtraBotanyTags.Items.RUNES_WRATH);
        Ingredient runeEnvy = Ingredient.of(ExtraBotanyTags.Items.RUNES_ENVY);
        Ingredient runePride = Ingredient.of(ExtraBotanyTags.Items.RUNES_PRIDE);

        Ingredient redstoneRoot = Ingredient.of(BotaniaItems.redstoneRoot);
        Ingredient pixieDust = Ingredient.of(BotaniaItems.pixieDust);
        Ingredient gaiaSpirit = Ingredient.of(BotaniaItems.lifeEssence);
        Ingredient spirit = Ingredient.of(ExtraBotanyItems.spirit);

        make(consumer, ExtraBotanyBlocks.annoyingFlower, green, pink, pink, white, white, runeMana, spirit);
        make(consumer, ExtraBotanyBlocks.serenitian, blue, blue, purple, purple, runeMana, runeSloth, runeGreed, gaiaSpirit, Ingredient.of(Items.WITHER_ROSE));
        make(consumer, ExtraBotanyBlocks.bellFlower, lime, lime, yellow, yellow, yellow, yellow);
        make(consumer, ExtraBotanyBlocks.edelweiss, white, white, white, blue, blue, runeWinter, runeMana);
        make(consumer, ExtraBotanyBlocks.geminiOrchid, yellow, yellow, yellow, orange, orange, orange);
        make(consumer, ExtraBotanyBlocks.sunBless, yellow, yellow, yellow, white);
        make(consumer, ExtraBotanyBlocks.moonBless, purple, purple, purple, white);
        make(consumer, ExtraBotanyBlocks.omniViolet, blue, blue, purple, purple, runeSpring, runeMana, runeLust);
        make(consumer, ExtraBotanyBlocks.reikarLily, lightBlue, lightBlue, cyan, cyan, blue, runePride, runeSloth, runeEnvy, gaiaSpirit);
        make(consumer, ExtraBotanyBlocks.tinkleFlower, yellow, red, green, blue, runeEarth, runeWater);
    }
}
