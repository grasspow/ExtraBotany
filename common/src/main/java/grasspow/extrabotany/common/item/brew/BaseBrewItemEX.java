/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 */
package grasspow.extrabotany.common.item.brew;

import com.google.common.collect.Lists;
import grasspow.extrabotany.common.item.ExtraBotanyItems;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.alchemy.PotionContents;
import net.minecraft.world.level.Level;
import vazkii.botania.common.item.brew.BaseBrewItem;

import java.util.List;
import java.util.function.Supplier;

public class BaseBrewItemEX extends BaseBrewItem {
    public static final int DEFAULT_USES_COCKTAIL = 8;
    public static final int DEFAULT_USES_INFINITE_WINE = 12;
    private final float multiplier;
    private final int amplifier;

    public BaseBrewItemEX(Properties builder, int drinkSpeed, float multiplier, int amplifier, Supplier<Item> baseItem) {
        super(builder, drinkSpeed, baseItem);
        this.multiplier = multiplier;
        this.amplifier = amplifier;
    }

    @Override
    public ItemStack finishUsingItem(ItemStack stack, Level world, LivingEntity living) {
        if (!world.isClientSide && getSwigsLeft(stack) >= 1) {
            for (MobEffectInstance effect : getBrew(stack).getPotionEffects(stack)) {
                MobEffectInstance newEffect = new MobEffectInstance(effect.getEffect(), (int) (effect.getDuration() * multiplier), effect.getAmplifier() + amplifier, true, true);
                if (effect.getEffect().value().isInstantenous()) {
                    effect.getEffect().value().applyInstantenousEffect(living, living, living, newEffect.getAmplifier(), 1F);
                } else {
                    living.addEffect(newEffect);
                }
            }
            if (world.random.nextBoolean()) {
                world.playSound(null, living.getX(), living.getY(), living.getZ(), SoundEvents.PLAYER_BURP, SoundSource.PLAYERS, 1F, 1F);
            }

            int swigs = getSwigsLeft(stack);
            if (living instanceof Player player && !player.isCreative()) {
                if (swigs <= 1 && !stack.is(ExtraBotanyItems.infiniteWine)) {
                    ItemStack result = getBaseStack();
                    if (!player.getInventory().add(result)) {
                        return result;
                    } else {
                        return ItemStack.EMPTY;
                    }
                }

                setSwigsLeft(stack, swigs - 1);
            }
        }

        return stack;
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> list, TooltipFlag flags) {
        List<MobEffectInstance> effectInstances = Lists.newArrayList();
        getBrew(stack).getPotionEffects(stack).forEach((effect) -> effectInstances.add(new MobEffectInstance(effect.getEffect(), (int) (effect.getDuration() * multiplier), effect.getAmplifier() + amplifier, true, true)));
        PotionContents.addPotionTooltip(effectInstances, list::add, 1, context.tickRate());
    }

}
