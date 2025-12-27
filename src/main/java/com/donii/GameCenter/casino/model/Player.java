package com.donii.GameCenter.casino.model;

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
        if(amount < 0){
            System.out.println("Нельзя вывести отрицательное значение");
            return false;
        }
        if(this.balance >= amount){
            System.out.println("Success!");
            this.balance -= amount;
            return true;
        }
        else {
            System.out.println("Недостаточно средств");
            return false;
        }
    }

}
