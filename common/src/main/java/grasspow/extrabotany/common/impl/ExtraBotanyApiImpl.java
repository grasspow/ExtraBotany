package grasspow.extrabotany.common.impl;

import grasspow.extrabotany.api.ExtraBotanyAPI;
import grasspow.extrabotany.api.item.ExtraBotanyArmorMaterials;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;

public class ExtraBotanyApiImpl implements ExtraBotanyAPI {
    public Holder<ArmorMaterial> getMaidArmorMaterial() {
        return ExtraBotanyArmorMaterials.MAID;
    }

    public Holder<ArmorMaterial> getMikuArmorMaterial() {
        return ExtraBotanyArmorMaterials.MIKU;
    }

    public Holder<ArmorMaterial> getGoblinSlayerArmorMaterial() {
        return ExtraBotanyArmorMaterials.GOBLINS_LAYER;
    }

    public Holder<ArmorMaterial> getShadowWarriorArmorMaterial() {
        return ExtraBotanyArmorMaterials.SHADOW_WARRIOR;
    }

    public Holder<ArmorMaterial> getShootingGuardianArmorMaterial() {
        return ExtraBotanyArmorMaterials.SHOOTING_GUARDIAN;
    }

    public Holder<ArmorMaterial> getSilentSagesArmorMaterial() {
        return ExtraBotanyArmorMaterials.SILENT_SAGES;
    }

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
