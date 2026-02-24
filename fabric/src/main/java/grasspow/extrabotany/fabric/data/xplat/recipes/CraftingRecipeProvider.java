package grasspow.extrabotany.fabric.data.xplat.recipes;

import grasspow.extrabotany.common.block.ExtraBotanyBlocks;
import grasspow.extrabotany.common.crafting.recipe.*;
import grasspow.extrabotany.common.item.ExtraBotanyItems;
import grasspow.extrabotany.common.lib.ExtraBotanyTags;
import grasspow.extrabotany.common.lib.LibItemNames;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CraftingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.lib.BotaniaTags;
import vazkii.botania.data.recipes.BotaniaRecipeProvider;
import vazkii.botania.data.recipes.builder.BotaniaSpecialRecipeBuilder;
import vazkii.botania.mixin.RecipeProviderAccessor;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;
import static vazkii.botania.fabric.data.xplat.CraftingRecipeProvider.conditionsFromItem;
import static vazkii.botania.fabric.data.xplat.CraftingRecipeProvider.conditionsFromTag;

public class CraftingRecipeProvider extends BotaniaRecipeProvider {
    public CraftingRecipeProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    public String getName() {
        return "ExtraBotany crafting recipes";
    }
    protected ResourceLocation prefix(String path) {
        return exbotRL(path);
    }

    @Override
    public void buildRecipes(RecipeOutput recipeOutput) {
        specialRecipe(recipeOutput, CocktailUpgradeRecipe::new, CraftingBookCategory.MISC);
        specialRecipe(recipeOutput, SplashGrenadeRecipe::new, CraftingBookCategory.MISC);
        specialRecipe(recipeOutput, InfiniteWineUpgradeRecipe::new, CraftingBookCategory.MISC);
        specialRecipe(recipeOutput, InfiniteWineSwitchBrewRecipe::new, CraftingBookCategory.MISC);
        specialRecipe(recipeOutput, GoldClothWipeRelicRecipe::new, CraftingBookCategory.MISC);
        specialRecipe(recipeOutput, PotionLensBindBrewRecipe::new, CraftingBookCategory.MISC);

        ingotStorage(ExtraBotanyBlocks.photoniumBlock, ExtraBotanyItems.photonium, recipeOutput);
        ingotStorage(ExtraBotanyBlocks.shadowiumBlock, ExtraBotanyItems.shadowium, recipeOutput);
        ingotStorage(ExtraBotanyBlocks.orichalcosBlock, ExtraBotanyItems.orichalcos, recipeOutput);

        cosmeticBauble(recipeOutput, ExtraBotanyItems.foxEar, BotaniaItems.pinkPetal);
        cosmeticBauble(recipeOutput, ExtraBotanyItems.foxMask, BotaniaItems.whitePetal);
        cosmeticBauble(recipeOutput, ExtraBotanyItems.pylon, BotaniaItems.greenPetal);
        cosmeticBauble(recipeOutput, ExtraBotanyItems.blackGrasses, BotaniaItems.blackPetal);
        cosmeticBauble(recipeOutput, ExtraBotanyItems.redScarf, BotaniaItems.redPetal);
        cosmeticBauble(recipeOutput, ExtraBotanyItems.mask, BotaniaItems.grayPetal);
        cosmeticBauble(recipeOutput, ExtraBotanyItems.superCrown, BotaniaItems.yellowPetal);

        buildCommonCraftingRecipes(recipeOutput);
//        buildingFloatingFlowerRecipes(recipeOutput);
    }


    private void buildCommonCraftingRecipes(RecipeOutput consumer) {
        buildArmorRecipes(consumer);
        buildWeaponRecipes(consumer);
        buildBaubleRecipes(consumer);
        buildToolRecipes(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ExtraBotanyBlocks.pedestal)
                .define('G', Items.GOLD_NUGGET)
                .define('L', BotaniaBlocks.livingrock)
                .pattern("LGL")
                .pattern(" L ")
                .pattern("LLL")
                .unlockedBy("has_item", conditionsFromItem(BotaniaBlocks.livingrock))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ExtraBotanyItems.emptyBottle)
                .define('G', BotaniaBlocks.manaGlass)
                .pattern("G G")
                .pattern("G G")
                .pattern(" G ")
                .unlockedBy("has_item", conditionsFromItem(BotaniaBlocks.manaGlass))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ExtraBotanyBlocks.livingrockBarrel)
                .define('G', BotaniaBlocks.livingrock)
                .pattern("G G")
                .pattern("G G")
                .pattern("GGG")
                .unlockedBy("has_item", conditionsFromItem(BotaniaBlocks.livingrock))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ExtraBotanyBlocks.manaBuffer)
                .define('P', BotaniaBlocks.fabulousPool)
                .define('L', BotaniaItems.lensNormal)
                .define('I', BotaniaItems.gaiaIngot)
                .pattern("PLP")
                .pattern("PIP")
                .pattern("PLP")
                .unlockedBy("has_item", conditionsFromItem(BotaniaItems.gaiaIngot))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ExtraBotanyBlocks.quantumManaBuffer)
                .define('B', ExtraBotanyBlocks.manaBuffer)
                .define('O', ExtraBotanyItems.orichalcos)
                .pattern("BBB")
                .pattern("BOB")
                .pattern("BBB")
                .unlockedBy("has_item", conditionsFromItem(ExtraBotanyItems.orichalcos))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ExtraBotanyBlocks.dimensionCatalyst)
                .define('L', BotaniaBlocks.livingrock)
                .define('E', Items.ENDER_EYE)
                .define('N', ExtraBotanyItems.nightmareFuel)
                .define('A', BotaniaBlocks.alchemyCatalyst)
                .pattern("LEL")
                .pattern("NAN")
                .pattern("LNL")
                .unlockedBy("has_item", conditionsFromItem(BotaniaBlocks.alchemyCatalyst))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ExtraBotanyItems.goldCloth, 4)
                .define('L', BotaniaItems.lifeEssence)
                .define('C', BotaniaItems.manaweaveCloth)
                .define('G', Items.GOLD_INGOT)
                .pattern("LCL")
                .pattern("CGC")
                .pattern("LCL")
                .unlockedBy("has_item", conditionsFromItem(BotaniaItems.manaDiamond))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.DECORATIONS, ExtraBotanyBlocks.powerFrame)
                .define('M', BotaniaItems.manaSteel)
                .define('P', BotaniaItems.pixieDust)
                .define('L', BotaniaBlocks.livingrock)
                .pattern("MMM")
                .pattern("PLP")
                .pattern("MMM")
                .unlockedBy("has_item", conditionsFromItem(BotaniaItems.pixieDust))
                .save(consumer);

        //lens
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ExtraBotanyItems.manaLens)
                .requires(BotaniaItems.lensNormal)
                .requires(ExtraBotanyTags.Items.RUNES_MANA)
                .requires(BotaniaItems.manaPowder)
                .unlockedBy("has_item", conditionsFromItem(BotaniaItems.lensNormal))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ExtraBotanyItems.potionLens)
                .requires(BotaniaItems.lensNormal)
                .requires(ExtraBotanyTags.Items.RUNES_SPRING)
                .requires(BotaniaItems.manaPowder)
                .requires(BotaniaItems.dragonstone)
                .requires(BotaniaItems.enderAirBottle)
                .unlockedBy("has_item", conditionsFromItem(BotaniaItems.lensNormal))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ExtraBotanyItems.pushLens)
                .requires(BotaniaItems.lensNormal)
                .requires(ExtraBotanyTags.Items.RUNES_EARTH)
                .requires(BotaniaItems.manaPowder)
                .unlockedBy("has_item", conditionsFromItem(BotaniaItems.lensNormal))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ExtraBotanyItems.smeltLens)
                .requires(BotaniaItems.lensNormal)
                .requires(ExtraBotanyTags.Items.RUNES_FIRE)
                .requires(BotaniaItems.manaPowder)
                .unlockedBy("has_item", conditionsFromItem(BotaniaItems.lensNormal))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ExtraBotanyItems.traceLens)
                .requires(BotaniaItems.lensNormal)
                .requires(ExtraBotanyTags.Items.RUNES_GREED)
                .requires(BotaniaItems.manaPowder)
                .unlockedBy("has_item", conditionsFromItem(BotaniaItems.lensNormal))
                .save(consumer);

        //universal
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ExtraBotanyItems.universalPetal, 8)
                .requires(Ingredient.of(BotaniaTags.Items.PETALS), 8)
                .requires(BotaniaItems.lifeEssence)
                .unlockedBy("has_item", conditionsFromItem(BotaniaItems.lifeEssence))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ExtraBotanyItems.elementRune, 8)
                .requires(BotaniaItems.lifeEssence)
                .requires(ExtraBotanyTags.Items.RUNES_AIR)
                .requires(ExtraBotanyTags.Items.RUNES_EARTH)
                .requires(ExtraBotanyTags.Items.RUNES_WATER)
                .requires(ExtraBotanyTags.Items.RUNES_FIRE)
                .requires(ExtraBotanyTags.Items.RUNES_SPRING)
                .requires(ExtraBotanyTags.Items.RUNES_SUMMER)
                .requires(ExtraBotanyTags.Items.RUNES_AUTUMN)
                .requires(ExtraBotanyTags.Items.RUNES_WINTER)
                .unlockedBy("has_item", conditionsFromItem(BotaniaItems.lifeEssence))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ExtraBotanyItems.sinRune, 8)
                .requires(BotaniaItems.lifeEssence)
                .requires(ExtraBotanyTags.Items.RUNES_MANA)
                .requires(ExtraBotanyTags.Items.RUNES_PRIDE)
                .requires(ExtraBotanyTags.Items.RUNES_GLUTTONY)
                .requires(ExtraBotanyTags.Items.RUNES_WRATH)
                .requires(ExtraBotanyTags.Items.RUNES_GREED)
                .requires(ExtraBotanyTags.Items.RUNES_ENVY)
                .requires(ExtraBotanyTags.Items.RUNES_LUST)
                .requires(ExtraBotanyTags.Items.RUNES_SLOTH)
                .unlockedBy("has_item", conditionsFromItem(BotaniaItems.lifeEssence))
                .save(consumer);


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ExtraBotanyItems.theChaos)
                .define('L', ExtraBotanyItems.photonium)
                .define('D', ExtraBotanyItems.shadowium)
                .define('S', ExtraBotanyItems.spirit)
                .pattern(" D ")
                .pattern("DSL")
                .pattern(" L ")
                .unlockedBy("has_item", conditionsFromItems(ExtraBotanyItems.photonium, ExtraBotanyItems.shadowium, ExtraBotanyItems.spirit))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ExtraBotanyItems.theOrigin)
                .define('L', BotaniaItems.terrasteel)
                .define('D', ExtraBotanyItems.aerialite)
                .define('S', ExtraBotanyItems.spirit)
                .pattern(" D ")
                .pattern("DSL")
                .pattern(" L ")
                .unlockedBy("has_item", conditionsFromItems(BotaniaItems.terrasteel, ExtraBotanyItems.aerialite, ExtraBotanyItems.spirit))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ExtraBotanyItems.theEnd)
                .define('L', ExtraBotanyItems.orichalcos)
                .define('D', BotaniaItems.gaiaIngot)
                .define('S', ExtraBotanyItems.spirit)
                .pattern(" D ")
                .pattern("DSL")
                .pattern(" L ")
                .unlockedBy("has_item", conditionsFromItems(ExtraBotanyItems.orichalcos, BotaniaItems.gaiaIngot, ExtraBotanyItems.spirit))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ExtraBotanyItems.challengeTicket)
                .requires(ExtraBotanyItems.theChaos)
                .requires(BotaniaItems.gaiaIngot)
                .unlockedBy("has_item", conditionsFromItems(ExtraBotanyItems.theChaos, BotaniaItems.gaiaIngot))
                .save(consumer);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ExtraBotanyItems.rewardBag943, 3)
                .requires(BotaniaItems.dice)
                .unlockedBy("has_item", conditionsFromItem(BotaniaItems.dice))
                .save(consumer);
    }

    private void buildToolRecipes(RecipeOutput consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ExtraBotanyItems.manasteelHammer)
                .define('M', BotaniaItems.manaSteel)
                .define('W', BotaniaItems.livingwoodTwig)
                .pattern("MMM")
                .pattern("MMM")
                .pattern(" W ")
                .unlockedBy("has_item", conditionsFromItem(BotaniaItems.manaSteel))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ExtraBotanyItems.elementiumHammer)
                .define('M', BotaniaItems.elementium)
                .define('W', BotaniaItems.livingwoodTwig)
                .pattern("MMM")
                .pattern("MMM")
                .pattern(" W ")
                .unlockedBy("has_item", conditionsFromItem(BotaniaItems.elementium))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ExtraBotanyItems.terrasteelHammer)
                .define('M', BotaniaItems.elementium)
                .define('W', BotaniaItems.livingwoodTwig)
                .pattern("MMM")
                .pattern("MMM")
                .pattern(" W ")
                .unlockedBy("has_item", conditionsFromItem(BotaniaItems.terrasteel))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ExtraBotanyItems.manaReader)
                .define('D', BotaniaItems.manaDiamond)
                .define('T', BotaniaItems.manaPowder)
                .define('S', BotaniaItems.livingwoodTwig)
                .pattern(" TD")
                .pattern(" ST")
                .pattern("S  ")
                .unlockedBy("has_item", conditionsFromItem(BotaniaItems.manaDiamond))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ExtraBotanyItems.walkingCane)
                .define('G', Items.GOLD_INGOT)
                .define('R', BotaniaBlocks.livingrock)
                .define('W', BotaniaItems.livingwoodTwig)
                .pattern(" RG")
                .pattern(" WR")
                .pattern("W  ")
                .unlockedBy("has_item", conditionsFromItem(Items.GOLD_INGOT))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ExtraBotanyItems.rodOfDiscord)
                .define('C', ExtraBotanyItems.theChaos)
                .define('D', BotaniaItems.pixieDust)
                .define('W', BotaniaItems.livingwoodTwig)
                .pattern(" DC")
                .pattern(" WD")
                .pattern("W  ")
                .unlockedBy("has_item", conditionsFromItem(ExtraBotanyItems.theChaos))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ExtraBotanyItems.silverBullet)
                .define('P', ExtraBotanyTags.Items.INGOTS_PHOTONIUM)
                .define('S', BotaniaItems.manaSteel)
                .define('C', ExtraBotanyItems.theChaos)
                .define('G', BotaniaItems.manaGun)
                .pattern("PPS")
                .pattern(" GC")
                .pattern("  P")
                .unlockedBy("has_item", conditionsFromItem(ExtraBotanyItems.theChaos))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, ExtraBotanyItems.camera)
                .define('B', BotaniaBlocks.darkQuartz)
                .define('H', ExtraBotanyItems.heroMedal)
                .define('T', BotaniaItems.terrasteel)
                .pattern("BBB")
                .pattern("BHB")
                .pattern("TTT")
                .unlockedBy("has_item", conditionsFromItems(ExtraBotanyItems.heroMedal, BotaniaBlocks.darkQuartz, BotaniaItems.terrasteel))
                .save(consumer);
    }

    private void buildBaubleRecipes(RecipeOutput consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ExtraBotanyItems.peaceAmulet)
                .define('L', BotaniaTags.Items.LIVINGWOOD_LOGS)
                .define('R', BotaniaBlocks.livingrock)
                .pattern(" L ")
                .pattern("LRL")
                .pattern(" L ")
                .unlockedBy("has_item", conditionsFromItems(BotaniaBlocks.livingrock, BotaniaBlocks.livingwoodLog))
                .save(consumer);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ExtraBotanyItems.manaDriveRing)
                .requires(BotaniaItems.manaRing)
                .requires(ExtraBotanyTags.Items.RUNES_MANA)
                .unlockedBy("has_item", conditionsFromItems(BotaniaItems.manaRing, BotaniaItems.runeMana))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ExtraBotanyItems.natureOrb)
                .define('T', BotaniaItems.terrasteel)
                .define('D', BotaniaItems.dragonstone)
                .define('P', BotaniaItems.manaPearl)
                .pattern("TDT")
                .pattern("DPD")
                .pattern("TDT")
                .unlockedBy("has_item", conditionsFromItem(BotaniaItems.terrasteel))
                .save(consumer);
        // todo move to platform
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ExtraBotanyItems.jingweiFeather)
                .requires(Items.FEATHER)
                .requires(Items.LAVA_BUCKET)
                .requires(ExtraBotanyItems.heroMedal)
                .unlockedBy("has_item", conditionsFromItem(ExtraBotanyItems.heroMedal))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ExtraBotanyItems.sagesManaRing)
                .define('T', BotaniaItems.terrasteel)
                .define('M', BotaniaItems.manaTablet)
                .define('D', BotaniaItems.manaRingGreater)
                .define('H', ExtraBotanyItems.heroMedal)
                .pattern("THT")
                .pattern("MDM")
                .pattern("TMT")
                .unlockedBy("has_item", conditionsFromItem(ExtraBotanyItems.heroMedal))
                .save(consumer);
    }

    private void buildWeaponRecipes(RecipeOutput consumer) {
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ExtraBotanyItems.shadowKatana)
                .define('W', BotaniaItems.livingwoodTwig)
                .define('S', ExtraBotanyItems.shadowium)
                .pattern("S")
                .pattern("S")
                .pattern("W")
                .unlockedBy("has_item", conditionsFromTag(ExtraBotanyTags.Items.INGOTS_SHADOWIUM))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ExtraBotanyItems.influxWaver)
                .define('T', BotaniaItems.thunderSword)
                .define('L', BotaniaItems.lifeEssence)
                .define('O', ExtraBotanyItems.theOrigin)
                .define('A', ExtraBotanyItems.aerialite)
                .pattern("  A")
                .pattern("LO ")
                .pattern("TL ")
                .unlockedBy("has_item", conditionsFromItems(ExtraBotanyItems.theOrigin, ExtraBotanyItems.aerialite, BotaniaItems.thunderSword, BotaniaItems.lifeEssence))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ExtraBotanyItems.starWrath)
                .define('T', BotaniaItems.starSword)
                .define('L', BotaniaItems.lifeEssence)
                .define('O', ExtraBotanyItems.theOrigin)
                .define('A', ExtraBotanyItems.aerialite)
                .pattern("  A")
                .pattern("LO ")
                .pattern("TL ")
                .unlockedBy("has_item", conditionsFromItems(ExtraBotanyItems.theOrigin, ExtraBotanyItems.aerialite, BotaniaItems.starSword, BotaniaItems.lifeEssence))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ExtraBotanyItems.trueShadowKatana)
                .define('T', ExtraBotanyItems.shadowKatana)
                .define('L', BotaniaItems.lifeEssence)
                .define('O', ExtraBotanyItems.theOrigin)
                .define('A', BotaniaItems.terrasteel)
                .pattern("  A")
                .pattern("LO ")
                .pattern("TL ")
                .unlockedBy("has_item", conditionsFromItems(ExtraBotanyItems.theOrigin, ExtraBotanyItems.shadowKatana, BotaniaItems.terrasteel, BotaniaItems.lifeEssence))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ExtraBotanyItems.trueTerraBlade)
                .define('T', BotaniaItems.terraSword)
                .define('L', BotaniaItems.lifeEssence)
                .define('O', ExtraBotanyItems.theOrigin)
                .define('A', BotaniaItems.terrasteel)
                .pattern("  A")
                .pattern("LO ")
                .pattern("TL ")
                .unlockedBy("has_item", conditionsFromItems(ExtraBotanyItems.theOrigin, BotaniaItems.terraSword, BotaniaItems.terrasteel, BotaniaItems.lifeEssence))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ExtraBotanyItems.excaliber)
                .define('H', ExtraBotanyItems.heroMedal)
                .define('T', BotaniaItems.terraSword)
                .define('D', BotaniaItems.dreamwoodTwig)
                .pattern("H")
                .pattern("T")
                .pattern("D")
                .unlockedBy("has_item", conditionsFromItems(ExtraBotanyItems.heroMedal, BotaniaItems.terraSword, BotaniaItems.dreamwoodTwig))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ExtraBotanyItems.fallnaught)
                .define('O', ExtraBotanyTags.Items.INGOTS_ORICHALCOS)
                .define('T', BotaniaItems.terrasteel)
                .define('S', BotaniaItems.manaString)
                .pattern(" TS")
                .pattern("TOS")
                .pattern(" TS")
                .unlockedBy("has_item", conditionsFromItems(ExtraBotanyItems.orichalcos, BotaniaItems.terrasteel, BotaniaItems.manaString))
                .save(consumer);
    }

    private void buildArmorRecipes(RecipeOutput consumer) {
        //miku
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ExtraBotanyItems.mikuHelm)
                .define('C', BotaniaItems.manaweaveCloth)
                .define('A', BotaniaItems.manasteelHelm)
                .pattern("CCC")
                .pattern("CAC")
                .pattern("CCC")
                .unlockedBy("has_item", conditionsFromItem(BotaniaItems.manaweaveCloth))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ExtraBotanyItems.mikuChest)
                .define('C', BotaniaItems.manaweaveCloth)
                .define('A', BotaniaItems.manasteelChest)
                .pattern("CCC")
                .pattern("CAC")
                .pattern("CCC")
                .unlockedBy("has_item", conditionsFromItem(BotaniaItems.manasteelChest))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ExtraBotanyItems.mikuLegs)
                .define('C', BotaniaItems.manaweaveCloth)
                .define('A', BotaniaItems.manasteelLegs)
                .pattern("CCC")
                .pattern("CAC")
                .pattern("CCC")
                .unlockedBy("has_item", conditionsFromItem(BotaniaItems.manasteelLegs))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ExtraBotanyItems.mikuBoots)
                .define('C', BotaniaItems.manaweaveCloth)
                .define('A', BotaniaItems.manasteelBoots)
                .pattern("CCC")
                .pattern("CAC")
                .pattern("CCC")
                .unlockedBy("has_item", conditionsFromItem(BotaniaItems.manasteelBoots))
                .save(consumer);
        registerSimpleArmorSet(consumer, Ingredient.of(ExtraBotanyTags.Items.INGOTS_PHOTONIUM), LibItemNames.GOBLINS_LAYER, conditionsFromTag(ExtraBotanyTags.Items.INGOTS_PHOTONIUM));
        registerSimpleArmorSet(consumer, Ingredient.of(ExtraBotanyTags.Items.INGOTS_SHADOWIUM), LibItemNames.SHADOW_WARRIOR, conditionsFromTag(ExtraBotanyTags.Items.INGOTS_SHADOWIUM));
        //shooting_guardian
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ExtraBotanyItems.shootingGuardianHelm)
                .define('T', BotaniaItems.dreamwoodTwig)
                .define('R', ExtraBotanyTags.Items.RUNES_SPRING)
                .define('A', ExtraBotanyTags.Items.INGOTS_AERIALITE)
                .define('S', BotaniaItems.manasteelHelm)
                .pattern("TRT")
                .pattern("ASA")
                .pattern(" A ")
                .unlockedBy("has_item", conditionsFromTag(ExtraBotanyTags.Items.INGOTS_AERIALITE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ExtraBotanyItems.shootingGuardianChest)
                .define('T', BotaniaItems.dreamwoodTwig)
                .define('R', ExtraBotanyTags.Items.RUNES_SPRING)
                .define('A', ExtraBotanyTags.Items.INGOTS_AERIALITE)
                .define('S', BotaniaItems.manasteelChest)
                .pattern("TRT")
                .pattern("ASA")
                .pattern(" A ")
                .unlockedBy("has_item", conditionsFromTag(ExtraBotanyTags.Items.INGOTS_AERIALITE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ExtraBotanyItems.shootingGuardianLegs)
                .define('T', BotaniaItems.dreamwoodTwig)
                .define('R', ExtraBotanyTags.Items.RUNES_SPRING)
                .define('A', ExtraBotanyTags.Items.INGOTS_AERIALITE)
                .define('S', BotaniaItems.manasteelLegs)
                .pattern("TRT")
                .pattern("ASA")
                .pattern(" A ")
                .unlockedBy("has_item", conditionsFromTag(ExtraBotanyTags.Items.INGOTS_AERIALITE))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ExtraBotanyItems.shootingGuardianBoots)
                .define('T', BotaniaItems.dreamwoodTwig)
                .define('R', ExtraBotanyTags.Items.RUNES_SPRING)
                .define('A', ExtraBotanyTags.Items.INGOTS_AERIALITE)
                .define('S', BotaniaItems.manasteelBoots)
                .pattern("TRT")
                .pattern("ASA")
                .pattern(" A ")
                .unlockedBy("has_item", conditionsFromTag(ExtraBotanyTags.Items.INGOTS_AERIALITE))
                .save(consumer);
        //maid
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ExtraBotanyItems.maidHelm)
                .define('G', BotaniaItems.gaiaIngot)
                .define('C', ExtraBotanyItems.goldCloth)
                .define('T', BotaniaItems.terrasteelHelm)
                .pattern("GGG")
                .pattern("CTC")
                .unlockedBy("has_item", conditionsFromItems(BotaniaItems.terrasteelHelm, BotaniaItems.gaiaIngot))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ExtraBotanyItems.maidChest)
                .define('G', BotaniaItems.gaiaIngot)
                .define('C', ExtraBotanyItems.goldCloth)
                .define('T', BotaniaItems.terrasteelChest)
                .pattern("C C")
                .pattern("CTC")
                .pattern("GGG")
                .unlockedBy("has_item", conditionsFromItems(BotaniaItems.terrasteelChest, BotaniaItems.gaiaIngot))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ExtraBotanyItems.maidLegs)
                .define('G', BotaniaItems.gaiaIngot)
                .define('C', ExtraBotanyItems.goldCloth)
                .define('T', BotaniaItems.terrasteelLegs)
                .pattern("GGG")
                .pattern("CTC")
                .pattern("C C")
                .unlockedBy("has_item", conditionsFromItems(BotaniaItems.terrasteelLegs, BotaniaItems.gaiaIngot))
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, ExtraBotanyItems.maidBoots)
                .define('G', BotaniaItems.gaiaIngot)
                .define('C', ExtraBotanyItems.goldCloth)
                .define('T', BotaniaItems.terrasteelBoots)
                .pattern("CTC")
                .pattern("GGG")
                .unlockedBy("has_item", conditionsFromItems(BotaniaItems.terrasteelBoots, BotaniaItems.gaiaIngot))
                .save(consumer);
    }

    private void ingotStorage(Block block, Item item, RecipeOutput consumer) {
        compression(block, item).save(consumer);
        deconstruct(consumer, block, item, BuiltInRegistries.BLOCK.getKey(block).getPath() + "_deconstruct");
    }

    private ShapedRecipeBuilder compression(ItemLike output, TagKey<Item> input) {
        return ShapedRecipeBuilder.shaped(output instanceof Block ? RecipeCategory.BUILDING_BLOCKS : RecipeCategory.MISC, output)
                .define('I', input)
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .unlockedBy("has_item", conditionsFromTag(input));
    }

    protected ShapedRecipeBuilder compression(ItemLike output, ItemLike input) {
        return ShapedRecipeBuilder.shaped(output instanceof Block ? RecipeCategory.BUILDING_BLOCKS : RecipeCategory.MISC, output)
                .define('I', input)
                .pattern("III")
                .pattern("III")
                .pattern("III")
                .unlockedBy("has_item", conditionsFromItem(input));
    }

    protected void deconstruct(RecipeOutput recipeOutput, ItemLike output, ItemLike input, String name) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, output, 9)
                .unlockedBy("has_item", conditionsFromItem(output))
                .requires(input)
                .save(recipeOutput, prefix("conversions/" + name));
    }

    protected void deconstruct(RecipeOutput recipeOutput, ItemLike output, TagKey<Item> input, String name) {
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, output, 9)
                .unlockedBy("has_item", conditionsFromItem(output))
                .requires(input)
                .save(recipeOutput, prefix("conversions/" + name));
    }

    protected void specialRecipe(RecipeOutput recipeOutput, Function<CraftingBookCategory, Recipe<?>> factory, CraftingBookCategory category) {
        BotaniaSpecialRecipeBuilder.special(factory, category).save(recipeOutput);
    }

    private static Criterion<InventoryChangeTrigger.TriggerInstance> conditionsFromItems(ItemLike... items) {
        ItemPredicate.Builder[] preds = new ItemPredicate.Builder[items.length];
        for (int i = 0; i < items.length; i++) {
            preds[i] = ItemPredicate.Builder.item().of(items[i]);
        }

        return RecipeProviderAccessor.botania_inventoryTrigger(preds);
    }

    protected void cosmeticBauble(RecipeOutput consumer, ItemLike output, ItemLike input) {
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, output)
                .define('P', input)
                .define('S', ExtraBotanyItems.spirit)
                .pattern("PPP")
                .pattern("PSP")
                .pattern("PPP")
                .group("extrabotany:cosmetic_bauble")
                .unlockedBy("has_item", conditionsFromItem(BotaniaItems.manaString))
                .save(consumer);
    }

    protected Item getItemOrThrow(ResourceLocation location) {
        return BuiltInRegistries.ITEM.getOrThrow(ResourceKey.create(Registries.ITEM, location));
    }

    protected void registerSimpleArmorSet(RecipeOutput consumer, Ingredient item, String variant, Criterion<InventoryChangeTrigger.TriggerInstance> criterion) {
        Item helmet = getItemOrThrow(prefix(variant + "_helmet"));
        Item chestplate = getItemOrThrow(prefix(variant + "_chestplate"));
        Item leggings = getItemOrThrow(prefix(variant + "_leggings"));
        Item boots = getItemOrThrow(prefix(variant + "_boots"));
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, helmet)
                .define('S', item)
                .pattern("SSS")
                .pattern("S S")
                .unlockedBy("has_item", criterion)
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, chestplate)
                .define('S', item)
                .pattern("S S")
                .pattern("SSS")
                .pattern("SSS")
                .unlockedBy("has_item", criterion)
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, leggings)
                .define('S', item)
                .pattern("SSS")
                .pattern("S S")
                .pattern("S S")
                .unlockedBy("has_item", criterion)
                .save(consumer);
        ShapedRecipeBuilder.shaped(RecipeCategory.COMBAT, boots)
                .define('S', item)
                .pattern("S S")
                .pattern("S S")
                .unlockedBy("has_item", criterion)
                .save(consumer);
    }
}
