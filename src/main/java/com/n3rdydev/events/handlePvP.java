package com.n3rdydev.events;

import com.n3rdydev.entity.player;
import com.n3rdydev.settings.config;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class handlePvP implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntityDamageByEntity(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Player && e.getEntity() instanceof Player) {
            Player attacker = (Player) e.getDamager();
            Player target = (Player) e.getEntity();

            if (is_safe_zone(e.getEntity().getLocation())) {
                e.setCancelled(true);
            }

            if(player.selected_kit.get(attacker.getUniqueId()) == "ninja" && player.selected_kit.get(attacker) == "Ninja"){
                player.lastplayer_hit.put(attacker.getUniqueId(), target.getUniqueId());
            }

        }
    }


    private boolean is_safe_zone(Location location) {

        String spawn_pos1 = config.get().getString("spawn.protection.pos1");
        String spawn_pos2 = config.get().getString("spawn.protection.pos2");
        String[] spawn_sep_1 = spawn_pos1.split(" ");
        String[] spawn_sep_2 = spawn_pos2.split(" ");

        int x1 = Integer.parseInt(spawn_sep_1[0]);
        int x2 = Integer.parseInt(spawn_sep_2[0]);

        int z1 = Integer.parseInt(spawn_sep_1[1]);
        int z2 = Integer.parseInt(spawn_sep_2[1]);

        int minX = Math.min(x1, x2);
        int minZ = Math.min(z1, z2);
        int maxX = Math.max(x1, x2);
        int maxZ = Math.max(z1, z2);

        int locX = location.getBlockX();
        int locZ = location.getBlockZ();

        return locX >= minX && locX <= maxX && locZ >= minZ && locZ <= maxZ;
    }
}
