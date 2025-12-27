package com.donii.GameCenter.casino.Games;

import com.donii.GameCenter.casino.Utils.GameCreator;

import java.util.Scanner;

public class SlotMachine extends GameCreator {
    private final String[] symbols;

    public SlotMachine(String[] symbols) {
        super("Однорукий бандит");
        this.symbols = symbols;
    }

    @Override
    public int play(Scanner scanner){
        String[] result = new String[this.symbols.length];
        System.out.println("Ваши слоты: ");
        for(int i = 0; i < symbols.length; i++){
            int randomSlot = (int)(Math.random() * symbols.length);
            result[i] = symbols[randomSlot];
            System.out.print(result[i] + " ");
        }

        if(result[0].equals(result[1]) && result[1].equals(result[2])) {
            return 5;
        }

        return 0;
    }
}
