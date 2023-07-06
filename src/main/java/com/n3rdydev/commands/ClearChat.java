package com.n3rdydev.commands;

import com.n3rdydev.entity.chat;
import com.n3rdydev.settings.serverinfo;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] strings) {
        if(!(commandSender instanceof Player)) {
            System.out.println("§cApenas jogadores pode usar este comando!");
            return true;
        }

        Player p = (Player) commandSender;

        if (!p.hasPermission("n3rdydev.command.clearchat")) {
            p.sendMessage("§cSem permissão!");
            return true;
        }
        if (strings.length < 1) {
            p.sendMessage("§cErro! Digite o argumento:\n /chat [ativar/desativar/limpar]");
            return true;
        }

        switch (strings[0]) {
            case "desativar":
                chat.active_chat = false;
                p.sendMessage(serverinfo.name() + " | §aChat Desativado!");
                return true;
            case "ativar":
                chat.active_chat = true;
                p.sendMessage(serverinfo.name() + " | §aChat Ativado!");
                return true;
            case "limpar":
                for (Player allp : Bukkit.getOnlinePlayers()) {
                    for (int i = 0; i <= 255; i++) {

                        allp.sendMessage(" ");
                    }
                    allp.sendMessage("            " + serverinfo.name() + "      ");
                    allp.sendMessage(">     §eChat limpo por um Staff!§r   <");
                }
                return true;
        }


        return true;
    }
}
