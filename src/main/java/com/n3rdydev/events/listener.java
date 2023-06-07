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
    public void PlayerRespawnEvent(PlayerRespawnEvent e){
        Player p = e.getPlayer();
        Location spawn_loc = new Location(p.getWorld(),  spawn.spawn_x, spawn.spawn_y, spawn.spawn_z);
        e.setRespawnLocation(spawn_loc);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = (Player) e.getEntity().getPlayer();
        e.setDeathMessage("");
        e.getDrops().clear();

        if (e.getEntity().getKiller() == null) {
            p.sendMessage("§cVocê morreu!");
        } else {
            Player pk = (Player) e.getEntity().getKiller().getPlayer();
            p.sendMessage("§cVocê morreu para " + pk.getDisplayName() + "! (-5 xp)");
            pk.sendMessage("§a Você matou " + p.getDisplayName() + "! (+5 xp)");
        }

        e.getEntity().spigot().respawn();

        new BukkitRunnable() {
            @Override
            public void run() {
                com.n3rdydev.kits.spawn.Receive(p);
            }
        }.runTaskLater(com.n3rdydev.main.getPlugin(), 1L);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent e) {
        e.setQuitMessage("");
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onplayerMove(PlayerMoveEvent e) {

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerDrop(PlayerDropItemEvent e) {
        Player p = (Player) e.getPlayer();
        Material item_drop = e.getItemDrop().getItemStack().getType();

        boolean is_sword = false;

        switch(item_drop){
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
                e.setCancelled(true);
                break;
            case COMPASS:
                e.setCancelled(true);
                break;
        }

        if(is_sword){
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
    public void PlayerInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            if (e.getItem() != null && e.getItem().getType().equals(Material.CHEST)) {
                p.openInventory(com.n3rdydev.gui.kits.list_kits(p));
            }
            else{
                e.setCancelled(false);
            }
        }
        else{
            e.setCancelled(false);
        }
    }

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
        }
        else{
            e.setCancelled(false);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            Player attacker = (Player) e.getDamager();
            Player target = (Player) e.getEntity();

            if (is_safe_zone(e.getEntity().getLocation(), -28, 378, -34, 384)) {
                e.setCancelled(true);
            }
        }
    }


    private boolean is_safe_zone(Location location, int x1, int z1, int x2, int z2) {
        int minX = Math.min(x1, x2);
        int minZ = Math.min(z1, z2);
        int maxX = Math.max(x1, x2);
        int maxZ = Math.max(z1, z2);

        int locX = location.getBlockX();
        int locZ = location.getBlockZ();

        return locX >= minX && locX <= maxX && locZ >= minZ && locZ <= maxZ;
    }

    private boolean calc_between(Location location, int x1, int z1, int x2, int z2) {
        int minX = Math.min(x1, x2);
        int minZ = Math.min(z1, z2);
        int maxX = Math.max(x1, x2);
        int maxZ = Math.max(z1, z2);

        int locX = location.getBlockX();
        int locZ = location.getBlockZ();

        return locX >= minX && locX <= maxX && locZ >= minZ && locZ <= maxZ;
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
    public void PlayerHunger(FoodLevelChangeEvent e) {
        e.setCancelled(true);
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
