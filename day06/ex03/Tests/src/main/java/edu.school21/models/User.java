package edu.school21.models;

import java.util.Objects;

public class User {
    private Long id;
    private String login;
    private String password;
    private boolean isAuthenticated;

    public User(Long id, String login, String password, boolean isAuthenticated) {
        this.id = id;
        this.login = login;
        this.password = password;
        this.isAuthenticated = isAuthenticated;
    }
    public User()
    {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAuthenticated() {
        return isAuthenticated;
    }

    public void setAuthenticated(boolean authenticated) {
        isAuthenticated = authenticated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;
        User user = (User) o;
        return isAuthenticated() == user.isAuthenticated() && getId().equals(user.getId()) && getLogin().equals(user.getLogin()) && getPassword().equals(user.getPassword());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getLogin(), getPassword(), isAuthenticated());
    }
}
