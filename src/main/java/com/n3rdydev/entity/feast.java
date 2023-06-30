package com.n3rdydev.entity;

import org.bukkit.*;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class feast {

    public static ItemStack[] gen_items;

    public static void generate(Player p) {
        int baus = 0;
        World world = p.getWorld();
        for (Chunk c : world.getLoadedChunks()) {
            for (BlockState b : c.getTileEntities()) {
                if (b instanceof Chest) {
                    Chest chest = (Chest) b;
                    Location bau_loc = b.getLocation();
                    baus++;
                    p.sendMessage(b.getX() + " " + b.getY() + " " + b.getZ());
                    world.strikeLightning(bau_loc);

                }
            }
        }
        p.sendMessage("encontrei " + baus + " baús!");


    }

    public static void load() {
        Integer a = gen_items.length;
        a++;
        gen_items[a] = new ItemStack(Material.DIAMOND_SWORD);
    }


}
