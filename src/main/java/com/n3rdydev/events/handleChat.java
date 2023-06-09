package com.n3rdydev.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class handleChat implements Listener {

    @EventHandler (priority = EventPriority.HIGHEST)
    public static void onPlayerChat(PlayerChatEvent e){
        e.setFormat(e.getPlayer().getName() + ": " + e.getMessage());
    }

}
