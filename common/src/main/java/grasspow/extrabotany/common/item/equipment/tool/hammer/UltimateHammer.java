package grasspow.extrabotany.common.item.equipment.tool.hammer;

import grasspow.extrabotany.common.component.ExtraBotanyDataComponents;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Vec3i;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.item.SequentialBreaker;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.annotations.SoftImplement;
import vazkii.botania.common.component.BotaniaDataComponents;
import vazkii.botania.common.handler.BotaniaSounds;
import vazkii.botania.common.helper.DataComponentHelper;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import vazkii.botania.xplat.XplatAbstractions;

import java.util.List;
import java.util.function.Predicate;

public class UltimateHammer extends ManasteelHammer implements SequentialBreaker {
    private static final int MANA_PER_DAMAGE = 80;
    private static final int MAX_MANA = Integer.MAX_VALUE - 1;

    public UltimateHammer(Properties props) {
        super(BotaniaAPI.instance().getTerrasteelItemTier(), props,-3.0F);
    }

    @Override
    public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltipComponents, TooltipFlag tooltipFlag) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag);
        if (getAttack(stack) > 0) {
            tooltipComponents.add(Component.translatable("extrabotany.upgrade.attack" + getAttack(stack)));
        }
        if (getRepair(stack) > 0) {
            tooltipComponents.add(Component.translatable("extrabotany.upgrade.repair" + getAttack(stack)));
        }
        if (hasRange(stack)) {
            tooltipComponents.add(Component.translatable("extrabotany.upgrade.range." + (isEnabled(stack) ? "on" : "off")));
        }
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (isEnabled(stack)) {
            if (!hasRange(stack) || getMana_(stack) == 0) {
                setEnabled(stack, false);
            } else if (entity instanceof Player player && !player.swinging) {
                var manaItem = XplatAbstractions.INSTANCE.findManaItem(stack);
                manaItem.addMana(-MANA_PER_DAMAGE);
            }
        }
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (player.isSecondaryUseActive() && hand == InteractionHand.MAIN_HAND) {
            BlockHitResult blockhitresult = getPlayerPOVHitResult(level, player, ClipContext.Fluid.NONE);
            if (blockhitresult.getType() != HitResult.Type.BLOCK && hasRange(stack) && player.isShiftKeyDown()) {
                setEnabled(stack, !isEnabled(stack));
                if (!level.isClientSide) {
                    level.playSound(null, player.getX(), player.getY(), player.getZ(), BotaniaSounds.terraPickMode, SoundSource.PLAYERS, 1F, 1F);
                }
                return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
            }
        }
        return InteractionResultHolder.pass(stack);
    }

    @Override
    public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
        if (getAttack(stack) > 0 && ManaItemHandler.instance().requestManaExact(stack, (Player) attacker, (80 * getAttack(stack) - getRepair(stack) * 15), true)) {
            if (getAttack(stack) > 1)
                target.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 1));
            if (getAttack(stack) > 3)
                target.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 1));
            if (getAttack(stack) > 5)
                target.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 100, 2));
            if (getAttack(stack) > 7)
                target.addEffect(new MobEffectInstance(MobEffects.WITHER, 100, 2));
            if (getAttack(stack) > 9)
                attacker.heal(4F);
            target.hurt(target.damageSources().playerAttack((Player) attacker), 2F * getAttack(stack));
        }
        return true;
    }

    @Override
    public float getDestroySpeed(ItemStack stack, BlockState state) {
        if (state.is(BlockTags.MINEABLE_WITH_PICKAXE) ||
                state.is(BlockTags.MINEABLE_WITH_AXE) ||
                state.is(BlockTags.MINEABLE_WITH_SHOVEL)) {
            return 4.0F;
        }
        return 1.0F;
    }

    @SoftImplement("IItemExtension")
    public boolean onBlockStartBreak(ItemStack stack, BlockPos pos, Player player) {
        BlockHitResult raycast = ToolCommons.raytraceFromEntity(player, 10, false);
        if (!player.level().isClientSide && raycast.getType() == HitResult.Type.BLOCK) {
            Direction face = raycast.getDirection();
            breakOtherBlock(player, stack, pos, pos, face);
            if (player.isSecondaryUseActive()) {
                BotaniaAPI.instance().breakOnAllCursors(player, stack, pos, face);
            }
        }
        return false;
    }

    @Override
    public void breakOtherBlock(Player player, ItemStack stack, BlockPos pos, BlockPos originPos, Direction side) {
        if (!isEnabled(stack)) {
            return;
        }

        Level world = player.level();
        Predicate<BlockState> canMine = state -> {
            boolean rightToolForDrops = !state.requiresCorrectToolForDrops() || stack.isCorrectToolForDrops(state);
            boolean rightToolForSpeed = stack.getDestroySpeed(state) > 1
                    || state.is(BlockTags.MINEABLE_WITH_SHOVEL)
                    || state.is(BlockTags.MINEABLE_WITH_HOE);
            return rightToolForDrops && rightToolForSpeed;
        };

        BlockState targetState = world.getBlockState(pos);
        if (!canMine.test(targetState)) {
            return;
        }

        if (world.isEmptyBlock(pos)) {
            return;
        }

        boolean doX = side.getStepX() == 0;
        boolean doY = side.getStepY() == 0;
        boolean doZ = side.getStepZ() == 0;

        Vec3i beginDiff = new Vec3i(doX ? -1 : 0, doY ? -1 : 0, doZ ? -1 : 0);
        Vec3i endDiff = new Vec3i(doX ? 1 : 0, doY ? 1 : 0, doZ ? 1 : 0);
        ToolCommons.removeBlocksInIteration(player, stack, world, pos, beginDiff, endDiff, canMine);
    }

    public static boolean isEnabled(ItemStack stack) {
        return stack.has(BotaniaDataComponents.ACTIVE);
    }

    private static void setEnabled(ItemStack stack, boolean enabled) {
        DataComponentHelper.setFlag(stack, BotaniaDataComponents.ACTIVE, enabled);
    }

    public static void setRepair(ItemStack stack, int repair) {
        DataComponentHelper.setIntNonZero(stack, ExtraBotanyDataComponents.REPAIR_UPGRADE, repair);
    }

    public static int getRepair(ItemStack stack) {
        return stack.getOrDefault(ExtraBotanyDataComponents.REPAIR_UPGRADE,0);
    }

    public static void setAttack(ItemStack stack, int attack) {
        DataComponentHelper.setIntNonZero(stack, ExtraBotanyDataComponents.DAMAGE_UPGRADE, attack);
    }

    public static int getAttack(ItemStack stack) {
        return stack.getOrDefault(ExtraBotanyDataComponents.DAMAGE_UPGRADE,0);
    }

    public static boolean hasRange(ItemStack stack) {
        return stack.has(ExtraBotanyDataComponents.RANGE_UPGRADE);
    }

    public static void setRange(ItemStack stack, boolean enabled) {
        DataComponentHelper.setFlag(stack, ExtraBotanyDataComponents.RANGE_UPGRADE, enabled);
    }

    protected static void setMana(ItemStack stack, int mana) {
        DataComponentHelper.setIntNonZero(stack, BotaniaDataComponents.MANA, mana);
    }

    public static int getMana_(ItemStack stack) {
        return stack.getOrDefault(BotaniaDataComponents.MANA,0);
    }

}
