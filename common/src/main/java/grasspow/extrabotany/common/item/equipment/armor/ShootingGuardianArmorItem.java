package grasspow.extrabotany.common.item.equipment.armor;

import com.google.common.base.Suppliers;
import grasspow.extrabotany.client.lib.LibResources;
import grasspow.extrabotany.common.item.ExtraBotanyItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;

import java.util.List;
import java.util.function.Supplier;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;

public class ShootingGuardianArmorItem extends MikuArmorItem {
    public ShootingGuardianArmorItem(Type type, Properties props) {
        super(type, props);
    }
//    @SubscribeEvent
//    public void onPlayerHeal(LivingHealEvent event) {
//        if (event.getEntity() instanceof Player player && hasArmorSet(player)) {
//            float originalHealAmount = event.getAmount();
//            float newHealAmount = originalHealAmount * 0.1f; // 恢复速度降低
//            event.setAmount(newHealAmount);
//        }
//    }

//    @Override
//    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
//        Multimap<Attribute, AttributeModifier> attributes = super.getAttributeModifiers(slot, stack);
//        UUID uuid = new UUID(stack.getItem().hashCode() + slot.toString().hashCode(), 0);
//        if (slot == getEquipmentSlot()) {
//            attributes = HashMultimap.create(attributes);
//            attributes.put(Attributes.FLYING_SPEED, new AttributeModifier(uuid, "Shooting Guardian modifier flying speed" + type, 0.10F, AttributeModifier.Operation.MULTIPLY_BASE));
//            attributes.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "Shooting Guardian modifier speed" + type, 0.10F, AttributeModifier.Operation.MULTIPLY_BASE));
//            attributes.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "Shooting Guardian modifier attack damage" + type, 0.10F, AttributeModifier.Operation.MULTIPLY_BASE));
//            attributes.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, "Shooting Guardian modifier attack speed" + type, 0.03F, AttributeModifier.Operation.MULTIPLY_BASE));
//        }
//        return attributes;
//    }

    private static final Supplier<ItemStack[]> armorSet = Suppliers.memoize(() -> new ItemStack[]{
            new ItemStack(ExtraBotanyItems.shootingGuardianHelm),
            new ItemStack(ExtraBotanyItems.shootingGuardianChest),
            new ItemStack(ExtraBotanyItems.shootingGuardianLegs),
            new ItemStack(ExtraBotanyItems.shootingGuardianBoots)
    });

    @Override
    public ItemStack[] getArmorSetStacks() {
        return armorSet.get();
    }

    @Override
    public boolean hasArmorSetItem(Player player, EquipmentSlot slot) {
        if (player == null) {
            return false;
        }

        ItemStack stack = player.getItemBySlot(slot);
        if (stack.isEmpty()) {
            return false;
        }

        return switch (slot) {
            case HEAD -> stack.is(ExtraBotanyItems.shootingGuardianHelm);
            case CHEST -> stack.is(ExtraBotanyItems.shootingGuardianChest);
            case LEGS -> stack.is(ExtraBotanyItems.shootingGuardianLegs);
            case FEET -> stack.is(ExtraBotanyItems.shootingGuardianBoots);
            default -> false;
        };
    }

    @Override
    public ResourceLocation getArmorTextureAfterInk(ItemStack stack, EquipmentSlot slot) {
        return exbotRL(LibResources.MODEL_SHOOTING_GUARDIAN);
    }

    @Override
    public MutableComponent getArmorSetName() {
        return Component.translatable("extrabotany.armorset.shooting_guardian.name");
    }

    @Override
    public void addArmorSetDescription(ItemStack stack, List<Component> list) {
        list.add(Component.translatable("extrabotany.armorset.shooting_guardian.desc0").withStyle(ChatFormatting.GRAY));
        list.add(Component.translatable("extrabotany.armorset.shooting_guardian.desc1").withStyle(ChatFormatting.GRAY));
        list.add(Component.translatable("extrabotany.armorset.shooting_guardian.desc2").withStyle(ChatFormatting.GRAY));
        list.add(Component.translatable("extrabotany.armorset.shooting_guardian.desc3").withStyle(ChatFormatting.GRAY));
    }
}
