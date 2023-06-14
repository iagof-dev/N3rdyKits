package com.n3rdydev.events;

import com.n3rdydev.settings.config;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class handleArenaTeleport {

    public static Location random_tp(Player p) {
        Location coords;
        int min = 1;
        int max = 10;
        int rndNumber = ThreadLocalRandom.current().nextInt(min, max + 1);


        //Random rnd = new Random();
        //int rndNumber = rnd.nextInt(0, 10);


        String arena0 = config.get().getString("arenas.arena"+rndNumber);
        String[] sp_cord_arena = arena0.split(" ");
        Double spawn_x = Double.parseDouble(sp_cord_arena[0]);
        Double spawn_y = Double.parseDouble(sp_cord_arena[1]);
        Double spawn_z = Double.parseDouble(sp_cord_arena[2]);
        coords = new Location(p.getWorld(), spawn_x, spawn_y, spawn_z);
        return coords;
    }

}
