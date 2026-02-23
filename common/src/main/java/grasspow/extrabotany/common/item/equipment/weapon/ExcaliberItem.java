package grasspow.extrabotany.common.item.equipment.weapon;

import grasspow.extrabotany.api.IAdvancementRequirement;
import grasspow.extrabotany.common.component.ExtraBotanyDataComponents;
import grasspow.extrabotany.common.handler.DamageHandler;
import grasspow.extrabotany.common.lib.LibAdvancementNames;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.internal.ManaBurst;
import vazkii.botania.api.item.Relic;
import vazkii.botania.api.mana.BurstProperties;
import vazkii.botania.api.mana.LensEffectItem;
import vazkii.botania.common.entity.ManaBurstEntity;
import vazkii.botania.common.handler.BotaniaSounds;
import vazkii.botania.common.helper.VecHelper;
import vazkii.botania.common.item.equipment.tool.ToolCommons;
import vazkii.botania.common.item.relic.RelicImpl;
import vazkii.botania.xplat.XplatAbstractions;

import java.util.List;
import java.util.function.Consumer;

public class ExcaliberItem extends RelicSwordItem implements LensEffectItem, IAdvancementRequirement {

    public ExcaliberItem(Properties prop) {
        super(Tiers.NETHERITE, 8, -2f, prop);
    }

//    @Override
//    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
//        Multimap<Holder<Attribute>, AttributeModifier> ret = HashMultimap.create(super.getAttributeModifiers(slot, stack));
//        if (slot == EquipmentSlot.MAINHAND) {
//            ret.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(getBaubleUUID(stack), "Excaliber modifier", 0.3D, AttributeModifier.Operation.MULTIPLY_TOTAL));
//        }
//        return ret;
//    }

    @Override
    public int getManaPerDamage() {
        return 160;
    }

    @Override
    public void attack(LivingEntity player, Entity target, int times, double speedTime, float damageTime) {
        if (player instanceof Player p) {
            if (!player.getItemInHand(InteractionHand.MAIN_HAND).isEmpty() && player.getItemInHand(InteractionHand.MAIN_HAND).getItem() == this) {
                ManaBurstEntity burst = getBurst(p, player.getItemInHand(InteractionHand.MAIN_HAND));
                player.level().addFreshEntity(burst);
                ToolCommons.damageItemIfPossible(player.getItemInHand(InteractionHand.MAIN_HAND), 1, player, getManaPerDamage());
                player.level().playSound(null, player.getX(), player.getY(), player.getZ(), BotaniaSounds.terraBlade,
                        SoundSource.PLAYERS, 0.4F, 1.4F);
            }
        }
    }

    public static ManaBurstEntity getBurst(Player player, ItemStack stack) {
        ManaBurstEntity burst = new ManaBurstEntity(player);
        float motionModifier = 9F;
        burst.setColor(0xFFFF20);
        burst.setMana(160);
        burst.setStartingMana(160);
        burst.setMinManaLoss(40);
        burst.setManaLossPerTick(4F);
        burst.setGravity(0F);
        burst.setDeltaMovement(burst.getDeltaMovement().scale(motionModifier));

        ItemStack lens = stack.copy();
        lens.set(ExtraBotanyDataComponents.ATTACKER,player.getName().getString());
        burst.setSourceLens(lens);
        return burst;
    }

    @Override
    public void apply(ItemStack stack, BurstProperties props, Level level) {

    }

    @Override
    public boolean collideBurst(ManaBurst burst, HitResult pos, boolean isManaBlock, boolean shouldKill, ItemStack stack) {
        return shouldKill;
    }

    @Override
    public void updateBurst(ManaBurst burst, ItemStack stack) {
        ThrowableProjectile entity = burst.entity();
        AABB axis = new AABB(entity.getX(), entity.getY(), entity.getZ(), entity.xOld, entity.yOld, entity.zOld).inflate(1);
        Entity attacker = entity.getOwner();

//        int homeID = ItemNBTHelper.getInt(stack, TAG_HOME_ID, -1);
        int homeID = stack.getOrDefault(ExtraBotanyDataComponents.HOME_ID,-1);
        if (homeID == -1) {
            AABB axis1 = new AABB(entity.getX() - 5F, entity.getY() - 5F, entity.getZ() - 5F,
                    entity.xOld + 5F, entity.yOld + 5F, entity.zOld + 5F);
            List<LivingEntity> entities = entity.level().getEntitiesOfClass(LivingEntity.class, axis1);
            for (LivingEntity living : entities) {
                if (living instanceof Player || !(living instanceof Mob) || living.hurtTime != 0)
                    continue;
                homeID = living.getId();
//                ItemNBTHelper.setInt(stack, TAG_HOME_ID, homeID);
                stack.set(ExtraBotanyDataComponents.HOME_ID,homeID);
                break;
            }
        }

        List<LivingEntity> entities = entity.level().getEntitiesOfClass(LivingEntity.class, axis);
        if (homeID != -1) {
            Entity home = entity.level().getEntity(homeID);
            if (home != null) {
                Vec3 vecEntity = VecHelper.fromEntityCenter(home);
                Vec3 vecThis = VecHelper.fromEntityCenter(entity);
                Vec3 vecMotion = vecEntity.subtract(vecThis);
                Vec3 currMotion = entity.getDeltaMovement();

                vecMotion.normalize().scale(currMotion.length());
                entity.setDeltaMovement(vecMotion.x, vecMotion.y, vecMotion.z);
            }
        }

        for (LivingEntity living : entities) {
            if (attacker != null && living instanceof Player && (living.getId() == attacker.getId()))
                continue;

            if (!living.isRemoved()) {
                int cost = getManaPerDamage() / 3;
                int mana = burst.getMana();
                if (mana >= cost) {
                    burst.setMana(mana - cost);
                    float damage = BotaniaAPI.instance().getTerrasteelItemTier().getAttackDamageBonus() + 3F;
                    if (!burst.isFake() && !entity.level().isClientSide) {
                        Player player = living.level().getPlayerByUUID(XplatAbstractions.INSTANCE.findRelic(stack).getSoulbindUUID());
                        DamageHandler.INSTANCE.dmg(living, player, damage, DamageHandler.INSTANCE.GENERAL_PIERCING);
                        entity.discard();
                        break;
                    }
                }
            }
        }
    }

    public static Relic makeRelic(ItemStack stack) {
        return new RelicImpl(stack, null);
    }

    @Override
    public boolean doParticles(ManaBurst burst, ItemStack stack) {
        return true;
    }

    @Override
    public String getAdvancementName() {
        return LibAdvancementNames.EGO_DEFEAT;
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, @Nullable T entity, Consumer<Item> breakCallback) {
        return 0;
    }
}
