package com.dndManager;

import java.io.*;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

@WebServlet(name = "CharactersTableServlet", value = "/index")
public class DNDManagerServlet extends HttpServlet {
    ITable tableCharacters,tableTasks;

    private DBTablesInfo dbTablesInfo = DBTablesInfo.getInstance();
    private LoginManager loginManager = new LoginManager();
    public void init() {
        System.out.println("[Init servlet] Index");
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
       ITable resTable  = new TasksTable(dbTablesInfo.GetTasksByCharacterName(request.getParameter("nameCharacter")));
       request.setAttribute("restasks", resTable);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,  IOException {
        request.setCharacterEncoding("utf-8"); // очень надо
        response.setContentType("text/html;charset=UTF-8"); // установка кодировки

        setLang(request);
        getAllTableCharacters(request);
        getAllTableTask(request);

        var auth = loginManager.tryAuthCookies(request);
        if(auth.getAuthSuccess())
        {
            request.setAttribute("canTask", true);
            request.setAttribute("nameUsr", auth.getUsername());
        }else{
            loginManager.removeLoginCookies(request, response);
            request.setAttribute("canTask", false);
        }

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        var auth = loginManager.tryAuthCookies(request);
        if(auth.getAuthSuccess()) {
            getTaskTableByName(request);
        }
        else
        {
            request.setAttribute("canTask", false);
            RequestDispatcher requestDispatcher =
                    request.getRequestDispatcher("login.jsp");
            request.setAttribute("incorrectData", true);
            requestDispatcher.forward(request, response);
        }
        doGet(request, response);
    }


    public void destroy() {
        System.out.println("[Init destroy] Index");
    }
}