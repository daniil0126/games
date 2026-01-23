package com.donii.GameCenter.casino.repository;

import com.donii.GameCenter.casino.model.Player;

public interface UserRepository {
    void savePlayer(Player player);
    void updatePlayer(Player player);
    int getBalance(Player player);
    String getPassword(String username);
    Player findByName(String username);
}
