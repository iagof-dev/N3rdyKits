package com.n3rdydev.kits;

import com.n3rdydev.entity.player;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Parkour {
    public static void Receive(Player p){
        p.getInventory().clear();
        player.selected_kit.put(p.getUniqueId(), "Parkour");
        player.warp.put(p.getUniqueId(), 3);
        ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP, 1);
        ItemStack r_mushroom = new ItemStack(Material.RED_MUSHROOM, 64);
        ItemStack b_mushroom = new ItemStack(Material.BROWN_MUSHROOM, 64);
        ItemStack bowl = new ItemStack(Material.BOWL, 64);
        ItemMeta sopa = (ItemMeta) soup.getItemMeta();
        sopa.setDisplayName("§eSopa Mágica");
        soup.setItemMeta(sopa);

        /*
        for (int z = 0; z <= 36; z++){
            switch(z){
                case 13:
                    p.getInventory().addItem(r_mushroom);
                    break;
                case 14:
                    p.getInventory().addItem(bowl);
                    break;
                case 15:
                    p.getInventory().addItem(b_mushroom);
                    break;
                default:
                    p.getInventory().addItem(soup);
                    break;
            }
        }

         */
        p.updateInventory();
    }
}
