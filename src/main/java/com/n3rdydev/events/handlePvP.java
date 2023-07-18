package com.n3rdydev.events;

import com.n3rdydev.entity.player;
import com.n3rdydev.manager.PlayerManager;
import com.n3rdydev.settings.config;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class handlePvP implements Listener {

    private PlayerManager manager;


    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            Player attacker = (Player) e.getDamager();
            Player target = (Player) e.getEntity();

            if (com.n3rdydev.settings.spawn.is_safe_zone(e.getEntity().getLocation())) {
                e.setCancelled(true);
            }

            String selected_kit = manager.getPlayers().get(attacker.getUniqueId()).getKit();
            selected_kit = selected_kit.toLowerCase();
            if(selected_kit == "ninja"){
                manager.getPlayers().get(attacker.getUniqueId()).setLastHit(target.getUniqueId());
            }

        }
    }



}
