package grasspow.extrabotany.fabric.data.fabric;

import grasspow.extrabotany.data.tag.ItemTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;

import static grasspow.extrabotany.common.item.ExtraBotanyItems.*;

public class FabricItemTagProvider extends ItemTagProvider {
    public FabricItemTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTagProvider) {
        super(packOutput, lookupProvider, blockTagProvider);
    }

    @Override
    public String getName() {
        return "ExtraBotany item tags (Fabric-specific)";
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        generateAccessoryTags();
        generateCompatTags();
    }

    private void generateAccessoryTags() {
        Item[] hat = {
                potatoChips,
                foxEar, foxMask, pylon, blackGrasses, superCrown, thugLife, mask};
        tag(accessory("head/hat")).add(hat);

        Item[] necklace = {
                moonPendant,
                redScarf
//                ,CORE_GOD
        };
        tag(accessory("chest/necklace")).add(necklace);

        Item[] ring = {
                frostStar, deathRing, manaDriveRing, sunRing,
                sagesManaRing
        };
        tag(accessory("hand/ring")).add(ring);
        tag(accessory("offhand/ring")).add(ring);

        Item[] all = {
                aeroStone, aquaStone, earthStone, ignisStone, theCommunity,
                peaceAmulet, powerGrove, natureOrb, jingweiFeather};
        tag(accessory("all")).add(all);
    }

    private void generateCompatTags() {
    }

    private static TagKey<Item> accessory(String name) {
        return itemTag(ResourceLocation.fromNamespaceAndPath("trinkets", name));
    }

    private static TagKey<Item> itemTag(ResourceLocation location) {
        return TagKey.create(Registries.ITEM, location);
    }
}
