package com.company;

import javax.servlet.ServletException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Servlet extends javax.servlet.http.HttpServlet {
    int counter = 0;
    private List<String> messages;
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    @Override
    public void init() throws ServletException {
        super.init();
        messages=new ArrayList<>();
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
                String message = qs.get("msg");
                if(message == null) return;
                messages.add(message);
                response.getWriter().write("ok");
                break;
            case "read":
                int from = 0;
                String fromString = qs.get("from");
                if(fromString == null)
                    return;
                try{
                    from = Integer.valueOf(fromString);
                }catch (Exception ex){
                    return;
                }
                if(from < 0)
                    return;
                StringBuilder stringBuilder = new StringBuilder();
                for (int i=from; i < messages.size(); i++) {
                    stringBuilder.append(messages.get(i) + "&");
                }
                if(messages.size() > 0){
                    stringBuilder.delete(stringBuilder.length()-1, stringBuilder.length());
                }
                //if(messages.size() != 0 && from < messages.size())
                //    stringBuilder.append(messages.get(messages.size()-1));
                response.getWriter().write(stringBuilder.toString());
                break;
            default:
        }

    }
}
