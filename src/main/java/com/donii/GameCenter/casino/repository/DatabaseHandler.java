package com.donii.GameCenter.casino.repository;

import com.donii.GameCenter.casino.Utils.Text;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseHandler {
    private static final String DB_URL = "jdbc:sqlite:casino.db";

    public static Connection connection;

    public Connection getConnection() {
        try {
            if(connection == null || connection.isClosed()){
                connection = DriverManager.getConnection(DB_URL);
                System.out.println(Text.green("Подключение к БД успешно"));
            }
        } catch (SQLException e) {
            System.out.println(Text.red("Ошибка подключения к БД"));
        }
        return connection;
    }

    public void createNewTable() {
        String sql = "CREATE TABLE IF NOT EXISTS casino_users (\n" +
                "id integer PRIMARY KEY AUTOINCREMENT,\n" +
                "name text NOT NULL UNIQUE, \n" +
                "password text NOT NULL, \n" +
                "balance integer\n" +
                ");";

        try(Statement statement = getConnection().createStatement()){
            statement.execute(sql);
        } catch(SQLException e) {
            System.out.println(Text.RED + "Error connecting to database: " + e.getMessage() +  Text.RESET);
        }
    }
}

