package grasspow.extrabotany.common.item.equipment.bauble;

import grasspow.extrabotany.common.item.ExtraBotanyItems;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.common.handler.EquipmentHandler;
import vazkii.botania.common.item.equipment.bauble.BaubleItem;

public class AquaStoneItem extends BaubleItem {

    public AquaStoneItem(Properties props) {
        super(props);
//        MinecraftForge.EVENT_BUS.addListener(this::manaDiscount);
    }

//    @SubscribeEvent
//    public void manaDiscount(ManaDiscountEvent event) {
//        Player player = event.getEntityPlayer();
//        if (!EquipmentHandler.findOrEmpty(this, player).isEmpty() || !EquipmentHandler.findOrEmpty(ExtraBotanyItems.THE_COMMUNITY.get(), player).isEmpty()) {
//            event.setDiscount(event.getDiscount() + 0.1F);
//        }
//    }

    @Override
    public boolean canEquip(ItemStack stack, LivingEntity entity) {
        return EquipmentHandler.findOrEmpty(this, entity).isEmpty()
                && EquipmentHandler.findOrEmpty(ExtraBotanyItems.theCommunity, entity).isEmpty();
    }

}
