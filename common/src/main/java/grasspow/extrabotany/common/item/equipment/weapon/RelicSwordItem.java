package grasspow.extrabotany.common.item.equipment.weapon;

import grasspow.extrabotany.api.item.IItemWithLeftClick;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.CustomDamageItem;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import vazkii.botania.common.item.relic.RelicImpl;
import vazkii.botania.xplat.XplatAbstractions;

import java.util.List;
import java.util.function.Consumer;

public abstract class RelicSwordItem extends SwordItem implements IItemWithLeftClick, CustomDamageItem {
    public RelicSwordItem(Tier tier,Properties props) {
        super(tier,props);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        RelicImpl.addDefaultTooltip(stack, tooltipComponents);
    }

    public static BlockHitResult raytraceFromEntity(Entity e, double distance, boolean fluids) {
        return (BlockHitResult) e.pick(distance, 1, fluids);
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, @Nullable T entity, Consumer<Item> breakCallback) {
        return ToolCommons.damageItemIfPossible(stack, amount, entity, getManaPerDamage());
    }

    @Override
    public InteractionResult onLeftClick(Player player, Entity target) {
        if (!player.level().isClientSide() && !player.getMainHandItem().isEmpty()
                && player.getMainHandItem().getItem() == this
                && player.getAttackStrengthScale(0) == 1
                && ManaItemHandler.instance().requestManaExactForTool(player.getMainHandItem(), player, getManaPerDamage(), true)) {
            attack(player, target);
            player.awardStat(Stats.ITEM_USED.get(this));
        }
        return InteractionResult.PASS;
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (!level.isClientSide && entity instanceof Player player) {
            var relic = XplatAbstractions.INSTANCE.findRelic(stack);
            if (relic != null) {
                relic.tickBinding(player);
            }
        }
    }

    public void attack(LivingEntity player, Entity target) {
        attack(player, target, 1, 1D, 1F);
    }

    public abstract int getManaPerDamage();

    public abstract void attack(LivingEntity player, Entity target, int times, double speedTime, float damageTime);
}
