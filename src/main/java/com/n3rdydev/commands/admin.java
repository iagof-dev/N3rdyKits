package com.n3rdydev.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class admin implements CommandExecutor {

    public static HashMap<UUID, Boolean> invis = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;

        //Não finalizado

        if(p.hasPermission("n3rdydev.command.admin") || p.hasPermission("n3rdydev.*")){

            Collection<? extends Player> players = p.getServer().getOnlinePlayers();

            if(invis.get(p.getUniqueId()) != false){
                for (Player p_list : players) {
                    if(!p_list.hasPermission("n3rdydev.command.admin")){
                        p_list.showPlayer(p);
                    }

                }
                p.setAllowFlight(false);
                p.setFlying(false);
                com.n3rdydev.kits.spawn.Receive(p);
                p.sendMessage(com.n3rdydev.settings.serverinfo.name() + " §cVocê saiu do MODO ADMIN e está visivel!");
                invis.put(p.getUniqueId(), false);
            }
            else{
                for (Player p_list : players) {
                    if(!p_list.hasPermission("n3rdydev.command.admin")) {
                        p_list.hidePlayer(p);
                    }
                    else{
                        if(p_list.getName() != p.getName()){
                            p_list.sendMessage(com.n3rdydev.settings.serverinfo.name() + " §eStaff "+ p.getName() + " entrou no modo ADMIN!");
                            p_list.showPlayer(p);
                        }
                    }
                }
                com.n3rdydev.kits.Admin.Receive(p);
                p.setAllowFlight(true);
                p.setFlying(true);
                p.sendMessage(com.n3rdydev.settings.serverinfo.name() + " §aVocê entrou no MODO ADMIN e está invisivel!");
                invis.put(p.getUniqueId(), true);
            }

            return true;
        }

        return false;
    }
}
