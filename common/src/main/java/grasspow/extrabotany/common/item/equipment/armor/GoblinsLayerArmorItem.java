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


public class GoblinsLayerArmorItem extends MikuArmorItem {

    public GoblinsLayerArmorItem(Type type, Properties props) {
        super(type, props);
    }
    
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
