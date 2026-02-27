package grasspow.extrabotany.client.model;

import grasspow.extrabotany.client.model.armor.*;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.builders.LayerDefinition;

import java.util.function.BiConsumer;
import java.util.function.Supplier;

public class ExtraBotanyLayerDefinitions {
    public static void init(BiConsumer<ModelLayerLocation, Supplier<LayerDefinition>> consumer) {
        consumer.accept(ExtraBotanyModelLayers.SHADOW_INNER_ARMOR, () -> LayerDefinition.create(ShadowArmorModel.createInsideMesh(), 128, 128));
        consumer.accept(ExtraBotanyModelLayers.SHADOW_OUTER_ARMOR, () -> LayerDefinition.create(ShadowArmorModel.createOutsideMesh(), 128, 128));
        consumer.accept(ExtraBotanyModelLayers.GOBLIN_SLAYER_INNER, () -> LayerDefinition.create(GoblinArmorModel.createInsideMesh(), 128, 128));
        consumer.accept(ExtraBotanyModelLayers.GOBLIN_SLAYER_OUTER, () -> LayerDefinition.create(GoblinArmorModel.createOutsideMesh(), 128, 128));
        consumer.accept(ExtraBotanyModelLayers.MIKU_INNER, () -> LayerDefinition.create(MikuArmorModel.createInsideMesh(), 128, 128));
        consumer.accept(ExtraBotanyModelLayers.MIKU_OUTER, () -> LayerDefinition.create(MikuArmorModel.createOutsideMesh(), 128, 128));
        consumer.accept(ExtraBotanyModelLayers.MAID_INNER, () -> LayerDefinition.create(MaidArmorModel.createInsideMesh(), 128, 128));
        consumer.accept(ExtraBotanyModelLayers.MAID_OUTER, () -> LayerDefinition.create(MaidArmorModel.createOutsideMesh(), 128, 128));
        consumer.accept(ExtraBotanyModelLayers.SHOOTING_GUARDIAN_INNER, () -> LayerDefinition.create(ShootingGuardianModel.createInsideMesh(), 128, 128));
        consumer.accept(ExtraBotanyModelLayers.SHOOTING_GUARDIAN_OUTER, () -> LayerDefinition.create(ShootingGuardianModel.createOutsideMesh(), 128, 128));
    }
}
