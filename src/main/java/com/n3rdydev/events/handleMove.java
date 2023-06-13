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
    }
}
