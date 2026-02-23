package grasspow.extrabotany.common.item.equipment.bauble;

import grasspow.extrabotany.common.handler.DamageHandler;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.AABB;
import vazkii.botania.api.mana.ManaItemHandler;
import vazkii.botania.common.item.equipment.bauble.BaubleItem;

public class DeathRingItem extends BaubleItem {

    public DeathRingItem(Properties props) {
        super(props);
    }

    private static final int RANGE = 6;
    private static final int MANA_PER_DAMAGE = 80;

    @Override
    public void onWornTick(ItemStack stack, LivingEntity entity) {
        super.onWornTick(stack, entity);
        if (entity instanceof Player player) {
            if (!player.level().isClientSide()) {
                for (LivingEntity living : entity.level().getEntitiesOfClass(LivingEntity.class, (new AABB(player.getOnPos())).inflate(RANGE))) {
                    if (((ServerPlayer) player).getCamera() != living
                            && living != player
                            && DamageHandler.checkPassable(living, player)
                            && ManaItemHandler.instance().requestManaExactForTool(stack, player, MANA_PER_DAMAGE, true)
                            && player.tickCount % 30 == 0) {
                        living.addEffect(new MobEffectInstance(MobEffects.WITHER, 60, 1));
                        living.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 60, 1));
                        DamageHandler.INSTANCE.dmg(living, entity, 0.5F, DamageHandler.INSTANCE.LIFE_LOSING);
                    }
                }
            }
        }
    }
}
