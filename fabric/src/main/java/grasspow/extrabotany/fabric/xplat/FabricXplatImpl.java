package grasspow.extrabotany.fabric.xplat;

import grasspow.extrabotany.api.ExtraBotanyAPI;
import grasspow.extrabotany.api.ExtraBotanyFabricCapabilities;
import grasspow.extrabotany.api.NatureOrb;
import grasspow.extrabotany.xplat.XplatAbstractions;
import net.fabricmc.api.EnvType;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.util.Unit;
import net.minecraft.world.item.ItemStack;

public class FabricXplatImpl implements XplatAbstractions {
    @Override
    public boolean isFabric() {
        return true;
    }

    @Override
    public boolean isModLoaded(String modId) {
        return FabricLoader.getInstance().isModLoaded(modId);
    }

    @Override
    public boolean isDevEnvironment() {
        return FabricLoader.getInstance().isDevelopmentEnvironment();
    }

    @Override
    public boolean isPhysicalClient() {
        return FabricLoader.getInstance().getEnvironmentType() == EnvType.CLIENT;
    }

    @Override
    public String getExtraBotanyVersion() {
        return FabricLoader.getInstance().getModContainer(ExtraBotanyAPI.MODID).orElseThrow()
                .getMetadata().getVersion().getFriendlyString();
    }

    @Override
    public NatureOrb findNatureOrbItem(ItemStack stack) {
        return ExtraBotanyFabricCapabilities.NATURE_ORB.find(stack, Unit.INSTANCE);
    }
}
