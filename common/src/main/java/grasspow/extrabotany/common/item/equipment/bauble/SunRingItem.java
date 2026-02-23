package grasspow.extrabotany.common.item.equipment.bauble;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import grasspow.extrabotany.api.IAdvancementRequirement;
import grasspow.extrabotany.common.item.ExtraBotanyItems;
import grasspow.extrabotany.common.lib.LibAdvancementNames;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.api.item.Relic;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.handler.EquipmentHandler;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.equipment.bauble.BandOfAuraItem;
import vazkii.botania.common.item.equipment.bauble.RingOfCorrectionItem;
import vazkii.botania.common.item.equipment.bauble.RingOfTheMantleItem;
import vazkii.botania.common.item.relic.RelicBaubleItem;
import vazkii.botania.common.item.relic.RelicImpl;

public class SunRingItem extends RelicBaubleItem implements IAdvancementRequirement {

    private static final int WATER_RING_COST = 3;

    public SunRingItem(Properties props) {
        super(props);
    }

    @Override
    public boolean canEquip(ItemStack stack, LivingEntity entity) {
        return EquipmentHandler.findOrEmpty(this, entity).isEmpty();
    }

    @Override
    public void onWornTick(ItemStack stack, LivingEntity living) {
        super.onWornTick(stack, living);
        ((RingOfTheMantleItem) BotaniaItems.miningRing).onWornTick(stack, living);
        ((BandOfAuraItem) BotaniaItems.auraRingGreater).onWornTick(stack, living);
        ((RingOfCorrectionItem) BotaniaItems.swapRing).onWornTick(stack, living);
        ((DeathRingItem) ExtraBotanyItems.deathRing).onWornTick(stack, living);
        ((FrostStarItem) ExtraBotanyItems.frostStar).onWornTick(stack, living);
        ((ManaDriveRingItem) ExtraBotanyItems.manaDriveRing).onWornTick(stack, living);

        // water ring
        if (!living.level().isClientSide) {
            if (living instanceof Player player && !ManaItemHandler.instance().requestManaExact(stack, player, WATER_RING_COST, true)) {
                onUnequipped(stack, living);
            } else {
                addEffect(living, MobEffects.CONDUIT_POWER);
                addEffect(living, MobEffects.DOLPHINS_GRACE);
            }
        }
    }

    private static void addEffect(LivingEntity living, Holder<MobEffect> effect) {
        MobEffectInstance inst = living.getEffect(effect);
        if (inst == null || (inst.getAmplifier() == 0 && inst.getDuration() == 1)) {
            MobEffectInstance neweffect = new MobEffectInstance(effect, 100, 0, true, true);
            living.addEffect(neweffect);
        }
    }

    @Override
    public void onUnequipped(ItemStack stack, LivingEntity player) {
        super.onUnequipped(stack, player);
        ((RingOfTheMantleItem) BotaniaItems.miningRing).onUnequipped(stack, player);
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getEquippedAttributeModifiers(ItemStack stack, ResourceLocation slotId) {
        Multimap<Holder<Attribute>, AttributeModifier> attributes = HashMultimap.create();
//        attributes.put(ForgeMod.BLOCK_REACH.get(), new AttributeModifier(exbotRL("sun_ring_1"), 3.5, AttributeModifier.Operation.ADDITION));
//        attributes.put(BuiltInRegistries.ATTRIBUTE.getHolderOrThrow(PixieHandler.PIXIE_SPAWN_CHANCE), new AttributeModifier(exbotRL("sun_ring_2"), 0.25, AttributeModifier.Operation.ADD_VALUE));
        return attributes;
    }

    public static Relic makeRelic(ItemStack stack) {
        return new RelicImpl(stack, null);
    }

    @Override
    public String getAdvancementName() {
        return LibAdvancementNames.EGO_DEFEAT;
    }
}
