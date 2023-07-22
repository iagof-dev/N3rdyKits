package com.n3rdydev.events;

import com.n3rdydev.entity.player;
import com.n3rdydev.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.EventListener;
import java.util.UUID;

public class handleTeleport implements Listener {

    private PlayerManager manager;

    @EventHandler
    public void PlayerShift(PlayerToggleSneakEvent e) {
        Player p = e.getPlayer();
        UUID puid = p.getUniqueId();
        if (manager.jogador.get(puid).getKit() == null) return;

        if (manager.jogador.get(puid).getLastHit() == null) return;


        //kit ninja
        String kit = manager.jogador.get(puid).getKit().toLowerCase();
        if (kit != "ninja") {
            return;
        }
        Boolean cooldown = com.n3rdydev.entity.player.getCooldown(puid);
        if (cooldown != true) {

            UUID vitima = manager.jogador.get(puid).getLastHit();
            Player victim = Bukkit.getPlayer(vitima);
            Location tp = victim.getLocation();
            if (com.n3rdydev.settings.spawn.is_safe_zone(tp) != true) {
                p.teleport(tp);
                com.n3rdydev.entity.player.setCooldown(puid, 7);
            }

        } else if (cooldown) {
            p.sendMessage(player.getCooldownTime(puid));
        }
    }
}