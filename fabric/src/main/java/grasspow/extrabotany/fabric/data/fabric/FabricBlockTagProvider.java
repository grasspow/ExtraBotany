package grasspow.extrabotany.fabric.data.fabric;

import grasspow.extrabotany.data.tag.BlockTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;

import java.util.concurrent.CompletableFuture;

public class FabricBlockTagProvider extends BlockTagProvider {
    public FabricBlockTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

    }
}
