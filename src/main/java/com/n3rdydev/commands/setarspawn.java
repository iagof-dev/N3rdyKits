package com.n3rdydev.commands;

import com.n3rdydev.config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class setarspawn implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        if(p.hasPermission("n3rdydev.command.setarspawn") || p.hasPermission("n3rdydev.*")){
            double spawn_x = p.getLocation().getX();
            double spawn_y = p.getLocation().getY();
            double spawn_z = p.getLocation().getZ();
            String spawn_format = (spawn_x + " " + spawn_y + " " + spawn_z);
            config.get().set("spawn.point", spawn_format);
            config.save();
            config.reload();
            com.n3rdydev.settings.spawn.load();
            p.sendMessage("§aVocê definiu o novo spawn! ("+ spawn_format + ")");
            return true;
        }
        else{
            p.sendMessage("§cSem permissão.");
            return true;
        }


    }
}
