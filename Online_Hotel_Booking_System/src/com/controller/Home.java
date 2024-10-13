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

@WebServlet("/")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<rooms> room = rooms_util.getAllrooms();
		List<rooms> highroom = rooms_util.gethighlyBookedrooms();
		List<rooms> highroom = rooms_util.RecentlySelectedRooms();

	        
	        // Debug: Log room data to the server console
	        if (room == null || room.isEmpty()) {
	            System.out.println("No rooms retrieved from the utility.");
	            request.setAttribute("errorMessage", "Issue in loading page");
	            request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
	        } else {
	            System.out.println("rooms retrieved from the utility.");
	            for (rooms rom : room) {
	                System.out.println("Room ID: " + rom.getRoomId());
	            }
	            request.setAttribute("room", room);
	    		request.setAttribute("highroom",highroom);

	            System.out.println("passing to home");
	            request.getRequestDispatcher("views/home.jsp").forward(request, response);
	        }
	        
	       
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
