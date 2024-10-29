package repository;

import model.Book;
import utils.MyList;

public class BookRepositoryImpl implements BookRepository {


    @Override
    public void addBook(int id, String title, String author) {

    }

    @Override
    public void getBookById(int id) {

    }

    @Override
    public MyList<Book> getAllBooks() {
        return null;
    }

    @Override
    public MyList<Book> getAvailableBooks() {
        return null;
    }

    @Override
    public MyList<Book> getBorrowedBooks() {
        return null;
    }
}
