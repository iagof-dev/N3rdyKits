package com.n3rdydev.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Anunciar implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)) {
            System.out.println("§cApenas jogadores pode usar este comando!");
            return true;
        }
        Player p = (Player) commandSender;

        if(!(p.hasPermission("n3rdydev.command.anunciar"))){
            p.sendMessage("§cSem permissão!");
            return true;
        }

        for(Player all : Bukkit.getServer().getOnlinePlayers())
        {
            p.sendTitle("Anúncio", "Um staff enviou um anúncio no chat!");


            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < strings.length; i++) {
                if (i > 0) sb.append(" ");
                sb.append(strings[i]);
            }
            p.sendMessage(com.n3rdydev.settings.serverinfo.name() + " - §c§lAnúncio\n§r" + sb.toString());
        }
        return true;
    }
}
