package com.n3rdydev.commands;

import com.n3rdydev.config;
import com.n3rdydev.entity.player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;
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
                    LocalTime horario_atual = java.time.LocalTime.now();
                    LocalTime proximo_uso = horario_atual.plusSeconds(6);
                    player.cooldown_ninja.put(p.getUniqueId(), proximo_uso);
                    break;
                case "possousar":
                    LocalTime horario_atual1 = java.time.LocalTime.now();
                    LocalTime delay_player = player.cooldown_ninja.get(p.getUniqueId());
                    if(horario_atual1.isAfter(delay_player)){
                        p.sendMessage("§aVocê pode utilizar novamente!!");
                    }
                    else{
                        Duration tempo_restante = Duration.between(horario_atual1, delay_player);
                        long segs = tempo_restante.getSeconds();
                        p.sendMessage("§cVocê só podera utilizar daqui " + segs + " segundos.");
                    }
                    break;

            }

        }
        else{
            p.sendMessage("§cSem permissão");
        }

        return true;
    }
}
