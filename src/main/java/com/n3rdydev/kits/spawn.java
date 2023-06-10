package com.n3rdydev.kits;


import com.n3rdydev.entity.player;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class spawn {

    public static void Receive(Player p){
        p.getInventory().clear();
        player.selected_kit.put(p.getUniqueId(), "Nenhum");

        ItemStack gui_kits = new ItemStack(Material.CHEST);
        ItemStack gui_warps = new ItemStack(Material.COMPASS);

        ItemMeta kits_meta = (ItemMeta) gui_kits.getItemMeta();
        ItemMeta warps_meta = (ItemMeta) gui_warps.getItemMeta();

        kits_meta.setDisplayName("§l§eKits");
        gui_kits.setItemMeta(kits_meta);
        warps_meta.setDisplayName("§l§6Warps");
        gui_warps.setItemMeta(warps_meta);
        p.getInventory().setItem(4, gui_kits);
        p.getInventory().setItem(0, gui_warps);

        p.updateInventory();
        com.n3rdydev.scoreboard.sb_default.Set(p);

    }

}
