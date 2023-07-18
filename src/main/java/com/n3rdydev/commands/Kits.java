package com.n3rdydev.commands;

import com.n3rdydev.entity.player;
import com.n3rdydev.kits.*;
import com.n3rdydev.manager.PlayerManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import java.util.UUID;

import static com.n3rdydev.gui.Kits.*;

public class Kits implements CommandExecutor, Listener {

    private static PlayerManager manager;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if(!(commandSender instanceof Player)) {
            System.out.println("§cApenas jogadores pode usar este comando!");
            return true;
        }
        Player p = (Player) commandSender;
        UUID puid = p.getUniqueId();

        String kit = manager.getPlayers().get(puid).getKit().toLowerCase();
        if(kit != null || kit != "nenhum"){
            p.sendMessage("§cVocê ja está com kit!");
            return true;
        }

        if (!(command.getName().equalsIgnoreCase(label))) {
            String sel_kit = args[0].toLowerCase();
            switch (sel_kit) {
                case "pvp":
                    PvP.Receive(p);
                    break;
                case "kangaroo":
                    Kangaroo.Receive(p);
                    break;
                case "archer":
                    Archer.Receive(p);
                    break;
                case "ninja":
                    Ninja.Receive(p);
                    break;
                case "boxer":
                    Boxer.Receive(p);
                    break;
                case "stomper":
                    Stomper.Receive(p);
                    break;
                case "phantom":
                    Phantom.Receive(p);
                    break;
                case "gladiator":
                    Gladiator.Receive(p);
                    break;
                default:
                    p.sendMessage("§cKit inexistente!");
                    return true;
            }
            p.sendMessage("§aVocê pegou kit " + kit);
            return true;
        }

        p.openInventory(list_kits(p));

        return true;
    }


}