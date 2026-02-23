package grasspow.extrabotany.common.item.misc;

import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class GoldClothItem extends Item {
    public GoldClothItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult interactLivingEntity(ItemStack stack, Player own, LivingEntity entity, InteractionHand hand) {
//        if (entity instanceof Player player && "soulbindUUID".equals(ItemNBTHelper.getString(stack, "soulbindUUID", "soulbindUUID"))) {
//            ItemStack newStack = new ItemStack(ExtraBotanyItems.GOLD_CLOTH.get(), 1);
//            ItemNBTHelper.setString(newStack, "soulbindUUID", player.getStringUUID());
//            if (!player.getAbilities().instabuild) stack.shrink(1);
//            own.getInventory().placeItemBackInInventory(newStack);
//            return InteractionResult.SUCCESS;
//        }
        return InteractionResult.PASS;
    }
}
