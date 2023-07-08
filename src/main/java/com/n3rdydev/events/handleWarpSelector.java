package com.n3rdydev.events;

import com.n3rdydev.gui.Kits;
import com.n3rdydev.gui.Warps;
import com.n3rdydev.kits.*;
import com.n3rdydev.scoreboard.sb_default;
import com.n3rdydev.settings.config;
import com.n3rdydev.settings.serverinfo;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;

import static com.n3rdydev.entity.player.*;
import static com.n3rdydev.entity.player.scoreboard;

public class handleWarpSelector implements Listener {

    //Esta classe irá controlar, dependendo em qual item o usuário/jogador
    //irá executar um evento...
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onInventoryClick(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();

        Location tp;
        if (e.getView().getTitle().equals("Warps") && e.getCurrentItem() != null) {
            e.setCancelled(true);
            p.closeInventory();
            switch (e.getCurrentItem().getType()) {
                case GLASS:
                    if(config.get().getBoolean("warps.fps.active") != false){
                        tp = convert_config_location("warps.fps.spawnpos");
                        p.teleport(tp);
                        FPS.Receive(p);
                        return;
                    }
                    p.sendMessage(serverinfo.name() + " | §cWarp em manutenção!");
                    break;
                case LAVA_BUCKET:
                    if(config.get().getBoolean("warps.lavachallenge.active") != false){
                        tp = convert_config_location("warps.lavachallenge.spawnpos");
                        p.teleport(tp);
                        LavaChallenge.Receive(p);
                        return;
                    }
                    p.sendMessage(serverinfo.name() + " | §cWarp em manutenção!");
                    break;
                default:
                    p.openInventory(Warps.open());
                    break;
            }
            UUID puid = p.getUniqueId();
            if (scoreboard.get(puid) != false ) {
                sb_default.Set(p);
            }
            return;

        }
    }

}
