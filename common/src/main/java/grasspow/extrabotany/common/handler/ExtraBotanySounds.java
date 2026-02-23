package grasspow.extrabotany.common.handler;

import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import vazkii.botania.common.helper.RegistryHelper;

import java.util.ArrayList;
import java.util.List;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;

public final class ExtraBotanySounds {
    private static final List<RegistryHelper.HolderProxy<SoundEvent>> SOUND_EVENTS = new ArrayList<>();

    public static final SoundEvent CYCLONE = makeSoundEvent("cyclone");
    public static final SoundEvent RIDEON = makeSoundEvent("rideon");
    public static final SoundEvent SHOOT = makeSoundEvent("shoot");
    public static final SoundEvent SLASH = makeSoundEvent("slash");
    public static final SoundEvent FLAMESCIONULT = makeSoundEvent("flamescionult");

    public static final SoundEvent SWORDLAND = makeSoundEvent("music.ego");
    public static final SoundEvent SALVATION = makeSoundEvent("music.herrscher");

    	private static SoundEvent makeSoundEvent(String name) {
		return makeSoundEventHolder(name).value();
	}

	private static Holder<SoundEvent> makeSoundEventHolder(String name) {
		ResourceLocation id = exbotRL(name);
		RegistryHelper.HolderProxy<SoundEvent> proxy = RegistryHelper.holderProxy(Registries.SOUND_EVENT, id,
				SoundEvent.createVariableRangeEvent(id));
        SOUND_EVENTS.add(proxy);
		return proxy;
	}

    public static void init(Registry<SoundEvent> registry) {
        SOUND_EVENTS.forEach(proxy -> proxy.register(registry));
    }
}
