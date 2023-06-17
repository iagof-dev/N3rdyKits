package com.n3rdydev.kits;

import com.n3rdydev.entity.player;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Phantom {

    public static void Receive(Player p){
        p.getInventory().clear();
        player.selected_kit.put(p.getUniqueId(), "phantom");

        ItemStack sword = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP, 1);
        ItemStack r_mushroom = new ItemStack(Material.RED_MUSHROOM, 64);
        ItemStack b_mushroom = new ItemStack(Material.BROWN_MUSHROOM, 64);
        ItemStack bowl = new ItemStack(Material.BOWL, 64);
        ItemMeta sopa = (ItemMeta) soup.getItemMeta();
        sopa.setDisplayName("§eSopa Mágica");
        soup.setItemMeta(sopa);
        ItemStack player_track = new ItemStack(Material.COMPASS, 1);
        ItemMeta ptrack_meta = (ItemMeta) player_track.getItemMeta();
        ptrack_meta.setDisplayName("§eRastreador");
        player_track.setItemMeta(ptrack_meta);
        ItemStack feather = new ItemStack(Material.FEATHER, 1);

        ItemMeta feather_meta = feather.getItemMeta();
        feather_meta.setDisplayName("§ePhantom");
        feather.setItemMeta(feather_meta);


        for (int z = 0; z <= 36; z++){

            switch(z){
                case 0:
                    p.getInventory().addItem(sword);
                    break;
                case 1:
                    p.getInventory().addItem(feather);
                    break;
                case 13:
                    p.getInventory().addItem(r_mushroom);
                    break;
                case 14:
                    p.getInventory().addItem(bowl);
                    break;
                case 15:
                    p.getInventory().addItem(b_mushroom);
                    break;
                case 8:
                    p.getInventory().addItem(player_track);
                    break;
                default:
                    p.getInventory().addItem(soup);
                    break;

            }
        }
        p.updateInventory();
        player.randomTpArena(p);





    }
}
