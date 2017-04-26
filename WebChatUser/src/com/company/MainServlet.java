package com.company;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by eladlavi on 25/04/2017.
 */
public class MainServlet extends javax.servlet.http.HttpServlet {

    List<String> messages;
    Map<String,User> users;

    @Override
    public void init() throws ServletException {
        super.init();
        messages = new ArrayList<>();
        users = new HashMap<>();
    }
    private boolean validUser(User user){
        if(user == null)
            return false;
        User userInDB = users.get(user.getUserName());
        return user.getPassword() != null &&
                userInDB.getPassword().equals(
                        user.getPassword());
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
        switch (action) {
            case "signUp":
                boolean found=false;
                String userName = qs.get("username");
                String pw = qs.get("password");
                synchronized (users) {
                    found = users.get(userName) != null;
                }
                if(!found)
                users.put(userName, new User(userName, pw));
                response.getWriter().write(found? "existsUser":"connect");
                break;
            case "signIn":
                String userE = qs.get("username");
                String pwE = qs.get("password");
                if (users.get(userE) == null)
                    response.getWriter().write("notExistsUser");
                if(validUser(new User(userE,pwE)))
                    response.getWriter().write("connect");
                else response.getWriter().write("pwFail");
                break;
            case "send":
                String message = qs.get("message");
                if (message == null)
                    return;
                messages.add(message);
                response.getWriter().write("ok");
                break;
            case "check":
                int from = 0;
                String fromString = qs.get("from");
                if (fromString == null)
                    return;
                try {
                    from = Integer.valueOf(fromString);
                } catch (Exception ex) {
                    return;
                }
                if (from < 0)
                    return;
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = from; i < messages.size(); i++) {
                    stringBuilder.append(messages.get(i) + "&");
                }
                if (messages.size() > 0) {
                    stringBuilder.delete(stringBuilder.length() - 1, stringBuilder.length());
                }
                response.getWriter().write(stringBuilder.toString());
                break;
            case "signout":
                response.getWriter().write("out");
                break;
        }

    }
}