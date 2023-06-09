package com.n3rdydev.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class admin implements CommandExecutor {

    HashMap<UUID, Boolean> invis = new HashMap<>();

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;

        //Não finalizado

        if(p.hasPermission("n3rdydev.command.admin") || p.hasPermission("n3rdydev.*")){

            Collection<? extends Player> players = p.getServer().getOnlinePlayers();

            if(invis.get(p.getUniqueId()) != false || invis.get(p.getUniqueId()) != null){
                for (Player p_list : players) {
                    if(!p_list.hasPermission("n3rdydev.command.admin")){
                        p_list.showPlayer(p);
                    }
                }
                p.sendMessage("§cVocê saiu do modo invisivel!");
                invis.put(p.getUniqueId(), false);
            }
            else{
                for (Player p_list : players) {
                    if(!p_list.hasPermission("n3rdydev.command.admin")){
                        p_list.hidePlayer(p);
                    }
                }
                p.sendMessage("§eVocê está no modo invisivel!");
                invis.put(p.getUniqueId(), true);
            }

            return true;
        }

        return false;
    }
}
