package com.n3rdydev.events;

import com.n3rdydev.entity.player;
import com.n3rdydev.gui.kits;
import com.n3rdydev.kits.*;
import com.n3rdydev.scoreboard.sb_default;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;

import static com.n3rdydev.entity.player.scoreboard;

public class handleKitSelector implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals("Lista de Kits") && e.getCurrentItem() != null) {
            e.setCancelled(true);
            p.closeInventory();
            switch (e.getCurrentItem().getType()) {
                case DIAMOND_SWORD:
                    PvP.Receive(p);
                    break;
                case FIREWORK:
                    Kangaroo.Receive(p);
                    break;
                case STONE_SWORD:
                    Boxer.Receive(p);
                    break;
                case BOW:
                    Archer.Receive(p);
                    break;
                case IRON_BOOTS:
                    Stomper.Receive(p);
                    break;
                case NETHER_STAR:
                    Ninja.Receive(p);
                    break;
                case FEATHER:
                    Phantom.Receive(p);
                    break;
                default:
                    p.openInventory(kits.list_kits(p));
                    break;
            }
            UUID puid = p.getUniqueId();
            if (scoreboard.get(puid) != false ) {
                sb_default.Set(p);
            }
        } else {
            e.setCancelled(false);
        }
    }
}
