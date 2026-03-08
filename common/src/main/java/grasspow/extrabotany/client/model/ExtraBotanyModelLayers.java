package grasspow.extrabotany.client.model;

import net.minecraft.client.model.geom.ModelLayerLocation;

import static grasspow.extrabotany.api.ExtraBotanyAPI.exbotRL;


public class ExtraBotanyModelLayers {

    public static final ModelLayerLocation SHADOW_INNER_ARMOR = make("shadowwarrior_armor", "inner_armor");
    public static final ModelLayerLocation SHADOW_OUTER_ARMOR = make("shadowwarrior_armor", "outer_armor");
    public static final ModelLayerLocation GOBLIN_SLAYER_INNER = make("goblinslayer_armor", "inner_armor");
    public static final ModelLayerLocation GOBLIN_SLAYER_OUTER = make("goblinslayer_armor", "outer_armor");
    public static final ModelLayerLocation MIKU_INNER = make("miku_armor", "inner_armor");
    public static final ModelLayerLocation MIKU_OUTER = make("miku_armor", "outer_armor");
    public static final ModelLayerLocation MAID_INNER = make("maid_armor", "inner_armor");
    public static final ModelLayerLocation MAID_OUTER = make("maid_armor", "outer_armor");
    public static final ModelLayerLocation SHOOTING_GUARDIAN_INNER = make("shooting_guardian_armor", "inner_armor");
    public static final ModelLayerLocation SHOOTING_GUARDIAN_OUTER = make("shooting_guardian_armor", "outer_armor");

    private static ModelLayerLocation make(String name) {
        return make(name, "main");
    }

    private static ModelLayerLocation make(String name, String layer) {
        // Don't add to vanilla's ModelLayers. It seems to only be used for error checking
        // And would be annoying to do under Forge's parallel mod loading
        return new ModelLayerLocation(exbotRL(name), layer);
    }

    public static void init() {}
}
