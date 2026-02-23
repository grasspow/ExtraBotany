package grasspow.extrabotany.common.handler;

import grasspow.extrabotany.api.ExtraBotanyAPI;
import net.minecraft.DefaultUncaughtExceptionHandler;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ContributorListHandler {

    public static volatile Map<String, Integer> contributorsMap = Collections.emptyMap();
    private static boolean startedLoading = false;

    public static void firstStart() {
        if (!startedLoading) {
            Thread thread = new Thread(ContributorListHandler::fetch);
            thread.setName("ExtraBotany Contributor Thread");
            thread.setDaemon(true);
            thread.setUncaughtExceptionHandler(new DefaultUncaughtExceptionHandler(ExtraBotanyAPI.LOGGER));
            thread.start();
            ExtraBotanyAPI.LOGGER.info("Start Loading Contributors' List");
            startedLoading = true;
        }
    }

    private static void load(Properties props) {
        Map<String, Integer> m = new HashMap<>();
        for (String key : props.stringPropertyNames()) {
            String value = props.getProperty(key);
            int i = 0;
            try {
                i = Integer.parseInt(value);
            } catch (NumberFormatException e) {

            }
            m.put(key, i);
        }
        contributorsMap = m;
    }

    public static boolean isSupporter(String name) {
        return contributorsMap.containsKey(name);
    }

    public static boolean isDeveloper(String name) {
        return isSupporter(name) && contributorsMap.get(name) == 0;
    }

    public static boolean isArtist(String name) {
        return isSupporter(name) && contributorsMap.get(name) == 1;
    }

    public static boolean isContributor(String name) {
        return isSupporter(name) && contributorsMap.get(name) == 2;
    }

    public static boolean isPatreoner(String name) {
        return isSupporter(name) && contributorsMap.get(name) == 3;
    }

    public static String getRole(String name) {
        if (!isSupporter(name))
            return "No Role";
        if (isDeveloper(name))
            return "Developer";
        else if (isArtist(name))
            return "Artist";
        else if (isContributor(name))
            return "Contributor";
        else if (isPatreoner(name))
            return "Patreoner";
        else
            return "Supporter";
    }

    private static void fetch() {
        try {
            URL url = new URL("https://raw.githubusercontent.com/ExtraMeteorP/Extra-Botany/refs/heads/master/contributors.md");
            Properties props = new Properties();
            try (InputStreamReader reader = new InputStreamReader(url.openStream(), StandardCharsets.UTF_8)) {
                props.load(reader);
                load(props);
            }
        } catch (IOException e) {
            ExtraBotanyAPI.LOGGER.info("Could not load contributors list.");
        }
    }

}
