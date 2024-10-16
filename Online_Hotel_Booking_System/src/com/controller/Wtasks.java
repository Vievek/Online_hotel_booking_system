package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.model.BookingServices;
import com.util.BookingServices_util;


@WebServlet("/Wtasks")
public class Wtasks extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
        Integer wId = (Integer) session.getAttribute("w_id");
        List<BookingServices> bookingServicesList = null;

        if (wId != null) {
            // Retrieve booking services for the specified worker ID
            bookingServicesList = BookingServices_util.getBookingServicesByWorker(wId);
            
            // Print for debugging
            if (bookingServicesList != null && !bookingServicesList.isEmpty()) {
                for (BookingServices service : bookingServicesList) {
                    System.out.println("Service Date: " + service.getDate());
                }
            } else {
                System.out.println("No services found for worker ID: " + wId);
            }

            // Set the booking services list as an attribute in the request
            request.setAttribute("bookingServicesList", bookingServicesList);
        } else {
            request.setAttribute("errorMessage", "Worker ID is not set in session.");
        }

 
        // Forward to JSP page for display
        request.getRequestDispatcher("views/workerDashboard.jsp").forward(request, response);
    
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
