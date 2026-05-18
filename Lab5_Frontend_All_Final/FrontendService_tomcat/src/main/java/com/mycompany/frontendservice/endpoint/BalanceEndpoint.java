package com.mycompany.frontendservice.endpoint;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class BalanceEndpoint extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String customerID = request.getParameter("customerID");

        URL url = new URL("http://" + System.getenv("ACCOUNT_SERVICE")
                + "/AccountService_tomcat/api/balance/" + customerID);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        BufferedReader reader;
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() < 300) {
            reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            reader = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }

        response.setContentType("application/xml;charset=UTF-8");

        String line;
        while ((line = reader.readLine()) != null) {
            response.getWriter().println(line);
        }
        reader.close();
    }
}
