package grasspow.extrabotany.common.crafting.recipe;

import net.minecraft.core.HolderLookup;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;
import vazkii.botania.api.item.Relic;
import vazkii.botania.xplat.XplatAbstractions;

import java.util.UUID;

import static grasspow.extrabotany.common.item.ExtraBotanyItems.goldCloth;

public class GoldClothWipeRelicRecipe extends CustomRecipe {
    public static final SimpleCraftingRecipeSerializer<GoldClothWipeRelicRecipe> SERIALIZER = new SimpleCraftingRecipeSerializer<>(GoldClothWipeRelicRecipe::new);

    public GoldClothWipeRelicRecipe(CraftingBookCategory craftingBookCategory) {
        super(craftingBookCategory);
    }

    @Override
    public boolean matches(CraftingInput inv, Level level) {
        boolean foundCloth = false;
        boolean foundRelic = false;

        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty()) {
                if (stack.getItem() == goldCloth && !foundCloth) {
                    foundCloth = true;
                } else if (XplatAbstractions.INSTANCE.findRelic(stack) != null && XplatAbstractions.INSTANCE.findRelic(stack).getSoulbindUUID() != null && !foundRelic) {
                    foundRelic = true;
                } else {
                    return false;
                }
            }
        }
        return foundCloth && foundRelic;
    }


    @NotNull
    @Override
    public ItemStack assemble(CraftingInput inv, HolderLookup.Provider registries) {
        ItemStack relicStack = ItemStack.EMPTY;
        for (int i = 0; i < inv.size(); i++) {
            ItemStack stack = inv.getItem(i);
            if (!stack.isEmpty() && XplatAbstractions.INSTANCE.findRelic(stack) != null) {
                relicStack = stack;
                break;
            }
        }
        ItemStack stack = relicStack.copy();
        Relic relic = XplatAbstractions.INSTANCE.findRelic(stack);
        UUID soulbindUUID = relic.getSoulbindUUID() ;
        if (soulbindUUID != null) {
            relic.bindToUUID(null);
        }
        return stack;
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return width > 1 || height > 1;
    }

    @NotNull
    @Override
    public RecipeSerializer<GoldClothWipeRelicRecipe> getSerializer() {
        return SERIALIZER;
    }
}
