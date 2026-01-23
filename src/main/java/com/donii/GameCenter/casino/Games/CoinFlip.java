package com.donii.GameCenter.casino.Games;

import com.donii.GameCenter.casino.Utils.GameCreator;
import com.donii.GameCenter.casino.Utils.InputUtils;
import com.donii.GameCenter.casino.Utils.Text;

import java.util.Scanner;

public class CoinFlip extends GameCreator {
    public CoinFlip() {
        super("CoinFlip");
    }

    public int play(Scanner scanner){
        int first = 1;
        int second = 2;

        int choice = InputUtils.safeInput(() -> {
            System.out.println(Text.BLUE + "1. Орел\n2. Решка" + Text.RESET);
            System.out.print("Сделайте выбор: ");
            return InputUtils.numberChoice(first, second, scanner);
        });
        int player = choice == 1 ? first : second;

        int coin = (Math.random() < 0.5) ? first : second;
        System.out.println("Выпал" + ((coin == first) ? " Орел" : "a Решка"));
        return coin == player ? 2 : 0;
    }
}
