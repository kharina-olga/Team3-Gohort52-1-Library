package repository;

/*
CRUD - операции
- Create (Создание) - добавление новых данных
- Read (Чтение) - получение или чтение данных
- Update (Обновление) - изменение существующих данных
- Delete (Удаление) - удаление данных
 */

import model.Book;
import utils.MyList;

public interface BookRepository {

    // Добавить новую книгу
    Book addBook(String title, String author, int publicationYear);

    // Посмотреть книгу по айди
    Book getBookById(int id);

    // Получить список всех книг
    MyList<Book> getAllBooks();

    // Получить список доступных книг
    MyList<Book> getAvailableBooks();

    // Получить список всех книг на руках
    MyList<Book> getBorrowedBooks();

    // Удалить книгу из списка всех книг
    void deleteBook(Book book);

    // Найти книгу по её названию
    Object getBookByTitle(String title);
}
