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

        boolean is_sword = false;

        switch (item_drop) {
            case DIAMOND_SWORD:
                is_sword = true;
                break;
            case IRON_SWORD:
                is_sword = true;
                break;
            case GOLD_SWORD:
                is_sword = true;
                break;
            case STONE_SWORD:
                is_sword = true;
                break;
            case WOOD_SWORD:
                is_sword = true;
                break;
            case CHEST:
            case FIREWORK:
            case COMPASS:
            case BOW:
            case FEATHER:
                e.setCancelled(true);
                break;
        }

        if (is_sword) {
            e.setCancelled(true);
            p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 1);
            p.sendMessage("§cCuidado guerreiro, não drope seu armamento...");
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
