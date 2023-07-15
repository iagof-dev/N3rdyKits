package com.n3rdydev.events;

import com.n3rdydev.entity.player;
import com.n3rdydev.entity.server;
import com.n3rdydev.main;
import org.bukkit.Location;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

public class handleWarpParkour implements Listener {

    @EventHandler(priority= EventPriority.HIGH)
    public void onPlayerOverCheckpoint(PlayerMoveEvent e) {
        if (player.warp.get(e.getPlayer().getUniqueId()) != 3) return;

        if (e.getTo().getBlock().isLiquid()) {
            if (player.getParkourCheckpoint(e.getPlayer().getUniqueId()) == null) {
                e.getPlayer().teleport(new Location(e.getPlayer().getWorld(), 216, 99, -1, e.getPlayer().getLocation().getYaw(), e.getPlayer().getLocation().getPitch()));
                return;
            }
            Location tp = player.getParkourCheckpoint(e.getPlayer().getUniqueId());
            e.getPlayer().teleport(new Location(server.getWorld(), tp.getX(), tp.getY()+1, tp.getZ(), tp.getYaw(), tp.getPitch()));
        }
        new BukkitRunnable() {
            @Override
            public void run() {
                Player p = e.getPlayer();
                UUID puid = p.getUniqueId();
                Block b = p.getLocation().getBlock().getRelative(BlockFace.DOWN);

            /*
                Golden block = CheckPoint
                Diamond block = Finish and give 1.000xp
            */

                switch (b.getType()) {
                    case GOLD_BLOCK:
                        Location checkpoint = player.getParkourCheckpoint(e.getPlayer().getUniqueId());
                        if (player.getParkourCheckpoint(e.getPlayer().getUniqueId()) != null && b.getLocation() ==  player.getParkourCheckpoint(e.getPlayer().getUniqueId()))
                            return;
                        player.setParkourCheckpoint(puid, b.getLocation());
                        p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 1, 1);
                        return;
                    case DIAMOND_BLOCK:
                        p.sendMessage("Você finalizou o parkour! (+1000 xp)");
                        player.setFinishParkour(puid);
                        p.teleport(new Location(p.getWorld(), 216, 99, -1));
                        p.playSound(p.getLocation(), Sound.FIREWORK_LARGE_BLAST, 1, 1);
                        return;
                }

            }
        }.runTaskLater(main.getPlugin(), 8L);
    }
}
