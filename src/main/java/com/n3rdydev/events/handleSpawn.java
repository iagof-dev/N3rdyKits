package com.n3rdydev.events;

import com.n3rdydev.commands.Admin;
import com.n3rdydev.entity.player;
import com.n3rdydev.kits.Spawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.potion.PotionEffect;

import java.util.UUID;

import static com.n3rdydev.entity.player.can_build;
import static com.n3rdydev.entity.player.scoreboard;

public class handleSpawn implements Listener {

    public static void user_setup(Player p) {
        UUID puid = p.getUniqueId();
        player.selected_kit.put(puid, "Nenhum");
        handleFallDamage.launchpad.put(puid, false);
        scoreboard.put(puid, true);
        can_build.put(puid, false);
        player.loadStats(p);
        com.n3rdydev.scoreboard.sb_default.Set(p);

        p.setHealth(20);
        for (PotionEffect effect : p.getActivePotionEffects())
            p.removePotionEffect(effect.getType());

        String prefix = "§7" + p.getName();

        if (p.hasPermission("n3rdydev.cargo.vip")) {
            prefix = ("§d[VIP] " + p.getName());
        }
        if (p.hasPermission("n3rdydev.cargo.admin")) {
            prefix = ("§c[ADMIN] " + p.getName());
        }
        if (p.hasPermission("n3rdydev.cargo.developer")) {
            prefix = ("§6[DEV] " + p.getName());
        }

        p.setDisplayName(prefix);
        p.setPlayerListName(prefix);

        if (p.hasPermission("n3rdydev.0000.buildon")) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission unset n3rdydev.0000.buildon");
        }
        if (p.hasPermission("n3rdydev.command.admin")) {
            Admin.invis.put(p.getUniqueId(), false);
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = (Player) e.getPlayer();
        e.setJoinMessage("");
        user_setup(p);
        Location spawn_loc = new Location(p.getWorld(), com.n3rdydev.settings.spawn.spawn_x, com.n3rdydev.settings.spawn.spawn_y, com.n3rdydev.settings.spawn.spawn_z);
        p.teleport(spawn_loc);
        p.setHealth(20);
        Spawn.Receive(p);
    }

}
