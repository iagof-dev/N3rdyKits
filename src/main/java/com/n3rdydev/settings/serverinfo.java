package com.n3rdydev.settings;

import com.n3rdydev.config;

public class serverinfo {
    public static String name(){
        return config.get().getString("server.name");
    }
}
