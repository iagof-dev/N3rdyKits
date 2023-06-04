package com.n3rdydev.events;

import com.n3rdydev.config;
import com.n3rdydev.settings.spawn;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class listener implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = (Player) e.getPlayer();
        e.setJoinMessage("");

        Location spawn_loc = new Location(p.getWorld(),  spawn.spawn_x, spawn.spawn_y, spawn.spawn_z);

        p.teleport(spawn_loc);
        p.setHealth(20);
        com.n3rdydev.kits.spawn.Receive(p);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onWeatherChange(WeatherChangeEvent e) {
        e.setCancelled(true);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = (Player) e.getEntity().getPlayer();
        e.setDeathMessage("");

        new BukkitRunnable() {
            @Override
            public void run() {
                for (ItemStack drop : e.getDrops()) {
                    drop.setAmount(0);
                }
                e.getDrops().clear();
                p.playSound(p.getLocation(), Sound.LAVA_POP, 1, 1);
            }
        }.runTaskLater(com.n3rdydev.main.getPlugin(), 60L);

        e.getEntity().spigot().respawn();
        p.getInventory().clear();
        p.updateInventory();

        Location spawn_loc = new Location(p.getWorld(),  spawn.spawn_x, spawn.spawn_y, spawn.spawn_z);
        p.teleport(spawn_loc);

        com.n3rdydev.kits.spawn.Receive(p);
        p.updateInventory();

        if (e.getEntity().getKiller() == null) {
            p.sendMessage("§cVocê morreu!");
        } else {
            Player pk = (Player) e.getEntity().getKiller().getPlayer();
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
    public void onplayerMove(PlayerMoveEvent e) {
        Player p = e.getPlayer();
        Location p_loc = p.getLocation();

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
            return;
        }

        if(item_drop == Material.CHEST){
            e.setCancelled(true);
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
    public void PlayerInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (e.getItem() != null && e.getItem().getType().equals(Material.CHEST)) {
                p.openInventory(com.n3rdydev.gui.kits.list_kits(p));
                e.setCancelled(false);
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        if (e.getView().getTitle().equals("Lista de Kits")) {
            switch (e.getCurrentItem().getType()) {
                case DIAMOND_SWORD:
                    p.closeInventory();
                    com.n3rdydev.kits.PvP.Receive(p);
                    break;
            }
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
