package grasspow.extrabotany.data.tag;

import grasspow.extrabotany.common.lib.ExtraBotanyDamageTypes;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.DamageTypeTags;
import net.minecraft.world.damagesource.DamageType;

import java.util.concurrent.CompletableFuture;

public class DamageTypeTagProvider extends TagsProvider<DamageType> {
    public DamageTypeTagProvider(PackOutput packOutput, CompletableFuture<HolderLookup.Provider> lookupProvider) {
        super(packOutput, Registries.DAMAGE_TYPE, lookupProvider);
    }

    @Override
    protected void addTags(HolderLookup.Provider pProvider) {
        this.tag(DamageTypeTags.BYPASSES_ARMOR).add(ExtraBotanyDamageTypes.GENERAL_ARMOR_PIERCING);
        this.tag(DamageTypeTags.BYPASSES_ARMOR).add(ExtraBotanyDamageTypes.MAGIC_ARMOR_PIERCING);

        this.tag(DamageTypeTags.BYPASSES_COOLDOWN).add(ExtraBotanyDamageTypes.CRITICAL);

        this.tag(DamageTypeTags.BYPASSES_EFFECTS).add(ExtraBotanyDamageTypes.CRITICAL);

        this.tag(DamageTypeTags.BYPASSES_ENCHANTMENTS).add(ExtraBotanyDamageTypes.CRITICAL);

        this.tag(DamageTypeTags.BYPASSES_INVULNERABILITY).add(ExtraBotanyDamageTypes.CRITICAL);

        this.tag(DamageTypeTags.BYPASSES_RESISTANCE).add(ExtraBotanyDamageTypes.CRITICAL);

        this.tag(DamageTypeTags.BYPASSES_SHIELD).add(ExtraBotanyDamageTypes.CRITICAL);
    }
}
