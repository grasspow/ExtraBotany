package grasspow.extrabotany.data.model;

import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
import grasspow.extrabotany.api.ExtraBotanyAPI;
import grasspow.extrabotany.common.item.ExtraBotanyItems;
import grasspow.extrabotany.common.item.brew.BaseBrewItemEX;
import grasspow.extrabotany.common.item.brew.CocktailItem;
import grasspow.extrabotany.common.item.brew.InfiniteWineItem;
import grasspow.extrabotany.common.lib.LibEntityNames;
import grasspow.extrabotany.common.lib.LibItemNames;
import grasspow.extrabotany.common.lib.LibMisc;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.DataProvider;
import net.minecraft.data.PackOutput;
import net.minecraft.data.models.model.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import vazkii.botania.common.block.BotaniaBlocks;
import vazkii.botania.common.block.BotaniaMushroomBlock;
import vazkii.botania.common.block.LuminizerBlock;
import vazkii.botania.common.block.flower.BotaniaFlowerBlock;
import vazkii.botania.common.block.flower.FloatingFlowerBaseBlock;
import vazkii.botania.common.block.flower.SpecialFlowerBlock;
import vazkii.botania.data.util.ModelWithOverrides;
import vazkii.botania.data.util.OverrideHolder;
import vazkii.botania.mixin.TextureSlotAccessor;

import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;

public class ItemModelProvider implements DataProvider {
    private static final TextureSlot LAYER1 = TextureSlotAccessor.make("layer1");
    private static final TextureSlot LAYER2 = TextureSlotAccessor.make("layer2");
    private static final TextureSlot LAYER3 = TextureSlotAccessor.make("layer3");
    private static final ModelTemplate GENERATED_0 = new ModelTemplate(Optional.of(ResourceLocation.withDefaultNamespace("item/generated")), Optional.empty(), TextureSlot.LAYER0);
    private static final ModelTemplate GENERATED_1 = new ModelTemplate(Optional.of(ResourceLocation.withDefaultNamespace("item/generated")), Optional.empty(), TextureSlot.LAYER0, LAYER1);
    private static final ModelTemplate GENERATED_2 = new ModelTemplate(Optional.of(ResourceLocation.withDefaultNamespace("item/generated")), Optional.empty(), TextureSlot.LAYER0, LAYER1, LAYER2);
    private static final ModelTemplate HANDHELD_1 = new ModelTemplate(Optional.of(ResourceLocation.withDefaultNamespace("item/handheld")), Optional.empty(), TextureSlot.LAYER0, LAYER1);
    private static final ModelTemplate HANDHELD_3 = new ModelTemplate(Optional.of(ResourceLocation.withDefaultNamespace("item/handheld")), Optional.empty(), TextureSlot.LAYER0, LAYER1, LAYER2, LAYER3);
    private static final ModelWithOverrides GENERATED_OVERRIDES = new ModelWithOverrides(ResourceLocation.withDefaultNamespace("item/generated"), TextureSlot.LAYER0);
    private static final ModelWithOverrides GENERATED_OVERRIDES_1 = new ModelWithOverrides(ResourceLocation.withDefaultNamespace("item/generated"), TextureSlot.LAYER0, LAYER1);
    private static final ModelWithOverrides HANDHELD_OVERRIDES = new ModelWithOverrides(ResourceLocation.withDefaultNamespace("item/handheld"), TextureSlot.LAYER0);
    private static final ModelWithOverrides HANDHELD_OVERRIDES_2 = new ModelWithOverrides(ResourceLocation.withDefaultNamespace("item/handheld"), TextureSlot.LAYER0, LAYER1, LAYER2);

    private final PackOutput packOutput;

    public ItemModelProvider(PackOutput packOutput) {
        this.packOutput = packOutput;
    }

    @Override
    public CompletableFuture<?> run(CachedOutput cache) {
        Set<Item> items = BuiltInRegistries.ITEM.stream().filter(i -> LibMisc.MOD_ID.equals(BuiltInRegistries.ITEM.getKey(i).getNamespace()))
                .collect(Collectors.toSet());
        Map<ResourceLocation, Supplier<JsonElement>> map = new HashMap<>();
        registerItemBlocks(takeAll(items, i -> i instanceof BlockItem).stream().map(i -> (BlockItem) i).collect(Collectors.toSet()), map::put);
        registerItemOverrides(items, map::put);
        registerItems(items, map::put);
        PackOutput.PathProvider modelPathProvider = packOutput.createPathProvider(PackOutput.Target.RESOURCE_PACK, "models");
        List<CompletableFuture<?>> output = new ArrayList<>();

        for (Map.Entry<ResourceLocation, Supplier<JsonElement>> e : map.entrySet()) {
            ResourceLocation id = e.getKey();
            output.add(DataProvider.saveStable(cache, e.getValue().get(), modelPathProvider.json(id)));
        }

        return CompletableFuture.allOf(output.toArray(CompletableFuture[]::new));
    }

    private void registerItemBlocks(Set<BlockItem> itemBlocks, BiConsumer<ResourceLocation, Supplier<JsonElement>> consumer) {
        //flowers
        itemBlocks.removeIf(i -> {
            var id = BuiltInRegistries.BLOCK.getKey(i.getBlock());
            return id.getNamespace().equals(LibMisc.MOD_ID) && i.getBlock() instanceof FloatingFlowerBaseBlock;
        });
        Predicate<BlockItem> defaultGenerated = i -> {
            Block b = i.getBlock();
            return b instanceof SpecialFlowerBlock
                    || b instanceof BotaniaMushroomBlock
                    || b instanceof LuminizerBlock
                    || b instanceof BotaniaFlowerBlock
                    || b == BotaniaBlocks.ghostRail;
        };
        takeAll(itemBlocks, defaultGenerated).forEach(i -> {
            ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(i), TextureMapping.layer0(i.getBlock()), consumer);
        });
        itemBlocks.forEach(i -> {
            consumer.accept(ModelLocationUtils.getModelLocation(i), new DelegatedModel(ModelLocationUtils.getModelLocation(i.getBlock())));
        });
    }

    private static void registerItemOverrides(Set<Item> items, BiConsumer<ResourceLocation, Supplier<JsonElement>> consumer) {
        OverrideHolder cocktailOverrides = new OverrideHolder();
        for (int i = 1; i < CocktailItem.DEFAULT_USES_COCKTAIL; i++) {
            ResourceLocation overrideModel = ModelLocationUtils.getModelLocation(ExtraBotanyItems.cocktail, "_" + i);
            GENERATED_1.create(overrideModel,
                    TextureMapping.layer0(ExtraBotanyItems.emptyBottle).put(LAYER1, overrideModel),
                    consumer);
            cocktailOverrides.add(overrideModel, Pair.of(exbotRL("swigs_taken"), (double) i / (BaseBrewItemEX.DEFAULT_USES_COCKTAIL - 1)));
        }
        GENERATED_OVERRIDES_1.create(ModelLocationUtils.getModelLocation(ExtraBotanyItems.cocktail),
                TextureMapping.layer0(ExtraBotanyItems.emptyBottle).put(LAYER1, TextureMapping.getItemTexture(ExtraBotanyItems.cocktail, "_0")),
                cocktailOverrides,
                consumer);
        items.remove(ExtraBotanyItems.cocktail);

        OverrideHolder infiniteWineOverrides = new OverrideHolder();
        for (int i = 1; i < InfiniteWineItem.DEFAULT_USES_INFINITE_WINE; i++) {
            ResourceLocation overrideModel = ModelLocationUtils.getModelLocation(ExtraBotanyItems.infiniteWine, "_" + i);
            GENERATED_1.create(overrideModel,
                    TextureMapping.layer0(exbotRL("item/" + LibItemNames.INFINITE_WINE)).put(LAYER1, overrideModel),
                    consumer);
            infiniteWineOverrides.add(overrideModel, Pair.of(exbotRL("swigs_taken"), (double) i / (BaseBrewItemEX.DEFAULT_USES_INFINITE_WINE - 1)));
        }
        GENERATED_OVERRIDES_1.create(ModelLocationUtils.getModelLocation(ExtraBotanyItems.infiniteWine),
                TextureMapping.layer0(ExtraBotanyItems.infiniteWine).put(LAYER1, TextureMapping.getItemTexture(ExtraBotanyItems.infiniteWine, "_0")),
                infiniteWineOverrides,
                consumer);
        items.remove(ExtraBotanyItems.infiniteWine);
    }

    private static void registerItems(Set<Item> items, BiConsumer<ResourceLocation, Supplier<JsonElement>> consumer) {
        //skip
        items.remove(ExtraBotanyItems.silverBullet);
        items.remove(ExtraBotanyItems.flamescionWeapon);
        items.remove(ExtraBotanyItems.fallnaught);

        takeAll(items, ExtraBotanyItems.splashGrenade).forEach(i -> GENERATED_1.create(ModelLocationUtils.getModelLocation(i),
                TextureMapping.layer0(TextureMapping.getItemTexture(i)).put(LAYER1, TextureMapping.getItemTexture(i, "_1")), consumer));
        takeAll(items,
                //hammer
                ExtraBotanyItems.elementiumHammer,
                ExtraBotanyItems.manasteelHammer,
                ExtraBotanyItems.terrasteelHammer,
                ExtraBotanyItems.ultimateHammer,

                //tool
                ExtraBotanyItems.manaReader,
                ExtraBotanyItems.walkingCane,
                ExtraBotanyItems.rodOfDiscord,

                //weapon
                ExtraBotanyItems.shadowKatana,
                ExtraBotanyItems.starWrath,
                ExtraBotanyItems.influxWaver,
                ExtraBotanyItems.trueShadowKatana,
                ExtraBotanyItems.trueTerraBlade,
                ExtraBotanyItems.excaliber,
                ExtraBotanyItems.firstFractal
        ).forEach(i -> ModelTemplates.FLAT_HANDHELD_ITEM.create(ModelLocationUtils.getModelLocation(i), TextureMapping.layer0(i), consumer));
        GENERATED_0.create(exbotRL("item/first_fractal"),
                TextureMapping.layer0(exbotRL("item/sword_domain_9")),
                consumer);
        items.remove(ExtraBotanyItems.firstFractal);
        takeAll(items, i -> true).forEach(i -> ModelTemplates.FLAT_ITEM.create(ModelLocationUtils.getModelLocation(i), TextureMapping.layer0(i), consumer));

    }

    private void registerIcons(BiConsumer<ResourceLocation, Supplier<JsonElement>> consumer) {
        simpleWithIcon(consumer, LibEntityNames.INFLUX_WAVER);
        simpleIcon(consumer, LibItemNames.TRUE_SHADOW_KATANA);
        simpleIcon(consumer, LibItemNames.TRUE_TERRA_BLADE);
        for (int i = 0; i < 10; i++) {
            GENERATED_0.create(exbotRL("icon/sword_domain_" + i),
                    TextureMapping.layer0(exbotRL("item/sword_domain_" + i)),
                    consumer);
        }
        for (int i = 0; i < 4; i++) {
            var wing = "";
            switch (i) {
                case 0:
                    wing = "coregod";
                    break;
                case 1:
                    wing = "volantoro";
                    break;
                case 2:
                    wing = "jim";
                    break;
                case 3:
                    wing = "mechanical";
                    break;
            }
            GENERATED_0.create(exbotRL("icon/wing_" + i),
                    TextureMapping.layer0(exbotRL("item/wing_" + wing)),
                    consumer);
        }
        GENERATED_0.create(exbotRL("icon/wing_core_god"),
                TextureMapping.layer0(exbotRL("item/wing_coregod_")),
                consumer);
    }

    @SafeVarargs
    public static <T> Collection<T> takeAll(Set<? extends T> src, T... items) {
        List<T> ret = Arrays.asList(items);
        for (T item : items) {
            if (!src.contains(item)) {
                ExtraBotanyAPI.LOGGER.warn("Item {} not found in set", item);
            }
        }
        if (!src.removeAll(ret)) {
            ExtraBotanyAPI.LOGGER.warn("takeAll array didn't yield anything ({})", Arrays.toString(items));
        }
        return ret;
    }

    public static <T> Collection<T> takeAll(Set<T> src, Predicate<T> pred) {
        List<T> ret = new ArrayList<>();

        Iterator<T> iter = src.iterator();
        while (iter.hasNext()) {
            T item = iter.next();
            if (pred.test(item)) {
                iter.remove();
                ret.add(item);
            }
        }

        if (ret.isEmpty()) {
            ExtraBotanyAPI.LOGGER.warn("takeAll predicate yielded nothing", new Throwable());
        }
        return ret;
    }

    private void simpleIcon(BiConsumer<ResourceLocation, Supplier<JsonElement>> consumer, String name) {
        GENERATED_0.create(exbotRL("icon/" + name + "_projectile"),
                TextureMapping.layer0(exbotRL("item/" + name)),
                consumer);
    }

    private void simpleWithIcon(BiConsumer<ResourceLocation, Supplier<JsonElement>> consumer, String name) {
        GENERATED_0.create(exbotRL("icon/" + name),
                TextureMapping.layer0(exbotRL("item/" + name)),
                consumer);
    }

    @Override
    public String getName() {
        return "ExtraBotany item models";
    }
}
