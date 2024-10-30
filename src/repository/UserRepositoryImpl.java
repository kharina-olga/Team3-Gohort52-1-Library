package repository;

import model.Book;
import model.Role;
import model.User;
import utils.MyArrayList;
import utils.MyList;

import java.util.concurrent.atomic.AtomicInteger;

public class UserRepositoryImpl implements UserRepository {

    private final MyList<User> users;
    private final AtomicInteger usersCount = new AtomicInteger(1);
    private final BookRepository bookRepository;

    public UserRepositoryImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        users = new MyArrayList<>();
        addUsers();
    }

    private void addUsers() {
        User admin = new User("1", "1");
        admin.setRole(Role.ADMIN);

        User blocked = new User("2", "2");
        blocked.setRole(Role.BLOCKED);
        users.addAll(
                admin,
                blocked,
                new User("user@email.com", "qwerty1Q!")
        );
    }

    @Override
    public User addUser(String email, String password) {
        User user = new User(email, password);
        users.add(user);
        return user;
    }

    @Override
    public boolean isEmailExists(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) return true;
        }
        return false;
    }

    @Override
    public User getUserByEmail(String email) {
        for (User user : users) {
            if (user.getEmail().equals(email)) return user;
        }
        return null;
    }

    @Override
    public MyList<User> getAllUsers() {
        return users;
    }

    // Метод вернет список книг, взятых пользователем
    @Override
    public MyList<Book> getBooksByUser(User user) {
        MyList<Book> userBooks = new MyArrayList<>();

        for (Book book : bookRepository.getBorrowedBooks()) {
            if (book.getBorrowedBy() != null && book.getBorrowedBy().equals(user)) {
                userBooks.add(book);
            }
        }
        return userBooks;
    }

    @Override
    public MyList<Book> getAllBooks(User activeUser) {
        return null;
    }


}
