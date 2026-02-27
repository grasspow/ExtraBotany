package grasspow.extrabotany.common.item.equipment.armor;

import com.google.common.base.Suppliers;
import grasspow.extrabotany.api.ExtraBotanyAPI;
import grasspow.extrabotany.client.lib.LibResources;
import grasspow.extrabotany.common.item.ExtraBotanyItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import vazkii.botania.common.component.BotaniaDataComponents;
import vazkii.botania.common.helper.DataComponentHelper;

import java.util.List;
import java.util.function.Supplier;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;

public class ShadowWarriorArmorItem extends MikuArmorItem {
    public ShadowWarriorArmorItem(Type type, Properties props) {
        super(type, ExtraBotanyAPI.instance().getShadowWarriorArmorMaterial(), props);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (!world.isClientSide() && entity instanceof Player player) {
            DataComponentHelper.setFlag(stack, BotaniaDataComponents.ACTIVE, hasArmorSet(player) && world.isNight());
        }
    }

//    @Override
//    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
//        Multimap<Attribute, AttributeModifier> attributes = super.getAttributeModifiers(slot, stack);
//        UUID uuid = new UUID(stack.getItem().hashCode() + slot.toString().hashCode(), 0);
//        boolean night = ItemNBTHelper.getBoolean(stack, TAG_NIGHT, false);
//        if (slot == getEquipmentSlot()) {
//            attributes = HashMultimap.create(super.getAttributeModifiers(slot, stack));
//            if (night) {
//                attributes.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, "ShadowWarrior modifier health" + type, 0.25F, AttributeModifier.Operation.MULTIPLY_BASE));
//                attributes.put(Attributes.ATTACK_SPEED, new AttributeModifier(uuid, "ShadowWarrior modifier attack speed" + type, 0.125F, AttributeModifier.Operation.MULTIPLY_BASE));
//                attributes.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(uuid, "ShadowWarrior modifier attack damage" + type, 0.05F, AttributeModifier.Operation.MULTIPLY_BASE));
//            }
//        }
//        return attributes;
//    }

    private static final Supplier<ItemStack[]> armorSet = Suppliers.memoize(() -> new ItemStack[]{
            new ItemStack(ExtraBotanyItems.shadowWarriorHelm),
            new ItemStack(ExtraBotanyItems.shadowWarriorChest),
            new ItemStack(ExtraBotanyItems.shadowWarriorLegs),
            new ItemStack(ExtraBotanyItems.shadowWarriorBoots)
    });

    @Override
    public ItemStack[] getArmorSetStacks() {
        return armorSet.get();
    }

    @Override
    public boolean hasArmorSetItem(Player player, EquipmentSlot slot) {
        ItemStack stack = player.getItemBySlot(slot);
        if (stack.isEmpty()) {
            return false;
        }

        return switch (slot) {
            case HEAD -> stack.is(ExtraBotanyItems.shadowWarriorHelm);
            case CHEST -> stack.is(ExtraBotanyItems.shadowWarriorChest);
            case LEGS -> stack.is(ExtraBotanyItems.shadowWarriorLegs);
            case FEET -> stack.is(ExtraBotanyItems.shadowWarriorBoots);
            default -> false;
        };
    }

    @Override
    public ResourceLocation getArmorTextureAfterInk(ItemStack stack, EquipmentSlot slot) {
        return exbotRL(LibResources.MODEL_SHADOW_WARRIOR);
    }

    @Override
    public MutableComponent getArmorSetName() {
        return Component.translatable("extrabotany.armorset.shadow_warrior.name");
    }

    @Override
    public void addArmorSetDescription(ItemStack stack, List<Component> list) {
        list.add(Component.translatable("extrabotany.armorset.shadow_warrior.desc0").withStyle(ChatFormatting.GRAY));
        list.add(Component.translatable("extrabotany.armorset.shadow_warrior.desc1").withStyle(ChatFormatting.GRAY));
    }
}
