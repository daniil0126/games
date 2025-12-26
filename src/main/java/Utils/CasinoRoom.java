package Utils;

import Entity.Player;

import java.util.Scanner;

public class CasinoRoom {
    private FileSaver casinoSaver;

    public CasinoRoom(FileSaver casinoSaver) {
        this.casinoSaver = casinoSaver;
    }

    public void playGame(Player player, Scanner scanner, GameCreator gameCreator) {
        gameCreator.showHeader(gameCreator.getName());

        if(player.getBalance() <= 0) {
            System.out.println("Мало денег на балансе");
            return;
        }

        System.out.print("Ваша ставка: ");
        int bet = scanner.nextInt();
        if(player.withdraw(bet)) {
            int coefficient = gameCreator.play(scanner);
            if(coefficient > 0){
                int win = bet * coefficient;
                System.out.println("Победа! Выигрыш: " + win);
                player.deposit(win);
            }
            else {
                System.out.println("Проигрыш((((");
            }
            casinoSaver.save(player.getBalance());
        }
    }
}
