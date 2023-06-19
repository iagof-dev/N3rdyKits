package com.n3rdydev.events;

import com.n3rdydev.entity.player;
import com.n3rdydev.kits.Spawn;
import com.n3rdydev.main;
import com.n3rdydev.scoreboard.sb_default;
import com.n3rdydev.settings.spawn;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

import static com.n3rdydev.entity.player.scoreboard;

public class handleRespawn implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerRespawnEvent(PlayerRespawnEvent e) {
        Player p = e.getPlayer();
        Location spawn_loc = new Location(p.getWorld(), com.n3rdydev.settings.spawn.spawn_x, com.n3rdydev.settings.spawn.spawn_y, spawn.spawn_z);
        e.setRespawnLocation(spawn_loc);
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerDeathEvent e) {
        e.setDeathMessage("");
        e.getDrops().clear();
        new BukkitRunnable() {
            @Override
            public void run() {
                Player p = (Player) e.getEntity().getPlayer();
                p.setFireTicks(0);
                handleFallDamage.launchpad.put(p.getUniqueId(), false);
                UUID puid = p.getUniqueId();

                if (e.getEntity().getKiller() == null) {
                    player.addDeaths(p);
                    p.sendMessage("§cVocê morreu!");


                } else {
                    Player pk = (Player) e.getEntity().getKiller().getPlayer();
                    p.sendMessage("§cVocê morreu para " + pk.getName() + "! (-5 xp)");
                    pk.sendMessage("§a Você matou " + p.getName() + "! (+5 xp)");
                    player.addKills(pk);
                    player.addDeaths(p);
                    UUID pkuid = pk.getUniqueId();

                    if (scoreboard.get(pkuid) != false ) {
                        sb_default.Set(pk);
                    }


                }

                e.getEntity().spigot().respawn();

                Spawn.Receive(p);



            }
        }.runTaskLater(main.getPlugin(), 1L);

    }
}
