package grasspow.extrabotany.common.lib;

import grasspow.extrabotany.api.NatureOrb;
import grasspow.extrabotany.common.item.equipment.armor.MaidArmorHelmetItem;
import grasspow.extrabotany.xplat.XplatAbstractions;
import net.minecraft.core.Holder;
import net.minecraft.stats.Stats;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static grasspow.extrabotany.common.handler.DamageHandler.checkPassable;

public class CommonHelper {
    public static void clearPotions(ItemStack stack, Player player) {
        List<Holder<MobEffect>> potionsToRemove = player.getActiveEffectsMap().entrySet().stream()
                .filter(effect -> effect.getValue().getEffect().value().getCategory() == MobEffectCategory.HARMFUL)
                .map(Map.Entry::getKey)
                .distinct()
                .toList();
        NatureOrb orb = XplatAbstractions.INSTANCE.findNatureOrbItem(stack);
        potionsToRemove.forEach(potion -> {
            if (stack.getItem() instanceof MaidArmorHelmetItem || (orb != null && orb.addNature(-50))) {
                player.removeEffect(potion);
//                CriteriaTriggers.CONSUME_ITEM.trigger((ServerPlayer) player, stack);
                player.awardStat(Stats.ITEM_USED.get(stack.getItem()));
            }
        });
    }

    public static List<LivingEntity> getFilteredEntities(List<LivingEntity> entities, Entity source) {
        List<LivingEntity> list = entities.stream().filter((living) -> checkPassable(living, source) && !living.isRemoved()).collect(Collectors.toList());
        return list;
    }


}
