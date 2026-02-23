package grasspow.extrabotany.common.item.equipment.bauble;

import grasspow.extrabotany.api.IAdvancementRequirement;
import grasspow.extrabotany.common.lib.LibAdvancementNames;
import grasspow.extrabotany.common.lib.LibItemNames;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import vazkii.botania.api.item.Relic;
import vazkii.botania.common.item.equipment.bauble.BandOfManaItem;
import vazkii.botania.common.item.relic.RelicImpl;
import vazkii.botania.xplat.XplatAbstractions;

import java.util.List;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;


public class SagesManaRingItem extends BandOfManaItem implements IAdvancementRequirement {

    public static final int DEFAULT_MAX_MANA = Integer.MAX_VALUE - 1;

    public SagesManaRingItem(Properties props) {
        super(props);
    }

    @Override
    public void addToCreativeTab(Item me, CreativeModeTab.Output output) {
        output.accept(this);

        ItemStack full = new ItemStack(this);
        setMana(full, DEFAULT_MAX_MANA);
        output.accept(full);
    }

    public static Relic makeRelic(ItemStack stack) {
        return new RelicImpl(stack, exbotRL(LibItemNames.SAGES_MANA_RING));
    }

    @Override
    public void inventoryTick(ItemStack stack, Level level, Entity entity, int slot, boolean selected) {
        if (!level.isClientSide && entity instanceof Player player) {
            var relic = XplatAbstractions.INSTANCE.findRelic(stack);
            if (relic != null) {
                relic.tickBinding(player);
            }
        }
    }

    @Override
    public void onWornTick(ItemStack stack, LivingEntity entity) {
        var relic = XplatAbstractions.INSTANCE.findRelic(stack);
        if (relic != null && entity instanceof Player ePlayer) {
            relic.tickBinding(ePlayer);
        }
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, context, tooltip, flags);
        RelicImpl.addDefaultTooltip(stack, tooltip);
    }

    @Override
    public String getAdvancementName() {
        return LibAdvancementNames.EGO_DEFEAT;
    }
}
