package grasspow.extrabotany.common.item.equipment.armor;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.DamageType;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vazkii.botania.api.mana.ManaItemHandler;

import java.util.ArrayList;
import java.util.List;

import static grasspow.extrabotany.common.lib.CommonHelper.clearHarmfulPotions;

public class MaidArmorHelmetItem extends MaidArmorItem {
    public List<ResourceKey<DamageType>> source = new ArrayList<>();

    public MaidArmorHelmetItem(Type type,Properties props) {
        super(Type.HELMET, props);
        source.add(DamageTypes.FALLING_ANVIL);
        source.add(DamageTypes.CACTUS);
        source.add(DamageTypes.DROWN);
        source.add(DamageTypes.FALL);
        source.add(DamageTypes.FALLING_BLOCK);
        source.add(DamageTypes.IN_FIRE);
        source.add(DamageTypes.LAVA);
        source.add(DamageTypes.ON_FIRE);
        source.add(DamageTypes.LIGHTNING_BOLT);
        source.add(DamageTypes.FLY_INTO_WALL);
        source.add(DamageTypes.HOT_FLOOR);
        source.add(DamageTypes.SWEET_BERRY_BUSH);
    }


//    @SubscribeEvent
//    public void onEntityAttacked(LivingHurtEvent event) {
//        Entity attacker = event.getSource().getEntity();
//        LivingEntity target = event.getEntity();
//        if (attacker instanceof Player player && target != null && target != player) {
//            if (hasArmorSet(player)) {
//                if (player.getMainHandItem().isEmpty() && player.getOffhandItem().isEmpty()
//                        && ManaItemHandler.instance().requestManaExactForTool(new ItemStack(this), player, 200, true))
//                    event.setAmount(event.getAmount() + 8F);
//                if (player.isHurt()
//                        && ManaItemHandler.instance().requestManaExactForTool(new ItemStack(this), player, 80, true))
//                    player.heal(event.getAmount() / 10F);
//            }
//        }
//    }

//    @SubscribeEvent(priority = EventPriority.LOW)
//    public void onPlayerAttacked(LivingHurtEvent event) {
//        Entity target = event.getEntity();
//        if (target instanceof Player player) {
//            if (hasArmorSet(player)) {
//                event.getSource().is(DamageTypes.FALL);
//                if (!source.stream().filter(event.getSource()::is).toList().isEmpty()) {
//                    event.setAmount(0F);
//                }
//                if (event.getSource().is(DamageTypes.MAGIC)) {
//                    event.setAmount(event.getAmount() * 0.75F);
//                }
//            }
//        }
//    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (entity instanceof Player player && hasArmorSet(player) && !player.level().isClientSide()) {
            ManaItemHandler.instance().dispatchManaExact(stack, player, 1, true);
            if (player.isHurt() && player.tickCount % 40 == 0
                    && ManaItemHandler.instance().requestManaExactForTool(stack, player, 20, true))
                player.heal(1F);
            if (player.tickCount % 40 == 0 && ManaItemHandler.instance().requestManaExactForTool(stack, player, 20, true))
                clearHarmfulPotions(stack, player);
        }
    }
}
