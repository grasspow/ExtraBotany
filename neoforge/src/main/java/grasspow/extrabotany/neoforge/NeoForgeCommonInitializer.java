package grasspow.extrabotany.neoforge;

import com.google.common.base.Suppliers;
import grasspow.extrabotany.api.ExtraBotanyAPI;
import grasspow.extrabotany.api.ExtraBotanyNeoForgeCapabilities;
import grasspow.extrabotany.api.ExtraBotanyRegistries;
import grasspow.extrabotany.api.NatureOrb;
import grasspow.extrabotany.api.item.ExtraBotanyArmorMaterials;
import grasspow.extrabotany.common.advancements.ExtraBotanyCriteriaTriggers;
import grasspow.extrabotany.common.block.ExtraBotanyBlocks;
import grasspow.extrabotany.common.block.block_entity.ExtraBotanyBlockEntities;
import grasspow.extrabotany.common.block.block_entity.PedestalBlockEntity;
import grasspow.extrabotany.common.component.ExtraBotanyDataComponents;
import grasspow.extrabotany.common.crafting.ExtraBotanyRecipeTypes;
import grasspow.extrabotany.common.effect.brew.ExtraBotanyBrews;
import grasspow.extrabotany.common.entity.ExtraBotanyEntities;
import grasspow.extrabotany.common.handler.ContributorListHandler;
import grasspow.extrabotany.common.handler.ExtraBotanySounds;
import grasspow.extrabotany.common.impl.DefaultNatureOrb;
import grasspow.extrabotany.common.item.ExtraBotanyItems;
import grasspow.extrabotany.common.item.brew.InfiniteWineItem;
import grasspow.extrabotany.common.item.equipment.BuddhistRelicsItem;
import grasspow.extrabotany.common.item.equipment.bauble.BaubleItem;
import grasspow.extrabotany.common.item.equipment.bauble.MoonPendantItem;
import grasspow.extrabotany.common.item.equipment.bauble.SagesManaRingItem;
import grasspow.extrabotany.common.item.equipment.bauble.SunRingItem;
import grasspow.extrabotany.common.item.equipment.tool.CameraItem;
import grasspow.extrabotany.common.item.equipment.weapon.*;
import grasspow.extrabotany.common.item.misc.RewardBagItem;
import grasspow.extrabotany.common.lib.LibBlockNames;
import grasspow.extrabotany.common.lib.LibMisc;
import grasspow.extrabotany.common.network.server.BuddhistChangePack;
import grasspow.extrabotany.neoforge.network.NeoForgePacketHandler;
import grasspow.extrabotany.xplat.ClientXplatAbstractions;
import grasspow.extrabotany.xplat.XplatAbstractions;
import net.minecraft.ChatFormatting;
import net.minecraft.client.Minecraft;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.neoforged.neoforge.capabilities.ItemCapability;
import net.neoforged.neoforge.capabilities.RegisterCapabilitiesEvent;
import net.neoforged.neoforge.client.event.InputEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.registries.RegisterEvent;
import org.lwjgl.glfw.GLFW;
import vazkii.botania.api.BotaniaForgeCapabilities;
import vazkii.botania.api.BotaniaRegistries;
import vazkii.botania.api.item.Relic;
import vazkii.botania.api.mana.ManaItem;
import vazkii.botania.common.handler.EquipmentHandler;
import vazkii.botania.common.impl.mana.DefaultManaItemImpl;
import vazkii.botania.common.item.CustomCreativeTabContents;
import vazkii.botania.neoforge.integration.curios.CurioIntegration;
import vazkii.patchouli.api.PatchouliAPI;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;


@Mod(LibMisc.MOD_ID)
public class NeoForgeCommonInitializer {

    public NeoForgeCommonInitializer(IEventBus modBus, ModContainer modContainer) {
        ExtraBotanyAPI.LOGGER.debug("API instances: {}",
                List.of(ExtraBotanyAPI.instance(), XplatAbstractions.instance()));
        NeoForgeExtraBotanyConfig.setup(modContainer);
        modBus.register(this);
        modBus.addListener(NeoForgePacketHandler::registerPayloadHandlers);
    }

    @SubscribeEvent
    public void commonSetup(FMLCommonSetupEvent evt) {
        registerEvents();

        evt.enqueueWork(() -> {
            ContributorListHandler.firstStart();
            PatchouliAPI.get().registerMultiblock(exbotRL(LibBlockNames.PEDESTAL + "_multi_block"), PedestalBlockEntity.MULTIBLOCK.get());
            PatchouliAPI.get().registerMultiblock(exbotRL(LibBlockNames.PEDESTAL + "_multi_block_2"), PedestalBlockEntity.MULTIBLOCK2.get());
            RewardBagItem.initCategoryMap();
        });
    }

    @SubscribeEvent
    private void loadComplete(FMLLoadCompleteEvent event) {
//        MemeHandler.spam();
    }

    @SubscribeEvent
    public void registryInit(RegisterEvent event) {

        runRegistration(event, Registries.SOUND_EVENT, ExtraBotanySounds::init);
        runRegistration(event, Registries.ARMOR_MATERIAL, ExtraBotanyArmorMaterials::registerArmorMaterials);


        bind(event, Registries.DATA_COMPONENT_TYPE, ExtraBotanyDataComponents::registerComponents);
        //            ExtraBotanyFlammability.register();
        bind(event, Registries.BLOCK, ExtraBotanyBlocks::registerBlocks);
        bindForItems(event, ExtraBotanyBlocks::registerItemBlocks);
        bind(event, Registries.BLOCK_ENTITY_TYPE, ExtraBotanyBlockEntities::registerTiles);
        bindForItems(event, ExtraBotanyItems::registerItems);

        bind(event, Registries.RECIPE_TYPE, ExtraBotanyRecipeTypes::submitRecipeTypes);
        bind(event, Registries.RECIPE_SERIALIZER, ExtraBotanyRecipeTypes::submitRecipeSerializers);

        bind(event, Registries.ENTITY_TYPE, ExtraBotanyEntities::registerEntities);
        
        bind(event, BotaniaRegistries.BREWS, ExtraBotanyBrews::submitRegistrations);

        bind(event, Registries.TRIGGER_TYPE, ExtraBotanyCriteriaTriggers::init);

        bind(event, Registries.CREATIVE_MODE_TAB, consumer -> consumer.accept(
                CreativeModeTab.builder()
                        .title(Component.translatable("itemGroup.extrabotany").withStyle(style -> style.withColor(ChatFormatting.WHITE)))
                        .hideTitle()
                        .icon(() -> new ItemStack(ExtraBotanyItems.pylon))
                        .backgroundTexture(exbotRL("textures/gui/tab_extrabotany.png"))
                        .build(),
                ExtraBotanyRegistries.EXBOT_TAB_KEY.location()));
    }


    private static <T> void runRegistration(RegisterEvent event, ResourceKey<Registry<T>> registryKey, Consumer<Registry<T>> source) {
        Registry<T> registry = event.getRegistry(registryKey);
        if (registry != null) {
            source.accept(registry);
        }
    }

    private static <T> void bind(RegisterEvent event, ResourceKey<Registry<T>> registryKey, Consumer<BiConsumer<T, ResourceLocation>> source) {
        Registry<T> registry = event.getRegistry(registryKey);
        if (registry != null) {
            source.accept((t, rl) -> Registry.register(registry, rl, t));
        }
    }

    private final Set<Item> itemsToAddToCreativeTab = new LinkedHashSet<>();

    private void bindForItems(RegisterEvent event, Consumer<BiConsumer<Item, ResourceLocation>> source) {
        Registry<Item> registry = event.getRegistry(Registries.ITEM);
        if (registry != null) {
            source.accept((t, rl) -> {
                itemsToAddToCreativeTab.add(t);
                Registry.register(registry, rl, t);
            });
        }
    }

    private void registerEvents() {
        IEventBus bus = NeoForge.EVENT_BUS;

        bus.addListener((InputEvent.Key event) -> {
            Player player = Minecraft.getInstance().player;
            if (player == null)
                return;
            if (event.getAction() == GLFW.GLFW_PRESS && event.getKey() == GLFW.GLFW_KEY_LEFT_CONTROL) {
                if (!BuddhistRelicsItem.relicShift(player.getMainHandItem()).isEmpty()) {
                    ClientXplatAbstractions.INSTANCE.sendToServer(BuddhistChangePack.INSTANCE);
                }
            }
        });
    }

    private static final Supplier<Map<Item, Function<ItemStack, ManaItem>>> MANA_ITEM = Suppliers.memoize(() -> Map.of(
            ExtraBotanyItems.sagesManaRing, DefaultManaItemImpl::new,
            ExtraBotanyItems.ultimateHammer, DefaultManaItemImpl::new
    ));

    private static final Supplier<Map<Item, Function<ItemStack, NatureOrb>>> NATURE_ORB = Suppliers.memoize(() -> Map.of(
            ExtraBotanyItems.natureOrb, DefaultNatureOrb::new
    ));

    private static final Supplier<Map<Item, Function<ItemStack, Relic>>> RELIC = Suppliers.memoize(() -> Map.ofEntries(
            Map.entry(ExtraBotanyItems.infiniteWine, InfiniteWineItem::makeRelic),
            Map.entry(ExtraBotanyItems.sunRing, SunRingItem::makeRelic),
            Map.entry(ExtraBotanyItems.moonPendant, MoonPendantItem::makeRelic),
            Map.entry(ExtraBotanyItems.sagesManaRing, SagesManaRingItem::makeRelic),
            Map.entry(ExtraBotanyItems.influxWaver, InfluxWaverItem::makeRelic),
            Map.entry(ExtraBotanyItems.starWrath, StarWrathItem::makeRelic),
            Map.entry(ExtraBotanyItems.trueShadowKatana, TrueShadowKatanaItem::makeRelic),
            Map.entry(ExtraBotanyItems.trueTerraBlade, TrueTerraBladeItem::makeRelic),
            Map.entry(ExtraBotanyItems.excaliber, ExcaliberItem::makeRelic),
            Map.entry(ExtraBotanyItems.firstFractal, FirstFractalItem::makeRelic),
            Map.entry(ExtraBotanyItems.fallnaught, FailnaughtItem::makeRelic),
            Map.entry(ExtraBotanyItems.camera, CameraItem::makeRelic),
//            Map.entry(ExtraBotanyItems.CORE_GOD.get(), CoreGodItem::makeRelic),
            Map.entry(ExtraBotanyItems.buddhistRelics, BuddhistRelicsItem::makeRelic)
    ));

    @SubscribeEvent
    private void attachItemCaps(RegisterCapabilitiesEvent e) {
        if (EquipmentHandler.instance instanceof CurioIntegration ci) {
            // fool way
            Thread.startVirtualThread(() -> {
                boolean flag = false;
                while (!flag) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    Item[] baubleItems = BuiltInRegistries.ITEM.stream().filter(item -> item instanceof BaubleItem).toArray(Item[]::new);
                    if (baubleItems.length == 0) continue;
                    ci.initCapability(e, baubleItems);
                    flag = true;
                }
            });
        }
        attachMappedItemCaps(e, BotaniaForgeCapabilities.MANA_ITEM, MANA_ITEM.get());
        attachMappedItemCaps(e, BotaniaForgeCapabilities.RELIC, RELIC.get());
        attachMappedItemCaps(e, ExtraBotanyNeoForgeCapabilities.NATURE_ORB, NATURE_ORB.get());
    }

    private static <T> void attachMappedItemCaps(RegisterCapabilitiesEvent e, ItemCapability<T, Void> capability,
                                                 Map<Item, Function<ItemStack, T>> itemProviderMap) {
        itemProviderMap.forEach((item, provider) -> e.registerItem(
                capability, (stack, context) -> provider.apply(stack), item));
    }

    @SubscribeEvent
    private void addItemsToCreativeTab(BuildCreativeModeTabContentsEvent e) {
        if (e.getTabKey() == ExtraBotanyRegistries.EXBOT_TAB_KEY) {
            for (Item item : this.itemsToAddToCreativeTab) {
                if (item instanceof CustomCreativeTabContents cc) {
                    cc.addToCreativeTab(item, e);
                } else if (item instanceof BlockItem bi && bi.getBlock() instanceof CustomCreativeTabContents cc) {
                    cc.addToCreativeTab(item, e);
                } else {
                    e.accept(item);
                }
            }
        }
    }
}
