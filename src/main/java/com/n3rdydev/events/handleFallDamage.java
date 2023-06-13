package com.n3rdydev.events;

import com.n3rdydev.entity.player;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
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
            Location landingLocation = p.getLocation().subtract(0, e.getDamage(), 0);
            Block fallblock = landingLocation.getBlock();
            if(launchpad.get(p.getUniqueId()) != false){
                e.setDamage(0);
                launchpad.put(p.getUniqueId(), false);
            }
            else{
                e.setCancelled(false);
            }

            if(player.selected_kit.get(p.getUniqueId()) == "stomper"){
                e.setDamage(0);
                for (Entity entity : p.getNearbyEntities(3, 3, 3)) {
                    if (entity instanceof Player) {
                        Player target = (Player) entity;
                        if (!target.isSneaking()) {
                            float fallDistance = p.getFallDistance();
                            float damage = (fallDistance - 3) / 2;
                            double health_calc = target.getHealth() - damage;
                            target.damage(damage);
                            if(health_calc < 1){
                                target.sendMessage("§cVocê morreu para " + p.getName() + "! (-5 xp)");
                                p.sendMessage("§a Você matou " + target.getName() + "! (+5 xp)");
                                com.n3rdydev.entity.player.addKills(p);
                                com.n3rdydev.entity.player.addDeaths(target);
                                com.n3rdydev.scoreboard.sb_default.Set(target);
                            }

                        }
                    }
                }
            }
        }
    }


}
