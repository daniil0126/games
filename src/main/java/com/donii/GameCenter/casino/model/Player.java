package com.donii.GameCenter.casino.model;

import com.donii.GameCenter.casino.Exceptions.InsufficientFundsException;
import com.donii.GameCenter.casino.Utils.Text;

public class Player {
    private int balance;
    private final String username;
    private final String password;

    public Player(int startBalance,  String username, String password) {
        this.balance = startBalance;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public int getBalance() {
        return balance;
    }

    public void deposit(int amount){
        this.balance += amount;
    }

    public boolean withdraw(int amount){
        int MIN_BET = 100;
        if(amount < 0){
            throw new IllegalArgumentException("Сумма не может быть отрицательной");
        }
        else if(this.balance >= amount && amount >= MIN_BET){
            this.balance -= amount;
            return true;
        }
        else if(this.balance < MIN_BET) {
            throw new InsufficientFundsException("Недостаточно средств");
        }
        else {
            System.out.println(Text.red("Непредвиденная ошибка"));
            return false;
        }
    }

}
