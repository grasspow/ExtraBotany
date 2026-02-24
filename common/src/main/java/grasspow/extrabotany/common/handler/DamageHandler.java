package grasspow.extrabotany.common.handler;

import grasspow.extrabotany.common.item.ExtraBotanyItems;
import grasspow.extrabotany.common.lib.ExtraBotanyDamageTypes;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.player.Player;
import vazkii.botania.common.handler.EquipmentHandler;

public final class DamageHandler {
    public static final DamageHandler INSTANCE = new DamageHandler();

    public final int GENERAL = 0;
    public final int MAGIC = 1;
    public final int GENERAL_PIERCING = 2;
    public final int MAGIC_PIERCING = 3;
    public final int LIFE_LOSING = 4;

    public boolean dmg(Entity target, Entity source, float amount, int type) {
        if (target == null || !checkPassable(target, source))
            return false;
        switch (type) {
            case GENERAL: {
                if (source instanceof Player player) {
                    return target.hurt(player.damageSources().playerAttack(player), amount);
                } else if (source instanceof LivingEntity living) {
                    return target.hurt(living.damageSources().mobAttack(living), amount);
                } else {
                    return target.hurt(source.damageSources().generic(), amount);
                }
            }
            case MAGIC: {
                if (source == null) {
                    return target.hurt(target.damageSources().magic(), amount);
                } else {
                    return target.hurt(source.damageSources().indirectMagic(source, target), amount);
                }
            }
            case GENERAL_PIERCING: {
                if (source == null) {
                    return target.hurt(ExtraBotanyDamageTypes.Sources.generalArmorPiercing(target.level().registryAccess(), null), amount);
                } else {
                    return target.hurt(ExtraBotanyDamageTypes.Sources.generalArmorPiercing(source.level().registryAccess(), source), amount);
                }
            }
            case MAGIC_PIERCING: {
                if (source == null) {
                    return target.hurt(ExtraBotanyDamageTypes.Sources.magicArmorPiercing(target.level().registryAccess(), null), amount);
                } else {
                    return target.hurt(ExtraBotanyDamageTypes.Sources.magicArmorPiercing(source.level().registryAccess(), source), amount);
                }
            }
            case LIFE_LOSING: {
                if (!(target instanceof LivingEntity living))
                    return false;
                float currentHealth = living.getHealth();
                float trueHealth = Math.max(1F, currentHealth - amount);
                living.setHealth(trueHealth);
                return dmg(target, source, 0.01F, GENERAL);
            }
        }
        return false;
    }

    public static boolean checkPassable(Entity target, Entity attacker) {
        if (target == attacker) {
            return false;
        }
        if (attacker instanceof Player player) {
            boolean sourceEquipped = !EquipmentHandler.findOrEmpty(ExtraBotanyItems.peaceAmulet, player).isEmpty();
            if (target instanceof Player targetPlayer) {
                return !sourceEquipped && EquipmentHandler.findOrEmpty(ExtraBotanyItems.peaceAmulet, targetPlayer).isEmpty() && !targetPlayer.isCreative();
            }
            return !sourceEquipped || target instanceof Monster;
        }
        return true;
    }
}
