package com.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Get the session object
        HttpSession session = request.getSession(false); // false to avoid creating a new session

        if (session != null) {
            // Invalidate the session
            session.invalidate();
        }

        // Redirect to the login page or home page
        response.sendRedirect("/Online_Hotel_Booking_System/");
    }
}
