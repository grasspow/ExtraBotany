package grasspow.extrabotany.common.item.equipment.weapon;

import grasspow.extrabotany.api.item.IItemWithLeftClick;
import net.minecraft.network.chat.Component;
import net.minecraft.stats.Stats;
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
    public RelicSwordItem(Tier pTier, int pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pProperties.rarity(Rarity.EPIC));
//        MinecraftForge.EVENT_BUS.addListener(this::leftClick);
//        MinecraftForge.EVENT_BUS.addListener(this::leftClickBlock);
//        MinecraftForge.EVENT_BUS.addListener(this::attackEntity);
    }

//    public void attackEntity(AttackEntityEvent evt) {
//        if (!evt.getEntity().level().isClientSide()) {
//            onLeftClick(evt.getEntity(), evt.getTarget());
//        }
//    }
//
//    public void leftClick(PlayerInteractEvent.LeftClickEmpty evt) {
//        if (!evt.getItemStack().isEmpty() && evt.getItemStack().getItem() == this) {
//            ClientExbotXplatAbs.INSTANCE.sendToServer(new LeftClickPack(evt.getItemStack()));
//        }
//    }
//
//    public void leftClickBlock(PlayerInteractEvent.LeftClickBlock evt) {
//        if (evt.getEntity().level().isClientSide() && !evt.getItemStack().isEmpty() && evt.getItemStack().getItem() == this) {
//            ClientExbotXplatAbs.INSTANCE.sendToServer(new LeftClickPack(evt.getItemStack()));
//        }
//    }

//    @Override
//    public int getEntityLifespan(ItemStack itemStack, Level level) {
//        return Integer.MAX_VALUE;
//    }

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
    public void onLeftClick(Player player, Entity target) {
        if (!player.level().isClientSide() && !player.getMainHandItem().isEmpty()
                && player.getMainHandItem().getItem() == this
                && player.getAttackStrengthScale(0) == 1
                && ManaItemHandler.instance().requestManaExactForTool(player.getMainHandItem(), player, getManaPerDamage(), true)) {
            attack(player, target);
            player.awardStat(Stats.ITEM_USED.get(this));
        }
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
