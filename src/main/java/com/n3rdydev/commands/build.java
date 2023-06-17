package com.n3rdydev.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import static com.n3rdydev.entity.player.can_build;
import static com.n3rdydev.entity.player.toggleBuild;

public class build implements CommandExecutor, Listener {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;
        if(p.hasPermission("n3rdydev.command.build") || p.hasPermission("n3rdydev.*")){
            if(can_build(p)){
                //remove
                toggleBuild(p);
                p.sendMessage(com.n3rdydev.settings.serverinfo.name() + " §cModo Construir DESABILITADO!");
                return true;
            }
            else{
                //adiciona
                toggleBuild(p);
                p.sendMessage(com.n3rdydev.settings.serverinfo.name() + " §aModo Construir HABILITADO!");
                return true;
            }
        }
        return true;
    }
}