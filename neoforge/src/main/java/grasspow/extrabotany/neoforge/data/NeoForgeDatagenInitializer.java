package grasspow.extrabotany.neoforge.data;

import grasspow.extrabotany.api.ExtraBotanyAPI;
import grasspow.extrabotany.neoforge.data.neoforge.NeoForgeBlockTagProvider;
import grasspow.extrabotany.neoforge.data.neoforge.NeoForgeItemTagProvider;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.data.event.GatherDataEvent;

@EventBusSubscriber(modid = ExtraBotanyAPI.MODID)
public class NeoForgeDatagenInitializer {
    @SubscribeEvent
    public static void configureForgeDatagen(GatherDataEvent evt) {
        var generator = evt.getGenerator();
        var output = generator.getPackOutput();
        var blockTagProvider = new NeoForgeBlockTagProvider(output, evt.getLookupProvider());
        generator.addProvider(evt.includeServer(), blockTagProvider);
        generator.addProvider(evt.includeServer(), new NeoForgeItemTagProvider(output, evt.getLookupProvider(),
                blockTagProvider.contentsGetter()));


//
//        generator.addProvider(event.includeClient(), new BlockstateProvider(output));
//        generator.addProvider(event.includeClient(), new BlockstateForgeProvider(output, fileHelper));
//        generator.addProvider(event.includeClient(), new ItemModelProvider(output));
//        generator.addProvider(event.includeClient(), new FloatingFlowerModelProvider(output));
//
//        LanguageHelper.init();
//        generator.addProvider(event.includeClient(), new EnUsProvider(output));
//        generator.addProvider(event.includeClient(), new ZhCnProvider(output));
//
//        generator.addProvider(event.includeClient(), new SoundDefinitionsProvider(output, fileHelper));
//
//        generator.addProvider(event.includeServer(),
//                new DatapackBuiltinEntriesProvider(output, lookupProvider, new RegistrySetBuilder()
//                        .add(Registries.DAMAGE_TYPE, ExtraBotanyDamageTypes::bootstrap), Set.of(MOD_ID))
//        );
////        generator.addProvider(event.includeServer(), new DamageTypeTagProvider(output, lookupProvider));
//
//        generator.addProvider(event.includeServer(), new AdvancementProvider(output, lookupProvider, fileHelper));
//
//        generator.addProvider(event.includeServer(), new EntityTagProvider(output, lookupProvider));
//
//        generator.addProvider(event.includeServer(), new LootTableProvider(output));
//
//        generator.addProvider(event.includeServer(), new CraftingRecipeProvider(output));
//        generator.addProvider(event.includeServer(), new PetalApothecaryProvider(output));
//        generator.addProvider(event.includeServer(), new ManaInfusionProvider(output));
//        generator.addProvider(event.includeServer(), new RunicAltarProvider(output));
//        generator.addProvider(event.includeServer(), new PedestalClickProvider(output));
//        generator.addProvider(event.includeServer(), new BrewProvider(output));
//        generator.addProvider(event.includeServer(), new TerrestrialAgglomerationProvider(output));

    }
}
