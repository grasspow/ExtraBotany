package grasspow.extrabotany.fabric.client;

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
import grasspow.extrabotany.fabric.network.FabricPacketHandler;
import grasspow.extrabotany.xplat.ClientXplatAbstractions;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientLifecycleEvents;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.Item;

import java.util.LinkedHashMap;
import java.util.Map;

public class FabricClientInitializer implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        FabricPacketHandler.initClient();

        // Blocks and Items
        ModelLoadingPlugin.register(pluginContext -> {
            MiscellaneousModels.INSTANCE.onModelRegister(Minecraft.getInstance().getResourceManager(), pluginContext::addModels);
            pluginContext.modifyModelAfterBake().register((bakedModel, context) -> MiscellaneousModels.INSTANCE.modifyModelAfterbake(bakedModel, context.resourceId()));
        });
		BlockRenderLayers.init(BlockRenderLayerMap.INSTANCE::putBlock);
        ExtraBotanyItemProperties.init((i, id, propGetter) -> ItemProperties.register(i.asItem(), id, propGetter));
        ExtraBotanyLayerDefinitions.init((loc, supplier) -> EntityModelLayerRegistry.registerModelLayer(loc, supplier::get));
        ExtraBotanyEntityRenderers.registerBlockEntityRenderers(BlockEntityRenderers::register);
        ExtraBotanyEntityRenderers.registerEntityRenderers(EntityRendererRegistry::register);

        //events
        ClientLifecycleEvents.CLIENT_STARTED.register(this::loadComplete);
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            Player player = client.player;
            while (ClientProxy.BUDDHIST_RELICS_MORPH.consumeClick()) {
                if (player!=null && !BuddhistRelicsItem.relicShift(player.getMainHandItem()).isEmpty()) {
                    ClientXplatAbstractions.INSTANCE.sendToServer(BuddhistChangePack.INSTANCE);
                }
            }
        });

        registerArmors();

        //etc
        ClientProxy.initKeybindings(KeyBindingHelper::registerKeyBinding);
    }

    private static void registerArmors() {
		Map<Item, ArmorMaterial.Layer> armors = new LinkedHashMap<>();
		for (var entry : BuiltInRegistries.ITEM.entrySet()) {
			Item item = entry.getValue();
			ResourceLocation id = entry.getKey().location();
			if (item instanceof MikuArmorItem armor
					&& id.getNamespace().equals(ExtraBotanyAPI.MODID)) {
				armors.put(armor, armor.getMaterial().value().layers().getFirst());
			}
		}

		ArmorRenderer renderer = (matrices, vertexConsumers, stack, entity, slot, light, contextModel) -> {
            MikuArmorItem armor = (MikuArmorItem) stack.getItem();
			var model = ArmorModels.get(stack);
			var texture = armor.getArmorTexture(stack, entity, slot, armors.get(stack.getItem()), false);
			if (model != null) {
				contextModel.copyPropertiesTo(model);
				ArmorRenderer.renderPart(matrices, vertexConsumers, light, stack, model, texture);
			}
		};
		ArmorRenderer.register(renderer, armors.keySet().toArray(Item[]::new));
	}

    private void loadComplete(Minecraft mc) {
        ColorHandler.submitItems(ColorProviderRegistry.ITEM::register);
    }
}