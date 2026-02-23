package grasspow.extrabotany.common.item.misc;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;

public class NightmareFuelItem extends Item {

    public NightmareFuelItem(Properties prop) {
        super(prop);
    }

    @NotNull
    @Override
    public ItemStack finishUsingItem(@NotNull ItemStack pStack, @NotNull Level world, @NotNull LivingEntity living) {
        return super.finishUsingItem(pStack, world, living);
//        if (living instanceof ServerPlayer serverplayer) {
//            CriteriaTriggers.CONSUME_ITEM.trigger(serverplayer, pStack);
//            serverplayer.awardStat(Stats.ITEM_USED.get(this));
//        }
//        if (pStack.isEmpty()) {
//            return new ItemStack(ExtraBotanyItems.SPIRIT_FUEL.get());
//        } else {
//            if (living instanceof Player player && !player.getAbilities().instabuild) {
//                ItemStack itemstack = new ItemStack(ExtraBotanyItems.SPIRIT_FUEL.get());
//                if (!player.getInventory().add(itemstack)) {
//                    player.drop(itemstack, false);
//                }
//            }
//            return pStack;
//        }
    }

}
