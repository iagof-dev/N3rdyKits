package com.n3rdydev.events;

import com.n3rdydev.config;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.Random;

public class arena_tp {

    public static Location random_tp(Player p) {
        Location coords;

        Random rnd = new Random();
        int rndNumber = rnd.nextInt(0,10);

        switch(rndNumber){
            case 0:
                String arena0 = config.get().getString("arenas.arena0");
                String[] sp_cord_arena0 = arena0.split(" ");
                Double spawn_x_0 = Double.parseDouble(sp_cord_arena0[0]);
                Double spawn_y_0 = Double.parseDouble(sp_cord_arena0[1]);
                Double spawn_z_0 = Double.parseDouble(sp_cord_arena0[2]);
                coords = new Location(p.getWorld(), spawn_x_0, spawn_y_0, spawn_z_0);
                return coords;
            case 1:
                String arena1 = config.get().getString("arenas.arena1");
                String[] sp_cord_arena1 = arena1.split(" ");
                Double spawn_x_1 = Double.parseDouble(sp_cord_arena1[0]);
                Double spawn_y_1 = Double.parseDouble(sp_cord_arena1[1]);
                Double spawn_z_1 = Double.parseDouble(sp_cord_arena1[2]);
                coords = new Location(p.getWorld(), spawn_x_1, spawn_y_1, spawn_z_1);
                return coords;
            case 2:
                String arena2 = config.get().getString("arenas.arena2");
                String[] sp_cord_arena2 = arena2.split(" ");
                Double spawn_x_2 = Double.parseDouble(sp_cord_arena2[0]);
                Double spawn_y_2 = Double.parseDouble(sp_cord_arena2[1]);
                Double spawn_z_2 = Double.parseDouble(sp_cord_arena2[2]);
                coords = new Location(p.getWorld(), spawn_x_2, spawn_y_2, spawn_z_2);
                return coords;

        }


        coords = new Location(p.getWorld(), 0, 0, 0);


        return coords;
    }

}
