package grasspow.extrabotany.neoforge;

import grasspow.extrabotany.xplat.ExtraBotanyConfig;
import grasspow.extrabotany.xplat.XplatAbstractions;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public class NeoForgeExtraBotanyConfig {

    private static class Client implements ExtraBotanyConfig.ClientConfigAccess {
        public final ModConfigSpec.BooleanValue disableLogSpam;

        public Client(ModConfigSpec.Builder builder) {
            builder.push("client");
            disableLogSpam = builder
                    .comment("Whether to disable the spam in the logs. Default is false.")
                    .define("disableLogSpam", false);
            builder.pop();
        }

        @Override
        public boolean disableLogSpam() {
            return disableLogSpam.get();
        }

    }

    private static final Client CLIENT;
    private static final ModConfigSpec CLIENT_SPEC;

    static {
        final Pair<Client, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = specPair.getRight();
        CLIENT = specPair.getLeft();
    }


    private static class Common implements ExtraBotanyConfig.ConfigAccess {

        public final ModConfigSpec.BooleanValue doCompatTConstruct;
        public final ModConfigSpec.BooleanValue disableDisarm;
        public final ModConfigSpec.BooleanValue doProjectileBreakBlock;
        public final ModConfigSpec.BooleanValue doStrictAdvancementChecking;

        public Common(ModConfigSpec.Builder builder) {

            builder.push("common");
            doCompatTConstruct = builder.comment("Whether to add materials into TConstruct. Default is false(WIP)")
                    .define("doCompatTConstruct", false);

            disableDisarm = builder
                    .comment("Whether to disable the Ego's disarm. Default is false.")
                    .define("disableDisarm", false);

            doProjectileBreakBlock = builder
                    .comment("Whether Relic projectiles break blocks. Default is false.")
                    .define("doProjectileBreakBlock", false);

            doStrictAdvancementChecking = builder
                    .comment("Whether advancement-checker drop items from players' inventory (not curio's slot and equipment slot) that are not grant specific advancement. Default is false.")
                    .define("doStrictAdvancementChecking", false);

            builder.pop();
        }

        @Override
        public boolean doCompatTConstruct() {
            return doCompatTConstruct.get();
        }

        @Override
        public boolean disableDisarm() {
            return disableDisarm.get();
        }


        @Override
        public boolean doProjectileBreakBlock() {
            return doProjectileBreakBlock.get();
        }


        @Override
        public boolean doStrictAdvancementChecking() {
            return doStrictAdvancementChecking.get();
        }

    }

    private static final Common COMMON;
    private static final ModConfigSpec COMMON_SPEC;

    static {
        final Pair<Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    public static void setup(ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, COMMON_SPEC);
        ExtraBotanyConfig.setCommon(COMMON);

        if (XplatAbstractions.INSTANCE.isPhysicalClient()) {
            modContainer.registerConfig(ModConfig.Type.CLIENT, CLIENT_SPEC);
            ExtraBotanyConfig.setClient(CLIENT);
        }
    }
}
