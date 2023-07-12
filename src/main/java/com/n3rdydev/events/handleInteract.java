package com.n3rdydev.events;

import com.n3rdydev.entity.server;
import com.n3rdydev.gui.Kits;
import com.n3rdydev.gui.RecraftRefil;
import com.n3rdydev.gui.SoupRefil;
import com.n3rdydev.kits.Spawn;
import com.n3rdydev.settings.config;
import com.n3rdydev.settings.serverinfo;
import com.n3rdydev.settings.spawn;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;
import com.n3rdydev.entity.player;

import java.util.ArrayList;
import java.util.Set;
import java.util.UUID;

import static com.n3rdydev.entity.server.criarArenaGlad;

public class handleInteract implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {

            //=========MENUS===========

            //se o jogador clicar em QUALQUER baú, ele vai abrir o menu de kit
            //preciso verificar se o baú está nomeado...
            if (e.getItem() != null && e.getItem().equals(Spawn.gui_kits)) {
                p.openInventory(Kits.list_kits(p));
                return;
            }

            //Se o jogador clicar com o botão direito em uma placa
            //com o nome do servidor e escrito refil/recraft
            //ele vai abrir um gui com sopa ou recraft
            if (e.getAction() == Action.RIGHT_CLICK_BLOCK) {
                if (e.getClickedBlock().getType().equals(Material.SIGN_POST) || e.getClickedBlock().getType().equals(Material.WALL_SIGN)) {
                    Sign sign = (Sign) e.getClickedBlock().getState();

                    if (sign.getLine(1).contains("Refil de sopa")) {
                        p.openInventory(SoupRefil.open(p));
                    } else if (sign.getLine(1).contains("Refil de recraft")) {
                        p.openInventory(RecraftRefil.open(p));
                    }

                    return;
                }
            }

            //Warp
            //Ao clicar na bussola ele vai abrir o gui de warps
            if (e.getItem() != null && e.getItem().equals(Spawn.gui_warps)) {
                p.openInventory(com.n3rdydev.gui.Warps.open());
                return;
            }

            //==========KITS==========

            //Bussola rastreadora
            ItemStack p_tracker = new ItemStack(Material.COMPASS, 1);
            ItemMeta p_tracker_meta = (ItemMeta) p_tracker.getItemMeta();
            p_tracker_meta.setDisplayName("§eRastreador");
            p_tracker.setItemMeta(p_tracker_meta);
            //bussola rastreadora:
            if (e.getItem() != null && e.getItem().equals(p_tracker)) {
                String nickname = "Nenhum";
                Player target = getNearest(p, 250.0);
                float distance = 0;

                //se a vitima for diferente de nula e não está no spawn
                //pegar nome, localização e calcular distancia de blocos
                if (target != null && !spawn.is_safe_zone(target.getLocation())) {
                    nickname = target.getPlayer().getName();
                    p.setCompassTarget(target.getLocation());
                    distance = Math.round(p.getLocation().distanceSquared(target.getLocation()));
                }

                p.sendMessage("Jogador mais próximo: " + nickname + " (" + distance + " metros).");
                return;
            }

            //kit feather:
            //se clicar na pena, ele ativa o fly
            if (e.getItem() != null && e.getItem().getType().equals(Material.FEATHER) && e.getItem().getItemMeta().getDisplayName().equals("§ePhantom")) {
                if (p.getAllowFlight() != true && player.getCooldown(p.getUniqueId()) != true) {
                    p.setAllowFlight(true);
                    p.sendMessage("§aPhantom ativo! Desativando em 6 segundos...");
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            p.setAllowFlight(false);
                            p.sendMessage("§cPhantom desativado!");
                            player.setCooldown(p.getUniqueId(), 6);

                        }
                    }.runTaskLater(com.n3rdydev.main.getPlugin(), 100L);
                } else {
                    if (player.getCooldown(p.getUniqueId()) != false) {
                        p.sendMessage(player.getCooldownTime(p.getUniqueId()));
                    } else {
                        p.sendMessage("§cVocê já está com phantom ativado!");
                    }
                }
            }

            //kit kangaroo:
            if (e.getItem() != null && e.getItem().getType().equals(Material.FIREWORK)) {
                if (e.getItem().getItemMeta().getDisplayName().equals("§eKangaroo")) {
                    e.setCancelled(true);
                    if (p.isOnGround()) {
                        new BukkitRunnable() {
                            @Override
                            public void run() {
                                Vector direction = p.getLocation().getDirection();
                                direction.setY(0.40);
                                direction.multiply(2.0);
                                handleFallDamage.launchpad.put(p.getUniqueId(), true);
                                p.setVelocity(direction);
                            }
                        }.runTaskLater(com.n3rdydev.main.getPlugin(), 1L);

                    }

                    return;
                }
            }

            if (e.getItem() != null && e.getItem().getType().equals(Material.BEACON)) {
                if (e.getItem().getItemMeta().getDisplayName().equals("§eProteção do Spawn - POSIÇÃO 1")) {
                    e.setCancelled(true);
                    UUID uid = p.getUniqueId();

                    if (player.config_menu.get(uid) != null || player.config_menu.get(uid) != false && player.config_position_1.get(uid) == null) {
                        player.config_position_1.put(uid, p.getLocation());
                        p.getInventory().clear();

                        ItemStack protect_item = new ItemStack(Material.BEACON);
                        ItemMeta protect_item_meta = protect_item.getItemMeta();
                        protect_item_meta.setDisplayName("§eProteção do Spawn - POSIÇÃO 2");
                        protect_item.setItemMeta(protect_item_meta);
                        p.getInventory().setItem(0, protect_item);

                        p.updateInventory();
                        p.sendMessage(serverinfo.name() + " | §dPosição 1 definida");
                    }

                    return;
                }
                if (e.getItem().getItemMeta().getDisplayName().equals("§eProteção do Spawn - POSIÇÃO 2")) {
                    e.setCancelled(true);
                    UUID uid = p.getUniqueId();
                    if (player.config_position_1.get(uid) != null) {
                        player.config_position_2.put(uid, p.getLocation());
                        p.sendMessage(serverinfo.name() + " | §dPosição 2 definida");

                        Location pos1 = player.config_position_1.get(uid);
                        Location pos2 = player.config_position_2.get(uid);

                        String pos1_format = pos1.getX() + " " + pos1.getZ();
                        String pos2_format = pos2.getX() + " " + pos2.getZ();

                        config.get().set("spawn.protection.pos1", pos1_format);
                        config.get().set("spawn.protection.pos2", pos2_format);
                        config.save();
                        config.reload();
                        player.config_menu.put(uid, false);
                        com.n3rdydev.kits.Spawn.Receive(p);
                        p.sendMessage(serverinfo.name() + " | §aProteção definida entre (" + pos1_format + ") e (" + pos2_format + ")");
                    }
                }

            }

            //
            //KIT GLADIATOR
            //
            Player target = null;
            target = getNearest(p, 3.0);
            if (e.getItem() != null && e.getAction() == Action.RIGHT_CLICK_AIR && target instanceof Player && e.getItem().getType().equals(Material.IRON_FENCE) && e.getItem().getItemMeta().getDisplayName().equals("§eGladiator")) {

                target = (Player) getNearestEntityInSight(p, 2);

                UUID puid = p.getUniqueId();
                if(server.arena_glad.get(puid) != null) return;

                World wrld = server.getWorld();
                player.last_pos.put(puid, p.getLocation());
                player.last_pos.put(target.getUniqueId(), target.getLocation());
                server.arena_glad_players.put(puid, target.getUniqueId());
                server.arena_glad_players.put(target.getUniqueId(), puid);
                criarArenaGlad(p, target ,17);
            }
        }
        //independente, ele não vai cancelar a interação...
        e.setCancelled(false);
    }

    //pegar o jogador mais perto...
    //usado na bussola rastreadora
    public Player getNearest(Player p, Double range) {
        double distance = Double.POSITIVE_INFINITY;
        Player target = null;
        for (Entity e : p.getNearbyEntities(range, range, range)) {
            if (!(e instanceof Player))
                continue;
            if (e == p) continue;
            double distanceto = p.getLocation().distance(e.getLocation());
            if (distanceto > distance)
                continue;
            distance = distanceto;
            target = (Player) e;
        }
        return target;
    }


    public static Entity getNearestEntityInSight(Player player, int range) {
        ArrayList<Entity> entities = (ArrayList<Entity>) player.getNearbyEntities(range, range, range);
        ArrayList<Block> sightBlock = (ArrayList<Block>) player.getLineOfSight( (Set<Material>) null, range);
        ArrayList<Location> sight = new ArrayList<Location>();
        for (int i = 0;i<sightBlock.size();i++)
            sight.add(sightBlock.get(i).getLocation());
        for (int i = 0;i<sight.size();i++) {
            for (int k = 0;k<entities.size();k++) {
                if (Math.abs(entities.get(k).getLocation().getX()-sight.get(i).getX())<1.3) {
                    if (Math.abs(entities.get(k).getLocation().getY()-sight.get(i).getY())<1.5) {
                        if (Math.abs(entities.get(k).getLocation().getZ()-sight.get(i).getZ())<1.3) {
                            return entities.get(k);
                        }
                    }
                }
            }
        }
        return null; //Return null/nothing if no entity was found
    }



}
