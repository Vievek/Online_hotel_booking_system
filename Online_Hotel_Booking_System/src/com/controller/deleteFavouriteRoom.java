package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.Favourite_util;

@WebServlet("/deleteFavouriteRoom")
public class deleteFavouriteRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
   

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String FId = request.getParameter("FId");
        String userId = request.getParameter("userId");

		System.out.println(FId);
		System.out.println("uid"+userId);
		 
		 boolean isTrue;
			
			isTrue = Favourite_util.deletefavroom(FId);
			
			System.out.println(isTrue);
			
			if(isTrue == true) {
				String redirectUrl = "favouriteRooms?uid=" + userId; // Create the URL with the user ID as a parameter
			    RequestDispatcher dis = request.getRequestDispatcher(redirectUrl);
				dis.forward(request, response);
			}else {
				request.setAttribute("errorMessage", "Can't delete from favourites");
				RequestDispatcher dis = request.getRequestDispatcher("views/errorPage.jsp");
				dis.forward(request, response);
			}
	}

}
