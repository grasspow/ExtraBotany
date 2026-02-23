package grasspow.extrabotany.common.item.equipment.tool;


import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.client.gui.ItemsRemainingRenderHandler;
import vazkii.botania.common.advancements.ManaBlasterTrigger;
import vazkii.botania.common.entity.ManaBurstEntity;
import vazkii.botania.common.handler.BotaniaSounds;
import vazkii.botania.common.handler.EquipmentHandler;
import vazkii.botania.common.item.BotaniaItems;
import vazkii.botania.common.item.ManaBlasterItem;

public class SilverBulletItem extends ManaBlasterItem {
    private static final int COOLDOWN = 30;
    private static final String TAG_COOLDOWN = "cooldown";

    public SilverBulletItem(Properties props) {
        super(props);
    }

    @Override
    public @NotNull InteractionResultHolder<ItemStack> use(Level world, Player player, @NotNull InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        int effCd = COOLDOWN;
        MobEffectInstance effect = player.getEffect(MobEffects.DIG_SPEED);
        if (effect != null) {
            effCd = Math.max(2, COOLDOWN - (effect.getAmplifier() + 1) * 8);
        }
        if (player.isSecondaryUseActive() && hasClip(stack)) {
            rotatePos(stack);
            world.playSound(null, player.getX(), player.getY(), player.getZ(), BotaniaSounds.manaBlasterCycle, SoundSource.PLAYERS, 0.6F, (1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.2F) * 0.7F);
            if (!world.isClientSide) {
                ItemStack lens = getLens(stack);
                ItemsRemainingRenderHandler.send(player, lens, -2);
                setCooldown(stack, effCd);
            }
            return InteractionResultHolder.sidedSuccess(stack, world.isClientSide);
        } else if (getCooldown(stack) <= 0) {
            ManaBurstEntity burst = getBurst(player, stack, true, hand);
//            if (burst != null && (((ShootingGuardianArmorItem) ExtraBotanyItems.SHOOTING_GUARDIAN_HELM).hasArmorSet(player) || ManaItemHandler.instance().requestManaExact(stack, player, burst.getMana(), true))) {
            if (burst != null && ManaItemHandler.instance().requestManaExact(stack, player, burst.getMana(), true)){
                if (!world.isClientSide) {
                    world.playSound(null, player.getX(), player.getY(), player.getZ(), BotaniaSounds.manaBlaster, SoundSource.PLAYERS, 1F, 1);
                    world.addFreshEntity(burst);
                    ManaBlasterTrigger.INSTANCE.trigger((ServerPlayer) player, stack);
                    setCooldown(stack, effCd);
                } else if (!EquipmentHandler.getAllWorn(player).hasAnyMatching(k -> k.is(BotaniaItems.knockbackBelt))) {
                    player.setDeltaMovement(player.getDeltaMovement().subtract(burst.getDeltaMovement().multiply(0.1, 0.3, 0.1)));
                }
            } else{
                player.playSound(BotaniaSounds.manaBlasterMisfire, 0.6F, (1.0F + (world.random.nextFloat() - world.random.nextFloat()) * 0.2F) * 0.7F);
            }
            return InteractionResultHolder.sidedSuccess(stack, world.isClientSide);
        }
        return InteractionResultHolder.pass(stack);
    }

    private ManaBurstEntity getBurst(Player player, ItemStack stack, boolean request, InteractionHand hand) {
        ManaBurstEntity burst = new ManaBurstEntity(player);
        BurstProperties props = getBurstProps(player, stack, request, hand);

        burst.setSourceLens(getLens(stack));
        if (!request || ManaItemHandler.instance().requestManaExact(stack, player, props.maxMana, false)) {
            int color = 0x87CEFA;
            burst.setColor(color);
            burst.setMana(props.maxMana);
            burst.setStartingMana(props.maxMana);
            burst.setMinManaLoss(props.ticksBeforeManaLoss);
            burst.setManaLossPerTick(props.manaLossPerTick);
            burst.setGravity(props.gravity);
            burst.setDeltaMovement(burst.getDeltaMovement().scale(props.motionModifier));

            return burst;
        }
        return null;
    }

    private void setCooldown(ItemStack stack, int cooldown) {
//        CompoundTag tag = stack.getOrCreateTag();
//        tag.putInt(TAG_COOLDOWN, cooldown);

    }

    private int getCooldown(ItemStack stack) {
//        return stack.getOrCreateTag().getInt(TAG_COOLDOWN);
        return 0;
    }
}
