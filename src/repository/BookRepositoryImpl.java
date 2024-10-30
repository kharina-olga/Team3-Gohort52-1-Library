package repository;

import model.Book;
import utils.MyArrayList;
import utils.MyList;

import java.util.concurrent.atomic.AtomicInteger;

public class BookRepositoryImpl implements BookRepository {

    // тут будут хранится все наши машинки. Имитация БД
    private final MyList<Book> books;

    // объект, отвечающий за генерацию уникальных id
    private final AtomicInteger currentId = new AtomicInteger(1);

    public BookRepositoryImpl() {
        this.books = new MyArrayList<>();
        addBooks();
    }

    private void addBooks() {
        books.addAll(
                new Book(currentId.getAndIncrement(), "To Kill a Mockingbird", "Harper Lee"),
                new Book(currentId.getAndIncrement(), "1984", "George Orwell"),
                new Book(currentId.getAndIncrement(), "Pride and Prejudice", "Jane Austen"),
                new Book(currentId.getAndIncrement(), "The Great Gatsby", "F. Scott Fitzgerald"),
                new Book(currentId.getAndIncrement(), "Moby-Dick", "Herman Melville"),
                new Book(currentId.getAndIncrement(), "The Catcher in the Rye", "J.D. Salinger"),
                new Book(currentId.getAndIncrement(), "The Hobbit", "J.R.R. Tolkien"),
                new Book(currentId.getAndIncrement(), "War and Peace", "Leo Tolstoy"),
                new Book(currentId.getAndIncrement(), "Crime and Punishment", "Fyodor Dostoevsky"),
                new Book(currentId.getAndIncrement(), "The Odyssey", "Homer")
        );

    }

    @Override
    public void addBook(int id, String title, String author) {
        Book book = new Book(id, title, author);
        books.add(book);
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
        return null;
    }

    @Override
    public MyList<Book> getAvailableBooks() {
        return null;
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
    public void deleteCar(Book book) {
        books.remove(book);
    }
}


