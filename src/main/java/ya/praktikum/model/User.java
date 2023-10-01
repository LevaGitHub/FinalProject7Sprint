package ya.praktikum.model;

import io.qameta.allure.Step;

public class User {
    private String login;
    private String password;

    public User() {
    }

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }

    public static User from(Courier courier) {
        return new User(courier.getLogin(), courier.getPassword());
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {return login;
    }

    public String getPassword() {return password;
    }

}

