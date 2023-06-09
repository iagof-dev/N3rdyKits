package com.n3rdydev.events;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class handleInteract implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {

            if (e.getItem() != null && e.getItem().getType().equals(Material.CHEST)) {
                p.openInventory(com.n3rdydev.gui.kits.list_kits(p));
                return;
            }
            if (e.getItem() != null && e.getItem().getType().equals(Material.FIREWORK)) {
                if (e.getItem().getItemMeta().getDisplayName().equals("§eKangaroo")) {
                    e.setCancelled(true);
                    if (p.isOnGround()) {
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                Vector direction = p.getLocation().getDirection();
                                direction.setY(0.50); // Adjust the jump height as needed
                                direction.multiply(2.0); // Adjust the push force as needed
                                p.setVelocity(direction);
                            }
                        }.runTaskLater(com.n3rdydev.main.getPlugin(), 1L);

                    }

                    return;
                }

            }

            e.setCancelled(false);
        } else {
            e.setCancelled(false);
        }
    }
}
