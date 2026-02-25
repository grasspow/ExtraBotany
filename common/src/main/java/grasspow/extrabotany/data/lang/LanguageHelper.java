package grasspow.extrabotany.data.lang;

import grasspow.extrabotany.common.block.ExtraBotanyBlocks;
import grasspow.extrabotany.common.entity.ExtraBotanyEntities;
import grasspow.extrabotany.common.item.ExtraBotanyItems;
import grasspow.extrabotany.common.lib.LibAdvancementNames;
import grasspow.extrabotany.common.lib.LibBlockNames;
import grasspow.extrabotany.common.lib.LibEntityNames;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;

public class LanguageHelper {

    public static final Map<String, String> en_us = new HashMap<>();
    public static final Map<String, String> zh_cn = new HashMap<>();

    public static void init() {
        add("itemGroup.extrabotany", "ExtraBotany", "额外植物学");
        transItem();
        transBlock();
        transEntity();
        transAdvancement();

        //brew
        add("extrabotany.brew.revolution", "Revolution", "革命");
        add("extrabotany.brew.all_mighty", "All-mighty", "全能");
        add("extrabotany.brew.shell", "Shell", "龟壳");
        add("extrabotany.brew.deadpool", "Deadpool", "死灵");
        add("extrabotany.brew.floating", "Floating", "漂浮");

        //damage_type
        add("death.attack.general_armor_piercing", "%1$s was slain by %2$s", "%1$s被%2$s杀死了");
        add("death.attack.general_armor_piercing.item", "%1$s was slain by %2$s with %3$s", "%1$s被%2$s用%3$s杀死了");
        add("death.attack.magic_armor_piercing", "%1$s was slain by %2$s", "%1$s被%2$s杀死了");
        add("death.attack.magic_armor_piercing.item", "%1$s was slain by %2$s with %3$s", "%1$s被%2$s用%3$s杀死了");
        add("death.attack.critical", "%1$s was slain by %2$s", "%1$s被%2$s杀死了");
        add("death.attack.critical.item", "%1$s was slain by %2$s with %3$s", "%1$s被%2$s用%3$s杀死了");

        //ultimate_hammer upgrade
        add("extrabotany.upgrade.attack1", "Sharpness Upgrade I", "锋利升级I");
        add("extrabotany.upgrade.attack2", "Sharpness Upgrade II", "锋利升级II");
        add("extrabotany.upgrade.attack3", "Sharpness Upgrade III", "锋利升级III");
        add("extrabotany.upgrade.attack4", "Sharpness Upgrade IV", "锋利升级IV");
        add("extrabotany.upgrade.attack5", "Sharpness Upgrade V", "锋利升级V");
        add("extrabotany.upgrade.attack6", "Sharpness Upgrade VI", "锋利升级VI");
        add("extrabotany.upgrade.attack7", "Sharpness Upgrade VII", "锋利升级VII");
        add("extrabotany.upgrade.attack8", "Sharpness Upgrade VIII", "锋利升级VIII");
        add("extrabotany.upgrade.attack9", "Sharpness Upgrade IX", "锋利升级IX");
        add("extrabotany.upgrade.attack10", "Sharpness Upgrade X", "锋利升级X");
        add("extrabotany.upgrade.repair1", "Repair Upgrade I", "修复升级I");
        add("extrabotany.upgrade.repair2", "Repair Upgrade II", "修复升级II");
        add("extrabotany.upgrade.repair3", "Repair Upgrade III", "修复升级III");
        add("extrabotany.upgrade.range.on", "Range Upgrade:On", "范围升级:开启");
        add("extrabotany.upgrade.range.off", "Range Upgrade:Off", "范围升级:关闭");

        //budd morph
        add("extrabotany.misc.morph", "(morph)", "（幻化）");

        //keymapping
        add("extrabotany.key.buddhist_relics_morph", "Omniscience's morph", "虚空万藏幻化");
    }

    private static void transItem() {
        // cosmetic bauble
        addItem(ExtraBotanyItems.foxEar, "Fox Ear", "樱樱耳");
        addItem(ExtraBotanyItems.foxMask, "Mask with Memory", "附有记忆的面具");
        addItem(ExtraBotanyItems.pylon, "Sims", "模拟人生");
        addItem(ExtraBotanyItems.blackGrasses, "Black Glasses", "黑框眼镜");
        addItem(ExtraBotanyItems.thugLife, "THUG LIFE", "THUG LIFE");
        addItem(ExtraBotanyItems.redScarf, "Red Scarf", "红领巾");
        addItem(ExtraBotanyItems.mask, "Mask", "石鬼面");
        addItem(ExtraBotanyItems.superCrown, "Super Crown", "超级王冠");

        //bauble
        addItem(ExtraBotanyItems.aeroStone, "Aero Stone", "风之符石");
        addItem(ExtraBotanyItems.aquaStone, "Aqua stone", "水之符石");
        addItem(ExtraBotanyItems.earthStone, "Earth Stone", "地之符石");
        addItem(ExtraBotanyItems.ignisStone, "Ignis Stone", "火之符石");
        addItem(ExtraBotanyItems.theCommunity, "The Community", "四元归一石");
        addItem(ExtraBotanyItems.peaceAmulet, "Peace Amulet", "和平友好之证");
        addItem(ExtraBotanyItems.powerGrove, "Power Glove", "强力手套");
        addItem(ExtraBotanyItems.frostStar, "Frost Star", "霜冻之星");
        addItem(ExtraBotanyItems.deathRing, "Death Ring", "诅咒指环");
        addItem(ExtraBotanyItems.manaDriveRing, "Ring of Mana Drive", "驭魔之戒");
        addItem(ExtraBotanyItems.natureOrb, "Nature Orb", "自然蕴息宝珠");
        add("extrabotany.nature_orb", "Natural Breath: %s / %s", "自然之息: %s / %s");
        add("extrabotany.nature_orb_effect1", "Blessing of Mana", "魔力的加护");
        add("extrabotany.nature_orb_effect2", "Blessing of Regeneration", "再生的加护");
        add("extrabotany.nature_orb_effect3", "Blessing of Senro", "森罗的加护");
        addItem(ExtraBotanyItems.jingweiFeather, "Feather of Jingwei", "精卫之羽");
        addItem(ExtraBotanyItems.potatoChips, "Potato Chips", "薯片");
        addItem(ExtraBotanyItems.sunRing, "Ring of Sacred Sun", "圣阳尊戒");
        addItem(ExtraBotanyItems.moonPendant, "Heart of Corrupted Moon", "蚀月之心");
        addItem(ExtraBotanyItems.sagesManaRing, "Sage's Ring of Mana", "贤者魔力指环");
//        addItem(ExtraBotanyItems.CORE_GOD, "Core of God", "律者核心");
        add("extrabotany.wings0", "Wings: Herrscher", "翅膀：律者");
        add("extrabotany.wings1", "Wings: Flandre", "翅膀：芙兰朵露");
        add("extrabotany.wings2", "Wings: Jim", "翅膀：吉米");
        add("extrabotany.wings3", "Wings: Steampunk", "翅膀：蒸汽朋克");

        // food
        addItem(ExtraBotanyItems.spiritFuel, "Spirit Fuel", "精神燃料");
        addItem(ExtraBotanyItems.nightmareFuel, "Nightmare Fuel", "梦魇燃料");
        addItem(ExtraBotanyItems.gildedMashedPotato, "Gilded Mashed Potato", "镀金土豆泥");
        addItem(ExtraBotanyItems.friedChicken, "Fried Chicken", "香香鸡");
        addItem(ExtraBotanyItems.manaDrink, "Mana Drink", "魔力鸡尾酒");

        //tool
        addItem(ExtraBotanyItems.manasteelHammer, "Manasteel Hammer", "魔力钢锤");
        addItem(ExtraBotanyItems.elementiumHammer, "Elementium Hammer", "源质钢锤");
        addItem(ExtraBotanyItems.terrasteelHammer, "Terrasteel Hammer", "泰拉钢锤");
        addItem(ExtraBotanyItems.ultimateHammer, "Ultimate Hammer", "镀金究极锤");
        addItem(ExtraBotanyItems.manaReader, "Mana Reader", "魔力读取器");
        addItem(ExtraBotanyItems.walkingCane, "Walking Cane", "步行者手杖");
        addItem(ExtraBotanyItems.rodOfDiscord, "Rod of Discord", "不谐传送杖");
        addItem(ExtraBotanyItems.silverBullet, "Silver Bullet", "银翼射手");
        addItem(ExtraBotanyItems.camera, "Shameimaru's Camera", "文文的相机");

        //weapon
        addItem(ExtraBotanyItems.shadowKatana, "Shadow Catana", "影刃");
        addItem(ExtraBotanyItems.flamescionWeapon, "Key of Flamescion", "焢煌之境:劫炎永燎");
        addItem(ExtraBotanyItems.influxWaver, "Influx Waver", "波涌之刃");
        addItem(ExtraBotanyItems.starWrath, "Star Wrath", "狂星之怒");
        addItem(ExtraBotanyItems.trueShadowKatana, "True Shadow Catana", "真影刃");
        addItem(ExtraBotanyItems.trueTerraBlade, "True Terra Blade", "真泰拉之刃");
        addItem(ExtraBotanyItems.excaliber, "Excaliber", "王者圣剑");
        addItem(ExtraBotanyItems.firstFractal, "First Fractal", "最初分型");
        addItem(ExtraBotanyItems.fallnaught, "Failnaught", "百中弓");

        //armor
        add("extrabotany.armorset.miku.name", "Starry Idol", "星空歌姬");
        addItem(ExtraBotanyItems.mikuHelm, "Starry Idol Headgear", "星空歌姬头饰");
        addItem(ExtraBotanyItems.mikuChest, "Starry Idol Suit", "星空歌姬服");
        addItem(ExtraBotanyItems.mikuLegs, "Starry Idol Skirt", "星空歌姬裙甲");
        addItem(ExtraBotanyItems.mikuBoots, "Starry Idol Boots", "星空歌姬鞋子");
        add("extrabotany.armorset.mana.desc", "Super Mana Affinity.", "超强魔力亲和");

        add("extrabotany.armorset.goblins_layer.name", "Goblin Slayer", "哥布林杀手");
        addItem(ExtraBotanyItems.goblinsLayerHelm, "Goblin Slayer Helmet", "哥布林杀手头盔");
        addItem(ExtraBotanyItems.goblinsLayerChest, "Goblin Slayer Chestplate", "哥布林杀手胸甲");
        addItem(ExtraBotanyItems.goblinsLayerLegs, "Goblin Slayer Leggings", "哥布林杀手护腿");
        addItem(ExtraBotanyItems.goblinsLayerBoots, "Goblin Slayer Boots", "哥布林杀手靴子");
        add("extrabotany.armorset.goblins_layer.desc0", "Praise the Sun.", "我不拯救世界，只管杀哥布林。");
        add("extrabotany.armorset.goblins_layer.desc1", "May the sun enlighten you.", "想象力也是武器，没有想象力的人会先死。");

        add("extrabotany.armorset.shadow_warrior.name", "Shadow Warrior", "暗影武士");
        addItem(ExtraBotanyItems.shadowWarriorHelm, "Shadow Warrior Helmet", "暗影武士头盔");
        addItem(ExtraBotanyItems.shadowWarriorChest, "Shadow Warrior Chestplate", "暗影武士胸甲");
        addItem(ExtraBotanyItems.shadowWarriorLegs, "Shadow Warrior Leggings", "暗影武士护腿");
        addItem(ExtraBotanyItems.shadowWarriorBoots, "Shadow Warrior Boots", "暗影武士靴子");
        add("extrabotany.armorset.shadow_warrior.desc0", "This night is so frightful and boundless. That my eyes come down with gloomy darkness.", "黑夜给了我黑色的眼睛，");
        add("extrabotany.armorset.shadow_warrior.desc1", "But just by them both. I am seeking my rosiness.", "我却用它寻找光明。");

        add("extrabotany.armorset.shooting_guardian.name", "Shooting Guardian", "银翼护卫");
        addItem(ExtraBotanyItems.shootingGuardianHelm, "Shooting Guardian Helmet", "银翼护卫头盔");
        addItem(ExtraBotanyItems.shootingGuardianChest, "Shooting Guardian Chestplate", "银翼护卫胸甲");
        addItem(ExtraBotanyItems.shootingGuardianLegs, "Shooting Guardian Leggings", "银翼护卫护腿");
        addItem(ExtraBotanyItems.shootingGuardianBoots, "Shooting Guardian Boots", "银翼护卫靴子");
        add("extrabotany.armorset.shooting_guardian.desc0", "Faster Bow Drawing.", "快速拉弓");
        add("extrabotany.armorset.shooting_guardian.desc1", "Grant Armor-Piercing and Life Stealing.", "攻击附带穿甲和吸血");
        add("extrabotany.armorset.shooting_guardian.desc2", "Faster Speed.", "移动速度增加");
        add("extrabotany.armorset.shooting_guardian.desc3", "Greatly decrease natural life regeneration.", "生命恢复大幅减弱");

        add("extrabotany.armorset.maid.name", "Pleiades Combat Maid", "昴星团战斗女仆");
        addItem(ExtraBotanyItems.maidHelm, "Pleiades Combat Maid Headgear", "昴星团战斗女仆头饰");
        addItem(ExtraBotanyItems.maidChest, "Pleiades Combat Maid Suit", "昴星团战斗女仆服");
        addItem(ExtraBotanyItems.maidLegs, "Pleiades Combat Maid Skirt", "昴星团战斗女仆裙甲");
        addItem(ExtraBotanyItems.maidBoots, "Pleiades Combat Maid Boots", "昴星团战斗女仆鞋子");
        add("extrabotany.armorset.maid.desc0", "Super Empty-handed Power.", "空手怪力");
        add("extrabotany.armorset.maid.desc1", "Greater Regeneration.", "再生增强");
        add("extrabotany.armorset.maid.desc2", "Mana Affinity.", "魔力亲和");

        //brew
        addItem(ExtraBotanyItems.cocktail, "Special-made CocktailItem of %s(%s)", "装有%s(%s)的秘制鸡尾酒");
        addItem(ExtraBotanyItems.infiniteWine, "Infinite Wine of %s(%s)", "装有%s(%s)的无限之酒");
        addItem(ExtraBotanyItems.splashGrenade, "Holy Grenade of %s", "装有%s的圣水手雷");

        //lens
        addItemLens(ExtraBotanyItems.manaLens, "Mana", "魔力");
        addItemLens(ExtraBotanyItems.potionLens, "Potion", "药水");
        addItemLens(ExtraBotanyItems.pushLens, "Push", "冲击");
        addItemLens(ExtraBotanyItems.smeltLens, "Smelt", "冶炼");
        addItemLens(ExtraBotanyItems.superConductorLens, "Super Conductor", "超导");
        addItemLens(ExtraBotanyItems.traceLens, "Mana", "追踪");

        //misc
        addItem(ExtraBotanyItems.gildedPotato, "Gilded Potato", "镀金服务器");
        addItem(ExtraBotanyItems.photonium, "Phontonium Ingot", "光子锭");
        addItem(ExtraBotanyItems.shadowium, "Shadowium Ingot", "暗影锭");
        addItem(ExtraBotanyItems.aerialite, "Aerialite Ingot", "天空锭");
        addItem(ExtraBotanyItems.orichalcos, "Orichalcos Ingot", "奥利哈刚锭");
        addItem(ExtraBotanyItems.spirit, "Spirit Fragment", "精神碎片");
        addItem(ExtraBotanyItems.emptyBottle, "Empty Mana Glass Bottle", "魔法玻璃空瓶");
        addItem(ExtraBotanyItems.heroMedal, "Medal of Heroism", "英雄勋章");
        addItem(ExtraBotanyItems.goldCloth, "Das Rheingold", "莱茵河的黄金");

        addItem(ExtraBotanyItems.universalPetal, "Universal Petal", "彩虹花瓣");
        addItem(ExtraBotanyItems.elementRune, "Rune of Element", "元灵符文");
        addItem(ExtraBotanyItems.sinRune, "Rune of Sin", "大罪符文");

        addItem(ExtraBotanyItems.theChaos, "The Chaos", "混沌物质");
        addItem(ExtraBotanyItems.theOrigin, "The Origin", "起源物质");
        addItem(ExtraBotanyItems.theEnd, "The End", "终末物质");
        addItem(ExtraBotanyItems.theUniverse, "The Universe", "宇宙之心");

        addItem(ExtraBotanyItems.challengeTicket, "Challenge Ticket", "寄给自己的邀请函");
        addItem(ExtraBotanyItems.treasureBox, "Pandora's Box", "潘多拉魔盒");
        addItem(ExtraBotanyItems.rewardBagA, "Reward Bag Eins", "奖励袋Eins");
        addItem(ExtraBotanyItems.rewardBagB, "Reward Bag Zwei", "奖励袋Zwei");
        addItem(ExtraBotanyItems.rewardBagC, "Reward Bag Drei", "奖励袋Drei");
        addItem(ExtraBotanyItems.rewardBagD, "Reward Bag Vier", "奖励袋Vier");
        addItem(ExtraBotanyItems.rewardBag943, "Reward Bag 9-3/4", "九又四分之三奖励袋");
        addItem(ExtraBotanyItems.buddhistRelics, "Origin Creation - Omniscience", "源初造物丨虚空万藏");
    }

    private static void transBlock() {
        addBlock(ExtraBotanyBlocks.photoniumBlock, "Photonium Block", "光子块");
        addBlock(ExtraBotanyBlocks.shadowiumBlock, "Shadowium Block", "暗影块");
        addBlock(ExtraBotanyBlocks.orichalcosBlock, "Orichalcos Block", "奥利哈刚块");
        addBlock(ExtraBotanyBlocks.pedestal, "Livingrock Pedestal", "活石祭坛");
        addBlock(ExtraBotanyBlocks.manaBuffer, "Mana Buffer", "魔力缓存器");
        addBlock(ExtraBotanyBlocks.quantumManaBuffer, "Quantum Mana Buffer", "量子魔力缓存器");
        addBlock(ExtraBotanyBlocks.trophy, "Trophy", "奖杯");
        addBlock(ExtraBotanyBlocks.livingrockBarrel, "Livingrock Barrel", "活石桶");
        addBlock(ExtraBotanyBlocks.dimensionCatalyst, "Dimension Catalyst", "次元催化器");
        addBlock(ExtraBotanyBlocks.powerFrame, "Power Frame", "力量框架");

        //flower 1.16
        addFlower(LibBlockNames.ANNOYING_FLOWER, "Annoying Flower", "神烦花", "Time to rest", "摸了");
        addFlower(LibBlockNames.SERENITIAN, "Serenitian", "永寂龙胆", "Torn to oblivion", "无念，断绝");
        addFlower(LibBlockNames.BELL_FLOWER, "Bell Flower", "风铃花", "Lost wind", "迷失的风");
        addFlower(LibBlockNames.EDELWEISS, "Edelweiss", "雪绒花", "Do you want to build a snowman?", "你想堆个雪人吗？");
        addFlower(LibBlockNames.GEMINI_ORCHID, "Gemini Orchid", "双子兰", "Why is a raven like a writing desk?", "为什么乌鸦像写字台？");
        addFlower(LibBlockNames.SUN_BLESS, "Sunshine Lily", "日曜百合", "May the light heal and enlighten you", "愿光芒能治愈并指引你");
        addFlower(LibBlockNames.MOON_BLESS, "Moonlight Lily", "月光百合", "May you find all you have lost", "愿你能找到所有失去的东西");
        addFlower(LibBlockNames.OMNI_VIOLET, "Omniviolet", "全知瑾", "Need not to know", "我知万物");
        addFlower(LibBlockNames.REIKAR_LILY, "Reikar Lily", "雷卡兰", "Game Crash", "游戏崩溃");
        addFlower(LibBlockNames.TINKLE_FLOWER, "Tinkle Flower", "叮当舞花", "My turn", "接下来是我的回合了");
//        addFlower(LibBlockNames.BLOODY_ENCHANTRESS, "Bloody Enchantress", "血腥妖姬", "My turn", "接下来是我的回合了");
    }

    private static void transEntity() {
        addEntity(ExtraBotanyEntities.SPLASH_GRENADE, "Splash Grenade", "圣水手雷");
        addEntity(ExtraBotanyEntities.MAGIC_ARROW, "Magic Arrow Projectile", "百中弓射弹");
        addEntity(ExtraBotanyEntities.TRUE_TERRA_BLADE, "True TerraBlade Projectile", "真泰拉之刃射弹");
        addEntity(ExtraBotanyEntities.TRUE_SHADOW_KATANA, "True ShadowKatana Projectile", "真影刃射弹");
        addEntity(ExtraBotanyEntities.INFLUX_WAVER, "Influx Waver Projectile", "波涌之刃射弹");
        addEntity(ExtraBotanyEntities.PHANTOM_SWORD, "First Fractal Projectile", "最初分型射弹");
        addEntity(LibEntityNames.EGO, "EGO", "本我");
        addEntity(LibEntityNames.EGO_MINION, "EGO's Minion", "本我的仆从");
        addEntity(LibEntityNames.EGO_LANDMINE, "EGO's Landmine", "本我的地雷");
        add("extrabotany.misc.inventoryUnfeasible",
                "You have something that isn't allowed in this boss fight. Check your inventory and tips in the lexicon again!",
                "你的背包里包含违禁物品，请再次检查你的背包！"
        );
        add("extrabotany.misc.unlegalPlayercount",
                "There are more people than there were when the boss is summoned. That's illegal!",
                "在场玩家数大于召唤时的玩家数，这是不符合规则的！"
        );
        add("extrabotany.misc.description",
                "You can not use it until you complete corresponding advancement <%s>.",
                "你无法使用该物品直到你完成进度 <%s>。"
        );
    }


    private static void transAdvancement() {
        add("advancement.extrabotany.root.title", "Welcome to the World", "欢迎来到世界");
        add("advancement.extrabotany.root.desc", "Don't have a good day, have a great day", "不要错过今天，去过好每一天");
        add("advancement.item_disabled.desc", "You can' use it until you complete corresponding advancement <%s>.", "你无法使用该物品直到你完成进度 <%s>");
        makeAdv(LibAdvancementNames.NIGHTMARE_FUEL_EAT,
                "Deep Dark Fantasy", "Deep Dark Fantasy",
                "Eat a Nightmare Fuel (Unbelievable)", "食用一个梦魇燃料(这真的能吃吗)"
        );
        makeAdv(LibAdvancementNames.POWER_FRAME_CRAFT, "Letter Song", "Letter Song", "Craft a Position Reader", "合成一个力量框架");
        makeAdv(LibAdvancementNames.ARMORSET_MIKU, "Cat's Dance", "Cat's Dance", "Equip Starry Idol Armor Set", "装备一套星空歌姬");
        makeAdv(LibAdvancementNames.TINKLE_USE, "Project Diva Desu", "Project Diva Desu", "Dance around a Tinkle Flower", "在叮当舞花旁跳舞");
        makeAdv(LibAdvancementNames.MANA_BUFFER_CRAFT, "ロストワンの号哭", "ロストワンの号哭", "Craft a Mana Buffer", "合成一个魔力缓存器");
        makeAdv(LibAdvancementNames.MANA_READER_CRAFT, "Satisfaction", "Satisfaction", "Craft a Mana Reader", "合成一个魔力读取器");
        makeAdv(LibAdvancementNames.SPIRIT_CRAFT, "PONPONPON", "PONPONPON", "Craft a Spirit Fragment", "获得精神碎片");
        makeAdv(LibAdvancementNames.ARMORSET_GOBLINS_LAYER, "Befall", "Befall", "Equip Goblin Slayer Armor Set", "装备一套哥布林杀手");
        makeAdv(LibAdvancementNames.ARMORSET_SHADOW_WARRIOR, "Crazy ∞ nighT", "Crazy ∞ nighT", "Equip Shadow Warrior Armor Set", "装备一套暗影武士");
        makeAdv(LibAdvancementNames.ARMORSET_SHOOTING_GUARDIAN, "CONNECT", "CONNECT", "Equip Shooting Guardian Armor Set", "装备一套银翼护卫");
        makeAdv(LibAdvancementNames.ARMORSET_MAID, "Drug Of Gold", "Drug Of Gold", "Equip Pleiades Combat Maid Armor Set", "装备一套昂星团战斗女仆");
        makeAdv(LibAdvancementNames.NATURE_ORB_CRAFT, "Single floor", "雨夢楼", "Craft a Nature Orb", "合成一个自然蕴息宝珠");
        makeAdv(LibAdvancementNames.EGO_DEFEAT, "KiLLER LADY", "KiLLER LADY", "Defeat Ego", "击败本我");
        makeAdv(LibAdvancementNames.SUN_RING_CRAFT, "Promise", "Promise", "Obtain Ring of Sacred Sun", "获得圣阳尊戒");
        makeAdv(LibAdvancementNames.MOON_PENDANT_CRAFT, "Crystalline", "Crystalline", "Obtain Heart of Corrupted Moon", "获得蚀月之心");
        makeAdv(LibAdvancementNames.FAILNAUGHT_CRAFT, "From Y to Y", "From Y to Y", "Obtain Failnaught", "获得百中弓");
        makeAdv(LibAdvancementNames.CAMERA_CRAFT, "lukaluka night fever", "lukaluka night fever", "Obtain Shameimaru's Camera", "获得文文的相机");
        makeAdv(LibAdvancementNames.THE_UNIVERSE_CRAFT, "Gears of Love", "Gears of Love", "Obtain The Universe", "获得宇宙之心");
        makeAdv(LibAdvancementNames.FIRST_FRACTAL_CRAFT, "Infinity +1 Sword", "Infinity +1 Sword", "Obtain First Fractal", "获得最初分型");
        makeAdv(LibAdvancementNames.CORE_GOD_CRAFT, "All Hail The Queen", "All Hail The Queen", "Obtain Core of Herrscher", "获得律者核心");
        makeAdv(LibAdvancementNames.INFINITE_WINE_CRAFT, "Shake it", "Shake it", "Obtain Infinite Wine", "获得无限之酒");
        makeAdv(LibAdvancementNames.BUDDHIST_RELICS_OBTAIN, "SPiCa", "SPiCa", "Obtain Origin Creation|Omniscience", "获得源初造物丨虚空万藏");
        makeAdv(LibAdvancementNames.EXCALIBER_CRAFT, "ReAct", "ReAct", "Obtain Excaliber", "获得王者圣剑");
        makeAdv(LibAdvancementNames.SAGES_MANA_RING_CRAFT, "COLOR", "COLOR", "Obtain Sages Mana Ring", "获得贤者魔力指环");
        makeAdv(LibAdvancementNames.SAGES_MANA_RING_FILL, "Fairytale", "Fairytale", "Fill it to WIN the game", "将一个贤者魔力指环充满以获得游戏胜利");
    }

    private static void makeAdv(String key, String titleEn, String titleZh, String descEn, String descZh) {
        add("advancement.extrabotany." + key, titleEn, titleZh);
        add("advancement.extrabotany." + key + ".desc", descEn, descZh);
    }

    /**
     * @param key the whole key,example: item.minecraft.apple
     */
    private static void add(String key, String en, String cn) {
        en_us.put(key, en);
        zh_cn.put(key, cn);
    }

    private static void addItem(@NotNull Item item, String en, String zh) {
        en_us.put(item.getDescriptionId(), en);
        zh_cn.put(item.getDescriptionId(), zh);
    }

    private static void addItemLens(@NotNull Item item, String en, String zh) {
        addItem(item, "Mana Lens: " + en, "魔力透镜:" + zh);
        add(item.getDescriptionId() + ".short", en, zh);
    }

    private static void addBlock(@NotNull Block block, String en, String zh) {
        en_us.put(block.getDescriptionId(), en);
        zh_cn.put(block.getDescriptionId(), zh);
    }

    private static void addFlower(Block flower, String en, String zh, String refEn, String refZh) {
        ResourceLocation id = exbotRL(flower.getDescriptionId());
        en_us.put(flower.getDescriptionId(), en);
        String name = "block." + id.getNamespace() + ".floating_" + id.getPath();
        String info = "block." + id.getNamespace() + ".floating_" + id.getPath() + ".reference";
        en_us.put(name, "Floating " + en);
        en_us.put(flower.getDescriptionId() + ".reference", refEn);
        en_us.put(info, refEn);
        zh_cn.put(flower.getDescriptionId(), zh);
        zh_cn.put(name, "浮空" + zh);
        zh_cn.put(flower.getDescriptionId() + ".reference", refZh);
        zh_cn.put(info, refZh);
    }

    private static void addFlower(String name, String en, String zh, String refEn, String refZh) {
        en_us.put("block.extrabotany." + name, en);
        en_us.put("block.extrabotany.floating_" + name, "Floating " + en);
        en_us.put("block.extrabotany." + name + ".reference", refEn);
        en_us.put("block.extrabotany.floating_" + name + ".reference", refEn);
        zh_cn.put("block.extrabotany." + name, zh);
        zh_cn.put("block.extrabotany.floating_" + name, "浮空" + zh);
        zh_cn.put("block.extrabotany." + name + ".reference", refZh);
        zh_cn.put("block.extrabotany.floating_" + name + ".reference", refZh);
    }

    private static void addEntity(EntityType<? extends Entity> type, String en, String zh) {
        en_us.put(type.getDescriptionId(), en);
        zh_cn.put(type.getDescriptionId(), zh);
    }

    private static void addEntity(String name, String en, String zh) {
        en_us.put("entity.extrabotany." + name, en);
        zh_cn.put("entity.extrabotany." + name, zh);
    }
}
