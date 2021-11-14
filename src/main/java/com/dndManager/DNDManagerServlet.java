package com.dndManager;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "CharactersTableServlet", value = "/")
public class DNDManagerServlet extends HttpServlet {
    ITable tableCharacters,tableTasks;

    private DBTablesInfo dbTablesInfo = DBTablesInfo.getInstance();

    public void init() {
        System.out.println("[Init servlet]");
        tableCharacters = new CharactersTable(dbTablesInfo.GetCharactersALL());
        tableTasks = new TasksTable(dbTablesInfo.GetTasksALL());

    }

    private void getAllTableCharacters(HttpServletRequest request)
    {
        request.setAttribute("tableCharacters", tableCharacters);
    }

    private void getAllTableTask(HttpServletRequest request)
    {
        request.setAttribute("tableTasks", tableTasks);
    }

    private void setLang(HttpServletRequest request){
        String langStr = request.getParameter("lang");
        Localizator.Languages lang;
        try{
            lang = Localizator.Languages.valueOf(langStr);
        }
        catch (Exception e)
        {
            lang = Localizator.Languages.en;
        }
        request.setAttribute("lang", lang);
    }

    private void getTaskTableByName(HttpServletRequest request)
    {
       ITable resTable  = new TasksTable(dbTablesInfo.GetTasksByCharacterName(request.getParameter("name")));
       request.setAttribute("restasks", resTable);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {
        request.setCharacterEncoding("utf-8"); // очень надо
        response.setContentType("text/html;charset=UTF-8"); // установка кодировки

        setLang(request);
        getAllTableCharacters(request);
        getAllTableTask(request);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doGet(request, response);
    }


    public void destroy() {
    }
}