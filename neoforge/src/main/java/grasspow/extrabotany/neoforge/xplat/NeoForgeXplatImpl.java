package grasspow.extrabotany.neoforge.xplat;

import grasspow.extrabotany.api.ExtraBotanyAPI;
import grasspow.extrabotany.api.ExtraBotanyNeoForgeCapabilities;
import grasspow.extrabotany.api.NatureOrb;
import grasspow.extrabotany.xplat.XplatAbstractions;
import net.minecraft.world.item.ItemStack;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModList;
import net.neoforged.fml.loading.FMLLoader;

public class NeoForgeXplatImpl implements XplatAbstractions {
    @Override
    public boolean isNeoForge() {
        return true;
    }

    @Override
    public boolean isModLoaded(String modId) {
        return ModList.get().isLoaded(modId);
    }

    @Override
    public boolean isDevEnvironment() {
        return !FMLLoader.isProduction();
    }

    @Override
    public boolean isPhysicalClient() {
        return FMLLoader.getDist() == Dist.CLIENT;
    }

    @Override
    public String getExtraBotanyVersion() {
        return ModList.get().getModContainerById(ExtraBotanyAPI.MODID).orElseThrow()
                .getModInfo().getVersion().toString();
    }

    @Override
    public NatureOrb findNatureOrbItem(ItemStack stack) {
        return stack.getCapability(ExtraBotanyNeoForgeCapabilities.NATURE_ORB);
    }
}
