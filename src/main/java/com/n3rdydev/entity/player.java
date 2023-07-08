package com.n3rdydev.entity;

import com.n3rdydev.kits.Spawn;
import com.n3rdydev.settings.config;
import com.n3rdydev.settings.serverinfo;
import com.n3rdydev.settings.statistics;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Array;
import java.time.Duration;
import java.time.LocalTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class player {

    public static HashMap<UUID, UUID> lastplayer_hit = new HashMap();
    public static HashMap<UUID, String> selected_kit = new HashMap();
    public static HashMap<UUID, Integer> kills = new HashMap();
    public static HashMap<UUID, Integer> deaths = new HashMap();
    public static HashMap<UUID, Integer> score = new HashMap();
    public static HashMap<UUID, Boolean> scoreboard = new HashMap();
    private static HashMap<UUID, LocalTime> kit_cooldown = new HashMap();
    private static HashMap<UUID, Boolean> can_build = new HashMap();

    public static HashMap<UUID, Boolean> config_menu = new HashMap();
    public static HashMap<UUID, Location> config_position_1 = new HashMap();
    public static HashMap<UUID, Location> config_position_2 = new HashMap();
    public static HashMap<UUID, Boolean> invis = new HashMap<>();

    /*
        0 - SPAWN
        1 - FPS
        2 - LAVA CHALLENGE
     */
    public static HashMap<UUID, Integer> warp= new HashMap<>();


    public static void toggleInvis(Player p){

        UUID puid = p.getUniqueId();

        Collection<? extends Player> players = p.getServer().getOnlinePlayers();
        if (invis.get(puid) != null && invis.get(puid) != false) {
            for (Player p_list : players) {
                if (!p_list.hasPermission("n3rdydev.command.admin")) {
                    p_list.showPlayer(p);
                }

            }
            p.setAllowFlight(false);
            p.setFlying(false);
            Spawn.Receive(p);
            p.sendMessage(serverinfo.name() + " §cVocê saiu do MODO ADMIN e está visivel!");
            invis.put(p.getUniqueId(), false);
        } else {
            for (Player p_list : players) {
                if (!p_list.hasPermission("n3rdydev.command.admin")) {
                    p_list.hidePlayer(p);
                } else {
                    if (p_list.getName() != p.getName()) {
                        p_list.sendMessage(serverinfo.name() + " §e " + p.getName() + " entrou no modo ADMIN!");
                        p_list.showPlayer(p);
                    }
                }
            }
            com.n3rdydev.kits.Admin.Receive(p);
            p.setAllowFlight(true);
            p.setFlying(true);
            p.sendMessage(serverinfo.name() + " §aVocê entrou no MODO ADMIN e está invisivel!");
            invis.put(p.getUniqueId(), true);
        }
    }

    public static void setCooldown(UUID player_uuid, long Seconds) {
        LocalTime time_now = LocalTime.now();
        LocalTime finish_cooldown = time_now.plusSeconds(Seconds);
        kit_cooldown.put(player_uuid, finish_cooldown);
    }

    public static Boolean getCooldown(UUID puid) {
        if (player.kit_cooldown.get(puid) == null) {
            return false;
        }
        LocalTime atual = LocalTime.now();
        LocalTime delay_player = player.kit_cooldown.get(puid);
        //se tiver com delay
        //retornar: true
        if (atual.isAfter(delay_player)) {
            kit_cooldown.put(puid, null);
            return false;
        } else {
            return true;
        }
    }

    public static String getCooldownTime(UUID puid) {
        if (player.kit_cooldown.get(puid) == null) {
            return null;
        }
        LocalTime atual = java.time.LocalTime.now();
        LocalTime delay_player = player.kit_cooldown.get(puid);
        Duration tempo_restante = Duration.between(atual, delay_player);
        long segs = tempo_restante.getSeconds();
        return ("§cVocê só podera utilizar daqui " + segs + " segundos.");
    }

    public static Integer getXP(UUID puid){
        if(score.get(puid) == null){
            score.put(puid, 0);
        }
        return score.get(puid);
    }

    public static boolean can_build(UUID puid) {
        if (can_build.get(puid) != null) {
            return can_build.get(puid);
        } else {
            can_build.put(puid, false);
            return false;
        }
    }

    public static void toggleBuild(Player p) {
        String nome = serverinfo.name();
        UUID puid = p.getUniqueId();
        if (can_build(p.getUniqueId()) != true) {
            can_build.put(puid, true);
            p.sendMessage(nome + " §aModo Construir HABILITADO!");

        } else {
            can_build.put(puid, false);
            p.sendMessage(nome + " §cModo Construir DESABILITADO!");
        }
    }

    public static void addKills(UUID p_uid) {
        Integer valor = 1;
        if (kills.get(p_uid) != null) {
            Integer qnt_kills = kills.get(p_uid);
            Integer sum_kills = qnt_kills + 1;
        }
        kills.put(p_uid, valor);
    }
    public static void addXP(UUID puid){
        Integer valor = 5;
        if(player.score.get(puid) != null){
            valor = (player.score.get(puid) + 5);
        }
        player.score.put(puid, valor);
    }
    public static void remXP(UUID puid){
        Integer valor = 0;
        if(player.score.get(puid) != null){
            valor = (player.score.get(puid) - 5);
        }
        player.score.put(puid, valor);
    }

    public static void loadStats(UUID puid) {
        player.kills.put(puid, statistics.get().getInt(puid + ".kills"));
        player.deaths.put(puid, statistics.get().getInt(puid + ".deaths"));
        player.score.put(puid, statistics.get().getInt(puid + ".xp"));
    }

    public static void saveStats(UUID puid) {
        statistics.get().set(puid + ".kills", player.getKills(puid));
        statistics.get().set(puid + ".deaths", player.getDeaths(puid));
        statistics.get().set(puid + ".xp", player.getXP(puid));
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

    public static Integer getKills(UUID puid) {
        if (kills.get(puid) == null) {
            kills.put(puid, 0);
        }
        return kills.get(puid);

    }

    public static Integer getDeaths(UUID puid) {
        if (deaths.get(puid) == null) {
            deaths.put(puid, 0);
            return 0;
        }
        return deaths.get(puid);
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
        try {
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
            Location spawn_loc = new Location(server.world, spawn[0], spawn[1], spawn[2]);
            p.teleport(spawn_loc);
        } catch (Exception ex) {
            randomTpArena(p);
        }
    }

    public static Location convert_config_location(String config_name) {
        Double[] spawn = new Double[3];
        String arena = config.get().getString(config_name);
        if (arena != null) {

            String[] sp_cord_arena = arena.split(" ");
            spawn[0] = Double.parseDouble(sp_cord_arena[0]);
            spawn[1] = Double.parseDouble(sp_cord_arena[1]);
            spawn[2] = Double.parseDouble(sp_cord_arena[2]);
            Location spawn_loc = new Location(server.world, spawn[0], spawn[1], spawn[2]);
            return spawn_loc;
        }
        return null;
    }


}
