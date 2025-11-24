package com.learn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;

public class DatabaseConnectionListener implements ServletContextListener {

    private Connection connection;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // This method runs ONCE when the app starts
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            ServletContext ctx = sce.getServletContext();
            String connectionURL = ctx.getInitParameter("connectionURL");
            String username = ctx.getInitParameter("username");
            String password = ctx.getInitParameter("password");
            
            this.connection = DriverManager.getConnection(connectionURL, username, password);
            
            // Store the single connection in the ServletContext
            ctx.setAttribute("dbConnection", connection);
            System.out.println("Database connection created and stored in ServletContext.");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // This method runs ONCE when the app stops
        try {
            if (this.connection != null && !this.connection.isClosed()) {
                this.connection.close();
                System.out.println("Database connection closed.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}