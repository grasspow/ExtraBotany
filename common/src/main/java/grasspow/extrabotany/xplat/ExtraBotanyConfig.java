package grasspow.extrabotany.xplat;

import grasspow.extrabotany.api.ExtraBotanyAPI;

public class ExtraBotanyConfig {
    public interface ConfigAccess {

        boolean doCompatTConstruct();

        boolean  disableDisarm();

        boolean  doProjectileBreakBlock();

        boolean   doStrictAdvancementChecking();
    }

    public interface ClientConfigAccess {

        boolean disableLogSpam();
    }
    
    private static ConfigAccess config = null;
	private static ClientConfigAccess clientConfig = null;

	public static ConfigAccess common() {
		return config;
	}

	public static ClientConfigAccess client() {
		return clientConfig;
	}

	public static void setCommon(ConfigAccess access) {
		if (config != null) {
			ExtraBotanyAPI.LOGGER.warn("ConfigAccess was replaced! Old {} New {}",
					config.getClass().getName(), access.getClass().getName());
		}
		config = access;
	}

	public static void setClient(ClientConfigAccess access) {
		if (clientConfig != null) {
            ExtraBotanyAPI.LOGGER.warn("ClientConfigAccess was replaced! Old {} New {}",
					clientConfig.getClass().getName(), access.getClass().getName());
		}
		clientConfig = access;
	}

}
