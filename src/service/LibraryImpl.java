package service;

import model.Book;
import model.Role;
import model.User;
import repository.BookRepository;
import repository.UserRepository;
import utils.MyList;

public class LibraryImpl implements LibraryService{

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    private MyList<Book> books;

    private User activeUser;
    public LibraryImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository=bookRepository;
        this.userRepository=userRepository;
    }

    @Override
    public void getByAuthor() {
        System.out.println("Test");
    }

    @Override
    public void addBook(int id, String title, String author) {
        // Проверка роли текущего пользователя
        if (activeUser == null || activeUser.getRole() != Role.ADMIN) {
            System.out.println("Добавление нового автомобиля доступно только Администраторам");
            return;
        }
        if (title!=null && author!=null) {
            Book book=new Book(id, title, author);
            books.add(book);
        }
    }

    @Override
    public MyList<Book> getAllBooks() {

        return null;
    }

    @Override
    public MyList<Book> getAllFreeBooks() {

        return null;
    }

    @Override
    public MyList<Book> getAllBorrowedBooks() {

        return null;
    }

    @Override
    public boolean borrowBook(int id) {
        // Проверка, что пользователь авторизирован в системе
        if (activeUser == null) {
            System.out.println("Выполните вход в систему");
            return false;
        }

        if (activeUser.getRole() == Role.BLOCKED) {
            System.out.println("Ваш аккаунт заблокирован! Обратитесь в службу поддержки");
            return false;
        }

        Book book = bookRepository.getBookById(id);
        if(id!=0 && book.isAvailable()) {
            activeUser.getUserBooks().add(book);
            return false;
        }

        return true;
    }

    @Override
    public void returnBook() {

    }

    @Override
    public void editBook() {

    }
}
