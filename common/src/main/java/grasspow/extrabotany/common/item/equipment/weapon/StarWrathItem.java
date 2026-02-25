package grasspow.extrabotany.common.item.equipment.weapon;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.phys.Vec3;
import vazkii.botania.api.item.Relic;
import vazkii.botania.common.entity.FallingStarEntity;
import vazkii.botania.common.item.relic.RelicImpl;

public class StarWrathItem extends RelicSwordItem {
    public StarWrathItem(Tier tier, Properties prop) {
        super(tier, prop);
    }

    @Override
    public int getManaPerDamage() {
        return 500;
    }

    @Override
    public void attack(LivingEntity player, Entity target, int times, double speedTime, float damageTime) {
        Vec3 targetpos = target == null ? raytraceFromEntity(player, 64F, true).getLocation().add(0, 1, 0) : target.position().add(0, 1, 0);

        for (int i = 0; i < 5; i++) {
            Vec3 posVec = targetpos.add((0.5F - Math.random()) * 6F, 0, (0.5F - Math.random()) * 6F);
            Vec3 motVec = new Vec3((0.5 * Math.random() - 0.25) * 18, 24, (0.5 * Math.random() - 0.25) * 18);
            posVec = posVec.add(motVec);
            motVec = motVec.normalize().reverse().scale(1.5);

            FallingStarEntity star = new FallingStarEntity(player, player.level());
            star.setPos(posVec.x, posVec.y, posVec.z);
            star.setDeltaMovement(motVec);
            player.level().addFreshEntity(star);
        }
    }

    public static Relic makeRelic(ItemStack stack) {
        return new RelicImpl(stack, null);
    }
}
