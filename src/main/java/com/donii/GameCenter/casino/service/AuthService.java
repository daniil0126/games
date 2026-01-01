package com.donii.GameCenter.casino.service;

import com.donii.GameCenter.casino.Utils.Text;
import com.donii.GameCenter.casino.model.Player;
import com.donii.GameCenter.casino.repository.UserRepository;

public class AuthService {
    private final UserRepository userRepository;

    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Player registerPlayer(String username, String password) {
        if(userRepository.findByName(username) != null) {
            System.out.println(Text.RED + "User already exists" + Text.RESET);
        }

        Player player = new Player(0, username, password);
        userRepository.savePlayer(player);
        return player;
    }

    public Player loginPlayer(String username, String password) {
        if(userRepository.findByName(username) == null) {
            System.out.println(Text.RED + "User not found" + Text.RESET);
        }
        return password.equals(userRepository.getPassword(username)) ? userRepository.findByName(username) : null;
    }
}
