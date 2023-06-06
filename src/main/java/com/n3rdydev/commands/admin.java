package com.n3rdydev.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class admin implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;

        if(p.hasPermission("n3rdydev.command.admin") || p.hasPermission("n3rdydev.*")){

            return true;
        }

        return false;
    }
}
