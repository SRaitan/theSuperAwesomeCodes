package com.company;

import java.io.IOException;

/**
 * Created by hackeru on 4/19/2017.
 */
public class MainServlet extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {

    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        response.getWriter().write("heyyyy");
        String queryString = request.getQueryString();
        System.out.println("Query String "+queryString);
    }
}
