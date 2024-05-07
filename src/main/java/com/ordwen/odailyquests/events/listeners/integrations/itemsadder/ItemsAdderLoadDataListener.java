package com.ordwen.odailyquests.events.listeners.integrations.itemsadder;

import com.ordwen.odailyquests.OWeeklyQuests;
import com.ordwen.odailyquests.configuration.integrations.ItemsAdderEnabled;
import com.ordwen.odailyquests.tools.PluginLogger;
import dev.lone.itemsadder.api.Events.ItemsAdderLoadDataEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class ItemsAdderLoadDataListener implements Listener {

    private final OWeeklyQuests plugin;

    public ItemsAdderLoadDataListener(OWeeklyQuests plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onItemsAdderLoadData(ItemsAdderLoadDataEvent event) {
        PluginLogger.info("ItemsAdder updated its data. Reloading...");
        ItemsAdderEnabled.setLoaded(true);
        plugin.getReloadService().reload();
    }
}
