package grasspow.extrabotany.common.component;

import com.mojang.serialization.Codec;
import grasspow.extrabotany.common.lib.LibComponentNames;
import net.minecraft.core.component.DataComponentType;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.ExtraCodecs;
import net.minecraft.util.Unit;
import net.minecraft.world.item.ItemStack;
import vazkii.botania.common.item.ManaBlasterItem;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;
import java.util.function.UnaryOperator;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;

public final class ExtraBotanyDataComponents {
    private static final Map<String, DataComponentType<?>> ALL = new HashMap<>();
    /**
     * ultimate hammer
     */
    public static final DataComponentType<Integer> REPAIR_UPGRADE = makeInteger(LibComponentNames.REPAIR_UPGRADE);
    public static final DataComponentType<Integer> DAMAGE_UPGRADE = makeInteger(LibComponentNames.DAMAGE_UPGRADE);
    public static final DataComponentType<Unit> RANGE_UPGRADE = makeUnit(LibComponentNames.RANGE_UPGRADE);

    /**
     * excaliber
     */
    public static final DataComponentType<String> ATTACKER = makeString(LibComponentNames.ATTACKER);
    public static final DataComponentType<Integer> HOME_ID = makeInteger(LibComponentNames.HOME_ID);

    /**
     * buddhist
     */
    public static final DataComponentType<List<ItemStack>> RELIC_DATA = makeItemList(LibComponentNames.RELIC_DATA);

    /**
     * armor
     */


    /**
     * nature
     */
    public static final DataComponentType<Integer> NATURE = makeInteger(LibComponentNames.NATURE);
    public static final DataComponentType<Integer> MAX_NATURE = makeInteger(LibComponentNames.MAX_NATURE);

    public static void registerComponents(BiConsumer<DataComponentType<?>, ResourceLocation> biConsumer) {
        for (Map.Entry<String, DataComponentType<?>> entry : ALL.entrySet()) {
            biConsumer.accept(entry.getValue(), exbotRL(entry.getKey()));
        }
    }

    private static <T> DataComponentType<T> make(String name, UnaryOperator<DataComponentType.Builder<T>> builder) {
        if (!name.matches("[a-z]+(?:_[a-z0-9]+)*")) {
            throw new IllegalArgumentException("Typo? Name should be in snake_case: " + name);
        }
        DataComponentType<T> type = builder.apply(DataComponentType.builder()).build();
        var old = ALL.put(name, type);
        if (old != null) {
            throw new IllegalArgumentException("Typo? Duplicate name " + name);
        }
        return type;
    }

    private static DataComponentType<Unit> makeUnit(String name) {
        return make(name, builder -> builder.persistent(Unit.CODEC).networkSynchronized(StreamCodec.unit(Unit.INSTANCE)));
    }

    private static DataComponentType<Unit> makeTransientUnit(String name) {
        return make(name, builder -> builder.networkSynchronized(StreamCodec.unit(Unit.INSTANCE)));
    }
    private static DataComponentType<String> makeString(String name) {
        return make(name, builder -> builder.persistent(Codec.STRING).networkSynchronized(ByteBufCodecs.STRING_UTF8));
    }

    private static DataComponentType<Integer> makeInteger(String name){
        return make(name,builder -> builder.persistent(ExtraCodecs.POSITIVE_INT).networkSynchronized(ByteBufCodecs.VAR_INT));
    }
    private static DataComponentType<List<ItemStack>> makeItemList(String name){
        return make(name,builder -> builder.persistent(ExtraCodecs.nonEmptyList(ItemStack.CODEC.sizeLimitedListOf(ManaBlasterItem.CLIP_SLOTS)))
                .cacheEncoding().networkSynchronized(ItemStack.LIST_STREAM_CODEC));
    }
}
