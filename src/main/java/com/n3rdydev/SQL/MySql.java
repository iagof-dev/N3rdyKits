package com.n3rdydev.SQL;

import com.n3rdydev.entity.player;
import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Map;
import java.util.UUID;

public class MySql {

    static String ip = "";
    static int port = 3306;
    static String user = "";
    static String pass = "";
    static String database = "";
    static String table = "";
    static String db_type = "jdbc:mysql://";
    public static String db = db_type+ip+":"+port+"/"+database+"?jdbcCompliantTruncation=false";

    public static Connection start(){
        try{
            return DriverManager.getConnection(db, user, pass);

        }
        catch(Exception e){
            Bukkit.getConsoleSender().sendMessage("N3rdyKits | §cErro!\n" + e);
            return null;
        }
    }

    public static void SaveAll(){
        try {
            //Connection con = start();
            String send = "use N3rdykits;";
            for (Map.Entry<UUID, Integer> kills : player.kills.entrySet()) {
                    send += "insert into deaths values (default, '" + kills.getKey() + "', " + kills.getValue() + ", " + player.deaths.get(kills.getKey()) +");";
                    System.out.println("UUID: " + kills.getKey() + " | Kills: " + kills.getValue() + "| Mortes: " + player.deaths.get(kills.getKey()));
            }

            //PreparedStatement st = con.prepareStatement(send);
            //st.executeUpdate();
            System.out.println(com.n3rdydev.settings.serverinfo.name() + " | Estatisticas salvas no mysql");

        }
        catch (Exception ex){
            System.out.println("§cErro!\n" + ex.getMessage());
        }
    }


}
