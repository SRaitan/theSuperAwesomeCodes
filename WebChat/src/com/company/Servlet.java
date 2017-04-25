package com.company;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Servlet extends javax.servlet.http.HttpServlet {
    private String message;
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String queryString = request.getQueryString();
        Map<String, String> qs = new HashMap<>();
        if (queryString != null) {
            String[] keyValues = queryString.split("&");
            for (String keyValue : keyValues) {
                String[] keyValuePair = keyValue.split("=");
                if (keyValuePair.length != 2)
                    continue;
                qs.put(keyValuePair[0], keyValuePair[1]);
            }
        }
        String action = qs.get("action");
        if (action == null)
            return;
        switch (action) {
            case "send":
                message = qs.get("msg");
                break;
            case "read":
                response.getWriter().write(message);
                break;
            default:
                return;
        }

    }
}
