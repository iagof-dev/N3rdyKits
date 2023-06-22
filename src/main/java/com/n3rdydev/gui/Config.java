package com.n3rdydev.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Config {
    public static Inventory start(Player p) {
        //                                  Player | tamanho 3 linhas com 9 colunas | Nome que aparece em cima
        Inventory inv = Bukkit.createInventory(p, 9 * 3, "Configuração");

        for (int v = 0; v <= 26; v++) {
            switch (v) {
                case 10:
                    inv.setItem(v, createItem(new ItemStack(Material.BED), "§eSpawn", "Configure o Spawn", " ", "[Clique para abrir menu]"));
                    break;
                case 13:
                    inv.setItem(v, createItem(new ItemStack(Material.COMPASS), "§eWarps", "Configure as Warps", " ", "[Clique para abrir menu]"));
                    break;
                case 16:
                    inv.setItem(v, createItem(new ItemStack(Material.CHEST), "§eFeast", "Configure os baús do feast", " ", "[Clique para abrir menu]"));
                    break;
                case 26:
                    inv.setItem(v, createItem(new ItemStack(Material.BARRIER), "§eSair", "", " ", ""));
                    break;
            }
        }
        return inv;
    }

    public static Inventory spawn(Player p) {
        //                                  Player | tamanho 3 linhas com 9 colunas | Nome que aparece em cima
        Inventory inv = Bukkit.createInventory(p, 9 * 3, "Spawn Config");

        for (int v = 0; v <= 26; v++) {
            switch (v) {
                case 18:
                    inv.setItem(v, createItem(new ItemStack(Material.SIGN), "§eVoltar", "", " ", ""));
                    break;
                case 11:
                    inv.setItem(v, createItem(new ItemStack(Material.NETHER_STAR), "§eDefinir Spawn", "Onde o jogador irá spawnar", " ", "[CLIQUE PARA DEFINIR]"));
                    break;
                case 15:
                    inv.setItem(v, createItem(new ItemStack(Material.DIAMOND_SWORD), "§eProteção", "Definir proteção do spawn", " ", "[CLIQUE PARA DEFINIR]"));
                    break;
                case 26:
                    inv.setItem(v, createItem(new ItemStack(Material.BARRIER), "§eSair", "", " ", ""));
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
