package grasspow.extrabotany.common.lib;

import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;


public final class ExtraBotanyTags {
    public static class Items {
        public static final TagKey<Item> HAMMER = tag("hammer");
        public static final TagKey<Item> PEDESTAL_DENY = tag("pedestal_deny");
        public static final TagKey<Item> INGOTS_PHOTONIUM = tag(LibItemNames.PHOTONIUM + "_ingots");
        public static final TagKey<Item> INGOTS_SHADOWIUM = tag(LibItemNames.SHADOWIUM + "_ingots");
        public static final TagKey<Item> INGOTS_AERIALITE = tag(LibItemNames.AERIALITE + "_ingots");
        public static final TagKey<Item> INGOTS_ORICHALCOS = tag(LibItemNames.ORICHALCOS + "_ingots");

        //runes
        public static final TagKey<Item> RUNES_WATER = tag("runes/water");
        public static final TagKey<Item> RUNES_FIRE = tag("runes/fire");
        public static final TagKey<Item> RUNES_EARTH = tag("runes/earth");
        public static final TagKey<Item> RUNES_AIR = tag("runes/air");
        public static final TagKey<Item> RUNES_SPRING = tag("runes/spring");
        public static final TagKey<Item> RUNES_SUMMER = tag("runes/summer");
        public static final TagKey<Item> RUNES_AUTUMN = tag("runes/autumn");
        public static final TagKey<Item> RUNES_WINTER = tag("runes/winter");
        public static final TagKey<Item> RUNES_MANA = tag("runes/mana");
        public static final TagKey<Item> RUNES_LUST = tag("runes/lust");
        public static final TagKey<Item> RUNES_GLUTTONY = tag("runes/gluttony");
        public static final TagKey<Item> RUNES_GREED = tag("runes/greed");
        public static final TagKey<Item> RUNES_SLOTH = tag("runes/sloth");
        public static final TagKey<Item> RUNES_WRATH = tag("runes/wrath");
        public static final TagKey<Item> RUNES_ENVY = tag("runes/envy");
        public static final TagKey<Item> RUNES_PRIDE = tag("runes/pride");

        private static TagKey<Item> tag(String name) {
            return TagKey.create(Registries.ITEM, exbotRL(name));
        }
    }

    public static class Blocks {
        public static final TagKey<Block> BLOCKS_SHADOWIUM = tag(LibBlockNames.SHADOWIUM_BLOCK + "s");
        public static final TagKey<Block> BLOCKS_PHOTONIUM = tag(LibBlockNames.PHOTONIUM_BLOCK + "s");
        public static final TagKey<Block> BLOCKS_ORICHALCOS = tag(LibBlockNames.ORICHALCOS_BLOCK + "s");

        private static TagKey<Block> tag(String name) {
            return TagKey.create(Registries.BLOCK, exbotRL(name));
        }
    }
}
