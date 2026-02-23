package grasspow.extrabotany.neoforge.network;

import grasspow.extrabotany.network.client.PotatoChipsPack;
import grasspow.extrabotany.network.server.BuddhistChangePack;
import grasspow.extrabotany.network.server.FlamescionStrengthenPack;
import grasspow.extrabotany.network.server.LeftClickPack;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.neoforged.neoforge.network.event.RegisterPayloadHandlersEvent;
import net.neoforged.neoforge.network.handling.IPayloadHandler;
import net.neoforged.neoforge.network.registration.PayloadRegistrar;

import java.util.function.Consumer;


public class NeoForgePacketHandler {

    public static void registerPayloadHandlers(final RegisterPayloadHandlersEvent event) {

        PayloadRegistrar registrar = event.registrar("1");

        registrar.playToServer(LeftClickPack.ID, LeftClickPack.STREAM_CODEC, makeServerBoundHandler(LeftClickPack::handle));
        registrar.playToServer(BuddhistChangePack.ID, BuddhistChangePack.STREAM_CODEC, makeServerBoundHandler(BuddhistChangePack::handle));
        registrar.playToServer(FlamescionStrengthenPack.ID, FlamescionStrengthenPack.STREAM_CODEC, makeServerBoundHandler(FlamescionStrengthenPack::handle));

        registrar.playToClient(PotatoChipsPack.ID, PotatoChipsPack.STREAM_CODEC, makeClientBoundHandler(PotatoChipsPack.Handler::handle));

    }

    private static <T extends CustomPacketPayload> IPayloadHandler<T> makeServerBoundHandler(vazkii.botania.network.TriConsumer<T, MinecraftServer, ServerPlayer> handler) {
        return (m, ctx) -> handler.accept(m, ctx.player().getServer(), (ServerPlayer) ctx.player());
    }

    private static <T extends CustomPacketPayload> IPayloadHandler<T> makeClientBoundHandler(Consumer<T> consumer) {
        return (m, ctx) -> consumer.accept(m);
    }
}
