package com.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.BookingServices_util;
import com.util.Booking_util;

@WebServlet("/AddBooking")
public class AddBooking extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve and process the form data for the booking details
        String userid = request.getParameter("userId");
        String roomid = request.getParameter("roomId");

        int userId = 0;
        int roomId = 0;

        try {
            userId = Integer.parseInt(userid);
            roomId = Integer.parseInt(roomid);
        } catch (NumberFormatException e) {
            request.setAttribute("errorMessage", "Invalid User ID or Room ID.");
            request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
            return;
        }

        String checkInDate = request.getParameter("checkInDate");
        String checkOutDate = request.getParameter("checkOutDate");

        // Retrieve service details
        String spaDate = request.getParameter("1Date");
        String spaTimeStart = request.getParameter("1TimeStart");
        String spaTimeEnd = request.getParameter("1TimeEnd");
        String spaSelected = request.getParameter("1Selected") != null ? "Yes" : "No";

        String cleaningDate = request.getParameter("2Date");
        String cleaningTimeStart = request.getParameter("2TimeStart");
        String cleaningTimeEnd = request.getParameter("2TimeEnd");
        String cleaningSelected = request.getParameter("2Selected") != null ? "Yes" : "No";

        String laundryDate = request.getParameter("3Date");
        String laundryTimeStart = request.getParameter("3TimeStart");
        String laundryTimeEnd = request.getParameter("3TimeEnd");
        String laundrySelected = request.getParameter("3Selected") != null ? "Yes" : "No";

        String fitnessDate = request.getParameter("4Date");
        String fitnessTimeStart = request.getParameter("4TimeStart");
        String fitnessTimeEnd = request.getParameter("4TimeEnd");
        String fitnessSelected = request.getParameter("4Selected") != null ? "Yes" : "No";

        String petDate = request.getParameter("5Date");
        String petTimeStart = request.getParameter("5TimeStart");
        String petTimeEnd = request.getParameter("5TimeEnd");
        String petSelected = request.getParameter("5Selected") != null ? "Yes" : "No";

        String chefDate = request.getParameter("6Date");
        String chefTimeStart = request.getParameter("6TimeStart");
        String chefTimeEnd = request.getParameter("6TimeEnd");
        String chefSelected = request.getParameter("6Selected") != null ? "Yes" : "No";

        // Retrieve room price, total service cost, and total cost
        String roomPriceStr = request.getParameter("roomPrice");
        String totalServiceCostStr = request.getParameter("totalServiceCost");
        String totalCostStr = request.getParameter("totalCost");

        double totalServiceCost = parseDouble(totalServiceCostStr);
        double totalCost = parseDouble(totalCostStr);
        double roomPrice = parseDouble(roomPriceStr);

        int bid = Booking_util.insertBooking(roomPriceStr, totalServiceCostStr, totalCostStr, checkInDate, checkOutDate, userId, roomId);

        if (bid > 0) {
            boolean success = true;

            // Process each selected service
            if (spaSelected.equals("Yes")) {
                success &= BookingServices_util.insertBookingServices(formatDate(spaDate), spaTimeStart, spaTimeEnd, 1, bid) > 0;
            }
            if (cleaningSelected.equals("Yes")) {
                success &= BookingServices_util.insertBookingServices(formatDate(cleaningDate), cleaningTimeStart, cleaningTimeEnd, 2, bid) > 0;
            }
            if (laundrySelected.equals("Yes")) {
                success &= BookingServices_util.insertBookingServices(formatDate(laundryDate), laundryTimeStart, laundryTimeEnd, 3, bid) > 0;
            }
            if (fitnessSelected.equals("Yes")) {
                success &= BookingServices_util.insertBookingServices(formatDate(fitnessDate), fitnessTimeStart, fitnessTimeEnd, 4, bid) > 0;
            }
            if (petSelected.equals("Yes")) {
                success &= BookingServices_util.insertBookingServices(formatDate(petDate), petTimeStart, petTimeEnd, 5, bid) > 0;
            }
            if (chefSelected.equals("Yes")) {
                success &= BookingServices_util.insertBookingServices(formatDate(chefDate), chefTimeStart, chefTimeEnd, 6, bid) > 0;
            }

           
                request.setAttribute("bid", bid);
                request.getRequestDispatcher("ReadBooking").forward(request, response);
           
        } else {
            request.setAttribute("errorMessage", "Booking failed. Please try again.");
            request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
        }
    }

    private String formatDate(String dateStr) {
        SimpleDateFormat inputFormat = new SimpleDateFormat("MMM dd, yyyy"); // Input date format (e.g., "Jun 10, 2024")
        SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");  // MySQL date format
        try {
            Date date = inputFormat.parse(dateStr);
            return outputFormat.format(date);
        } catch (ParseException e) {
            e.printStackTrace();
            return null; // Handle this case as needed
        }
    }


    private double parseDouble(String value) {
        if (value != null && !value.isEmpty()) {
            try {
                return Double.parseDouble(value);
            } catch (NumberFormatException e) {
                // Handle parsing error, log it, or return a default value
            }
        }
        return 0; // Return 0 if parsing fails or value is null/empty
    }
}
