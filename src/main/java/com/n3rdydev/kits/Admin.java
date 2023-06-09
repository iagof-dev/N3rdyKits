package com.n3rdydev.kits;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Admin {

    public static void Receive(Player p) {
        p.getInventory().clear();

        ItemStack kb_stick = new ItemStack(Material.STICK, 1);
        ItemMeta kb_stick_meta = (ItemMeta) kb_stick.getItemMeta();
        kb_stick_meta.setDisplayName("§7« §e§lTeste de Knockback§r §7»");
        kb_stick.setItemMeta(kb_stick_meta);
        kb_stick.addUnsafeEnchantment(Enchantment.KNOCKBACK, 1000);


        for (int z = 0; z <= 36; z++) {
            switch (z) {

                case 0:
                    p.getInventory().addItem(kb_stick);
                    break;
            }

        }


        p.updateInventory();
    }
}
