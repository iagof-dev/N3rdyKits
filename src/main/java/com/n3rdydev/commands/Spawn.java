package com.n3rdydev.commands;

import com.n3rdydev.entity.player;
import com.n3rdydev.manager.PlayerManager;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Spawn implements CommandExecutor {
    private static PlayerManager manager;
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            System.out.println("§cApenas jogadores pode usar este comando!");
            return false;
        }
        Player p = (Player) commandSender;
        PlayerInventory inv = p.getInventory();
        inv.setArmorContents(new ItemStack[inv.getArmorContents().length]);
        manager.jogador.get(p.getUniqueId()).setWarp(0);
        p.getInventory().clear();
        Location spawn_loc = new Location(p.getWorld(), com.n3rdydev.settings.spawn.spawn_x, com.n3rdydev.settings.spawn.spawn_y, com.n3rdydev.settings.spawn.spawn_z);
        p.teleport(spawn_loc);
        p.setHealth(20);
        com.n3rdydev.kits.Spawn.Receive(p);
        return true;
    }
}
