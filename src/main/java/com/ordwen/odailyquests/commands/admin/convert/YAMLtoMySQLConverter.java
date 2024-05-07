package com.ordwen.odailyquests.commands.admin.convert;

import com.ordwen.odailyquests.OWeeklyQuests;
import com.ordwen.odailyquests.files.ProgressionFile;
import com.ordwen.odailyquests.quests.player.progression.storage.sql.SQLManager;
import com.ordwen.odailyquests.quests.player.progression.storage.sql.mysql.MySQLManager;
import com.ordwen.odailyquests.tools.PluginLogger;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;

public class YAMLtoMySQLConverter extends SQLConverter {

    public boolean convert(OWeeklyQuests oWeeklyQuests) {

        try {
            Bukkit.getScheduler().runTaskAsynchronously(oWeeklyQuests, () -> {

                final FileConfiguration progressionFile = ProgressionFile.getProgressionFileConfiguration();
                final SQLManager sqlManager = new MySQLManager(OWeeklyQuests.INSTANCE);

                convertData(progressionFile, sqlManager);
            });
        } catch (Exception e) {
            PluginLogger.error("An error occurred while converting YAML to MySQL.");
            PluginLogger.error(e.getMessage());
            return false;
        }

        return true;
    }
}
