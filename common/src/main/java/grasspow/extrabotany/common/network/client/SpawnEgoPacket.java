package grasspow.extrabotany.common.network.client;

import grasspow.extrabotany.common.entity.ego.EGO;
import net.minecraft.client.Minecraft;
import net.minecraft.core.BlockPos;
import net.minecraft.core.UUIDUtil;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.entity.Entity;

import java.util.UUID;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;

public record SpawnEgoPacket(int entityId, int playerCount,
                             BlockPos source, UUID bossInfoId) implements CustomPacketPayload {

	public static final Type<SpawnEgoPacket> ID = new Type<>(exbotRL("spg"));
	public static final StreamCodec<RegistryFriendlyByteBuf, SpawnEgoPacket> STREAM_CODEC = StreamCodec.composite(
			ByteBufCodecs.VAR_INT, SpawnEgoPacket::entityId,
			ByteBufCodecs.VAR_INT, SpawnEgoPacket::playerCount,
			BlockPos.STREAM_CODEC, SpawnEgoPacket::source,
			UUIDUtil.STREAM_CODEC, SpawnEgoPacket::bossInfoId,
            SpawnEgoPacket::new
	);

	@Override
	public Type<SpawnEgoPacket> type() {
		return ID;
	}

	public static class Handler {
		public static void handle(SpawnEgoPacket packet) {
			int playerCount = packet.playerCount();
			BlockPos source = packet.source();
			UUID bossInfoUuid = packet.bossInfoId();

			Minecraft.getInstance().execute(() -> {
				var player = Minecraft.getInstance().player;
				if (player != null) {
					Entity e = player.level().getEntity(packet.entityId());
					if (e instanceof EGO) {
                        EGO dopple = (EGO) e;
						dopple.readSpawnData(playerCount, source, bossInfoUuid);
					}
				}
			});
		}
	}
}
