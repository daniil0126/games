package com.donii.GameCenter.casino;

import com.donii.GameCenter.casino.Utils.GameCreator;
import com.donii.GameCenter.casino.Utils.GameType;
import com.donii.GameCenter.casino.Utils.Menu;
import com.donii.GameCenter.casino.model.Player;
import com.donii.GameCenter.casino.repository.DatabaseHandler;
import com.donii.GameCenter.casino.repository.SqlUserRepository;
import com.donii.GameCenter.casino.repository.UserRepository;
import com.donii.GameCenter.casino.service.AuthService;
import com.donii.GameCenter.casino.service.PlayerService;

import java.util.Scanner;

public class CasinoApp {
    private final Scanner scanner;
    private Player player = null;
    private final PlayerService room;
    private final Menu menu;
    private final UserRepository userRepository;
    private final AuthService authService;

    public CasinoApp() {
        this.scanner = new Scanner(System.in);
        DatabaseHandler dbHandler = new DatabaseHandler();
        this.userRepository = new SqlUserRepository(dbHandler);
        this.authService = new AuthService(userRepository);
        System.out.println("Подключение к базе данных...");
        dbHandler.createNewTable();
        System.out.println("Таблица 'casino_players' успешно проверена/создана");
        String[] menuOptions = {"Пополнить", "Играть", "Выход"};
        this.menu = new Menu(menuOptions, this.scanner);
        this.room = new PlayerService(userRepository);
    }

    public void run(){
        player = authorization();
        System.out.println("Ваш баланс: " + userRepository.getBalance(player) + "\n");
        System.out.println("Имя игрока: " + player.getUsername() + "\n");
        while(true){
            System.out.println("=== МЕНЮ ДЕЙСТВИЙ ===");
            menu.showMenu();
            int choice = scanner.nextInt();
            handleMenuChoice(choice);
        }
    }

    public Player authorization(){
        System.out.println("------ Авторизация / Регистрация ------");
        System.out.println(
                "1. Зарегистрироваться\n" +
                "2. Войти\n" +
                "3. Выйти");
        System.out.print("Выбери действие: ");
        int choice = scanner.nextInt();
        switch(choice){
            case 1 -> {
                try{
                    System.out.print("Введите имя пользователя: ");
                    String username = scanner.next();
                    System.out.print("Придумайте пароль: ");
                    String password = scanner.next();
                    player = authService.registerPlayer(username, password);

                    System.out.println("Пользователь " + username + " успешно создан!");
                } catch(Exception e){
                    System.out.println("Возникли ошибки при создании игрока: " + e.getMessage());
                }
            }
            case 2 -> {
                try {
                    System.out.print("Введите имя пользователя: ");
                    String username = scanner.next();
                    System.out.print("Введите пароль: ");
                    String password = scanner.next();
                    player = authService.loginPlayer(username, password);

                    System.out.println("Привет снова, " + username + "!");
                } catch (Exception e) {
                    System.out.println("Возникли ошибки при авторизации игрока: " + e.getMessage());
                }
            }
            case  3 -> {
                System.exit(0);
            }
            default -> {
                return null;
            }
        }
        return player;
    }

    private void handleMenuChoice(int choice){
        switch(choice){
            case 1:
                deposit();
                userRepository.updatePlayer(player);
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

    private int deposit(){
        int amount = 0;
        System.out.print("Впиши сумму пополнения: ");
        if(this.scanner.hasNextInt()){
            amount = this.scanner.nextInt();
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
        return amount;
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
