package com.n3rdydev.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Tphere implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            System.out.println("§cApenas jogadores pode usar este comando!");
            return true;
        }
        Player p = (Player) commandSender;

        if(!(p.hasPermission("n3rdydev.command.tphere"))){
            p.sendMessage("§cSem permissão!");
            return true;
        }

        if(strings[0] != null){
            Player vitima = Bukkit.getPlayer(strings[0]);
            if (vitima == null){
                p.sendMessage(com.n3rdydev.settings.serverinfo.name() + " | §cJogador não encontrado!");
                return true;
            }

            vitima.teleport(p.getLocation());

            p.sendMessage(com.n3rdydev.settings.serverinfo.name() + " | §aVocê teleportou o jogador " + vitima.getName() + " até você!");
            return true;

        }
            return false;
    }
}
