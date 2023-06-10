package com.n3rdydev.events;

import com.n3rdydev.entity.player;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

import java.util.HashMap;
import java.util.UUID;

public class handleMove implements Listener {

    private HashMap<UUID, Long> tp_cooldown = new HashMap<>();
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        Block block = p.getLocation().subtract(0, 1, 0).getBlock();

        switch (block.getType()){
            case SPONGE:
                handleFallDamage.launchpad.put(p.getUniqueId(), true);
                Vector direction = p.getLocation().getDirection();
                direction.setY(4);
                p.setVelocity(direction);
                break;
        }

        if(player.selected_kit.get(p.getUniqueId()) == "ninja" && p.isSneaking() != false){
            //agachando
            long lastTeleportTime = tp_cooldown.get(p.getUniqueId());
            long currentTime = System.currentTimeMillis();
            long timeDiff = currentTime - lastTeleportTime;
            long cooldown = 10000; // Tempo de cooldown em milissegundos (10 segundos)

            if (timeDiff < cooldown) {
                p.sendMessage("Você só pode teleportar novamente após " + (cooldown - timeDiff) / 1000 + " segundos.");
                event.setCancelled(true);
                return;
            }
            else{
                
            }

        }
    }
}
