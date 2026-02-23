package grasspow.extrabotany.common.item.equipment.bauble;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;
import vazkii.botania.api.block_entity.FunctionalFlowerBlockEntity;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.bauble.BaubleItem;

public class ManaDriveRingItem extends BaubleItem {

    public ManaDriveRingItem(Properties props) {
        super(props);
    }

    private static final int RANGE = 7;

    @Override
    public void onWornTick(ItemStack stack, LivingEntity entity) {
        super.onWornTick(stack, entity);
        if (!(entity instanceof Player player))
            return;
        if (player.tickCount % 20 == 0)
            for (int x = -RANGE; x <= RANGE; x++)
                for (int y = -RANGE; y <= RANGE; y++)
                    for (int z = -RANGE; z <= RANGE; z++) {
                        BlockEntity te = player.level().getBlockEntity(new BlockPos(player.getOnPos().offset(x, y, z)));
                        if (te instanceof FunctionalFlowerBlockEntity f) {
                            int manaToUse = f.getMaxMana() - f.getMana();
                            if (ManaItemHandler.instance().requestManaExact(stack, player, manaToUse, true)) {
                                f.addMana(manaToUse);
                            }
                        }
                    }
    }
}
