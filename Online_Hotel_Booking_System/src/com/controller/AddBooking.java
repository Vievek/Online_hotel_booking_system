package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.BookingServices_util;
import com.util.Booking_util;
import com.util.Favourite_util;


@WebServlet("/AddBooking")
public class AddBooking extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieve and process the form data for the booking details
        String userid = request.getParameter("userId");
        String roomid = request.getParameter("roomId");
        
        int userId = Integer.parseInt(userid);
        int roomId = Integer.parseInt(roomid);
        
        String checkInDate = request.getParameter("checkInDate");
        String checkOutDate = request.getParameter("checkOutDate");
        
        System.out.println(userId+roomId+checkInDate+checkOutDate);
		
		// Spa
        String spaDate = request.getParameter("1Date");
        String spaTimeStart = request.getParameter("1TimeStart");
        String spaTimeEnd = request.getParameter("1TimeEnd");
        String spaSelected = request.getParameter("1Selected") != null ? "Yes" : "No";

        // Cleaning
        String cleaningDate = request.getParameter("2Date");
        String cleaningTimeStart = request.getParameter("2TimeStart");
        String cleaningTimeEnd = request.getParameter("2TimeEnd");
        String cleaningSelected = request.getParameter("2Selected") != null ? "Yes" : "No";

        // Laundry
        String laundryDate = request.getParameter("3Date");
        String laundryTimeStart = request.getParameter("3TimeStart");
        String laundryTimeEnd = request.getParameter("3TimeEnd");
        String laundrySelected = request.getParameter("3Selected") != null ? "Yes" : "No";

        // Fitness Services
        String fitnessDate = request.getParameter("4Date");
        String fitnessTimeStart = request.getParameter("4TimeStart");
        String fitnessTimeEnd = request.getParameter("4TimeEnd");
        String fitnessSelected = request.getParameter("4Selected") != null ? "Yes" : "No";

        // Pet Services
        String petDate = request.getParameter("5Date");
        String petTimeStart = request.getParameter("5TimeStart");
        String petTimeEnd = request.getParameter("5TimeEnd");
        String petSelected = request.getParameter("5Selected") != null ? "Yes" : "No";

        // Private Chef or Dining
        String chefDate = request.getParameter("6Date");
        String chefTimeStart = request.getParameter("6TimeStart");
        String chefTimeEnd = request.getParameter("6TimeEnd");
        String chefSelected = request.getParameter("6Selected") != null ? "Yes" : "No";

        // Retrieve room price, total service cost, and total cost
        String roomPriceStr = request.getParameter("roomPrice");
        String totalServiceCostStr = request.getParameter("totalServiceCost");
        String totalCostStr = request.getParameter("totalCost");


        // Now parse the values safely
        double totalServiceCost = totalServiceCostStr != null && !totalServiceCostStr.isEmpty() ? 
            Double.parseDouble(totalServiceCostStr) : 0;
        double totalCost = totalCostStr != null && !totalCostStr.isEmpty() ? 
            Double.parseDouble(totalCostStr) : 0;
        double roomPrice = roomPriceStr != null && !roomPriceStr.isEmpty() ? 
            Double.parseDouble(roomPriceStr) : 0;


        // Log the total costs if necessary (for debugging)
        System.out.println("Room Price: " + roomPrice);
        System.out.println("Total Service Cost: " + totalServiceCost);
        System.out.println("Total Cost: " + totalCost);
        
        
        int bid = Booking_util.insertBooking(roomPriceStr,totalServiceCostStr,totalCostStr,checkInDate,checkOutDate,userId,roomId);
		
        if (bid > 0) {
	        if (spaSelected.equals("Yes")) {
	            int bsid = BookingServices_util.insertBookingServices( spaDate, spaTimeStart, spaTimeEnd,1,bid);
	        	System.out.println(spaDate+ spaTimeStart+ spaTimeEnd);
	        }
	        if (cleaningSelected.equals("Yes")) {
	        	int bsid = BookingServices_util.insertBookingServices( cleaningDate, cleaningTimeStart, cleaningTimeEnd,2 ,bid);
	        	System.out.println( cleaningDate+ cleaningTimeStart+ cleaningTimeEnd);
	        }
	        if (laundrySelected.equals("Yes")) {
	        	int bsid = BookingServices_util.insertBookingServices( laundryDate, laundryTimeStart, laundryTimeEnd,3,bid);
	        	System.out.println(laundryDate+ laundryTimeStart +laundryTimeEnd);
	        }
	        if (fitnessSelected.equals("Yes")) {
	        	int bsid = BookingServices_util.insertBookingServices( fitnessDate, fitnessTimeStart, fitnessTimeEnd,4,bid);
	        	System.out.println(fitnessDate+ fitnessTimeStart+ fitnessTimeEnd);
	        }
	        if (petSelected.equals("Yes")) {
	        	int bsid = BookingServices_util.insertBookingServices( petDate, petTimeStart, petTimeEnd,5,bid);
	        	System.out.println(petDate+ petTimeStart+ petTimeEnd);
	        }
	        if (chefSelected.equals("Yes")) {
	        	int bsid = BookingServices_util.insertBookingServices(chefDate, chefTimeStart, chefTimeEnd,6,bid);
	        }
	    	// If everything is correct, redirect to readBooking with bid as an attribute
			request.setAttribute("bid", bid);
			request.getRequestDispatcher("ReadBooking").forward(request, response);
		} else {
			// If booking failed, navigate to the error page with an error message
			request.setAttribute("errorMessage", "Booking failed. Please try again.");
			request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
		}
	
	}

}
