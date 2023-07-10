package com.n3rdydev;

import com.n3rdydev.commands.*;
import com.n3rdydev.events.*;
import com.n3rdydev.settings.config;
import com.n3rdydev.settings.statistics;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import com.n3rdydev.SQL.MySql;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.Connection;

public class main extends JavaPlugin {

    private static main plugin;

    public static main getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable(){
        plugin = this;
        com.n3rdydev.entity.server.feast_clear();

        com.n3rdydev.entity.server.loop_events();

         //Registrando Eventos
        Listener drop = new handleDrop();
        Listener inventory = new handleKitSelector();
        Listener pvp = new handlePvP();
        Listener interact = new handleInteract();
        Listener protection = new protections();
        Listener launchpad = new handleMove();
        Listener fall_damage = new handleFallDamage();
        Listener respawn = new handleRespawn();
        Listener spawn = new handleSpawn();
        Listener sopa = new handleSoup();
        Listener chat = new handleChat();
        Listener blocks = new handlePlaceBlocks();
        Listener placa_sopa = new handleSoupSign();
        Listener player_shift = new handleTeleport();
        Listener player_quit = new handlePlayerQuit();
        Listener handleMotd = new handleMotd();
        Listener handleWarpSelector = new handleWarpSelector();
        Listener handleConfig = new handleConfig();

        //Event Listeners
        this.getServer().getPluginManager().registerEvents(handleConfig, this);
        this.getServer().getPluginManager().registerEvents(handleWarpSelector, this);
        this.getServer().getPluginManager().registerEvents(blocks, this);
        this.getServer().getPluginManager().registerEvents(placa_sopa, this);
        this.getServer().getPluginManager().registerEvents( sopa, this);
        this.getServer().getPluginManager().registerEvents(drop, this);
        this.getServer().getPluginManager().registerEvents(inventory, this);
        this.getServer().getPluginManager().registerEvents(pvp, this);
        this.getServer().getPluginManager().registerEvents(interact, this);
        this.getServer().getPluginManager().registerEvents(protection, this);
        this.getServer().getPluginManager().registerEvents(respawn , this);
        this.getServer().getPluginManager().registerEvents(spawn, this);
        this.getServer().getPluginManager().registerEvents(launchpad, this);
        this.getServer().getPluginManager().registerEvents(chat, this);
        this.getServer().getPluginManager().registerEvents(fall_damage, this);
        this.getServer().getPluginManager().registerEvents(player_shift, this);
        this.getServer().getPluginManager().registerEvents(player_quit, this);
        this.getServer().getPluginManager().registerEvents(handleMotd, this);

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

        com.n3rdydev.settings.spawn.load();
        Bukkit.getServer().getMotd();
        Bukkit.getConsoleSender().sendMessage("§a[N3rdyKits] | Plugin Habilitado!");
    }

    @Override
    public void onDisable(){
        config.save();
        statistics.save();
        MySql.SaveAll();
        Bukkit.getConsoleSender().sendMessage("§c[N3rdyKits] | Plugin Desabilitado!");
    }

    @Override
    public void onLoad(){
        Bukkit.getConsoleSender().sendMessage("§e[N3rdyKits] | Plugin carregando...");
        statistics.setup();
        statistics.save();
        config.start();
        if(config.get().getBoolean("database.mysql.enable") != false){
            Connection DB = MySql.Start();
        }
    }

}
