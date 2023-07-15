package com.n3rdydev.events;

import org.bukkit.Material;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import static com.n3rdydev.entity.player.can_build;

public class handlePlaceBlocks implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerPlaceBlocks(BlockPlaceEvent e) {
        // Havia uma checagem desnecessária
        e.setCancelled(!can_build(e.getPlayer().getUniqueId()));
    }
}
