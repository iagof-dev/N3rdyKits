package com.n3rdydev.events;

import com.n3rdydev.commands.Admin;
import com.n3rdydev.entity.player;
import com.n3rdydev.kits.Spawn;
import com.n3rdydev.manager.PlayerManager;
import com.n3rdydev.models.PlayerData;
import com.n3rdydev.scoreboard.sb_default;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.potion.PotionEffect;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.n3rdydev.entity.player.can_build;

public class handleSpawn implements Listener {

    private PlayerManager manager;


    public void user_setup(Player p) {
        UUID puid = p.getUniqueId();
        PlayerData data = PlayerManager.jogador.get(p.getUniqueId());
        //limpar chat
        for(int i = 0; i <= 255; i++){
            p.sendMessage("\n");
        }
        if(can_build(puid)){
            player.toggleBuild(p);
        }
        data.setWarp(0);

        data.setKit("Nenhum");
        handleFallDamage.launchpad.put(puid, false);
        data.setScoreboard(true);
        player.loadStats(puid);
        sb_default.Set(p);
        p.setHealth(20);
        for (PotionEffect effect : p.getActivePotionEffects())
            p.removePotionEffect(effect.getType());

        if (!(player.invis.isEmpty() || player.invis.size() == 0)) {
            for (Map.Entry<UUID, Boolean> entry : player.invis.entrySet()) {
                if (entry.getValue() != false) {
                    Player staff = Bukkit.getPlayer(entry.getKey());
                    p.hidePlayer(staff);
                }
            }
        }

    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = (Player) e.getPlayer();

        PlayerManager.jogador.put(p.getUniqueId(), new PlayerData());
        PlayerData data = PlayerManager.jogador.get(p.getUniqueId());
        data.setKit("Nenhum");

        e.setJoinMessage("");
        PlayerInventory inv = p.getInventory();
        inv.setArmorContents(new ItemStack[inv.getArmorContents().length]);
        p.getInventory().clear();
        user_setup(p);
        Location spawn_loc = new Location(p.getWorld(), com.n3rdydev.settings.spawn.spawn_x, com.n3rdydev.settings.spawn.spawn_y, com.n3rdydev.settings.spawn.spawn_z);
        p.teleport(spawn_loc);
        p.setHealth(20);
        Spawn.Receive(p);
    }

}
