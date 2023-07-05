package com.n3rdydev.entity;

import org.bukkit.*;
import org.bukkit.block.BlockState;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class server {
    public static ItemStack[] gen_items;
    static World world = Bukkit.getWorlds().get(0);

    public static void feast_generate() {
        //int baus = 0;
        int chance = 18;

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
        int max = 17;
        List<ItemStack> items = new ArrayList<>();

        ItemStack wooden_sword = new ItemStack(Material.GOLD_SWORD, 1);
        wooden_sword.addEnchantment(Enchantment.DAMAGE_ALL, 1);

        ItemStack potion_speed = new Potion(PotionType.SPEED, 1, false ,false).toItemStack(1);

        items.add(wooden_sword); //0
        items.add(new ItemStack(Material.LEATHER_CHESTPLATE, 1));
        items.add(new ItemStack(Material.LEATHER_HELMET, 1));
        items.add(new ItemStack(Material.RED_MUSHROOM, 64));
        items.add(new ItemStack(Material.LEATHER_LEGGINGS, 1));
        items.add(new ItemStack(Material.LEATHER_BOOTS, 1));
        items.add(new ItemStack(Material.MUSHROOM_SOUP, 1));  //5
        items.add(new ItemStack(Material.BROWN_MUSHROOM, 64));
        items.add(new ItemStack(Material.ARROW, 5));
        items.add(new ItemStack(Material.BOW, 1));
        items.add(new ItemStack(Material.WOOD_SWORD, 1));
        items.add(new ItemStack(Material.COOKED_BEEF, 1));
        items.add(new ItemStack(Material.BOWL, 64));
        items.add(potion_speed);
        items.add(new ItemStack(Material.CHAINMAIL_BOOTS, 1));
        items.add(new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1));
        items.add(new ItemStack(Material.CHAINMAIL_LEGGINGS, 1));
        items.add(new ItemStack(Material.CHAINMAIL_HELMET, 1));





        int rndNumber = ThreadLocalRandom.current().nextInt(0, max + 1);

        return items.get(rndNumber);


    }
    public static void feast_clear(){
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

}
