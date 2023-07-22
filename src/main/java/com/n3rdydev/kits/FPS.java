package com.n3rdydev.kits;

import com.n3rdydev.entity.player;
import com.n3rdydev.manager.PlayerManager;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FPS {
    private static PlayerManager manager;

    public static void Receive(Player p) {
        p.getInventory().clear();
        manager.jogador.get(p.getUniqueId()).setKit("fps");
        manager.jogador.get(p.getUniqueId()).setWarp(1);

        ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP, 1);
        ItemStack r_mushroom = new ItemStack(Material.RED_MUSHROOM, 64);
        ItemStack b_mushroom = new ItemStack(Material.BROWN_MUSHROOM, 64);
        ItemStack diamond_sword = new ItemStack(Material.DIAMOND_SWORD, 1);
        ItemStack bowl = new ItemStack(Material.BOWL, 64);
        ItemMeta sopa = (ItemMeta) soup.getItemMeta();
        sopa.setDisplayName("§eSopa Mágica");
        soup.setItemMeta(sopa);

        //armadura
        ItemStack helmet = new ItemStack(Material.IRON_HELMET, 1);
        ItemStack chestplate = new ItemStack(Material.IRON_CHESTPLATE, 1);
        ItemStack leggings = new ItemStack(Material.IRON_LEGGINGS, 1);
        ItemStack boots = new ItemStack(Material.IRON_BOOTS, 1);


        for (int z = 0; z <= 39; z++) {
            switch (z) {
                case 0:
                    p.getInventory().setItem(z, diamond_sword);
                    break;
                case 13:
                    p.getInventory().setItem(z, r_mushroom);
                    break;
                case 14:
                    p.getInventory().setItem(z, bowl);
                    break;
                case 15:
                    p.getInventory().setItem(z, b_mushroom);
                    break;
                case 36:
                    p.getInventory().setItem(z, boots);
                    break;
                case 37:
                    p.getInventory().setItem(z, leggings);
                    break;
                case 38:
                    p.getInventory().setItem(z, chestplate);
                    break;
                case 39:
                    p.getInventory().setItem(z, helmet);
                    break;
                default:
                    p.getInventory().setItem(z, soup);
                    break;
            }

        }
        p.updateInventory();
    }

}
