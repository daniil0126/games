package com.donii.GameCenter.casino.repository;

import com.donii.GameCenter.casino.Utils.Text;
import com.donii.GameCenter.casino.model.Player;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class SqlUserRepository implements UserRepository {
    private final DatabaseHandler databaseHandler;

    public SqlUserRepository(DatabaseHandler databaseHandler) {
        this.databaseHandler = databaseHandler;
    }

    public void ConnectionException(Exception e) {
        System.out.println(Text.RED + "Error connecting to database: " + e.getMessage() + Text.RESET);
    }

    @Override
    public void savePlayer(Player player) {
        String sql = "INSERT OR REPLACE INTO casino_users(name, password, balance) VALUES(?, ?, ?)";

        try(Connection conn = databaseHandler.connect();
            PreparedStatement preparedStmt = conn.prepareStatement(sql)){

            preparedStmt.setString(1, player.getUsername());
            preparedStmt.setString(2, player.getPassword());
            preparedStmt.setInt(3, player.getBalance());

            preparedStmt.executeUpdate();
        } catch(SQLException e) {
            ConnectionException(e);
        }
    }

    @Override
    public void updatePlayer(Player player) {
        String sql = "UPDATE casino_users SET balance = ? WHERE name = ?";
        try(Connection conn = this.databaseHandler.connect();
            PreparedStatement preparedStmt = conn.prepareStatement(sql)){

            preparedStmt.setInt(1, player.getBalance());
            preparedStmt.setString(2, player.getUsername());

            preparedStmt.executeUpdate();
        } catch(SQLException e) {
            ConnectionException(e);
        }
    }

    @Override
    public Player findByName(String username){
        String sql = "SELECT * FROM casino_users WHERE name = ?";
        try (Connection conn = databaseHandler.connect();
             PreparedStatement preparedStmt = conn.prepareStatement(sql)){

            preparedStmt.setString(1, username);
            ResultSet rs = preparedStmt.executeQuery();
            if(rs.next()){
                return new Player(
                        rs.getInt("balance"),
                        rs.getString("name"),
                        rs.getString("password")
                );
            }
        }
        catch(SQLException e){
            ConnectionException(e);
        }
        return null;
    }

    @Override
    public int getBalance(Player player){
        String sql = "SELECT balance FROM casino_users WHERE name = ?";
        try(Connection conn = databaseHandler.connect();
            PreparedStatement preparedStmt = conn.prepareStatement(sql)){

            preparedStmt.setString(1,  player.getUsername());
            ResultSet rs = preparedStmt.executeQuery();
            if(rs.next()){
                return rs.getInt("balance");
            }
        } catch (SQLException e){
            ConnectionException(e);
        }
        return 0;
    }

    @Override
    public String getPassword(String username){
        String sql = "SELECT password FROM casino_users WHERE name = ?";
        try(Connection conn = databaseHandler.connect();
            PreparedStatement preparedStmt = conn.prepareStatement(sql)){

            preparedStmt.setString(1,  username);
            ResultSet rs = preparedStmt.executeQuery();
            if(rs.next()){
                return rs.getString("password");
            }
        } catch (SQLException e){
            ConnectionException(e);
        }
        return null;
    }
}
