package com.dndManager;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Objects;

public class LoginManager {
    private final DBFacade db = DBFacade.getInstance();
    public boolean tryLogin (String login, String password) {
        try {
            var query = db.ExecuteQuery("select * from users u where u.login ='"
                    + login + "' and u.password='"
                    + password +"';");
            return query.next();
        }catch (DBFacade.DBNotConnectedException | SQLException e)
        {
            e.printStackTrace();
        }
        return false;
    }

    public static class AuthResilt {
        private String login, password;
        private boolean authSuccess = false;
        public AuthResilt(String login, String password, boolean authSuccess)
        {
            this.login = login;
            this.password = password;
            this.authSuccess = authSuccess;
        }
        public String getUsername(){ return login; }
        public boolean getAuthSuccess() { return authSuccess; }
    }

    public AuthResilt tryAuthCookies(HttpServletRequest request)
    {
        String login = "", password = "";

        try{
            var cookies = request.getCookies();
            login = Arrays.stream(cookies).filter(x -> Objects.equals(x.getName(), "login"))
                    .findAny().get().getValue();
            password = Arrays.stream(cookies).filter(x -> Objects.equals(x.getName(), "password"))
                    .findAny().get().getValue();
        }catch ( Exception e)
        {
            return new AuthResilt("", "", false);
        }
        return new AuthResilt(login,password,tryLogin(login,password));
    }

    private void killCookie(Cookie cookie, HttpServletRequest request, HttpServletResponse response)
    {
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    public void removeLoginCookies(HttpServletRequest request, HttpServletResponse response)
    {
        try
        {
            var cookies = request.getCookies();
            var username = Arrays.stream(cookies)
                    .filter(x -> Objects.equals(x.getName(), "username"))
                    .findAny().get(); killCookie(username, request, response);
            var password = Arrays.stream(cookies)
                    .filter(x -> Objects.equals(x.getName(), "password"))
                    .findAny().get(); killCookie(password, request, response);
        } catch (Exception ignored) {}
    }
}
