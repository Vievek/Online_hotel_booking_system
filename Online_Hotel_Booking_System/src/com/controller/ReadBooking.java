package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Booking;
import com.model.Services;
import com.util.Booking_util;
import com.util.Services_util;


@WebServlet("/ReadBooking")
public class ReadBooking extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int bid = (int) request.getAttribute("bid");
		
		  Booking booking  = Booking_util.getBookingDetails(bid);
	        
		  if (booking != null) {
              // Set the booking details as request attributes
              request.setAttribute("booking", booking);
              
              // Forward the request to the booking details JSP page
              request.getRequestDispatcher("views/bookingDetails.jsp").forward(request, response);
          } else {
              // If booking not found, set an error message and forward to an error page
              request.setAttribute("errorMessage", "Booking not found for ID: " + bid);
              request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
          }
      

}
}
