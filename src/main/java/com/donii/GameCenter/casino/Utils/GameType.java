package com.donii.GameCenter.casino.Utils;

import com.donii.GameCenter.casino.Games.CoinFlip;
import com.donii.GameCenter.casino.Games.SlotMachine;

public enum GameType {
    SLOTS("Слоты"){
        @Override
        public GameCreator createGame(){
            String[] symbols = {"🍒", "🍌", "🍋"};
            return new SlotMachine(symbols);
        }
    },
    COIN_FLIP("Орел и Решка"){
        @Override
        public GameCreator createGame(){
            return new CoinFlip();
        }
    };

    private final String title;

    GameType(String title){
        this.title = title;
    }

    public String getTitle(){
        return title;
    }

    public abstract GameCreator createGame();
}
