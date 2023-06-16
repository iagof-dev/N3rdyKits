package com.n3rdydev.commands;

import com.n3rdydev.entity.player;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class score implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;

        if(com.n3rdydev.entity.player.is_scoreboard_enabled(p)){
            player.scoreboard.put(p.getUniqueId(), false);
            p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());
            p.sendMessage("§cVocê desativou a scoreboard!");
        }
        else{
            player.scoreboard.put(p.getUniqueId(), true);
            p.sendMessage("§aVocê ativou a scoreboard!");
        }

        return true;
    }
}
