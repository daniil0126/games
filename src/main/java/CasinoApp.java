import Entity.Player;
import Utils.*;

import java.util.Scanner;

public class CasinoApp {
    private final Scanner scanner;
    private final Player player;
    private final CasinoRoom room;
//    private final SaveProgress saveProgress;
    private final FileSaver casinoSaver;
    private final Menu menu;
    private final String[] menuOptions = {"Пополнить", "Играть", "Выход"};

    public CasinoApp() {
        this.scanner = new Scanner(System.in);
        this.casinoSaver = new FileSaver("casino");
        this.player = new Player(casinoSaver.get());
        this.menu = new Menu(menuOptions, this.scanner);
        this.room = new CasinoRoom(casinoSaver);
    }

    public void run(){
        System.out.println("Ваш баланс: " + player.getBalance() + "\n");
        while(true){
            System.out.println("=== МЕНЮ ДЕЙСТВИЙ ===");
            menu.showMenu();
            int choice = scanner.nextInt();
            handleMenuChoice(choice);
        }
    }

    private void handleMenuChoice(int choice){
        switch(choice){
            case 1:
                deposit();
                casinoSaver.save(player.getBalance());
                break;
            case 2:
                chooseAndPlayGame();
                break;
            case 3:
                System.out.println("Выход...");
                System.exit(0);
            default:
                System.out.println("Непредвиденный ввод");
                break;
        }
    }

    private void deposit(){
        System.out.print("Впиши сумму пополнения: ");
        if(this.scanner.hasNextInt()){
            int amount = this.scanner.nextInt();
            if(amount > 0 && amount<100000){
                player.deposit(amount);
                System.out.println("Баланс пополнен на " + amount);
            } else {
                System.out.println("Invalid data: Некорректная сумма");
            }

        } else {
            System.out.println("Invalid data: Введено не число");
            scanner.next();
        }
    }

    private void chooseAndPlayGame(){
        System.out.println("----ВЫБОР ИГРЫ----");
        GameType[] availableGames = GameType.values();
        for(int i = 0; i < availableGames.length; i++){
            System.out.println((i + 1) + ". " + availableGames[i].getTitle());
        }
        System.out.print("Выберите игру: ");
        int choice = scanner.nextInt();
        if(choice < 1 || choice > availableGames.length){
            System.out.println("Ошибка при выборе игры");
            return;
        }

        GameType selectedGameType = availableGames[choice-1];
        GameCreator gameCreator = selectedGameType.createGame();
        this.room.playGame(player, scanner, gameCreator);
    }
}
