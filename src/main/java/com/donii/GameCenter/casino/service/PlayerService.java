package com.donii.GameCenter.casino.service;

import com.donii.GameCenter.casino.Utils.GameCreator;
import com.donii.GameCenter.casino.model.Player;
import com.donii.GameCenter.casino.repository.UserRepository;

import java.util.Scanner;

public class PlayerService {
    private final UserRepository userRepository;
    public PlayerService(UserRepository ur) {
        this.userRepository = ur;
    }

    public void playGame(Player player, Scanner scanner, GameCreator gameCreator) {
        gameCreator.showHeader(gameCreator.getName());

        if(player.getBalance() <= 0) {
            System.out.println("Мало денег на балансе");
            return;
        }

        System.out.print("Ваша ставка: ");
        int bet = scanner.nextInt();
        if(player.withdraw(bet)) {
            int coefficient = gameCreator.play(scanner);
            if(coefficient > 0){
                int win = bet * coefficient;
                System.out.println("Победа! Выигрыш: " + win);
                player.deposit(win);
            }
            else {
                System.out.println("Проигрыш((((");
            }
            //
            userRepository.updatePlayer(player);
        }
    }
}