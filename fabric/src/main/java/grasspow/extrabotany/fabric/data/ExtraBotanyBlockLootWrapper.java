/*
 * This class is distributed as part of the Botania Mod.
 * Get the Source Code in github:
 * https://github.com/Vazkii/Botania
 *
 * Botania is Open Source and distributed under the
 * Botania License: http://botaniamod.net/license.php
 *
 */

package grasspow.extrabotany.fabric.data;

import grasspow.extrabotany.data.loot.ExtraBotanyBlockLoot;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;
import java.util.function.Function;

/**
 * Wrapper for executing BlockLootSubProvider logic in Fabric context without having to extend Fabric classes.
 */
public class ExtraBotanyBlockLootWrapper extends FabricBlockLootTableProvider {
	private final Function<HolderLookup.Provider, ExtraBotanyBlockLoot> blockLootProvider;
	private final CompletableFuture<HolderLookup.Provider> registryLookup;

	private ExtraBotanyBlockLootWrapper(Function<HolderLookup.Provider, ExtraBotanyBlockLoot> blockLootProvider,
                                        FabricDataOutput dataOutput, CompletableFuture<HolderLookup.Provider> registryLookup) {
		super(dataOutput, registryLookup);
		this.blockLootProvider = blockLootProvider;
		this.registryLookup = registryLookup;
	}

	public static FabricDataGenerator.Pack.RegistryDependentFactory<ExtraBotanyBlockLootWrapper> wrap(
			Function<HolderLookup.Provider, ExtraBotanyBlockLoot> vanillaSubProvider) {
		return (output, registriesFuture) -> new ExtraBotanyBlockLootWrapper(
				vanillaSubProvider, output, registriesFuture);
	}

	@Override
	public void generate() {
		var generator = blockLootProvider.apply(registryLookup.join());
		generator.generate();
		this.map.putAll(generator.getMap());
	}
}
