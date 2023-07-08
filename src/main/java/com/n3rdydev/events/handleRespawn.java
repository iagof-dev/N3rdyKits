package com.n3rdydev.events;

import com.n3rdydev.entity.player;
import com.n3rdydev.kits.FPS;
import com.n3rdydev.kits.LavaChallenge;
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

import static com.n3rdydev.entity.player.convert_config_location;
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
                player.selected_kit.put(puid, "Nenhum");
                if (e.getEntity().getKiller() == null) {
                    player.addDeaths(p);
                    p.sendMessage("§cVocê morreu!");
                    if(scoreboard.get(puid) != false || scoreboard.get(puid) == null){
                        sb_default.Set(p);
                    }
                } else {
                    Player pk = (Player) e.getEntity().getKiller().getPlayer();
                    p.sendMessage("§cVocê morreu para " + pk.getName() + "! (-5 xp)");
                    pk.sendMessage("§a Você matou " + p.getName() + "! (+5 xp)");

                    player.addKills(pk.getUniqueId());
                    player.addDeaths(p);
                    player.remXP(puid);
                    UUID pkuid = pk.getUniqueId();
                    player.addXP(pkuid);

                    if (scoreboard.get(pkuid) != false && pkuid != null) {
                        sb_default.Set(pk);
                    }
                    if(scoreboard.get(puid) != false || scoreboard.get(puid) == null){
                        sb_default.Set(p);
                    }
                }
                e.getEntity().spigot().respawn();

                int warp = player.warp.get(puid);
                Location tp;
                if( warp != 0){
                    switch (warp){
                        case 1:
                            tp = convert_config_location("warps.fps.spawnpos");
                            p.teleport(tp);
                            FPS.Receive(p);
                            break;
                        case 2:
                            tp = convert_config_location("warps.lavachallenge.spawnpos");
                            p.teleport(tp);
                            LavaChallenge.Receive(p);
                            break;
                    }

                }
                else{
                    Spawn.Receive(p);
                }
            }
        }.runTaskLater(main.getPlugin(), 1L);

    }
}
