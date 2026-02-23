package grasspow.extrabotany.common.item.brew;

import grasspow.extrabotany.common.item.ExtraBotanyItems;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import vazkii.botania.api.item.Relic;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.relic.RelicImpl;
import vazkii.botania.xplat.XplatAbstractions;

import java.util.List;

public class InfiniteWineItem extends BaseBrewItemEX {
    private static final int MANA_PER_DAMAGE = 12000;

    public InfiniteWineItem(Properties builder) {
        super(builder,16, 1.5f, 1, ()-> ExtraBotanyItems.emptyBottle);
    }

    public static Relic makeRelic(ItemStack stack) {
        return new RelicImpl(stack, null) {
        };
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (!level.isClientSide && entity instanceof Player player) {
            var relic = XplatAbstractions.INSTANCE.findRelic(stack);
            if (relic != null) {
                relic.tickBinding(player);
                if (level.getDayTime() % 8000 == 0
                        && getSwigsLeft(stack) < 12
                        && ManaItemHandler.instance().requestManaExactForTool(stack, player, MANA_PER_DAMAGE, true)) {
                    setSwigsLeft(stack, getSwigsLeft(stack) + 1);
                }
            }
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flags) {
        super.appendHoverText(stack, context, list, flags);
        RelicImpl.addDefaultTooltip(stack, list);
    }
}
