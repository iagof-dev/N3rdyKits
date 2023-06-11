package com.n3rdydev.commands;

import com.n3rdydev.entity.player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class kit implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;

        if(args.length == 0){
            p.openInventory(com.n3rdydev.gui.kits.list_kits(p));
            return true;
        }

        if(player.selected_kit.get(p.getUniqueId()) != "Nenhum" || player.selected_kit.get(p.getUniqueId()) != "nenhum"){
            p.sendMessage("§cVocê ja está com kit!");
            return true;
        }

        String kit = args[0].toLowerCase();

        switch(args[0]){
            case "pvp":
                com.n3rdydev.kits.PvP.Receive(p);
                break;
            case "kangaroo":
                com.n3rdydev.kits.Kangaroo.Receive(p);
                break;
            case "archer":
                com.n3rdydev.kits.Archer.Receive(p);
                break;
            case "ninja":
                com.n3rdydev.kits.Ninja.Receive(p);
                break;
            case "boxer":
                com.n3rdydev.kits.Boxer.Receive(p);
                break;
            case "stomper":
                com.n3rdydev.kits.Stomper.Receive(p);
                break;
            default:
                p.sendMessage("§cKit inexistente!");
                return true;
        }
        p.sendMessage("§aVocê pegou kit " + kit);


        return true;
    }


}