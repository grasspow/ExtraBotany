package grasspow.extrabotany.common.item.equipment.armor;

import com.google.common.base.Suppliers;
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


public class GoblinsLayerArmorItem extends MikuArmorItem {

    public GoblinsLayerArmorItem(Type type, Properties props) {
        super(type, props);
    }

    @Override
    public void inventoryTick(ItemStack stack, Level world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if (!world.isClientSide() && entity instanceof Player player) {
            DataComponentHelper.setFlag(stack, BotaniaDataComponents.ACTIVE, hasArmorSet(player) && world.isDay());
        }
    }
    //    @Override
//    public Multimap<Attribute, AttributeModifier> getAttributeModifiers(EquipmentSlot slot, ItemStack stack) {
//        Multimap<Attribute, AttributeModifier> attributes = super.getAttributeModifiers(slot, stack);
//        UUID uuid = new UUID(stack.getItem().hashCode() + slot.toString().hashCode(), 0);
//        boolean night = ItemNBTHelper.getBoolean(stack, TAG_DAY, false);
//        if (slot == getEquipmentSlot()) {
//            attributes = HashMultimap.create(attributes);
//            if (night) {
//                attributes.put(Attributes.MAX_HEALTH, new AttributeModifier(uuid, "GoblinSlayer modifier " + type, 0.5F, AttributeModifier.Operation.MULTIPLY_BASE));
//                attributes.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(uuid, "GoblinSlayer modifier " + type, 0.04F, AttributeModifier.Operation.MULTIPLY_BASE));
//            }
//        }
//        return attributes;
//    }

    private static final Supplier<ItemStack[]> armorSet = Suppliers.memoize(() -> new ItemStack[]{
            new ItemStack(ExtraBotanyItems.goblinsLayerHelm),
            new ItemStack(ExtraBotanyItems.goblinsLayerChest),
            new ItemStack(ExtraBotanyItems.goblinsLayerLegs),
            new ItemStack(ExtraBotanyItems.goblinsLayerBoots)
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
            case HEAD -> stack.is(ExtraBotanyItems.goblinsLayerHelm);
            case CHEST -> stack.is(ExtraBotanyItems.goblinsLayerChest);
            case LEGS -> stack.is(ExtraBotanyItems.goblinsLayerLegs);
            case FEET -> stack.is(ExtraBotanyItems.goblinsLayerBoots);
            default -> false;
        };
    }

    @Override
    public ResourceLocation getArmorTextureAfterInk(ItemStack stack, EquipmentSlot slot) {
        return exbotRL(LibResources.MODEL_GOBLINS_LAYER);
    }

    @Override
    public MutableComponent getArmorSetName() {
        return Component.translatable("extrabotany.armorset.goblins_layer.name");
    }

    @Override
    public void addArmorSetDescription(ItemStack stack, List<Component> list) {
        list.add(Component.translatable("extrabotany.armorset.goblins_layer.desc0").withStyle(ChatFormatting.GRAY));
        list.add(Component.translatable("extrabotany.armorset.goblins_layer.desc1").withStyle(ChatFormatting.GRAY));
    }
}
