package com.n3rdydev.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

import java.util.HashMap;
import java.util.UUID;

public class handleFallDamage implements Listener {

    public static HashMap<UUID,Boolean> launchpad = new HashMap<>();


    @EventHandler(priority = EventPriority.HIGHEST)
    public static void onPlayerFall(EntityDamageEvent e){
        if (e.getEntity() instanceof Player && e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            Player p = (Player) e.getEntity();
            if(launchpad.get(p.getUniqueId()) != false){
                e.setCancelled(true);
                launchpad.put(p.getUniqueId(), false);
            }
            else{
                e.setCancelled(false);
            }

        }
    }


}
