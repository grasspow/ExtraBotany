package grasspow.extrabotany.api;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.CreativeModeTab;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;

public class ExtraBotanyRegistries {
    public static final ResourceKey<CreativeModeTab> EXBOT_TAB_KEY = ResourceKey.create(Registries.CREATIVE_MODE_TAB,
            exbotRL("extrabotany"));
}
