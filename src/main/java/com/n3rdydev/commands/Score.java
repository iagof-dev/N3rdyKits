package com.n3rdydev.commands;

import com.n3rdydev.entity.player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Score implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;
        UUID puid = p.getUniqueId();

        if (player.scoreboard.get(puid) != false) {
            player.updateScoreboard(p);
            p.sendMessage("§cVocê desativou a scoreboard!");

        } else {
            player.updateScoreboard(p);
            p.sendMessage("§aVocê ativou a scoreboard!");
        }
        return true;
    }
}
