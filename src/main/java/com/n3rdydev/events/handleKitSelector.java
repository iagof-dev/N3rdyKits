package com.n3rdydev.events;

import com.n3rdydev.gui.Kits;
import com.n3rdydev.kits.*;
import com.n3rdydev.scoreboard.sb_default;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;

import static com.n3rdydev.entity.player.scoreboard;

public class handleKitSelector implements Listener {

    //Essa classe determina qual kit o jogador clica, e oq o jogador irá receber no inventario antes do teleporte

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();



        if (e.getView().getTitle().equals("Lista de Kits") && e.getCurrentItem() != null) {
            e.setCancelled(true);
            p.closeInventory();
            boolean sucess = false;
            switch (e.getCurrentItem().getType()) {
                case DIAMOND_SWORD:
                    sucess = PvP.Receive(p);
                    break;
                case FIREWORK:
                    sucess = Kangaroo.Receive(p);
                    break;
                case STONE_SWORD:
                    sucess = Boxer.Receive(p);
                    break;
                case BOW:
                    sucess = Archer.Receive(p);
                    break;
                case IRON_BOOTS:
                    sucess = Stomper.Receive(p);
                    break;
                case NETHER_STAR:
                    sucess = Ninja.Receive(p);
                    break;
                case FEATHER:
                    sucess = Phantom.Receive(p);
                    break;
                default:
                    p.openInventory(Kits.list_kits(p));
                    break;
            }
            UUID puid = p.getUniqueId();
            if (scoreboard.get(puid) != false ) {
                sb_default.Set(p);
            }

            if(sucess != false){
                p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 1, 1);
            }
            else{
                p.playSound(p.getLocation(), Sound.NOTE_BASS , 1, 1);
            }

            return;
        }
    }
}
