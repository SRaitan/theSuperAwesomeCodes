package com.company;

/**
 * Created by hackeru on 3/21/2017.
 */
public class User {
    String username;
    String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {return username;}

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null) return false;
        if(obj == this) return true;
        if(obj instanceof User) {
            User other = (User) obj;
            return this.getUsername().equals(other.getUsername()) && this.getPassword().equals(other.getPassword());
        }
        return false;
    }
}
