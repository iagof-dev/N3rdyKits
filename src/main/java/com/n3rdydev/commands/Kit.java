package com.n3rdydev.commands;

import com.n3rdydev.gui.Kits;
import com.n3rdydev.settings.serverinfo;
import com.n3rdydev.entity.player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import com.n3rdydev.kits.*;

public class Kit implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        if(!(commandSender instanceof Player)) {
            System.out.println("§cApenas jogadores pode usar este comando!");
            return true;
        }

        Player p = (Player) commandSender;

        if(args.length == 0){
            p.openInventory(Kits.list_kits(p));
            return true;
        }

        String skit = player.selected_kit.get(p.getUniqueId());
        skit = skit.toLowerCase();
        if (!skit.equals("nenhum")) {
            p.sendMessage(serverinfo.name() + " §cVocê já está com kit!");
            return true;
        }

        String kit = args[0].toLowerCase();

        switch(args[0]){
            case "pvp":
                PvP.Receive(p);
                break;
            case "kangaroo":
                Kangaroo.Receive(p);
                break;
            case "archer":
                Archer.Receive(p);
                break;
            case "ninja":
                Ninja.Receive(p);
                break;
            case "boxer":
                Boxer.Receive(p);
                break;
            case "stomper":
                Stomper.Receive(p);
                break;
            case "phantom":
                Phantom.Receive(p);
                break;
            default:
                p.sendMessage("§cKit inexistente!");
                return true;
        }
        p.sendMessage("§aVocê pegou kit " + kit);


        return true;
    }


}