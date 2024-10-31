package service;
import model.Book;
import utils.MyList;

public interface LibraryService {

    void getByAuthor();

    Book addBook(String title, String author, int publicationYear );

    MyList<Book> getAllBooks();

    MyList<Book> getAllFreeBooks();

    MyList<Book> getAllBorrowedBooks();

    boolean borrowBook(int id);

    boolean returnBook(int id);
    void editBook();
}
