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

public class SoupRefil {
    public static Inventory open(Player p) {
        //                                  Player | tamanho 3 linhas com 9 colunas | Nome que aparece em cima
        Inventory inv = Bukkit.createInventory(p, 9 * 3, "Refil de sopa");
        ItemStack soup = new ItemStack(Material.MUSHROOM_SOUP, 1);
        ItemMeta sopa = (ItemMeta) soup.getItemMeta();
        sopa.setDisplayName("§eSopa Mágica");
        soup.setItemMeta(sopa);
        for (int v = 0; v <= 26; v++) {
            switch (v) {
                default:
                    inv.setItem(v, createItem(soup, "§eSopa Mágica"));
                    break;
            }
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
