package grasspow.extrabotany.common.item.len;

import net.minecraft.world.item.ItemStack;
import vazkii.botania.api.internal.ManaBurst;
import vazkii.botania.common.item.lens.Lens;

public class TraceLens extends Lens {

    private static final String TAG_HOME_ID = "homeID";

    @Override
    public void updateBurst(ManaBurst burst, ItemStack stack) {
//        ThrowableProjectile self = burst.entity();
//        if (self.level().isClientSide())
//            return;
//        AABB axis = new AABB(self.getX(), self.getY(), self.getZ(), self.xOld,
//                self.yOld, self.zOld).inflate(4);
//        List<LivingEntity> entities = self.level().getEntitiesOfClass(LivingEntity.class, axis);
//        int homeID = self.getPersistentData().getInt(TAG_HOME_ID);
//        if (homeID == 0) {
//            for (LivingEntity living : entities) {
//                self.getPersistentData().putInt(TAG_HOME_ID, living.getId());
//                break;
//            }
//        } else {
//            Entity target = self.level().getEntity(homeID);
//            if (target != null && target.closerThan(self, 3F) && !burst.isFake()) {
//                Vec3 targetPos = new Vec3(target.getX(), target.getY() + 0.5, target.getZ());
//                Vec3 selfPos = new Vec3(self.position().toVector3f());
//                Vec3 distence = targetPos.subtract(selfPos);
//                double length = distence.length() / 0.2;
//                self.setDeltaMovement(distence.x / length, distence.y / length, distence.z / length);
//            }
//        }
    }

}
