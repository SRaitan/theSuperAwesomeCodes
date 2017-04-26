package com.company;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by eladlavi on 26/04/2017.
 */
public class MainServlet extends javax.servlet.http.HttpServlet {

    Map<String, String> users;

    @Override
    public void init() throws ServletException {
        super.init();
        users = new HashMap<>();
    }

    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String queryString = request.getQueryString();
        if(queryString == null)
            return;
        Map<String, String> qs = new HashMap<>();
        String[] keyValuePairs = queryString.split("&");
        for(String keyValuePair : keyValuePairs){
            String[] keyValue = keyValuePair.split("=");
            if(keyValue.length != 2)
                continue;
            qs.put(keyValue[0], keyValue[1]);
        }
        String action = qs.get("action");
        if(action == null)
            return;
        String username = qs.get("username");
        String password = qs.get("password");
        if(username == null || password == null)
            return;
        switch (action){
            case "makeMove":
                if(!validatedUser(username, password))
                    return;
                break;
            case "checkBoard":
                if(!validatedUser(username, password))
                    return;
                break;
            case "getLobby":

                break;
            case "choosePartner":

                break;
            case "login":
                response.getWriter().write(
                        validatedUser(username,password) ? "ok" : "failure");
                break;
            case "signup":
                boolean success = false;
                synchronized (users){
                    if(!users.containsKey(username)){
                        users.put(username, password);
                        success = true;
                    }
                }
                response.getWriter().write(success ? "ok" : "failure");
                break;
        }
    }

    private boolean validatedUser(String username, String password){
        String existingPassword = users.get(username);
        return password.equals(existingPassword);
    }
}