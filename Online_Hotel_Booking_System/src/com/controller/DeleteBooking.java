package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.BookingServices_util;


@WebServlet("/DeleteBooking")
public class DeleteBooking extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String bIdString = request.getParameter("bId"); // Retrieve bId as a String
	        int bid = Integer.parseInt(bIdString); // Convert bId to an integer
	        
	        System.out.println("Deleting booking ID: " + bid);

	        // Call the delete method
	        boolean delete = BookingServices_util.deleteBookingServices(bid);

	        if (delete) {
	            // If deletion is successful, navigate to a success page
                request.getRequestDispatcher("/").forward(request, response);
	        } else {
	            // If deletion fails, set an error message and navigate to an error page
	            request.setAttribute("errorMessage", "Failed to delete booking service. Please try again.");
	            request.getRequestDispatcher("views/errorPage.jsp").forward(request, response); // Change this to your error page
	        }	        
	    }
	 
	 protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        // Call doGet() to handle the POST request in the same way as GET
	        doGet(request, response);
	    }

}
