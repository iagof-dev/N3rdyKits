package com.n3rdydev.settings;

public class serverinfo {
    public static String name(){
        return config.get().getString("server.name");
    }
    public static String ip(){
        return config.get().getString("server.ip");
    }
}
