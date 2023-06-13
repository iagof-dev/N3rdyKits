package com.n3rdydev.settings;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class config {

    public static File file;
    public static FileConfiguration config;

    public static void setup(){
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("N3rdyKits").getDataFolder(), "config.yml");

        if(!file.exists()){
            try{
                file.createNewFile();
                Bukkit.getConsoleSender().sendMessage("§a[N3rdyKits] Arquivo de config.yml foi criado!");
            }
            catch (Exception e){
                Bukkit.getConsoleSender().sendMessage("§c[N3rdyKits] Configuracao erro!\n"+e.getMessage());
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get(){
        return config;
    }

    public static void save(){
        Bukkit.getConsoleSender().sendMessage("[N3rdyKits] Salvando configurações...");
        try{
            config.save(file);
            Bukkit.getConsoleSender().sendMessage("§a[N3rdyKits] Configurações Salvas!");
        }
        catch (Exception e){
            Bukkit.getConsoleSender().sendMessage("§c[N3rdyKits] Configurações não foi salva... erro:\n"+ e.getMessage());
        }
    }

    public static void reload(){
        config = YamlConfiguration.loadConfiguration(file);
        Bukkit.getConsoleSender().sendMessage("§a[N3rdyKits] Configurações foi atualizada!");

    }

}
