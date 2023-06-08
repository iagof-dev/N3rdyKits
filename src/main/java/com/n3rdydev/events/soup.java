package com.n3rdydev.events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class soup implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInteract(PlayerInteractEvent e) {
        Player p = (Player) e.getPlayer();
        if (e.getItem() != null && e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (p.getInventory().getItemInHand().getType() == Material.MUSHROOM_SOUP && p.getHealth() < 20) {
                e.setCancelled(true);
                new BukkitRunnable() {
                    @Override
                    public void run() {

                        ItemStack pote = new ItemStack(Material.BOWL, 1);
                        ItemMeta pote_meta = pote.getItemMeta();
                        pote_meta.setDisplayName("Pote Vazio");
                        pote.setItemMeta(pote_meta);

                        p.playSound(p.getLocation(), Sound.EAT, 1, 1);
                        p.setHealth(Math.min(20, p.getHealth() + 8));

                        p.getInventory().setItemInHand(pote);
                        p.updateInventory();
                    }

                }.runTaskLater(com.n3rdydev.main.getPlugin(), 1L);

            }
        } else {
            e.setCancelled(false);
        }
    }

}
