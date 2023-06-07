package com.n3rdydev;

import com.n3rdydev.commands.setar;
import com.n3rdydev.commands.setarspawn;
import com.n3rdydev.events.listener;
import com.n3rdydev.events.soup;
import com.n3rdydev.events.spawn;
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
        config.get().addDefault("spawn.point", "0 0 0");
        config.get().addDefault("spawn.pos1", "0 0 0");
        config.get().addDefault("spawn.pos2", "0 0 0");

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


        Listener basic_events = new listener();
        Listener spawn = new spawn();
        Listener sopa = new soup();

        //Event Listeners
        this.getServer().getPluginManager().registerEvents( sopa, this);
        this.getServer().getPluginManager().registerEvents( basic_events, this);
        this.getServer().getPluginManager().registerEvents(spawn, this);
        this.getServer().getPluginManager().registerEvents(this, this);

        getCommand("setarspawn").setExecutor(new setarspawn());
        getCommand("build").setExecutor(new com.n3rdydev.commands.build());
        getCommand("kit").setExecutor(new com.n3rdydev.commands.kit());
        getCommand("kits").setExecutor(new com.n3rdydev.commands.kits());
        getCommand("admin").setExecutor(new com.n3rdydev.commands.admin());
        getCommand("n3rdykits").setExecutor(new com.n3rdydev.commands.n3rdykits());
        getCommand("setar").setExecutor(new setar());


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
