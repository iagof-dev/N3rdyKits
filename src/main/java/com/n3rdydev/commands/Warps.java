package com.n3rdydev.commands;

import com.n3rdydev.entity.player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Warps implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)) {
            System.out.println("§cApenas jogadores pode usar este comando!");
            return false;
        }

        Player p = (Player) commandSender;

        if(player.selected_kit.get(p.getUniqueId()) != null && player.selected_kit.get(p.getUniqueId()) != "nenhum"){
            p.sendMessage("§cErro! Você está com kit!");
            return false;
        }

        p.openInventory(com.n3rdydev.gui.Warps.open());
        return true;
    }
}
