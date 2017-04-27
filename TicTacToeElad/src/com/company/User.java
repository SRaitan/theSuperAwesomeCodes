package com.company;

/**
 * Created by hackeru on 4/27/2017.
 */
public class User {
    private String username, password;
    private User opponent;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getOpponent() {
        return opponent;
    }

    public void setOpponent(User opponent) {
        this.opponent = opponent;
    }

    public User(String username, String password) {

        this.username = username;
        this.password = password;
    }
}
