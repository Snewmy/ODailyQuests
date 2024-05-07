package com.ordwen.odailyquests.events;

import com.ordwen.odailyquests.OWeeklyQuests;
import com.ordwen.odailyquests.configuration.essentials.UseCustomFurnaceResults;
import com.ordwen.odailyquests.configuration.integrations.ItemsAdderEnabled;
import com.ordwen.odailyquests.configuration.integrations.OraxenEnabled;
import com.ordwen.odailyquests.events.listeners.customs.CustomFurnaceExtractListener;
import com.ordwen.odailyquests.events.listeners.integrations.itemsadder.CustomBlockBreakListener;
import com.ordwen.odailyquests.events.listeners.integrations.itemsadder.ItemsAdderLoadDataListener;
import com.ordwen.odailyquests.events.listeners.integrations.oraxen.OraxenItemsLoadedListener;
import com.ordwen.odailyquests.events.listeners.item.custom.PyroFishCatchListener;
import com.ordwen.odailyquests.events.listeners.vote.VotifierListener;
import com.ordwen.odailyquests.externs.hooks.mobs.EliteMobsHook;
import com.ordwen.odailyquests.externs.hooks.mobs.MythicMobsHook;
import com.ordwen.odailyquests.externs.hooks.stackers.WildStackerHook;
import com.ordwen.odailyquests.events.listeners.entity.*;
import com.ordwen.odailyquests.events.listeners.entity.custom.mobs.EliteMobDeathListener;
import com.ordwen.odailyquests.events.listeners.entity.custom.stackers.EntityUnstackListener;
import com.ordwen.odailyquests.events.listeners.entity.custom.mobs.MythicMobDeathListener;
import com.ordwen.odailyquests.events.listeners.global.*;
import com.ordwen.odailyquests.events.listeners.inventory.InventoryClickListener;
import com.ordwen.odailyquests.events.listeners.inventory.InventoryCloseListener;
import com.ordwen.odailyquests.events.listeners.item.*;
import org.bukkit.Bukkit;

public class EventsManager {

    private final OWeeklyQuests oWeeklyQuests;

    public EventsManager(OWeeklyQuests oWeeklyQuests) {
        this.oWeeklyQuests = oWeeklyQuests;
    }

    /**
     * Registers all events.
     */
    public void registerListeners() {

        // entity events
        Bukkit.getPluginManager().registerEvents(new EntityBreedListener(), oWeeklyQuests);
        Bukkit.getPluginManager().registerEvents(new EntityTameListener(), oWeeklyQuests);
        Bukkit.getPluginManager().registerEvents(new ShearEntityListener(), oWeeklyQuests);
        Bukkit.getPluginManager().registerEvents(new EntityDeathListener(), oWeeklyQuests);
        Bukkit.getPluginManager().registerEvents(new SpawnerSpawnListener(), oWeeklyQuests);
        //Bukkit.getPluginManager().registerEvents(new ProjectileHitListener(), oDailyQuests);

        if (EliteMobsHook.isEliteMobsSetup()) {
            Bukkit.getPluginManager().registerEvents(new EliteMobDeathListener(), oWeeklyQuests);
        }

        if (MythicMobsHook.isMythicMobsSetup()) {
            Bukkit.getPluginManager().registerEvents(new MythicMobDeathListener(), oWeeklyQuests);
        }

        if (WildStackerHook.isWildStackerSetup()) {
            Bukkit.getPluginManager().registerEvents(new EntityUnstackListener(), oWeeklyQuests);
        }

        // global events
        Bukkit.getPluginManager().registerEvents(new BucketFillListener(), oWeeklyQuests);
        Bukkit.getPluginManager().registerEvents(new PlayerExpChangeListener(), oWeeklyQuests);
        Bukkit.getPluginManager().registerEvents(new PlayerLevelChangeListener(), oWeeklyQuests);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractListener(), oWeeklyQuests);
        Bukkit.getPluginManager().registerEvents(new PlayerInteractEntityListener(), oWeeklyQuests);
        Bukkit.getPluginManager().registerEvents(new PlayerDeathListener(), oWeeklyQuests);
        Bukkit.getPluginManager().registerEvents(new PlayerRespawnListener(), oWeeklyQuests);

        // item events
        Bukkit.getPluginManager().registerEvents(new BlockBreakListener(), oWeeklyQuests);
        Bukkit.getPluginManager().registerEvents(new BlockPlaceListener(), oWeeklyQuests);
        Bukkit.getPluginManager().registerEvents(new CraftItemListener(), oWeeklyQuests);
        Bukkit.getPluginManager().registerEvents(new SmithItemListener(), oWeeklyQuests);
        Bukkit.getPluginManager().registerEvents(new EnchantItemListener(), oWeeklyQuests);
        Bukkit.getPluginManager().registerEvents(new FurnaceExtractListener(), oWeeklyQuests);
        Bukkit.getPluginManager().registerEvents(new PickupItemListener(), oWeeklyQuests);
        Bukkit.getPluginManager().registerEvents(new PlayerFishListener(), oWeeklyQuests);
        Bukkit.getPluginManager().registerEvents(new PlayerItemConsumeListener(), oWeeklyQuests);
        Bukkit.getPluginManager().registerEvents(new ProjectileLaunchListener(), oWeeklyQuests);
        Bukkit.getPluginManager().registerEvents(new InventoryClickListener(), oWeeklyQuests);
        Bukkit.getPluginManager().registerEvents(new BlockDropItemListener(), oWeeklyQuests);
        Bukkit.getPluginManager().registerEvents(new PlayerHarvestBlockListener(), oWeeklyQuests);
        Bukkit.getPluginManager().registerEvents(new PlayerDropItemListener(), oWeeklyQuests);

        if (Bukkit.getServer().getPluginManager().isPluginEnabled("PyroFishingPro")) {
            Bukkit.getPluginManager().registerEvents(new PyroFishCatchListener(), oWeeklyQuests);
        }

        if (Bukkit.getServer().getPluginManager().isPluginEnabled("Votifier")) {
            Bukkit.getPluginManager().registerEvents(new VotifierListener(), oWeeklyQuests);
        }

        // inventory events
        Bukkit.getPluginManager().registerEvents(new InventoryCloseListener(), oWeeklyQuests);

        // custom events
        if (ItemsAdderEnabled.isEnabled()
                || OraxenEnabled.isEnabled()
                || UseCustomFurnaceResults.isEnabled()) {
            Bukkit.getPluginManager().registerEvents(new CustomFurnaceExtractListener(), oWeeklyQuests);
        }

        // other plugins events
        if (ItemsAdderEnabled.isEnabled()) {
            Bukkit.getPluginManager().registerEvents(new ItemsAdderLoadDataListener(oWeeklyQuests), oWeeklyQuests);
            Bukkit.getPluginManager().registerEvents(new CustomBlockBreakListener(), oWeeklyQuests);
        }

        if (OraxenEnabled.isEnabled()) {
            Bukkit.getPluginManager().registerEvents(new OraxenItemsLoadedListener(oWeeklyQuests), oWeeklyQuests);
        }
    }
}
