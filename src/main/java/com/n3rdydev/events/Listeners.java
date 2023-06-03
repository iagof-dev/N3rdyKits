package com.n3rdydev.events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

import javax.imageio.stream.ImageInputStream;

public class Listeners implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = (Player) e.getPlayer();
        e.setJoinMessage("");
        p.getInventory().clear();
        p.updateInventory();

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWeatherChange(WeatherChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerDeathEvent e) {
        e.setDeathMessage("");
        Player p = (Player) e.getEntity().getPlayer();
        if(e.getEntity().getKiller() == null){
            p.sendMessage("§cVocê morreu!");
        }
        else{
            Player pk = (Player) e.getEntity().getKiller().getPlayer();
            e.getEntity().spigot().respawn();
            p.sendMessage("§cVocê morreu para " + pk.getDisplayName() + "! (-5 xp)");
            pk.sendMessage("§a Você matou " + p.getDisplayName() + "! (+5 xp)");
        }

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = (Player) e.getPlayer();
        e.setQuitMessage("");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerHunger(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerDrop(PlayerDropItemEvent e) {
        Player p = (Player) e.getPlayer();
        Material item_drop = e.getItemDrop().getItemStack().getType();
        if (item_drop == Material.DIAMOND_SWORD || item_drop == Material.IRON_SWORD || item_drop == Material.STONE_SWORD || item_drop == Material.WOOD_SWORD || item_drop == Material.GOLD_SWORD) {
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
            }.runTaskLater(com.n3rdydev.main.getPlugin(), 60L);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerTake(PlayerPickupItemEvent e) {
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerBreak(BlockBreakEvent e) {
        if (e.getPlayer().hasPermission("n3rdydev.0000.buildon")) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerPlaceBlocks(BlockPlaceEvent e) {
        if (e.getPlayer().hasPermission("n3rdydev.0000.buildon")) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }


    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerItemDamage(PlayerItemDamageEvent e) {
        ItemStack item = e.getItem();
        if (item.getType() == Material.DIAMOND_SWORD || item.getType() == Material.IRON_SWORD || item.getType() == Material.STONE_SWORD || item.getType() == Material.GOLD_SWORD || item.getType() == Material.WOOD_SWORD) {

        }

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void MobsExplode(EntityExplodeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void TntExplode(ExplosionPrimeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void Lava(BlockSpreadEvent e) {
        e.setCancelled(true);
    }

}
