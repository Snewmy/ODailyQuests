package com.ordwen.odailyquests.events.restart;

import com.ordwen.odailyquests.OWeeklyQuests;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.server.ServerCommandEvent;

public class RestartCommandListener extends RestartHandler implements Listener {

    public RestartCommandListener(OWeeklyQuests oWeeklyQuests) {
        super(oWeeklyQuests);
    }

    @EventHandler
    public void onPlayerCommandPreprocess(PlayerCommandPreprocessEvent event) {
        final String command = event.getMessage();
        if (command.equals("/stop") || command.equals("/restart")) {
            setServerStopping();
        }
    }

    @EventHandler
    public void onServerCommand(ServerCommandEvent event) {
        final String command = event.getCommand();
        if (command.equalsIgnoreCase("stop") || command.equalsIgnoreCase("restart")) {
            setServerStopping();
        }
    }
}
