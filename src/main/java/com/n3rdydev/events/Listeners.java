package com.n3rdydev.events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

import javax.imageio.stream.ImageInputStream;

public class Listeners implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = (Player) e.getPlayer();
        e.setJoinMessage("");
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = (Player) e.getPlayer();
        e.setQuitMessage("");
    }

    @EventHandler
    public void PlayerHunger(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler
    public void PlayerDrop(PlayerDropItemEvent e) {
        Player p = (Player) e.getPlayer();
        Material item_drop = e.getItemDrop().getItemStack().getType();
        if (item_drop == Material.DIAMOND_SWORD) {
            e.setCancelled(true);
            p.playSound(p.getLocation(), Sound.ITEM_BREAK, 1, 1);
            p.sendMessage("§cCuidado guerreiro, não drope seu armamento...");
        } else {
            Item drop = e.getItemDrop();
            new BukkitRunnable() {
                @Override
                public void run() {
                    drop.remove();
                    p.playSound(p.getLocation(), Sound.LAVA_POP, 1, 1);
                }
            }.runTaskLater(com.n3rdydev.main.getPlugin(), 100L);
        }
    }

    @EventHandler
    public void PlayerTake(PlayerPickupItemEvent e) {
        e.setCancelled(true);
    }


}
