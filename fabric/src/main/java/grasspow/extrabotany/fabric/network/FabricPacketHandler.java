package grasspow.extrabotany.fabric.network;

import grasspow.extrabotany.common.network.client.PotatoChipsPack;
import grasspow.extrabotany.common.network.client.SpawnEgoPacket;
import grasspow.extrabotany.common.network.server.BuddhistChangePack;
import grasspow.extrabotany.common.network.server.FlamescionStrengthenPack;
import grasspow.extrabotany.common.network.server.LeftClickPack;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import vazkii.botania.network.TriConsumer;

import java.util.function.Consumer;

public final class FabricPacketHandler {


    public static void init() {
		PayloadTypeRegistry.playC2S().register(LeftClickPack.ID, LeftClickPack.STREAM_CODEC);
		PayloadTypeRegistry.playC2S().register(BuddhistChangePack.ID, BuddhistChangePack.STREAM_CODEC);
		PayloadTypeRegistry.playC2S().register(FlamescionStrengthenPack.ID, FlamescionStrengthenPack.STREAM_CODEC);

		PayloadTypeRegistry.playS2C().register(PotatoChipsPack.ID, PotatoChipsPack.STREAM_CODEC);
		PayloadTypeRegistry.playS2C().register(SpawnEgoPacket.ID, SpawnEgoPacket.STREAM_CODEC);

		ServerPlayNetworking.registerGlobalReceiver(LeftClickPack.ID, makeServerBoundHandler(LeftClickPack::handle));
		ServerPlayNetworking.registerGlobalReceiver(BuddhistChangePack.ID, makeServerBoundHandler(BuddhistChangePack::handle));
		ServerPlayNetworking.registerGlobalReceiver(FlamescionStrengthenPack.ID, makeServerBoundHandler(FlamescionStrengthenPack::handle));
    }
    private static <T extends CustomPacketPayload> ServerPlayNetworking.PlayPayloadHandler<T> makeServerBoundHandler(TriConsumer<T, MinecraftServer, ServerPlayer> handle) {
        return (payload, context) -> handle.accept(payload, context.server(), context.player());
    }
    public static void initClient() {
		ClientPlayNetworking.registerGlobalReceiver(PotatoChipsPack.ID, makeClientBoundHandler(PotatoChipsPack.Handler::handle));
		ClientPlayNetworking.registerGlobalReceiver(SpawnEgoPacket.ID, makeClientBoundHandler(SpawnEgoPacket.Handler::handle));
    }
    private static <T extends CustomPacketPayload> ClientPlayNetworking.PlayPayloadHandler<T> makeClientBoundHandler(Consumer<T> handler) {
        return (payload, context) -> handler.accept(payload);
    }
    private FabricPacketHandler(){}
}
