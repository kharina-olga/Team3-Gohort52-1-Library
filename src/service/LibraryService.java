package service;
import model.Book;
import utils.MyList;

public interface LibraryService {

    void getByAuthor();

    void addBook(int id, String title, String author);

    MyList<Book> getAllBooks();

    MyList<Book> getAllFreeBooks();

    MyList<Book> getAllBorrowedBooks();

    boolean borrowBook(int id);

    boolean returnBook(int id);
    void editBook();
}
