package com.n3rdydev.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class kit implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;
        String kit = args[0].toLowerCase();

        switch(args[0]){
            case "pvp":
                com.n3rdydev.kits.PvP.Receive(p);
                break;
            default:
                p.sendMessage("§cKit inexistente!");
                return true;
        }
        p.sendMessage("§aVocê pegou kit " + kit);


        return true;
    }


}