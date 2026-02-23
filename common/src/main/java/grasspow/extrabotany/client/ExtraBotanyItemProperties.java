package grasspow.extrabotany.client;

import grasspow.extrabotany.common.item.ExtraBotanyItems;
import grasspow.extrabotany.common.item.equipment.weapon.FailnaughtItem;
import net.minecraft.client.renderer.item.ClampedItemPropertyFunction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.ItemLike;
import vazkii.botania.common.item.brew.BaseBrewItem;
import vazkii.botania.network.TriConsumer;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;

public class ExtraBotanyItemProperties {
    public static void init(TriConsumer<ItemLike, ResourceLocation, ClampedItemPropertyFunction> consumer) {
        //brew
        ClampedItemPropertyFunction brewGetter = (stack, world, entity, seed) -> {
            BaseBrewItem item = ((BaseBrewItem) stack.getItem());
            int swigsLeft = item.getSwigsLeft(stack) - 1;
            int totalSwigs = item.getSwigs(stack) - 1;
            return swigsLeft == totalSwigs ? 0.0F : Math.nextUp((totalSwigs - swigsLeft) / (float) totalSwigs);
        };
        consumer.accept(ExtraBotanyItems.cocktail, exbotRL("swigs_taken"), brewGetter);
        consumer.accept(ExtraBotanyItems.infiniteWine, exbotRL("swigs_taken"), brewGetter);

        ClampedItemPropertyFunction pulling = (stack, worldIn, entity, seed) -> entity != null && entity.isUsingItem() && entity.getUseItem() == stack ? 1.0F : 0.0F;
        ClampedItemPropertyFunction pull = (stack, worldIn, entity, seed) -> {
            if (entity == null) {
                return 0.0F;
            } else {
                FailnaughtItem item = ((FailnaughtItem) stack.getItem());
                return entity.getUseItem() != stack
                        ? 0.0F
                        : (stack.getUseDuration(entity) - entity.getUseItemRemainingTicks()) * item.chargeVelocityMultiplier() / 20.0F;
            }
        };
        consumer.accept(ExtraBotanyItems.fallnaught, ResourceLocation.withDefaultNamespace("pulling"), pulling);
        consumer.accept(ExtraBotanyItems.fallnaught, ResourceLocation.withDefaultNamespace("pull"), pull);
    }
}
