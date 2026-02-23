package grasspow.extrabotany.api;

import grasspow.extrabotany.common.lib.LibMisc;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vazkii.botania.api.ServiceUtil;


public interface ExtraBotanyAPI {
    String MODID = LibMisc.MOD_ID;
    Logger LOGGER = LoggerFactory.getLogger(MODID);
    ExtraBotanyAPI INSTANCE = ServiceUtil.findService(ExtraBotanyAPI.class, () -> new ExtraBotanyAPI() {
    });

    static ExtraBotanyAPI instance() {
        return INSTANCE;
    }

    static ResourceLocation exbotRL(String path) {
        return ResourceLocation.fromNamespaceAndPath(MODID, path);
    }

    static ModelResourceLocation exbotModelRL(String path, String variant) {
        return new ModelResourceLocation(exbotRL(path), variant);
    }

    default void addPotionEffect(LivingEntity entity, Holder<MobEffect> potion, int max) {
    }

    default int apiVersion() {
        return 0;
    }

    default float calcDamage(float v, Player player) {
        return 0;
    }
}
