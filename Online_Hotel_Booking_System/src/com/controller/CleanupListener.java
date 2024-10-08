package com.controller;

import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import com.mysql.jdbc.AbandonedConnectionCleanupThread;

public class CleanupListener implements ServletContextListener {
    // This method is called when your web application stops
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // Unregister JDBC drivers to avoid memory leaks
        Enumeration<Driver> drivers = DriverManager.getDrivers();
        while (drivers.hasMoreElements()) {
            Driver driver = drivers.nextElement();
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        // Stop MySQL cleanup thread to avoid memory leaks
        AbandonedConnectionCleanupThread.checkedShutdown();
    }

    // This method is called when your web application starts
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        // You can add any initialization logic here (optional)
    }
}
