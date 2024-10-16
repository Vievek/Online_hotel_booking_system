package com.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Booking;
import com.util.Booking_util;


@WebServlet("/listBookings")
public class listBookings extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Booking> bookingList = null;
        bookingList = Booking_util.getAllBookingDetails();

        // Set the bookings list as an attribute in the request
        request.setAttribute("bookingList", bookingList);

        // Forward to JSP page for display
        request.getRequestDispatcher("views/ABookingList.jsp").forward(request, response);
	}

}
