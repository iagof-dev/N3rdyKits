package com.n3rdydev.settings;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class config {

    public static File file;
    public static FileConfiguration config;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("N3rdyKits").getDataFolder(), "config.yml");

        if (!file.exists()) {
            try {
                file.createNewFile();
                Bukkit.getConsoleSender().sendMessage("§a[N3rdyKits] Arquivo de config.yml foi criado!");
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage("§c[N3rdyKits] Configuracao erro!\n" + e.getMessage());
            }
        }
        config = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return config;
    }

    public static void save() {
        Bukkit.getConsoleSender().sendMessage("[N3rdyKits] Salvando configurações...");
        try {
            config.save(file);
            Bukkit.getConsoleSender().sendMessage("§a[N3rdyKits] Configurações Salvas!");
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage("§c[N3rdyKits] Configurações não foi salva... erro:\n" + e.getMessage());
        }
    }

    public static void reload() {
        config = YamlConfiguration.loadConfiguration(file);
        Bukkit.getConsoleSender().sendMessage("§a[N3rdyKits] Configurações foi atualizada!");

    }

    public static void start() {
        setup();

        //SERVER INFO
        get().addDefault("server.name", "§8[§l§2N3rdyKits§8]");
        get().addDefault("server.ip", "§7localhost");
        get().addDefault("server.motd.1.line1", "                §2§l§nN3rdyKits§r §c[1.8.x]");
        get().addDefault("server.motd.1.line2", "                §eAdquira VIP: &§loja.servidor.com");
        get().addDefault("server.feast.timer", 20);

        //DATABASE
        get().addDefault("database.mysql.enable", false);
        get().addDefault("database.mysql.address", "localhost");
        get().addDefault("database.mysql.port", 3306);
        get().addDefault("database.mysql.user", "root");
        get().addDefault("database.mysql.password", "132490Kj@br=");
        get().addDefault("database.mysql.database", "N3rdyKits");
        get().addDefault("database.mysql.table", "estatistica");



        //SPAWN
        get().addDefault("spawn.point", "0 0 0");
        get().addDefault("spawn.protection.pos1", "0 0");
        get().addDefault("spawn.protection.pos2", "0 0");

        //WARPS
        get().addDefault("warps.fps.active", false);
        get().addDefault("warps.fps.spawnpos", "0 0 0");
        get().addDefault("warps.fps.spawnprotection.pos1", "0 0 0");
        get().addDefault("warps.fps.spawnprotection.pos2", "0 0 0");


        //ARENA LIST
        get().addDefault("arenas.arena0", "0 0 0");
        get().addDefault("arenas.arena1", "0 0 0");
        get().addDefault("arenas.arena2", "0 0 0");
        get().addDefault("arenas.arena3", "0 0 0");
        get().addDefault("arenas.arena4", "0 0 0");
        get().addDefault("arenas.arena5", "0 0 0");
        get().addDefault("arenas.arena6", "0 0 0");
        get().addDefault("arenas.arena7", "0 0 0");
        get().addDefault("arenas.arena8", "0 0 0");
        get().addDefault("arenas.arena9", "0 0 0");

        get().options().copyDefaults(true);
        get().options().copyHeader(true);
        save();
    }

}
