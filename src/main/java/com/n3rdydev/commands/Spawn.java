package com.n3rdydev.commands;

import com.n3rdydev.entity.player;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            System.out.println("§cApenas jogadores pode usar este comando!");
            return false;
        }
        Player p = (Player) commandSender;

        player.warp.put(p.getUniqueId(), 0);
        p.getInventory().clear();
        Location spawn_loc = new Location(p.getWorld(), com.n3rdydev.settings.spawn.spawn_x, com.n3rdydev.settings.spawn.spawn_y, com.n3rdydev.settings.spawn.spawn_z);
        p.teleport(spawn_loc);
        p.setHealth(20);
        com.n3rdydev.kits.Spawn.Receive(p);
        return true;
    }
}
