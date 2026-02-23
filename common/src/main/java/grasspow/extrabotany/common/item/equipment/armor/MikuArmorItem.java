package grasspow.extrabotany.common.item.equipment.armor;

import com.google.common.base.Suppliers;
import grasspow.extrabotany.client.lib.LibResources;
import grasspow.extrabotany.common.item.ExtraBotanyItems;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.api.mana.ManaDiscountArmor;
import vazkii.botania.common.item.equipment.armor.manasteel.ManasteelArmorItem;
import vazkii.botania.common.item.equipment.tool.ToolCommons;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;
import static vazkii.botania.api.BotaniaAPI.DUMMY_ARMOR_MATERIAL;

public class MikuArmorItem extends ManasteelArmorItem implements ManaDiscountArmor {
    public MikuArmorItem(Type type,Properties props) {
        super(type, DUMMY_ARMOR_MATERIAL, props);
    }

//    @SubscribeEvent(priority = EventPriority.LOW)
//    public void onPlayerAttacked(LivingHurtEvent event) {
//        Entity target = event.getEntity();
//        if (target instanceof Player player) {
//            if (hasArmorSet(player) && getEquipmentSlot() == EquipmentSlot.HEAD) {
//                if (event.getSource().is(DamageTypes.MAGIC)) {
//                    event.setAmount(event.getAmount() * 0.25F);
//                }
//            }
//        }
//    }

    @Override
    public float getDiscount(ItemStack stack, int slot, Player player, @Nullable ItemStack tool) {
        return hasArmorSet(player) ? 0.15F : 0;
    }

    @Override
    public <T extends LivingEntity> int damageItem(ItemStack stack, int amount, @Nullable T entity, Consumer<Item> breakCallback) {
        return ToolCommons.damageItemIfPossible(stack, amount, entity, getManaPerDamage());
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
        if (player == null) {
            return false;
        }

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
