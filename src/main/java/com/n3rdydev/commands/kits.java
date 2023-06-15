package com.n3rdydev.commands;

import com.n3rdydev.entity.player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;

public class kits implements CommandExecutor, Listener {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        String kit = player.selected_kit.get(p.getUniqueId());
        kit = kit.toLowerCase();
        if(kit != "nenhum"){
            p.sendMessage("§cVocê ja está com kit!");
            return true;
        }
        p.openInventory(com.n3rdydev.gui.kits.list_kits(p));

        return true;
    }


}