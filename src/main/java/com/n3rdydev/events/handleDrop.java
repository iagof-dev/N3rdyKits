package com.n3rdydev.events;

import com.n3rdydev.settings.spawn;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class handleDrop implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerDrop(PlayerDropItemEvent e) {
        Player p = (Player) e.getPlayer();
        Material item_drop = e.getItemDrop().getItemStack().getType();

        boolean blocked = false;

        switch (item_drop) {
            case DIAMOND_SWORD:
            case IRON_SWORD:
            case GOLD_SWORD:
            case STONE_SWORD:
            case WOOD_SWORD:
            case CHEST:
            case FIREWORK:
            case COMPASS:
            case BOW:
            case IRON_FENCE:
            case FEATHER:
                blocked = true;
                break;
        }

        if (blocked) {
            e.setCancelled(true);
            p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 1);
            return;
        }

        Item drop = e.getItemDrop();
        new BukkitRunnable() {
            @Override
            public void run() {
                drop.remove();
                p.playSound(p.getLocation(), Sound.LAVA_POP, 1, 1);
            }
        }.runTaskLater(com.n3rdydev.main.getPlugin(), 60L);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerTake(PlayerPickupItemEvent e) {

        if(spawn.is_safe_zone(e.getItem().getLocation())){
            e.setCancelled(true);
        }

        new BukkitRunnable() {
            @Override
            public void run() {
                e.setCancelled(true);
            }
        }.runTaskLater(com.n3rdydev.main.getPlugin(), 60L);
    }

}
