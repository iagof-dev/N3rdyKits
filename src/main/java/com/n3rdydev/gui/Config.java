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

import static org.bukkit.Material.*;

public class Config {
    public static Inventory start(Player p) {
        //                                  Player | tamanho 3 linhas com 9 colunas | Nome que aparece em cima
        Inventory inv = Bukkit.createInventory(p, 9 * 3, "Configuração");

        for (int v = 0; v <= 26; v++) {
            switch (v) {
                case 10:
                    inv.setItem(v, createItem(new ItemStack(BED), "§eSpawn", "Configure o Spawn", " ", "[Clique para abrir menu]"));
                    break;
                case 13:
                    inv.setItem(v, createItem(new ItemStack(COMPASS), "§eWarps", "Configure as Warps", " ", "[Clique para abrir menu]"));
                    break;
                case 16:
                    inv.setItem(v, createItem(new ItemStack(CHEST), "§eFeast", "Configure os baús do feast", " ", "[Clique para abrir menu]"));
                    break;
                case 26:
                    inv.setItem(v, createItem(new ItemStack(BARRIER), "§eSair", "", " ", ""));
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
                    inv.setItem(v, createItem(new ItemStack(SIGN), "§eVoltar", "", " ", ""));
                    break;
                case 11:
                    inv.setItem(v, createItem(new ItemStack(NETHER_STAR), "§eDefinir Spawn", "Onde o jogador irá spawnar", " ", "[CLIQUE PARA DEFINIR]"));
                    break;
                case 15:
                    inv.setItem(v, createItem(new ItemStack(DIAMOND_SWORD), "§eProteção", "Definir proteção do spawn", " ", "[CLIQUE PARA DEFINIR]"));
                    break;
                case 26:
                    inv.setItem(v, createItem(new ItemStack(BARRIER), "§eSair", "", " ", ""));
                    break;
            }
        }
        return inv;
    }


    public static Inventory warps(Player p) {
        //                                  Player | tamanho 3 linhas com 9 colunas | Nome que aparece em cima
        Inventory inv = Bukkit.createInventory(p, 9 * 3, "Warps Config");

        for (int v = 0; v <= 26; v++) {
            switch (v) {
                case 18:
                    inv.setItem(v, createItem(new ItemStack(SIGN), "§eVoltar", "", " ", ""));
                    break;
                case 11:
                    inv.setItem(v, createItem(new ItemStack(GLASS), "§eFPS", "WARP FPS", " ", "[CLIQUE PARA DEFINIR]"));
                    break;
                case 26:
                    inv.setItem(v, createItem(new ItemStack(BARRIER), "§eSair", "", " ", ""));
                    break;
            }
        }
        return inv;
    }
    public static Inventory warp_fps(Player p) {
        //                                  Player | tamanho 3 linhas com 9 colunas | Nome que aparece em cima
        Inventory inv = Bukkit.createInventory(p, 9 * 3, "FPS CONFIG");

        for (int v = 0; v <= 26; v++) {
            switch (v) {
                case 18:
                    inv.setItem(v, createItem(new ItemStack(SIGN), "§eVoltar", "", " ", ""));
                    break;
                case 12:
                    String status = "Desativado";
                    byte cor = 14;
                    if(com.n3rdydev.settings.config.get().getBoolean("warps.fps.active") != false){
                        cor = 5;
                        status = "Ativo";
                    }
                    inv.setItem(v, createItem(new ItemStack(WOOL, 1, cor), "§eStatus: " + status, "ATIVAR OU DESATIVAR WARP FPS", " ", "[CLIQUE PARA DEFINIR]"));
                    break;
                case 14:
                    inv.setItem(v, createItem(new ItemStack(BED), "§eDefinir SPAWN", "DEFINIR SPAWN DA WARP FPS", " ", "[CLIQUE PARA DEFINIR]"));
                    break;
                case 26:
                    inv.setItem(v, createItem(new ItemStack(BARRIER), "§eSair", "", " ", ""));
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
