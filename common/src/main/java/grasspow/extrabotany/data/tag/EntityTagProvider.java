package grasspow.extrabotany.data.tag;

import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.IntrinsicHolderTagsProvider;
import net.minecraft.world.entity.EntityType;

import java.util.concurrent.CompletableFuture;

public class EntityTagProvider extends IntrinsicHolderTagsProvider<EntityType<?>> {
    public EntityTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, Registries.ENTITY_TYPE, lookupProvider, (entityType) -> entityType.builtInRegistryHolder().key());
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
//        tag(BotaniaTags.Entities.SHADED_MESA_BLACKLIST).add(
//                ExtraBotanyEntities.EGO
//        );
//        tag(Tags.EntityTypes.BOSSES).add(ExtraBotanyEntities.EGO.get());
    }
}
