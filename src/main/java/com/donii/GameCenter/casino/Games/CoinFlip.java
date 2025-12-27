package com.donii.GameCenter.casino.Games;
import com.donii.GameCenter.casino.Utils.GameCreator;

import java.util.Scanner;

public class CoinFlip extends GameCreator {
    public CoinFlip() {
        super("CoinFlip");
    }

    public int play(Scanner scanner){
        System.out.println("Орел/Решка");
        System.out.print("Сделайте выбор: ");
        String choice = scanner.next();
        int player =
                choice.equalsIgnoreCase("орел") ||
                choice.equalsIgnoreCase("орёл") ? 0 : 1;

        int coin = (Math.random() < 0.5) ? 0 : 1;
        System.out.println("Выпал" + ((coin == 0) ? " Орел" : "a Решка"));
        return coin == player ? 5 : 0;
    }
}
