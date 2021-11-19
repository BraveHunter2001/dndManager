<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.dndManager.*" %>
<%
    Localizator loc = Localizator.getInstance();
    loc.setLang((Localizator.Languages) request.getAttribute("lang"));
    ITable tableCharacters = (ITable) request.getAttribute("tableCharacters");
    ITable tableTasks = (ITable) request.getAttribute("tableTasks");
    ITable resTasks = (ITable) request.getAttribute("restasks");
    boolean canTask = (boolean)request.getAttribute("canTask");
    String nameUser = (String) request.getAttribute("nameUsr");

%>
<!DOCTYPE html>
<html>
<head>
    <title><%= loc.getResource("title")%></title>
    <link rel="stylesheet" href="styles.css">
    <meta charset="utf-8">
</head>
<body>
<%
    request.setCharacterEncoding("utf-8");
    response.setContentType("text/html;charset=UTF-8");
%>
<% if (nameUser == null) {%>
<a href="/dndManager_war_exploded/login"> LOG IN </a>
<% }else{%>
<a href="/dndManager_war_exploded/login?logout=true"> LOG OUT </a><br>
<h3><%= nameUser%></h3>
<%}%>
<div class="tables">
    <div class="characters">
        <h2><%=loc.getResource("headerCharacters")%></h2>
        <table>
            <tr>
                <th><%=loc.getResource("id")%></th>
                <th><%=loc.getResource("name")%></th>
                <th><%=loc.getResource("apperance")%></th>
                <th><%=loc.getResource("location")%></th>
                <th><%=loc.getResource("status")%></th>
                </tr>
            <%=tableCharacters.getHTMLTables()%>
        </table>
    </div>
    <div class ="tasks">
        <h2><%=loc.getResource("headerTasks")%></h2>
        <table>
            <tr>
                <th><%=loc.getResource("id")%></th>
                <th><%=loc.getResource("name")%></th>
                <th><%=loc.getResource("status")%></th>
                </tr>
            <%= tableTasks.getHTMLTables()%>
        </table>
    </div>
</div>
<% if (canTask) {%>
<div class ="getTasks">
    <form action="" method="post">
        <p><b><%=loc.getResource("namePers") %>:</b><br>
            <input type="text" name="nameCharacter" size="40">
            <input type="submit" value="<%= loc.getResource("butt")%>">
    </form>
    <div class ="tasks">
        <h2><%=loc.getResource("formTask")%></h2>
        <table>
            <tr>
                <th><%=loc.getResource("id")%></th>
                <th><%=loc.getResource("name")%></th>
                <th><%=loc.getResource("status")%></th>
            </tr>
            <%= (resTasks != null) ? resTasks.getHTMLTables(): ""%>
        </table>
    </div>

</div>
<% }%>
<footer>
    <span class="lang"><%=loc.getResource("changeLang")%>: <a href="?lang=ru"><%=loc.getResource("rusL")%></a>, <a href="?lang=en"> <%= loc.getResource("enL")%></a> </span>
</footer>
</body>
</html>