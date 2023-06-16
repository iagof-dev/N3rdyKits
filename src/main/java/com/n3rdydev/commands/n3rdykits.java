package com.n3rdydev.commands;

import com.n3rdydev.settings.config;
import com.n3rdydev.entity.player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Random;

public class n3rdykits implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        Player p = (Player) commandSender;

        if (p.hasPermission("n3rdydev.developer.test") || p.hasPermission("n3rdydev.*")) {

            switch(strings[0]){
                case "reload":
                    config.save();
                    config.reload();
                    com.n3rdydev.settings.spawn.load();
                    p.sendMessage(com.n3rdydev.settings.serverinfo.name() + " DEV | Recarregado");
                    break;
                case "rndnumber":
                    Random rnd = new Random();
                    int rndNumber = rnd.nextInt(0,10);
                    p.sendMessage("§aNumero gerado: " + rndNumber);
                    break;
                case "definircl":
                    player.setCooldown(p, 6);
                    break;
                case "verificarcl":
                    Boolean cooldown = com.n3rdydev.entity.player.getCooldown(p);
                    if(cooldown != false){
                        p.sendMessage(player.getCooldownTime(p));
                    }
                    break;
                case "addkills":
                    com.n3rdydev.entity.player.addKills(p);
                    com.n3rdydev.scoreboard.sb_default.Set(p);
                    p.sendMessage("§5Kill adicionada!");
                    break;
                case "addmortes":
                    com.n3rdydev.entity.player.addDeaths(p);
                    com.n3rdydev.scoreboard.sb_default.Set(p);
                    p.sendMessage("§5morte adicionada!");
                    break;
                case "mostrarkit":
                    String kit = player.selected_kit.get(p.getUniqueId());
                    kit = kit.toLowerCase();
                    p.sendMessage("§5Você está com o kit: "+ kit);
                    break;
                case"score":
                    p.sendMessage("Status da score: " + player.scoreboard.get(p.getUniqueId()));
                    break;

            }

        }
        else{
            p.sendMessage("§cSem permissão");
        }

        return true;
    }
}
