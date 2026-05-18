<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page import="javax.servlet.http.Cookie" %>
<%@ page import="com.mycompany.frontendservice.endpoint.JWTUtil" %>

<%
    String token = null;
    Cookie[] cookies = request.getCookies();
    if (cookies != null) {
        for (Cookie c : cookies) {
            if ("token".equals(c.getName())) {
                token = c.getValue();
                break;
            }
        }
    }

    if (token == null || !JWTUtil.validateToken(token)) {
        response.sendRedirect("login.html");
        return;
    }

    String username = JWTUtil.getUsername(token);
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Dashboard</title>
</head>
<body>
    <h2>Welcome, <%= username %></h2>

    <h3>View Balance</h3>
    <form method="get" action="balance">
        <label>Customer ID:</label>
        <input type="text" name="customerID" value="1" required><br><br>
        <button type="submit">View Balance</button>
    </form>

    <br><br>

    <h3>Transfer Funds</h3>
    <form method="post" action="transfer">
        <label>From Account ID:</label>
        <input type="text" name="fromAccountID" required><br><br>

        <label>To Account ID:</label>
        <input type="text" name="toAccountID" required><br><br>

        <label>Amount:</label>
        <input type="text" name="amount" required><br><br>

        <button type="submit">Transfer</button>
    </form>

    <br>
    <a href="logout">Logout</a>
</body>
</html>
