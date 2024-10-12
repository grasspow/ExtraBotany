package io.grasspow.extrabotany.common.event;

import io.grasspow.extrabotany.common.item.ExtraBotanyItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.living.LivingAttackEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import vazkii.botania.common.handler.EquipmentHandler;

@Mod.EventBusSubscriber
public class DamageEventHandler {

    @SubscribeEvent
    public static void onLivingAttack(LivingAttackEvent event) {
        if (!checkPassable(event.getEntity(), event.getSource().getEntity()) && !event.isCanceled()) {
            event.setCanceled(true);
        }
    }

    public static boolean checkPassable(LivingEntity target, Entity attacker) {
        if (target == attacker) {
            return false;
        }
        if (attacker instanceof Player player) {
            boolean sourceEquipped = !EquipmentHandler.findOrEmpty(ExtraBotanyItems.PEACE_AMULET.get(), player).isEmpty();
            if (target instanceof Player targetPlayer) {
                return !sourceEquipped && EquipmentHandler.findOrEmpty(ExtraBotanyItems.PEACE_AMULET.get(), targetPlayer).isEmpty();
            }
            return !sourceEquipped || target instanceof Monster;
        }
        return true;
    }
}
