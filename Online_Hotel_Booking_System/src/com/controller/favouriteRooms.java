package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Favourite;
import com.model.rooms;
import com.util.Favourite_util;


@WebServlet("/favouriteRooms")
public class favouriteRooms extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		// Retrieve the user ID from the request parameter
        String userIdStr = request.getParameter("uid");
        
        // Check if userId is not null or empty
        if ( userIdStr != null && !userIdStr.isEmpty()) {
            try {
            	int userId = Integer.parseInt(userIdStr);
                // Query the database to get the list of favourites for the user
                List<Favourite> favourites = Favourite_util.getFavouritesByUserId(userId);
                
                // Debug: Log room data to the server console
                if (favourites == null || favourites.isEmpty()) {
                    System.out.println("No rooms retrieved from the utility.");
                } else {
                    for (Favourite rom : favourites) {
                        System.out.println("Favourite Room ID: " + rom.getRoomId());
                    }
                }
                
                // Set the favourites as a request attribute
                request.setAttribute("favouritesList", favourites);
                
                // Forward the request to the Favourite.jsp page
                request.getRequestDispatcher("views/Favourite.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "An error occurred while retrieving favourites.");
                request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
            }
        } else {
            // If userId is invalid, redirect to an error page or show an error message
            request.setAttribute("errorMessage", "Invalid User ID.");
            request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
        }

	}
}
