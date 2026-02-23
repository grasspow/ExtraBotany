package grasspow.extrabotany.neoforge.client;

import grasspow.extrabotany.api.ExtraBotanyAPI;
import grasspow.extrabotany.client.ExtraBotanyItemProperties;
import grasspow.extrabotany.client.core.handler.MiscellaneousModels;
import grasspow.extrabotany.client.model.ExtraBotanyLayerDefinitions;
import grasspow.extrabotany.client.render.ColorHandler;
import grasspow.extrabotany.client.render.entity.ExtraBotanyEntityRenderers;
import net.minecraft.client.renderer.item.ItemProperties;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;
import net.neoforged.neoforge.client.event.ModelEvent;
import net.neoforged.neoforge.client.event.RegisterColorHandlersEvent;

@EventBusSubscriber(modid = ExtraBotanyAPI.MODID,value = Dist.CLIENT)
public class NeoForgeClientInitializer {

    @SubscribeEvent
    public static void registerItemColors(RegisterColorHandlersEvent.Item evt) {
        ColorHandler.submitItems(evt::register);
    }

    @SubscribeEvent
    public static void onModelRegister(ModelEvent.RegisterAdditional evt) {
        ExtraBotanyItemProperties.init((item, id, prop) -> ItemProperties.register(item.asItem(), id, prop));
    }

    @SubscribeEvent
    public static void registerEntityLayers(EntityRenderersEvent.RegisterLayerDefinitions evt) {
        ExtraBotanyLayerDefinitions.init(evt::registerLayerDefinition);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        ExtraBotanyEntityRenderers.registerBlockEntityRenderers(event::registerBlockEntityRenderer);
        ExtraBotanyEntityRenderers.registerEntityRenderers(event::registerEntityRenderer);
    }


    @SubscribeEvent
    public static void onModelBake(ModelEvent.ModifyBakingResult evt) {
        MiscellaneousModels.INSTANCE.onModelBake(evt.getModelBakery(), evt.getModels());
    }
}
