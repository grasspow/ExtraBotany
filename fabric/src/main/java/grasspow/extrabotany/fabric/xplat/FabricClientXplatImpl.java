package grasspow.extrabotany.fabric.xplat;

import grasspow.extrabotany.api.ExtraBotanyAPI;
import grasspow.extrabotany.xplat.ClientXplatAbstractions;
import net.fabricmc.api.EnvType;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;

public class FabricClientXplatImpl implements ClientXplatAbstractions {
    @Override
    public boolean isFabric() {
        return true;
    }
    
    @Override
    public void sendToServer(CustomPacketPayload packet) {
        ClientPlayNetworking.send(packet);
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public boolean isPhysicalClient() {
        return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT;
    }

    @Override
    public String getExtraBotanyVersion() {
        return FabricLoader.getInstance().getModContainer(ExtraBotanyAPI.MODID).orElseThrow()
				.getMetadata().getVersion().getFriendlyString();
    }
}
