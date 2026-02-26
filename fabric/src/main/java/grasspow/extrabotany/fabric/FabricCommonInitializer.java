package grasspow.extrabotany.fabric;

import grasspow.extrabotany.api.ExtraBotanyFabricCapabilities;
import grasspow.extrabotany.api.ExtraBotanyRegistries;
import grasspow.extrabotany.api.item.IItemWithLeftClick;
import grasspow.extrabotany.common.block.ExtraBotanyBlocks;
import grasspow.extrabotany.common.block.block_entity.ExtraBotanyBlockEntities;
import grasspow.extrabotany.common.block.block_entity.PedestalBlockEntity;
import grasspow.extrabotany.common.component.ExtraBotanyDataComponents;
import grasspow.extrabotany.common.crafting.ExtraBotanyRecipeTypes;
import grasspow.extrabotany.common.effect.brew.ExtraBotanyBrews;
import grasspow.extrabotany.common.entity.ExtraBotanyEntities;
import grasspow.extrabotany.common.handler.ContributorListHandler;
import grasspow.extrabotany.common.impl.DefaultNatureOrb;
import grasspow.extrabotany.common.item.ExtraBotanyItems;
import grasspow.extrabotany.common.item.brew.InfiniteWineItem;
import grasspow.extrabotany.common.item.equipment.BuddhistRelicsItem;
import grasspow.extrabotany.common.item.equipment.bauble.CoreGodItem;
import grasspow.extrabotany.common.item.equipment.bauble.MoonPendantItem;
import grasspow.extrabotany.common.item.equipment.bauble.SagesManaRingItem;
import grasspow.extrabotany.common.item.equipment.bauble.SunRingItem;
import grasspow.extrabotany.common.item.equipment.tool.CameraItem;
import grasspow.extrabotany.common.item.equipment.weapon.*;
import grasspow.extrabotany.common.item.misc.RewardBagItem;
import grasspow.extrabotany.common.lib.LibBlockNames;
import grasspow.extrabotany.common.network.server.LeftClickPack;
import grasspow.extrabotany.fabric.network.FabricPacketHandler;
import grasspow.extrabotany.xplat.ClientXplatAbstractions;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.AttackBlockCallback;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.EntityHitResult;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.BotaniaFabricCapabilities;
import vazkii.botania.api.brew.Brew;
import vazkii.botania.common.handler.EquipmentHandler;
import vazkii.botania.common.impl.mana.DefaultManaItemImpl;
import vazkii.botania.common.item.CustomCreativeTabContents;
import vazkii.patchouli.api.PatchouliAPI;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.function.BiConsumer;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;

public class FabricCommonInitializer implements ModInitializer {

    @Override
    public void onInitialize() {
        coreInit();
        registryInit();

        ContributorListHandler.firstStart();
        PatchouliAPI.get().registerMultiblock(exbotRL(LibBlockNames.PEDESTAL + "_multi_block"), PedestalBlockEntity.MULTIBLOCK.get());
        PatchouliAPI.get().registerMultiblock(exbotRL(LibBlockNames.PEDESTAL + "_multi_block_2"), PedestalBlockEntity.MULTIBLOCK2.get());
        RewardBagItem.initCategoryMap();

        registerCapabilities();
        registerEvents();
    }

    private void coreInit() {
        EquipmentHandler.init();
        FabricPacketHandler.init();
        RewardBagItem.initCategoryMap();
    }

    private void registryInit() {
        // Core item/block/BE
        ExtraBotanyDataComponents.registerComponents(bind(BuiltInRegistries.DATA_COMPONENT_TYPE));
        ExtraBotanyBlocks.registerBlocks(bind(BuiltInRegistries.BLOCK));
        ExtraBotanyBlocks.registerItemBlocks(boundForItem);
        ExtraBotanyBlockEntities.registerTiles(bind(BuiltInRegistries.BLOCK_ENTITY_TYPE));
        ExtraBotanyItems.registerItems(boundForItem);
//		ExtraBotanyBlocks.registerFlowerPotPlants(bind(BuiltInRegistries.BLOCK));

        // Entity
        ExtraBotanyEntities.registerEntities(bind(BuiltInRegistries.ENTITY_TYPE));

        //potion
        Registry<Brew> BREW_REGISTRY = BotaniaAPI.instance().getBrewRegistry();
        if (BREW_REGISTRY == null) {
            try {
                Class.forName("vazkii.botania.fabric.FabricCommonInitializer");
            } catch (Exception ignored) {
            }

            BREW_REGISTRY = BotaniaAPI.instance().getBrewRegistry();
            assert BREW_REGISTRY != null;
        }
        ExtraBotanyBrews.submitRegistrations(bind(BREW_REGISTRY));

        // recipes
        ExtraBotanyRecipeTypes.submitRecipeTypes(bind(BuiltInRegistries.RECIPE_TYPE));
        ExtraBotanyRecipeTypes.submitRecipeSerializers(bind(BuiltInRegistries.RECIPE_SERIALIZER));

        // Rest
        Registry.register(
                BuiltInRegistries.CREATIVE_MODE_TAB,
                ExtraBotanyRegistries.EXBOT_TAB_KEY,
                FabricItemGroup.builder()
                        .title(Component.translatable("itemGroup.extrabotany").withStyle((style -> style.withColor(ChatFormatting.WHITE))))
                        .hideTitle()
                        .icon(() -> new ItemStack(ExtraBotanyItems.pylon))
                        .backgroundTexture(exbotRL("textures/gui/tab_extrabotany.png"))
                        .build()
        );
        ItemGroupEvents.modifyEntriesEvent(ExtraBotanyRegistries.EXBOT_TAB_KEY)
                .register(entries -> {
                    for (Item item : this.itemsToAddToCreativeTab) {
                        if (item instanceof CustomCreativeTabContents cc) {
                            cc.addToCreativeTab(item, entries);
                        } else if (item instanceof BlockItem bi && bi.getBlock() instanceof CustomCreativeTabContents cc) {
                            cc.addToCreativeTab(item, entries);
                        } else {
                            entries.accept(item);
                        }
                    }
                });
    }

    private void registerEvents() {
        AttackEntityCallback.EVENT.register(
                (Player player, Level world, InteractionHand hand, Entity entity, EntityHitResult hitResult) -> {
                    if (player.getItemInHand(hand).getItem() instanceof IItemWithLeftClick i) {
                        return i.onLeftClick(player, entity);
                    }
                    return InteractionResult.PASS;
                }
        );
        AttackBlockCallback.EVENT.register(
                (Player player, Level world, InteractionHand hand, BlockPos pos, Direction direction) -> {
                    if (player.getItemInHand(hand).getItem() instanceof IItemWithLeftClick i) {
                        ClientXplatAbstractions.INSTANCE.sendToServer(new LeftClickPack(player.getItemInHand(hand)));
                        return InteractionResult.SUCCESS;
                    }
                    return InteractionResult.PASS;
                }
        );
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            server.getPlayerList().getPlayers().forEach(CoreGodItem::updatePlayerFlyStatus);
        });
        ServerPlayConnectionEvents.DISCONNECT.register(((handler, server) -> {
            CoreGodItem.playerLoggedOut(handler.player);
        }));
    }

    private void registerCapabilities() {
        BotaniaFabricCapabilities.MANA_ITEM.registerForItems((st, c) -> new DefaultManaItemImpl(st),
                ExtraBotanyItems.sagesManaRing, ExtraBotanyItems.ultimateHammer);
        BotaniaFabricCapabilities.RELIC.registerForItems((st, c) -> InfiniteWineItem.makeRelic(st), ExtraBotanyItems.infiniteWine);
        BotaniaFabricCapabilities.RELIC.registerForItems((st, c) -> SunRingItem.makeRelic(st), ExtraBotanyItems.sunRing);
        BotaniaFabricCapabilities.RELIC.registerForItems((st, c) -> MoonPendantItem.makeRelic(st), ExtraBotanyItems.moonPendant);
        BotaniaFabricCapabilities.RELIC.registerForItems((st, c) -> SagesManaRingItem.makeRelic(st), ExtraBotanyItems.sagesManaRing);
        BotaniaFabricCapabilities.RELIC.registerForItems((st, c) -> InfluxWaverItem.makeRelic(st), ExtraBotanyItems.influxWaver);
        BotaniaFabricCapabilities.RELIC.registerForItems((st, c) -> StarWrathItem.makeRelic(st), ExtraBotanyItems.starWrath);
        BotaniaFabricCapabilities.RELIC.registerForItems((st, c) -> TrueShadowKatanaItem.makeRelic(st), ExtraBotanyItems.trueShadowKatana);
        BotaniaFabricCapabilities.RELIC.registerForItems((st, c) -> TrueTerraBladeItem.makeRelic(st), ExtraBotanyItems.trueTerraBlade);
        BotaniaFabricCapabilities.RELIC.registerForItems((st, c) -> ExcaliberItem.makeRelic(st), ExtraBotanyItems.excaliber);
        BotaniaFabricCapabilities.RELIC.registerForItems((st, c) -> FirstFractalItem.makeRelic(st), ExtraBotanyItems.firstFractal);
        BotaniaFabricCapabilities.RELIC.registerForItems((st, c) -> FailnaughtItem.makeRelic(st), ExtraBotanyItems.fallnaught);
        BotaniaFabricCapabilities.RELIC.registerForItems((st, c) -> CameraItem.makeRelic(st), ExtraBotanyItems.camera);
        BotaniaFabricCapabilities.RELIC.registerForItems((st, c) -> CoreGodItem.makeRelic(st), ExtraBotanyItems.coreGod);
        BotaniaFabricCapabilities.RELIC.registerForItems((st, c) -> BuddhistRelicsItem.makeRelic(st), ExtraBotanyItems.buddhistRelics);
        ExtraBotanyFabricCapabilities.NATURE_ORB.registerForItems((st, c) -> new DefaultNatureOrb(st), ExtraBotanyItems.natureOrb);
    }

    private static <T> BiConsumer<T, ResourceLocation> bind(Registry<? super T> registry) {
        return (t, id) -> Registry.register(registry, id, t);
    }

    private final Set<Item> itemsToAddToCreativeTab = new LinkedHashSet<>();
    private final BiConsumer<Item, ResourceLocation> boundForItem =
            (t, id) -> {
                this.itemsToAddToCreativeTab.add(t);
                Registry.register(BuiltInRegistries.ITEM, id, t);
            };
}