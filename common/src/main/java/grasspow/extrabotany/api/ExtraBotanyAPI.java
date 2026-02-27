package grasspow.extrabotany.api;

import grasspow.extrabotany.common.lib.LibMisc;
import net.minecraft.client.resources.model.ModelResourceLocation;
import net.minecraft.core.Holder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ArmorMaterial;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.ServiceUtil;


public interface ExtraBotanyAPI {
    String MODID = LibMisc.MOD_ID;
    Logger LOGGER = LoggerFactory.getLogger(MODID);
    ExtraBotanyAPI INSTANCE = ServiceUtil.findService(ExtraBotanyAPI.class, () -> new ExtraBotanyAPI() {
    });

    static ExtraBotanyAPI instance() {
        return INSTANCE;
    }


    default Holder<ArmorMaterial> getMaidArmorMaterial(){
        return BotaniaAPI.DUMMY_ARMOR_MATERIAL;
    }

    default Holder<ArmorMaterial> getMikuArmorMaterial(){
        return BotaniaAPI.DUMMY_ARMOR_MATERIAL;
    }
    default Holder<ArmorMaterial> getGoblinSlayerArmorMaterial(){
        return BotaniaAPI.DUMMY_ARMOR_MATERIAL;
    }
    default Holder<ArmorMaterial> getShadowWarriorArmorMaterial(){
        return BotaniaAPI.DUMMY_ARMOR_MATERIAL;
    }
    default Holder<ArmorMaterial> getShootingGuardianArmorMaterial(){
        return BotaniaAPI.DUMMY_ARMOR_MATERIAL;
    }
    default Holder<ArmorMaterial> getSilentSagesArmorMaterial(){
        return BotaniaAPI.DUMMY_ARMOR_MATERIAL;
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
