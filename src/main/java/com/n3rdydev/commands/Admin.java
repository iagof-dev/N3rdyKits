package com.n3rdydev.commands;

import com.n3rdydev.entity.player;
import com.n3rdydev.kits.Spawn;
import com.n3rdydev.manager.PlayerManager;
import com.n3rdydev.settings.serverinfo;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Collection;
import java.util.HashMap;
import java.util.UUID;

public class Admin implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)) {
            System.out.println("§cApenas jogadores pode usar este comando!");
            return true;
        }

        Player p = (Player) commandSender;

        if (!(p.hasPermission("n3rdydev.command.admin") || p.hasPermission("n3rdydev.*"))) {
            p.sendMessage("§cSem permissão!");
            return true;
        }
        player.toggleInvis(p);

        return true;
    }

}
