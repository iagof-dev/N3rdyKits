package com.n3rdydev.events;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerJoinEvent;

public class spawn extends Listeners{



    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e){
        Player p = (Player) e.getPlayer();
        World world_p = p.getWorld();
        Location spawn = new Location(world_p, 0,0,0);
        p.teleport(spawn);

    }


    public void load_spawn(){

    }

}
