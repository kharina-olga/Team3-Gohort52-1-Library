package service;

import model.Book;
import model.User;
import repository.UserRepository;
import utils.MyArrayList;
import utils.MyList;
import utils.PersonValidator;

public class UserImpl implements UserService{

    private final UserRepository repositoryUser;
    private User activeUser; // Хранит текущего авторизованного пользователя, показывает, кто в данный момент авторизован


    public UserImpl(UserRepository repositoryUser) {
        this.repositoryUser = repositoryUser;
    }

    @Override
    public User registerUser(String email, String password) {
        if (!PersonValidator.isEmailValid(email)) {
            System.out.println("Email не прошел проверку!");
            return null;
        }

        if (!PersonValidator.isPasswordValid(password)) {
            System.out.println("Пароль не прошел валидацию!");
            return null;
        }

        if (repositoryUser.isEmailExists(email)) {
            System.out.println("Пользователь с таким адресом электронной почты уже существует!");
            return null;
        }

        return repositoryUser.addUser(email, password); // Добавляем нового пользователя
    }





    @Override
    public boolean loginUser(String email, String password) {
        User user = repositoryUser.getUserByEmail(email);

        if (user == null) {
            System.out.println("Неверный адрес электронной почты!");
            return false;
        }

        if (!user.getPassword().equals(password)) {
            System.out.println("Неверный пароль!");
            return false;
        }

        activeUser = user; // Сохранение текущего пользователя
        return true;
    }

    @Override
    public MyList<User> getUsers(String email) {
        MyList<User> allUsers = repositoryUser.getAllUsers(); // Получаем всех пользователей
        MyList<User> filteredUsers = new MyArrayList<>(); // Создаем новый список для отфильтрованных пользователей

        for (User user : allUsers) {
            if (user.getEmail().equalsIgnoreCase(email)) { // Сравниваем с учетом регистра
                filteredUsers.add(user); // Добавляем подходящего пользователя в новый список
            }
        }

        return filteredUsers; // Возвращаем отфильтрованный список
    }

    @Override
    public User getUser(String email) {
        return null;
    }


    @Override
    public MyList<Book> getUsersBooks(String title) {
        if (activeUser == null) {
            System.out.println("Пользователь не авторизован!");
            return new MyArrayList<>(); // Возвращаем пустой список, если пользователь не авторизован
        }
        return repositoryUser.getBooksByUser(activeUser);
    }

    //вывести список всех книг пользователя
    @Override
    public MyList<Book> getAllBooksByUser(User user) {
        if (user== null) {
            System.out.println("Пользователь не авторизован!");
            return new MyArrayList<>(); // Возвращаем пустой список, если пользователь не авторизован
        }
        return repositoryUser.getAllBooks(user);
    }



    @Override
    public void logout() {
        activeUser = null; // Очищаем активного пользователя
    }


}
