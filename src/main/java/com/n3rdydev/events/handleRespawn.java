package com.n3rdydev.events;

import com.n3rdydev.settings.spawn;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class handleRespawn implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerRespawnEvent(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        Location spawn_loc = new Location(p.getWorld(), com.n3rdydev.settings.spawn.spawn_x, com.n3rdydev.settings.spawn.spawn_y, spawn.spawn_z);
        e.setRespawnLocation(spawn_loc);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerDeathEvent e) {
        Player p = (Player) e.getEntity().getPlayer();
        handleFallDamage.launchpad.put(p.getUniqueId(), false);
        e.setDeathMessage("");
        e.getDrops().clear();

        if (e.getEntity().getKiller() == null) {
            p.sendMessage("§cVocê morreu!");
        } else {
            Player pk = (Player) e.getEntity().getKiller().getPlayer();
            p.sendMessage("§cVocê morreu para " + pk.getName() + "! (-5 xp)");
            pk.sendMessage("§a Você matou " + p.getName() + "! (+5 xp)");
            com.n3rdydev.scoreboard.sb_default.Set(pk);

        }

        e.getEntity().spigot().respawn();

        new BukkitRunnable() {
            @Override
            public void run() {
                com.n3rdydev.scoreboard.sb_default.Set(p);
                com.n3rdydev.kits.spawn.Receive(p);
            }
        }.runTaskLater(com.n3rdydev.main.getPlugin(), 1L);
    }
}
