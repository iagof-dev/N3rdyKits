package com.n3rdydev.scoreboard;

import com.n3rdydev.entity.player;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

public class sb_default {

    public static void Set(Player p){
            ScoreboardManager sb_manager = Bukkit.getScoreboardManager();
            Scoreboard sbdefault = sb_manager.getNewScoreboard();
            Objective obj = sbdefault.registerNewObjective("title",  "dummy");

            obj.setDisplayName(com.n3rdydev.settings.serverinfo.name());
            obj.setDisplaySlot(DisplaySlot.SIDEBAR);

            Score serverip = obj.getScore(com.n3rdydev.settings.serverinfo.ip());
            Score sel_kit = obj.getScore("Kit: " + player.selected_kit.get(p.getUniqueId()));
            Score p_kill = obj.getScore("Kills: " + p.getStatistic(Statistic.PLAYER_KILLS));
            Score p_death = obj.getScore("Mortes: " + p.getStatistic(Statistic.DEATHS));
            Score xp = obj.getScore("XP: 0");
            Score vazio = obj.getScore(" ");
            Score vazio2 = obj.getScore("  ");
            sel_kit.setScore(6);
            vazio2.setScore(5);
            p_kill.setScore(4);
            p_death.setScore(3);
            xp.setScore(2);
            vazio.setScore(1);
            serverip.setScore(0);

            p.setScoreboard(sbdefault);
    }


}
