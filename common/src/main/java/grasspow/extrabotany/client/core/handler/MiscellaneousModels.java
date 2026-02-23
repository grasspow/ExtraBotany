package grasspow.extrabotany.client.core.handler;

import grasspow.extrabotany.api.ExtraBotanyAPI;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.client.resources.model.ModelBakery;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;
import org.jetbrains.annotations.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;

public class MiscellaneousModels {

    public static final MiscellaneousModels INSTANCE = new MiscellaneousModels();

    private final Map<ResourceLocation, Consumer<BakedModel>> modelConsumers;
    private final Map<ResourceLocation, Function<BakedModel, BakedModel>> afterBakeModifiers;

    public boolean registeredModels = false;
    public BakedModel influxWaverProjectileModel;
    public BakedModel trueShadowKatanaProjectileModel;
    public BakedModel trueTerraBladeProjectileModel;
    public BakedModel[] firstFractalWeaponModels = new BakedModel[10];
    public BakedModel[] coreGodWingsModel = new BakedModel[4];
    public BakedModel coreGodModel;

    //neoforge
    public void onModelBake(ModelBakery loader, Map<ModelResourceLocation, BakedModel> map) {
		if (!registeredModels) {
			ExtraBotanyAPI.LOGGER.error("Additional models failed to register! Aborting baking models to avoid early crashing.");
			return;
		}
		afterBakeModifiers.forEach((resourceLocation, afterBakeModifier) -> map
				.computeIfPresent(new ModelResourceLocation(resourceLocation, ""),
						(resourceLoc, bakedModel) -> afterBakeModifier.apply(bakedModel)));
		modelConsumers.forEach((resourceLocation, bakedModelConsumer) -> bakedModelConsumer
				.accept(map.get(new ModelResourceLocation(resourceLocation, "standalone"))));
	}

    // Fabric
	public BakedModel modifyModelAfterbake(BakedModel bakedModel, @Nullable ResourceLocation id) {
		if (id == null) {
			return bakedModel;
		}
		modelConsumers.getOrDefault(id, model -> {}).accept(bakedModel);
		return afterBakeModifiers.getOrDefault(stripBlockPrefix(id), Function.identity()).apply(bakedModel);
	}

    private ResourceLocation stripBlockPrefix(ResourceLocation id) {
        String path = id.getPath();
        return ResourceLocation.fromNamespaceAndPath(id.getNamespace(), path.startsWith("block/") ? path.substring(6) : path);
    }

    public void onModelRegister(ResourceManager rm, Consumer<ResourceLocation> consumer) {
		modelConsumers.keySet().forEach(consumer);

        if (!registeredModels) {
            registeredModels = true;
        }
    }


    private MiscellaneousModels() {
        afterBakeModifiers = new HashMap<>();

        modelConsumers = new HashMap<>();
        modelConsumers.put(exbotRL("icon/influx_waver_projectile"), bakedModel -> this.influxWaverProjectileModel = bakedModel);
        modelConsumers.put(exbotRL("icon/true_shadow_katana_projectile"), bakedModel -> this.trueShadowKatanaProjectileModel = bakedModel);
        modelConsumers.put(exbotRL("icon/true_terra_blade_projectile"), bakedModel -> this.trueTerraBladeProjectileModel = bakedModel);

        for (int i = 0; i < 10; i++) {
            int finalI = i;
            modelConsumers.put(exbotRL("icon/sword_domain_" + i), bakedModel -> this.firstFractalWeaponModels[finalI] = bakedModel);
        }
        for (int i = 0; i < 4; i++) {
            int finalI = i;
            modelConsumers.put(exbotRL("icon/wing_" + i), bakedModel -> this.coreGodWingsModel[finalI] = bakedModel);
        }
        modelConsumers.put(exbotRL("icon/wing_core_god"), bakedModel -> this.coreGodModel = bakedModel);
    }
}
