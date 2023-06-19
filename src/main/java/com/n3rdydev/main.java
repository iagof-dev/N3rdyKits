package com.n3rdydev;

import com.n3rdydev.commands.*;
import com.n3rdydev.events.*;
import com.n3rdydev.settings.config;
import com.n3rdydev.settings.statistics;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;
import com.n3rdydev.SQL.MySql;

import java.sql.Connection;

public class main extends JavaPlugin implements Listener {

    private static main plugin;

    public static main getPlugin() {
        return plugin;
    }

    @Override
    public void onEnable(){
        plugin = this;

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

        //Event Listeners
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
        this.getServer().getPluginManager().registerEvents(this, this);

        getCommand("build").setExecutor(new Build());
        getCommand("kit").setExecutor(new Kit());
        getCommand("kits").setExecutor(new Kits());
        getCommand("admin").setExecutor(new Admin());
        getCommand("n3rdykits").setExecutor(new N3rdyKits());
        getCommand("definir").setExecutor(new Definir());
        getCommand("gm").setExecutor(new GameMode());
        getCommand("score").setExecutor(new Score());

        com.n3rdydev.settings.spawn.load();

        Bukkit.getServer().getMotd();
        Bukkit.getConsoleSender().sendMessage("§a[N3rdyKits] | Plugin Habilitado!");
    }

    @Override
    public void onDisable(){
        config.save();
        statistics.save();
        Bukkit.getConsoleSender().sendMessage("§c[N3rdyKits] | Plugin Desabilitado!");

    }

    @Override
    public void onLoad(){
        Bukkit.getConsoleSender().sendMessage("§e[N3rdyKits] | Plugin carregando...");

        statistics.setup();
        statistics.save();

        config.setup();
        config.get().addDefault("server.name", "§8[§l§2N3rdyKits§8]");
        config.get().addDefault("server.ip", "§7localhost");
        config.get().addDefault("server.motd.1.line1", "                §2§l§nN3rdyKits§r §c[1.8.x]");
        config.get().addDefault("server.motd.1.line2", "                §eAdquira VIP: &§loja.servidor.com");

        config.get().addDefault("database.mysql.enable", false);
        config.get().addDefault("database.mysql.address", "localhost");
        config.get().addDefault("database.mysql.port", 3306);
        config.get().addDefault("database.mysql.user", "root");
        config.get().addDefault("database.mysql.password", "132490Kj@br=");
        config.get().addDefault("database.mysql.database", "N3rdyKits");
        config.get().addDefault("database.mysql.table", "estatistica");

        config.get().addDefault("spawn.point", "0 0 0");
        config.get().addDefault("spawn.protection.pos1", "0 0");
        config.get().addDefault("spawn.protection.pos2", "0 0");


        config.get().addDefault("arenas.arena0", "0 0 0");
        config.get().addDefault("arenas.arena1", "0 0 0");
        config.get().addDefault("arenas.arena2", "0 0 0");
        config.get().addDefault("arenas.arena3", "0 0 0");
        config.get().addDefault("arenas.arena4", "0 0 0");
        config.get().addDefault("arenas.arena5", "0 0 0");
        config.get().addDefault("arenas.arena6", "0 0 0");
        config.get().addDefault("arenas.arena7", "0 0 0");
        config.get().addDefault("arenas.arena8", "0 0 0");
        config.get().addDefault("arenas.arena9", "0 0 0");

        config.get().options().copyDefaults(true);
        config.get().options().copyHeader(true);
        config.save();

        if(config.get().getBoolean("database.mysql.enable") != false){
            Connection DB = MySql.start();
        }
    }



}
