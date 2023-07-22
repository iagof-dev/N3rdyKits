package com.n3rdydev.commands;

import com.n3rdydev.entity.player;
import com.n3rdydev.manager.PlayerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Score implements CommandExecutor {
    private static PlayerManager manager;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            System.out.println("§cApenas jogadores pode usar este comando!");
            return true;
        }


        Player p = (Player) commandSender;
        UUID puid = p.getUniqueId();

        if (manager.jogador.get(puid).getScoreboard() != false) {
            player.updateScoreboard(p);
            p.sendMessage("§cVocê desativou a scoreboard!");

        } else {
            player.updateScoreboard(p);
            p.sendMessage("§aVocê ativou a scoreboard!");
        }
        return true;
    }
}
