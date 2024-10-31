package view;

import service.LibraryService;
import service.UserService;

import java.util.Scanner;

public class Menu {
    private final UserService service;
    private final LibraryService serviceBook;
    private final Scanner scanner = new Scanner(System.in);

    public Menu(UserService service, LibraryService serviceBook) {
        this.service = service;
        this.serviceBook = serviceBook;
    }

    public void run() {
        showMenu();
    }

    private void showMenu() {
        while (true) {
            System.out.println(Color.GREEN + "Добро пожаловать в библиотеку" + Color.RESET);
            System.out.println("1. Меню библиотеки");
            System.out.println("2. Меню пользователей");
            System.out.println("3. Меню администратора");
            System.out.println("0. Выход из системы");
            System.out.println(Color.YELLOW + "\nВведите пункт меню:" + Color.RESET);

            int choice = getInt();
            scanner.nextLine();

            if (choice == 0) {
                System.out.println("До свидания!");
                // Завершить работу приложения
                System.exit(0);
            }

            showSubMenu(choice);
        }
    }

    private void showSubMenu(int choice) {
        switch (choice) {
            case 1:
                showBookMenu();
                break;
            case 2:
                showUserMenu();
                break;
            case 3:
                 showAdminMenu();
                break;
            default:
                System.out.println(Color.RED + "Сделайте корректный выбор\n" + Color.RESET);
        }
    }


    private void showBookMenu() {
        while (true) {
            System.out.println(Color.GREEN + "Меню библиотеки" + Color.RESET);
            System.out.println("1. Список всех книг");
            System.out.println("2. Поиск книги по названию");
            System.out.println("3. Список всех свободных книг");
            System.out.println("4. Список всех книг, находящихся сейчас у читателей");
            //user
            System.out.println("5. Взятие книги из библиотеки");
            System.out.println("6. Возврат книги в библиотеку");
            //admin
            System.out.println("7. Добавление книги");
            System.out.println("8. Удаление книги");

            System.out.println("0. Вернуться в предыдущее меню");
            System.out.println("\nСделайте выбор пункта меню");
            int input = getInt();
            scanner.nextLine();
            // прервать текущий цикл
            if (input == 0) break;

            cubBookMenu(input);

        }
    }

    private void showUserMenu() {
        while (true) {
            System.out.println(Color.GREEN + "Меню пользователя" + Color.RESET);
            System.out.println("1. Вход в систему");
            System.out.println("2. Регистрация нового пользователя");
            System.out.println("3. Logout");
            System.out.println("0. Вернуться в предыдущее меню");

            System.out.println("\nСделайте выбор пункта меню");
            int input = getInt();
            scanner.nextLine();
            if (input == 0) break;

            handleUserMenuChoice(input);

        }
    }

    private void showAdminMenu() {
        while (true) {
            System.out.println(Color.GREEN + "Меню администратора" + Color.RESET);
            System.out.println("1. Вход в систему");
            System.out.println("2. Регистрация нового администратора");
            System.out.println("3. Logout");
            System.out.println("4. Редактирование прав доступа у пользователей");
            System.out.println("5. Список книг у пользователей и Дата, когда была книга взята");

            System.out.println("0. Вернуться в предыдущее меню");

            System.out.println("\nСделайте выбор пункта меню");
            int input = getInt();
            scanner.nextLine();
            if (input == 0) break;

            cubAdminMenu(input);

        }
    }

    private void cubBookMenu(int input) {
        switch (input) {
            case 1:
                System.out.println("1. Список всех книг");
                //todo
                waitRead();
                break;
            case 2:
                System.out.println("Поиск книги по названию");
                //todo
                waitRead();
                break;
            case 3:
                System.out.println(" Список всех свободных книг");
                //todo
                waitRead();
                break;
            case 4:
                System.out.println(" Список всех книг, находящихся сейчас у читателей");
                //todo
                waitRead();
                break;
            case 5:
                System.out.println(" Взятие книги из библиотеки");
                //todo
                waitRead();
                break;
            case 6:
                System.out.println(" Возврат книги в библиотеку");
                //todo
                waitRead();
                break;
            case 7:
                System.out.println(" Добавление книги");
                //todo
                waitRead();
                break;
            case 8:
                System.out.println(" Удаление книги");
                //todo
                waitRead();
                break;
            default:
                System.out.println("\nНе верный ввод");
        }
    }

    private void handleUserMenuChoice(int input) {
        switch (input) {
            case 1:
                //Авторизацию
                //Todo написать авторизацию
                System.out.println("Введите email:");
                String email = scanner.nextLine();

                System.out.println("Введите пароль");
                String password = scanner.nextLine();
                waitRead();
                break;
            case 2:
                // Регистрацию
                System.out.println("Регистрация нового пользователя");
                System.out.println("Введите email:");
                String email1 = scanner.nextLine();

                System.out.println("Введите пароль");
                String password1 = scanner.nextLine();

                //User user = service.registerUser(email, password);

//                if (user != null) {
//                    System.out.println("Вы успешно зарегистрировались в системе");
//                } else {
//                    System.out.println("Регистрация провалена!");
//                }

                waitRead();

                break;
            case 3:
                System.out.println("logout");
                //service.logout();
                System.out.println("Вы вышли из системы");
                waitRead();
                break;
            default:
                System.out.println("\nНе верный ввод");
        }
    }

    private void cubAdminMenu(int input) {
        switch (input) {
            case 1:
                //Авторизацию
                //Todo написать авторизацию
                System.out.println("Введите email:");
                String email = scanner.nextLine();

                System.out.println("Введите пароль");
                String password = scanner.nextLine();
                waitRead();
                break;
            case 2:
                // Регистрацию
                System.out.println("Регистрация нового администратора");
                System.out.println("Введите email:");
                String email1 = scanner.nextLine();

                System.out.println("Введите пароль");
                String password1 = scanner.nextLine();

                //User user = service.registerUser(email, password);

//                if (user != null) {
//                    System.out.println("Вы успешно зарегистрировались в системе");
//                } else {
//                    System.out.println("Регистрация провалена!");
//                }

                waitRead();

                break;
            case 3:
                System.out.println("logout");
                //service.logout();
                System.out.println("Вы вышли из системы");
                waitRead();
                break;
            case 4:
                System.out.println(" Редактирование прав доступа у пользователей");
                //todo
                waitRead();
                break;
            case 5:
                System.out.println(" Список книг у пользователей и Дата, когда была книга взята");
                //todo
                waitRead();
                break;
            default:
                System.out.println("\nНе верный ввод");

        }
    }

    private void waitRead() {
        System.out.println(Color.YELLOW + "\nДля продолжения нажмите Enter..." + Color.RESET);
        scanner.nextLine();
    }

    public int getInt() {

        int num;
        if (scanner.hasNextInt()) {

            num = scanner.nextInt();
        } else {

            System.out.println(Color.RED + "Вы допустили ошибку при вводе. Попробуйте еще раз." + Color.RESET);
            scanner.next();//рекурсия
            //showMenu();
            num = getInt();
        }
        return num;
    }
}
