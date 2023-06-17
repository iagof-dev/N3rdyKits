package com.n3rdydev.commands;

import com.n3rdydev.settings.serverinfo;
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

        String nome = serverinfo.name();
        Player p = (Player) sender;
        if(p.hasPermission("n3rdydev.command.build") || p.hasPermission("n3rdydev.*")){
            toggleBuild(p);
            if(can_build(p)){
                //remove
                p.sendMessage(nome + " §cModo Construir DESABILITADO!");
            }
            else{
                //adiciona
                p.sendMessage(nome + " §aModo Construir HABILITADO!");
            }
            return true;
        }
        return true;
    }
}