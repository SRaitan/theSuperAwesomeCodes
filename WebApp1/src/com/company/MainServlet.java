package com.company;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MainServlet extends javax.servlet.http.HttpServlet {
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
        if (action == null) return;
        String op1str = qs.get("operand1");
        String op2str = qs.get("operand2");
        if (op1str == null || op2str == null)
            return;
        try {
            int op1 = Integer.valueOf(op1str);
            int op2 = Integer.valueOf(op2str);


            String res = "";
            switch (action) {
                case "add":
                    res = String.valueOf(op1 + op2);
                    break;
                case "subtract":
                    res = String.valueOf(op1 - op2);
                    break;
                case "mult":
                    res = String.valueOf(op1 * op2);
                    break;
                case "div":
                    if (op2 == 0) {
                        response.getWriter().write("division by zero error");
                        return;
                    }
                    res = String.valueOf(op1 / op2);
                    break;
                default:
                    return;
            }
            response.getWriter().write(res);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
