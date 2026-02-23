package grasspow.extrabotany.fabric.client;

import grasspow.extrabotany.client.core.handler.MiscellaneousModels;
import grasspow.extrabotany.fabric.network.FabricPacketHandler;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.model.loading.v1.ModelLoadingPlugin;
import net.minecraft.client.Minecraft;

public class FabricClientInitializer implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
		FabricPacketHandler.initClient();

        // Blocks and Items
		ModelLoadingPlugin.register(pluginContext -> {
			MiscellaneousModels.INSTANCE.onModelRegister(Minecraft.getInstance().getResourceManager(), pluginContext::addModels);
			pluginContext.modifyModelAfterBake().register((bakedModel, context) -> MiscellaneousModels.INSTANCE.modifyModelAfterbake(bakedModel, context.resourceId()));
		});
    }
}