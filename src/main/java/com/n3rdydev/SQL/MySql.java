package com.n3rdydev.SQL;

import org.bukkit.Bukkit;

import java.sql.Connection;
import java.sql.DriverManager;

public class MySql {
    private static String ip = "";
    private static int port = 3306;
    private String user = "";
    private String pass = "";
    private static String database = "";
    private String table = "";
    private String db_type = "jdbc:mysql://";
    private String db = db_type + ip + ":" + port + "/" + database;
    private Connection con = null;

    public void MySql() {
        try {
            con = DriverManager.getConnection(this.db, this.user, this.pass);
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage("N3rdyKits | §cErro!\n" + e);
        }
    }

    public Connection getCon() {
        return this.con;
    }

    /* public Connection Start() {
        try {
            return DriverManager.getConnection(db, this.user, this.pass);
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage("N3rdyKits | §cErro!\n" + e);
            return null;
        }
    }*/


}
