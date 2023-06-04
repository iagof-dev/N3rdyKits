package com.n3rdydev.events;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class soup extends listener {

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = (Player) e.getPlayer();
        if(e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if(p.getInventory().getItemInHand().getType() == Material.MUSHROOM_SOUP && p.getHealth() < 20) {
                e.setCancelled(true);
                ItemStack pote = new ItemStack(Material.BOWL, 1);
                p.getInventory().setItemInHand(pote);
                p.playSound(p.getLocation(), Sound.EAT, 1, 1);
                p.setHealth(Math.min(20, p.getHealth() + 8));
                p.updateInventory();
            }
        }
    }

}
