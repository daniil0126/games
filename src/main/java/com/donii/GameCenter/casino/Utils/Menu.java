package com.donii.GameCenter.casino.Utils;

import java.util.Scanner;

public class Menu {
    private final Scanner scanner;
    private final String[] menuOptions;

    public Menu(String[] menuOptions, Scanner scanner){
        this.menuOptions = menuOptions;
        this.scanner = scanner;
    }

    public void showMenu(){
        int i = 1;
        for(String option : menuOptions){
            System.out.println(i + " - " + option);
            i++;
        }
        System.out.print("Выберите действие: ");
    }

    public int getChoice(){
        return scanner.nextInt();
    }
}
