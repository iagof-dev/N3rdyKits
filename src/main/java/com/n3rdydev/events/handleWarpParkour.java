package com.n3rdydev.events;

import com.n3rdydev.entity.player;
import com.n3rdydev.entity.server;
import com.n3rdydev.main;
import com.n3rdydev.manager.PlayerManager;
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
    private PlayerManager manager;

    @EventHandler(priority = EventPriority.HIGH)
    public void onPlayerOverCheckpoint(PlayerMoveEvent e) {
        if (manager.jogador.get(e.getPlayer().getUniqueId()).getWarp() != 3) return;

        if (e.getTo().getBlock().isLiquid()) {
            if (player.getParkourCheckpoint(e.getPlayer().getUniqueId()) == null) {
                e.getPlayer().teleport(new Location(e.getPlayer().getWorld(), 216, 99, -1, e.getPlayer().getLocation().getYaw(), e.getPlayer().getLocation().getPitch()));
                return;
            }
            Location tp = player.getParkourCheckpoint(e.getPlayer().getUniqueId());
            e.getPlayer().teleport(new Location(server.getWorld(), tp.getX(), tp.getY() + 1, tp.getZ(), tp.getYaw(), tp.getPitch()));
        }

        Player p = e.getPlayer();
        UUID puid = p.getUniqueId();
        Block b = p.getLocation().getBlock().getRelative(BlockFace.DOWN);

            /*
                Golden block = CheckPoint
                Diamond block = Finish and give 1.000xp
            */

        switch (b.getType()) {
            case GOLD_BLOCK:
                boolean pula = false;
                if(player.getParkourCheckpoint(puid) == null){
                    p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 1, 1);
                    p.sendTitle("Checkpoint!", "§7Você alcançou o checkpoint...");
                    player.setParkourCheckpoint(puid, b.getLocation());
                    return;
                }
                Location LastCheckpoint = player.getParkourCheckpoint(puid);
                if(b.getLocation().getX() == LastCheckpoint.getX() && b.getLocation().getZ() == LastCheckpoint.getZ()) return;
                p.playSound(p.getLocation(), Sound.SUCCESSFUL_HIT, 1, 1);
                p.sendTitle("Checkpoint!", "§7Você alcançou o checkpoint...");
                player.setParkourCheckpoint(puid, b.getLocation());
                return;
            case DIAMOND_BLOCK:
                p.sendMessage("Você finalizou o parkour! (+1000 xp)");
                p.sendTitle("§dV§co§ac§9ê§6 §3f§2i§1n§8a§fl§6i§5z§4o§bu§c §do §ep§3a§2r§1k§5o§9u§7r§e!", "§7Você ganhou 1.000 xp");
                player.setFinishParkour(puid);
                p.teleport(new Location(p.getWorld(), 216, 99, -1));
                p.playSound(p.getLocation(), Sound.FIREWORK_LARGE_BLAST, 1, 1);
                return;
        }

    }
}
