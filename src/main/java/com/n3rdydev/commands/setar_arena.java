package com.n3rdydev.commands;

import com.n3rdydev.config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setar_arena implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        boolean permission = false;


        if(p.hasPermission("n3rdydev.command.arenaset") || p.hasPermission("n3rdydev.*")){
            permission = true;
        }

        if(!permission){
            p.sendMessage("§cSem permissão!");
            return true;
        }

        if(strings.length == 0){
            p.sendMessage("§a[N3rdyKits] Você precisa escolher qual arena deseja setar\n -kit (0-9)\n-warp");
        }

        if(strings.length == 1){
            double player_x = p.getLocation().getX();
            double player_y = p.getLocation().getY();
            double player_z = p.getLocation().getZ();
            String coords = player_x + " " + player_y + " " + player_z;
            config.get().set("arenas.arena" + strings[0], coords);
            config.save();
            config.reload();
            com.n3rdydev.settings.spawn.load();

            p.sendMessage("§aSucesso! você definiu arena " + strings[0] + "! (" + coords +")");


        }







        return false;
    }
}
