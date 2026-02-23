package grasspow.extrabotany.common.item.equipment.tool;

import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.block.entity.BlockEntity;
import vazkii.botania.api.block_entity.BindableSpecialFlowerBlockEntity;
import vazkii.botania.api.mana.ManaReceiver;

public class ManaReader extends Item {
    public ManaReader(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        Player player = context.getPlayer();
        if (player == null) return InteractionResult.PASS;
        BlockEntity blockEntity = context.getLevel().getBlockEntity(context.getClickedPos());
        if (blockEntity instanceof ManaReceiver receiver) {
            int mana = receiver.getCurrentMana();
            if (context.getLevel().isClientSide()) {
                player.sendSystemMessage(Component.literal(String.format("Mana:%d", mana)));
            }
            return InteractionResult.SUCCESS;
        } else if (blockEntity instanceof BindableSpecialFlowerBlockEntity flower) {
            int mana = flower.getMana();
            if (context.getLevel().isClientSide()) {
                player.sendSystemMessage(Component.literal(String.format("Mana:%d", mana)));
            }
            return InteractionResult.SUCCESS;
        } else {
            return InteractionResult.PASS;
        }
    }
}
