package com.dndManager;

import java.io.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    private String message;
    private String table;
    private DBTablesInfo dbTablesInfo = DBTablesInfo.getInstance();;

    public void init() {

        table = new CharactersTable(dbTablesInfo.GetCharactersALL()).getHTMLTables();
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println(table);
        out.println("</body></html>");
    }

    public void destroy() {
    }
}