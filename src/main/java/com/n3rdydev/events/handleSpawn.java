package com.n3rdydev.events;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;

public class handleSpawn implements Listener {

    public static void user_role(Player p){

        for (PotionEffect effect : p.getActivePotionEffects())
            p.removePotionEffect(effect.getType());

        p.setHealth(20);

        if (p.hasPermission("n3rdydev.cargo.vip")) {
            p.setDisplayName("§d[VIP] §f§d" + p.getName());
            p.setPlayerListName("§d[VIP] §f§d" + p.getName());
        }
        if (p.hasPermission("n3rdydev.cargo.admin")) {
            p.setDisplayName("§c[ADMIN] §f§c" + p.getName());
            p.setPlayerListName("§c[Admin] §f§c" + p.getName());
        }
        if (p.hasPermission("n3rdydev.cargo.developer")) {
            p.setDisplayName(ChatColor.BOLD + "" + ChatColor.GOLD + "DEV " + ChatColor.GOLD + p.getName());
            p.setPlayerListName("§6[DEV] " + p.getName());
        }
        else{
            p.setDisplayName("§7" + p.getName());
        }

        if(p.hasPermission("n3rdydev.0000.buildon")){
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission unset n3rdydev.0000.buildon");
        }


    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = (Player) e.getPlayer();
        e.setJoinMessage("");
        user_role(p);
        Location spawn_loc = new Location(p.getWorld(), com.n3rdydev.settings.spawn.spawn_x, com.n3rdydev.settings.spawn.spawn_y, com.n3rdydev.settings.spawn.spawn_z);
        p.teleport(spawn_loc);
        p.setHealth(20);
        com.n3rdydev.kits.spawn.Receive(p);
    }

}
