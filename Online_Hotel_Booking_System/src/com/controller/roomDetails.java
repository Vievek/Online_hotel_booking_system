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

/**
 * Servlet implementation class roomDetails
 */
@WebServlet("/roomDetails")
public class roomDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String roomId = request.getParameter("roomId");
	     String userId = request.getParameter("userId");
	     
	     System.out.println(roomId + userId);
	     
	     List<rooms> room = rooms_util.getSelectedRoom(roomId);
	        
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
	            request.setAttribute("errorMessage", "room detail not available.");
	            request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
	        } else {
	        	 
	   	     List<Services> services = Services_util.getAllServices();
	   	        
	   	        // Debug: Log room data to the server console
	   	        if (services == null || services.isEmpty()) {
	   	            System.out.println("No services retrieved from the utility.");
	   	        } else {
	   	            for (Services ser : services) {
	   	                System.out.println("ser ID: " + ser.getServices_id());
	   	            }
	   	        }
	   	        
	   	        // Check if the list is empty
	   	        if (services == null || services.isEmpty()) {
	   	            // Set error message and dispatch to the error page
	   	            request.setAttribute("errorMessage", "services not available.");
	   	            request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
	   	        } else {
	   	            // Set the services as an attribute and forward to the JSP
	   	            request.setAttribute("services", services);
	   	        }
	        	     	
	            // Set the rooms as an attribute and forward to the JSP
	            request.setAttribute("room", room);
	            request.getRequestDispatcher("views/roomDetails.jsp").forward(request, response);
	        }
	}

}
