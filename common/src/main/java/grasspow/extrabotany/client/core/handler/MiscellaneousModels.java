package grasspow.extrabotany.client.core.handler;

import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.resources.ResourceManager;

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

    public void onModelRegister(ResourceManager rm, Consumer<ResourceLocation> consumer) {
		modelConsumers.keySet().forEach(consumer);

        if (!registeredModels) {
            registeredModels = true;
        }
    }

    public BakedModel modifyModelAfterbake(BakedModel bakedModel, ResourceLocation id) {
		modelConsumers.getOrDefault(id, model -> {}).accept(bakedModel);
		return afterBakeModifiers.getOrDefault(id, Function.identity()).apply(bakedModel);
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
