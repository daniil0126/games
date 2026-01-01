package com.donii.GameCenter.casino.Utils;

import java.util.Scanner;

public abstract class GameCreator {
    private final String name;

    public GameCreator(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public abstract int play(Scanner scanner);

    public void showHeader(String header){
        System.out.println(Text.PURPLE + "=== ИГРА: " + header + " ===" + Text.RESET);
    }

}
