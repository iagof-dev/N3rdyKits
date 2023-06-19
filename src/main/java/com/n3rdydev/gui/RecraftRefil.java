package com.n3rdydev.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public class RecraftRefil extends JavaPlugin implements Listener {
    public static Inventory open(Player p) {


        Inventory inv = Bukkit.createInventory(p, 9 * 3, "Refil de Recraft");

        //bomba não pega '-'


        ItemStack cogu_vermelho = new ItemStack(Material.RED_MUSHROOM, 64);
        ItemStack cogu_marrom = new ItemStack(Material.BROWN_MUSHROOM, 64);
        ItemStack pote = new ItemStack(Material.BOWL, 64);

        int control = 0;
        for (int v = 1; v <= 9; v++) {
            inv.setItem(control, createItem(cogu_vermelho, "Cogumelo Vermelho"));
            control+= 1;
            inv.setItem(control, createItem(pote, "Pote Vazio"));
            control+= 1;
            inv.setItem(control, createItem(cogu_marrom, "Cogumelo Marrom"));
            control+=1;
        }


        return inv;
    }

    private static ItemStack createItem(ItemStack item, String nome, String... descricao) {

        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(ChatColor.translateAlternateColorCodes('&', nome));
        List<String> lores = new ArrayList<>();
        for (String s : descricao) {
            lores.add(ChatColor.translateAlternateColorCodes('&', s));
        }
        meta.setLore(lores);
        item.setItemMeta(meta);
        return item;
    }


}
