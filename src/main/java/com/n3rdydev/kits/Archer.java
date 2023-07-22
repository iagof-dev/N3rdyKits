package com.n3rdydev.kits;

import com.n3rdydev.entity.player;
import com.n3rdydev.manager.PlayerManager;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Archer {

    private static PlayerManager manager;

    public static boolean Receive(Player p) {
        if (!(p.hasPermission("n3rdydev.kit.archer") || p.hasPermission("n3rdydev.kit.*"))) {
            p.sendMessage("§cVocê não possui o kit Archer.");
            return false;
        }

        p.getInventory().clear();
        manager.jogador.get(p.getUniqueId()).setKit("archer");

        ItemStack sword = new ItemStack(Material.STONE_SWORD, 1);
        ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP, 1);
        ItemStack r_mushroom = new ItemStack(Material.RED_MUSHROOM, 64);
        ItemStack b_mushroom = new ItemStack(Material.BROWN_MUSHROOM, 64);
        ItemStack bowl = new ItemStack(Material.BOWL, 64);

        ItemStack bow = new ItemStack(Material.BOW, 1);
        bow.addEnchantment(Enchantment.ARROW_INFINITE, 1);
        ItemStack arrow = new ItemStack(Material.ARROW, 1);

        ItemStack player_track = new ItemStack(Material.COMPASS, 1);
        ItemMeta ptrack_meta = (ItemMeta) player_track.getItemMeta();
        ptrack_meta.setDisplayName("§eRastreador");
        player_track.setItemMeta(ptrack_meta);


        ItemMeta sopa = (ItemMeta) soup.getItemMeta();
        sopa.setDisplayName("§eSopa Mágica");
        soup.setItemMeta(sopa);


        for (int z = 0; z <= 36; z++) {

            switch (z) {
                case 0:
                    p.getInventory().addItem(sword);
                    break;
                case 1:
                    p.getInventory().addItem(bow);
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
                case 17:
                    p.getInventory().addItem(arrow);
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
        return true;

    }
}
