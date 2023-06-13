package com.n3rdydev.settings;

public class spawn {

    public static double spawn_x;
    public static double spawn_y;
    public static double spawn_z;

    public static void load(){
        String spawn_cord = config.get().getString("spawn.point");
        String[] spawn_sep = spawn_cord.split(" ");
        spawn_x = Double.parseDouble(spawn_sep[0]);
        spawn_y = Double.parseDouble(spawn_sep[1]);
        spawn_z = Double.parseDouble(spawn_sep[2]);
    }

}
