package com.donii.GameCenter.casino.repository;

import java.sql.*;

public class DatabaseHandler {
    private static final String DB_URL = "jdbc:sqlite:casino-db.db";

    public Connection connect(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DB_URL);
        } catch(SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
        return conn;
    }

    public void createNewTable() {
        String sql = "CREATE TABLE IF NOT EXISTS casino_users (\n" +
                "id integer PRIMARY KEY AUTOINCREMENT,\n" +
                "name text NOT NULL UNIQUE, \n" +
                "password text NOT NULL, \n" +
                "balance integer\n" +
                ");";

        try(Connection conn = this.connect();
            Statement statement = conn.createStatement()){

            statement.execute(sql);
        } catch(SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }
}

