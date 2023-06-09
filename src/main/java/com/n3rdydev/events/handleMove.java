package com.n3rdydev.events;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.util.Vector;

public class handleMove implements Listener {
    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event) {
        Player p = event.getPlayer();
        Block block = p.getLocation().subtract(0, 1, 0).getBlock();

        switch (block.getType()){
            case SPONGE:
                handleFallDamage.launchpad.put(p.getUniqueId(), true);
                Vector direction = p.getLocation().getDirection();
                direction.setY(5);
                p.setVelocity(direction);
                break;
        }
    }
}
