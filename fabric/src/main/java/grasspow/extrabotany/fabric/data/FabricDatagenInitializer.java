package grasspow.extrabotany.fabric.data;

import grasspow.extrabotany.data.AdvancementProvider;
import grasspow.extrabotany.data.lang.EnUsProvider;
import grasspow.extrabotany.data.lang.LanguageHelper;
import grasspow.extrabotany.data.lang.ZhCnProvider;
import grasspow.extrabotany.data.loot.ExtraBotanyBlockLoot;
import grasspow.extrabotany.data.model.BlockstateProvider;
import grasspow.extrabotany.data.model.ItemModelProvider;
import grasspow.extrabotany.data.tag.BlockTagProvider;
import grasspow.extrabotany.data.tag.EntityTagProvider;
import grasspow.extrabotany.data.tag.ItemTagProvider;
import grasspow.extrabotany.fabric.data.fabric.FabricBlockLootProvider;
import grasspow.extrabotany.fabric.data.fabric.FabricBlockTagProvider;
import grasspow.extrabotany.fabric.data.fabric.FabricItemTagProvider;
import grasspow.extrabotany.data.model.FloatingFlowerModelProvider;
import grasspow.extrabotany.fabric.data.xplat.recipes.*;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.world.damagesource.DamageType;

import static grasspow.extrabotany.common.lib.ExtraBotanyDamageTypes.*;


public class FabricDatagenInitializer implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator generator) {
        if (System.getProperty("extrabotany.xplat_datagen") != null) {
            configureXplatDatagen(generator.createPack());
        } else {
            configureFabricDatagen(generator.createPack());
        }
    }

    private static void configureFabricDatagen(FabricDataGenerator.Pack pack) {
        pack.addProvider(FabricBlockLootProvider::new);
        var blockTagProvider = pack.addProvider(FabricBlockTagProvider::new);
        pack.addProvider((output, registriesFuture) -> new FabricItemTagProvider(output, registriesFuture, blockTagProvider.contentsGetter()));
    }

    private static void configureXplatDatagen(FabricDataGenerator.Pack pack) {
        pack.addProvider(ExtraBotanyBlockLootWrapper.wrap(ExtraBotanyBlockLoot::new));
        BlockTagProvider blockTagProvider = pack.addProvider(BlockTagProvider::new);
        pack.addProvider((output, registriesFuture) -> new ItemTagProvider(output, registriesFuture, blockTagProvider.contentsGetter()));
        pack.addProvider(EntityTagProvider::new);
        pack.addProvider(ExtraBotanyDynamicRegistryProvider::new);
//        pack.addProvider(DamageTypeTagProvider::new);
        pack.addProvider(CraftingRecipeProvider::new);
        pack.addProvider(ManaInfusionProvider::new);
        pack.addProvider(BrewProvider::new);
        pack.addProvider(PetalApothecaryProvider::new);
        pack.addProvider(RunicAltarProvider::new);
        pack.addProvider(TerrestrialAgglomerationProvider::new);
        pack.addProvider((PackOutput output) -> new BlockstateProvider(output));
        pack.addProvider((PackOutput output) -> new FloatingFlowerModelProvider(output));
        pack.addProvider((PackOutput output) -> new ItemModelProvider(output));
        pack.addProvider(AdvancementProvider::create);
        LanguageHelper.init();
        pack.addProvider((PackOutput output) -> new EnUsProvider(output));
        pack.addProvider((PackOutput output) -> new ZhCnProvider(output));
    }

    @Override
    public void buildRegistry(RegistrySetBuilder builder) {
        builder.add(Registries.DAMAGE_TYPE, FabricDatagenInitializer::damageTypeBC);
    }

    protected static void damageTypeBC(BootstrapContext<DamageType> context) {
        context.register(GENERAL_ARMOR_PIERCING, GENERAL_AP);
        context.register(MAGIC_ARMOR_PIERCING, MAGIC_AP);
        context.register(CRITICAL, CRI);
    }
}
