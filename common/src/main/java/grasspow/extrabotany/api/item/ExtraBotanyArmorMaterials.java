package grasspow.extrabotany.api.item;

import grasspow.extrabotany.common.item.ExtraBotanyItems;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;
import vazkii.botania.common.handler.BotaniaSounds;
import vazkii.botania.common.helper.RegistryHelper;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;

public class ExtraBotanyArmorMaterials {
    private static final List<RegistryHelper.HolderProxy<ArmorMaterial>> ALL = new ArrayList<>();
    
    public static final Holder<ArmorMaterial> MIKU = create("miku",
			Map.of(
					ArmorItem.Type.BOOTS, 2,
                    ArmorItem.Type.CHESTPLATE, 4,
					ArmorItem.Type.LEGGINGS, 5,
					ArmorItem.Type.HELMET, 1
			),
			22, BotaniaSounds.equipManasteel, () -> Ingredient.of(ExtraBotanyItems.manaDrink), 0);

    public static final Holder<ArmorMaterial> MAID = create("maid",
			Map.of(
					ArmorItem.Type.BOOTS, 4,
                    ArmorItem.Type.CHESTPLATE, 7,
					ArmorItem.Type.LEGGINGS, 9,
					ArmorItem.Type.HELMET, 4
			),
			22, BotaniaSounds.equipManaweave, () -> Ingredient.of(ExtraBotanyItems.goldCloth), 3);

    public static final Holder<ArmorMaterial> GOBLINS_LAYER = create("goblins_layer",
			Map.of(
					ArmorItem.Type.BOOTS, 3,
                    ArmorItem.Type.CHESTPLATE, 5,
					ArmorItem.Type.LEGGINGS, 7,
					ArmorItem.Type.HELMET, 2
			),
			30, BotaniaSounds.equipManasteel, () -> Ingredient.of(ExtraBotanyItems.photonium), 1);

    public static final Holder<ArmorMaterial> SHADOW_WARRIOR = create("shadow_warrior",
			Map.of(
					ArmorItem.Type.BOOTS, 2,
                    ArmorItem.Type.CHESTPLATE, 5,
					ArmorItem.Type.LEGGINGS, 6,
					ArmorItem.Type.HELMET, 2
			),
			26, BotaniaSounds.equipManasteel, () -> Ingredient.of(ExtraBotanyItems.shadowium), 1);

    public static final Holder<ArmorMaterial> SHOOTING_GUARDIAN = create("shooting_guardian",
			Map.of(
					ArmorItem.Type.BOOTS, 3,
                    ArmorItem.Type.CHESTPLATE, 7,
					ArmorItem.Type.LEGGINGS, 8,
					ArmorItem.Type.HELMET, 9
			),
			34, BotaniaSounds.equipTerrasteel, () -> Ingredient.of(ExtraBotanyItems.orichalcos), 2);

    public static final Holder<ArmorMaterial> SILENT_SAGES = create("silent_sages",
			Map.of(
					ArmorItem.Type.BOOTS, 4,
                    ArmorItem.Type.CHESTPLATE, 8,
					ArmorItem.Type.LEGGINGS, 9,
					ArmorItem.Type.HELMET, 5
			),
			40, BotaniaSounds.equipTerrasteel, () -> Ingredient.of(ExtraBotanyItems.orichalcos), 3);

    private static Supplier<Holder<SoundEvent>> getSoundEventHolder(SoundEvent soundEvent) {
        return () -> BuiltInRegistries.SOUND_EVENT.getHolder(soundEvent.getLocation()).orElseThrow();
    }
    
    private static Holder<ArmorMaterial> create(
			String name,
			Map<ArmorItem.Type, Integer> defense,
			int enchantmentValue,
			Holder<SoundEvent> equipSound,
			Supplier<Ingredient> repairIngredient,
			float toughness) {
		List<ArmorMaterial.Layer> list = List.of(new ArmorMaterial.Layer(exbotRL(name)));
		return create(name, defense, enchantmentValue, equipSound, toughness, repairIngredient, list);
	}

	private static Holder<ArmorMaterial> create(
			String name,
			Map<ArmorItem.Type, Integer> defense,
			int enchantmentValue,
			Holder<SoundEvent> equipSound,
			float toughness,
			Supplier<Ingredient> repairIngredient,
			List<ArmorMaterial.Layer> layers) {
		EnumMap<ArmorItem.Type, Integer> enummap = new EnumMap<>(ArmorItem.Type.class);

		for (ArmorItem.Type armoritem$type : ArmorItem.Type.values()) {
			enummap.put(armoritem$type, defense.get(armoritem$type));
		}

		ResourceLocation id = exbotRL(name);
		RegistryHelper.HolderProxy<ArmorMaterial> proxy = RegistryHelper.lazyHolderProxy(Registries.ARMOR_MATERIAL, id,
				() -> new ArmorMaterial(enummap, enchantmentValue, equipSound, repairIngredient, layers, toughness, 0));
		ALL.add(proxy);
		return proxy;
	}

	public static void registerArmorMaterials(Registry<ArmorMaterial> registry) {
		ALL.forEach(proxy -> proxy.register(registry));
	}
}
