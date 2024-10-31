package service;

import model.Book;
import model.User;
import utils.MyList;

public interface UserService {

    User registerUser(String email, String password);

    MyList<User> getUsers(String email);

    User getUser(String email);

    boolean loginUser(String email, String password);

    // Метод для установки активного пользователя, без аутентификации
    void setActiveUser(User user);

    // Показывает Возвращает пользователя, который залогинен в текущий момент
    User getActiveUser();

    MyList<Book> getUsersBooks(String title);


    MyList<Book> getAllBooksByUser(User user);

    void logout();

}

