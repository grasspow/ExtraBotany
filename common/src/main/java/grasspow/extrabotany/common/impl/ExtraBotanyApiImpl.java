package grasspow.extrabotany.common.impl;

import grasspow.extrabotany.api.ExtraBotanyAPI;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;

public class ExtraBotanyApiImpl implements ExtraBotanyAPI {
//    public ExtraBotanyArmorMaterial getMaidArmorMaterial() {
//        return ExtraBotanyArmorMaterial.MAID;
//    }
//
//    public ExtraBotanyArmorMaterial getMikuArmorMaterial() {
//        return ExtraBotanyArmorMaterial.MIKU;
//    }
//
//    public ExtraBotanyArmorMaterial getGoblinSlayerArmorMaterial() {
//        return ExtraBotanyArmorMaterial.GOBLINS_LAYER;
//    }
//
//    public ExtraBotanyArmorMaterial getShadowWarriorArmorMaterial() {
//        return ExtraBotanyArmorMaterial.SHADOW_WARRIOR;
//    }
//
//    public ExtraBotanyArmorMaterial getShootingGuardianArmorMaterial() {
//        return ExtraBotanyArmorMaterial.SHOOTING_GUARDIAN;
//    }

//    public ExtraBotanyArmorMaterial getSilentSagesArmorMaterial() {
//        return ExtraBotanyArmorMaterial.SILENT_SAGES;
//    }

    public void addPotionEffect(LivingEntity entity, Holder<MobEffect> potion, int time, int max, boolean multi) {
        if (!entity.hasEffect(potion))
            entity.addEffect(new MobEffectInstance(potion, time, 0));
        else {
            int amp = entity.getEffect(potion).getAmplifier();
            int t = multi ? time + 200 * amp : time;
            entity.addEffect(new MobEffectInstance(potion, t, Math.min(max, amp + 1)));
        }
    }

    @Override
    public void addPotionEffect(LivingEntity entity, Holder<MobEffect> potion, int max) {
        addPotionEffect(entity, potion, 100, max, false);
    }

    @Override
    public int apiVersion() {
        return 1;
    }

    public float calcDamage(float orig, Player player) {
        if (player == null)
            return orig;
        double value = player.getAttributeValue(Attributes.ATTACK_DAMAGE);
        return (float) (orig + value);
    }
}
