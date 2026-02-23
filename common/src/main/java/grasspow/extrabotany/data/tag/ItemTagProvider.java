package grasspow.extrabotany.data.tag;

import grasspow.extrabotany.common.lib.ExtraBotanyTags;
import grasspow.extrabotany.common.lib.LibMisc;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.lens.LensItem;
import vazkii.botania.common.lib.BotaniaTags;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static grasspow.extrabotany.common.item.ExtraBotanyItems.*;

public class ItemTagProvider extends ItemTagsProvider {
    public ItemTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTagProvider) {
        super(packOutput, lookupProvider, blockTagProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        registerVanillaTag();
        registerBotaniaTag();
        Stream.of(
                manasteelHammer, elementiumHammer, terrasteelHammer, ultimateHammer
        ).forEach(tag(ExtraBotanyTags.Items.HAMMER)::add);
        tag(ExtraBotanyTags.Items.INGOTS_SHADOWIUM).add(shadowium);
        tag(ExtraBotanyTags.Items.INGOTS_PHOTONIUM).add(photonium);
        tag(ExtraBotanyTags.Items.INGOTS_AERIALITE).add(aerialite);
        tag(ExtraBotanyTags.Items.INGOTS_ORICHALCOS).add(orichalcos);
        tag(ExtraBotanyTags.Items.PEDESTAL_DENY).add(Items.SHIELD);
        tag(ExtraBotanyTags.Items.RUNES_WATER).add(elementRune, BotaniaItems.runeWater);
        tag(ExtraBotanyTags.Items.RUNES_FIRE).add(elementRune, BotaniaItems.runeFire);
        tag(ExtraBotanyTags.Items.RUNES_EARTH).add(elementRune, BotaniaItems.runeEarth);
        tag(ExtraBotanyTags.Items.RUNES_AIR).add(elementRune, BotaniaItems.runeAir);
        tag(ExtraBotanyTags.Items.RUNES_SPRING).add(elementRune, BotaniaItems.runeSpring);
        tag(ExtraBotanyTags.Items.RUNES_SUMMER).add(elementRune, BotaniaItems.runeSummer);
        tag(ExtraBotanyTags.Items.RUNES_AUTUMN).add(elementRune, BotaniaItems.runeAutumn);
        tag(ExtraBotanyTags.Items.RUNES_WINTER).add(elementRune, BotaniaItems.runeWinter);
        tag(ExtraBotanyTags.Items.RUNES_MANA).add(sinRune, BotaniaItems.runeMana);
        tag(ExtraBotanyTags.Items.RUNES_LUST).add(sinRune, BotaniaItems.runeLust);
        tag(ExtraBotanyTags.Items.RUNES_GLUTTONY).add(sinRune, BotaniaItems.runeGluttony);
        tag(ExtraBotanyTags.Items.RUNES_GREED).add(sinRune, BotaniaItems.runeGreed);
        tag(ExtraBotanyTags.Items.RUNES_SLOTH).add(sinRune, BotaniaItems.runeSloth);
        tag(ExtraBotanyTags.Items.RUNES_WRATH).add(sinRune, BotaniaItems.runeWrath);
        tag(ExtraBotanyTags.Items.RUNES_ENVY).add(sinRune, BotaniaItems.runeEnvy);
        tag(ExtraBotanyTags.Items.RUNES_PRIDE).add(sinRune, BotaniaItems.runePride);
    }

    private void registerVanillaTag() {
//        tag(ItemTags.MUSIC_DISCS).add(RECORD_EGO ,RECORD_HERRSCHER );

        //armor
        this.tag(ItemTags.HEAD_ARMOR).add(
                mikuHelm, shootingGuardianHelm , goblinsLayerHelm , maidHelm , shadowWarriorHelm
        );
        this.tag(ItemTags.CHEST_ARMOR).add(
                mikuChest , shootingGuardianChest , goblinsLayerChest , maidChest , shadowWarriorChest
        );
        this.tag(ItemTags.LEG_ARMOR).add(
                mikuLegs , shootingGuardianLegs , goblinsLayerLegs , maidLegs , shadowWarriorLegs
        );
        this.tag(ItemTags.FOOT_ARMOR).add(
                mikuBoots , shootingGuardianBoots , goblinsLayerBoots , maidBoots , shadowWarriorBoots
        );

        //weapon
        this.tag(ItemTags.SWORDS).add(
                shadowKatana , flamescionWeapon , influxWaver , starWrath ,
                trueShadowKatana , trueTerraBlade , excaliber , firstFractal
        );
    }


    private void registerBotaniaTag() {
        TagAppender<Item> builder = this.tag(BotaniaTags.Items.LENS);
		BuiltInRegistries.ITEM.stream().filter(i -> i instanceof LensItem && BuiltInRegistries.ITEM.getKey(i).getNamespace().equals(LibMisc.MOD_ID))
				.map(BuiltInRegistries.ITEM::getKey)
				.sorted()
				.forEach(item -> builder.add(ResourceKey.create(Registries.ITEM, item)));
        Stream.of(
                manasteelHammer, elementiumHammer, terrasteelHammer, ultimateHammer,
                infiniteWine, camera,
                frostStar, deathRing, manaDriveRing, jingweiFeather, potatoChips,sunRing, moonPendant, rodOfDiscord, silverBullet,
                mikuHelm, mikuChest, mikuLegs, mikuBoots,
                goblinsLayerHelm, goblinsLayerChest, goblinsLayerLegs, goblinsLayerBoots,
                shadowWarriorHelm, shadowWarriorChest, shadowWarriorLegs, shadowWarriorBoots,
                shootingGuardianHelm, shootingGuardianChest, shootingGuardianLegs, shootingGuardianBoots,
                maidHelm, maidChest, maidLegs, maidBoots,
                influxWaver, starWrath,
                trueShadowKatana, trueTerraBlade,
                excaliber, firstFractal, fallnaught
//                ,coreGod
        ).forEach(tag(BotaniaTags.Items.MANA_USING_ITEMS)::add);
        Stream.of(
                        BotaniaTags.Items.PETALS,
                        BotaniaTags.Items.PETALS_BLACK,
                        BotaniaTags.Items.PETALS_BLUE,
                        BotaniaTags.Items.PETALS_BROWN,
                        BotaniaTags.Items.PETALS_CYAN,
                        BotaniaTags.Items.PETALS_GRAY,
                        BotaniaTags.Items.PETALS_GREEN,
                        BotaniaTags.Items.PETALS_LIGHT_BLUE,
                        BotaniaTags.Items.PETALS_LIGHT_GRAY,
                        BotaniaTags.Items.PETALS_LIME,
                        BotaniaTags.Items.PETALS_MAGENTA,
                        BotaniaTags.Items.PETALS_ORANGE,
                        BotaniaTags.Items.PETALS_PINK,
                        BotaniaTags.Items.PETALS_PURPLE,
                        BotaniaTags.Items.PETALS_RED,
                        BotaniaTags.Items.PETALS_WHITE,
                        BotaniaTags.Items.PETALS_YELLOW)
                .forEach(i -> tag(i).add(universalPetal));
        tag(BotaniaTags.Items.RUNES).add(elementRune, sinRune);
    }
}
