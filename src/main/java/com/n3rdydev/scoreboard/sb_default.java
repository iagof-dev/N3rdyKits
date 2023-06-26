package com.n3rdydev.scoreboard;

import com.n3rdydev.entity.player;
import com.n3rdydev.settings.serverinfo;
import org.bukkit.Bukkit;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.*;

import java.util.UUID;

public class sb_default {

    public static void Set(Player p) {
        ScoreboardManager sb_manager = Bukkit.getScoreboardManager();
        Scoreboard sbdefault = sb_manager.getNewScoreboard();
        Objective obj = sbdefault.registerNewObjective("title", "dummy");

        UUID puid = p.getUniqueId();

        String selected_kit = "Nenhum";
        if (player.selected_kit.get(puid) != null) {
            selected_kit = (player.selected_kit.get(puid));
            if(selected_kit == "pvp"){
                selected_kit = "PvP";
            }
            else{
                selected_kit = selected_kit.substring(0, 1).toUpperCase() + selected_kit.substring(1);
            }
        }

        obj.setDisplayName(" §7§l« §6§lKitPvP §7§l» ");
        obj.setDisplaySlot(DisplaySlot.SIDEBAR);
        Score vazio3 = obj.getScore("   ");
        Score sel_kit = obj.getScore("Kit: §a" + selected_kit);
        Score vazio = obj.getScore(" ");
        Score p_kill = obj.getScore("Kills: §7" + player.getKills(p));
        Score p_death = obj.getScore("Mortes: §7" + player.getDeaths(p));
        Score xp = obj.getScore("XP: §6" + player.score.get(puid));
        Score vazio2 = obj.getScore("  ");
        Score serverip = obj.getScore(serverinfo.ip());

        vazio3.setScore(7);
        sel_kit.setScore(6);
        vazio.setScore(5);
        p_kill.setScore(4);
        p_death.setScore(3);
        xp.setScore(2);
        vazio2.setScore(1);
        serverip.setScore(0);

        p.setScoreboard(sbdefault);
    }


}
