package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Services;
import com.model.rooms;
import com.util.Services_util;
import com.util.rooms_util;
import com.util.userRoom_interaction_util;

/**
 * Servlet implementation class roomDetails
 */
@WebServlet("/roomDetails")
public class roomDetails extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve roomId and userId from request parameters
        String roomIdParam = request.getParameter("roomId");
        String userIdParam = request.getParameter("userId");

        Integer IroomId = null;
        Integer IuserId = null;

        try {
            if (roomIdParam != null && !roomIdParam.isEmpty()) {
                IroomId = Integer.parseInt(roomIdParam); // Convert String to Integer
            } else {
                // Handle the case where roomId is not provided
                request.setAttribute("errorMessage", "Room ID is required.");
                request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
                return; // Stop further execution
            }

            if (userIdParam != null && !userIdParam.isEmpty()) {
                IuserId = Integer.parseInt(userIdParam); // Convert String to Integer
            }
        } catch (NumberFormatException e) {
            // Log parsing error and handle it
            e.printStackTrace();
            request.setAttribute("errorMessage", "Invalid ID format.");
            request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
            return; // Stop further execution
        }

        // Log the retrieved roomId and userId
        System.out.println("roomId: " + IroomId + ", userId: " + IuserId);

        // Check if userId is null (i.e., guest user)
        if (IuserId != null) {
            boolean isTrue = userRoom_interaction_util.addUserInteraction(IuserId, IroomId);
            if (isTrue) {
                System.out.println("Interaction added successfully");
            } else {
                System.out.println("Failed to add interaction.");
            }
        }

        // Fetch selected room details
        List<rooms> room = rooms_util.getSelectedRoom(IroomId);
        if (room == null || room.isEmpty()) {
            System.out.println("No room details available.");
            request.setAttribute("errorMessage", "Room detail not available.");
            request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
            return; // Stop further execution
        }

        // Log retrieved room details
        for (rooms rom : room) {
            System.out.println("Room ID: " + rom.getRoomId());
        }

        // Fetch all services
        List<Services> services = Services_util.getAllServices();
        if (services == null || services.isEmpty()) {
            System.out.println("No services available.");
            request.setAttribute("errorMessage", "Services not available.");
            request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
            return; // Stop further execution
        }

        // Log retrieved services details
        for (Services ser : services) {
            System.out.println("Service ID: " + ser.getServices_id());
        }

        // Set room and services attributes for the JSP
        request.setAttribute("room", room);
        request.setAttribute("services", services);

        // Forward to the room details JSP
        request.getRequestDispatcher("views/roomDetails.jsp").forward(request, response);
    }
}
