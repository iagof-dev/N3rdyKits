package com.n3rdydev.entity;

import com.n3rdydev.kits.Spawn;
import com.n3rdydev.manager.PlayerManager;
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

    private static PlayerManager manager;
/*

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


    public static HashMap<UUID, Integer> warp = new HashMap<>();

    public static HashMap<UUID, Location> last_pos = new HashMap<>();

    static HashMap<UUID, Location> parkour_checkpoint = new HashMap<>();
*/

    public static HashMap<UUID, Boolean> invis = new HashMap<>();

    public static Location getParkourCheckpoint(UUID puid) {
        return manager.getPlayers().get(puid).getParkour_checkpoint();
    }

    public static void setParkourCheckpoint(UUID puid, Location loc) {
        manager.getPlayers().get(puid).setParkour_checkpoint(loc);
    }

    public static void setFinishParkour(UUID puid) {
        manager.getPlayers().get(puid).setParkour_checkpoint(null);
        player.addMoreXP(puid, 1000);
        com.n3rdydev.scoreboard.sb_default.Set(Bukkit.getPlayer(puid));

    }


    public static void toggleInvis(Player p) {

        UUID puid = p.getUniqueId();

        Collection<? extends Player> players = p.getServer().getOnlinePlayers();
        if (player.invis.get(puid) != null && player.invis.get(puid) != false) {
            for (Player p_list : players) {
                if (!p_list.hasPermission("n3rdydev.command.admin")) {
                    p_list.showPlayer(p);
                }

            }
            p.setAllowFlight(false);
            p.setFlying(false);
            Spawn.Receive(p);
            p.sendMessage(serverinfo.name() + " §cVocê saiu do MODO ADMIN e está visivel!");
            player.invis.put(puid,false);
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
            player.invis.put(puid,true);
        }
    }

    public static void setCooldown(UUID puid, long Seconds) {
        LocalTime time_now = LocalTime.now();
        LocalTime finish_cooldown = time_now.plusSeconds(Seconds);
        manager.getPlayers().get(puid).setKit_cooldown(finish_cooldown);
    }

    public static Boolean getCooldown(UUID puid) {
        if (manager.getPlayers().get(puid).getKit_cooldown() == null) {
            return false;
        }
        LocalTime atual = LocalTime.now();
        LocalTime delay_player = manager.getPlayers().get(puid).getKit_cooldown();
        //se tiver com delay
        //retornar: true
        if (atual.isAfter(delay_player)) {
            manager.getPlayers().get(puid).setKit_cooldown(null);
            return false;
        } else {
            return true;
        }
    }

    public static String getCooldownTime(UUID puid) {
        if (manager.getPlayers().get(puid).getKit_cooldown() == null) {
            return null;
        }
        LocalTime atual = java.time.LocalTime.now();
        LocalTime delay_player = manager.getPlayers().get(puid).getKit_cooldown();
        Duration tempo_restante = Duration.between(atual, delay_player);
        long segs = tempo_restante.getSeconds();
        return ("§cVocê só podera utilizar daqui " + segs + " segundos.");

    }

    public static Integer getXP(UUID puid) {
        if (manager.getPlayers().get(puid).getScore() == null) manager.getPlayers().get(puid).setScore(0);
        return manager.getPlayers().get(puid).getScore();
    }

    public static boolean can_build(UUID puid) {
        if (manager.getPlayers().get(puid).getCan_build() != null) {
            return manager.getPlayers().get(puid).getCan_build();
        } else {
            manager.getPlayers().get(puid).setCan_build(false);
            return false;
        }
    }

    public static void toggleBuild(Player p) {
        String nome = serverinfo.name();
        UUID puid = p.getUniqueId();
        if (can_build(puid) != true) {
            manager.getPlayers().get(puid).setCan_build(true);
            p.sendMessage(nome + " §aModo Construir HABILITADO!");

        } else {
            manager.getPlayers().get(puid).setCan_build(false);
            p.sendMessage(nome + " §cModo Construir DESABILITADO!");
        }
    }

    public static void addKills(UUID p_uid) {
        Integer valor = 1;
        if (manager.getPlayers().get(p_uid).getKills() != null) {
            valor = manager.getPlayers().get(p_uid).getKills() + 1;
        }
        manager.getPlayers().get(p_uid).setKills(valor);
    }

    public static void addXP(UUID puid) {
        Integer valor = 5;
        if (manager.getPlayers().get(puid).getScore() != null) {
            valor = (manager.getPlayers().get(puid).getScore() + 5);
        }
        manager.getPlayers().get(puid).setScore(valor);
    }

    public static void addMoreXP(UUID puid, Integer qnt) {
        Integer valor = qnt;
        if (manager.getPlayers().get(puid).getScore() != null)
            valor = (manager.getPlayers().get(puid).getScore() + qnt);
        manager.getPlayers().get(puid).setScore(valor);
    }

    public static void remXP(UUID puid) {
        Integer valor = 0;
        if (manager.getPlayers().get(puid).getScore() != null) {
            valor = (manager.getPlayers().get(puid).getScore() - 5);
        }
        manager.getPlayers().get(puid).setScore(valor);
    }

    public static void loadStats(UUID puid) {
        manager.getPlayers().get(puid).setKills(statistics.get().getInt(puid + ".kills"));
        manager.getPlayers().get(puid).setDeaths(statistics.get().getInt(puid + ".deaths"));
        manager.getPlayers().get(puid).setScore(statistics.get().getInt(puid + ".xp"));
    }

    public static void saveStats(UUID puid) {
        statistics.get().set(puid + ".kills", player.getKills(puid));
        statistics.get().set(puid + ".deaths", player.getDeaths(puid));
        statistics.get().set(puid + ".xp", player.getXP(puid));
        statistics.save();
    }

    public static void addDeaths(UUID puid) {
        Integer valor = 1;
        if (manager.getPlayers().get(puid).getDeaths() != null) valor = manager.getPlayers().get(puid).getDeaths() + 1;
        manager.getPlayers().get(puid).setDeaths(valor);
    }

    public static Integer getKills(UUID puid) {
        if (manager.getPlayers().get(puid).getKills() == null) {
            manager.getPlayers().get(puid).setKills(0);
        }
        return manager.getPlayers().get(puid).getKills();

    }

    public static Integer getDeaths(UUID puid) {
        Integer valor = 0;
        if (manager.getPlayers().get(puid).getDeaths() == null) {
            manager.getPlayers().get(puid).setDeaths(0);
        }
        return manager.getPlayers().get(puid).getDeaths();
    }


    public static void updateScoreboard(Player p) {
        UUID puid = p.getUniqueId();
        if (manager.getPlayers().get(puid).getScoreboard() == null || manager.getPlayers().get(puid).getScoreboard() != true) {
            manager.getPlayers().get(puid).setScoreboard(true);
            com.n3rdydev.scoreboard.sb_default.Set(p);
        } else {
            manager.getPlayers().get(puid).setScoreboard(false);
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
