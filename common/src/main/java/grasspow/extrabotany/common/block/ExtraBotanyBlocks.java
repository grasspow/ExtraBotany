package grasspow.extrabotany.common.block;

import grasspow.extrabotany.common.block.block_entity.ExtraBotanyBlockEntities;
import grasspow.extrabotany.common.item.ExtraBotanyItems;
import grasspow.extrabotany.common.lib.LibBlockNames;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import vazkii.botania.api.block_entity.SpecialFlowerBlockEntity;
import vazkii.botania.common.block.flower.FloatingSpecialFlowerBlock;
import vazkii.botania.common.block.flower.SpecialFlowerBlock;
import vazkii.botania.common.item.block.SpecialFlowerBlockItem;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;
import static vazkii.botania.common.block.BotaniaBlocks.livingrock;

public class ExtraBotanyBlocks {
    public static final Block photoniumBlock = new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));
    public static final Block shadowiumBlock = new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));
    public static final Block orichalcosBlock = new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.DIAMOND_BLOCK));
    public static final Block pedestal = new PedestalBlock(BlockBehaviour.Properties.ofFullCopy(livingrock));
    public static final Block manaBuffer = new ManaBufferBlock(ManaBufferBlock.Variant.DEFAULT, BlockBehaviour.Properties.ofFullCopy(livingrock));
    public static final Block quantumManaBuffer = new ManaBufferBlock(ManaBufferBlock.Variant.QUANTUM, BlockBehaviour.Properties.ofFullCopy(livingrock));
    public static final Block trophy = new TrophyBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SKELETON_SKULL));
    public static final Block livingrockBarrel = new LivingrockBarrelBlock(BlockBehaviour.Properties.ofFullCopy(livingrock));
    public static final Block dimensionCatalyst = new DimensionCatalystBlock(BlockBehaviour.Properties.ofFullCopy(livingrock));
    public static final Block powerFrame = new PowerFrameBlock(BlockBehaviour.Properties.ofFullCopy(livingrock).noOcclusion());



    private static final BlockBehaviour.Properties FLOWER_PROPS = BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY);
    private static final BlockBehaviour.Properties FLOATING_PROPS = BlockBehaviour.Properties.of().mapColor(MapColor.PLANT).strength(0.5F).sound(SoundType.GRAVEL).lightLevel(s -> 15);

    public static final Block annoyingFlower = createSpecialFlowerBlock(MobEffects.HUNGER, 360, FLOWER_PROPS, () -> ExtraBotanyBlockEntities.ANNOYING_FLOWER);
    public static final Block annoyingFlowerFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> ExtraBotanyBlockEntities.ANNOYING_FLOWER);
    public static final Block annoyingFlowerPotted = ExtraBotanyBlocks.flowerPot(annoyingFlower, 0);

    public static final Block serenitian = createSpecialFlowerBlock(MobEffects.HERO_OF_THE_VILLAGE, 360, FLOWER_PROPS, () -> ExtraBotanyBlockEntities.SERENITIAN);
    public static final Block serenitianFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> ExtraBotanyBlockEntities.SERENITIAN);
    public static final Block serenitianPotted = ExtraBotanyBlocks.flowerPot(serenitian, 0);

    public static final Block bellFlower = createSpecialFlowerBlock(MobEffects.MOVEMENT_SPEED, 360, FLOWER_PROPS, () -> ExtraBotanyBlockEntities.BELL_FLOWER);
    public static final Block bellFlowerFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> ExtraBotanyBlockEntities.BELL_FLOWER);
    public static final Block bellFlowerPotted = ExtraBotanyBlocks.flowerPot(bellFlower, 0);

    public static final Block edelweiss = createSpecialFlowerBlock(MobEffects.MOVEMENT_SLOWDOWN, 80, FLOWER_PROPS, () -> ExtraBotanyBlockEntities.EDELWEISS);
    public static final Block edelweissFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> ExtraBotanyBlockEntities.EDELWEISS);
    public static final Block edelweissPotted = ExtraBotanyBlocks.flowerPot(edelweiss, 0);


    public static final Block geminiOrchid = createSpecialFlowerBlock(MobEffects.GLOWING, 1600, FLOWER_PROPS, () -> ExtraBotanyBlockEntities.GEMINI_ORCHID);
    public static final Block geminiOrchidFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> ExtraBotanyBlockEntities.GEMINI_ORCHID);
    public static final Block geminiOrchidPotted = ExtraBotanyBlocks.flowerPot(geminiOrchid, 0);


    public static final Block sunBless = createSpecialFlowerBlock(MobEffects.LUCK, 1600, FLOWER_PROPS, () -> ExtraBotanyBlockEntities.SUN_BLESS);
    public static final Block sunBlessFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> ExtraBotanyBlockEntities.SUN_BLESS);
    public static final Block sunBlessPotted = ExtraBotanyBlocks.flowerPot(sunBless, 0);

    public static final Block moonBless = createSpecialFlowerBlock(MobEffects.UNLUCK, 1600, FLOWER_PROPS, () -> ExtraBotanyBlockEntities.MOON_BLESS);
    public static final Block moonBlessFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> ExtraBotanyBlockEntities.MOON_BLESS);
    public static final Block moonBlessPotted = ExtraBotanyBlocks.flowerPot(moonBless, 0);

    public static final Block omniViolet = createSpecialFlowerBlock(MobEffects.REGENERATION, 360, FLOWER_PROPS, () -> ExtraBotanyBlockEntities.OMNI_VIOLET);
    public static final Block omniVioletFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> ExtraBotanyBlockEntities.OMNI_VIOLET);
    public static final Block omniVioletPotted = ExtraBotanyBlocks.flowerPot(omniViolet, 0);

    public static final Block reikarLily = createSpecialFlowerBlock(MobEffects.JUMP, 1600, FLOWER_PROPS, () -> ExtraBotanyBlockEntities.REIKAR_LILY);
    public static final Block reikarLilyFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> ExtraBotanyBlockEntities.REIKAR_LILY);
    public static final Block reikarLilyPotted = ExtraBotanyBlocks.flowerPot(reikarLily, 0);

    public static final Block tinkleFlower = createSpecialFlowerBlock(MobEffects.DIG_SPEED, 360, FLOWER_PROPS, () -> ExtraBotanyBlockEntities.TINKLE_FLOWER);
    public static final Block tinkleFlowerFloating = new FloatingSpecialFlowerBlock(FLOATING_PROPS, () -> ExtraBotanyBlockEntities.TINKLE_FLOWER);
    public static final Block tinkleFlowerPotted = ExtraBotanyBlocks.flowerPot(tinkleFlower, 0);

    private static FlowerBlock createSpecialFlowerBlock(
            Holder<MobEffect> effect, int effectDuration,
            BlockBehaviour.Properties props,
            Supplier<BlockEntityType<? extends SpecialFlowerBlockEntity>> beType) {
        return new SpecialFlowerBlock(effect, effectDuration, props, beType);
    }

    static FlowerPotBlock flowerPot(Block block, int lightLevel) {
        BlockBehaviour.Properties properties = BlockBehaviour.Properties.of().instabreak().noOcclusion().pushReaction(PushReaction.DESTROY);
        return new FlowerPotBlock(block, lightLevel > 0 ? properties.lightLevel(blockState -> lightLevel) : properties);
    }

    public static void registerBlocks(BiConsumer<Block, ResourceLocation> r) {
        r.accept(photoniumBlock, exbotRL(LibBlockNames.PHOTONIUM_BLOCK));
        r.accept(shadowiumBlock, exbotRL(LibBlockNames.SHADOWIUM_BLOCK));
        r.accept(orichalcosBlock, exbotRL(LibBlockNames.ORICHALCOS_BLOCK));
        r.accept(pedestal, exbotRL(LibBlockNames.PEDESTAL));
        r.accept(manaBuffer, exbotRL(LibBlockNames.MANA_BUFFER));
        r.accept(quantumManaBuffer, exbotRL(LibBlockNames.QUANTUM_MANA_BUFFER));
        r.accept(trophy, exbotRL(LibBlockNames.TROPHY));
        r.accept(livingrockBarrel, exbotRL(LibBlockNames.LIVINGROCK_BARREL));
        r.accept(dimensionCatalyst, exbotRL(LibBlockNames.DIMENSION_CATALYST));
        r.accept(powerFrame, exbotRL(LibBlockNames.POWER_FRAME));


        r.accept(annoyingFlower, exbotRL(LibBlockNames.ANNOYING_FLOWER));
        r.accept(annoyingFlowerFloating, floating(exbotRL(LibBlockNames.ANNOYING_FLOWER)));
        r.accept(annoyingFlowerPotted, potted(exbotRL(LibBlockNames.ANNOYING_FLOWER)));

        r.accept(serenitian, exbotRL(LibBlockNames.SERENITIAN));
        r.accept(serenitianFloating, floating(exbotRL(LibBlockNames.SERENITIAN)));
        r.accept(serenitianPotted, potted(exbotRL(LibBlockNames.SERENITIAN)));

        r.accept(bellFlower, exbotRL(LibBlockNames.BELL_FLOWER));
        r.accept(bellFlowerFloating, floating(exbotRL(LibBlockNames.BELL_FLOWER)));
        r.accept(bellFlowerPotted, potted(exbotRL(LibBlockNames.BELL_FLOWER)));

        r.accept(edelweiss, exbotRL(LibBlockNames.EDELWEISS));
        r.accept(edelweissFloating, floating(exbotRL(LibBlockNames.EDELWEISS)));
        r.accept(edelweissPotted, potted(exbotRL(LibBlockNames.EDELWEISS)));

        r.accept(geminiOrchid, exbotRL(LibBlockNames.GEMINI_ORCHID));
        r.accept(geminiOrchidFloating, floating(exbotRL(LibBlockNames.GEMINI_ORCHID)));
        r.accept(geminiOrchidPotted, potted(exbotRL(LibBlockNames.GEMINI_ORCHID)));

        r.accept(sunBless, exbotRL(LibBlockNames.SUN_BLESS));
        r.accept(sunBlessFloating, floating(exbotRL(LibBlockNames.SUN_BLESS)));
        r.accept(sunBlessPotted, potted(exbotRL(LibBlockNames.SUN_BLESS)));

        r.accept(moonBless, exbotRL(LibBlockNames.MOON_BLESS));
        r.accept(moonBlessFloating, floating(exbotRL(LibBlockNames.MOON_BLESS)));
        r.accept(moonBlessPotted, potted(exbotRL(LibBlockNames.MOON_BLESS)));

        r.accept(omniViolet, exbotRL(LibBlockNames.OMNI_VIOLET));
        r.accept(omniVioletFloating, floating(exbotRL(LibBlockNames.OMNI_VIOLET)));
        r.accept(omniVioletPotted, potted(exbotRL(LibBlockNames.OMNI_VIOLET)));

        r.accept(reikarLily, exbotRL(LibBlockNames.REIKAR_LILY));
        r.accept(reikarLilyFloating, floating(exbotRL(LibBlockNames.REIKAR_LILY)));
        r.accept(reikarLilyPotted, potted(exbotRL(LibBlockNames.REIKAR_LILY)));

        r.accept(tinkleFlower, exbotRL(LibBlockNames.TINKLE_FLOWER));
        r.accept(tinkleFlowerFloating, floating(exbotRL(LibBlockNames.TINKLE_FLOWER)));
        r.accept(tinkleFlowerPotted, potted(exbotRL(LibBlockNames.TINKLE_FLOWER)));
    }


    private static ResourceLocation floating(ResourceLocation orig) {
        return ResourceLocation.fromNamespaceAndPath(orig.getNamespace(), "floating_" + orig.getPath());
    }

    private static ResourceLocation potted(ResourceLocation orig) {
        return ResourceLocation.fromNamespaceAndPath(orig.getNamespace(), "potted_" + orig.getPath());
    }

    private static ResourceLocation chibi(ResourceLocation orig) {
        return ResourceLocation.fromNamespaceAndPath(orig.getNamespace(), orig.getPath() + "_chibi");
    }

    public static void registerItemBlocks(BiConsumer<Item, ResourceLocation> r) {
        Item.Properties props = ExtraBotanyItems.defaultBuilder();
        r.accept(new BlockItem(photoniumBlock, props), BuiltInRegistries.BLOCK.getKey(photoniumBlock));
        r.accept(new BlockItem(shadowiumBlock, props), BuiltInRegistries.BLOCK.getKey(shadowiumBlock));
        r.accept(new BlockItem(orichalcosBlock, props), BuiltInRegistries.BLOCK.getKey(orichalcosBlock));
        r.accept(new BlockItem(pedestal, props), BuiltInRegistries.BLOCK.getKey(pedestal));
        r.accept(new BlockItem(manaBuffer, props), BuiltInRegistries.BLOCK.getKey(manaBuffer));
        r.accept(new BlockItem(quantumManaBuffer, props), BuiltInRegistries.BLOCK.getKey(quantumManaBuffer));
        r.accept(new BlockItem(trophy, props), BuiltInRegistries.BLOCK.getKey(trophy));
        r.accept(new BlockItem(livingrockBarrel, props), BuiltInRegistries.BLOCK.getKey(livingrockBarrel));
        r.accept(new BlockItem(dimensionCatalyst, props), BuiltInRegistries.BLOCK.getKey(dimensionCatalyst));
        r.accept(new BlockItem(powerFrame, props), BuiltInRegistries.BLOCK.getKey(powerFrame));

        r.accept(new SpecialFlowerBlockItem(annoyingFlower, props), getId(annoyingFlower));
        r.accept(new SpecialFlowerBlockItem(annoyingFlowerFloating, props), getId(annoyingFlowerFloating));

        r.accept(new SpecialFlowerBlockItem(serenitian, props), getId(serenitian));
        r.accept(new SpecialFlowerBlockItem(serenitianFloating, props), getId(serenitianFloating));

        r.accept(new SpecialFlowerBlockItem( bellFlower, props), getId( bellFlower));
        r.accept(new SpecialFlowerBlockItem( bellFlowerFloating, props), getId( bellFlowerFloating));

        r.accept(new SpecialFlowerBlockItem(edelweiss, props), getId(edelweiss));
        r.accept(new SpecialFlowerBlockItem(edelweissFloating, props), getId(edelweissFloating));

        r.accept(new SpecialFlowerBlockItem(geminiOrchid, props), getId(geminiOrchid));
        r.accept(new SpecialFlowerBlockItem(geminiOrchidFloating, props), getId(geminiOrchidFloating));

        r.accept(new SpecialFlowerBlockItem(sunBless, props), getId(sunBless));
        r.accept(new SpecialFlowerBlockItem(sunBlessFloating, props), getId(sunBlessFloating));

        r.accept(new SpecialFlowerBlockItem(moonBless, props), getId(moonBless));
        r.accept(new SpecialFlowerBlockItem(moonBlessFloating, props), getId(moonBlessFloating));

        r.accept(new SpecialFlowerBlockItem(omniViolet, props), getId(omniViolet));
        r.accept(new SpecialFlowerBlockItem(omniVioletFloating, props), getId(omniVioletFloating));

        r.accept(new SpecialFlowerBlockItem(reikarLily, props), getId(reikarLily));
        r.accept(new SpecialFlowerBlockItem(reikarLilyFloating, props), getId(reikarLilyFloating));

        r.accept(new SpecialFlowerBlockItem(tinkleFlower, props), getId(tinkleFlower));
        r.accept(new SpecialFlowerBlockItem(tinkleFlowerFloating, props), getId(tinkleFlowerFloating));
    }

    private static ResourceLocation getId(Block b) {
        return BuiltInRegistries.BLOCK.getKey(b);
    }
}