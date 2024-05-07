package com.ordwen.odailyquests.files;

import com.ordwen.odailyquests.OWeeklyQuests;
import com.ordwen.odailyquests.tools.PluginLogger;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ProgressionFile {

    /**
     * Getting instance of main class.
     */
    private final OWeeklyQuests oWeeklyQuests;

    /**
     * Main class instance constructor.
     * @param oWeeklyQuests main class.
     */
    public ProgressionFile(OWeeklyQuests oWeeklyQuests) {
        this.oWeeklyQuests = oWeeklyQuests;
    }

    private static File progressionFile;
    private static FileConfiguration progression;

    /**
     * Get the configuration file.
     * @return config file.
     */
    public static FileConfiguration getProgressionFileConfiguration() {
        return progression;
    }

    /**
     * Get the file.
     * @return file.
     */
    public static File getProgressionFile() { return progressionFile; }

    /**
     * Init progression file.
     */
    public void loadProgressionFile() {

        progressionFile = new File(oWeeklyQuests.getDataFolder(), "progression.yml");

        if (!progressionFile.exists()) {
            oWeeklyQuests.saveResource("progression.yml", false);
            PluginLogger.info("Progression file created (YAML).");
        }

        progression = new YamlConfiguration();

        try {
            progression.load(progressionFile);
            PluginLogger.fine("Progression file successfully loaded (YAML).");
        } catch (InvalidConfigurationException | IOException e) {
            PluginLogger.error("An error occurred on the load of the progression file.");
            PluginLogger.error("Please inform the developer.");
            PluginLogger.error(e.getMessage());
        }
    }
}
