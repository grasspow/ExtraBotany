package grasspow.extrabotany.client.render;

import grasspow.extrabotany.common.lib.LibMisc;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.TallFlowerBlock;
import vazkii.botania.common.block.BotaniaMushroomBlock;
import vazkii.botania.common.block.flower.FloatingFlowerBaseBlock;

import java.util.function.BiConsumer;

public class BlockRenderLayers {
    public static void init(BiConsumer<Block, RenderType> consumer) {
        BuiltInRegistries.BLOCK.stream().filter(b -> BuiltInRegistries.BLOCK.getKey(b).getNamespace().equals(LibMisc.MOD_ID))
                .forEach(b -> {
                    if (b instanceof FloatingFlowerBaseBlock || b instanceof FlowerBlock
                            || b instanceof TallFlowerBlock || b instanceof BotaniaMushroomBlock) {
                        consumer.accept(b, RenderType.cutout());
                    }
                });
    }
}
