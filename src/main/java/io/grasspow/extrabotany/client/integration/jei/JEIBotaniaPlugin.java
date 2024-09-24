package io.grasspow.extrabotany.client.integration.jei;

import io.grasspow.extrabotany.common.registry.ModRecipeTypes;
import mezz.jei.api.IModPlugin;
import mezz.jei.api.JeiPlugin;
import mezz.jei.api.registration.IRecipeCategoryRegistration;
import mezz.jei.api.registration.IRecipeRegistration;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeType;
import vazkii.botania.common.crafting.BotaniaRecipeTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;

import static io.grasspow.extrabotany.common.libs.ResourceLocationHelper.resId;

@JeiPlugin
public class JEIBotaniaPlugin implements IModPlugin {
    private static final ResourceLocation ID = resId("main");

    @Override
    public ResourceLocation getPluginUid() {
        return ID;
    }

    @Override
    public void registerCategories(IRecipeCategoryRegistration registry) {
        registry.addRecipeCategories(
                new PedestalClickRecipeCategory(registry.getJeiHelpers().getGuiHelper())
        );
    }

    @Override
    public void registerRecipes(IRecipeRegistration registry) {
        registry.addRecipes(PedestalClickRecipeCategory.TYPE, sortRecipes(ModRecipeTypes.PEDESTAL_CLICK.get(), BY_ID));
    }

    private static <T extends Recipe<C>, C extends Container> List<T> sortRecipes(RecipeType<T> type, Comparator<? super T> comparator) {
        @SuppressWarnings("unchecked")
        Collection<T> recipes = (Collection<T>) BotaniaRecipeTypes.getRecipes(Minecraft.getInstance().level, type).values();
        List<T> list = new ArrayList<>(recipes);
        list.sort(comparator);
        return list;
    }

    private static final Comparator<Recipe<?>> BY_ID = Comparator.comparing(Recipe::getId);
}
