package grasspow.extrabotany.network.server;

import grasspow.extrabotany.common.item.equipment.BuddhistRelicsItem;
import io.netty.buffer.ByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EquipmentSlot;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;

public class BuddhistChangePack implements CustomPacketPayload {
    public static final BuddhistChangePack INSTANCE = new BuddhistChangePack();
    public static final Type<BuddhistChangePack> ID = new Type<>(exbotRL("bc"));
	public static final StreamCodec<ByteBuf, BuddhistChangePack> STREAM_CODEC = StreamCodec.unit(INSTANCE);

    public void handle(MinecraftServer server, ServerPlayer player) {
        server.execute(() -> {
            if (!BuddhistRelicsItem.relicShift(player.getMainHandItem()).isEmpty())
                player.setItemSlot(EquipmentSlot.MAINHAND, BuddhistRelicsItem.relicShift(player.getMainHandItem()));
        });
    }

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }
}
