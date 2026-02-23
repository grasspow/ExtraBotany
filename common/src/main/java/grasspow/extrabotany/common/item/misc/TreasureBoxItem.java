package grasspow.extrabotany.common.item.misc;

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
//            player.drop(new ItemStack(ExtraBotanyItems.REWARD_BAG_A.get(), 32), true).setNoPickUpDelay();
//            player.drop(new ItemStack(ExtraBotanyItems.REWARD_BAG_B.get(), 16), true).setNoPickUpDelay();
//            player.drop(new ItemStack(ExtraBotanyItems.REWARD_BAG_C.get(), 10), true).setNoPickUpDelay();
//            player.drop(new ItemStack(ExtraBotanyItems.REWARD_BAG_D.get(), 10), true).setNoPickUpDelay();
//            player.drop(new ItemStack(ExtraBotanyItems.HERO_MEDAL.get()), true).setNoPickUpDelay();
            if (!player.getAbilities().instabuild) {
                itemstack.shrink(1);
            }
        }
        return InteractionResultHolder.pass(itemstack);
    }

}
