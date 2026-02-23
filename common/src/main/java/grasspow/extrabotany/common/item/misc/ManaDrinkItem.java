package grasspow.extrabotany.common.item.misc;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.UseAnim;
import net.minecraft.world.level.Level;
import vazkii.botania.api.mana.ManaItemHandler;

public class ManaDrinkItem extends Item {
    public ManaDrinkItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public UseAnim getUseAnimation(ItemStack pStack) {
        return UseAnim.DRINK;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity living) {
        super.finishUsingItem(stack, level, living);
        Player player = living instanceof Player ? (Player) living : null;
        if (player != null) {
            ManaItemHandler.instance().dispatchManaExact(stack, player, 10000, true);
            if (!player.getAbilities().instabuild) {
                stack.shrink(1);
//                player.getInventory().placeItemBackInInventory(new ItemStack(ExtraBotanyItems.EMPTY_BOTTLE.get()));
            }
        }
        return stack;
    }
}
