package com.donii.GameCenter.numbers;

import com.donii.GameCenter.numbers.Interfaces.GameInterface;

import java.util.Scanner;
public class GuessNumber implements GameInterface {
    private static final int EASY_MODE = 7;
    private static final int MEDIUM_MODE = 5;
    private static final int HARD_MODE = 3;

    private final Scanner scanner = new Scanner(System.in);

    private void guessingProcess(int maxNumber, int mode){
        int randomNumber = (int)(Math.random() * maxNumber + 1);
        int userInput = 0;
        int attempts = 0;

        while (userInput != randomNumber) {
            attempts++;
            System.out.print("Впишите число: ");
            userInput = scanner.nextInt();

            if(isAttemptsLimitIsExhausted(mode, attempts)){
                System.out.println("Лимит попыток исчерпан. Вы проиграли, загаданное число: " + randomNumber);
                return;
            }

            if(userInput == randomNumber) {
                System.out.println("Поздравляю! Угаданное число: " + randomNumber);
                System.out.println(
                        attempts == 1 ? "Вы угадали число с первой попытки!" :
                                "Было потрачено попыток: " + attempts);

            }
            else if (userInput > randomNumber) {
                System.out.println("Меньше. Попыток осталось: " + attemptsLeft(mode, attempts) );
            } else {
                System.out.println("Больше. Попыток осталось: " + attemptsLeft(mode, attempts) );
            }
        }
    }

    private boolean isAttemptsLimitIsExhausted(int mode, int attempts) {
        boolean isWon;

        switch (mode){
            case 1 -> {isWon = attempts >= 7;}
            case 2 -> {isWon = attempts >= 5;}
            case 3 -> {isWon = attempts >= 3;}
            default -> {return false;}
        }

        return isWon;
    }

    private int attemptsLeft(int mode, int attempts) {
        return mode == 1 ?
                EASY_MODE - attempts :
                mode == 2 ?
                MEDIUM_MODE - attempts :
                mode == 3 ?
                HARD_MODE - attempts : 0;
    }

    public void play() {
        String answer;
        do {
            System.out.println("ИГРА: УГАДАЙ ЧИСЛО\n\nРежимы:\n" +
                    "1. Легкий (от 1 до 10, 7 попыток)\n" +
                    "2. Средний (от 1 до 100, 5 попыток)\n" +
                    "3. Сложный (от 1 до 1000, 3 попытки!)\n"
            );
            System.out.print("Выбери режим: ");
            int mode = scanner.nextInt();
            int maxNumber = 0;

            switch (mode) {
                case 1 -> maxNumber = 10;
                case 2 -> maxNumber = 100;
                case 3 -> maxNumber = 1000;
                default -> System.out.println("Нет такого режима");
            }

            guessingProcess(maxNumber, mode);
            System.out.println("Сыграть еще раз? (Да/Нет)");
            answer = scanner.next();

        } while(answer.equalsIgnoreCase("да"));
    }
}

