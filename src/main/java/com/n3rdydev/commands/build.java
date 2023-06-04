package com.n3rdydev.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class build implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;
        String perm = "n3rdydev.0000.buildon";
        if(p.hasPermission("n3rdydev.construir") || p.hasPermission("n3rdydev.*")){
            if(p.hasPermission(perm)){
                //remove
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission unset " + perm);
                p.sendMessage("§cModo Construir DESABILITADO!");
                return true;
            }
            else{
                //adiciona
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "lp user " + p.getName() + " permission set " + perm);
                p.sendMessage("§aModo Construir HABILITADO!");
                return true;
            }
        }
        return true;
    }
}