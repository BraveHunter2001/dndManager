package com.dndManager;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "CharactersTableServlet", value = "/CharactersTable-servlet")
public class CharactersTableServlet extends HttpServlet {
    ITables tableCh;
    private DBTablesInfo dbTablesInfo = DBTablesInfo.getInstance();

    public void init() {

        tableCh = new CharactersTable(dbTablesInfo.GetCharactersALL());

    }

    private void viewTable(HttpServletRequest request, HttpServletResponse response, ITables table) throws IOException
    {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println(table.getHTMLTables());

    }

    private ITables getTaskTableByName(HttpServletRequest request)
    {
        return new TasksTable(dbTablesInfo.GetTasksByCharacterName(request.getParameter("name")));
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        viewTable(request,response,tableCh);
        viewTable(request, response, getTaskTableByName(request));
        out.println("</body></html>");
        out.close();
    }



    public void destroy() {
    }
}