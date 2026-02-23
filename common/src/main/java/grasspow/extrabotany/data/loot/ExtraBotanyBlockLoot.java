package grasspow.extrabotany.data.loot;

import grasspow.extrabotany.api.ExtraBotanyAPI;
import grasspow.extrabotany.common.block.ExtraBotanyBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ExtraBotanyBlockLoot extends BlockLootSubProvider {
    private final Set<Block> specialHandling = new HashSet<>();

    private static final Set<Item> EXPLOSION_RESISTANT = Stream
            .of(
                    ExtraBotanyBlocks.trophy
            )
            .map(Block::asItem)
            .collect(Collectors.toSet());

    public ExtraBotanyBlockLoot(HolderLookup.Provider registries) {
        super(EXPLOSION_RESISTANT, FeatureFlags.REGISTRY.allFlags(), registries);
    }

    @Override
    public void generate() {

        Map<Block, LootTable.Builder> specialCases = new HashMap<>();

        // Flower component saving
//        saveSpecialFlowerState(specialCases, BotaniaBlocks.gourmaryllis, BotaniaBlocks.gourmaryllisFloating,
//                BotaniaDataComponents.STREAK_LENGTH, BotaniaDataComponents.LAST_REPEATS, BotaniaDataComponents.LAST_FOODS);
//        saveSpecialFlowerState(specialCases, BotaniaBlocks.hydroangeas, BotaniaBlocks.hydroangeasFloating,
//                BotaniaDataComponents.COOLDOWN, BotaniaDataComponents.DECAY_TICKS);
//        saveSpecialFlowerState(specialCases, BotaniaBlocks.munchdew, BotaniaBlocks.munchdewFloating,
//                BotaniaDataComponents.COOLDOWN, BotaniaDataComponents.ACTIVE);
//        saveSpecialFlowerState(specialCases, BotaniaBlocks.rafflowsia, BotaniaBlocks.rafflowsiaFloating,
//                BotaniaDataComponents.LAST_REPEATS, BotaniaDataComponents.LAST_FLOWERS);
//        saveSpecialFlowerState(specialCases, BotaniaBlocks.spectrolus, BotaniaBlocks.spectrolusFloating,
//                BotaniaDataComponents.NEXT_COLOR);
//        saveSpecialFlowerState(specialCases, BotaniaBlocks.thermalily, BotaniaBlocks.thermalilyFloating,
//                BotaniaDataComponents.COOLDOWN);

        for (Block block : BuiltInRegistries.BLOCK) {
            ResourceLocation blockId = BuiltInRegistries.BLOCK.getKey(block);
            if (!ExtraBotanyAPI.MODID.equals(blockId.getNamespace()) || specialHandling.contains(block)) {
                continue;
            }
            if (specialCases.containsKey(block)) {
                add(block, specialCases.get(block));
            } else if (block instanceof FlowerPotBlock flowerPot) {
                dropPottedContents(flowerPot);
            } else {
                dropSelf(block);
            }
        }
    }

    @Override
    public void otherWhenSilkTouch(Block block, Block other) {
        super.otherWhenSilkTouch(block, other);
        specialHandling.add(block);
    }

    public Map<? extends ResourceKey<LootTable>, ? extends LootTable.Builder> getMap() {
        return Collections.unmodifiableMap(this.map);
    }
}
