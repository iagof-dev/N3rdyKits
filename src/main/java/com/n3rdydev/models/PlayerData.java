package com.n3rdydev.models;

import org.bukkit.Location;

import java.time.LocalTime;
import java.util.UUID;

public class PlayerData {
    public UUID lastHit = null;
    public String Kit = "Nenhum";
    public Integer kills = 0;
    public Integer deaths = 0;
    public Integer score = 0;
    public Boolean scoreboard = true;
    public LocalTime kit_cooldown = null;
    public Boolean can_build = false;
    public Boolean config_menu = false;
    public Location config_position_1 = null;
    public Location config_position_2 = null;
    public Boolean invis = false;
    public Integer warp = 0;
    public Location last_pos = null;
    public Location parkour_checkpoint = null;

    public UUID getLastHit() {
        return lastHit;
    }

    public void setLastHit(UUID lastHit) {
        this.lastHit = lastHit;
    }

    public String getKit() {
        return Kit;
    }

    public void setKit(String kit) {
        Kit = kit;
    }

    public Integer getKills() {
        return kills;
    }

    public void setKills(Integer kills) {
        this.kills = kills;
    }

    public Integer getDeaths() {
        return deaths;
    }

    public void setDeaths(Integer deaths) {
        this.deaths = deaths;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Boolean getScoreboard() {
        return scoreboard;
    }

    public void setScoreboard(Boolean scoreboard) {
        this.scoreboard = scoreboard;
    }

    public LocalTime getKit_cooldown() {
        return kit_cooldown;
    }

    public void setKit_cooldown(LocalTime kit_cooldown) {
        this.kit_cooldown = kit_cooldown;
    }

    public Boolean getCan_build() {
        return can_build;
    }

    public void setCan_build(Boolean can_build) {
        this.can_build = can_build;
    }

    public Boolean getConfig_menu() {
        return config_menu;
    }

    public void setConfig_menu(Boolean config_menu) {
        this.config_menu = config_menu;
    }

    public Location getConfig_position_1() {
        return config_position_1;
    }

    public void setConfig_position_1(Location config_position_1) {
        this.config_position_1 = config_position_1;
    }

    public Location getConfig_position_2() {
        return config_position_2;
    }

    public void setConfig_position_2(Location config_position_2) {
        this.config_position_2 = config_position_2;
    }

    public Boolean getInvis() {
        return invis;
    }

    public void setInvis(Boolean invis) {
        this.invis = invis;
    }

    /*
            0 - SPAWN
            1 - FPS
            2 - LAVA CHALLENGE
            3 - Parkour
    */
    public Integer getWarp() {
        return warp;
    }

    public void setWarp(Integer warp) {
        this.warp = warp;
    }

    public Location getLast_pos() {
        return last_pos;
    }

    public void setLast_pos(Location last_pos) {
        this.last_pos = last_pos;
    }

    public Location getParkour_checkpoint() {
        return parkour_checkpoint;
    }

    public void setParkour_checkpoint(Location parkour_checkpoint) {
        this.parkour_checkpoint = parkour_checkpoint;
    }
}
