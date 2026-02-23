package grasspow.extrabotany.common.item.len;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import vazkii.botania.api.internal.ManaBurst;
import vazkii.botania.common.item.lens.Lens;

import java.util.List;

public class PushLens extends Lens {

    @Override
    public void updateBurst(ManaBurst burst, ItemStack stack) {
        ThrowableProjectile entity = burst.entity();
        AABB axis = new AABB(entity.getX(), entity.getY(), entity.getZ(), entity.xOld, entity.yOld, entity.zOld).inflate(0.25);
        List<LivingEntity> entities = entity.level().getEntitiesOfClass(LivingEntity.class, axis);

        if (!burst.isFake()) {
            for (LivingEntity living : entities) {
                living.move(MoverType.SELF, entity.getDeltaMovement());
            }
        }

    }

}
