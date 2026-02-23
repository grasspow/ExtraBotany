package grasspow.extrabotany.client.render;

import grasspow.extrabotany.common.item.ExtraBotanyItems;
import net.minecraft.client.color.block.BlockColor;
import net.minecraft.client.color.item.ItemColor;
import net.minecraft.util.FastColor;
import net.minecraft.util.Mth;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import vazkii.botania.api.brew.Brew;
import vazkii.botania.api.brew.BrewItem;
import vazkii.botania.client.core.handler.ClientTickHandler;
import vazkii.botania.common.brew.BotaniaBrews;

public final class ColorHandler {
    public interface BlockHandlerConsumer {
        void register(BlockColor handler, Block... blocks);
    }

    public interface ItemHandlerConsumer {
        void register(ItemColor handler, ItemLike... items);
    }

    public static void submitBlocks(BlockHandlerConsumer blocks) {
    }

    public static void submitItems(ItemHandlerConsumer items) {
        items.register((stack, tintIndex) ->
        {
            if (tintIndex != 1) {
                return -1;
            }

            Brew brew = ((BrewItem) stack.getItem()).getBrew(stack);
            if (brew == BotaniaBrews.fallbackBrew) {
                return 0xFFC6000E;
            }

			int add = (int) (Mth.sin(ClientTickHandler.getUiAnimationTicks() * 0.2f) * 24);

			return addToColor(brew.getColor(stack), add);
        }, ExtraBotanyItems.splashGrenade, ExtraBotanyItems.cocktail, ExtraBotanyItems.infiniteWine);

        items.register((s, t) -> t == 1 ? Mth.hsvToRgb(ClientTickHandler.getEntityTicksInGame() * 2 % 360 / 360F, 0.25F, 1F) : -1, ExtraBotanyItems.universalPetal);
    }

    private static int addToColor(int color, int add) {
        int r = Mth.clamp(FastColor.ARGB32.red(color) + add, 0, 255);
        int g = Mth.clamp(FastColor.ARGB32.green(color) + add, 0, 255);
        int b = Mth.clamp(FastColor.ARGB32.blue(color) + add, 0, 255);

        return FastColor.ARGB32.color(r, g, b);
    }

    private ColorHandler() {
    }
}
