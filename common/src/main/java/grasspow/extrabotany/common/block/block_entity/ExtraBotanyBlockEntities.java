package grasspow.extrabotany.common.block.block_entity;

import grasspow.extrabotany.api.ExtraBotanyAPI;
import grasspow.extrabotany.common.block.flower.functional.AnnoyingFlowerBlockEntity;
import grasspow.extrabotany.common.block.flower.functional.SerenitianBlockEntity;
import grasspow.extrabotany.common.block.flower.generating.*;
import grasspow.extrabotany.common.lib.LibBlockNames;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.Predicate;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;
import static grasspow.extrabotany.common.block.ExtraBotanyBlocks.*;


public class ExtraBotanyBlockEntities {
    private static final Map<ResourceLocation, BlockEntityType<?>> ALL = new HashMap<>();

	public static final BlockEntityType<PedestalBlockEntity> PEDESTAL = type(exbotRL(LibBlockNames.PEDESTAL), PedestalBlockEntity::new, pedestal);
    public static final BlockEntityType<ManaBufferBlockEntity> MANA_BUFFER = type(exbotRL(LibBlockNames.MANA_BUFFER),ManaBufferBlockEntity::new,manaBuffer,quantumManaBuffer);
    public static final BlockEntityType<LivingrockBarrelBlockEntity> LIVINGROCK_BARREL = type(exbotRL(LibBlockNames.LIVINGROCK_BARREL),LivingrockBarrelBlockEntity::new,livingrockBarrel);
    public static final BlockEntityType<PowerFrameBlockEntity> POWER_FRAME = type(exbotRL(LibBlockNames.POWER_FRAME),PowerFrameBlockEntity::new,powerFrame);

    public static final BlockEntityType<AnnoyingFlowerBlockEntity> ANNOYING_FLOWER = type(getId(annoyingFlower),AnnoyingFlowerBlockEntity::new, annoyingFlower, annoyingFlowerFloating);
    public static final BlockEntityType<SerenitianBlockEntity> SERENITIAN = type(getId(serenitian),SerenitianBlockEntity::new, serenitian, serenitianFloating);
    public static final BlockEntityType<BellFlowerBlockEntity> BELL_FLOWER = type(getId(bellFlower),BellFlowerBlockEntity::new, bellFlower, bellFlowerFloating);
    public static final BlockEntityType<EdelweissBlockEntity> EDELWEISS = type(getId(edelweiss),EdelweissBlockEntity::new, edelweiss, edelweissFloating);
    public static final BlockEntityType<GeminiOrchidBlockEntity> GEMINI_ORCHID = type(getId(geminiOrchid),GeminiOrchidBlockEntity::new, geminiOrchid, geminiOrchidFloating);
    public static final BlockEntityType<SunBlessBlockEntity> SUN_BLESS = type(getId(sunBless),SunBlessBlockEntity::new, sunBless, sunBlessFloating);
    public static final BlockEntityType<MoonBlessBlockEntity> MOON_BLESS = type(getId(moonBless),MoonBlessBlockEntity::new, moonBless, moonBlessFloating);
    public static final BlockEntityType<OmniVioletBlockEntity> OMNI_VIOLET = type(getId(omniViolet),OmniVioletBlockEntity::new, omniViolet, omniVioletFloating);
    public static final BlockEntityType<ReikarLilyBlockEntity> REIKAR_LILY = type(getId(reikarLily),ReikarLilyBlockEntity::new, reikarLily, reikarLilyFloating);
    public static final BlockEntityType<TinkleFlowerBlockEntity> TINKLE_FLOWER = type(getId(tinkleFlower),TinkleFlowerBlockEntity::new, tinkleFlower, tinkleFlowerFloating);

    private static ResourceLocation getId(Block b) {
        return BuiltInRegistries.BLOCK.getKey(b);
    }

    private static <T extends BlockEntity> BlockEntityType<T> type(String id, BlockEntityType.BlockEntitySupplier<T> factory, Block... blocks) {
        return type(exbotRL(id), factory, blocks);
    }

    private static <T extends BlockEntity> BlockEntityType<T> type(String id, BlockEntityType.BlockEntitySupplier<T> factory, Predicate<Block> blockPredicate) {
        return type(id, factory, BuiltInRegistries.BLOCK.stream()
                .filter(blockPredicate)
                .filter(block -> BuiltInRegistries.BLOCK.getKey(block).getNamespace().equals(ExtraBotanyAPI.MODID))
                .toArray(Block[]::new));
    }

    private static <T extends BlockEntity> BlockEntityType<T> type(ResourceLocation id, BlockEntityType.BlockEntitySupplier<T> factory, Block... blocks) {
        // TODO: should probably set up that datafixer type instead of passing null to build()
        var ret = BlockEntityType.Builder.of(factory, blocks).build(null);
        var old = ALL.put(id, ret);
        if (old != null) {
            throw new IllegalArgumentException("Duplicate id " + id);
        }
        return ret;
    }

    public static void registerTiles(BiConsumer<BlockEntityType<?>, ResourceLocation> r) {
        for (var e : ALL.entrySet()) {
            r.accept(e.getValue(), e.getKey());
        }
    }
}
