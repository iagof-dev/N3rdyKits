package com.n3rdydev.events;

import com.n3rdydev.entity.player;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;

import java.util.concurrent.ThreadLocalRandom;

import static com.n3rdydev.entity.player.*;

public class handleDamage implements Listener {

    @EventHandler
    public void reduceDamage(EntityDamageByEntityEvent e) {
        //quem recebeu hit
        Player target = (Player) e.getEntity();
        //quem deu hit
        Player player = (Player) e.getDamager();

        if (player.getItemInHand().getType() == null) return;

        ItemStack feast_golden_sword = new ItemStack(Material.GOLD_SWORD, 1);
        feast_golden_sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);

        double valor = 0;
        switch (player.getItemInHand().getType()) {
            case WOOD_SWORD:
                //se for espada do feast
                valor = 1.5;
                break;
            case STONE_SWORD:
                if(selected_kit.get(player.getUniqueId()) == "ninja"){
                    int rndNumber = ThreadLocalRandom.current().nextInt(0, 1 + 1);
                    if(rndNumber >= 1){
                        valor = 2.25;
                    }
                    else{
                        valor = 2;
                    }
                }
                else{
                    valor = 2;
                }
                break;
            case GOLD_SWORD:
                if (player.getItemInHand() == feast_golden_sword) {
                    valor = 2.9;
                } else {
                    valor = 2.5;
                }
                break;
            case IRON_SWORD:
                valor = (3.5);
                break;
            case DIAMOND_SWORD:
            default:
                return;
        }
        e.setDamage(valor);
    }

}
