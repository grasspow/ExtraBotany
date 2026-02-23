package grasspow.extrabotany.common.network.server;

import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;

public class FlamescionStrengthenPack implements CustomPacketPayload {
    public static final FlamescionStrengthenPack INSTANCE = new FlamescionStrengthenPack();
    public static final Type<FlamescionStrengthenPack> ID = new Type<>(exbotRL("fs"));

    public static final StreamCodec<ByteBuf, FlamescionStrengthenPack> STREAM_CODEC = StreamCodec.unit(INSTANCE);

    public void handle(MinecraftServer server, ServerPlayer player) {
        server.execute(() -> {
//            ((FlamescionWeaponItem) (ExtraBotanyItems.FLAMESCION_WEAPON.get())).tryStrengthenAttack(player);
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
