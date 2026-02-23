package grasspow.extrabotany.neoforge.data.neoforge;

import grasspow.extrabotany.data.tag.BlockTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;

import java.util.concurrent.CompletableFuture;

public class NeoForgeBlockTagProvider extends BlockTagProvider {
    public NeoForgeBlockTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {

    }
}
