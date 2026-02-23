package grasspow.extrabotany.common.crafting;

import com.google.common.base.Preconditions;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.NonNullList;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeInput;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.Level;
import vazkii.botania.common.crafting.recipe.RecipeUtils;

import java.util.List;

public class PedestalClickRecipe implements grasspow.extrabotany.api.recipe.PedestalClickRecipe {
    public static final RecipeSerializer<PedestalClickRecipe> SERIALIZER = new PedestalClickRecipe.Serializer();
    private final NonNullList<Ingredient> inputs;
    private final ItemStack output;

    public PedestalClickRecipe(ItemStack output, Ingredient[] inputs) {
        Preconditions.checkArgument(inputs.length <= 2, "Cannot have more than 2 ingredients");
        this.inputs = NonNullList.of(Ingredient.EMPTY, inputs);
        this.output = output;
    }

    private static PedestalClickRecipe of(List<Ingredient> inputs, ItemStack output) {
        return new PedestalClickRecipe(output, inputs.toArray(Ingredient[]::new));
    }

    @Override
    public boolean matches(RecipeInput container, Level level) {
        return RecipeUtils.matches(inputs, null, container, null, null);
    }

    @Override
    public NonNullList<Ingredient> getIngredients() {
        return inputs;
    }

    @Override
    public ItemStack getResultItem(HolderLookup.Provider registries) {
        return output;
    }

    @Override
    public ItemStack assemble(RecipeInput inv, HolderLookup.Provider registries) {
        return getResultItem(registries).copy();
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return SERIALIZER;
    }

    public Ingredient getClickTools() {
        return inputs.get(1);
    }

    public boolean containClickTool(ItemStack s) {
        return inputs.get(1).test(s);
    }

    public NonNullList<Ingredient> getInputs() {
        return inputs;
    }

    protected ItemStack getOutput() {
        return output;
    }

    public static class Serializer implements RecipeSerializer<PedestalClickRecipe> {
        public static final MapCodec<PedestalClickRecipe> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                Ingredient.CODEC_NONEMPTY.listOf().fieldOf("inputs").forGetter(PedestalClickRecipe::getIngredients),
                ItemStack.SIMPLE_ITEM_CODEC.fieldOf("output").forGetter(PedestalClickRecipe::getOutput)
        ).apply(instance, PedestalClickRecipe::of));
        public static final StreamCodec<RegistryFriendlyByteBuf, PedestalClickRecipe> STREAM_CODEC = StreamCodec.composite(
                Ingredient.CONTENTS_STREAM_CODEC.apply(ByteBufCodecs.list()), PedestalClickRecipe::getIngredients,
                ItemStack.STREAM_CODEC, PedestalClickRecipe::getOutput,
                PedestalClickRecipe::of
        );

        @Override
        public MapCodec<PedestalClickRecipe> codec() {
            return CODEC;
        }

        @Override
        public StreamCodec<RegistryFriendlyByteBuf, PedestalClickRecipe> streamCodec() {
            return STREAM_CODEC;
        }
    }
}
