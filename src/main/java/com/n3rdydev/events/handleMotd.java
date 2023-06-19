package com.n3rdydev.events;

import com.n3rdydev.settings.config;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

public class handleMotd implements Listener {
    @EventHandler
    public void onServerUpdate(ServerListPingEvent e){
        String motd_1 = config.get().getString("server.motd.1.line1");
        String motd_2 = config.get().getString("server.motd.1.line2");

        e.setMotd(motd_1 + "\n" + motd_2);
    }
}
