package service;

import com.sun.source.tree.UsesTree;
import model.Book;
import model.Role;
import model.User;
import repository.BookRepository;
import repository.UserRepository;
import utils.MyArrayList;
import utils.MyList;

public class LibraryImpl implements LibraryService {

    private final BookRepository bookRepository;
    private final UserRepository userRepository;

    private User activeUser;

    public LibraryImpl(BookRepository bookRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userRepository = userRepository;
    }

    //вернуть книгу по автору
    @Override
    public Book getByAuthor(String author) {
        MyList<Book> allBooks = bookRepository.getAllBooks();
        if(author!=null) {
            for (Book book : allBooks) {
                if (book.getAuthor().equals(author)) {
                    return book;
                }
            }
        }
        return null;
    }

    // Метод, который принимает данные от пользователя из view (title, author, year) в репо
    @Override
    public Book addBook(String title, String author, int publicationYear) {
        // Проверка роли текущего пользователя
        if (activeUser == null || activeUser.getRole() != Role.ADMIN) {
            System.out.println("Добавление новой книги доступно только Администраторам");
            // возвращать null
            return null;
        }

        if (title != null && author != null && publicationYear != 0) {
           Book book = bookRepository.addBook(title,author,publicationYear);
           return book;
        } else {
            System.out.println("Некорректные данные для добавления книги");
            return null;

        }
    }

    // Возвращаем все книги из репозитория
    @Override
    public MyList<Book> getAllBooks() {
        return bookRepository.getAllBooks();

    }

    // Получаем все книги из репозитория
    @Override
    public MyList<Book> getAllFreeBooks() {
        MyList<Book> allBooks = bookRepository.getAllBooks();
        MyList<Book> freeBooks = new MyArrayList<>();

        // Проходим по каждой книге и добавляем только доступные книги в новый список
        for (Book book : allBooks) {
            if (book.isAvailable()) {  // Проверяем, доступна ли книга
                freeBooks.add(book);
            }
        }

        return freeBooks; // Возвращаем список свободных книг

    }

    //возвращает список всех одолженных на данный момент книг
    @Override
    public MyList<Book> getAllBorrowedBooks() {
        MyList<Book> borrowedBooks = null;
        MyList<Book> allBooks = bookRepository.getAllBooks();
        for (Book book: allBooks) {
            if (!book.isAvailable()) {
                borrowedBooks.add(book);
            }
        }
        return borrowedBooks;
    }

    //одолжить книгу
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
        if (id != 0 && book.isAvailable()) {
            activeUser.getUserBooks().add(book);
            book.setAvailable(false);
            return true;
        }

        return false;
    }

    //вернуть книгу
    @Override
    public boolean returnBook(int id) {
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
        if(id!=0 && activeUser.getUserBooks().contains(book)) {
            activeUser.getUserBooks().remove(book);
            book.setAvailable(true);
            return true;
        }
        return false;
    }

    @Override
    public void editBook() {

    }
    @Override
    public void setActiveUser(User user) {
        this.activeUser = user; // Устанавливаем переданного пользователя как активного
    }


}
