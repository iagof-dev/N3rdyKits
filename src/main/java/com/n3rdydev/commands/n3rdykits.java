package com.n3rdydev.commands;

import com.n3rdydev.config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class n3rdykits implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;

        if (p.hasPermission("n3rdydev.command.reload") || p.hasPermission("n3rdydev.*")) {

            if (strings.length == 0) {
                p.sendMessage("-reload");
            }
            if (strings[0] == "reload"){
                config.reload();
                p.sendMessage("§aConfiguração reiniciada.");
            }
        }
        else{
            p.sendMessage("§cSem permissão");
        }

        return true;
    }
}
