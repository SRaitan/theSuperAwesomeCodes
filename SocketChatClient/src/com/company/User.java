package com.company;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by hackeru on 3/21/2017.
 */
public class User {
    String username;
    String password;
    int choice;

    public User() {
    }

    public int getChoice() {
        return choice;
    }

    public void setChoice(int choice) {
        this.choice = choice;
    }

    public User(String username, String password, int choice ){
        this.username = username;
        this.password = password;
        this.choice=choice;


    }

    public void streamUser (OutputStream outputStream) throws IOException {
        //write username and password to byteStream to send to server
        byte[] userNameBytes = this.username.getBytes();
        byte [] passwordBytes = this.password.getBytes();
        outputStream.write(userNameBytes.length);
        outputStream.write(userNameBytes);
        outputStream.write(passwordBytes.length);
        outputStream.write(passwordBytes);
        //todo: check how getBytes works
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
