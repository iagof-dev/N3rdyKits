package com.n3rdydev.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tpall implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)) {
            System.out.println("§cApenas jogadores pode usar este comando!");
            return true;
        }
        Player p = (Player) commandSender;

        if(!(p.hasPermission("n3rdydev.command.tpall"))){
            p.sendMessage("§cSem permissão!");
            return true;
        }

        for(Player all : Bukkit.getServer().getOnlinePlayers())
        {
            all.teleport(p.getLocation());
        }

        p.sendMessage(com.n3rdydev.settings.serverinfo.name() + " | §aVocê teleportou todos os jogadores online para sua localização!");

        return true;
    }
}
