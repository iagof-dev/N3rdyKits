package com.n3rdydev.events;

import com.n3rdydev.commands.Admin;
import com.n3rdydev.entity.player;
import com.n3rdydev.kits.Spawn;
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
import java.util.Map;
import java.util.UUID;

import static com.n3rdydev.entity.player.can_build;
import static com.n3rdydev.entity.player.scoreboard;

public class handleSpawn implements Listener {


    public static void user_setup(Player p) {
        //limpar chat
        for(int i = 0; i <= 255; i++){
            p.sendMessage("\n");
        }
        UUID puid = p.getUniqueId();
        if(can_build(puid)){
            player.toggleBuild(p);
        }
        player.warp.put(puid, 0);

        player.selected_kit.put(puid, "Nenhum");
        handleFallDamage.launchpad.put(puid, false);
        scoreboard.put(puid, true);
        player.warp.put(puid, 0);
        player.loadStats(puid);
        com.n3rdydev.scoreboard.sb_default.Set(p);
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

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerJoin(PlayerJoinEvent e) {
        Player p = (Player) e.getPlayer();

        PlayerInventory inv = p.getInventory();
        inv.setArmorContents(new ItemStack[inv.getArmorContents().length]);
        p.getInventory().clear();
        e.setJoinMessage("");
        user_setup(p);
        Location spawn_loc = new Location(p.getWorld(), com.n3rdydev.settings.spawn.spawn_x, com.n3rdydev.settings.spawn.spawn_y, com.n3rdydev.settings.spawn.spawn_z);
        p.teleport(spawn_loc);
        p.setHealth(20);
        Spawn.Receive(p);
    }

}
