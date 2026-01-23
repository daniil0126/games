package com.donii.GameCenter.casino.service;

import com.donii.GameCenter.casino.Exceptions.InsufficientFundsException;
import com.donii.GameCenter.casino.Utils.GameCreator;
import com.donii.GameCenter.casino.Utils.InputUtils;
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
            throw new InsufficientFundsException("Мало денег на балансе");
        }
        int bet = bet(scanner);
        if(player.withdraw(bet) && bet > 0) {
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

    private int bet(Scanner scanner) {
        int MIN_BET = 100;
        int MAX_BET = 999999;

        return InputUtils.safeInput(() -> {
            System.out.print("Ваша ставка: ");
            return InputUtils.numberChoice(MIN_BET, MAX_BET, scanner);
        });
    }

}