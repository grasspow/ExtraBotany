package grasspow.extrabotany.common.network.server;

import grasspow.extrabotany.api.IAdvancementRequirement;
import grasspow.extrabotany.api.item.IItemWithLeftClick;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;

public record LeftClickPack(ItemStack stack) implements CustomPacketPayload {

    public static final Type<LeftClickPack> ID = new Type<>(exbotRL("lc"));

    public static final StreamCodec<RegistryFriendlyByteBuf, LeftClickPack> STREAM_CODEC = ItemStack.STREAM_CODEC
            .map(LeftClickPack::new,LeftClickPack::stack);

    @Override
    public Type<? extends CustomPacketPayload> type() {
        return ID;
    }

    public void handle(MinecraftServer server, ServerPlayer player) {
        server.execute(() -> {
            if (!stack.isEmpty()) {
                if (stack.getItem() instanceof IAdvancementRequirement r) {
//                    if (!checkAdvancement(player, LibMisc.MOD_ID, r.getAdvancementName())) return;
                }
                if (stack.getItem() instanceof IItemWithLeftClick click) {
                    click.onLeftClick(player, null);
                }
            }
//            if (!EquipmentHandler.findOrEmpty(ExtraBotanyItems.JINGWEI_FEATHER, player).isEmpty()) {
//                ((JingweiFeatherItem) ExtraBotanyItems.JINGWEI_FEATHER).onLeftClick(player, null);
//            }
        });
    }
}
