package model;

import utils.MyArrayList;
import utils.MyList;


public class User {
    private String email;
    private String password;
    private Role role;
    private MyList<Book> borrowedBooks; // Список взятых книг
    private final MyList<Book>  userBooks;

    public User(String email, String password, Role role) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.userBooks = new MyArrayList<>();
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
                ", userBooks=" + userBooks +
                '}';
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public MyList<Book> getUserBooks() {
        return userBooks;
    }
}
