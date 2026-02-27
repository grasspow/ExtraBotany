package grasspow.extrabotany.neoforge.client;

import grasspow.extrabotany.api.ExtraBotanyAPI;
import grasspow.extrabotany.client.ExtraBotanyItemProperties;
import grasspow.extrabotany.client.core.handler.MiscellaneousModels;
import grasspow.extrabotany.client.core.proxy.ClientProxy;
import grasspow.extrabotany.client.model.ExtraBotanyLayerDefinitions;
import grasspow.extrabotany.client.model.armor.ArmorModels;
import grasspow.extrabotany.client.render.BlockRenderLayers;
import grasspow.extrabotany.client.render.ColorHandler;
import grasspow.extrabotany.client.render.entity.ExtraBotanyEntityRenderers;
import grasspow.extrabotany.common.item.equipment.BuddhistRelicsItem;
import grasspow.extrabotany.common.item.equipment.armor.MikuArmorItem;
import grasspow.extrabotany.common.network.server.BuddhistChangePack;
import grasspow.extrabotany.xplat.ClientXplatAbstractions;
import net.minecraft.client.Minecraft;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.neoforge.client.event.*;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import net.neoforged.neoforge.client.extensions.common.RegisterClientExtensionsEvent;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@EventBusSubscriber(modid = ExtraBotanyAPI.MODID, value = Dist.CLIENT)
public class NeoForgeClientInitializer {

    @SubscribeEvent
    public static void clientInit(FMLClientSetupEvent e){
        BlockRenderLayers.init(ItemBlockRenderTypes::setRenderLayer);
    }

    @SubscribeEvent
    public static void registerKeys(RegisterKeyMappingsEvent e) {
        ClientProxy.initKeybindings(e::register);
    }

    //key input
    @SubscribeEvent
    public static void onKeyPress(ClientTickEvent.Post event) {
        Player player = Minecraft.getInstance().player;
        if (player != null && ClientProxy.BUDDHIST_RELICS_MORPH.consumeClick()) {
            if (!BuddhistRelicsItem.relicShift(player.getMainHandItem()).isEmpty()) {
                ClientXplatAbstractions.INSTANCE.sendToServer(BuddhistChangePack.INSTANCE);
            }
        }
    }

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item evt) {
        ColorHandler.submitItems(evt::register);
    }

    @SubscribeEvent
    public static void onModelRegister(ModelEvent.RegisterAdditional evt) {
		var resourceManager = Minecraft.getInstance().getResourceManager();
		MiscellaneousModels.INSTANCE.onModelRegister(resourceManager,
				id -> evt.register(ModelResourceLocation.standalone(id)));
        ExtraBotanyItemProperties.init((item, id, prop) -> ItemProperties.register(item.asItem(), id, prop));
    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions evt) {
        ExtraBotanyLayerDefinitions.init(evt::registerLayerDefinition);
    }

    @SubscribeEvent
    public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
        ExtraBotanyEntityRenderers.registerBlockEntityRenderers(event::registerBlockEntityRenderer);
        ExtraBotanyEntityRenderers.registerEntityRenderers(event::registerEntityRenderer);
    }

    @SubscribeEvent
	public static void registerClientExtensions(RegisterClientExtensionsEvent event) {

		List<Item> armorItems = new ArrayList<>();
		for (var entry : BuiltInRegistries.ITEM.entrySet()) {
			Item item = entry.getValue();
			ResourceLocation id = entry.getKey().location();
			if (item instanceof MikuArmorItem armor && id.getNamespace().equals(ExtraBotanyAPI.MODID)) {
				armorItems.add(armor);
			}
		}
        event.registerItem(
				new IClientItemExtensions() {
					@Override
					public HumanoidModel<?> getHumanoidArmorModel(LivingEntity living, ItemStack stack, EquipmentSlot slot, HumanoidModel<?> defaultModel) {
						return Objects.requireNonNull(ArmorModels.get(stack));
					}
				},
				armorItems.toArray(Item[]::new)
		);
    }
    @SubscribeEvent
    public static void onModelBake(ModelEvent.ModifyBakingResult evt) {
        MiscellaneousModels.INSTANCE.onModelBake(evt.getModelBakery(), evt.getModels());
    }
}
