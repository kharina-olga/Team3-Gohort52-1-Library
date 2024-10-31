package repository;

import model.Book;
import utils.MyArrayList;
import utils.MyList;

import java.util.concurrent.atomic.AtomicInteger;

public class BookRepositoryImpl implements BookRepository {

    // тут будут хранится все наши книги. Имитация БД
    private final MyList<Book> books;

    // объект, отвечающий за генерацию уникальных id
    private final AtomicInteger currentId = new AtomicInteger(1);

    public BookRepositoryImpl() {
        this.books = new MyArrayList<>();
        addBooks();
    }

    private void addBooks() {
        books.addAll(
                new Book(currentId.getAndIncrement(), "To Kill a Mockingbird", "Harper Lee", 1960),
                new Book(currentId.getAndIncrement(), "1984", "George Orwell", 1949),
                new Book(currentId.getAndIncrement(), "Pride and Prejudice", "Jane Austen", 1813),
                new Book(currentId.getAndIncrement(), "The Great Gatsby", "F. Scott Fitzgerald", 1925),
                new Book(currentId.getAndIncrement(), "Moby-Dick", "Herman Melville", 1851),
                new Book(currentId.getAndIncrement(), "The Catcher in the Rye", "J.D. Salinger", 1951),
                new Book(currentId.getAndIncrement(), "The Hobbit", "J.R.R. Tolkien", 1937),
                new Book(currentId.getAndIncrement(), "War and Peace", "Leo Tolstoy", 1869),
                new Book(currentId.getAndIncrement(), "Crime and Punishment", "Fyodor Dostoevsky", 1866),
                new Book(currentId.getAndIncrement(), "The Odyssey", "Homer", -800) // предполагаемое написание в 8 веке до н.э.
        );

    }


    // метод принимает данные из слоя service, добавляет сгенерированный id и создает объект книга
    @Override
    public Book addBook(String title, String author, int publicationYear) {
//currentId.getAndIncrement() -> аналог currentId++; -> получение текущего id и затем увеличение его на +1
        Book book = new Book(currentId.getAndIncrement(), title, author, publicationYear);
        books.add(book);
        return book;  // сохранение в "хранилище"
    }


    @Override
    public Book getBookById(int id) {
        for (Book book : books) {
            if (book.getId() == id) return book;
        }
        return null;
    }

    @Override
    public MyList<Book> getAllBooks() {
        return books;
    }

    @Override
    public MyList<Book> getAvailableBooks() {
        MyList<Book> result = new MyArrayList<>();

        for (Book book : books) {
            if (book.isAvailable()) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public MyList<Book> getBorrowedBooks() {
        MyList<Book> result = new MyArrayList<>();


        for (Book book : books) {
            if (!book.isAvailable()) {
                result.add(book);
            }
        }
        return result;
    }

    @Override
    public boolean deleteBook(Book book) {

        books.remove(book);
        return true;

    }

    @Override
    public Object getBookByTitle(String title) {

        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return "Книга с названием '" + title + "' не найдена";
    }
}


