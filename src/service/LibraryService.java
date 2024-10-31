package service;
import model.Book;
import utils.MyList;

public interface LibraryService {

    Book getByAuthor(String author);

    Book addBook(String title, String author, int publicationYear );

    MyList<Book> getAllBooks();

    MyList<Book> getAllFreeBooks();

    MyList<Book> getAllBorrowedBooks();

    boolean borrowBook(int id);

    boolean returnBook(int id);
    void editBook();
}
