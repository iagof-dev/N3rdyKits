package com.n3rdydev.events;

import com.n3rdydev.entity.player;
import com.n3rdydev.entity.server;
import com.n3rdydev.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.util.UUID;

public class handlePlayerQuit implements Listener {

    private PlayerManager manager;

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerQuit(PlayerQuitEvent e) {
        Player p = e.getPlayer();
        UUID puid = p.getUniqueId();
        player.saveStats(p.getUniqueId());
        if(server.arena_glad.get(puid) != null && server.arena_glad_players.get(puid) != null){
            Player target = Bukkit.getServer().getPlayer(server.arena_glad_players.get(puid));
            UUID target_uid = target.getUniqueId();
            target.teleport(manager.getPlayers().get(target_uid).getLast_pos());
            server.remArenaGlad(server.arena_glad.get(puid), target, p);
            player.addXP(target_uid);
            player.addKills(target_uid);
            player.addDeaths(puid);
            server.arena_glad.put(target_uid, null);
            server.arena_glad.put(puid, null);
            target.sendMessage("§aO Jogador " + p.getName() + " deslogou... (+5 XP)");
            com.n3rdydev.scoreboard.sb_default.Set(target);
        }
        server.arena_glad.put(puid, null);
        e.setQuitMessage("");
    }
}
