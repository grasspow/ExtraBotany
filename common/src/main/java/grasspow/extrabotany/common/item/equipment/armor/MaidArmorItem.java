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

public class MaidArmorItem extends MikuArmorItem {

    public MaidArmorItem(Type type, Properties props) {
        super(type, props);
    }

//    @Override
//    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
//        Multimap<Attribute, AttributeModifier> attributes = super.getAttributeModifiers(slot, stack);
//        UUID uuid = new UUID(stack.getItem().hashCode() + slot.toString().hashCode(), 0);
//        if (slot == getEquipmentSlot()) {
//            attributes = HashMultimap.create(attributes);
//            attributes.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, "Maid modifier " + type, 5, AttributeModifier.Operation.ADDITION));
//            attributes.put(Attributes.KNOCKBACK_RESISTANCE, new AttributeModifier(uuid, "Maid modifier " + type, type.ordinal() / 20, AttributeModifier.Operation.ADDITION));
//        }
//        return attributes;
//    }

    private static final Supplier<ItemStack[]> armorSet = Suppliers.memoize(() -> new ItemStack[]{
            new ItemStack(ExtraBotanyItems.maidHelm),
            new ItemStack(ExtraBotanyItems.maidChest),
            new ItemStack(ExtraBotanyItems.maidLegs),
            new ItemStack(ExtraBotanyItems.maidBoots)
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
            case HEAD -> stack.is(ExtraBotanyItems.maidHelm);
            case CHEST -> stack.is(ExtraBotanyItems.maidChest);
            case LEGS -> stack.is(ExtraBotanyItems.maidLegs);
            case FEET -> stack.is(ExtraBotanyItems.maidBoots);
            default -> false;
        };
    }

    @Override
    public ResourceLocation getArmorTextureAfterInk(ItemStack stack, EquipmentSlot slot) {
        return exbotRL(LibResources.MODEL_MAID);
    }

    @Override
    public MutableComponent getArmorSetName() {
        return Component.translatable("extrabotany.armorset.maid.name");
    }

    @Override
    public void addArmorSetDescription(ItemStack stack, List<Component> list) {
        list.add(Component.translatable("extrabotany.armorset.maid.desc0").withStyle(ChatFormatting.GRAY));
        list.add(Component.translatable("extrabotany.armorset.maid.desc1").withStyle(ChatFormatting.GRAY));
        list.add(Component.translatable("extrabotany.armorset.maid.desc2").withStyle(ChatFormatting.GRAY));
    }
}
