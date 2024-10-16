package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.rooms;
import com.util.rooms_util;

@MultipartConfig
@WebServlet("/ShowRoomEdit")
public class ShowRoomEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 int roomId = Integer.parseInt(request.getParameter("roomId"));
		  List<rooms> room = rooms_util.getSelectedRoom(roomId);
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
	        if (room != null) {
	            request.setAttribute("room", room);
	            RequestDispatcher dispatcher = request.getRequestDispatcher("views/AroomForm.jsp");
	            dispatcher.forward(request, response);
	        }
	    
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
