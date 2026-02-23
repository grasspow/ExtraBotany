package grasspow.extrabotany.common.item.equipment.bauble;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import grasspow.extrabotany.common.item.ExtraBotanyItems;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.common.handler.EquipmentHandler;
import vazkii.botania.common.item.equipment.bauble.BaubleItem;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;

public class AeroStoneItem extends BaubleItem {
    public AeroStoneItem(Properties props) {
        super(props);
    }

    @Override
    public Multimap<Holder<Attribute>, AttributeModifier> getEquippedAttributeModifiers(ItemStack stack, ResourceLocation slotId) {
        Multimap<Holder<Attribute>, AttributeModifier> attributes = HashMultimap.create();
        attributes.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(exbotRL("aero_stone_1"), 0.15F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        attributes.put(Attributes.FLYING_SPEED, new AttributeModifier(exbotRL("aero_stone_2"), 0.15F, AttributeModifier.Operation.ADD_MULTIPLIED_TOTAL));
        return attributes;
    }

    @Override
    public boolean canEquip(ItemStack stack, LivingEntity entity) {
        return EquipmentHandler.findOrEmpty(this, entity).isEmpty()
                && EquipmentHandler.findOrEmpty(ExtraBotanyItems.theCommunity, entity).isEmpty();
    }


}
