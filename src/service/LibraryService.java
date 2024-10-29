package service;

public interface LibraryService {

    void getByAuthor();

    void addBook();

    void getAllBooks();

    void getAllFreeBooks();

    void getAllBorrowedBooks();
    void borrowBook();
    void returnBook();
    void editBook();
}
