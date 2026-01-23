package com.donii.GameCenter.casino;

import com.donii.GameCenter.casino.Exceptions.IncorrectInputException;
import com.donii.GameCenter.casino.Utils.*;
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
    private final String[] menuOptions = {"Пополнить", "Играть", "Выход"};

    public CasinoApp() {
        this.scanner = new Scanner(System.in);
        DatabaseHandler dbHandler = new DatabaseHandler();
        this.userRepository = new SqlUserRepository(dbHandler);
        this.authService = new AuthService(userRepository);
        System.out.println(Text.GREEN + "Подключение к базе данных..." + Text.RESET);
        dbHandler.createNewTable();
        System.out.println(Text.GREEN + "Таблица 'casino_players' успешно проверена/создана" + Text.RESET);
        this.menu = new Menu(menuOptions, this.scanner);
        this.room = new PlayerService(userRepository);
    }

    public void run(){
        player = authorization();
        if(player == null){
            return;
        }
        System.out.println("Ваш баланс: " + userRepository.getBalance(player) + "\n");
        System.out.println("Имя игрока: " + player.getUsername() + "\n");

        int firstChoiceRange = 1;
        int secondChoiceRange = menuOptions.length;
        while(true){
            int choice = InputUtils.safeInput(() -> {
                    System.out.println(Text.PURPLE + "=== МЕНЮ ДЕЙСТВИЙ ===" + Text.RESET);
                    menu.showMenu();
                    System.out.print("Выберите действие: ");
                    return InputUtils.numberChoice(firstChoiceRange, secondChoiceRange, scanner);
            });
            handleMenuChoice(choice);
        }
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
        }
    }

    private int deposit(){
        int MAX_DEPOSIT = 1000000;
        int MIN_DEPOSIT = 100;

        int amount = InputUtils.safeInput(() -> {
            System.out.print("Впиши сумму пополнения: ");
            return InputUtils.numberChoice(MIN_DEPOSIT, MAX_DEPOSIT, scanner);
        });
        player.deposit(amount);
        System.out.println(Text.green("Баланс был пополнен на " + amount));

        return amount;
    }

    private Player authorization(){
        System.out.println(Text.CYAN + "------ Авторизация / Регистрация ------" +  Text.RESET);
        System.out.println(
                "1. Зарегистрироваться\n" +
                        "2. Войти\n" +
                        "3. Выйти");
        boolean flag = true;
        int choice = 0;
        while(flag){
            System.out.print("Выбери действие: ");
            String input = scanner.next();
            try {
                choice = Integer.parseInt(input);
                if(choice < 1 || choice > 3){
                    System.out.println("Пошел нахуй");
                } else flag = false;
            } catch (NumberFormatException e) {
                System.out.println("ЗАНОВО НАХУЙ");
            }
        }
        switch(choice){
            case 1 -> {
                while(player == null){
                    try{
                        System.out.print("Введите имя пользователя: ");
                        String username = scanner.next();
                        System.out.print("Придумайте пароль: ");
                        String password = scanner.next();
                        player = authService.registerPlayer(username, password);

                        System.out.println(player != null ? "Пользователь " + username + " успешно создан!" : "");
                    } catch(Exception e){
                        System.out.println("Возникли ошибки при создании игрока: " + e.getMessage());
                    }
                }
            }
            case 2 -> {
                while(player == null){
                    try {
                        String username = "";
                        String password = "";
                        boolean isValid = false;

                        while(!isValid){
                            System.out.print("Введите имя пользователя: ");
                            username = scanner.next();
                            System.out.print("Введите пароль: ");
                            password = scanner.next();
                            player = authService.loginPlayer(username, password);
                            if(player == null){
                                throw new IncorrectInputException("Неверный пароль или имя пользователя");
                            } else {
                                isValid = true;
                            }
                        }
                        System.out.println(Text.CYAN + "Привет снова, " + username + "!" +  Text.RESET);
                    } catch (Exception e) {
                        System.out.println(Text.RED + "Возникли ошибки при авторизации игрока: " + e.getMessage() + Text.RESET);
                    }
                }
            }
            case  3 -> {
                System.exit(0);
            }
            default -> {
                System.out.println("Неверный ввод");
            }
        }
        return player;
    }



    private void chooseAndPlayGame(){
        System.out.println("----ВЫБОР ИГРЫ----");
        GameType[] availableGames = GameType.values();
        for(int i = 0; i < availableGames.length; i++){
            System.out.println((i + 1) + ". " + availableGames[i].getTitle());
        }
        int choice = InputUtils.safeInput(() -> {
            System.out.print("Выберите игру: ");
            return InputUtils.numberChoice(1, availableGames.length, scanner);
        });

        GameType selectedGameType = availableGames[choice-1];
        GameCreator gameCreator = selectedGameType.createGame();
        this.room.playGame(player, scanner, gameCreator);
    }
}

