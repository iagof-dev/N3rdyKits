package com.n3rdydev.entity;

import java.time.LocalTime;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

public class player {


    public static HashMap<UUID, String> selected_kit = new HashMap();
    public static HashMap<UUID, Integer> kills = new HashMap();
    public static HashMap<UUID, LocalTime> cooldown_ninja = new HashMap();

    public static void load(){

    }

    public static void save(){

    }


}
