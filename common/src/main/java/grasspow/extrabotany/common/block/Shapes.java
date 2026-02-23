package grasspow.extrabotany.common.block;

import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.VoxelShape;

import static net.minecraft.world.phys.shapes.Shapes.box;
import static net.minecraft.world.phys.shapes.Shapes.join;

public final class Shapes {
    public static final VoxelShape PEDESTAL = genShape1();
    public static final VoxelShape MANA_BUFFER = genShape2();
    public static final VoxelShape QUANTUM_MANA_BUFFER = genShape3();
    public static final VoxelShape TROPHY = genShape4();
    public static final VoxelShape LIVINGROCK_BARREL = genShape5();
    public static final VoxelShape POWER_FRAME = genShape6();

    private static VoxelShape genShape1() {
        VoxelShape shape = box(0, 0, 0, 1, 0.125, 1);
        shape = join(shape, box(0.125, 0.125, 0.125, 0.875, 0.25, 0.875), BooleanOp.OR);
        shape = join(shape, box(0.25, 0.25, 0.25, 0.75, 0.75, 0.75), BooleanOp.OR);
        shape = join(shape, box(0.125, 0.75, 0.125, 0.875, 0.875, 0.875), BooleanOp.OR);
        shape = join(shape, box(0.125, 0.875, 0.125, 0.875, 1.25, 0.1875), BooleanOp.OR);
        shape = join(shape, box(0.125, 0.875, 0.8125, 0.875, 1.25, 0.875), BooleanOp.OR);
        shape = join(shape, box(0.125, 0.875, 0.1875, 0.1875, 1.25, 0.8125), BooleanOp.OR);
        shape = join(shape, box(0.8125, 0.875, 0.1875, 0.875, 1.25, 0.8125), BooleanOp.OR);
        return shape;
    }

    private static VoxelShape genShape2() {
        VoxelShape shape = box(0.0625, 0, 0.0625, 0.9375, 0.875, 0.9375);
        shape = join(shape, box(0, 0.15625, 0, 0.0625, 0.21875, 1), BooleanOp.OR);
        shape = join(shape, box(0.9375, 0.15625, 0, 1, 0.21875, 1), BooleanOp.OR);
        shape = join(shape, box(0.0625, 0.15625, 0.9375, 0.9375, 0.21875, 1), BooleanOp.OR);
        shape = join(shape, box(0.0625, 0.15625, 0, 0.9375, 0.21875, 0.0625), BooleanOp.OR);
        shape = join(shape, box(0, 0.65625, 0, 0.0625, 0.71875, 1), BooleanOp.OR);
        shape = join(shape, box(0.9375, 0.65625, 0, 1, 0.71875, 1), BooleanOp.OR);
        shape = join(shape, box(0.0625, 0.65625, 0.9375, 0.9375, 0.71875, 1), BooleanOp.OR);
        shape = join(shape, box(0.0625, 0.65625, 0, 0.9375, 0.71875, 0.0625), BooleanOp.OR);
        shape = join(shape, box(-0.03125, 0.125, 0.4375, 0.0625, 0.25, 0.5625), BooleanOp.OR);
        shape = join(shape, box(0.9375, 0.125, 0.4375, 1.03125, 0.25, 0.5625), BooleanOp.OR);
        shape = join(shape, box(0.4375, 0.125, -0.03125, 0.5625, 0.25, 0.0625), BooleanOp.OR);
        shape = join(shape, box(0.4375, 0.125, 0.9375, 0.5625, 0.25, 1.03125), BooleanOp.OR);
        shape = join(shape, box(0.40625, 0.875, 0.40625, 0.59375, 0.9375, 0.59375), BooleanOp.OR);
        shape = join(shape, box(0.4375, 0.9375, 0.4375, 0.5625, 1.03125, 0.5625), BooleanOp.OR);
        return shape;
    }

    private static VoxelShape genShape3() {
        VoxelShape shape = box(0.0625, 0, 0.0625, 0.3125, 0.875, 0.3125);
        shape = join(shape, box(0.375, 0, 0.0625, 0.625, 0.875, 0.3125), BooleanOp.OR);
        shape = join(shape, box(0.6875, 0, 0.0625, 0.9375, 0.875, 0.3125), BooleanOp.OR);
        shape = join(shape, box(0.0625, 0, 0.6875, 0.3125, 0.875, 0.9375), BooleanOp.OR);
        shape = join(shape, box(0.0625, 0, 0.375, 0.3125, 0.875, 0.625), BooleanOp.OR);
        shape = join(shape, box(0.375, 0, 0.375, 0.625, 0.875, 0.625), BooleanOp.OR);
        shape = join(shape, box(0.6875, 0, 0.375, 0.9375, 0.875, 0.625), BooleanOp.OR);
        shape = join(shape, box(0.375, 0, 0.6875, 0.625, 0.875, 0.9375), BooleanOp.OR);
        shape = join(shape, box(0.6875, 0, 0.6875, 0.9375, 0.875, 0.9375), BooleanOp.OR);
        shape = join(shape, box(0, 0.15625, 0, 0.0625, 0.21875, 1), BooleanOp.OR);
        shape = join(shape, box(0.9375, 0.15625, 0, 1, 0.21875, 1), BooleanOp.OR);
        shape = join(shape, box(0.0625, 0.15625, 0.9375, 0.9375, 0.21875, 1), BooleanOp.OR);
        shape = join(shape, box(0.0625, 0.15625, 0, 0.9375, 0.21875, 0.0625), BooleanOp.OR);
        shape = join(shape, box(0, 0.65625, 0, 0.0625, 0.71875, 1), BooleanOp.OR);
        shape = join(shape, box(0.9375, 0.65625, 0, 1, 0.71875, 1), BooleanOp.OR);
        shape = join(shape, box(0.0625, 0.65625, 0.9375, 0.9375, 0.71875, 1), BooleanOp.OR);
        shape = join(shape, box(0.0625, 0.65625, 0, 0.9375, 0.71875, 0.0625), BooleanOp.OR);
        shape = join(shape, box(-0.03125, 0.125, 0.4375, 0.0625, 0.25, 0.5625), BooleanOp.OR);
        shape = join(shape, box(0.9375, 0.125, 0.4375, 1.03125, 0.25, 0.5625), BooleanOp.OR);
        shape = join(shape, box(0.4375, 0.125, -0.03125, 0.5625, 0.25, 0.0625), BooleanOp.OR);
        shape = join(shape, box(0.4375, 0.125, 0.9375, 0.5625, 0.25, 1.03125), BooleanOp.OR);
        shape = join(shape, box(0.78125, 0.875, 0.15625, 0.84375, 0.9375, 0.21875), BooleanOp.OR);
        shape = join(shape, box(0.46875, 0.875, 0.15625, 0.53125, 0.9375, 0.21875), BooleanOp.OR);
        shape = join(shape, box(0.15625, 0.875, 0.15625, 0.21875, 0.9375, 0.21875), BooleanOp.OR);
        shape = join(shape, box(0.15625, 0.875, 0.46875, 0.21875, 0.9375, 0.53125), BooleanOp.OR);
        shape = join(shape, box(0.15625, 0.875, 0.78125, 0.21875, 0.9375, 0.84375), BooleanOp.OR);
        shape = join(shape, box(0.46875, 0.875, 0.78125, 0.53125, 0.9375, 0.84375), BooleanOp.OR);
        shape = join(shape, box(0.78125, 0.875, 0.78125, 0.84375, 0.9375, 0.84375), BooleanOp.OR);
        shape = join(shape, box(0.78125, 0.875, 0.46875, 0.84375, 0.9375, 0.53125), BooleanOp.OR);
        shape = join(shape, box(0.46875, 0.875, 0.46875, 0.53125, 0.9375, 0.53125), BooleanOp.OR);
        shape = join(shape, box(0.4375, 0.9375, 0.4375, 0.5625, 1.03125, 0.5625), BooleanOp.OR);
        shape = join(shape, box(0.15625, 0.9375, 0.78125, 0.84375, 1, 0.84375), BooleanOp.OR);
        shape = join(shape, box(0.15625, 0.9375, 0.46875, 0.84375, 1, 0.53125), BooleanOp.OR);
        shape = join(shape, box(0.15625, 0.9375, 0.15625, 0.84375, 1, 0.21875), BooleanOp.OR);
        shape = join(shape, box(0.15625, 0.9375, 0.53125, 0.21875, 1, 0.78125), BooleanOp.OR);
        shape = join(shape, box(0.78125, 0.9375, 0.21875, 0.84375, 1, 0.46875), BooleanOp.OR);
        return shape;

    }

    private static VoxelShape genShape4() {
        VoxelShape shape = box(0.25, 0, 0.25, 0.75, 0.125, 0.75);
        shape = join(shape, box(0.375, 0.125, 0.4375, 0.625, 0.625, 0.5625), BooleanOp.OR);
        shape = join(shape, box(0.25, 0.375, 0.4375, 0.375, 0.625, 0.5625), BooleanOp.OR);
        shape = join(shape, box(0.625, 0.375, 0.4375, 0.75, 0.625, 0.5625), BooleanOp.OR);
        shape = join(shape, box(0.375, 0.625, 0.375, 0.625, 0.875, 0.625), BooleanOp.OR);
        return shape;
    }

    private static VoxelShape genShape5() {
        VoxelShape shape = box(0, 0, 0, 1, 0.0625, 1);
        shape = join(shape, box(0, 0.0625, 0, 0.0625, 0.9375, 1), BooleanOp.OR);
        shape = join(shape, box(0.9375, 0.0625, 0, 1, 0.9375, 1), BooleanOp.OR);
        shape = join(shape, box(0.0625, 0.0625, 0, 0.9375, 0.9375, 0.0625), BooleanOp.OR);
        shape = join(shape, box(0.0625, 0.0625, 0.9375, 0.9375, 0.9375, 1), BooleanOp.OR);
        return shape;
    }

    private static VoxelShape genShape6() {
        VoxelShape shape = box(0.9375, 0.9375, 0.0625, 1, 1, 0.9375);
        shape = join(shape, box(0, 0.9375, 0.0625, 0.0625, 1, 0.9375), BooleanOp.OR);
        shape = join(shape, box(0, 0.9375, 0, 1, 1, 0.0625), BooleanOp.OR);
        shape = join(shape, box(0, 0.9375, 0.9375, 1, 1, 1), BooleanOp.OR);
        shape = join(shape, box(0.9375, 0.0625, 0.9375, 1, 0.9375, 1), BooleanOp.OR);
        shape = join(shape, box(0.9375, 0.0625, 0, 1, 0.9375, 0.0625), BooleanOp.OR);
        shape = join(shape, box(0, 0.0625, 0.9375, 0.0625, 0.9375, 1), BooleanOp.OR);
        shape = join(shape, box(0, 0.0625, 0, 0.0625, 0.9375, 0.0625), BooleanOp.OR);
        shape = join(shape, box(0.9375, 0, 0.0625, 1, 0.0625, 0.9375), BooleanOp.OR);
        shape = join(shape, box(0, 0, 0.0625, 0.0625, 0.0625, 0.9375), BooleanOp.OR);
        shape = join(shape, box(0, 0, 0, 1, 0.0625, 0.0625), BooleanOp.OR);
        shape = join(shape, box(0, 0, 0.9375, 1, 0.0625, 1), BooleanOp.OR);
        shape = join(shape, box(0.125, 0.125, 0.125, 0.875, 0.875, 0.875), BooleanOp.OR);
        return shape;
    }
}
