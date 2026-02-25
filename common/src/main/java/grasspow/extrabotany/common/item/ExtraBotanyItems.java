package grasspow.extrabotany.common.item;

import grasspow.extrabotany.common.component.ExtraBotanyDataComponents;
import grasspow.extrabotany.common.item.brew.BaseBrewItemEX;
import grasspow.extrabotany.common.item.brew.CocktailItem;
import grasspow.extrabotany.common.item.brew.InfiniteWineItem;
import grasspow.extrabotany.common.item.brew.SplashGrenadeItem;
import grasspow.extrabotany.common.item.equipment.BuddhistRelicsItem;
import grasspow.extrabotany.common.item.equipment.armor.*;
import grasspow.extrabotany.common.item.equipment.bauble.*;
import grasspow.extrabotany.common.item.equipment.tool.*;
import grasspow.extrabotany.common.item.equipment.tool.hammer.ManasteelHammer;
import grasspow.extrabotany.common.item.equipment.tool.hammer.UltimateHammer;
import grasspow.extrabotany.common.item.equipment.weapon.*;
import grasspow.extrabotany.common.item.food.ModFoods;
import grasspow.extrabotany.common.item.len.*;
import grasspow.extrabotany.common.item.misc.*;
import grasspow.extrabotany.common.lib.LibItemNames;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Unit;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import vazkii.botania.common.component.BotaniaDataComponents;
import vazkii.botania.common.item.lens.Lens;
import vazkii.botania.common.item.lens.LensItem;
import vazkii.botania.xplat.XplatAbstractions;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;
import static vazkii.botania.common.item.BotaniaItems.unstackableCustomDamage;

public class ExtraBotanyItems {

    private static final Map<String, Item> ALL = new LinkedHashMap<>();

    // bauble
    public static final Item aeroStone = make(LibItemNames.AERO_STONE, AeroStoneItem::new, nonStackable());
    public static final Item aquaStone = make(LibItemNames.AQUA_STONE, AquaStoneItem::new, nonStackable());
    public static final Item earthStone = make(LibItemNames.EARTH_STONE, EarthStoneItem::new, nonStackable());
    public static final Item ignisStone = make(LibItemNames.IGNIS_STONE, IgnisStoneItem::new, nonStackable());
    public static final Item theCommunity = make(LibItemNames.THE_COMMUNITY, TheCommunityItem::new, nonStackable());
    public static final Item peaceAmulet = make(LibItemNames.PEACE_AMULET, PeaceAmuletItem::new, nonStackable());
    public static final Item powerGrove = make(LibItemNames.POWER_GLOVE, PowerGloveItem::new, nonStackable());
    public static final Item frostStar = make(LibItemNames.FROST_STAR, FrostStarItem::new, nonStackable());
    public static final Item deathRing = make(LibItemNames.DEATH_RING, DeathRingItem::new, nonStackable());
    public static final Item manaDriveRing = make(LibItemNames.MANA_DRIVE_RING, ManaDriveRingItem::new, nonStackable());
    public static final Item natureOrb = make(LibItemNames.NATURE_ORB, NatureOrbItem::new, nonStackable()
            .component(ExtraBotanyDataComponents.MAX_NATURE, NatureOrbItem.DEFAULT_MAX_NATURE));
    public static final Item jingweiFeather = make(LibItemNames.JINGWEI_FEATHER, JingweiFeatherItem::new, nonStackable());
    public static final Item potatoChips = make(LibItemNames.POTATO_CHIPS, PotatoChipsItem::new, nonStackable());
    public static final Item sunRing = make(LibItemNames.SUN_RING, SunRingItem::new, nonStackable());
    public static final Item moonPendant = make(LibItemNames.MOON_PENDANT, MoonPendantItem::new, nonStackable());
    public static final Item sagesManaRing = make(LibItemNames.SAGES_MANA_RING, SagesManaRingItem::new, nonStackable()
            .component(BotaniaDataComponents.MAX_MANA, SagesManaRingItem.DEFAULT_MAX_MANA)
            .component(BotaniaDataComponents.CAN_PROVIDE_MANA_TO_ITEMS, Unit.INSTANCE)
            .component(BotaniaDataComponents.CAN_DRAIN_MANA_TO_POOL, Unit.INSTANCE)
            .component(BotaniaDataComponents.CAN_ACCEPT_MANA_FROM_ITEMS, Unit.INSTANCE)
            .component(BotaniaDataComponents.CAN_RECEIVE_MANA_FROM_POOL, Unit.INSTANCE));
//    public static final Item coreGod = regDefItem(LibItemNames.CORE_GOD, CoreGodItem::new, nonStackable());

    // food
    public static final Item spiritFuel = make(LibItemNames.SPIRIT_FUEL, food(ModFoods.SPIRIT_FUEL));
    public static final Item nightmareFuel = make(LibItemNames.NIGHTMARE_FUEL, NightmareFuelItem::new, food(ModFoods.NIGHTMARE_FUEL));
    public static final Item gildedMashedPotato = make(LibItemNames.GILDED_MASHED_POTATO, food(ModFoods.GILDED_MASHED_POTATO));
    public static final Item friedChicken = make(LibItemNames.FRIED_CHICKEN, food(ModFoods.FRIED_CHICKEN));
    public static final Item manaDrink = make(LibItemNames.MANA_DRINK, ManaDrinkItem::new, food(ModFoods.MANA_DRINK));

    // lens
    public static final Item manaLens = make(LibItemNames.MANA_LENS, stackTo16(), ManaLens::new, LensItem.PROP_INTERACTION);
    public static final Item potionLens = make(LibItemNames.POTION_LENS, stackTo16(), PotionLens::new, LensItem.PROP_INTERACTION, true);
    public static final Item pushLens = make(LibItemNames.PUSH_LENS, stackTo16(), PushLens::new, LensItem.PROP_INTERACTION);
    public static final Item smeltLens = make(LibItemNames.SMELT_LENS, stackTo16(), SmeltLens::new, LensItem.PROP_TOUCH);
    public static final Item superConductorLens = make(LibItemNames.SUPER_CONDUCTOR_LENS, stackTo16(), SuperConductorLens::new, LensItem.PROP_POWER);
    public static final Item traceLens = make(LibItemNames.TRACE_LENS, stackTo16(), TraceLens::new, LensItem.PROP_CONTROL);

    // tool
    public static final Item manasteelHammer = make(LibItemNames.MANASTEEL_HAMMER, ManasteelHammer::new, ModTiers.MANASTEEL, nonStackable());
    public static final Item elementiumHammer = make(LibItemNames.ELEMENTIUM_HAMMER, ManasteelHammer::new, ModTiers.ELEMENTIUM, nonStackable());
    public static final Item terrasteelHammer = make(LibItemNames.TERRASTEEL_HAMMER, ManasteelHammer::new, ModTiers.TERRASTEEL, nonStackable());
    public static final Item ultimateHammer = make(LibItemNames.ULTIMATE_HAMMER, UltimateHammer::new, nonStackable().rarity(Rarity.EPIC));
    public static final Item manaReader = make(LibItemNames.MANA_READER, ManaReader::new, nonStackable());
    public static final Item walkingCane = make(LibItemNames.WALKING_CANE, WalkingCaneItem::new,
            nonStackable().attributes(
                    ItemAttributeModifiers.builder()
                            .add(Attributes.MOVEMENT_SPEED,
                                    new AttributeModifier(
                                            exbotRL(LibItemNames.WALKING_CANE + "_modifier"),
                                            0.6,
                                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                                    EquipmentSlotGroup.HAND)
                            .build()
            ));
    public static final Item rodOfDiscord = make(LibItemNames.ROD_OF_DISCORD, RodOfDiscordItem::new, nonStackable());
    public static final Item silverBullet = make(LibItemNames.SILVER_BULLET, SilverBulletItem::new, nonStackable());
    public static final Item camera = make(LibItemNames.CAMERA, CameraItem::new, nonStackable());

    // armor
    public static final Item mikuHelm = makeArmor(LibItemNames.MIKU_HELM, MikuArmorItem::new, nonStackable(), ArmorItem.Type.HELMET);
    public static final Item mikuChest = makeArmor(LibItemNames.MIKU_CHEST, MikuArmorItem::new, nonStackable(), ArmorItem.Type.CHESTPLATE);
    public static final Item mikuLegs = makeArmor(LibItemNames.MIKU_LEGS, MikuArmorItem::new, nonStackable(), ArmorItem.Type.LEGGINGS);
    public static final Item mikuBoots = makeArmor(LibItemNames.MIKU_BOOTS, MikuArmorItem::new, nonStackable(), ArmorItem.Type.BOOTS);
    public static final Item goblinsLayerHelm = makeArmor(LibItemNames.GOBLINS_LAYER_HELM, GoblinsLayerHelmetItem::new, nonStackable(), ArmorItem.Type.HELMET);
    public static final Item goblinsLayerChest = makeArmor(LibItemNames.GOBLINS_LAYER_CHEST, GoblinsLayerArmorItem::new, nonStackable(), ArmorItem.Type.CHESTPLATE);
    public static final Item goblinsLayerLegs = makeArmor(LibItemNames.GOBLINS_LAYER_LEGS, GoblinsLayerArmorItem::new, nonStackable(), ArmorItem.Type.LEGGINGS);
    public static final Item goblinsLayerBoots = makeArmor(LibItemNames.GOBLINS_LAYER_BOOTS, GoblinsLayerArmorItem::new, nonStackable(), ArmorItem.Type.BOOTS);
    public static final Item shadowWarriorHelm = makeArmor(LibItemNames.SHADOW_WARRIOR_HELM, ShadowWarriorArmorItem::new, nonStackable(), ArmorItem.Type.HELMET);
    public static final Item shadowWarriorChest = makeArmor(LibItemNames.SHADOW_WARRIOR_CHEST, ShadowWarriorArmorItem::new, nonStackable(), ArmorItem.Type.CHESTPLATE);
    public static final Item shadowWarriorLegs = makeArmor(LibItemNames.SHADOW_WARRIOR_LEGS, ShadowWarriorArmorItem::new, nonStackable(), ArmorItem.Type.LEGGINGS);
    public static final Item shadowWarriorBoots = makeArmor(LibItemNames.SHADOW_WARRIOR_BOOTS, ShadowWarriorArmorItem::new, nonStackable(), ArmorItem.Type.BOOTS);
    public static final Item shootingGuardianHelm = makeArmor(LibItemNames.SHOOTING_GUARDIAN_HELM, ShootingGuardianArmorItem::new, nonStackable(), ArmorItem.Type.HELMET);
    public static final Item shootingGuardianChest = makeArmor(LibItemNames.SHOOTING_GUARDIAN_CHEST, ShootingGuardianArmorItem::new, nonStackable(), ArmorItem.Type.CHESTPLATE);
    public static final Item shootingGuardianLegs = makeArmor(LibItemNames.SHOOTING_GUARDIAN_LEGS, ShootingGuardianArmorItem::new, nonStackable(), ArmorItem.Type.LEGGINGS);
    public static final Item shootingGuardianBoots = makeArmor(LibItemNames.SHOOTING_GUARDIAN_BOOTS, ShootingGuardianArmorItem::new, nonStackable(), ArmorItem.Type.BOOTS);
    public static final Item maidHelm = makeArmor(LibItemNames.MAID_HELM, MaidArmorHelmetItem::new, nonStackable(), ArmorItem.Type.HELMET);
    public static final Item maidChest = makeArmor(LibItemNames.MAID_CHEST, MaidArmorItem::new, nonStackable(), ArmorItem.Type.CHESTPLATE);
    public static final Item maidLegs = makeArmor(LibItemNames.MAID_LEGS, MaidArmorItem::new, nonStackable(), ArmorItem.Type.LEGGINGS);
    public static final Item maidBoots = makeArmor(LibItemNames.MAID_BOOTS, MaidArmorItem::new, nonStackable(), ArmorItem.Type.BOOTS);

    // weapon
    public static final Item shadowKatana = make(LibItemNames.SHADOW_KATANA, ShadowKatanaItem::new, Tiers.IRON,
            nonStackable().rarity(Rarity.UNCOMMON));
    public static final Item flamescionWeapon = make(LibItemNames.FLAMESCION_WEAPON, FlamescionWeaponItem::new, Tiers.NETHERITE,
            unstackableCustomDamage().fireResistant().rarity(Rarity.EPIC));
    public static final Item influxWaver = make(LibItemNames.INFLUX_WAVER, InfluxWaverItem::new, Tiers.DIAMOND,
            unstackableCustomDamage().fireResistant().rarity(Rarity.UNCOMMON).attributes(SwordItem.createAttributes(Tiers.DIAMOND, 5, -2F)));
    public static final Item starWrath = make(LibItemNames.STAR_WRATH, StarWrathItem::new, Tiers.DIAMOND,
            unstackableCustomDamage().fireResistant().rarity(Rarity.UNCOMMON).attributes(SwordItem.createAttributes(Tiers.DIAMOND, 6, -1.6F)));
    public static final Item trueShadowKatana = make(LibItemNames.TRUE_SHADOW_KATANA, TrueShadowKatanaItem::new, Tiers.DIAMOND,
            unstackableCustomDamage().fireResistant().rarity(Rarity.UNCOMMON).attributes(SwordItem.createAttributes(Tiers.DIAMOND, 5, -2F)));
    public static final Item trueTerraBlade = make(LibItemNames.TRUE_TERRA_BLADE, TrueTerraBladeItem::new, Tiers.DIAMOND,
            unstackableCustomDamage().fireResistant().rarity(Rarity.UNCOMMON).attributes(SwordItem.createAttributes(Tiers.DIAMOND, 5, -2F)));
    public static final Item excaliber = make(LibItemNames.EXCALIBER, ExcaliberItem::new, Tiers.NETHERITE,
            unstackableCustomDamage().fireResistant().rarity(Rarity.UNCOMMON)
                    .attributes(SwordItem.createAttributes(Tiers.NETHERITE, 8, -2f)
                            .withModifierAdded(
                                    Attributes.MOVEMENT_SPEED,
                                    new AttributeModifier(
                                            exbotRL(LibItemNames.EXCALIBER + "_modifier"),
                                            0.3,
                                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                                    EquipmentSlotGroup.MAINHAND
                            )
                    )
    );
    public static final Item firstFractal = make(LibItemNames.FIRST_FRACTAL, FirstFractalItem::new, Tiers.NETHERITE,
            unstackableCustomDamage().fireResistant().rarity(Rarity.EPIC)
                    .attributes(SwordItem.createAttributes(Tiers.NETHERITE, 8, -2f)
                            .withModifierAdded(
                                    Attributes.MOVEMENT_SPEED,
                                    new AttributeModifier(
                                            exbotRL(LibItemNames.FIRST_FRACTAL + "_modifier"),
                                            0.3,
                                            AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL),
                                    EquipmentSlotGroup.MAINHAND
                            )
                    )
    );
    public static final Item fallnaught = make(LibItemNames.FAILNAUGHT, FailnaughtItem::new, unstackableCustomDamage().fireResistant().rarity(Rarity.UNCOMMON));

    // cosmetic bauble
    public static final Item foxEar = make(LibItemNames.FOX_EAR, CosmeticBaubleItem.Variant.FOX_EAR, new Item.Properties());
    public static final Item foxMask = make(LibItemNames.FOX_MASK, CosmeticBaubleItem.Variant.FOX_MASK, new Item.Properties());
    public static final Item pylon = make(LibItemNames.PYLON, CosmeticBaubleItem.Variant.PYLON, new Item.Properties());
    public static final Item blackGrasses = make(LibItemNames.BLACK_GLASSES, CosmeticBaubleItem.Variant.BLACK_GLASSES, new Item.Properties());
    public static final Item thugLife = make(LibItemNames.THUG_LIFE, CosmeticBaubleItem.Variant.THUG_LIFE, new Item.Properties());
    public static final Item redScarf = make(LibItemNames.RED_SCARF, CosmeticBaubleItem.Variant.RED_SCARF, new Item.Properties());
    public static final Item mask = make(LibItemNames.MASK, CosmeticBaubleItem.Variant.MASK, new Item.Properties());
    public static final Item superCrown = make(LibItemNames.SUPER_CROWN, CosmeticBaubleItem.Variant.SUPER_CROWN, new Item.Properties());

    // misc
    public static final Item gildedPotato = make(LibItemNames.GILDED_POTATO, defaultBuilder());
    public static final Item photonium = make(LibItemNames.PHOTONIUM, defaultBuilder());
    public static final Item shadowium = make(LibItemNames.SHADOWIUM, defaultBuilder());
    public static final Item aerialite = make(LibItemNames.AERIALITE, defaultBuilder());
    public static final Item orichalcos = make(LibItemNames.ORICHALCOS, defaultBuilder());
    public static final Item spirit = make(LibItemNames.SPIRIT, defaultBuilder());
    public static final Item emptyBottle = make(LibItemNames.EMPTY_BOTTLE, EmptyBottleItem::new, defaultBuilder());
    public static final Item heroMedal = make(LibItemNames.HERO_MEDAL, defaultBuilder());
    public static final Item goldCloth = make(LibItemNames.GOLD_CLOTH, GoldClothItem::new, defaultBuilder());

    public static final Item universalPetal = make(LibItemNames.UNIVERSAL_PETAL, defaultBuilder());
    public static final Item elementRune = make(LibItemNames.ELEMENT_RUNE, defaultBuilder());
    public static final Item sinRune = make(LibItemNames.SIN_RUNE, defaultBuilder());

    public static final Item theChaos = make(LibItemNames.THE_CHAOS, defaultBuilder());
    public static final Item theOrigin = make(LibItemNames.THE_ORIGIN, defaultBuilder());
    public static final Item theEnd = make(LibItemNames.THE_END, defaultBuilder());
    public static final Item theUniverse = make(LibItemNames.THE_UNIVERSE, defaultBuilder());

    public static final Item challengeTicket = make(LibItemNames.CHALLENGE_TICKET, ChallengeTicketItem::new, defaultBuilder().fireResistant());
    public static final Item treasureBox = make(LibItemNames.TREASURE_BOX, TreasureBoxItem::new, defaultBuilder());
    public static final Item rewardBagA = makeRewardBag(LibItemNames.REWARD_BAG_A, RewardBagItem::new, defaultBuilder(), RewardBagItem.Variant.Eins);
    public static final Item rewardBagB = makeRewardBag(LibItemNames.REWARD_BAG_B, RewardBagItem::new, defaultBuilder(), RewardBagItem.Variant.Zwei);
    public static final Item rewardBagC = makeRewardBag(LibItemNames.REWARD_BAG_C, RewardBagItem::new, defaultBuilder(), RewardBagItem.Variant.Drei);
    public static final Item rewardBagD = makeRewardBag(LibItemNames.REWARD_BAG_D, RewardBagItem::new, defaultBuilder(), RewardBagItem.Variant.Vier);
    public static final Item rewardBag943 = makeRewardBag(LibItemNames.REWARD_BAG_943, RewardBagItem::new, defaultBuilder(), RewardBagItem.Variant._943);
    public static final Item buddhistRelics = make(LibItemNames.BUDDHIST_RELICS, BuddhistRelicsItem::new, relic());

    // record
//    public static final Item RECORD_EGO = make(LibItemNames.RECORD_EGO,nonStackable().rarity(Rarity.EPIC).jukeboxPlayable(ExtraBotanySounds.SWORDLAND.get()));
//    public static final Item RECORD_HERRSCHER = make(LibItemNames.RECORD_HERRSCHER,nonStackable().rarity(Rarity.EPIC).jukeboxPlayable(ExtraBotanySounds.SALVATION.get()));

    // brew
    public static final Item cocktail = make(LibItemNames.COCKTAIL, CocktailItem::new, nonStackable().component(BotaniaDataComponents.MAX_USES, BaseBrewItemEX.DEFAULT_USES_COCKTAIL));
    public static final Item infiniteWine = make(LibItemNames.INFINITE_WINE, InfiniteWineItem::new, nonStackable().component(BotaniaDataComponents.MAX_USES, BaseBrewItemEX.DEFAULT_USES_INFINITE_WINE));
    public static final Item splashGrenade = make(LibItemNames.SPLASH_GRENADE, SplashGrenadeItem::new, stackTo32());

    private static Item make(String name, Item.Properties props, Supplier<Lens> lens, int prop) {
        return make(name, props, lens, prop, false);
    }

    private static Item make(String name, Item.Properties props, Supplier<Lens> lens, int prop, boolean isBrew) {
        BrewLensItem item = new BrewLensItem(props, lens.get(), prop, isBrew);
        var old = ALL.put(name, item);
        if (old != null) {
            throw new IllegalArgumentException("Typo? Duplicate name: " + name);
        }
        return item;
    }

    private static Item make(String name, Item.Properties props) {
        Item item = new Item(props);
        var old = ALL.put(name, item);
        if (old != null) {
            throw new IllegalArgumentException("Typo? Duplicate name: " + name);
        }
        return item;
    }

    private static Item make(String name, Function<Item.Properties, Item> func, Item.Properties props) {
        Item item = func.apply(props);
        var old = ALL.put(name, item);
        if (old != null) {
            throw new IllegalArgumentException("Typo? Duplicate name: " + name);
        }
        return item;
    }

    private static Item make(String name, BiFunction<Tier, Item.Properties, Item> func, Tier tier, Item.Properties props) {
        Item item = func.apply(tier, props);
        var old = ALL.put(name, item);
        if (old != null) {
            throw new IllegalArgumentException("Typo? Duplicate name: " + name);
        }
        return item;
    }

    private static Item make(String name, CosmeticBaubleItem.Variant variant, Item.Properties props) {
        Item item = new CosmeticBaubleItem(variant, props);
        var old = ALL.put(name, item);
        if (old != null) {
            throw new IllegalArgumentException("Typo? Duplicate name: " + name);
        }
        return item;
    }

    private static Item makeArmor(String name, BiFunction<ArmorItem.Type, Item.Properties, ArmorItem> func, Item.Properties props, ArmorItem.Type type) {
        Item item = func.apply(type, props);
        var old = ALL.put(name, item);
        if (old != null) {
            throw new IllegalArgumentException("Typo? Duplicate name: " + name);
        }
        return item;
    }

    private static Item makeRewardBag(String name, BiFunction<Item.Properties, RewardBagItem.Variant, Item> func, Item.Properties props, RewardBagItem.Variant variant) {
        Item item = func.apply(props, variant);
        var old = ALL.put(name, item);
        if (old != null) {
            throw new IllegalArgumentException("Typo? Duplicate name: " + name);
        }
        return item;
    }

    public static Item.Properties defaultBuilder() {
        return XplatAbstractions.INSTANCE.defaultItemBuilder();
    }

    private static Item.Properties food(FoodProperties props) {
        return defaultBuilder().food(props);
    }

    private static Item.Properties nonStackable() {
        return defaultBuilder().stacksTo(1);
    }

    private static Item.Properties stackTo16() {
        return defaultBuilder().stacksTo(16);
    }

    private static Item.Properties stackTo32() {
        return defaultBuilder().stacksTo(32);
    }

    private static Item.Properties relic() {
        return nonStackable().rarity(Rarity.EPIC);
    }

    public static void registerItems(BiConsumer<Item, ResourceLocation> r) {
        for (var e : ALL.entrySet()) {
            r.accept(e.getValue(), exbotRL(e.getKey()));
        }
    }
}
