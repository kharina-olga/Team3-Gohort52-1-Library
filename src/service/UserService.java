package service;
/*
Сервис (UserService) обычно отвечает за бизнес-логику приложения,
такую как регистрация, аутентификация и взаимодействие пользователей с приложением.
Однако сервис не должен управлять основными операциями удаления всех записей из хранилища — это задача уровня репозитория.
 */

import model.Book;
import model.User;
import utils.MyList;

public interface UserService {

    User registerUser(String email, String password);

    MyList<User> getUsers(String email);

    User getUser(String email);

    boolean loginUser(String email, String password);


    MyList<Book> getUsersBooks(String title);


    MyList<Book> getAllBooksByUser(User user);

    void logout();

}

