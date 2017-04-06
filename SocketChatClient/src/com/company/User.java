package com.company;

import java.io.IOException;
import java.io.OutputStream;


/**
 * Created by eladlavi on 28/03/2017.
 */
public class User {
    private String userName;
    private String password;
    private int action;

    public User(String userName, String password, int action) {
        this.userName = userName;
        this.password = password;
        this.action = action;
    }

    public User(){

    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public void stream(OutputStream outputStream) throws IOException{
        outputStream.write(userName.getBytes().length);
        outputStream.write(userName.getBytes());
        outputStream.write(password.getBytes().length);
        outputStream.write(password.getBytes());
    }
}