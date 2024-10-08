package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.Favourite_util;


@WebServlet("/addFavouriteRoom")
public class addFavouriteRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get roomId and userId from request parameters
        String roomId = request.getParameter("roomId");
        String userId = request.getParameter("userId");
        
        System.out.println(roomId + userId);
        boolean isTrue;
        
        int RoomId = Integer.parseInt(roomId);
        int UserId = Integer.parseInt(userId);
		
		isTrue = Favourite_util.insertFavouriteRoom(RoomId,UserId);
		
		System.out.println(isTrue);
		if(isTrue == true) {
			RequestDispatcher dis = request.getRequestDispatcher("getAllrooms");
			dis.forward(request, response);
		}else {
	        request.setAttribute("errorMessage", "Already in the favourites");
			RequestDispatcher dis = request.getRequestDispatcher("views/errorPage.jsp");
			dis.forward(request, response);
		}

        
        }
}
