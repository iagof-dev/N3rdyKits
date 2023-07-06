package com.n3rdydev.commands;

import com.n3rdydev.entity.player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

import static com.n3rdydev.gui.Kits.*;

public class Kits implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {

        if(!(commandSender instanceof Player)) {
            System.out.println("§cApenas jogadores pode usar este comando!");
            return true;
        }


        Player p = (Player) commandSender;

        String kit = player.selected_kit.get(p.getUniqueId());
        kit = kit.toLowerCase();
        if(kit != null || kit != "nenhum"){
            p.sendMessage("§cVocê ja está com kit!");
            return true;
        }
        p.openInventory(list_kits(p));

        return true;
    }


}