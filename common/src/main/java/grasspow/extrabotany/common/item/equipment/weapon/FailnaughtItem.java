package grasspow.extrabotany.common.item.equipment.weapon;

import grasspow.extrabotany.api.ExtraBotanyAPI;
import grasspow.extrabotany.api.IAdvancementRequirement;
import grasspow.extrabotany.common.entity.projectile.MagicArrowProjectile;
import grasspow.extrabotany.common.lib.LibAdvancementNames;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.Level;
import vazkii.botania.api.item.Relic;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import vazkii.botania.common.item.equipment.tool.bow.LivingwoodBowItem;
import vazkii.botania.common.item.relic.RelicImpl;
import vazkii.botania.xplat.XplatAbstractions;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

public class FailnaughtItem extends LivingwoodBowItem implements IAdvancementRequirement {
    private static final int MANA_PER_DAMAGE = 320;

    public FailnaughtItem(Properties prop) {
        super(prop.durability(1999).rarity(Rarity.EPIC));
    }

    public static Relic makeRelic(ItemStack stack) {
        return new RelicImpl(stack, null);
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, @org.jetbrains.annotations.Nullable T entity, Consumer<Item> breakCallback) {
        Optional<Holder.Reference<Enchantment>> enchantmentReference = entity.level().holderLookup(Registries.ENCHANTMENT).get(Enchantments.INFINITY);
        int multiply = 1;
        if (enchantmentReference.isPresent()) {
            multiply = EnchantmentHelper.getItemEnchantmentLevel(enchantmentReference.get(), stack) > 0 ? 2 : 1;
        }
        return ToolCommons.damageItemIfPossible(stack, amount, entity, MANA_PER_DAMAGE / multiply);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        var stack = player.getItemInHand(hand);
        if (ManaItemHandler.instance().requestManaExactForTool(stack, player, 800, false)) {
            player.startUsingItem(hand);
            return InteractionResultHolder.consume(stack);
        } else {
            return InteractionResultHolder.fail(stack);
        }
    }

    @Override
    public void releaseUsing(ItemStack stack, Level worldIn, LivingEntity living, int timeLeft) {
        if (living instanceof Player player) {
            int i = (int) ((getUseDuration(stack,player) - timeLeft) * 1F);
            if (i < 8)
                return;
            //max at i=110
            float rank = (i - 2.5F) / 5;

            if (ManaItemHandler.instance().requestManaExactForTool(stack, player, Math.min(800, 350 + (int) (rank * 20)), true)) {
                MagicArrowProjectile arrow = new MagicArrowProjectile(worldIn, player);
                arrow.setPos(player.getX(), player.getY() + 1.1D, player.getZ());
                arrow.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 3.0F, 1.0F);
                arrow.setDamage((int) Math.min(50, ExtraBotanyAPI.INSTANCE.calcDamage(7 + rank * 2F, player)));
                arrow.setYRot(player.getYRot());
                int j = EnchantmentHelper.getEnchantmentLevel(player.level().holderLookup(Registries.ENCHANTMENT).getOrThrow(Enchantments.POWER), player);
                if (j > 0) {
                    arrow.setDamage(arrow.getDamage() + j * 2);
                }
                arrow.setLife(Math.min(150, 5 + i * 4));

                if (!worldIn.isClientSide)
                    worldIn.addFreshEntity(arrow);

                worldIn.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.ARROW_SHOOT, SoundSource.NEUTRAL, 1.0F, 0.5F);
            }
            player.awardStat(Stats.ITEM_USED.get(this));
        }
    }

    @Override
    public float chargeVelocityMultiplier() {
        return 0.2F;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        if (!world.isClientSide && entity instanceof Player player) {
            var relic = XplatAbstractions.INSTANCE.findRelic(stack);
            if (relic != null) {
                relic.tickBinding(player);
            }
            if (stack.getDamageValue() > 0 && ManaItemHandler.instance().requestManaExactForTool(stack, player, MANA_PER_DAMAGE * 2, true))
                stack.setDamageValue(stack.getDamageValue() - 1);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        RelicImpl.addDefaultTooltip(stack, tooltipComponents);
    }

    @Override
    public boolean isValidRepairItem(ItemStack bow, ItemStack material) {
        return false;
    }

    @Override
    public String getAdvancementName() {
        return LibAdvancementNames.EGO_DEFEAT;
    }
}
