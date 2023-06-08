package com.n3rdydev.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class handleInventory implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals("Lista de Kits")) {
            e.setCancelled(true);
            switch (e.getCurrentItem().getType()) {
                case DIAMOND_SWORD:
                    p.closeInventory();
                    com.n3rdydev.kits.PvP.Receive(p);
                    break;
                case FIREWORK:
                    p.closeInventory();
                    com.n3rdydev.kits.Kangaroo.Receive(p);
                    break;
            }
            p.teleport(arena_tp.random_tp(p));
        } else {
            e.setCancelled(false);
        }
    }
}
