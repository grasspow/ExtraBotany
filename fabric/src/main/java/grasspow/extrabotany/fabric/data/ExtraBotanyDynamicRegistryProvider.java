package grasspow.extrabotany.fabric.data;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

import static grasspow.extrabotany.common.lib.ExtraBotanyDamageTypes.*;

public class ExtraBotanyDynamicRegistryProvider extends FabricDynamicRegistryProvider {

	public ExtraBotanyDynamicRegistryProvider(FabricDataOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
		super(output, registriesFuture);
	}

	@Override
	protected void configure(HolderLookup.Provider registries, Entries entries) {
		entries.add(GENERAL_ARMOR_PIERCING,GENERAL_AP);
		entries.add(MAGIC_ARMOR_PIERCING,MAGIC_AP);
		entries.add(CRITICAL,CRI);
	}

	@Override
	public String getName() {
		return "ExtraBotanyDynamicRegistryProvider";
	}
}
