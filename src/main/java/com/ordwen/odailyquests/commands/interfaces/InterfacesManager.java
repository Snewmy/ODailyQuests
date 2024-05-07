package com.ordwen.odailyquests.commands.interfaces;

import com.ordwen.odailyquests.OWeeklyQuests;
import com.ordwen.odailyquests.commands.interfaces.playerinterface.items.Buttons;
import com.ordwen.odailyquests.commands.interfaces.playerinterface.items.PlayerHead;
import com.ordwen.odailyquests.commands.interfaces.playerinterface.PlayerQuestsInterface;
import com.ordwen.odailyquests.files.ConfigurationFiles;
import com.ordwen.odailyquests.tools.ColorConvert;
import com.ordwen.odailyquests.tools.PluginLogger;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class InterfacesManager implements Listener {

    /**
     * Getting instance of classes.
     */
    private final ConfigurationFiles configurationFiles;
    private static QuestsInterfaces questsInterfaces;

    private static String nextPageItemName;
    private static String previousPageItemName;


    /**
     * Class instance constructor.
     *
     * @param oWeeklyQuests main class instance.
     */
    public InterfacesManager(OWeeklyQuests oWeeklyQuests) {
        this.configurationFiles = oWeeklyQuests.getConfigurationFiles();
    }

    /* variables */
    private static List<ItemStack> emptyCaseItems;
    private static String globalQuestsInventoryName;
    private static String easyQuestsInventoryName;
    private static String mediumQuestsInventoryName;
    private static String hardQuestsInventoryName;

    /**
     * Load all interfaces objects.
     */
    public void initAllObjects() {
        new Buttons(configurationFiles).initItems();

        initInventoryNames();
        loadInterfaces();
    }

    /**
     * Load all interfaces.
     */
    public void loadInterfaces() {
        loadPlayerQuestsInterface();
        loadQuestsInterfaces();
    }

    /**
     * Load player quests interface.
     */
    public void loadPlayerQuestsInterface() {
        new PlayerHead().initPlayerHead();
        new PlayerQuestsInterface().loadPlayerQuestsInterface();

        initEmptyCaseItems();
    }

    /**
     * Load categorized quests interface.
     */
    public void loadQuestsInterfaces() {
        initPaginationItemNames();
        questsInterfaces = new QuestsInterfaces(configurationFiles);

        if (configurationFiles.getConfigFile().getInt("quests_mode") == 2) {
            questsInterfaces.loadCategorizedInterfaces();
        }
        else questsInterfaces.loadGlobalInterface();
    }
    /**
     * Init variables.
     */
    public void initInventoryNames() {
        final ConfigurationSection section = configurationFiles.getConfigFile().getConfigurationSection("interfaces");

        globalQuestsInventoryName = ColorConvert.convertColorCode(section.getString(".global_quests.inventory_name"));
        easyQuestsInventoryName = ColorConvert.convertColorCode(section.getString(".easy_quests.inventory_name"));
        mediumQuestsInventoryName = ColorConvert.convertColorCode(section.getString(".medium_quests.inventory_name"));
        hardQuestsInventoryName = ColorConvert.convertColorCode(section.getString(".hard_quests.inventory_name"));

        PluginLogger.fine("Interfaces names successfully loaded.");
    }

    /**
     * Init empty case items.
     */
    public void initEmptyCaseItems() {
        emptyCaseItems = new ArrayList<>();
        emptyCaseItems.addAll(PlayerQuestsInterface.getFillItems());
        emptyCaseItems.addAll(QuestsInterfaces.getEmptyCaseItems());
    }

    /**
     * Init pagination item names.
     */
    public void initPaginationItemNames() {
        nextPageItemName = ColorConvert.convertColorCode(configurationFiles.getConfigFile().getConfigurationSection("interfaces").getString(".next_item_name"));
        previousPageItemName = ColorConvert.convertColorCode(configurationFiles.getConfigFile().getConfigurationSection("interfaces").getString(".previous_item_name"));
    }

    public static String getGlobalQuestsInventoryName() {
        return globalQuestsInventoryName;
    }

    public static String getEasyQuestsInventoryName() {
        return easyQuestsInventoryName;
    }

    public static String getMediumQuestsInventoryName() {
        return mediumQuestsInventoryName;
    }

    public static String getHardQuestsInventoryName() {
        return hardQuestsInventoryName;
    }
    public static List<ItemStack> getEmptyCaseItems() { return emptyCaseItems; }
    public static String getNextPageItemName() { return nextPageItemName; }
    public static String getPreviousPageItemName() { return previousPageItemName; }

    public static Inventory getInterfaceFirstPage(String category, Player player) {
        return questsInterfaces.getInterfacePage(category, 0, player);
    }

    public static Inventory getInterfaceNextPage(String category, int page, Player player) {
        return questsInterfaces.getInterfacePage(category, page + 1, player);
    }

    public static Inventory getInterfacePreviousPage(String category, int page, Player player) {
        return questsInterfaces.getInterfacePage(category, page - 1, player);
    }
}


