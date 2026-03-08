package grasspow.extrabotany.client.model.armor;

import grasspow.extrabotany.client.model.ExtraBotanyModelLayers;
import grasspow.extrabotany.common.item.equipment.armor.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.Nullable;
import vazkii.botania.client.model.armor.ArmorModel;

import java.util.Collections;
import java.util.EnumMap;
import java.util.Map;

public class ArmorModels {
    private static Map<EquipmentSlot, ArmorModel> miku = Collections.emptyMap();
    private static Map<EquipmentSlot, ArmorModel> maid = Collections.emptyMap();
    private static Map<EquipmentSlot, ArmorModel> goblins_layer = Collections.emptyMap();
    private static Map<EquipmentSlot, ArmorModel> shadow_warrior = Collections.emptyMap();
    private static Map<EquipmentSlot, ArmorModel> shooting_guardian = Collections.emptyMap();

    private static Map<EquipmentSlot, ArmorModel> make(EntityRendererProvider.Context ctx, ModelLayerLocation inner, ModelLayerLocation outer) {
        Map<EquipmentSlot, ArmorModel> ret = new EnumMap<>(EquipmentSlot.class);
        for (var slot : EquipmentSlot.values()) {
            var mesh = ctx.bakeLayer(slot == EquipmentSlot.LEGS ? inner : outer);
            ret.put(slot, new ArmorModel(mesh, slot));
        }
        return ret;
    }

    public static void init(EntityRendererProvider.Context ctx) {
        miku = make(ctx, ExtraBotanyModelLayers.MIKU_INNER, ExtraBotanyModelLayers.MIKU_OUTER);
        maid = make(ctx, ExtraBotanyModelLayers.MAID_INNER, ExtraBotanyModelLayers.MAID_OUTER);
        goblins_layer = make(ctx, ExtraBotanyModelLayers.GOBLIN_SLAYER_INNER, ExtraBotanyModelLayers.GOBLIN_SLAYER_OUTER);
        shadow_warrior = make(ctx, ExtraBotanyModelLayers.SHADOW_INNER_ARMOR, ExtraBotanyModelLayers.SHADOW_OUTER_ARMOR);
        shooting_guardian = make(ctx, ExtraBotanyModelLayers.SHOOTING_GUARDIAN_INNER, ExtraBotanyModelLayers.SHOOTING_GUARDIAN_OUTER);
    }

    @Nullable
    public static ArmorModel get(ItemStack stack) {
        Item item = stack.getItem();
        return switch (item){
            case ShadowWarriorArmorItem armor -> shadow_warrior.get(armor.getEquipmentSlot());
            case GoblinsLayerArmorItem armor -> goblins_layer.get(armor.getEquipmentSlot());
            case ShootingGuardianArmorItem armor -> shooting_guardian.get(armor.getEquipmentSlot());
            case MaidArmorItem armor -> maid.get(armor.getEquipmentSlot());
            case MikuArmorItem armor -> miku.get(armor.getEquipmentSlot());
            default -> null;
        };
    }
}
