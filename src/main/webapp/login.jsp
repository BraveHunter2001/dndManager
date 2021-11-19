<%@ page import="com.dndManager.Localizator" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    Localizator loc = Localizator.getInstance();

    Object dataInc = request.getAttribute("incorrectData");
    boolean dataIncorrect = dataInc != null && (boolean) dataInc;
    String login = (String) request.getAttribute("login");
%>
<html>
<head>
    <title>Login</title>
</head>
<body>
<form action="" method="post">
    <%if(dataIncorrect) {%>
    <h3 style="color: red"><%=loc.getResource("incorrectLogin")%></h3>
    <%}%>
    <p><%= loc.getResource("logTitle")%></p>
    <%= loc.getResource("userLog")%>: <input type="text" name="login" size="40"><br>
    <%= loc.getResource("pas")%>: <input type="password" name="password" size="40"><br>
    <input type="submit" value="<%= loc.getResource("butt")%>">
</form>
</body>
</html>
