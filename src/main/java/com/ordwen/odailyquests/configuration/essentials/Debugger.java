package com.ordwen.odailyquests.configuration.essentials;

import com.ordwen.odailyquests.OWeeklyQuests;
import com.ordwen.odailyquests.tools.PluginLogger;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;

public class Debugger {

    private final OWeeklyQuests oWeeklyQuests;
    private static boolean debugMode;
    private static File debugFile;

    public Debugger(OWeeklyQuests oWeeklyQuests) {
        this.oWeeklyQuests = oWeeklyQuests;
    }

    public void loadDebugMode() {
        debugMode = oWeeklyQuests.getConfigurationFiles().getConfigFile().getBoolean("debug");
        if (debugMode) {
            loadDebugFile();
            PluginLogger.warn("Debug mode is enabled. This may cause performance issues.");
        }
    }

    public void loadDebugFile() {

        debugFile = new File(oWeeklyQuests.getDataFolder(), "debug.yml");

        if (!debugFile.exists()) {
            oWeeklyQuests.saveResource("debug.yml", false);
            PluginLogger.info("Debug file created (YAML).");
        }
    }

    public static void addDebug(String debugMessage) {
        if (debugMode) {
            final Date date = new Date();

            try {
                final FileWriter writer = new FileWriter(debugFile, true);
                writer.write("[" + date + "] " + debugMessage);
                writer.write(System.lineSeparator());
                writer.close();
            } catch (IOException e) {
                PluginLogger.error("An error happened on the write of the debug file.");
                PluginLogger.error("If the problem persists, contact the developer.");
                PluginLogger.error(e.getMessage());
            }
        }
    }
}
