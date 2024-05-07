package com.ordwen.odailyquests.files;

import com.ordwen.odailyquests.OWeeklyQuests;
import com.ordwen.odailyquests.enums.QuestsMessages;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import com.ordwen.odailyquests.tools.PluginLogger;

import java.io.File;
import java.io.IOException;

public class ConfigurationFiles {

    /**
     * Getting instance of main class.
     */
    private final OWeeklyQuests oWeeklyQuests;

    /**
     * Main class instance constructor.
     * @param oWeeklyQuests main class.
     */
    public ConfigurationFiles(OWeeklyQuests oWeeklyQuests) {
        this.oWeeklyQuests = oWeeklyQuests;
    }

    private FileConfiguration config;
    private File configFile;

    /**
     * Get the configuration file.
     * @return config file.
     */
    public FileConfiguration getConfigFile() {
        return this.config;
    }

    /**
     * Get the original file.
     * @return file
     */
    public File getFile() {
        return this.configFile;
    }

    /**
     * Init configuration files.
     */
    public void loadConfigurationFiles() {

        configFile = new File(oWeeklyQuests.getDataFolder(), "config.yml");

        /* Configuration file */
        if (!configFile.exists()) {
            oWeeklyQuests.saveResource("config.yml", false);
            PluginLogger.info("Configuration file created.");
        }

        config = new YamlConfiguration();

        /* Configuration file */
        try {
            config.load(configFile);
        } catch (InvalidConfigurationException | IOException e) {
            PluginLogger.error("An error occurred on the load of the configuration file.");
            PluginLogger.error("Please inform the developer.");
            PluginLogger.error(e.getMessage());
        }
        PluginLogger.fine("Configuration file successfully loaded.");
    }

    /**
     * Init messages files.
     */
    public void loadMessagesFiles() {

        File messagesFile = new File(oWeeklyQuests.getDataFolder(), "messages.yml");

        /* Messages file */
        if (!messagesFile.exists()) {
            oWeeklyQuests.saveResource("messages.yml", false);
            PluginLogger.info("Messages file created.");
        }

        FileConfiguration messages = new YamlConfiguration();

        /* Messages file */
        try {
            messages.load(messagesFile);
        } catch (InvalidConfigurationException | IOException e) {
            PluginLogger.error("An error occurred on the load of the messages file.");
            PluginLogger.error("Please inform the developer.");
            PluginLogger.error(e.getMessage());
        }

        for (QuestsMessages item : QuestsMessages.values()) {
            if (messages.getString(item.getPath()) == null) {
                messages.set(item.getPath(), item.getDefault());
            }
        }
        QuestsMessages.setFile(messages);

        try {
            messages.save(messagesFile);
        } catch(IOException e) {
            PluginLogger.error("An error happened on the save of the messages file.");
            PluginLogger.error("If the problem persists, contact the developer.");
            PluginLogger.error(e.getMessage());
        }

        PluginLogger.fine("Messages file successfully loaded.");
    }
}
