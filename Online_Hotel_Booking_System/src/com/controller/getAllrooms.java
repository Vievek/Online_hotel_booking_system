package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.rooms;
import com.util.rooms_util;

@WebServlet("/getAllrooms")
public class getAllrooms extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public getAllrooms() {
        super(); // Ensure to call the superclass constructor
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<rooms> room = rooms_util.getAllrooms();
        
        // Debug: Log room data to the server console
        if (room == null || room.isEmpty()) {
            System.out.println("No rooms retrieved from the utility.");
        } else {
            for (rooms rom : room) {
                System.out.println("Room ID: " + rom.getRoomId());
            }
        }
        
        // Check if the list is empty
        if (room == null || room.isEmpty()) {
            // Set error message and dispatch to the error page
            request.setAttribute("errorMessage", "No rooms available.");
            request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
        } else {
            // Set the rooms as an attribute and forward to the JSP
            request.setAttribute("room", room);
            request.getRequestDispatcher("views/rooms.jsp").forward(request, response);
        }
    }
}

