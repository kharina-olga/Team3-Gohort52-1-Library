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
    /**
     * Регистрирует нового пользователя с указанным email и паролем.
     *
     * @param email
     * @param password
     * @return
     */
    User registerUser(String email, String password);

    /**
     * Возвращает список пользователей, соответствующих указанному email.
     *
     * @param email
     * @return
     */
    MyList<User> getUsers(String email);

    /**
     * Получает пользователя по его адресу электронной почты.
     *
     * @param email
     * @return
     */
    User getUser(String email);


    /**
     * Выполняет аутентификацию пользователя с указанным email и паролем.
     *
     * @param email
     * @param password
     * @return
     */
    boolean loginUser(String email, String password);

    // Метод для установки активного пользователя, без аутентификации
    void setActiveUser(User user);

    // Показывает Возвращает пользователя, который залогинен в текущий момент
    User getActiveUser();

    /**
     * Получает список книг активного пользователя по указанному заголовку.
     *
     * @param title
     * @return
     */
    MyList<Book> getUsersBooks(String title);


    /**
     * Получает все книги, которые есть у текущего активного пользователя.
     *
     * @param user
     * @return
     */
    MyList<Book> getAllBooksByUser(User user);

    /**
     * Выходит из системы, очищая информацию о текущем пользователе.
     */
    void logout();

}

