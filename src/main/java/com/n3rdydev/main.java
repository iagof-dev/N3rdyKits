package com.n3rdydev;

import com.n3rdydev.commands.*;
import com.n3rdydev.entity.player;
import com.n3rdydev.events.*;
import com.n3rdydev.manager.PlayerManager;
import com.n3rdydev.settings.config;
import com.n3rdydev.settings.statistics;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import com.n3rdydev.SQL.MySql;

import java.sql.Connection;
import java.util.UUID;

public class main extends JavaPlugin {

    private static main plugin;
    private PlayerManager manager;

    public static main getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable(){
        plugin = this;
         //Registrando Eventos
        this.getServer().getPluginManager().registerEvents(new handleWarpParkour(), this);
        this.getServer().getPluginManager().registerEvents(new handleConfig(), this);
        this.getServer().getPluginManager().registerEvents(new handleWarpSelector(), this);
        this.getServer().getPluginManager().registerEvents(new handleDamage(), this);
        this.getServer().getPluginManager().registerEvents(new handleMotd(), this);
        this.getServer().getPluginManager().registerEvents(new handleSoup(), this);
        this.getServer().getPluginManager().registerEvents(new handleDrop(), this);
        this.getServer().getPluginManager().registerEvents(new handleKitSelector(), this);
        this.getServer().getPluginManager().registerEvents(new handlePvP(), this);
        this.getServer().getPluginManager().registerEvents(new handleInteract(), this);
        this.getServer().getPluginManager().registerEvents(new protections(), this);
        this.getServer().getPluginManager().registerEvents(new handleRespawn() , this);
        this.getServer().getPluginManager().registerEvents(new handleSpawn(), this);
        this.getServer().getPluginManager().registerEvents(new handleMove(), this);
        this.getServer().getPluginManager().registerEvents(new handleChat(), this);
        this.getServer().getPluginManager().registerEvents(new handleFallDamage(), this);
        this.getServer().getPluginManager().registerEvents(new handleTeleport(), this);
        this.getServer().getPluginManager().registerEvents(new handlePlayerQuit(), this);
        this.getServer().getPluginManager().registerEvents(new handlePlaceBlocks(), this);
        this.getServer().getPluginManager().registerEvents(new handleSoupSign(), this);

        //  ===============================================
        //  | NÃO ESQUECER DE REGISTRAR NO PLUGIN.YML     |
        //  | ANIMAL.                                     |
        //  | De: N3rdy                                   |
        //  | Para: N3rdy                                 |
        //  ===============================================

        getCommand("build").setExecutor(new Build());
        getCommand("kits").setExecutor(new Kits());
        getCommand("admin").setExecutor(new Admin());
        getCommand("n3rdykits").setExecutor(new N3rdyKits());
        getCommand("gm").setExecutor(new GameMode());
        getCommand("score").setExecutor(new Score());
        getCommand("chat").setExecutor(new ClearChat());
        getCommand("warps").setExecutor(new Warps());
        getCommand("suicidar").setExecutor(new Suicidar());
        getCommand("spawn").setExecutor(new Spawn());
        getCommand("tpall").setExecutor(new Tpall());
        getCommand("tphere").setExecutor(new Tphere());
        getCommand("anunciar").setExecutor(new Anunciar());


        com.n3rdydev.settings.spawn.load();
        Bukkit.getServer().getMotd();
        com.n3rdydev.entity.server.feast_clear();
        com.n3rdydev.entity.server.loop_events();

        Bukkit.getConsoleSender().sendMessage("\n" +
                "  _  _   ____  ___   ___   __   __  _  __  ___   _____   ___  \n" +
                " | \\| | |__ / | _ \\ |   \\  \\ \\ / / | |/ / |_ _| |_   _| / __| \n" +
                " | .` |  |_ \\ |   / | |) |  \\ V /  | ' <   | |    | |   \\__ \\ \n" +
                " |_|\\_| |___/ |_|_\\ |___/    |_|   |_|\\_\\ |___|   |_|   |___/ \n" +
                "                                                             \n");
        Bukkit.getConsoleSender().sendMessage("§a[N3rdyKits] | Plugin Habilitado!");
    }

    @Override
    public void onDisable(){

        
        config.save();
        statistics.save();
        new MySql();
        Bukkit.getConsoleSender().sendMessage("§c[N3rdyKits] | Plugin Desabilitado!");
    }

    @Override
    public void onLoad(){
        Bukkit.getConsoleSender().sendMessage("§e[N3rdyKits] | Plugin carregando...");
        statistics.setup();
        statistics.save();
        config.start();
        if(config.get().getBoolean("database.mysql.enable") != false){
            MySql mysql = new MySql();
        }
    }

}
