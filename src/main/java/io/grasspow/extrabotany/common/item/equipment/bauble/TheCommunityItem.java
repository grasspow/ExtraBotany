package io.grasspow.extrabotany.common.item.equipment.bauble;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import io.grasspow.extrabotany.common.item.ExtraBotanyItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.common.handler.EquipmentHandler;

public class TheCommunityItem extends BaubleItem {

    public TheCommunityItem(Properties props) {
        super(props);
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getEquippedAttributeModifiers(ItemStack stack) {
        Multimap<Attribute, AttributeModifier> attributes = HashMultimap.create();
        attributes.put(Attributes.ARMOR, new AttributeModifier(getBaubleUUID(stack), "Earth Stone", 4, AttributeModifier.Operation.ADDITION));
        attributes.put(Attributes.ATTACK_DAMAGE, new AttributeModifier(getBaubleUUID(stack), "Ignis Stone", 0.10F, AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(getBaubleUUID(stack), "Aero Stone 1", 0.15F, AttributeModifier.Operation.MULTIPLY_TOTAL));
        attributes.put(Attributes.FLYING_SPEED, new AttributeModifier(getBaubleUUID(stack), "Aero Stone 2", 0.15F, AttributeModifier.Operation.MULTIPLY_TOTAL));
        return attributes;
    }

    @Override
    public boolean canEquip(ItemStack stack, LivingEntity entity) {
        return EquipmentHandler.findOrEmpty(this, entity).isEmpty()
                && EquipmentHandler.findOrEmpty(ExtraBotanyItems.AERO_STONE.get(), entity).isEmpty()
                && EquipmentHandler.findOrEmpty(ExtraBotanyItems.AQUA_STONE.get(), entity).isEmpty()
                && EquipmentHandler.findOrEmpty(ExtraBotanyItems.EARTH_STONE.get(), entity).isEmpty()
                && EquipmentHandler.findOrEmpty(ExtraBotanyItems.IGNIS_STONE.get(), entity).isEmpty();
    }

}
