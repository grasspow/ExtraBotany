package grasspow.extrabotany.common.advancements;

import net.minecraft.advancements.CriterionTrigger;
import net.minecraft.resources.ResourceLocation;

import java.util.function.BiConsumer;

public class ExtraBotanyCriteriaTriggers {
    public static void init(BiConsumer<CriterionTrigger<?>, ResourceLocation> r) {
        r.accept(TinkleUseTrigger.INSTANCE,TinkleUseTrigger.ID);
    }
}
