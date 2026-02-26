package grasspow.extrabotany.neoforge.data.neoforge;

import grasspow.extrabotany.data.tag.ItemTagProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

import static grasspow.extrabotany.common.item.ExtraBotanyItems.*;

public class NeoForgeItemTagProvider extends ItemTagProvider {
    public NeoForgeItemTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTagProvider) {
        super(packOutput, lookupProvider, blockTagProvider);
    }

    @Override
    public String getName() {
        return "ExtraBotany item tags (NeoForge-specific)";
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        generateAccessoryTags();
        generateCompatTags();
    }

    private void generateAccessoryTags() {
        Stream.of(
                potatoChips,
                foxEar, foxMask, pylon, blackGrasses, superCrown, thugLife, mask
        ).forEach(tag(accessory("head"))::add);
        Stream.of(
                moonPendant
        ).forEach(tag(accessory("necklace"))::add);
        Stream.of(
                redScarf,coreGod
        ).forEach(tag(accessory("body"))::add);
        Stream.of(
                frostStar, deathRing, manaDriveRing, sunRing,
                sagesManaRing
        ).forEach(tag(accessory("ring"))::add);
        Stream.of(
                aeroStone, aquaStone, earthStone, ignisStone, theCommunity,
                peaceAmulet, powerGrove, natureOrb, jingweiFeather
        ).forEach(tag(accessory("curio"))::add);
    }

    private void generateCompatTags() {
    }

    private static TagKey<Item> accessory(String name) {
        return ItemTags.create(ResourceLocation.fromNamespaceAndPath("curios", name));
    }

    private static TagKey<Item> itemTag(ResourceLocation location) {
        return TagKey.create(Registries.ITEM, location);
    }
}
