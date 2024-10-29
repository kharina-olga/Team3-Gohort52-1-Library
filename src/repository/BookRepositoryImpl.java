package repository;
/*
CRUD - операции
- Create (Создание) - добавление новых данных
- Read (Чтение) - получение или чтение данных
- Update (Обновление) - изменение существующих данных
- Delete (Удаление) - удаление данных
 */

import model.Book;
import utils.MyArrayList;
import utils.MyList;

import java.util.concurrent.atomic.AtomicInteger;

public class BookRepositoryImpl implements BookRepository {
    // тут лежат все книги
    private final MyList<Book> books;

    public BookRepositoryImpl() {
        this.books = new MyArrayList<>();
        addBook();
    }

    // объект, отвечающий за генерацию уникальных id
    private final AtomicInteger currentId = new AtomicInteger(1);


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
