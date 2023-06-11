package com.n3rdydev.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.SignChangeEvent;

public class handleSoupSign implements Listener {

    @EventHandler
    public void onSignChange(SignChangeEvent e){
        Player p = e.getPlayer();
        if(e.getLine(0).contains("sopa")){
            e.setLine(0, com.n3rdydev.settings.serverinfo.name());
            e.setLine(1,"Refil de sopa");
            p.sendMessage(com.n3rdydev.settings.serverinfo.name()+" Placa adicionada!");
        }
        if(e.getLine(0).contains("recraft")){
            e.setLine(0, com.n3rdydev.settings.serverinfo.name());
            e.setLine(1,"Refil de recraft");
            p.sendMessage(com.n3rdydev.settings.serverinfo.name()+" Placa adicionada!");
        }
    }
}
