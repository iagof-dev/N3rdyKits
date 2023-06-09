package com.n3rdydev.commands;

import com.n3rdydev.config;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.metadata.MetadataValue;

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
                case "estacomkit":
                    List<MetadataValue> valor = p.getMetadata("kit");
                    p.sendMessage("valor: " + valor);
                    break;
                case "setarkit":
                    p.setMetadata("kit", new FixedMetadataValue(com.n3rdydev.main.getPlugin(),"1"));
                    break;

            }

        }
        else{
            p.sendMessage("§cSem permissão");
        }

        return true;
    }
}
