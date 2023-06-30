package com.n3rdydev.kits;


import com.n3rdydev.entity.player;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Spawn {


    public static ItemStack gui_kits = new ItemStack(Material.CHEST);
    public static ItemStack gui_warps = new ItemStack(Material.COMPASS);
    public static ItemStack gui_loja = new ItemStack(Material.EMERALD);

    public static void Receive(Player p){
        ItemStack[] armorContents = p.getInventory().getArmorContents().clone();
        p.getInventory().clear();
        p.getInventory().setArmorContents(armorContents);

        player.selected_kit.put(p.getUniqueId(), "Nenhum");

        ItemMeta kits_meta = (ItemMeta) gui_kits.getItemMeta();
        ItemMeta warps_meta = (ItemMeta) gui_warps.getItemMeta();
        ItemMeta loja_meta = (ItemMeta) gui_loja.getItemMeta();

        kits_meta.setDisplayName("§6§l« §rKits »§6§l");
        warps_meta.setDisplayName("§6§l« §rWarps »§6§l");
        loja_meta.setDisplayName("§6§l« §rLoja »§6§l");

        gui_kits.setItemMeta(kits_meta);
        gui_warps.setItemMeta(warps_meta);
        gui_loja.setItemMeta(loja_meta);

        p.getInventory().setItem(4, gui_kits);
        p.getInventory().setItem(0, gui_warps);
        p.getInventory().setItem(8, gui_loja);

        p.updateInventory();
    }

}
