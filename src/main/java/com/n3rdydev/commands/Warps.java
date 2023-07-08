package com.n3rdydev.commands;

import com.n3rdydev.entity.player;
import com.n3rdydev.settings.serverinfo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Warps implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)) {
            System.out.println("§cApenas jogadores pode usar este comando!");
            return true;
        }

        Player p = (Player) commandSender;
        UUID puid = p.getUniqueId();
        String skit = player.selected_kit.get(puid);
        skit = skit.toLowerCase();
        if (!skit.equals("nenhum")) {
            p.sendMessage("§cErro! Você está com kit!");
            return true;
        }


        if (!(command.getName().equalsIgnoreCase(s))) {
            commandSender.sendMessage("vc mandou comando com alias");
        }

        p.openInventory(com.n3rdydev.gui.Warps.open());
        return true;
    }
}
