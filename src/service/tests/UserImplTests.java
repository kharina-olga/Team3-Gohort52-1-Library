package service.tests;

import model.Book;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.UserRepository;
import repository.UserRepositoryImpl;
import service.UserImpl;
import utils.MyList;

import static org.junit.jupiter.api.Assertions.*;

class UserImplTests {

    private UserImpl userService;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        userRepository = new UserRepositoryImpl(); // Здесь создайте новый экземпляр UserRepository или используйте DI
        userService = new UserImpl(userRepository);
      //  userRepository.clearAllUsers(); // Очищаем пользователей перед каждым тестом
    }

    @Test
    void testRegisterUser_Success() {
        String validEmail = "user@example.com";
        User user = userService.registerUser(validEmail, "ValidPassword1!");
     //   System.out.println(user);
        assertNotNull(user, "Пользователь должен быть успешно зарегистрирован");
    //    System.out.println(userRepository.isEmailExists(validEmail));
        MyList<User>users = userRepository.getAllUsers();
        System.out.println(users);
        assertTrue(userRepository.isEmailExists(validEmail), "Пользователь должен быть добавлен в репозиторий");
    }
    @Test
    void testRegisterUser_EmailAlreadyExists() {
        userRepository.addUser("user@example.com", "password123");

        User result = userService.registerUser("user@example.com", "AnotherPassword!");
        assertNull(result, "Регистрация должна провалиться, если email уже существует");
    }
    @Test
    void testLoginUser_InvalidPassword() {
        userRepository.addUser("user@example.com", "CorrectPassword1!");
        boolean result = userService.loginUser("user@example.com", "WrongPassword");

        assertFalse(result, "Логин должен провалиться при неверном пароле");
    }
    @Test
    void testGetUser_ReturnsUser() {
        User user = userRepository.addUser("user@example.com", "password");
        User result = userService.getUser("user@example.com");

        assertEquals(user, result, "Метод должен вернуть пользователя с указанным email");
    }
    @Test
    void testGetUser_ReturnsNull() {
        User result = userService.getUser("nonexistent@example.com");

        assertNull(result, "Метод должен вернуть null, если пользователь не найден");
    }
    @Test
    void testGetUsersBooks_UserNotLoggedIn() {
        MyList<Book> result = userService.getUsersBooks("Book Title");

        assertTrue(result.isEmpty(), "Если пользователь не авторизован, должен быть возвращен пустой список");
    }
    @Test
    void testGetAllBooksByUser_UserNotAuthorized() {
        MyList<Book> result = userService.getAllBooksByUser(null);

        assertTrue(result.isEmpty(), "Если пользователь не авторизован, должен быть возвращен пустой список");
    }

    @Test
    void testLoginUser_Success() {
        userRepository.addUser("user@example.com", "ValidPassword1!");
        boolean result = userService.loginUser("user@example.com", "ValidPassword1!");
        assertTrue(result, "Логин должен пройти успешно");
    }

    @Test
    void testGetUsers_FiltersByEmail() {
        userRepository.addUser("user@example.com", "password");
        userRepository.addUser("other@example.com", "password");

        MyList<User> result = userService.getUsers("user@example.com");
        assertEquals(1, result.size(), "Должен быть найден только один пользователь с указанным email");
    }
/*
    @Test
    void testLogout() {
        userRepository.addUser("user@example.com", "password123");
        userService.loginUser("user@example.com", "password123");

        assertNotNull(userService.getActiveUser(), "Пользователь должен быть авторизован перед выходом");

        userService.logout();

        assertNull(userService.getActiveUser(), "После выхода активный пользователь должен быть null");
    }
    }
*/
}
