package repository;
/*
Репозиторий отвечает за управление хранением данных, включая операции добавления, удаления и получения данных
 */

import model.Book;
import model.User;
import utils.MyList;

public interface UserRepository {

    User addUser(String email, String password);

    boolean isEmailExists(String email);

    User getUserByEmail(String email);

    MyList<User> getAllUsers(); // Метод для получения всех пользователей

    MyList<Book> getBooksByUser(User user); // Метод позволят получать книги для конкретного пользователя

    MyList<Book> getAllBooks(User activeUser);

    void clearAllUsers(); // Метод для удаления всех пользователей из хранилища


}

