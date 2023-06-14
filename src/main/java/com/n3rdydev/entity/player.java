package com.n3rdydev.entity;

import org.bukkit.entity.Player;

import java.time.Duration;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class player {
    public static HashMap<UUID, UUID> lastplayer_hit = new HashMap();
    public static HashMap<UUID, String> selected_kit = new HashMap();
    public static HashMap<UUID, Integer> kills = new HashMap();
    public static HashMap<UUID, Integer> deaths = new HashMap();
    public static HashMap<UUID, LocalTime> kit_cooldown = new HashMap();

    public static void setCooldown(Player p, long Seconds){
        LocalTime time_now = LocalTime.now();
        LocalTime finish_cooldown = time_now.plusSeconds(Seconds);
        UUID player_uuid = p.getUniqueId();
        kit_cooldown.put(player_uuid, finish_cooldown);
    }
    public static Boolean getCooldown(Player p){
        UUID p_uid = p.getUniqueId();

        if(player.kit_cooldown.get(p_uid) == null){
            return false;
        }

        LocalTime atual = LocalTime.now();
        LocalTime delay_player = player.kit_cooldown.get(p.getUniqueId());

        //se tiver com delay
        //retornar: true
        if(atual.isAfter(delay_player)){
            kit_cooldown.put(p_uid, null);
            return false;
        }
        else{
            return true;
        }
    }
    public static String getCooldownTime(Player p){
        UUID p_uid = p.getUniqueId();

        if(player.kit_cooldown.get(p.getUniqueId()) == null){
            return null;
        }
        LocalTime atual = java.time.LocalTime.now();
        LocalTime delay_player = player.kit_cooldown.get(p.getUniqueId());
        Duration tempo_restante = Duration.between(atual, delay_player);
        long segs = tempo_restante.getSeconds();
        return ("§cVocê só podera utilizar daqui " + segs + " segundos.");
    }

    public static void addKills(Player p){
        UUID p_uid = p.getUniqueId();

        if(kills.get(p_uid) != null){
            Integer qnt_kills = kills.get(p_uid);
            Integer sum_kills = qnt_kills+1;
            kills.put(p_uid, sum_kills);
        }
        else{
            kills.put(p.getUniqueId(), 1);
        }

    }
    public static void addDeaths(Player p){

        UUID p_uid = p.getUniqueId();

        if(deaths.get(p_uid) != null){
            Integer qnt_deaths = deaths.get(p_uid);
            Integer sum_kills = qnt_deaths+1;
            deaths.put(p_uid, sum_kills);
        }
        else{
            deaths.put(p_uid, 1);
        }
    }

    public static Integer getKills(Player p){
        UUID p_uid = p.getUniqueId();
        if(kills.get(p_uid) == null){
            kills.put(p_uid, 0);
            return 0;
        }
        Integer p_kills = kills.get(p.getUniqueId());
        return p_kills;

    }

    public static Integer getDeaths(Player p){
        UUID p_uid = p.getUniqueId();
        if(deaths.get(p_uid) == null){
            deaths.put(p_uid, 0);
            return 0;
        }
        Integer p_deaths = deaths.get(p.getUniqueId());
        return p_deaths;
    }



    public static void load(){

    }

    public static void save(){

    }


}
