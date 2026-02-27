package grasspow.extrabotany.common.item.equipment.bauble;

import grasspow.extrabotany.common.component.ExtraBotanyDataComponents;
import grasspow.extrabotany.common.entity.ego.EGO;
import grasspow.extrabotany.xplat.XplatAbstractions;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.helper.DataComponentHelper;
import vazkii.botania.common.item.CustomCreativeTabContents;
import vazkii.botania.common.item.equipment.bauble.BaubleItem;

import java.util.List;

import static grasspow.extrabotany.common.lib.CommonHelper.clearHarmfulPotions;

public class NatureOrbItem extends BaubleItem implements CustomCreativeTabContents {

    public static final int DEFAULT_MAX_NATURE = 500000;

    public NatureOrbItem(Properties props) {
        super(props);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, context, tooltip, flags);
        var orb = XplatAbstractions.INSTANCE.findNatureOrbItem(stack);
        tooltip.add(Component.translatable("extrabotany.nature_orb", orb.getNature(), orb.getMaxNature()).withStyle(ChatFormatting.GRAY));
        tooltip.add(Component.translatable("extrabotany.nature_orb_effect1").withStyle(orb.getNature() >= 100000 ? ChatFormatting.AQUA : ChatFormatting.GRAY));
        tooltip.add(Component.translatable("extrabotany.nature_orb_effect2").withStyle(orb.getNature() >= 300000 ? ChatFormatting.DARK_RED : ChatFormatting.GRAY));
        tooltip.add(Component.translatable("extrabotany.nature_orb_effect3").withStyle(orb.getNature() >= 400000 ? ChatFormatting.DARK_GREEN : ChatFormatting.GRAY));
    }

    @Override
    public InteractionResult useOn(UseOnContext ctx) {
        ItemStack stack = ctx.getItemInHand();
        Level level = ctx.getLevel();
        BlockPos clickedPos = ctx.getClickedPos();
        return EGO.spawn(ctx.getPlayer(), stack, level, clickedPos) ? InteractionResult.SUCCESS : InteractionResult.FAIL;
    }

    @Override
    public void addToCreativeTab(Item me, CreativeModeTab.Output output) {
        output.accept(me);

        ItemStack full = new ItemStack(me);
        DataComponentHelper.setIntNonZero(full, ExtraBotanyDataComponents.NATURE, me.components().getOrDefault(ExtraBotanyDataComponents.MAX_NATURE, DEFAULT_MAX_NATURE));
        output.accept(full);
    }

    @Override
    public boolean isBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        var orb = XplatAbstractions.INSTANCE.findNatureOrbItem(stack);
        return Math.round((float) orb.getNature() * 13.0F / (float) orb.getMaxNature());
    }

    @Override
    public void onWornTick(ItemStack stack, LivingEntity entity) {
        super.onWornTick(stack, entity);
        if (entity instanceof Player player) {
            if (!player.level().isClientSide()) {
                var orb = XplatAbstractions.INSTANCE.findNatureOrbItem(stack);
                if (orb.getNature() > 100000 && player.tickCount % 5 == 0)
                    ManaItemHandler.instance().dispatchManaExact(stack, player, 5, true);
                if (orb.getNature() > 200000 && player.tickCount % 5 == 0)
                    ManaItemHandler.instance().dispatchManaExact(stack, player, 5, true);
                if (orb.getNature() > 300000 && player.tickCount % 5 == 0) {
                    ManaItemHandler.instance().dispatchManaExact(stack, player, 5, true);
                    if (player.tickCount % 60 == 0)
                        player.heal(1F);
                }
                if (orb.getNature() > 400000) {
                    if (player.tickCount % 40 == 0) {
                        clearHarmfulPotions(stack, player);
                    }
                }
            }
        }
    }
}
