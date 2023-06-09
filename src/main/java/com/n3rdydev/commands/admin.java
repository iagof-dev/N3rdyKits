package com.n3rdydev.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;

public class admin implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;

        //Não finalizado

        if(p.hasPermission("n3rdydev.command.admin") || p.hasPermission("n3rdydev.*")){

            Collection<? extends Player> players = p.getServer().getOnlinePlayers();

            for (Player p_list : players) {
                if(!p_list.hasPermission("n3rdydev.command.admin")){
                    p_list.hidePlayer(p);
                }
            }

            p.sendMessage("§cVocê está no modo invisivel!");

            return true;
        }

        return false;
    }
}
