package grasspow.extrabotany.common.advancements;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import grasspow.extrabotany.common.lib.LibAdvancementNames;
import net.minecraft.advancements.Criterion;
import net.minecraft.advancements.critereon.*;
import net.minecraft.core.BlockPos;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.item.ItemStack;

import java.util.Optional;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;

public class TinkleUseTrigger extends SimpleCriterionTrigger<TinkleUseTrigger.Instance> {
    public static final ResourceLocation ID = exbotRL(LibAdvancementNames.TINKLE_USE);
    public static final TinkleUseTrigger INSTANCE = new TinkleUseTrigger();

    private TinkleUseTrigger() {
    }

    public void trigger(ServerPlayer player, ServerLevel world, BlockPos pos, ItemStack wand) {
        trigger(player, instance -> instance.test(world, pos, wand));
    }

    @Override
    public Codec<Instance> codec() {
        return Instance.CODEC;
    }

    public record Instance(Optional<ContextAwarePredicate> player, Optional<ItemPredicate> wand, Optional<LocationPredicate> location) implements SimpleInstance {
        
        public static final Codec<Instance> CODEC = RecordCodecBuilder.create(instance -> instance.group(
				EntityPredicate.ADVANCEMENT_CODEC.optionalFieldOf("player").forGetter(Instance::player),
				ItemPredicate.CODEC.optionalFieldOf("wand").forGetter(Instance::wand),
				LocationPredicate.CODEC.optionalFieldOf("location").forGetter(Instance::location)
		).apply(instance, Instance::new));

        public static Criterion<TinkleUseTrigger.Instance> danceNearTinkle() {
            return INSTANCE.createCriterion(new Instance(Optional.empty(),Optional.empty(),Optional.empty()));
        }

        boolean test(ServerLevel world, BlockPos pos, ItemStack stack) {
            return this.location.isEmpty() || this.location.get().matches(world, pos.getX(), pos.getY(), pos.getZ());
        }

    }


}
