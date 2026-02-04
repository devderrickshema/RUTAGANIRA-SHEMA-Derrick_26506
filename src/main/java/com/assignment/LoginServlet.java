package com.assignment;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Login</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Login Form</h1>");
            out.println("<form method='POST' action='login'>");
            out.println("Username: <input type='text' name='username' required><br><br>");
            out.println("Password: <input type='password' name='password' required><br><br>");
            out.println("<input type='submit' value='Login'>");
            out.println("</form>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        try {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Login Response</title>");
            out.println("<style>");
            out.println("* { margin: 0; padding: 0; box-sizing: border-box; }");
            out.println("body { font-family: Arial, sans-serif; background: white; color: #333; padding: 20px; }");
            out.println("h1 { font-size: 20px; margin-bottom: 10px; font-weight: bold; }");
            out.println("p { font-size: 16px; margin-bottom: 15px; color: #555; }");
            out.println("a { display: inline-block; padding: 10px 20px; margin-top: 10px; background-color: #667eea; color: white; text-decoration: none; border-radius: 4px; cursor: pointer; }");
            out.println("a:hover { background-color: #5566d8; }");
            out.println(".weak { color: #d9534f; }");
            out.println(".strong { color: #28a745; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            
            if (username != null && !username.isEmpty() && password != null && !password.isEmpty()) {
                if (password.length() < 8) {
                    out.println("<h1 class='weak'>Password Validation Result</h1>");
                    out.println("<p>Hello " + username + ", your password is weak. Try a strong one.</p>");
                } else {
                    out.println("<h1 class='strong'>Login Successful</h1>");
                    out.println("<p>Welcome " + username + "</p>");
                }
            } else {
                out.println("<h1>Login Failed</h1>");
                out.println("<p>Please enter both username and password.</p>");
            }
            
            out.println("<a href='login'>Try Again</a>");
            out.println("</body>");
            out.println("</html>");
        } finally {
            out.close();
        }
    }
}
