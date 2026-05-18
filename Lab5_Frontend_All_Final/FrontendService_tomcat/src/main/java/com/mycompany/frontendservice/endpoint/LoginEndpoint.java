package com.mycompany.frontendservice.endpoint;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import java.sql.*;

public class LoginEndpoint extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        boolean authenticated = false;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection conn = DriverManager.getConnection(
                "jdbc:mysql://" + System.getenv("DB_URL") + "/OnlineBanking",
                "root",
                "student"
            );

            String query = "SELECT * FROM Customer WHERE username=? AND password=?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                authenticated = true;
            }

            conn.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        if (authenticated) {

            String token = JWTUtil.generateToken(username);

            Cookie cookie = new Cookie("token", token);
            cookie.setMaxAge(60 * 60);
            response.addCookie(cookie);

            response.sendRedirect("dashboard.jsp");

        } else {
            response.getWriter().println("Login Failed");
        }
    }
}