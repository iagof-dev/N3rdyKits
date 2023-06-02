package com.n3rdydev;

import org.bukkit.Bukkit;

public class config {

    public static void setup_run(){
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule doDaylightCycle false");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule randomTickSpeed 0");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "gamerule mobGriefing false");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "time set 0");
        Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "weather clear");
    }

}
