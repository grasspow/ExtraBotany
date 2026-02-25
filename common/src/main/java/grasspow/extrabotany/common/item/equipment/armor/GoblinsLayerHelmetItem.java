package grasspow.extrabotany.common.item.equipment.armor;

import grasspow.extrabotany.common.lib.LibItemNames;
import net.minecraft.core.component.DataComponents;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EquipmentSlotGroup;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.level.Level;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;

public class GoblinsLayerHelmetItem extends GoblinsLayerArmorItem{
    public GoblinsLayerHelmetItem(Type type, Properties props) {
        super(type, props);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (!world.isClientSide() && entity instanceof Player player && hasArmorSet(player) && world.isDay()) {
            ItemAttributeModifiers modifiers = stack.getOrDefault(DataComponents.ATTRIBUTE_MODIFIERS, ItemAttributeModifiers.builder().build());
            modifiers.withModifierAdded(
                    Attributes.MAX_HEALTH,
                    new AttributeModifier(exbotRL(LibItemNames.GOBLINS_LAYER+"_modifier_max_health"),0.5F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                    EquipmentSlotGroup.HEAD
            ).withModifierAdded(
                    Attributes.MOVEMENT_SPEED,
                    new AttributeModifier(exbotRL(LibItemNames.GOBLINS_LAYER+"_modifier_movement_speed"),0.4F, AttributeModifier.Operation.ADD_MULTIPLIED_BASE),
                    EquipmentSlotGroup.HEAD
            );
            stack.set(DataComponents.ATTRIBUTE_MODIFIERS, modifiers);
        }
    }
}
