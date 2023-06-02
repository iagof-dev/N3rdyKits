package com.n3rdydev;

import com.n3rdydev.events.Listeners;
import com.n3rdydev.events.soup;
import com.n3rdydev.events.spawn;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
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

        Listeners basic_events = new Listeners();
        Listeners spawn = new spawn();
        Listeners sopa = new soup();


        //Event Listeners
        this.getServer().getPluginManager().registerEvents( sopa, this);
        this.getServer().getPluginManager().registerEvents( basic_events, this);
        this.getServer().getPluginManager().registerEvents(spawn, this);
        this.getServer().getPluginManager().registerEvents(this, this);


        //getCommand("spawn").setExecutor(new spawn());


        Bukkit.getConsoleSender().sendMessage("§a[N3rdyKits] | Plugin Habilitado!");
    }

    @Override
    public void onDisable(){
        Bukkit.getConsoleSender().sendMessage("§c[N3rdyKits] | Plugin Desabilitado!");

    }

    @Override
    public void onLoad(){
        Connection DB = MySql.start();
        Bukkit.getConsoleSender().sendMessage("§e[N3rdyKits] | Plugin carregando...");
    }



}
