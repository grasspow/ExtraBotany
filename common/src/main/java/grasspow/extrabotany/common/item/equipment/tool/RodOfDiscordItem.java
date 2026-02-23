package grasspow.extrabotany.common.item.equipment.tool;

import net.minecraft.core.BlockPos;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import vazkii.botania.api.mana.ManaItemHandler;

public class RodOfDiscordItem extends Item {

    private static final int MANA_PER_DAMAGE = 2000;

    public RodOfDiscordItem(Properties properties) {
        super(properties.durability(81));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        HitResult hitResult = player.pick(64F, 1, false);
        ItemStack stack = player.getItemInHand(hand);
        if (hitResult instanceof BlockHitResult rtr && rtr.getType() != HitResult.Type.MISS && ManaItemHandler.instance().requestManaExactForTool(stack, player, MANA_PER_DAMAGE, true)) {
            BlockPos pos = rtr.getBlockPos();
            player.setPos(pos.getX(), pos.getY() + 1, pos.getZ());
            if (!level.isClientSide())
                level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ENDERMAN_TELEPORT, SoundSource.PLAYERS, 1F, 3F);
            player.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100));
            if (stack.getDamageValue() > 0) {
                float health = Math.max(1F, player.getHealth() - player.getMaxHealth() / 6F);
                player.setHealth(health);
            }
            stack.setDamageValue(stack.getMaxDamage() - 1);
        }
        return InteractionResultHolder.success(stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entityIn, int itemSlot, boolean isSelected) {
        if (!world.isClientSide() && stack.getDamageValue() > 0) {
            stack.setDamageValue(stack.getDamageValue() - 1);
        }
    }

}
