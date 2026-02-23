package grasspow.extrabotany.common.entity.item.brew;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrownPotion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import vazkii.botania.api.brew.Brew;
import vazkii.botania.api.brew.BrewItem;

import java.util.List;

public class SplashGrenadeEntity extends ThrownPotion {

    public SplashGrenadeEntity(EntityType<? extends ThrownPotion> type, Level level) {
        super(type, level);
    }

    public SplashGrenadeEntity(Level level, LivingEntity living) {
        super(level, living);
    }

    @Override
    protected void onHit(HitResult hitResult) {
        onImpact();
    }

    public void onImpact() {
        if (getItem().getItem() instanceof BrewItem bi) {
            Brew brew = bi.getBrew(getItem());
            double range = 5;
            AABB bounds = new AABB(getX() - range, getY() - range, getZ() - range,
                    getX() + range, getY() + range, getZ() + range);
            List<LivingEntity> entities = level().getEntitiesOfClass(LivingEntity.class, bounds);
            for (LivingEntity living2 : entities) {
                if (!(living2 instanceof Player))
                    living2.hurt(this.damageSources().magic(), 10F);
                for (var effect : brew.getPotionEffects(getItem())) {
                    MobEffectInstance newEffect = new MobEffectInstance(effect.getEffect(), (int) ((float) effect.getDuration() * 0.6F), effect.getAmplifier(), true, true);
                    if (!(living2 instanceof Player) && !effect.getEffect().value().isBeneficial()) {
                        if (effect.getEffect().value().isInstantenous()) {
                            effect.getEffect().value().applyInstantenousEffect(living2, living2, living2, newEffect.getAmplifier(), 1F);
                        } else {
                            living2.addEffect(newEffect);
                        }
                    } else if (living2 instanceof Player && effect.getEffect().value().isBeneficial()) {
                        if (effect.getEffect().value().isInstantenous()) {
                            effect.getEffect().value().applyInstantenousEffect(living2, living2, living2, newEffect.getAmplifier(), 1F);
                        } else {
                            living2.addEffect(newEffect);
                        }
                    }
                }
            }
            for (var effect : brew.getPotionEffects(getItem())) {
                int i = effect.getEffect().value().isInstantenous() ? 2007 : 2002;
                this.level().levelEvent(i, this.blockPosition(), brew.getColor(getItem()));
            }
        }
        if (!level().isClientSide)
            this.discard();
    }

    @Override
    protected double getDefaultGravity() {
        return 0.02F;
    }
}
