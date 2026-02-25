package grasspow.extrabotany.data.tag;

import grasspow.extrabotany.common.lib.ExtraBotanyTags;
import grasspow.extrabotany.common.lib.LibMisc;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import vazkii.botania.common.block.flower.FloatingSpecialFlowerBlock;
import vazkii.botania.common.lib.BotaniaTags;

import java.util.Comparator;
import java.util.concurrent.CompletableFuture;
import java.util.function.Predicate;
import java.util.stream.Stream;

import static grasspow.extrabotany.common.block.ExtraBotanyBlocks.*;


public class BlockTagProvider extends IntrinsicHolderTagsProvider<Block> {
    public static final Predicate<Block> EXBOT_BLOCK = b -> LibMisc.MOD_ID.equals(BuiltInRegistries.BLOCK.getKey(b).getNamespace());

    public BlockTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, Registries.BLOCK, lookupProvider, (block) -> block.builtInRegistryHolder().key());
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        registerVanillaTag();
        registerBotaniaTag();
        tag(ExtraBotanyTags.Blocks.BLOCKS_PHOTONIUM).add(photoniumBlock);
        tag(ExtraBotanyTags.Blocks.BLOCKS_SHADOWIUM).add(shadowiumBlock);
        tag(ExtraBotanyTags.Blocks.BLOCKS_ORICHALCOS).add(orichalcosBlock);
    }

    private void registerVanillaTag() {
        Stream.of(
                photoniumBlock, shadowiumBlock, orichalcosBlock
        ).forEach(tag(BlockTags.BEACON_BASE_BLOCKS)::add);
        Stream.of(
                photoniumBlock, shadowiumBlock, orichalcosBlock,
                pedestal, manaBuffer, quantumManaBuffer, livingrockBarrel,
                dimensionCatalyst, powerFrame
        ).forEach(tag(BlockTags.MINEABLE_WITH_PICKAXE)::add);
    }

    private void registerBotaniaTag() {
        Stream.of(
                annoyingFlower
        ).forEach(tag(BotaniaTags.Blocks.FUNCTIONAL_SPECIAL_FLOWERS)::add);
        Stream.of(
                serenitian
        ).forEach(tag(BotaniaTags.Blocks.MISC_SPECIAL_FLOWERS)::add);
        Stream.of(
                bellFlower, edelweiss, geminiOrchid, sunBless,
                moonBless, omniViolet, reikarLily, tinkleFlower
        ).forEach(tag(BotaniaTags.Blocks.GENERATING_SPECIAL_FLOWERS)::add);
        tag(BotaniaTags.Blocks.SPECIAL_FLOATING_FLOWERS).add(BuiltInRegistries.BLOCK.stream().filter(EXBOT_BLOCK)
                .filter(b -> b instanceof FloatingSpecialFlowerBlock)
                .sorted(Comparator.comparing(BuiltInRegistries.BLOCK::getKey))
                .toArray(Block[]::new)
        );
        tag(BlockTags.FLOWER_POTS)
                .add(
                        annoyingFlowerPotted, serenitianPotted, bellFlowerPotted, edelweissPotted,
                        geminiOrchidPotted,
                        sunBlessPotted, moonBlessPotted, omniVioletPotted, reikarLilyPotted, tinkleFlowerPotted
                );
    }
}
