package grasspow.extrabotany.common.item.misc;

import grasspow.extrabotany.common.item.ExtraBotanyItems;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import vazkii.botania.common.block.block_entity.mana.ManaPoolBlockEntity;
import vazkii.botania.common.block.mana.ManaPoolBlock;

public class EmptyBottleItem extends Item {
    public EmptyBottleItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        ItemStack stack = ctx.getItemInHand();
        BlockPos pos = ctx.getClickedPos();
        Level level = ctx.getLevel();
        BlockState state = level.getBlockState(pos);
        Block block = state.getBlock();
        if (block instanceof ManaPoolBlock && level.getBlockEntity(pos) instanceof ManaPoolBlockEntity entity) {
            entity.receiveMana(-10000);
            stack.shrink(1);
            ctx.getPlayer().getInventory().placeItemBackInInventory(new ItemStack(ExtraBotanyItems.manaDrink));
            return InteractionResult.SUCCESS;
        }
        return InteractionResult.PASS;
    }
}
