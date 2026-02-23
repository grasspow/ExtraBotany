package grasspow.extrabotany.xplat;

import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import vazkii.botania.api.ServiceUtil;

public interface ClientXplatAbstractions {
    // Yes, this forms a loop by default. Each loader overrides their own to break the loop
    default boolean isFabric() {
        return !isForge();
    }

    default boolean isForge() {
        return !isFabric();
    }

    void sendToServer(CustomPacketPayload packet);

    boolean isModLoaded(String modId);

    boolean isDevEnvironment();

    boolean isPhysicalClient();

    String getExtraBotanyVersion();

    ClientXplatAbstractions INSTANCE = ServiceUtil.findService(ClientXplatAbstractions.class, null);
}
