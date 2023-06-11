package com.n3rdydev;

import com.n3rdydev.commands.definir;
import com.n3rdydev.commands.gm;
import com.n3rdydev.commands.ping;
import com.n3rdydev.events.*;
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

        config.setup();
        config.get().addDefault("server.name", "§8[§l§2N3rdyKits§8]");
        config.get().addDefault("server.ip", "§7localhost");

        config.get().addDefault("spawn.point", "0 0 0");
        config.get().addDefault("spawn.pos1", "0 0 0");
        config.get().addDefault("spawn.pos2", "0 0 0");
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
        this.getServer().getPluginManager().registerEvents(this, this);

        getCommand("build").setExecutor(new com.n3rdydev.commands.build());
        getCommand("kit").setExecutor(new com.n3rdydev.commands.kit());
        getCommand("kits").setExecutor(new com.n3rdydev.commands.kits());
        getCommand("admin").setExecutor(new com.n3rdydev.commands.admin());
        getCommand("n3rdykits").setExecutor(new com.n3rdydev.commands.n3rdykits());
        getCommand("definir").setExecutor(new definir());
        getCommand("gm").setExecutor(new gm());
        getCommand("ping").setExecutor(new ping());

        com.n3rdydev.settings.spawn.load();

        Bukkit.getConsoleSender().sendMessage("§a[N3rdyKits] | Plugin Habilitado!");
    }

    @Override
    public void onDisable(){
        config.save();
        Bukkit.getConsoleSender().sendMessage("§c[N3rdyKits] | Plugin Desabilitado!");

    }

    @Override
    public void onLoad(){
        Connection DB = MySql.start();
        Bukkit.getConsoleSender().sendMessage("§e[N3rdyKits] | Plugin carregando...");
    }



}
