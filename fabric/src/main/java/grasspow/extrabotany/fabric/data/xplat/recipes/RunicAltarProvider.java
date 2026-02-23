package grasspow.extrabotany.fabric.data.xplat.recipes;

import grasspow.extrabotany.common.item.ExtraBotanyItems;
import grasspow.extrabotany.common.lib.ExtraBotanyTags;
import grasspow.extrabotany.common.lib.LibItemNames;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import vazkii.botania.common.item.BotaniaItems;

import java.util.concurrent.CompletableFuture;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;

public class RunicAltarProvider extends vazkii.botania.fabric.data.xplat.RunicAltarProvider {
	public RunicAltarProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
		super(packOutput, lookupProvider);
	}

    @Override
    public String getName() {
        return "ExtraBotany rune altar recipes";
    }

    @Override
    public void buildRecipes(RecipeOutput consumer) {
        Ingredient spirit = ingr(ExtraBotanyItems.spirit);
        Ingredient nightmareFuel = ingr(ExtraBotanyItems.nightmareFuel);
        Ingredient heroMedal = ingr(ExtraBotanyItems.heroMedal);
        Ingredient gaiaIngot = ingr(BotaniaItems.gaiaIngot);
        Ingredient lifeEssence = ingr(BotaniaItems.lifeEssence);
        Ingredient gilded_mashed_potato = ingr(ExtraBotanyItems.gildedMashedPotato);
        Ingredient quartz = ingr(Items.QUARTZ);
        Ingredient lapis = ingr(Items.LAPIS_LAZULI);
        Ingredient manaDiamond = ingr(BotaniaItems.manaDiamond);
        Ingredient manaCloth = ingr(BotaniaItems.manaweaveCloth);
        Ingredient manaSteel = ingr(BotaniaItems.manaSteel);
        defaultReagent(consumer, idFor(LibItemNames.ULTIMATE_HAMMER), new ItemStack(ExtraBotanyItems.ultimateHammer), 100000, gilded_mashed_potato, gilded_mashed_potato, gilded_mashed_potato, ingr(Items.GOLD_BLOCK), ingr(ExtraBotanyItems.terrasteelHammer));
        defaultReagent(consumer, idFor(LibItemNames.PHOTONIUM), new ItemStack(ExtraBotanyItems.photonium), 4200, ingr(BotaniaItems.elementium), gilded_mashed_potato, spirit, spirit);
        defaultReagent(consumer, idFor(LibItemNames.PHOTONIUM + "_"), new ItemStack(ExtraBotanyItems.photonium, 3), 12600, ingr(BotaniaItems.elementium), ingr(BotaniaItems.elementium), ingr(BotaniaItems.elementium), gilded_mashed_potato, spirit, spirit, spirit, spirit, spirit);
        defaultReagent(consumer, idFor(LibItemNames.SHADOWIUM), new ItemStack(ExtraBotanyItems.shadowium), 4200, ingr(BotaniaItems.elementium), gilded_mashed_potato, nightmareFuel, nightmareFuel);
        defaultReagent(consumer, idFor(LibItemNames.SHADOWIUM + "_"), new ItemStack(ExtraBotanyItems.shadowium, 3), 12600, ingr(BotaniaItems.elementium), ingr(BotaniaItems.elementium), ingr(BotaniaItems.elementium), gilded_mashed_potato, nightmareFuel, nightmareFuel, nightmareFuel, nightmareFuel, nightmareFuel);
        defaultReagent(consumer, idFor(LibItemNames.GILDED_POTATO), new ItemStack(ExtraBotanyItems.gildedMashedPotato), 800, ingr(Items.POTATO), ingr(Items.GOLD_NUGGET));

        defaultReagent(consumer, idFor(LibItemNames.AERO_STONE), new ItemStack(ExtraBotanyItems.aeroStone), 2000, ingr(ExtraBotanyTags.Items.RUNES_AIR), ingr(ExtraBotanyTags.Items.RUNES_AIR), gaiaIngot, quartz, lapis, manaDiamond);
        defaultReagent(consumer, idFor(LibItemNames.AQUA_STONE), new ItemStack(ExtraBotanyItems.aquaStone), 2000, ingr(ExtraBotanyTags.Items.RUNES_WATER), ingr(ExtraBotanyTags.Items.RUNES_WATER), gaiaIngot, quartz, lapis, manaDiamond);
        defaultReagent(consumer, idFor(LibItemNames.EARTH_STONE), new ItemStack(ExtraBotanyItems.earthStone), 2000, ingr(ExtraBotanyTags.Items.RUNES_EARTH), ingr(ExtraBotanyTags.Items.RUNES_EARTH), gaiaIngot, quartz, lapis, manaDiamond);
        defaultReagent(consumer, idFor(LibItemNames.IGNIS_STONE), new ItemStack(ExtraBotanyItems.ignisStone), 2000, ingr(ExtraBotanyTags.Items.RUNES_FIRE), ingr(ExtraBotanyTags.Items.RUNES_FIRE), gaiaIngot, quartz, lapis, manaDiamond);
        defaultReagent(consumer, idFor(LibItemNames.THE_COMMUNITY), new ItemStack(ExtraBotanyItems.theCommunity), 10000, ingr(ExtraBotanyItems.aeroStone), ingr(ExtraBotanyItems.aquaStone), gaiaIngot, ingr(ExtraBotanyItems.earthStone), ingr(ExtraBotanyItems.ignisStone), ingr(ExtraBotanyItems.theChaos));
        defaultReagent(consumer, idFor(LibItemNames.POWER_GLOVE), new ItemStack(ExtraBotanyItems.powerGrove), 2000, ingr(ExtraBotanyTags.Items.RUNES_WRATH), manaCloth, manaCloth, manaCloth, manaDiamond);
        defaultReagent(consumer, idFor(LibItemNames.FROST_STAR), new ItemStack(ExtraBotanyItems.frostStar), 2000, ingr(ExtraBotanyTags.Items.RUNES_MANA), manaSteel, manaSteel, ingr(Blocks.ICE), ingr(Blocks.ICE));
        defaultReagent(consumer, idFor(LibItemNames.DEATH_RING), new ItemStack(ExtraBotanyItems.deathRing), 2000, ingr(ExtraBotanyTags.Items.RUNES_ENVY), manaSteel, manaSteel, ingr(Blocks.WITHER_SKELETON_SKULL), manaDiamond);
        defaultReagent(consumer, idFor(LibItemNames.POTATO_CHIPS), new ItemStack(ExtraBotanyItems.potatoChips), 50000, ingr(BotaniaItems.tinyPotatoMask), ingr(ExtraBotanyItems.gildedPotato), ingr(Blocks.BLAST_FURNACE), ingr(Blocks.CAMPFIRE), ingr(Items.TOTEM_OF_UNDYING), ingr(Items.TOTEM_OF_UNDYING), ingr(Items.TOTEM_OF_UNDYING), ingr(ExtraBotanyTags.Items.RUNES_MANA));
        defaultReagent(consumer, idFor(LibItemNames.SUN_RING), new ItemStack(ExtraBotanyItems.sunRing), 500000, ingr(ExtraBotanyItems.theEnd), ingr(BotaniaItems.reachRing), ingr(ExtraBotanyItems.manaDriveRing), ingr(BotaniaItems.waterRing), ingr(BotaniaItems.swapRing), ingr(BotaniaItems.pixieRing), ingr(BotaniaItems.miningRing), ingr(BotaniaItems.auraRingGreater), ingr(ExtraBotanyItems.deathRing), ingr(ExtraBotanyItems.frostStar));
        defaultReagent(consumer, idFor(LibItemNames.MOON_PENDANT), new ItemStack(ExtraBotanyItems.moonPendant), 500000, ingr(ExtraBotanyItems.theOrigin), ingr(BotaniaItems.itemFinder), ingr(BotaniaItems.icePendant), ingr(BotaniaItems.superLavaPendant), ingr(BotaniaItems.superCloudPendant), ingr(BotaniaItems.knockbackBelt));
        defaultReagent(consumer, idFor(LibItemNames.FIRST_FRACTAL), new ItemStack(ExtraBotanyItems.firstFractal), 500000,
                ingr(ExtraBotanyItems.trueShadowKatana), ingr(ExtraBotanyItems.trueTerraBlade), ingr(ExtraBotanyItems.influxWaver), ingr(ExtraBotanyItems.starWrath), ingr(ExtraBotanyItems.excaliber),
                ingr(Items.WOODEN_SWORD), ingr(Items.DIAMOND_SWORD), ingr(Items.NETHERITE_SWORD),
                ingr(BotaniaItems.manasteelSword), ingr(BotaniaItems.elementiumSword),
                ingr(ExtraBotanyItems.gildedMashedPotato), ingr(ExtraBotanyItems.theUniverse)
        );
    }

    private Ingredient ingr(TagKey<Item> i) {
        return Ingredient.of(i);
    }

    protected static Ingredient ingr(ItemLike i) {
        return Ingredient.of(i);
    }

    private static ResourceLocation idFor(String s) {
        return exbotRL("runic_altar/" + s);
    }
}
