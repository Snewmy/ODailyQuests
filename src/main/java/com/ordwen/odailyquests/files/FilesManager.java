package com.ordwen.odailyquests.files;

import com.ordwen.odailyquests.OWeeklyQuests;

public class FilesManager {

    private final OWeeklyQuests oWeeklyQuests;

    public FilesManager(OWeeklyQuests oWeeklyQuests) {
        this.oWeeklyQuests = oWeeklyQuests;
    }

    /**
     * Load all files.
     */
    public void loadAllFiles() {

        oWeeklyQuests.getConfigurationFiles().loadConfigurationFiles();
        oWeeklyQuests.getConfigurationFiles().loadMessagesFiles();

        new QuestsFiles(oWeeklyQuests).loadQuestsFiles();
        new ProgressionFile(oWeeklyQuests).loadProgressionFile();
        new HologramsFile(oWeeklyQuests).loadHologramsFile();
        new PlayerInterfaceFile(oWeeklyQuests).loadPlayerInterfaceFile();
    }
}
