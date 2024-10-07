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

@WebServlet("/getFilteredRoom")
public class getFilteredRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String roomType = request.getParameter("roomType");
		String acType = request.getParameter("acType");
		        
        List<rooms> room;

	     // Check if the values are "1" and call the appropriate method
	     if (!"1".equals(roomType) && !"1".equals(acType)) {
	         room = rooms_util.getFilteredroom1(roomType,acType); // Call with both parameters
	     } else if (!"1".equals(roomType)) {
	         room = rooms_util.getFilteredroom2(roomType); // Call with roomType only
	     } else if (!"1".equals(acType)) {
	         room = rooms_util.getFilteredroom3(acType); // Call with acType only
	     } else {
	         room = rooms_util.getAllrooms(); // Handle the case when both are "1", return empty list or handle accordingly
	     }
        
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
