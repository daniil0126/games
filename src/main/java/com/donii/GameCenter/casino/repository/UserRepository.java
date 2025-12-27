package com.donii.GameCenter.casino.repository;

import com.donii.GameCenter.casino.model.Player;

public interface UserRepository {
    void savePlayer(Player player);
    void updatePlayer(Player player);
    String getPassword(String username);
    int getBalance(Player player);
    Player findByName(String username);
}
