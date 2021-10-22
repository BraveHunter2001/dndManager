package com.dndManager;

import java.sql.*;

public class DBFacade {
    public static DBFacade getInstance()
    {
        if(instance == null) {
            instance = new DBFacade();
            instance.ConnectDB();
        }
        return instance;
    }
    private static DBFacade instance;
    private static final String DB_URL = "jdbc:postgresql://localhost:5432/dnd";
    private static final String USER = "postgres";
    private static final String PASS = "admin";

    public static class DBNotConnectedException extends Exception
    {
        String message;
        public DBNotConnectedException()
        {
            message = "Error: database was not connected";
        }
    }
    private Connection conn;

    public void ConnectDB()
    {
        try {
            if(conn == null)
                conn = DriverManager.getConnection(DB_URL, USER, PASS);

        } catch (SQLException e) {
            System.out.println("Connection Failed");
            e.printStackTrace();
            return;
        }
        if (conn != null)
            System.out.println("You successfully connected to database now");
        else
            System.out.println("Failed to make connection to database");
    }

    public Statement GetStatement() throws DBNotConnectedException
    {
        if(conn == null)
            throw new DBNotConnectedException();
        try {
            return conn.createStatement();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ResultSet ExecuteQuery(String query) throws DBNotConnectedException
    {
        if(conn == null)
            throw new DBNotConnectedException();

        Statement stmt = GetStatement();
        try {
            return stmt.executeQuery(query);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }




}
