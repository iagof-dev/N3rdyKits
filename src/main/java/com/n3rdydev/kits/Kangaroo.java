package com.n3rdydev.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Kangaroo {

    public static void Receive(Player p) {
        p.getInventory().clear();

        ItemStack sword = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP, 1);
        ItemStack r_mushroom = new ItemStack(Material.RED_MUSHROOM, 64);
        ItemStack b_mushroom = new ItemStack(Material.BROWN_MUSHROOM, 64);
        ItemStack bowl = new ItemStack(Material.BOWL, 64);
        ItemStack kangaroo = new ItemStack(Material.FIREWORK, 1);


        ItemMeta kangaroo_meta = (ItemMeta) kangaroo.getItemMeta();
        kangaroo_meta.setDisplayName("§eKangaroo");
        kangaroo.setItemMeta(kangaroo_meta);

        ItemMeta sopa = (ItemMeta) soup.getItemMeta();
        sopa.setDisplayName("§eSopa Mágica");
        soup.setItemMeta(sopa);


        for (int z = 0; z <= 36; z++) {

            switch (z) {
                case 0:
                    p.getInventory().addItem(sword);
                    break;
                case 1:
                    p.getInventory().addItem(kangaroo);
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
                default:
                    p.getInventory().addItem(soup);
                    break;

            }
        }
        p.updateInventory();
    }

}
