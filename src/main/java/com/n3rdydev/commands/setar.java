package com.n3rdydev.commands;

import com.n3rdydev.config;
import com.sk89q.worldedit.extension.platform.Actor;
import com.sk89q.worldedit.internal.annotation.Selection;
import com.sk89q.worldedit.regions.RegionSelector;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import com.sk89q.worldedit.*;

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
            p.sendMessage("[N3rdyKits] §cErro! Sintaxe incorreta\n§eExemplo:\n§e/setar [arena/spawn/protecao] <valor>\n§7(Observação: o valor do proteção é apenas x e y de 2 posições)");
        }

        if (strings.length > 1 && strings.length >= 2) {

            switch (strings[0]) {
                case "protecao":
                    String pos1_x = strings[1];
                    String pos1_z = strings[2];
                    String pos2_x = strings[3];
                    String pos2_z = strings[4];

                    String pos1 = pos1_x + " " + pos1_z;
                    String pos2 = pos2_x + " " + pos2_z;

                    config.get().set("spawn.protection.pos1", pos1);
                    config.get().set("spawn.protection.pos2", pos2);
                    config.save();
                    config.reload();
                    p.sendMessage("[N3rdyKits] §aProteção definida entre (" + pos1 + ") e (" + pos2 +")");
                    return true;
                case "spawn":
                    double spawn_x = p.getLocation().getX();
                    double spawn_y = p.getLocation().getY();
                    double spawn_z = p.getLocation().getZ();
                    String spawn_format = (spawn_x + " " + spawn_y + " " + spawn_z);
                    config.get().set("spawn.point", spawn_format);
                    config.save();
                    config.reload();
                    com.n3rdydev.settings.spawn.load();
                    p.sendMessage("[N3rdyKits] §aVocê definiu o novo spawn! (" + spawn_format + ")");
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

                        p.sendMessage("[N3rdyKits] §aSucesso! você definiu arena " + strings[0] + "! (" + coords + ")");
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
