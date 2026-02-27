package grasspow.extrabotany.common.item.equipment.armor;

import com.google.common.base.Suppliers;
import grasspow.extrabotany.api.ExtraBotanyAPI;
import grasspow.extrabotany.api.item.IArmorSetsWithEffects;
import grasspow.extrabotany.client.lib.LibResources;
import grasspow.extrabotany.common.item.ExtraBotanyItems;
import net.minecraft.ChatFormatting;
import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.mana.ManaDiscountArmor;
import vazkii.botania.common.item.equipment.armor.manasteel.ManasteelArmorItem;

import java.util.List;
import java.util.function.Supplier;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;

public class MikuArmorItem extends ManasteelArmorItem implements ManaDiscountArmor, IArmorSetsWithEffects {
    public MikuArmorItem(Type type,Properties props) {
        super(type, ExtraBotanyAPI.instance().getMikuArmorMaterial(), props);
    }

    public MikuArmorItem(Type type,Holder<ArmorMaterial> mat,Properties props){
        super(type, mat, props);
    }

    @Override
    public float getDiscount(ItemStack stack, int slot, Player player, @Nullable ItemStack tool) {
        return hasArmorSet(player) ? 0.15F : 0;
    }

    protected int getManaPerDamage() {
        return 140;
    }
    
    @Override
    public ResourceLocation getArmorTextureAfterInk(ItemStack stack, EquipmentSlot slot) {
        return exbotRL(LibResources.MODEL_MIKU);
    }

    private static final Supplier<ItemStack[]> armorSet = Suppliers.memoize(() -> new ItemStack[]{
            new ItemStack(ExtraBotanyItems.mikuHelm),
            new ItemStack(ExtraBotanyItems.mikuChest),
            new ItemStack(ExtraBotanyItems.mikuLegs),
            new ItemStack(ExtraBotanyItems.mikuBoots)
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
            case HEAD -> stack.is(ExtraBotanyItems.mikuHelm);
            case CHEST -> stack.is(ExtraBotanyItems.mikuChest);
            case LEGS -> stack.is(ExtraBotanyItems.mikuLegs);
            case FEET -> stack.is(ExtraBotanyItems.mikuBoots);
            default -> false;
        };

    }

    @Override
    public MutableComponent getArmorSetName() {
        return Component.translatable("extrabotany.armorset.miku.name");
    }

    @Override
    public void addArmorSetDescription(ItemStack stack, List<Component> list) {
        list.add(Component.translatable("extrabotany.armorset.mana.desc").withStyle(ChatFormatting.GRAY));
    }
}
