package grasspow.extrabotany.common.block.flower.functional;

import grasspow.extrabotany.api.ExtraBotanyAPI;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.botania.api.block_entity.FunctionalFlowerBlockEntity;
import vazkii.botania.api.block_entity.RadiusDescriptor;
import vazkii.botania.common.block.block_entity.flower.generating.HydroangeasBlockEntity;

import java.lang.reflect.Field;

import static grasspow.extrabotany.common.block.block_entity.ExtraBotanyBlockEntities.SERENITIAN;

public class SerenitianBlockEntity extends FunctionalFlowerBlockEntity {
    private static final int RANGE = 3;

    public SerenitianBlockEntity(BlockPos pos, BlockState state) {
        super(SERENITIAN, pos, state);
    }

    @Override
    public void tickFlower() {
        super.tickFlower();

        for (int dx = -RANGE; dx <= RANGE; dx++)
            for (int dz = -RANGE; dz <= RANGE; dz++) {
                BlockPos pos = getEffectivePos().offset(dx, 0, dz);
                BlockEntity entity = getLevel().getBlockEntity(pos);
                if (entity instanceof HydroangeasBlockEntity generator) {
                    resetPassiveTime(HydroangeasBlockEntity.class, generator);

                }
            }
    }

    private static <T> void resetPassiveTime(Class<T> clazz, T generator) {
        try {
            Field passiveDecayTicks = clazz.getDeclaredField("passiveDecayTicks");
            if (!passiveDecayTicks.canAccess(generator)) {
                passiveDecayTicks.setAccessible(true);
            }
            if (passiveDecayTicks.getInt(generator) > 1000) {
                passiveDecayTicks.setInt(generator, 0);
            }
        } catch (NoSuchFieldException | IllegalAccessException e) {
            ExtraBotanyAPI.LOGGER.error("catch exception when editing passiveDelay:{}", e.getMessage());
        }
    }

    @Override
    public int getColor() {
        return 0x000000;
    }

    @Override
    public int getMaxMana() {
        return 1;
    }

    @Override
    public RadiusDescriptor getRadius() {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), RANGE);
    }

}
