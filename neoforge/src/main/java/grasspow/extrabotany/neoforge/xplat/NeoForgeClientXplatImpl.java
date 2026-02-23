package grasspow.extrabotany.neoforge.xplat;

import grasspow.extrabotany.xplat.ClientXplatAbstractions;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.neoforged.neoforge.network.PacketDistributor;

public class NeoForgeClientXplatImpl implements ClientXplatAbstractions {
    @Override
    public void sendToServer(CustomPacketPayload packet) {
        PacketDistributor.sendToServer(packet);
    }

    @Override
    public boolean isModLoaded(String modId) {
        return false;
    }

    @Override
    public boolean isDevEnvironment() {
        return false;
    }

    @Override
    public boolean isPhysicalClient() {
        return false;
    }
}
