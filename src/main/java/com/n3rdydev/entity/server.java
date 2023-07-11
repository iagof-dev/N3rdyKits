package com.n3rdydev.entity;

import com.n3rdydev.main;
import com.n3rdydev.settings.config;
import org.bukkit.*;
import org.bukkit.block.Block;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public class server {
    public static ItemStack[] gen_items;
    static World world = Bukkit.getWorlds().get(0);

    public static HashMap<UUID, Location> arena_glad = new HashMap();
    public static HashMap<UUID, UUID> arena_glad_players = new HashMap();


    public static World getWorld(){
        return world;
    }


    public static void loop_events() {
        //20 ticks = 1 segundo
        int config_feast_timer = config.get().getInt("server.feast.timer");
        int timer = ((20 * 60) * config_feast_timer);
        new BukkitRunnable() {
            @Override
            public void run() {

                com.n3rdydev.entity.server.feast_generate();
            }
        }.runTaskTimerAsynchronously(main.getPlugin(), 1L, timer);
    }


    public static void feast_generate() {
        for (Player all : Bukkit.getServer().getOnlinePlayers()) {
            all.sendMessage("§eFeast surgiu nas coordenadas 8 72 -62!");
        }

        //int baus = 0;
        int chance = 25;

        //World world = p.getWorld();
        for (Chunk c : world.getLoadedChunks()) {
            for (BlockState b : c.getTileEntities()) {
                if (b instanceof Chest) {
                    //baus++;
                    Chest chest = (Chest) b;

                    ((Chest) b).getBlockInventory().clear();
                    /*
                        Item Aleatorio que irá aparecer nos baús
                        18% de chance
                    */
                    for (int slot = 0; slot <= 26; slot++) {
                        int chance_item = ThreadLocalRandom.current().nextInt(0, 100 + 1);
                        if (chance_item <= chance) {
                            ((Chest) b).getBlockInventory().setItem(slot, random_item());
                        }
                    }
                    //((Chest) b).getBlockInventory().setItem(0, new ItemStack(Material.DIAMOND_SWORD));


                    Location bau_loc = b.getLocation();
                    world.strikeLightning(bau_loc);
                    b.update();
                }
            }
        }
    }

    public static ItemStack random_item() {

        ItemStack wooden_sword = new ItemStack(Material.GOLD_SWORD, 1);
        wooden_sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);

        ItemStack potion_speed = new Potion(PotionType.SPEED, 1, false, false).toItemStack(1);
        ItemStack potion_strenght = new Potion(PotionType.STRENGTH, 1, false, false).toItemStack(1);

        ItemStack[] items = {wooden_sword,
                create_item(Material.LEATHER_CHESTPLATE, 1),
                create_item(Material.LEATHER_HELMET,1),
                create_item(Material.RED_MUSHROOM,32),
                create_item(Material.LEATHER_LEGGINGS,1),
                create_item(Material.AIR,1),
                create_item(Material.LEATHER_BOOTS,1),
                create_item(Material.BOWL,32),
                create_item(Material.MUSHROOM_SOUP,1),
                create_item(Material.AIR,1),
                create_item(Material.BROWN_MUSHROOM,32),
                create_item(Material.ARROW,5),
                create_item(Material.BOW,1),
                create_item(Material.WOOD_SWORD,1),
                create_item(Material.COOKED_BEEF,5),
                potion_speed,
                create_item(Material.CHAINMAIL_BOOTS,1),
                create_item(Material.CHAINMAIL_CHESTPLATE,1),
                create_item(Material.CHAINMAIL_LEGGINGS,1),
                create_item(Material.AIR,1),
                create_item(Material.CHAINMAIL_HELMET,1),
                potion_strenght
                //create_item(Material.,1),
        };

        int rndNumber = ThreadLocalRandom.current().nextInt(0, items.length - 1);
        //System.out.println("Numero gerado: " + rndNumber);
        //System.out.println("Array Size: " + items.length);
        return items[rndNumber];
    }

    public static ItemStack create_item(Material material, int quantity) {
        return new ItemStack(material, quantity);
    }

    public static void feast_clear() {
        //World world = p.getWorld();
        for (Chunk c : world.getLoadedChunks()) {
            for (BlockState b : c.getTileEntities()) {
                if (b instanceof Chest) {
                    Chest chest = (Chest) b;
                    ((Chest) b).getBlockInventory().clear();
                }
            }
        }
    }

    public static void criarArenaGlad(Player player, Player target, int tamanho) {
        World world = player.getWorld();
        int x = player.getLocation().getBlockX();
        int y = player.getLocation().getBlockY() + 64;
        int z = player.getLocation().getBlockZ();

        Location loc = new Location(world, x, y,z);

        int metade = tamanho / 2;
        int raio = metade - 1;

        for (int offsetX = -raio; offsetX <= raio; offsetX++) {
            for (int offsetY = -raio; offsetY <= raio; offsetY++) {
                for (int offsetZ = -raio; offsetZ <= raio; offsetZ++) {
                    if (Math.abs(offsetX) == raio || Math.abs(offsetY) == raio || Math.abs(offsetZ) == raio) {
                        Block block = world.getBlockAt(x + offsetX, y + offsetY, z + offsetZ);
                        block.setType(Material.GLASS);
                    }
                }
            }
        }

        Location player_1 = new Location(getWorld(), player.getLocation().getX() + 5, player.getLocation().getY()+58 , player.getLocation().getZ() + 5);
        Location player_2 = new Location(getWorld(), player.getLocation().getX() - 5, player.getLocation().getY()+58 , player.getLocation().getZ() - 5);
        player.teleport(player_1);
        target.teleport(player_2);
        arena_glad.put(player.getUniqueId(), loc);
        arena_glad.put(target.getUniqueId(), loc);
    }

    public static void remArenaGlad(Location loc, Player p1, Player p2) {
        int x = (int) loc.getX();
        int y = (int) loc.getY();
        int z = (int) loc.getZ();

        int metade = 17 / 2;
        int raio = metade - 1;

        for (int offsetX = -raio; offsetX <= raio; offsetX++) {
            for (int offsetY = -raio; offsetY <= raio; offsetY++) {
                for (int offsetZ = -raio; offsetZ <= raio; offsetZ++) {
                    if (Math.abs(offsetX) == raio || Math.abs(offsetY) == raio || Math.abs(offsetZ) == raio) {
                        Block block = world.getBlockAt(x + offsetX, y + offsetY, z + offsetZ);
                        block.setType(Material.AIR);
                    }
                }
            }
        }

        UUID p1uid = p1.getUniqueId();

        player.last_pos.put(p1uid, null);
        if(p2 != null){
            UUID p2uid = p2.getUniqueId();
            server.arena_glad_players.put(p2uid, null);
            player.last_pos.put(p2uid, null);
        }
        server.arena_glad_players.put(p1uid, null);

    }


}
