package grasspow.extrabotany.common.item.equipment.tool;

import net.minecraft.world.item.Item;

public class WalkingCaneItem extends Item {
    public WalkingCaneItem(Properties properties) {
        super(properties);
    }

//    @Override
//    public Multimap<Holder<Attribute>, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
//        Multimap<Holder<Attribute>, AttributeModifier> ret = super.getAttributeModifiers(slot, stack);
//        if (slot == EquipmentSlot.MAINHAND) {
//            ret = HashMultimap.create(ret);
//            ret.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(UUID.fromString("995829fa-94c0-41bd-b046-0468c509a488"), "Cane modifier", 0.3D, AttributeModifier.Operation.MULTIPLY_TOTAL));
//        }
//        return ret;
//    }
}
