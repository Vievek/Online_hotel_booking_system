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
			if (roomIdParam != null) {
				IroomId = Integer.parseInt(roomIdParam); // Convert String to Integer
			}
			if (userIdParam != null) {
				IuserId = Integer.parseInt(userIdParam); // Convert String to Integer
			}
		} catch (NumberFormatException e) {
			// Log parsing error
			e.printStackTrace();
		}
		// Log the retrieved roomId and userId
		System.out.println("roomId: " + IroomId + ", userId: " + IuserId);
		// Now, try to retrieve roomId and userId as attributes (set by another servlet or JSP)
		Object roomIdAttr = request.getAttribute("UroomId");
		Object userIdAttr = request.getAttribute("UuserId");
		if (roomIdAttr instanceof Integer) {
			IroomId = (Integer) roomIdAttr; // Safely cast if it's an Integer
		}
		if (userIdAttr instanceof Integer) {
			IuserId = (Integer) userIdAttr; // Safely cast if it's an Integer
		}
		// Log roomId and userId after checking the attributes
		System.out.println("Final roomId: " + IroomId + ", userId: " + IuserId);
		
		boolean isTrue = userRoom_interaction_util.addUserInteraction(IuserId, IroomId);
		if(isTrue) {
			System.out.println("interaction added successfully");
		}
		// Fetch selected room details
		List<rooms> room = rooms_util.getSelectedRoom(IroomId);
		if (room == null) {
			System.out.println("Error: room_util.getSelectedRoom returned null.");
			request.setAttribute("errorMessage", "room detail not available.");
			request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
			return; // Stop further execution
		}
		if (room.isEmpty()) {
			System.out.println("No rooms retrieved from the utility.");
			request.setAttribute("errorMessage", "room detail not available.");
			request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
			return; // Stop further execution
		}
		// Log retrieved room details
		for (rooms rom : room) {
			System.out.println("Room ID: " + rom.getRoomId());
		}
		// Fetch all services
		List<Services> services = Services_util.getAllServices();
		if (services == null) {
			System.out.println("Error: Services_util.getAllServices returned null.");
			request.setAttribute("errorMessage", "services not available.");
			request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
			return; // Stop further execution
		}
		if (services.isEmpty()) {
			System.out.println("No services retrieved from the utility.");
			request.setAttribute("errorMessage", "services not available.");
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
	}}