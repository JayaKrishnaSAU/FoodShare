package com.example.foodshare.database;

import android.os.StrictMode;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

   //private static final String DRIVER=""
    //private static final String URL = "jdbc:mysql://4.tcp.ngrok.io:15584/foodshare";
    private static final String URL = "jdbc:mysql://192.168.1.120:3306/foodshare";
    //private static final String URL = "jdbc:jtds:sqlserver://192.168.1.120:3306/foodshare";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "password";

    public static Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        //Class.forName("net.sourceforge.jtds.jdbc.Driver");
        return DriverManager.getConnection(URL, USERNAME, PASSWORD);
    }
}