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

public class Warps{

        public static Inventory open(Player p) {
            //                                  Player | tamanho 3 linhas com 9 colunas | Nome que aparece em cima
            Inventory inv = Bukkit.createInventory(p, 9 * 3, "Warps");
            ItemStack vazio = new ItemStack(Material.THIN_GLASS, 1);
            for (int v = 0; v <= 26; v++) {
                switch (v) {
                    case 13:
                        inv.setItem(v, createItem(new ItemStack(Material.GLASS, 1),"§6FPS", "warp pra pc ruim", "[Clique para teleportar]"));
                        break;
                    default:
                        inv.setItem(v, createItem(vazio, "§eVazio"));
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
