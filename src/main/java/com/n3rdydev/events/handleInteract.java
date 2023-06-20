package com.n3rdydev.events;

import com.n3rdydev.gui.Kits;
import com.n3rdydev.gui.RecraftRefil;
import com.n3rdydev.gui.SoupRefil;
import com.n3rdydev.settings.config;
import com.n3rdydev.settings.spawn;
import org.bukkit.Location;
import org.bukkit.Material;
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

public class handleInteract implements Listener {
    @EventHandler(priority = EventPriority.HIGHEST)
    public void PlayerInteractEvent(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK || e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {

            //=========MENUS===========

            //se o jogador clicar em QUALQUER baú, ele vai abrir o menu de kit
            //preciso verificar se o baú está nomeado...
            if (e.getItem() != null && e.getItem().getType().equals(Material.CHEST)) {
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
            ItemStack gui_warps = new ItemStack(Material.COMPASS);
            ItemMeta warps_meta = (ItemMeta) gui_warps.getItemMeta();
            warps_meta.setDisplayName("§l§6Warps");
            gui_warps.setItemMeta(warps_meta);

            if (e.getItem() != null && e.getItem().equals(gui_warps)) {
                com.n3rdydev.gui.Warps.open(p);
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
                String nickname = "Ninguém";
                Player target = getNearest(p, 250.0);
                float distance = 0;

                //se a vitima for diferente de nula e não está no spawn
                //pegar nome, localização e calcular distancia de blocos
                if (target != null && !spawn.is_safe_zone(target.getLocation())) {
                    nickname = target.getPlayer().getName();
                    p.setCompassTarget(target.getLocation());
                    distance = Math.round(p.getLocation().distanceSquared(target.getLocation()));
                }

                p.sendMessage("Jogador mais próximo: " + nickname + "! (" + distance + " metros de distancia).");
                return;
            }

            //kit feather:
            //se clicar na pena, ele ativa o fly
            if (e.getItem() != null && e.getItem().getType().equals(Material.FEATHER) && e.getItem().getItemMeta().getDisplayName().equals("§ePhantom")) {
                if (p.getAllowFlight() != true && player.getCooldown(p) != true) {
                    p.setAllowFlight(true);
                    p.sendMessage("§aPhantom ativado! desativando em 6 segundos...");
                    new BukkitRunnable() {
                        @Override
                        public void run() {
                            p.setAllowFlight(false);
                            p.sendMessage("§cPhantom desativado!");
                            player.setCooldown(p, 6);

                        }
                    }.runTaskLater(com.n3rdydev.main.getPlugin(), 100L);
                } else {
                    if (player.getCooldown(p) != false) {
                        p.sendMessage(player.getCooldownTime(p));
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
        }
        //independente, ele vai cancelar a interação...
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

}
