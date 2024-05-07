package com.ordwen.odailyquests.files;

import com.ordwen.odailyquests.OWeeklyQuests;
import com.ordwen.odailyquests.tools.PluginLogger;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class HologramsFile {

    /**
     * Getting instance of main class.
     */
    private final OWeeklyQuests oWeeklyQuests;

    /**
     * Main class instance constructor.
     * @param oWeeklyQuests main class.
     */
    public HologramsFile(OWeeklyQuests oWeeklyQuests) {
        this.oWeeklyQuests = oWeeklyQuests;
    }

    private static File hologramsFile;
    private static FileConfiguration holograms;

    /**
     * Get the configuration file.
     * @return config file.
     */
    public static FileConfiguration getHologramsFileConfiguration() {
        return holograms;
    }

    /**
     * Get the file.
     * @return file.
     */
    public static File getHologramsFile() { return hologramsFile; }

    /**
     * Init progression file.
     */
    public void loadHologramsFile() {
        hologramsFile = new File(oWeeklyQuests.getDataFolder(), "holograms.yml");

        if (!hologramsFile.exists()) {
            oWeeklyQuests.saveResource("holograms.yml", false);
            PluginLogger.info("Holograms file created (YAML).");
        }

        holograms = new YamlConfiguration();

        try {
            holograms.load(hologramsFile);
        } catch (InvalidConfigurationException | IOException e) {
            PluginLogger.error("An error occurred on the load of the holograms file.");
            PluginLogger.error("Please inform the developer.");
            PluginLogger.error(e.getMessage());
        }
        PluginLogger.fine("Holograms file successfully loaded (YAML).");
    }

}
