package Utils;

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
        String sql = "CREATE TABLE IF NOT EXISTS casino_players (\n" +
                "id integer PRIMARY KEY AUTOINCREMENT,\n" +
                "name text NOT NULL UNIQUE, \n" +
                "balance integer\n" +
                ");";

        try(Connection conn = this.connect();
            Statement statement = conn.createStatement()){

            statement.execute(sql);
        } catch(SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }


    public void savePlayer(String name, int balance) {
        String sql = "INSERT OR REPLACE INTO casino_players(name, balance) VALUES(?, ?)";

        try(Connection conn = this.connect();
           PreparedStatement preparedStmt = conn.prepareStatement(sql)){

            preparedStmt.setString(1, name);
            preparedStmt.setInt(2, balance);

            preparedStmt.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }

    public void updatePlayerBalance(String name, int balance) {
        String sql = "UPDATE casino_players SET balance = ? WHERE name = ?";
        try(Connection conn = this.connect();
            PreparedStatement preparedStmt = conn.prepareStatement(sql)){

            preparedStmt.setInt(1, balance);
            preparedStmt.setString(2, name);

            preparedStmt.executeUpdate();
        } catch(SQLException e) {
            System.out.println("Error connecting to database: " + e.getMessage());
        }
    }

    public int getBalance(String playerName) {
        String sql = "SELECT balance FROM casino_players WHERE name = ?";

        try(Connection conn = this.connect();
            PreparedStatement preparedStmt = conn.prepareStatement(sql)){

            preparedStmt.setString(1,  playerName);
            ResultSet rs = preparedStmt.executeQuery();
            if(rs.next()){
                rs.getInt("balance");
            }
            return rs.getInt("balance");
        } catch (SQLException e){
            System.out.println("Error connecting to database: " + e.getMessage());
        }
        return 0;
    }

}

