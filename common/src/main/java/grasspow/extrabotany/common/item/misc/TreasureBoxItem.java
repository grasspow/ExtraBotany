package grasspow.extrabotany.common.item.misc;

import grasspow.extrabotany.common.item.ExtraBotanyItems;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;

public class TreasureBoxItem extends Item {
    public TreasureBoxItem(Properties prop) {
        super(prop);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack itemstack = player.getItemInHand(hand);
        if (!level.isClientSide) {
            player.drop(new ItemStack(ExtraBotanyItems.rewardBagA, 32), true).setNoPickUpDelay();
            player.drop(new ItemStack(ExtraBotanyItems.rewardBagB, 16), true).setNoPickUpDelay();
            player.drop(new ItemStack(ExtraBotanyItems.rewardBagC, 10), true).setNoPickUpDelay();
            player.drop(new ItemStack(ExtraBotanyItems.rewardBagD, 10), true).setNoPickUpDelay();
            player.drop(new ItemStack(ExtraBotanyItems.heroMedal), true).setNoPickUpDelay();
            if (!player.isCreative()) {
                itemstack.shrink(1);
            }
        }
        return InteractionResultHolder.pass(itemstack);
    }

}
