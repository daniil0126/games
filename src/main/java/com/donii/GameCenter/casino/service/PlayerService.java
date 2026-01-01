package com.donii.GameCenter.casino.service;

import com.donii.GameCenter.casino.Utils.GameCreator;
import com.donii.GameCenter.casino.Utils.Text;
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
            System.out.println(Text.RED + "Мало денег на балансе" + Text.RESET);
            return;
        }

        System.out.print("Ваша ставка: ");
        int bet = scanner.nextInt();
        if(player.withdraw(bet)) {
            int coefficient = gameCreator.play(scanner);
            if(coefficient > 0){
                int win = bet * coefficient;
                System.out.println(Text.GREEN + "Победа! Выигрыш: " + win +  Text.RESET);
                player.deposit(win);
            }
            else {
                System.out.println(Text.RED + "Проигрыш((((" + Text.RESET);
            }
            userRepository.updatePlayer(player);
        }
    }
}