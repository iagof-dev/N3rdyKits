package com.n3rdydev.commands;

import com.n3rdydev.config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setar implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        boolean permission = false;


        if (p.hasPermission("n3rdydev.command.arenaset") || p.hasPermission("n3rdydev.*")) {
            permission = true;
        }

        if (!permission) {
            p.sendMessage("§cSem permissão!");
            return true;
        }

        if (strings.length == 0) {
            p.sendMessage("§a[N3rdyKits] Você precisa escolher qual arena deseja setar\n/setar arena (0-9)\nsetar warp (fps)\n/setar spawn");
        }

        if (strings.length > 1 && strings.length == 2) {

            switch (strings[0]) {
                case "spawn":
                    double spawn_x = p.getLocation().getX();
                    double spawn_y = p.getLocation().getY();
                    double spawn_z = p.getLocation().getZ();
                    String spawn_format = (spawn_x + " " + spawn_y + " " + spawn_z);
                    config.get().set("spawn.point", spawn_format);
                    config.save();
                    config.reload();
                    com.n3rdydev.settings.spawn.load();
                    p.sendMessage("§aVocê definiu o novo spawn! (" + spawn_format + ")");
                    return true;
                case "arena":
                    if (strings[1].equals("0") || strings[1].equals("1") || strings[1].equals("2") || strings[1].equals("3") || strings[1].equals("4") || strings[1].equals("5") || strings[1].equals("6") || strings[1].equals("7") || strings[1].equals("8")) {

                        double player_x = p.getLocation().getX();
                        double player_y = p.getLocation().getY();
                        double player_z = p.getLocation().getZ();
                        String coords = player_x + " " + player_y + " " + player_z;
                        config.get().set("arenas.arena" + strings[1], coords);
                        config.save();
                        config.reload();
                        com.n3rdydev.settings.spawn.load();

                        p.sendMessage("§aSucesso! você definiu arena " + strings[0] + "! (" + coords + ")");
                    }
                    else{
                        p.sendMessage("§c[N3rdyKits] você precisa escolher a arena (0-9)!");
                    }

                    return true;
                case "warp":
                    break;
                default:
                    p.sendMessage("§a[N3rdyKits] Você precisa escolher qual arena deseja setar\n -arena (0-9)\n-warp");
                    break;
            }


        }


        return false;
    }
}
