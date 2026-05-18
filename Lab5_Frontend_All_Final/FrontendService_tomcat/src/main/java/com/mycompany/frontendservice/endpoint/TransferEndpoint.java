package com.mycompany.frontendservice.endpoint;

import java.io.*;
import java.net.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class TransferEndpoint extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String from = request.getParameter("fromAccountID");
        String to = request.getParameter("toAccountID");
        String amount = request.getParameter("amount");

        URL url = new URL("http://" + System.getenv("TRANSACTION_SERVICE")
                + "/TransactionService_tomcat/api/transfer");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("POST");
        conn.setDoOutput(true);
        conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");

        String data = "fromAccountID=" + URLEncoder.encode(from, "UTF-8")
                + "&toAccountID=" + URLEncoder.encode(to, "UTF-8")
                + "&amount=" + URLEncoder.encode(amount, "UTF-8");

        try (OutputStream os = conn.getOutputStream()) {
            os.write(data.getBytes("UTF-8"));
            os.flush();
        }

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
