package service.tests;

import model.Book;
import model.Role;
import model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repository.BookRepositoryImpl;
import service.LibraryImpl;
import utils.MyList;

import static org.junit.jupiter.api.Assertions.*;

class LibraryImplTests {

    private LibraryImpl libraryService;
    private BookRepositoryImpl bookRepository;
    private User adminUser;
    private User regularUser;
    private User blockedUser;

    // Переменные для email и паролей
    private static final String ADMIN_EMAIL = "admin@example.com";
    private static final String ADMIN_PASSWORD = "adminPassword";

    private static final String USER_EMAIL = "user@example.com";
    private static final String USER_PASSWORD = "userPassword";

    private static final String BLOCKED_EMAIL = "blocked@example.com";
    private static final String BLOCKED_PASSWORD = "password";

    // Данные для тестовой книги
    private static final String NEW_BOOK_TITLE = "New Book";
    private static final String NEW_BOOK_AUTHOR = "New Author";
    private static final int NEW_BOOK_YEAR = 2022;

    @BeforeEach
    void setUp() {
        bookRepository = new BookRepositoryImpl();
        libraryService = new LibraryImpl(bookRepository, null); // userRepository не используется

        adminUser = new User(ADMIN_EMAIL, ADMIN_PASSWORD, Role.ADMIN);
        regularUser = new User(USER_EMAIL, USER_PASSWORD, Role.USER);
        blockedUser = new User(BLOCKED_EMAIL, BLOCKED_PASSWORD, Role.BLOCKED);
    }

    @Test
    void testGetByAuthor_ValidAuthor() {
        Book book = libraryService.getByAuthor("George Orwell");
        assertNotNull(book, "Книга должна быть найдена для указанного автора");
        assertEquals("1984", book.getTitle(), "Название книги должно соответствовать автору");
    }

    @Test
    void testGetByAuthor_InvalidAuthor() {
        Book book = libraryService.getByAuthor("Nonexistent Author");
        assertNull(book, "Метод должен вернуть null, если автор не найден");
    }

    @Test
    void testAddBook_AsAdmin() {
        libraryService.setActiveUser(adminUser);
        Book book = libraryService.addBook(NEW_BOOK_TITLE, NEW_BOOK_AUTHOR, NEW_BOOK_YEAR);
        assertNotNull(book, "Книга должна быть успешно добавлена администратором");
        assertEquals(NEW_BOOK_TITLE, book.getTitle(), "Название книги должно соответствовать добавленному");
    }

    @Test
    void testAddBook_AsRegularUser() {
        libraryService.setActiveUser(regularUser);
        Book book = libraryService.addBook(NEW_BOOK_TITLE, NEW_BOOK_AUTHOR, NEW_BOOK_YEAR);
        assertNull(book, "Книга не должна быть добавлена обычным пользователем");
    }

    @Test
    void testGetAllBooks() {
        MyList<Book> allBooks = libraryService.getAllBooks();
        assertEquals(10, allBooks.size(), "Общее количество книг должно соответствовать инициализированному");
    }

    @Test
    void testGetAllFreeBooks() {
        libraryService.setActiveUser(adminUser);
        libraryService.borrowBook(1); // Берем книгу с id 1

        MyList<Book> freeBooks = libraryService.getAllFreeBooks();
        assertEquals(9, freeBooks.size(), "Количество доступных книг должно уменьшиться на одну после выдачи");
    }

    @Test
    void testGetAllBorrowedBooks() {
        libraryService.setActiveUser(adminUser);
        libraryService.borrowBook(1); // Берем книгу с id 1

        MyList<Book> borrowedBooks = libraryService.getAllBorrowedBooks();
        assertEquals(1, borrowedBooks.size(), "Количество выданных книг должно быть равно 1 после одной выдачи");
    }

    @Test
    void testBorrowBook_AsRegularUser() {
        libraryService.setActiveUser(regularUser);
        boolean result = libraryService.borrowBook(1);
        assertTrue(result, "Обычный пользователь должен иметь возможность взять книгу, если он не заблокирован");
    }

    @Test
    void testBorrowBook_AsBlockedUser() {
        libraryService.setActiveUser(blockedUser);
        boolean result = libraryService.borrowBook(1);
        assertFalse(result, "Заблокированный пользователь не должен иметь возможность взять книгу");
    }

    @Test
    void testReturnBook_Success() {
        libraryService.setActiveUser(regularUser);
        libraryService.borrowBook(1); // Берем книгу с id 1

        boolean result = libraryService.returnBook(1);
        assertTrue(result, "Пользователь должен иметь возможность вернуть книгу, которую он взял");
        assertTrue(bookRepository.getBookById(1).isAvailable(), "Книга должна стать доступной после возврата");
    }

    @Test
    void testReturnBook_NotBorrowed() {
        libraryService.setActiveUser(regularUser);
        boolean result = libraryService.returnBook(1);
        assertFalse(result, "Пользователь не может вернуть книгу, которую он не брал");
    }
}
