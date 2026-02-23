package grasspow.extrabotany.common.block.flower.generating;

import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.botania.api.block_entity.RadiusDescriptor;

import static grasspow.extrabotany.common.block.block_entity.ExtraBotanyBlockEntities.SUN_BLESS;


public class SunBlessBlockEntity extends PassiveLimitedGeneratingFlowerBlockEntity {

    private static final int RANGE = 0;

    public SunBlessBlockEntity(BlockPos pos, BlockState state) {
        super(SUN_BLESS, pos, state);
        setDecayTime(36000);
    }

    public SunBlessBlockEntity(BlockEntityType<MoonBlessBlockEntity> moonBless, BlockPos pos, BlockState state) {
        super(moonBless, pos, state);
        setDecayTime(36000);
    }

    @Override
    public int getMaxMana() {
        return 200;
    }

    @Override
    protected int getValueForPassiveGeneration() {
        return 1;
    }

    @Override
    public int getColor() {
        return 0xFFA500;
    }

    @Override
    public RadiusDescriptor getRadius() {
        return RadiusDescriptor.Rectangle.square(getEffectivePos(), RANGE);
    }

    @Override
    protected boolean canGeneratePassively() {
        return this.getLevel().isDay() && this.level.getRandom().nextInt(2)== 0;
    }

    @Override
    protected int getDelayBetweenPassiveGeneration() {
        return 2;
    }
}
