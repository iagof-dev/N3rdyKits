package com.n3rdydev.settings;

import org.bukkit.Location;

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

    public static boolean is_safe_zone(Location location) {

        String spawn_pos1 = config.get().getString("spawn.protection.pos1");
        String spawn_pos2 = config.get().getString("spawn.protection.pos2");
        String[] spawn_sep_1 = spawn_pos1.split(" ");
        String[] spawn_sep_2 = spawn_pos2.split(" ");

        double x1 = Double.parseDouble(spawn_sep_1[0]);
        double x2 = Double.parseDouble(spawn_sep_2[0]);
        double z1 = Double.parseDouble(spawn_sep_1[1]);
        double z2 = Double.parseDouble(spawn_sep_2[1]);

        double minX = Math.min(x1, x2);
        double minZ = Math.min(z1, z2);
        double maxX = Math.max(x1, x2);
        double maxZ = Math.max(z1, z2);

        double locX = location.getBlockX();
        double locZ = location.getBlockZ();

        return locX >= minX && locX <= maxX && locZ >= minZ && locZ <= maxZ;
    }

}
