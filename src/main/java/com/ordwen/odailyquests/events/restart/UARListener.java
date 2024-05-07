package com.ordwen.odailyquests.events.restart;

import com.ordwen.odailyquests.OWeeklyQuests;
import dev.norska.uar.api.UARRestartEvent;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

public class UARListener extends RestartHandler implements Listener {

    public UARListener(OWeeklyQuests oWeeklyQuests) {
        super(oWeeklyQuests);
    }

    @EventHandler
    public void onUARRestart(UARRestartEvent event) {
        setServerStopping();
    }
}
