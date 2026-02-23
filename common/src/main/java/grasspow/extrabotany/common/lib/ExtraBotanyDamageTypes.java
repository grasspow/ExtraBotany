package grasspow.extrabotany.common.lib;

import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.damagesource.*;
import net.minecraft.world.entity.Entity;
import org.jetbrains.annotations.Nullable;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;


public class ExtraBotanyDamageTypes {
    public static final ResourceKey<DamageType> GENERAL_ARMOR_PIERCING = ResourceKey.create(Registries.DAMAGE_TYPE, exbotRL("general_armor_piercing"));
    public static final ResourceKey<DamageType> MAGIC_ARMOR_PIERCING = ResourceKey.create(Registries.DAMAGE_TYPE, exbotRL("magic_armor_piercing"));
    public static final ResourceKey<DamageType> CRITICAL = ResourceKey.create(Registries.DAMAGE_TYPE, exbotRL("critical"));

    public static final DamageType GENERAL_AP=new DamageType("general_armor_piercing", DamageScaling.ALWAYS, 0F, DamageEffects.HURT, DeathMessageType.DEFAULT);
    public static final DamageType MAGIC_AP=new DamageType("magic_armor_piercing", DamageScaling.ALWAYS, 0F, DamageEffects.HURT, DeathMessageType.DEFAULT);
    public static final DamageType CRI=new DamageType("critical", DamageScaling.ALWAYS, 0F, DamageEffects.HURT, DeathMessageType.DEFAULT);

    public static class Sources {
        private static Holder.Reference<DamageType> getHolder(RegistryAccess ra, ResourceKey<DamageType> key) {
            return ra.registryOrThrow(Registries.DAMAGE_TYPE).getHolderOrThrow(key);
        }

        private static DamageSource source(RegistryAccess ra, ResourceKey<DamageType> resourceKey) {
            return new DamageSource(getHolder(ra, resourceKey));
        }

        private static DamageSource source(RegistryAccess ra, ResourceKey<DamageType> resourceKey, @Nullable Entity entity) {
            return new DamageSource(getHolder(ra, resourceKey), entity);
        }

        private static DamageSource source(RegistryAccess ra, ResourceKey<DamageType> resourceKey, @Nullable Entity entity, @Nullable Entity entity2) {
            return new DamageSource(getHolder(ra, resourceKey), entity, entity2);
        }

        public static DamageSource generalArmorPiercing(RegistryAccess ra, Entity entity) {
            return source(ra, GENERAL_ARMOR_PIERCING, entity);
        }

        public static DamageSource magicArmorPiercing(RegistryAccess ra, Entity entity) {
            return source(ra, MAGIC_ARMOR_PIERCING, entity);
        }

        public static DamageSource critical(RegistryAccess ra, Entity entity) {
            return source(ra, CRITICAL, entity);
        }
    }
}
