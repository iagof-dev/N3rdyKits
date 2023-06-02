package com.n3rdydev.SQL;

import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;

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


}
