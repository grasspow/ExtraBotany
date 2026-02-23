package grasspow.extrabotany.common.effect.brew;

import grasspow.extrabotany.common.lib.LibBrewNames;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.PotionContents;
import vazkii.botania.api.brew.Brew;

import java.util.Arrays;
import java.util.function.BiConsumer;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;

public class ExtraBotanyBrews {
    public static final Brew revolution = make(10000, new MobEffectInstance(MobEffects.UNLUCK, 1800, 2),
            new MobEffectInstance(MobEffects.DIG_SPEED, 1800, 2));
    public static final Brew shell = make(10000, new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 1200, 2),
            new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 2));
    public static final Brew all_mighty = make(30000, new MobEffectInstance(MobEffects.ABSORPTION, 900, 0),
            new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 900, 0), new MobEffectInstance(MobEffects.DIG_SPEED, 900, 0),
            new MobEffectInstance(MobEffects.JUMP, 900, 0), new MobEffectInstance(MobEffects.LUCK, 900, 0),
            new MobEffectInstance(MobEffects.REGENERATION, 900, 0), new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 900, 0),
            new MobEffectInstance(MobEffects.DAMAGE_BOOST, 900, 0));
    public static final Brew deadpool = make(20000, new MobEffectInstance(MobEffects.WITHER, 300, 1),
            new MobEffectInstance(MobEffects.POISON, 300, 1), new MobEffectInstance(MobEffects.GLOWING, 3600, 2),
            new MobEffectInstance(MobEffects.DAMAGE_BOOST, 3600, 2));
    public static final Brew floating = make(2000, new MobEffectInstance(MobEffects.LEVITATION, 160, 2));

    public static void submitRegistrations(BiConsumer<Brew, ResourceLocation> r) {
        r.accept(revolution, exbotRL(LibBrewNames.REVOLUTION));
        r.accept(shell, exbotRL(LibBrewNames.SHELL));
        r.accept(all_mighty, exbotRL(LibBrewNames.ALL_MIGHTY));
        r.accept(deadpool, exbotRL(LibBrewNames.DEADPOOL));
        r.accept(floating, exbotRL(LibBrewNames.FLOATING));
    }

    private static Brew make(int cost, MobEffectInstance... effects) {
        return new Brew(PotionContents.getColor(Arrays.asList(effects)), cost, effects);
    }
}
