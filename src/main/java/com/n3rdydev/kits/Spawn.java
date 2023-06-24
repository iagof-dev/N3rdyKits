package com.n3rdydev.kits;


import com.n3rdydev.entity.player;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Spawn {

    public static void Receive(Player p){
        ItemStack[] armorContents = p.getInventory().getArmorContents().clone();
        p.getInventory().clear();
        p.getInventory().setArmorContents(armorContents);

        player.selected_kit.put(p.getUniqueId(), "Nenhum");

        ItemStack gui_kits = new ItemStack(Material.CHEST);
        ItemStack gui_warps = new ItemStack(Material.COMPASS);
        ItemStack gui_loja = new ItemStack(Material.EMERALD);
        

        ItemMeta kits_meta = (ItemMeta) gui_kits.getItemMeta();
        ItemMeta warps_meta = (ItemMeta) gui_warps.getItemMeta();
        ItemMeta loja_meta = (ItemMeta) gui_loja.getItemMeta();

        kits_meta.setDisplayName("§l§eKits");
        warps_meta.setDisplayName("§l§6Warps");
        loja_meta.setDisplayName("§l§eLoja");

        gui_kits.setItemMeta(kits_meta);
        gui_warps.setItemMeta(warps_meta);
        gui_loja.setItemMeta(loja_meta);

        p.getInventory().setItem(4, gui_kits);
        p.getInventory().setItem(0, gui_warps);
        p.getInventory().setItem(8, gui_loja);

        p.updateInventory();
    }

}
