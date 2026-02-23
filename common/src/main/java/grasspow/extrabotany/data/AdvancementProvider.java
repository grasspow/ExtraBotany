package grasspow.extrabotany.data;

import grasspow.extrabotany.common.block.ExtraBotanyBlocks;
import grasspow.extrabotany.common.item.ExtraBotanyItems;
import grasspow.extrabotany.common.lib.LibAdvancementNames;
import net.minecraft.advancements.*;
import net.minecraft.advancements.critereon.ConsumeItemTrigger;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.component.DataComponentPredicate;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.ItemLike;
import vazkii.botania.common.item.BotaniaItems;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;
import static vazkii.botania.api.BotaniaAPI.botaniaRL;

public class AdvancementProvider {
    public static net.minecraft.data.advancements.AdvancementProvider create(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        return new net.minecraft.data.advancements.AdvancementProvider(packOutput, lookupProvider, List.of(new ExtraBotanyStoryAdvancements()));
    }

    public static class ExtraBotanyStoryAdvancements implements AdvancementSubProvider {
        @Override
        public void generate(HolderLookup.Provider lookup, Consumer<AdvancementHolder> consumer) {
            AdvancementHolder ROOT = Advancement.Builder.advancement()
                    .display(rootDisplay(ExtraBotanyItems.pylon, "advancement.extrabotany.root.title",
                            "advancement.extrabotany.root.desc", botaniaRL("textures/block/livingwood_log.png")))
                    .addCriterion("lexicon", InventoryChangeTrigger.TriggerInstance.hasItems(BotaniaItems.lexicon))
                    .save(consumer, mainId(LibAdvancementNames.ROOT));

            AdvancementHolder nightmare_fuel_eat = Advancement.Builder.advancement()
                    .display(simple(ExtraBotanyItems.nightmareFuel, LibAdvancementNames.NIGHTMARE_FUEL_EAT, AdvancementType.TASK))
                    .parent(ROOT)
                    .addCriterion(LibAdvancementNames.NIGHTMARE_FUEL_EAT, eatItem(ExtraBotanyItems.nightmareFuel))
                    .save(consumer, mainId(LibAdvancementNames.NIGHTMARE_FUEL_EAT));

            Advancement.Builder.advancement()
                    .display(simple(ExtraBotanyBlocks.powerFrame, LibAdvancementNames.POWER_FRAME_CRAFT, AdvancementType.TASK))
                    .parent(nightmare_fuel_eat)
                    .addCriterion(LibAdvancementNames.POWER_FRAME_CRAFT, onPickup(ExtraBotanyBlocks.powerFrame))
                    .save(consumer, mainId(LibAdvancementNames.POWER_FRAME_CRAFT));

            Advancement.Builder.advancement()
                    .display(simple(ExtraBotanyItems.mikuHelm, LibAdvancementNames.ARMORSET_MIKU, AdvancementType.TASK))
                    .parent(nightmare_fuel_eat)
                    .addCriterion(LibAdvancementNames.ARMORSET_MIKU, InventoryChangeTrigger.TriggerInstance.hasItems(ExtraBotanyItems.mikuHelm, ExtraBotanyItems.mikuChest, ExtraBotanyItems.mikuLegs, ExtraBotanyItems.mikuBoots))
                    .save(consumer, mainId(LibAdvancementNames.ARMORSET_MIKU));

//            Advancement.Builder.advancement()
//                    .display(simple(ExtraBotanyBlocks.TINKLE_FLOWER.asItem(), LibAdvancementNames.TINKLE_USE, AdvancementType.TASK))
//                    .parent(nightmare_fuel_eat)
//                    .addCriterion(LibAdvancementNames.TINKLE_USE, TinkleUseTrigger.Instance.danceNearTinkle())
//                    .save(consumer, mainId(LibAdvancementNames.TINKLE_USE));

            Advancement.Builder.advancement()
                    .display(simple(ExtraBotanyBlocks.manaBuffer.asItem(), LibAdvancementNames.MANA_BUFFER_CRAFT, AdvancementType.TASK))
                    .parent(nightmare_fuel_eat)
                    .addCriterion(LibAdvancementNames.MANA_BUFFER_CRAFT, InventoryChangeTrigger.TriggerInstance.hasItems(ExtraBotanyBlocks.manaBuffer))
                    .save(consumer, mainId(LibAdvancementNames.MANA_BUFFER_CRAFT));

            Advancement.Builder.advancement()
                    .display(simple(ExtraBotanyItems.manaReader, LibAdvancementNames.MANA_READER_CRAFT, AdvancementType.TASK))
                    .parent(nightmare_fuel_eat)
                    .addCriterion(LibAdvancementNames.MANA_READER_CRAFT, InventoryChangeTrigger.TriggerInstance.hasItems(ExtraBotanyItems.manaReader))
                    .save(consumer, mainId(LibAdvancementNames.MANA_READER_CRAFT));

            AdvancementHolder SPIRIT_CRAFT = Advancement.Builder.advancement()
                    .display(simple(ExtraBotanyItems.spirit, LibAdvancementNames.SPIRIT_CRAFT, AdvancementType.TASK))
                    .parent(nightmare_fuel_eat)
                    .addCriterion(LibAdvancementNames.SPIRIT_CRAFT, onPickup(ExtraBotanyItems.spirit))
                    .save(consumer, mainId(LibAdvancementNames.SPIRIT_CRAFT));

            Advancement.Builder.advancement()
                    .display(simple(ExtraBotanyItems.goblinsLayerHelm, LibAdvancementNames.ARMORSET_GOBLINS_LAYER, AdvancementType.TASK))
                    .parent(SPIRIT_CRAFT)
                    .addCriterion(LibAdvancementNames.ARMORSET_GOBLINS_LAYER, InventoryChangeTrigger.TriggerInstance.hasItems(ExtraBotanyItems.goblinsLayerHelm, ExtraBotanyItems.goblinsLayerChest, ExtraBotanyItems.goblinsLayerLegs, ExtraBotanyItems.goblinsLayerBoots))
                    .save(consumer, mainId(LibAdvancementNames.ARMORSET_GOBLINS_LAYER));
            Advancement.Builder.advancement()
                    .display(simple(ExtraBotanyItems.shadowWarriorHelm, LibAdvancementNames.ARMORSET_SHADOW_WARRIOR, AdvancementType.TASK))
                    .parent(SPIRIT_CRAFT)
                    .addCriterion(LibAdvancementNames.ARMORSET_SHADOW_WARRIOR, InventoryChangeTrigger.TriggerInstance.hasItems(ExtraBotanyItems.shadowWarriorHelm, ExtraBotanyItems.shadowWarriorChest, ExtraBotanyItems.shadowWarriorLegs, ExtraBotanyItems.shadowWarriorBoots))
                    .save(consumer, mainId(LibAdvancementNames.ARMORSET_SHADOW_WARRIOR));
            Advancement.Builder.advancement()
                    .display(simple(ExtraBotanyItems.shootingGuardianHelm, LibAdvancementNames.ARMORSET_SHOOTING_GUARDIAN, AdvancementType.TASK))
                    .parent(SPIRIT_CRAFT)
                    .addCriterion(LibAdvancementNames.ARMORSET_SHOOTING_GUARDIAN, InventoryChangeTrigger.TriggerInstance.hasItems(ExtraBotanyItems.shootingGuardianHelm, ExtraBotanyItems.shootingGuardianChest, ExtraBotanyItems.shootingGuardianLegs, ExtraBotanyItems.shootingGuardianBoots))
                    .save(consumer, mainId(LibAdvancementNames.ARMORSET_SHOOTING_GUARDIAN));
            Advancement.Builder.advancement()
                    .display(simple(ExtraBotanyItems.maidHelm, LibAdvancementNames.ARMORSET_MAID, AdvancementType.TASK))
                    .parent(SPIRIT_CRAFT)
                    .addCriterion(LibAdvancementNames.ARMORSET_MAID, InventoryChangeTrigger.TriggerInstance.hasItems(ExtraBotanyItems.maidHelm, ExtraBotanyItems.maidChest, ExtraBotanyItems.maidLegs, ExtraBotanyItems.maidBoots))
                    .save(consumer, mainId(LibAdvancementNames.ARMORSET_MAID));

            AdvancementHolder NATURE_ORB_CRAFT = Advancement.Builder.advancement()
                    .display(simple(ExtraBotanyItems.natureOrb, LibAdvancementNames.NATURE_ORB_CRAFT, AdvancementType.TASK))
                    .parent(nightmare_fuel_eat)
                    .addCriterion(LibAdvancementNames.NATURE_ORB_CRAFT, onPickup(ExtraBotanyItems.natureOrb))
                    .save(consumer, mainId(LibAdvancementNames.NATURE_ORB_CRAFT));

//            Advancement EGO_DEFEAT = Advancement.Builder.advancement()
//                    .display(simple(ExtraBotanyItems.heroMedal, LibAdvancementNames.EGO_DEFEAT, AdvancementType.TASK))
//                    .parent(NATURE_ORB_CRAFT)
//                    .addCriterion(LibAdvancementNames.EGO_DEFEAT, KilledTrigger.TriggerInstance
//                            .playerKilledEntity(EntityPredicate.Builder.entity().of(ExtraBotanyEntities.EGO.get())))
//                    .save(consumer, mainId(LibAdvancementNames.EGO_DEFEAT));

//            Advancement.Builder.advancement()
//                    .display(simple(ExtraBotanyItems.sunRing, LibAdvancementNames.SUN_RING_CRAFT, AdvancementType.CHALLENGE))
//                    .parent(EGO_DEFEAT)
//                    .addCriterion(LibAdvancementNames.SUN_RING_CRAFT, InventoryChangeTrigger.TriggerInstance.hasItems(ExtraBotanyItems.SUN_RING.get()))
//                    .save(consumer, mainId(LibAdvancementNames.SUN_RING_CRAFT));
//
//            Advancement.Builder.advancement()
//                    .display(simple(ExtraBotanyItems.moonPendant, LibAdvancementNames.MOON_PENDANT_CRAFT, AdvancementType.CHALLENGE))
//                    .parent(EGO_DEFEAT)
//                    .addCriterion(LibAdvancementNames.MOON_PENDANT_CRAFT, InventoryChangeTrigger.TriggerInstance.hasItems(ExtraBotanyItems.MOON_PENDANT.get()))
//                    .save(consumer, mainId(LibAdvancementNames.MOON_PENDANT_CRAFT));
//
//            Advancement.Builder.advancement()
//                    .display(simple(ExtraBotanyItems.fallnaught, LibAdvancementNames.FAILNAUGHT_CRAFT, AdvancementType.CHALLENGE))
//                    .parent(EGO_DEFEAT)
//                    .addCriterion(LibAdvancementNames.FAILNAUGHT_CRAFT, InventoryChangeTrigger.TriggerInstance.hasItems(ExtraBotanyItems.FAILNAUGHT.get()))
//                    .save(consumer, mainId(LibAdvancementNames.FAILNAUGHT_CRAFT));
//
//            Advancement.Builder.advancement()
//                    .display(simple(ExtraBotanyItems.camera, LibAdvancementNames.CAMERA_CRAFT, AdvancementType.CHALLENGE))
//                    .parent(EGO_DEFEAT)
//                    .addCriterion(LibAdvancementNames.CAMERA_CRAFT, InventoryChangeTrigger.TriggerInstance.hasItems(ExtraBotanyItems.CAMERA.get()))
//                    .save(consumer, mainId(LibAdvancementNames.CAMERA_CRAFT));
//
//            Advancement THE_UNIVERSE_CRAFT = Advancement.Builder.advancement()
//                    .display(simple(ExtraBotanyItems.theUniverse, LibAdvancementNames.THE_UNIVERSE_CRAFT, AdvancementType.CHALLENGE))
//                    .parent(EGO_DEFEAT)
//                    .addCriterion(LibAdvancementNames.THE_UNIVERSE_CRAFT, InventoryChangeTrigger.TriggerInstance.hasItems(ExtraBotanyItems.THE_UNIVERSE.get()))
//                    .save(consumer, mainId(LibAdvancementNames.THE_UNIVERSE_CRAFT));
//
//            Advancement.Builder.advancement()
//                    .display(simple(ExtraBotanyItems.firstFractal, LibAdvancementNames.FIRST_FRACTAL_CRAFT, AdvancementType.CHALLENGE))
//                    .parent(THE_UNIVERSE_CRAFT)
//                    .addCriterion(LibAdvancementNames.FIRST_FRACTAL_CRAFT, InventoryChangeTrigger.TriggerInstance.hasItems(ExtraBotanyItems.FIRST_FRACTAL.get()))
//                    .save(consumer, mainId(LibAdvancementNames.FIRST_FRACTAL_CRAFT));
//
//            Advancement.Builder.advancement()
//                    .display(simple(ExtraBotanyItems.CORE_GOD, LibAdvancementNames.CORE_GOD_CRAFT, AdvancementType.CHALLENGE))
//                    .parent(EGO_DEFEAT)
//                    .addCriterion(LibAdvancementNames.CORE_GOD_CRAFT, InventoryChangeTrigger.TriggerInstance.hasItems(ExtraBotanyItems.CORE_GOD.get()))
//                    .save(consumer, mainId(LibAdvancementNames.CORE_GOD_CRAFT));
//
//            Advancement.Builder.advancement()
//                    .display(simple(ExtraBotanyItems.infiniteWine, LibAdvancementNames.INFINITE_WINE_CRAFT, AdvancementType.CHALLENGE))
//                    .parent(EGO_DEFEAT)
//                    .addCriterion(LibAdvancementNames.INFINITE_WINE_CRAFT, InventoryChangeTrigger.TriggerInstance.hasItems(ExtraBotanyItems.INFINITE_WINE.get()))
//                    .save(consumer, mainId(LibAdvancementNames.INFINITE_WINE_CRAFT));
//
//            Advancement.Builder.advancement()
//                    .display(simple(ExtraBotanyItems.buddhistRelics, LibAdvancementNames.BUDDHIST_RELICS_OBTAIN, AdvancementType.CHALLENGE))
//                    .parent(EGO_DEFEAT)
//                    .addCriterion(LibAdvancementNames.BUDDHIST_RELICS_OBTAIN, InventoryChangeTrigger.TriggerInstance.hasItems(ExtraBotanyItems.BUDDHIST_RELICS.get()))
//                    .save(consumer, mainId(LibAdvancementNames.BUDDHIST_RELICS_OBTAIN));
//
//            Advancement.Builder.advancement()
//                    .display(simple(ExtraBotanyItems.excaliber, LibAdvancementNames.EXCALIBER_CRAFT, AdvancementType.CHALLENGE))
//                    .parent(EGO_DEFEAT)
//                    .addCriterion(LibAdvancementNames.EXCALIBER_CRAFT, InventoryChangeTrigger.TriggerInstance.hasItems(ExtraBotanyItems.EXCALIBER.get()))
//                    .save(consumer, mainId(LibAdvancementNames.EXCALIBER_CRAFT));
//
//            Advancement SAGES_MANA_RING_CRAFT = Advancement.Builder.advancement()
//                    .display(simple(ExtraBotanyItems.sagesManaRing, LibAdvancementNames.SAGES_MANA_RING_CRAFT, AdvancementType.CHALLENGE))
//                    .parent(EGO_DEFEAT)
//                    .addCriterion(LibAdvancementNames.SAGES_MANA_RING_CRAFT, InventoryChangeTrigger.TriggerInstance.hasItems(ExtraBotanyItems.SAGES_MANA_RING.get()))
//                    .save(consumer, mainId(LibAdvancementNames.SAGES_MANA_RING_CRAFT));
//
//            DataComponentPredicate tag = DataComponentPredicate.builder()
//                    .expect(BotaniaDataComponents.MANA, Integer.MAX_VALUE - 1).build();
//
//            Advancement.Builder.advancement()
//                    .display(hidden(ExtraBotanyItems.sagesManaRing, LibAdvancementNames.SAGES_MANA_RING_FILL, AdvancementType.CHALLENGE))
//                    .parent(SAGES_MANA_RING_CRAFT)
//                    .addCriterion(LibAdvancementNames.SAGES_MANA_RING_FILL, hasNbtItem(ExtraBotanyItems.sagesManaRing, tag))
//                    .save(consumer, mainId(LibAdvancementNames.SAGES_MANA_RING_FILL));
        }
    }

    protected static Criterion<ConsumeItemTrigger.TriggerInstance> eatItem(Item items) {
        return ConsumeItemTrigger.TriggerInstance.usedItem(items);
    }

    protected static Criterion<InventoryChangeTrigger.TriggerInstance> onPickup(TagKey<Item> tag) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(
                ItemPredicate.Builder.item().of(tag).build());
    }

    protected static Criterion<InventoryChangeTrigger.TriggerInstance> onPickup(ItemLike... items) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(matchItems(items));
    }

    protected static Criterion<InventoryChangeTrigger.TriggerInstance> hasNbtItem(ItemLike item, DataComponentPredicate nbt) {
        ItemPredicate itemPredicate = ItemPredicate.Builder.item().of(item).hasComponents(nbt).build();
        return InventoryChangeTrigger.TriggerInstance.hasItems(itemPredicate);
    }

    protected static ItemPredicate matchItems(ItemLike... items) {
        return ItemPredicate.Builder.item().of(items).build();
    }

    protected static DisplayInfo simple(ItemLike icon, String name, AdvancementType AdvancementType) {
        String expandedName = "advancement.extrabotany." + name;
        return new DisplayInfo(new ItemStack(icon.asItem()),
                Component.translatable(expandedName),
                Component.translatable(expandedName + ".desc"),
                Optional.empty(), AdvancementType, true, true, false);
    }

    protected static DisplayInfo hidden(ItemLike icon, String name, AdvancementType AdvancementType) {
        String expandedName = "advancement.extrabotany." + name;
        return new DisplayInfo(new ItemStack(icon.asItem()),
                Component.translatable(expandedName),
                Component.translatable(expandedName + ".desc"),
                Optional.empty(), AdvancementType, true, true, true);
    }

    protected static DisplayInfo rootDisplay(ItemLike icon, String titleKey, String descKey, ResourceLocation background) {
        return new DisplayInfo(new ItemStack(icon.asItem()),
                Component.translatable(titleKey),
                Component.translatable(descKey),
                Optional.of(background), AdvancementType.TASK, false, false, false);
    }

    private static String mainId(String name) {
        return exbotRL("main/" + name).toString();
    }
}
