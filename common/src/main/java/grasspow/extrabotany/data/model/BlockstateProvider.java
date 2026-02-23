package grasspow.extrabotany.data.model;

import com.google.gson.JsonElement;
import grasspow.extrabotany.api.ExtraBotanyAPI;
import grasspow.extrabotany.common.block.ExtraBotanyBlocks;
import grasspow.extrabotany.common.lib.LibMisc;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.model.ModelTemplate;
import net.minecraft.data.models.model.TextureMapping;
import net.minecraft.data.models.model.TextureSlot;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import org.slf4j.Logger;
import vazkii.botania.common.block.BotaniaMushroomBlock;
import vazkii.botania.common.block.flower.BotaniaFlowerBlock;
import vazkii.botania.common.block.flower.FloatingFlowerBaseBlock;
import vazkii.botania.common.block.flower.SpecialFlowerBlock;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;
import static net.minecraft.data.models.model.ModelLocationUtils.getModelLocation;

public class BlockstateProvider extends vazkii.botania.data.BlockstateProvider {

    protected final Map<ResourceLocation, Supplier<JsonElement>> models = new HashMap<>();
    protected final BiConsumer<ResourceLocation, Supplier<JsonElement>> modelOutput = models::put;

    public BlockstateProvider(PackOutput packOutput) {
        super(packOutput);
    }

    @Override
    public String getName() {
        return "ExtraBotany Blockstates";
    }

    protected Logger getLogger() {
        return ExtraBotanyAPI.LOGGER;
    }

    Predicate<Block> flowers = b -> b instanceof SpecialFlowerBlock
            || b instanceof BotaniaMushroomBlock
            || b instanceof BotaniaFlowerBlock;

    @Override
    protected void registerStatesAndModels() {
        Set<Block> remainingBlocks = BuiltInRegistries.BLOCK.stream()
                .filter(b -> LibMisc.MOD_ID.equals(BuiltInRegistries.BLOCK.getKey(b).getNamespace()))
                .collect(Collectors.toSet());

        //Manually written model
        manualModel(remainingBlocks, ExtraBotanyBlocks.pedestal);
        manualModel(remainingBlocks, ExtraBotanyBlocks.manaBuffer);
        manualModel(remainingBlocks, ExtraBotanyBlocks.quantumManaBuffer);
        manualModel(remainingBlocks, ExtraBotanyBlocks.trophy);
        manualModel(remainingBlocks, ExtraBotanyBlocks.livingrockBarrel);
        manualModel(remainingBlocks, ExtraBotanyBlocks.powerFrame);

        //flower
        takeAll(remainingBlocks, b -> b instanceof FloatingFlowerBaseBlock).forEach(b -> {
            singleVariantBlockState(b, getModelLocation(b));
        });
        ModelTemplate crossTemplate = new ModelTemplate(Optional.of(exbotRL("block/shapes/cross")), Optional.empty(), TextureSlot.CROSS);
        takeAll(remainingBlocks, flowers).forEach(b -> singleVariantBlockState(b, crossTemplate.create(b, TextureMapping.cross(b), this.modelOutput)));

        remainingBlocks.forEach(this::cubeAllNoRemove);
    }
}
