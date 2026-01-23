package com.donii.GameCenter.casino.Utils;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Supplier;

public class InputUtils {
    public static <T> T safeInput(Supplier<T> action){
        while(true){
            try{
                return action.get();
            } catch (Exception e) {
                System.out.println("\n"+Text.red("Что то пошло не так: " + e.getMessage()));
            }
        }
    }

    public static int numberChoice(int firstRange, int secondRange, Scanner scanner){
        String input = scanner.next();
        int val = Integer.parseInt(input);

        if(val < firstRange || val > secondRange){
            throw new InputMismatchException();
        }
        return val;
    }
}
