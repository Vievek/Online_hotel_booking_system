package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		

        if (spaSelected.equals("Yes")) {
            //serviceDAO.saveService("Spa", spaDate, spaTimeStart, spaTimeEnd, spaSelected, booking.getBookingId());
        	System.out.println(spaDate+ spaTimeStart+ spaTimeEnd);
        }
        if (cleaningSelected.equals("Yes")) {
            //serviceDAO.saveService("Cleaning", cleaningDate, cleaningTimeStart, cleaningTimeEnd, cleaningSelected, booking.getBookingId());
        	System.out.println( cleaningDate+ cleaningTimeStart+ cleaningTimeEnd);
        }
        if (laundrySelected.equals("Yes")) {
            //serviceDAO.saveService("Laundry", laundryDate, laundryTimeStart, laundryTimeEnd, laundrySelected, booking.getBookingId());
        	System.out.println(laundryDate+ laundryTimeStart +laundryTimeEnd);
        }
        if (fitnessSelected.equals("Yes")) {
            //serviceDAO.saveService("Fitness Services", fitnessDate, fitnessTimeStart, fitnessTimeEnd, fitnessSelected, booking.getBookingId());
        	System.out.println(fitnessDate+ fitnessTimeStart+ fitnessTimeEnd);
        }
        if (petSelected.equals("Yes")) {
            //saveService("Pet Services", petDate, petTimeStart, petTimeEnd, petSelected, booking.getBookingId());
        	System.out.println(petDate+ petTimeStart+ petTimeEnd);
        }
        if (chefSelected.equals("Yes")) {
            //serviceDAO.saveService("Private Chef or Dining", chefDate, chefTimeStart, chefTimeEnd, chefSelected, booking.getBookingId());
        	System.out.println(chefDate+ chefTimeStart+ chefTimeEnd);
        }
	}

}
