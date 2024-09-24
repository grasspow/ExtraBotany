package io.grasspow.extrabotany.data;

import io.grasspow.extrabotany.common.libs.LibMisc;
import io.grasspow.extrabotany.common.libs.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static io.grasspow.extrabotany.common.registry.ModBlocks.*;


public class BlockTagProvider extends IntrinsicHolderTagsProvider<Block> {
    public BlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> provider,
                            ExistingFileHelper existingFileHelper) {
        super(output, Registries.BLOCK, provider, (block) -> block.builtInRegistryHolder().key(), LibMisc.MOD_ID, existingFileHelper);
    }

    @Override
    public String getName() {
        return "ExtraBotany block tags";
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        registerVanillaTag();
        tag(ModTags.Blocks.PHOTONIUM_BLOCK).add(PHOTONIUM_BLOCK.get());
        tag(ModTags.Blocks.SHADOWIUM_BLOCK).add(SHADOWIUM_BLOCK.get());
    }

    private void registerVanillaTag() {
        Stream.of(
                PHOTONIUM_BLOCK, SHADOWIUM_BLOCK
        ).map(RegistryObject::get).forEach(tag(BlockTags.BEACON_BASE_BLOCKS)::add);
        Stream.of(
                PHOTONIUM_BLOCK, SHADOWIUM_BLOCK, PEDESTAL
        ).map(RegistryObject::get).forEach(tag(BlockTags.MINEABLE_WITH_PICKAXE)::add);
    }
}