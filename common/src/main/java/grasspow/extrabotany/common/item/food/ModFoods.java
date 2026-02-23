package grasspow.extrabotany.common.item.food;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoods {
    public static final FoodProperties SPIRIT_FUEL = new FoodProperties.Builder()
            .nutrition(4).saturationModifier(0.3F)
            .alwaysEdible()
            .effect(new MobEffectInstance(MobEffects.HEAL, 2, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.NIGHT_VISION, 500, 0), 1.0F)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 500, 0), 1.0F)
            .effect(new MobEffectInstance(MobEffects.LUCK, 500, 0), 1.0F)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 500, 0), 1.0F)
            .build();

    public static final FoodProperties NIGHTMARE_FUEL = new FoodProperties.Builder()
            .nutrition(4).saturationModifier(0.3F)
            .alwaysEdible()
            .effect(new MobEffectInstance(MobEffects.HARM, 1, 1), 1.0F)
            .effect(new MobEffectInstance(MobEffects.BLINDNESS, 500), 1.0F)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 500), 1.0F)
            .effect(new MobEffectInstance(MobEffects.UNLUCK, 500), 1.0F)
            .effect(new MobEffectInstance(MobEffects.WEAKNESS, 500), 1.0F)
            .build();

    public static final FoodProperties GILDED_MASHED_POTATO = new FoodProperties.Builder()
            .nutrition(5).saturationModifier(0.2F)
            .alwaysEdible()
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 600, 3), 1.0F)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 600, 3), 1.0F)
            .effect(new MobEffectInstance(MobEffects.ABSORPTION, 600, 1), 1.0F)
            .build();

    public static final FoodProperties MANA_DRINK = new FoodProperties.Builder()
            .nutrition(0).saturationModifier(1F)
            .alwaysEdible()
            .effect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 1200, 0), 1.0F)
            .effect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 1200, 0), 1.0F)
            .effect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 1200, 0), 1.0F)
            .effect(new MobEffectInstance(MobEffects.JUMP, 1200, 0), 1.0F)
            .build();

    public static final FoodProperties FRIED_CHICKEN = new FoodProperties.Builder().nutrition(6).saturationModifier(0.5F).build();
}
