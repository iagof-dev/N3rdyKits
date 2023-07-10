package com.n3rdydev.events;

import com.n3rdydev.entity.player;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.EventListener;
import java.util.UUID;

public class handleTeleport implements Listener {

    @EventHandler
    public static void PlayerShift(PlayerToggleSneakEvent e) {
        Player p = e.getPlayer();
        UUID puid = p.getUniqueId();
        if (player.selected_kit.get(p.getUniqueId()) == null) {
            return;
        }
        if (player.lastplayer_hit.get(puid) == null) {
            return;
        }

        //kit ninja
        String kit = player.selected_kit.get(puid);
        kit = kit.toLowerCase();
        if (kit == "ninja") {
            return;
        }
        Boolean cooldown = com.n3rdydev.entity.player.getCooldown(puid);
        if (!(cooldown)) {

            UUID vitima = player.lastplayer_hit.get(puid);
            Player victim = Bukkit.getPlayer(vitima);
            Location tp = victim.getLocation();
            if (com.n3rdydev.settings.spawn.is_safe_zone(tp) != true) {
                p.teleport(tp);
                com.n3rdydev.entity.player.setCooldown(p.getUniqueId(), 7);
            }

        } else if (cooldown) {
            p.sendMessage(player.getCooldownTime(p.getUniqueId()));
        }
    }
}