package com.assignment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // redirect GET to the index page
        response.sendRedirect(request.getContextPath() + "/");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String query = request.getParameter("query");
        if (query == null || query.trim().isEmpty()) {
            // nothing provided, go back to the input page
            response.sendRedirect(request.getContextPath() + "/");
            return;
        }

        String encoded = URLEncoder.encode(query.trim(), StandardCharsets.UTF_8.toString());
        String target = "https://www.google.com/search?q=" + encoded;
        response.sendRedirect(target);
    }
}