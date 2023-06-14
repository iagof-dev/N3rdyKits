package com.n3rdydev.settings;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class statistics {
    public static File file;
    public static FileConfiguration stats;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("N3rdyKits").getDataFolder(), "statistics.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
                Bukkit.getConsoleSender().sendMessage("§a[N3rdyKits] Arquivo de statistics.yml foi criado!");
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage("§c[N3rdyKits] Configuracao erro!\n" + e.getMessage());
            }
        }
        stats = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return stats;
    }

    public static void save() {
        Bukkit.getConsoleSender().sendMessage("[N3rdyKits] Salvando Estatisticas...");
        try {
            stats.save(file);
            Bukkit.getConsoleSender().sendMessage("§a[N3rdyKits] Estatisticas Salvas!");
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage("§c[N3rdyKits] Estatisticas não foi salva... erro:\n" + e.getMessage());
        }
    }

    public static void reload() {
        stats = YamlConfiguration.loadConfiguration(file);
        Bukkit.getConsoleSender().sendMessage("§a[N3rdyKits] Configurações foi atualizada!");

    }


}
