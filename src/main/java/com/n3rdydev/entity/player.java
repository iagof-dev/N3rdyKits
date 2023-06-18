package com.n3rdydev.entity;

import com.n3rdydev.settings.config;
import com.n3rdydev.settings.statistics;
import jdk.javadoc.internal.doclets.toolkit.util.NewAPIBuilder;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class player {
    public static HashMap<UUID, UUID> lastplayer_hit = new HashMap();
    public static HashMap<UUID, String> selected_kit = new HashMap();
    public static HashMap<UUID, Integer> kills = new HashMap();
    public static HashMap<UUID, Integer> deaths = new HashMap();

    public static HashMap<UUID, Boolean> scoreboard = new HashMap();
    public static HashMap<UUID, LocalTime> kit_cooldown = new HashMap();
    public static HashMap<UUID, Boolean> can_build = new HashMap();

    public static void setCooldown(Player p, long Seconds) {
        LocalTime time_now = LocalTime.now();
        LocalTime finish_cooldown = time_now.plusSeconds(Seconds);
        UUID player_uuid = p.getUniqueId();
        kit_cooldown.put(player_uuid, finish_cooldown);
    }

    public static Boolean getCooldown(Player p) {
        UUID p_uid = p.getUniqueId();

        if (player.kit_cooldown.get(p_uid) == null) {
            return false;
        }

        LocalTime atual = LocalTime.now();
        LocalTime delay_player = player.kit_cooldown.get(p.getUniqueId());

        //se tiver com delay
        //retornar: true
        if (atual.isAfter(delay_player)) {
            kit_cooldown.put(p_uid, null);
            return false;
        } else {
            return true;
        }
    }

    public static String getCooldownTime(Player p) {
        UUID p_uid = p.getUniqueId();

        if (player.kit_cooldown.get(p.getUniqueId()) == null) {
            return null;
        }
        LocalTime atual = java.time.LocalTime.now();
        LocalTime delay_player = player.kit_cooldown.get(p.getUniqueId());
        Duration tempo_restante = Duration.between(atual, delay_player);
        long segs = tempo_restante.getSeconds();
        return ("§cVocê só podera utilizar daqui " + segs + " segundos.");
    }

    public static boolean can_build(Player p){
        UUID puid = p.getUniqueId();
        if(can_build.get(puid) != null){
            return can_build.get(puid);
        }
        else{
            can_build.put(puid, false);
            return false;
        }
    }

    public static void toggleBuild(Player p){
        UUID puid = p.getUniqueId();
        if(can_build(p) != true){
            can_build.put(puid, true);
        }
        else{
            can_build.put(puid, false);
        }
    }

    public static void addKills(Player p) {
        UUID p_uid = p.getUniqueId();

        if (kills.get(p_uid) != null) {
            Integer qnt_kills = kills.get(p_uid);
            Integer sum_kills = qnt_kills + 1;
            kills.put(p_uid, sum_kills);
        } else {
            kills.put(p.getUniqueId(), 1);
        }


    }

    public static void loadStats(Player p){
        UUID puid = p.getUniqueId();
        player.kills.put(puid, statistics.get().getInt(puid+".kills"));
        player.deaths.put(puid, statistics.get().getInt(puid+".deaths"));
    }

    public static void saveStats(Player p){
        UUID puid = p.getUniqueId();
        statistics.get().set(puid+".kills", player.getKills(p));
        statistics.get().set(puid+".deaths", player.getDeaths(p));
        statistics.get().set(puid+".xp", 0);
        statistics.save();
    }

    public static void addDeaths(Player p) {

        UUID p_uid = p.getUniqueId();

        if (deaths.get(p_uid) != null) {
            Integer qnt_deaths = deaths.get(p_uid);
            Integer sum_kills = qnt_deaths + 1;
            deaths.put(p_uid, sum_kills);
        } else {
            deaths.put(p_uid, 1);
        }
    }

    public static Integer getKills(Player p) {
        UUID p_uid = p.getUniqueId();
        if (kills.get(p_uid) == null) {
            kills.put(p_uid, 0);
            return 0;
        }
        Integer p_kills = kills.get(p.getUniqueId());
        return p_kills;

    }

    public static Integer getDeaths(Player p) {
        UUID p_uid = p.getUniqueId();
        if (deaths.get(p_uid) == null) {
            deaths.put(p_uid, 0);
            return 0;
        }
        Integer p_deaths = deaths.get(p.getUniqueId());
        return p_deaths;
    }


    public static void updateScoreboard(Player p) {

        UUID puid = p.getUniqueId();

        if (scoreboard.get(puid) != true || scoreboard.get(puid) == null) {
            scoreboard.put(puid, true);
            com.n3rdydev.scoreboard.sb_default.Set(p);
        } else {
            scoreboard.put(puid, false);
            p.setScoreboard(Bukkit.getScoreboardManager().getNewScoreboard());

        }
    }

    public static void randomTpArena(Player p) {

        new BukkitRunnable() {
            @Override
            public void run() {
                Double[] spawn = new Double[3];

                int min = 1;
                int max = 10;
                int rndNumber = ThreadLocalRandom.current().nextInt(min, max + 1);
                //Random rnd = new Random();
                //int rndNumber = rnd.nextInt(min, max);
                String arena0 = config.get().getString("arenas.arena" + rndNumber);
                String[] sp_cord_arena = arena0.split(" ");
                spawn[0] = Double.parseDouble(sp_cord_arena[0]);
                spawn[1] = Double.parseDouble(sp_cord_arena[1]);
                spawn[2] = Double.parseDouble(sp_cord_arena[2]);
                Location spawn_loc = new Location(p.getWorld(), spawn[0], spawn[1], spawn[2]);
                p.teleport(spawn_loc);
            }

        }.runTaskLater(com.n3rdydev.main.getPlugin(), 10L);

    }


    public static void load() {

    }

    public static void save() {

    }


}
