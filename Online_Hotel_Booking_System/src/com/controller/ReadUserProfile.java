package com.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Booking;
import com.model.BookingWithPayments;
import com.model.Payment;
import com.model.ProfileUser;
import com.util.Booking_util;
import com.util.Payment_util;
import com.util.user_util;

/**
 * Servlet implementation class ReadUserProfile
 */
@WebServlet("/ReadUserProfile")
public class ReadUserProfile extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdStr = request.getParameter("userId");
        
        // Parse userId and handle potential NumberFormatException
        int userId = 0;
        try {
            userId = Integer.parseInt(userIdStr);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid user ID format.");
            request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
            return; // Exit method after forwarding
        }
		Object userIdAttr = request.getAttribute("UuserId");

		if (userIdAttr instanceof Integer) {
			userId = (Integer) userIdAttr; // Safely cast if it's an Integer
		}
		
		System.out.println(userId);

        ProfileUser user = user_util.getUserDetailsByRuId(userId);
        System.out.println("User name: " + (user != null ? user.getName() : "User not found"));

        List<Booking> bookings = Booking_util.getBookingDetailsByRuId(userId);
        System.out.println("Number of bookings found: " + (bookings != null ? bookings.size() : 0));

        List<BookingWithPayments> bookingsWithPayments = new ArrayList<>();

        // Populate bookings with payments
        for (Booking booking : bookings) {
            List<Payment> payments = Payment_util.getAllPaymentDetails(booking.getId()); // Pass the booking ID
            System.out.println("Payments found for booking ID " + booking.getId() + ": " + payments.size());
            bookingsWithPayments.add(new BookingWithPayments(booking, payments));
        }
        
        // Check if user and bookingsWithPayments are valid
        if (user != null && bookingsWithPayments != null && !bookingsWithPayments.isEmpty()) {
            // Set objects as attributes to be used in the JSP
            request.setAttribute("user", user);
            request.setAttribute("bookingsWithPayments", bookingsWithPayments);
            
            // Forward to the user profile JSP
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/userProfile.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Unable to view the user profile at this time.");
            request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
