package com.ordwen.odailyquests.files;

import com.ordwen.odailyquests.OWeeklyQuests;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import com.ordwen.odailyquests.tools.PluginLogger;

import java.io.File;
import java.io.IOException;

public class QuestsFiles {

    /**
     * Getting instance of main class.
     */
    private final OWeeklyQuests oWeeklyQuests;

    /**
     * Main class instance constructor.
     * @param oWeeklyQuests main class.
     */
    public QuestsFiles(OWeeklyQuests oWeeklyQuests) {
        this.oWeeklyQuests = oWeeklyQuests;
    }

    private static File globalQuestsFile;
    private static File easyQuestsFile;
    private static File mediumQuestsFile;
    private static File hardQuestsFile;

    private static FileConfiguration globalQuestsConfiguration;
    private static FileConfiguration easyQuestsConfiguration;
    private static FileConfiguration mediumQuestsConfiguration;
    private static FileConfiguration hardQuestsConfiguration;

    /**
     * Get the global quests file.
     * @return global quests file.
     */
    public static File getGlobalQuestsFile() {
        return globalQuestsFile;
    }

    /**
     * Get the easy quests file.
     * @return easy quests file.
     */
    public static File getEasyQuestsFile() {
        return easyQuestsFile;
    }

    /**
     * Get the medium quests file.
     * @return medium quests file.
     */
    public static File getMediumQuestsFile() {
        return mediumQuestsFile;
    }

    /**
     * Get the hard quests file.
     * @return hard quests file.
     */
    public static File getHardQuestsFile() {
        return hardQuestsFile;
    }

    /**
     * Get the global quests configuration.
     * @return global quests configuration.
     */
    public static FileConfiguration getGlobalQuestsConfiguration() {
        return globalQuestsConfiguration;
    }

    /**
     * Get the easy quests configuration.
     * @return easy quests configuration.
     */
    public static FileConfiguration getEasyQuestsConfiguration() {
        return easyQuestsConfiguration;
    }

    /**
     * Get the medium quests configuration.
     * @return medium quests configuration.
     */
    public static FileConfiguration getMediumQuestsConfiguration() {
        return mediumQuestsConfiguration;
    }

    /**
     * Get the hard quests configuration.
     * @return hard quests configuration.
     */
    public static FileConfiguration getHardQuestsConfiguration() {
        return hardQuestsConfiguration;
    }

    /**
     * Init quests files.
     */
    public void loadQuestsFiles() {

        globalQuestsFile = new File(oWeeklyQuests.getDataFolder(), "quests/globalQuests.yml");
        easyQuestsFile = new File(oWeeklyQuests.getDataFolder(), "quests/easyQuests.yml");
        mediumQuestsFile = new File(oWeeklyQuests.getDataFolder(), "quests/mediumQuests.yml");
        hardQuestsFile = new File(oWeeklyQuests.getDataFolder(), "quests/hardQuests.yml");

        /* Global quests */
        if (!globalQuestsFile.exists()) {
            oWeeklyQuests.saveResource("quests/globalQuests.yml", false);
            PluginLogger.info("Global quests file created.");
        }

        /* Easy quests */
        if (!easyQuestsFile.exists()) {
            oWeeklyQuests.saveResource("quests/easyQuests.yml", false);
            PluginLogger.info("Easy quests file created.");
        }

        /* Medium quests */
        if (!mediumQuestsFile.exists()) {
            oWeeklyQuests.saveResource("quests/mediumQuests.yml", false);
            PluginLogger.info("Medium quests file created.");
        }

        /* Hard quests */
        if (!hardQuestsFile.exists()) {
            oWeeklyQuests.saveResource("quests/hardQuests.yml", false);
            PluginLogger.info("Hard quests file created.");
        }

        globalQuestsConfiguration = new YamlConfiguration();
        easyQuestsConfiguration = new YamlConfiguration();
        mediumQuestsConfiguration = new YamlConfiguration();
        hardQuestsConfiguration = new YamlConfiguration();

        /* Global quests */
        try {
            globalQuestsConfiguration.load(globalQuestsFile);
        } catch (InvalidConfigurationException | IOException e) {
            PluginLogger.error("An error occured on the load of the global quests file.");
            PluginLogger.error("Please inform the developper.");
            PluginLogger.error(e.getMessage());
        }
        PluginLogger.fine("Global quests file successfully loaded.");

        /* Easy quests */
        try {
            easyQuestsConfiguration.load(easyQuestsFile);
        } catch (InvalidConfigurationException | IOException e) {
            PluginLogger.error("An error occured on the load of the easy quests file.");
            PluginLogger.error("Please inform the developper.");
            PluginLogger.error(e.getMessage());
        }
        PluginLogger.fine("Easy quests file successfully loaded.");

        /* Medium quests */
        try {
            mediumQuestsConfiguration.load(mediumQuestsFile);
        } catch (InvalidConfigurationException | IOException e) {
            PluginLogger.error("An error occured on the load of the medium quests file.");
            PluginLogger.error("Please inform the developper.");
            PluginLogger.error(e.getMessage());
        }
        PluginLogger.fine("Medium quests file successfully loaded.");

        /* Hard quests */
        try {
            hardQuestsConfiguration.load(hardQuestsFile);
        } catch (InvalidConfigurationException | IOException e) {
            PluginLogger.error("An error occured on the load of the hard quests file.");
            PluginLogger.error("Please inform the developper.");
            PluginLogger.error(e.getMessage());
        }
        PluginLogger.fine("Hard quests file successfully loaded.");
    }

}
