package com.n3rdydev.events;

import com.n3rdydev.entity.player;
import com.n3rdydev.entity.server;
import com.n3rdydev.kits.FPS;
import com.n3rdydev.kits.LavaChallenge;
import com.n3rdydev.kits.Parkour;
import com.n3rdydev.kits.Spawn;
import com.n3rdydev.main;
import com.n3rdydev.scoreboard.sb_default;
import com.n3rdydev.settings.spawn;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.UUID;

import static com.n3rdydev.entity.player.convert_config_location;
import static com.n3rdydev.entity.player.scoreboard;

public class handleRespawn implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerRespawnEvent(PlayerRespawnEvent e) {
        //teleporte para o spawn
        //não sei nem se isso funciona
        Player p = e.getPlayer();
        Location spawn_loc = new Location(p.getWorld(), com.n3rdydev.settings.spawn.spawn_x, com.n3rdydev.settings.spawn.spawn_y, spawn.spawn_z);
        e.setRespawnLocation(spawn_loc);
        p.teleport(spawn_loc);
    }

    //TODO:
    //ARRUMAR O CÓDIGO DO GLAD
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerDeath(PlayerDeathEvent e) {
        //sem mensagem de morte desnecessária para todos os jogadores
        e.setDeathMessage("");
        
        //limpar drop
        e.getDrops().clear();

        //caso jogador estiver pegando fogo, parar...
        e.getEntity().getPlayer().setFireTicks(0);

        new BukkitRunnable() {
            @Override
            public void run() {

                //pegar Player
                Player p = (Player) e.getEntity().getPlayer();
                Player pk;

                handleFallDamage.launchpad.put(p.getUniqueId(), false);
                UUID puid = p.getUniqueId();
                player.selected_kit.put(puid, "Nenhum");

                if (e.getEntity().getKiller() == null) {
                    //adicionar estatistica
                    player.addDeaths(p.getUniqueId());
                    //mensagem
                    p.sendMessage("§cVocê morreu!");

                    /*
                        se o jogador estiver na hashmap que estava em um glad
                        pode ser q ele morreu de forma desconhecida
                        isto faz remover a arena e contar pro outro
                        jogador que estava como kill e xp
                        para não ficar arena bugada
                    */
                    if(server.arena_glad.get(puid) != null && server.arena_glad_players.get(puid) != null){
                        Player target = Bukkit.getPlayer(server.arena_glad_players.get(puid));
                        target.teleport(player.last_pos.get(target.getUniqueId()));
                        server.remArenaGlad(server.arena_glad.get(puid), p, null);

                        //estatisticas caso o jogador estava em um glad e morreu
                        //sem motivo aparente
                        player.addKills(target.getUniqueId());
                        player.addXP(target.getUniqueId());

                        //enviando mensagem
                        p.sendMessage("§cVocê morreu para " + target.getName() + "! (-5 xp)");
                        target.sendMessage("§a Você matou " + p.getName() + "! (+5 xp)");

                        UUID pkuid = target.getUniqueId();

                        if (player.last_pos.get(pkuid) != null) target.teleport(player.last_pos.get(pkuid));
                        if (server.arena_glad.get(pkuid) != null) server.arena_glad.put(pkuid, null);
                        if (scoreboard.get(pkuid) != false && pkuid != null) sb_default.Set(target);

                    }
                } else {
                    //jogador que matou
                    pk = (Player) e.getEntity().getKiller().getPlayer();
                    UUID pkuid = pk.getUniqueId();
                    //mensagem para ambos jogadores
                    p.sendMessage("§cVocê morreu para " + pk.getName() + "! (-5 xp)");
                    pk.sendMessage("§a Você matou " + p.getName() + "! (+5 xp)");

                    //estatisticas
                    player.addKills(pkuid);
                    player.addDeaths(puid);
                    player.remXP(puid);
                    player.addXP(pkuid);

                    //atualizando scoreboard do cara q matou
                    if (scoreboard.get(pkuid) != false && pkuid != null) sb_default.Set(pk);


                    if (player.last_pos.get(pkuid) != null) pk.teleport(player.last_pos.get(pkuid));
                    if (server.arena_glad.get(pkuid) != null) server.arena_glad.put(pkuid, null);

                    if (server.arena_glad.get(puid) != null) server.remArenaGlad(server.arena_glad.get(puid), p, pk);
                }
                //limpa arena do glad
                server.arena_glad.put(puid, null);
                if (server.arena_glad.get(puid) != null) server.remArenaGlad(server.arena_glad.get(puid), p, null);


                //renasce o jogador sem aparecer
                //a tela pra clicar pra renascer
                e.getEntity().spigot().respawn();

                /*
                    se o jogador deu /score para desativar a scoreboard
                    ele não vai atualizar
                */
                if (scoreboard.get(puid) != false || scoreboard.get(puid) == null) sb_default.Set(p);

                //definindo o kit selecionado para nenhum
                //para não ocorrer erro...
                player.selected_kit.put(puid, "nenhum");

                /*
                    Local de TP
                    se o jogador estiver em uma warp, ele irá teleportar
                    para a arena da warp
                    exemplo:
                    0 = spawn
                    1 = fps
                    2 = lava challenge
                 */
                int warp = player.warp.get(puid);
                Location tp;
                if (warp != 0) {
                    switch (warp) {
                        case 1:
                            tp = convert_config_location("warps.fps.spawnpos");
                            p.teleport(tp);
                            FPS.Receive(p);
                            break;
                        case 2:
                            tp = convert_config_location("warps.lavachallenge.spawnpos");
                            p.teleport(tp);
                            LavaChallenge.Receive(p);
                            break;
                        case 3:
                            tp = convert_config_location("warps.parkour.spawnpos");
                            p.teleport(tp);
                            Parkour.Receive(p);
                            break;
                    }

                } else {
                    Spawn.Receive(p);
                }
            }
        }.runTaskLater(main.getPlugin(), 1L);

    }
}
