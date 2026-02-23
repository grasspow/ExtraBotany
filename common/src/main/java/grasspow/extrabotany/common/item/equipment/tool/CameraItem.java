package grasspow.extrabotany.common.item.equipment.tool;

import grasspow.extrabotany.api.IAdvancementRequirement;
import grasspow.extrabotany.common.lib.LibAdvancementNames;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import vazkii.botania.api.item.Relic;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.relic.RelicImpl;
import vazkii.botania.common.item.relic.RelicItem;
import vazkii.botania.xplat.XplatAbstractions;

import java.util.List;

public class CameraItem extends RelicItem implements IAdvancementRequirement {

    public static final int MANA_PER_DAMAGE = 1500;
    public static final int RANGE = 20;
    public static final String TAG_FREEZETIME = "freezeTime";
    public static final String TAG_TIMES = "freezeTimes";

    public CameraItem(Properties props) {
        super(props);
//        MinecraftForge.EVENT_BUS.addListener(this::onLivingUpdate);
    }

//    @SubscribeEvent(priority = EventPriority.HIGHEST)
//    public void onLivingUpdate(LivingEvent.LivingTickEvent event) {
//        if (event.getEntity() instanceof LivingEntity) {
//            if (!(event.getEntity() instanceof Player)) {
//                if (event.getEntity().getPersistentData().getInt(TAG_FREEZETIME) > 0) {
//                    event.getEntity().getPersistentData().putInt(TAG_FREEZETIME, event.getEntity().getPersistentData().getInt(TAG_FREEZETIME) - 1);
//                    event.setCanceled(true);
//                }
//                if (event.getEntity().level().getGameTime() % 24000 == 0) {
//                    event.getEntity().getPersistentData().putInt(TAG_TIMES, 0);
//                }
//            }
//        }
//    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        Relic relic = XplatAbstractions.INSTANCE.findRelic(stack);
        if (relic != null && relic.isRightPlayer(player) && ManaItemHandler.instance().requestManaExactForTool(stack, player, MANA_PER_DAMAGE, true)
                && !level.isClientSide) {
            for (LivingEntity living : level.getEntitiesOfClass(LivingEntity.class,
                    (new AABB(player.getOnPos())).inflate(RANGE))) {
                if (living == player)
                    continue;
//                if (!((ServerPlayer) player).getCamera().equals(living)) {
//                    living.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 100, 5));
//                    int time = 200;
//                    if (!living.canChangeDimensions())
//                        time = 40;
//                    if (living.getPersistentData().getInt(TAG_TIMES) > 10)
//                        time = 0;
//                    living.getPersistentData().putInt(TAG_FREEZETIME, time);
//                    living.getPersistentData().putInt(TAG_TIMES, living.getPersistentData().getInt(TAG_TIMES) + 1);
//                }
            }

            for (Entity e : level.getEntitiesOfClass(Entity.class,
                    (new AABB(player.getOnPos())).inflate(RANGE))) {

                if (e instanceof Projectile)
                    e.discard();
            }
            player.getCooldowns().addCooldown(stack.getItem(), 200);
        }
        return InteractionResultHolder.pass(stack);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        if (!world.isClientSide && entity instanceof Player player) {
            var relic = XplatAbstractions.INSTANCE.findRelic(stack);
            if (relic != null) {
                relic.tickBinding(player);
            }
        }
    }

    public static Relic makeRelic(ItemStack stack) {
        return new RelicImpl(stack, null);
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
