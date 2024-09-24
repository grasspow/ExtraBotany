package io.grasspow.extrabotany.data;

import io.grasspow.extrabotany.common.libs.LibMisc;
import io.grasspow.extrabotany.common.registry.ModBlocks;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

public class BlockStateProvider extends net.minecraftforge.client.model.generators.BlockStateProvider {
    public BlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, LibMisc.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        List.of(
                ModBlocks.PHOTONIUM_BLOCK,
                ModBlocks.SHADOWIUM_BLOCK
        ).forEach(this::normal);
        List.of(
                ModBlocks.PEDESTAL
        ).forEach(this::custom);
    }

    /**
     * generate block and item
     */
    private void normal(RegistryObject<Block> block) {
        simpleBlockWithItem(block.get(), cubeAll(block.get()));
    }

    /**
     * generate item with custom block
     */
    private void custom(RegistryObject<Block> block) {
        String path = block.getId().getPath();
        simpleBlockWithItem(block.get(), models().withExistingParent(path, new ResourceLocation("extrabotany:block/" + path + "_default")));
    }
}