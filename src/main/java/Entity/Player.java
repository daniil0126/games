package Entity;

public class Player {
    private int balance;

    public Player(int startBalance) {
        this.balance = startBalance;
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
