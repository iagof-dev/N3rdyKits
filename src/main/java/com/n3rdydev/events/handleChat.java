package com.n3rdydev.events;

import com.n3rdydev.entity.chat;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;

public class handleChat implements Listener {

    @EventHandler
    public void onPlayerChat(AsyncPlayerChatEvent e){
        Player p = e.getPlayer();

        // Checando logo de primeira se o chat está ativado
        if (chat.active_chat != true){
            e.setCancelled(true);
            p.sendMessage("§cChat está desativado!");
            return;     
        }

        if (e.isCancelled()) {
            /* Caso algum outro plugin cancele a mensagem, não continuará
            (Plugin de punições e afins)  */
            return;
        }

        String cargo = "§7";
        
        if (p.hasPermission("n3rdydev.cargo.developer")) {
            cargo = "§6[DEV]§f ";
        } else if (p.hasPermission("n3rdydev.cargo.admin")) {
            cargo = "§c[ADMIN]§f ";
        } else if (p.hasPermission("n3rdydev.cargo.vip")) {
            cargo = "§d[VIP]§f ";
        }

        // Utilizando cores & caso haja permissão de VIP
        String message = p.hasPermission("n3rdydev.chat.cores") ? e.getMessage().replace("&", "§") : e.getMessage();

        /* Compatibilidade com qualquer plugin que defina displayName (RECOMENDADO)
            Bukkit.broadcastMessage(p.getDisplayName() + ": " + e.getMessage());
        */

        // Caso não aplique a compatibilidade:
       /* Bukkit.broadcastMessage(cargo + p.getName() + ": " + message);*/
        e.setFormat(cargo + p.getName() + ": " + message);

    }

}
