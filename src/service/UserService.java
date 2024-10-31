package service;

import model.Book;
import model.User;
import utils.MyList;

public interface UserService {

    User registerUser(String email, String password);

    MyList<User> getUsers(String email);

    User getUser(String email);

    boolean loginUser(String email, String password);

    User getActiveUser(); // Возвращает пользователя, который залогинен в текущий момент

    MyList<Book> getUsersBooks(String title);


    MyList<Book> getAllBooksByUser(User user);

    void logout();

}

