package service;
import model.Book;
import model.User;
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

    /**
     * Удалить книгу из списка всех книг
     * @param
     */
    void deleteBook(String title);

    void setActiveUser(User adminUser);

}
