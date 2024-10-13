package com.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.model.rooms;
import com.util.rooms_util;

@WebServlet("/")
public class Home extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            // Get rooms and highly booked rooms
            List<rooms> room = rooms_util.getAllrooms();
            List<rooms> highroom = rooms_util.gethighlyBookedrooms();
            List<rooms> recentroom = null;

            // Handle user session and retrieve recent rooms
            HttpSession session = request.getSession(false); // Do not create a new session
            if (session != null) {
                Integer ruId = (Integer) session.getAttribute("ru_id");
                
                if (ruId != null) {
                    // Fetch recently selected rooms for the user
                    recentroom = rooms_util.RecentlySelectedRooms(ruId);
                } else {
                    // ru_id is not found in session
                    request.setAttribute("errorMessage", "User session is missing. Please log in again.");
                }
            } else {
                // No session exists
                request.setAttribute("errorMessage", "No active session found. Please log in.");
            }

            // Check if rooms were retrieved successfully
            if (room == null || room.isEmpty()) {
                // If no rooms were found, set error message and forward to an error page
                request.setAttribute("errorMessage", "Unable to load rooms. Please try again later.");
                request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
                return; // Stop further execution
            }

            // Set attributes for the rooms and forward to home page
            request.setAttribute("room", room);
            request.setAttribute("highroom", highroom);
            request.setAttribute("recentroom", recentroom);
            request.getRequestDispatcher("views/home.jsp").forward(request, response);
        } catch (Exception e) {
            // Log the exception and forward to a generic error page
            e.printStackTrace(); // Ideally, use a logging framework like Log4j or SLF4J
            request.setAttribute("errorMessage", "An unexpected error occurred. Please try again later.");
            request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Forward POST requests to doGet
        doGet(request, response);
    }
}
