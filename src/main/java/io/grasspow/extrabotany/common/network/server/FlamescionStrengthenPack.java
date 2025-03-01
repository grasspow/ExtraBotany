package io.grasspow.extrabotany.common.network.server;

import io.grasspow.extrabotany.common.item.ExtraBotanyItems;
import io.grasspow.extrabotany.common.item.equipment.weapon.FlamescionWeaponItem;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.level.ServerPlayer;
import vazkii.botania.network.BotaniaPacket;

import static io.grasspow.extrabotany.common.libs.CommonHelper.resId;

public class FlamescionStrengthenPack implements BotaniaPacket {
    public static final FlamescionStrengthenPack INSTANCE = new FlamescionStrengthenPack();
    public static final ResourceLocation ID = resId("lc");

    public void handle(MinecraftServer server, ServerPlayer player) {
        server.execute(() -> {
            ((FlamescionWeaponItem) (ExtraBotanyItems.FLAMESCION_WEAPON.get())).tryStrengthenAttack(player);
        });
    }

    @Override
    public void encode(FriendlyByteBuf buf) {

    }

    public static FlamescionStrengthenPack decode(FriendlyByteBuf buf) {
        return INSTANCE;
    }

    @Override
    public ResourceLocation getFabricId() {
        return ID;
    }
}
