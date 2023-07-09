package com.n3rdydev.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

import static org.bukkit.Material.*;

public class Kits {
    static String select_kit = "§eClique para selecionar.";
    public static Inventory list_kits(Player p) {
        //                                  Player | tamanho 3 linhas com 9 colunas | Nome que aparece em cima
        Inventory inv = Bukkit.createInventory(p, 9 * 3, "Lista de Kits");

        ItemStack nao_possui = new ItemStack(STAINED_GLASS_PANE, 1, (byte) 14);

        for (int v = 0; v <= 26; v++) {
            switch (v) {
                case 0:
                    inv.setItem(v, createItem(new ItemStack(DIAMOND_SWORD), "§aPvP", "§7Kit padrão", " ", "§eClique para selecionar."));
                    break;
                case 1:
                    if (p.hasPermission("n3rdydev.kit.kangaroo")) {
                        inv.setItem(v, createItem(new ItemStack(FIREWORK), "§aKangaroo", "§7Foguetinho", " ", "§eClique para selecionar."));
                    } else {
                        inv.setItem(v, createItem(nao_possui, "§cKangaroo", "§7Foguetinho", "§cNão possui", "§eClique para selecionar."));
                    }
                    break;
                case 2:
                    if (p.hasPermission("n3rdydev.kit.boxer")) {
                        inv.setItem(v, createItem(new ItemStack(STONE_SWORD), "§aBoxer", "§7Kit Boxer", " ", select_kit));
                    } else {
                        inv.setItem(v, createItem(nao_possui, "§aBoxer", "§7Kit Boxer", "§cNão possui", select_kit));
                    }

                    break;
                case 3:
                    if (p.hasPermission("n3rdydev.kit.archer")) {
                        inv.setItem(v, createItem(new ItemStack(BOW), "§aArcher", "§7Kit Archer", "", "§eClique para selecionar."));
                    } else {
                        inv.setItem(v, createItem(nao_possui, "§aArcher", "§7Kit Archer", "§cNão possui", "§eClique para selecionar."));
                    }
                    break;
                case 4:
                    if (p.hasPermission("n3rdydev.kit.stomper")) {
                        inv.setItem(v, createItem(new ItemStack(IRON_BOOTS), "§aStomper", "§7Kit Stomper", " ", "§eClique para selecionar."));
                    } else {
                        inv.setItem(v, createItem(nao_possui, "§aStomper", "§7Kit Stomper", "§cNão possui", "§eClique para selecionar."));
                    }
                    break;
                case 5:
                    if (p.hasPermission("n3rdydev.kit.ninja")) {
                        inv.setItem(v, createItem(new ItemStack(NETHER_STAR), "§aNinja", "§7Kit Ninja", " ", "§eClique para selecionar."));
                    } else {
                        inv.setItem(v, createItem(nao_possui, "§aNinja", "§7Kit Ninja", "§cNão possui", "§eClique para selecionar."));
                    }
                    break;
                case 6:
                    if (p.hasPermission("n3rdydev.kit.feather")) {
                        inv.setItem(v, createItem(new ItemStack(FEATHER), "§aPhantom", "§7Kit Phantom", " ", "§eClique para selecionar."));
                    } else {
                        inv.setItem(v, createItem(nao_possui, "§aPhantom", "§7Kit Phantom", "§cNão possui", ""));
                    }
                    break;
                case 7:
                    if (p.hasPermission("n3rdydev.kit.gladiator")) {
                        inv.setItem(v, createItem(new ItemStack(IRON_FENCE), "§aGladiator", "§7Kit Gladiator", " ", "§eClique para selecionar."));
                    } else {
                        inv.setItem(v, createItem(nao_possui, "§aGladiator", "§7Kit Gladiator", "§cNão possui", ""));
                    }
                    break;
                default:
                    inv.setItem(v, createItem(new ItemStack(THIN_GLASS), "§aVazio", " ", " ", " "));
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
