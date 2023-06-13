package com.n3rdydev.events;

import com.n3rdydev.entity.player;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerToggleSneakEvent;

import java.util.EventListener;

public class handleTeleport implements Listener {

    @EventHandler
    public static void PlayerShift(PlayerToggleSneakEvent e){
        Player p = e.getPlayer();
        if(player.selected_kit.get(p.getUniqueId()) == null){
            return;
        }


        //kit ninja
        String kit = player.selected_kit.get(p.getUniqueId());
        kit = kit.toLowerCase();
        if(kit == "ninja"){
            Boolean cooldown = com.n3rdydev.entity.player.getCooldown(p);
            if (cooldown != true){
                if(player.lastplayer_hit.get(p) != null){
                    Player victim = Bukkit.getPlayer(player.lastplayer_hit.get(p));
                    Location tp = victim.getLocation();
                    p.teleport(tp);
                    com.n3rdydev.entity.player.setCooldown(p, 7);
                }
            }
            else{
                p.sendMessage(com.n3rdydev.entity.player.getCooldownTime(p));
            }
        }

    }


}
