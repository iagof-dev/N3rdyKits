package com.n3rdydev.commands;

import com.n3rdydev.SQL.MySql;
import com.n3rdydev.entity.server;
import com.n3rdydev.scoreboard.sb_default;
import com.n3rdydev.settings.config;
import com.n3rdydev.entity.player;
import com.n3rdydev.settings.serverinfo;
import com.n3rdydev.settings.spawn;
import com.n3rdydev.settings.statistics;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.UUID;

public class N3rdyKits implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {

        if(!(commandSender instanceof Player)) {
            System.out.println("§cApenas jogadores pode usar este comando!");
            return true;
        }


        Player p = (Player) commandSender;
        if (!(p.hasPermission("n3rdydev.developer.test") || p.hasPermission("n3rdydev.*"))) {
            p.sendMessage("§cSem permissão");
            return true;
        }

        if (strings.length == 0) {
            p.openInventory(com.n3rdydev.gui.Config.start(p));
            return true;
        }

        /*
                Abaixo é só teste
         */


        String mensagem = serverinfo.name() + " | ";
        UUID p_uid = p.getUniqueId();

        switch (strings[0]) {
            case "reload":
                config.reload();
                statistics.reload();
                spawn.load();
                mensagem += ("§5Recarregado");
                break;
            case "addkills":
                player.addKills(p.getUniqueId());
                sb_default.Set(p);
                mensagem += ("§5Kill adicionada!");
                break;
            case "addmortes":
                player.addDeaths(p_uid);
                sb_default.Set(p);
                mensagem += ("§5morte adicionada!");
                break;
            case "addxp":
                player.addXP(p_uid);
                mensagem += ("§5xp adicionado!");
                break;
            case "kit":
                String kit = player.selected_kit.get(p.getUniqueId());
                kit = kit.toLowerCase();
                mensagem += ("§5Você está com o kit: " + kit);
                break;
            case "save":
                statistics.get().set(p_uid + ".kills", player.getKills(p.getUniqueId()));
                statistics.get().set(p_uid + ".deaths", player.getDeaths(p.getUniqueId()));
                statistics.get().set(p_uid + ".xp", player.score.get(p.getUniqueId()));
                statistics.save();
                break;
            case "mysql":
                MySql mysql = new MySql();
                break;
            case "feast":
                mensagem+="§eFeast Gerado!";
                server.feast_generate();
                break;
            case "ritem":
                mensagem += server.random_item();
                break;
            case "cfeast":
                server.feast_clear();
                mensagem+="§eFeast Limpo!";
                break;
            case "tfeast":
                mensagem += "§rFeast Timer: §c" + config.get().getInt("server.feast.timer") + " minutos.";
                break;
            case "arena":
                if (strings[1].equals("0") || strings[1].equals("1") || strings[1].equals("2") || strings[1].equals("3") || strings[1].equals("4") || strings[1].equals("5") || strings[1].equals("6") || strings[1].equals("7") || strings[1].equals("8") || strings[1].equals("9")) {
                    double player_x = p.getLocation().getX();
                    double player_y = p.getLocation().getY();
                    double player_z = p.getLocation().getZ();
                    String coords = player_x + " " + player_y + " " + player_z;
                    config.get().set("arenas.arena" + strings[1], coords);
                    config.save();
                    config.reload();
                    com.n3rdydev.settings.spawn.load();
                    p.sendMessage(com.n3rdydev.settings.serverinfo.name() + " §aSucesso! você definiu arena " + strings[0] + "! (" + coords + ")");
                }
                else{
                    p.sendMessage(com.n3rdydev.settings.serverinfo.name() + " você precisa escolher a arena (0-9)!");
                }
                return true;
            case "cglad":
                //server.criarArenaGlad(p, null,17);
                break;
            case "rglad":
                //server.remArenaGlad(p, 17);
                break;
            default:
                mensagem+="§cErro, Argumentos inválidos/inexistente";
                break;
        }
        p.sendMessage(mensagem);
        return true;


    }
}
