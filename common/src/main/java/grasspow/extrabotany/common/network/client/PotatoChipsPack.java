package grasspow.extrabotany.common.network.client;

import grasspow.extrabotany.common.item.ExtraBotanyItems;
import io.netty.buffer.ByteBuf;
import net.minecraft.client.Minecraft;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.world.item.ItemStack;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;

public class PotatoChipsPack implements CustomPacketPayload {
    public static final PotatoChipsPack INSTANCE = new PotatoChipsPack();
    public static final Type<PotatoChipsPack> ID = new Type<>(exbotRL("pc"));

    public static final StreamCodec<ByteBuf, PotatoChipsPack> STREAM_CODEC = StreamCodec.unit(INSTANCE);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }

    public static class Handler {
        public static void handle(PotatoChipsPack packet) {
            Minecraft.getInstance().gameRenderer.displayItemActivation(new ItemStack(ExtraBotanyItems.potatoChips));
        }
    }
}
