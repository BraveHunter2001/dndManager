package com.dndManager;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

@WebServlet(name = "LoginServlet", value = "/login")
public class LoginServlet extends HttpServlet {
    private LoginManager loginManager = new LoginManager();

    public void init() {
        System.out.println("[Init servlet] login");
    }

    boolean checkLogout(HttpServletRequest request, HttpServletResponse responce)
    {
        var logout = request.getParameter("logout");
        if(logout!=null && logout.equals("true")){
            loginManager.removeLoginCookies(request, responce);
            return true;
        }
        return false;
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

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8"); // очень надо
        response.setContentType("text/html;charset=UTF-8"); // установка кодировки
        if(checkLogout(request, response)) {
            response.sendRedirect("/dndManager_war_exploded/index");
            return;
        }
        setLang(request);


        RequestDispatcher requestDispatcher = request.getRequestDispatcher("login.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            var login = request.getParameter("login");
            var pass = request.getParameter("password");
            if (loginManager.tryLogin(login, pass)) {
                Cookie loginCookie = new Cookie("login", login);
                Cookie passCookie = new Cookie("password", pass);
                loginCookie.setMaxAge(24 * 60 * 60);
                passCookie.setMaxAge(24 * 60 * 60);
                response.addCookie(loginCookie);
                response.addCookie(passCookie);
            } else {
                loginManager.removeLoginCookies(request, response);
                request.setAttribute("login", login);
                request.setAttribute("incorrectData", true);
                RequestDispatcher requestDispatcher =
                        request.getRequestDispatcher("login.jsp");
                requestDispatcher.forward(request, response);
            }
        } finally {
            response.sendRedirect("/dndManager_war_exploded/index");
        }
    }


    public void destroy() {
        System.out.println("[Init destroy] Index");
    }
}
