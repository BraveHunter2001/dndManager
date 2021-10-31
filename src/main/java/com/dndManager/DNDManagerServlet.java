package com.dndManager;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "CharactersTableServlet", value = "/CharactersTable-servlet")
public class DNDManagerServlet extends HttpServlet {
    ITablesHTML tableCharacters,tableTasks;

    private DBTablesInfo dbTablesInfo = DBTablesInfo.getInstance();

    public void init() {
        tableCharacters = new CharactersTable(dbTablesInfo.GetCharactersALL());
        tableTasks = new TasksTable(dbTablesInfo.GetTasksALL());

    }

    private void viewMain(HttpServletRequest request, HttpServletResponse response, Localizator loc) throws IOException
    {

        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>"+loc.getResource("title")+"</title>\n" +
                "<link rel=\"stylesheet\" href=\"styles.css\">\n" +
                "<meta charset=\"utf-8\">"+
                "</head>");
        out.println("<body>");
        out.println("<div class=\"tables\">");
        out.println(viewTabelCharaters(loc));
        out.println(viewTabelTasks(tableTasks,loc));
        out.println("</div");
        out.println("</body>");
        out.println("</html>");
        out.close();

    }

    private String viewTabelCharaters(Localizator loc)
    {

        String ans = "";
        ans +="<div class =\"characters\">";
        ans += "<h2>"+loc.getResource("headerCharacters")+"</h2>\n";
        ans += "<table>";
        ans += "<tr>" +
                "<th>"+loc.getResource("id")+"</th>" +
                "<th>"+loc.getResource("name")+"</th>"+
                "<th>"+loc.getResource("apperance")+"</th>"+
                "<th>"+loc.getResource("location")+"</th>"+
                "<th>"+loc.getResource("status")+"</th>"+
                "</tr>";
        ans += tableCharacters.getHTMLTables();
        ans += "</table>";
        ans +="</div>";
        return ans;
    }

    private String viewTabelTasks(ITablesHTML task, Localizator loc)
    {
        String ans = "";
        ans +="<div class =\"tasks\">";
        ans += "<h2>"+loc.getResource("headerTasks")+"</h2>\n";
        ans += "<table>";
        ans += "<tr>" +
                "<th>"+loc.getResource("id")+"</th>" +
                "<th>"+loc.getResource("name")+"</th>"+
                "<th>"+loc.getResource("status")+"</th>"+
                "</tr>";
        ans += task.getHTMLTables();
        ans += "</table>";
        ans +="</div>";
        return ans;
    }

    private ITablesHTML getTaskTableByName(HttpServletRequest request)
    {
        return new TasksTable(dbTablesInfo.GetTasksByCharacterName(request.getParameter("name")));
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        request.setCharacterEncoding("utf-8"); // очень надо
        response.setContentType("text/html;charset=UTF-8"); // установка кодировки

        Localizator loc = Localizator.getInstance(request);
        loc.setLang(request, false);
        viewMain(request,response, loc);
    }



    public void destroy() {
    }
}