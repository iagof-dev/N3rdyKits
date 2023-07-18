package com.n3rdydev.settings;

import com.n3rdydev.entity.player;
import com.n3rdydev.manager.PlayerManager;
import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.UUID;

public class statistics {
    public static File file;
    public static FileConfiguration stats;

    public static void setup() {
        file = new File(Bukkit.getServer().getPluginManager().getPlugin("N3rdyKits").getDataFolder(), "statistics.yml");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                Bukkit.getConsoleSender().sendMessage("§c[N3rdyKits] Configuracao erro!\n" + e.getMessage());
            }
        }
        stats = YamlConfiguration.loadConfiguration(file);
    }

    public static FileConfiguration get() {
        return stats;
    }

    private static PlayerManager manager;
    public static void save() {

        for (UUID puid: manager.getPlayers().keySet()) {
            statistics.get().set(puid + ".kills", player.getKills(puid));
            statistics.get().set(puid + ".deaths", player.getDeaths(puid));
            statistics.get().set(puid + ".xp", player.getXP(puid));
        }

        Bukkit.getConsoleSender().sendMessage("[N3rdyKits] Salvando Estatisticas...");
        try {
            stats.save(file);
            Bukkit.getConsoleSender().sendMessage("[N3rdyKits] Estatisticas Salvas!");
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage("§c[N3rdyKits] Estatisticas não foi salva... erro:\n" + e.getMessage());
        }
    }

    public static void reload() {
        stats = YamlConfiguration.loadConfiguration(file);
        Bukkit.getConsoleSender().sendMessage("§a[N3rdyKits] Configurações foi atualizada!");

    }


}
