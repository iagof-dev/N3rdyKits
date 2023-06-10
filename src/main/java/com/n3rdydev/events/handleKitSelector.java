package com.n3rdydev.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class handleKitSelector implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals("Lista de Kits")) {
            e.setCancelled(true);
            p.closeInventory();
            switch (e.getCurrentItem().getType()) {
                case DIAMOND_SWORD:
                    com.n3rdydev.kits.PvP.Receive(p);
                    break;
                case FIREWORK:
                    com.n3rdydev.kits.Kangaroo.Receive(p);
                    break;
                case STONE_SWORD:
                    com.n3rdydev.kits.Boxer.Receive(p);
                    break;
                case BOW:
                    com.n3rdydev.kits.Archer.Receive(p);
                    break;
                case IRON_BOOTS:
                    com.n3rdydev.kits.Stomper.Receive(p);
                    break;
                default:
                    p.openInventory(com.n3rdydev.gui.kits.list_kits(p));
                    break;
            }
            com.n3rdydev.scoreboard.sb_default.Set(p);
        } else {
            e.setCancelled(false);
        }
    }
}
