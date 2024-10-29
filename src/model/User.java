package model;

import utils.MyArrayList;
import utils.MyList;


public class User {
    private String email;
    private String password;
    private Role role;

        this.password = password;
        this.role = Role.USER;
    }

    @Override
    public String toString() {
        return "User{" +
                "email='" + email + '\'' +
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

    }
}
