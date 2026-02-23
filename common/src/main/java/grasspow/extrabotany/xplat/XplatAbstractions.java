package grasspow.extrabotany.xplat;

import grasspow.extrabotany.api.NatureOrb;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.api.ServiceUtil;

public interface XplatAbstractions {
    // Yes, this forms a loop by default. Each loader overrides their own to break the loop
    default boolean isFabric() {
        return !isNeoForge();
    }

    default boolean isNeoForge() {
        return !isFabric();
    }

    boolean isModLoaded(String modId);

    boolean isDevEnvironment();

    boolean isPhysicalClient();

    String getExtraBotanyVersion();

    NatureOrb findNatureOrbItem(ItemStack stack);

    XplatAbstractions INSTANCE = ServiceUtil.findService(XplatAbstractions.class, null);

    static XplatAbstractions instance() {
        return INSTANCE;
    }
}
