package com.n3rdydev.commands;

import com.n3rdydev.entity.player;
import com.n3rdydev.kits.FPS;
import com.n3rdydev.kits.LavaChallenge;
import com.n3rdydev.manager.PlayerManager;
import com.n3rdydev.settings.config;
import com.n3rdydev.settings.serverinfo;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.UUID;

import static com.n3rdydev.entity.player.convert_config_location;

public class Warps implements CommandExecutor {

    private static PlayerManager manager;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)) {
            System.out.println("§cApenas jogadores pode usar este comando!");
            return true;
        }

        Player p = (Player) commandSender;
        UUID puid = p.getUniqueId();
        String skit = manager.jogador.get(puid).Kit.toLowerCase();
        if (!skit.equals("nenhum")) {
            p.sendMessage("§cErro! Você está com kit!");
            return true;
        }
        if (!(command.getName().equalsIgnoreCase(s))) {
            String warp = strings[0].toLowerCase();
            Location tp;
            switch (warp){
                case "fps":
                    if(config.get().getBoolean("warps.fps.active") != false){
                        tp = convert_config_location("warps.fps.spawnpos");
                        p.teleport(tp);
                        FPS.Receive(p);
                        return true;
                    }
                    p.sendMessage(serverinfo.name() + " | §cWarp em manutenção!");
                    break;
                case "lava":
                    if(config.get().getBoolean("warps.lavachallenge.active") != false){
                        tp = convert_config_location("warps.lavachallenge.spawnpos");
                        p.teleport(tp);
                        LavaChallenge.Receive(p);
                        return true;
                    }
                    p.sendMessage(serverinfo.name() + " | §cWarp em manutenção!");
                    break;
                default:
                    p.openInventory(com.n3rdydev.gui.Warps.open());
            }
            return true;
        }

        p.openInventory(com.n3rdydev.gui.Warps.open());
        return true;
    }
}
