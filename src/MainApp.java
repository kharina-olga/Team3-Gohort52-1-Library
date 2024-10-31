import repository.BookRepository;
import repository.BookRepositoryImpl;
import repository.UserRepository;
import repository.UserRepositoryImpl;

import service.LibraryService;
import service.LibraryImpl;
import service.UserService;
import service.UserImpl;
import view.Menu;


public class MainApp {
    public static void main(String[] args) {

        BookRepository bookRepository = new BookRepositoryImpl();
        UserRepository userRepository = new UserRepositoryImpl(bookRepository);

        UserService serviceUser = new UserImpl(userRepository);
        LibraryService serviceBook = new LibraryImpl(bookRepository,userRepository);

        Menu menu = new Menu(serviceUser,serviceBook);

        menu.run();

    }
}
