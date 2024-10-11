package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Booking;
import com.util.BookingServices_util;
import com.util.Booking_util;
import com.model.BookingServices;

@WebServlet("/UpdateBookingRead")
public class UpdateBookingRead extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String bIdString = request.getParameter("bId"); // Retrieve bId as a String
        int bid = Integer.parseInt(bIdString); // Convert bId to an integer

        System.out.println("update read booking ID: " + bid);

        Booking booking = Booking_util.getBookingDetails(bid);
        List<Integer> serviceIds = BookingServices_util.getBookedServiceIdsByBookingId(bid);
        List<BookingServices> services = BookingServices_util.getBookingServices(bid);

        // Check if the booking exists
        if (booking != null) {
        	System.out.println("Booking found: " + booking);
        	
        	 boolean delete = BookingServices_util.deleteBookingServices(bid);
        	 
        	 System.out.println(delete);
        	
        	int roomId = booking.getR_id(); // Should give you the room ID
            int userId = booking.getRu_id(); // Should give you the user ID

            System.out.println("Room ID: " + roomId); // Check what this prints
            System.out.println("User ID: " + userId); // Check what this prints
            
            request.setAttribute("UroomId", roomId);
            request.setAttribute("UuserId", userId);
       
        	
            request.setAttribute("booking", booking);
            request.setAttribute("Rservices", services);
            // Initialize default values for all service selections
            request.setAttribute("1Service", "no");
            request.setAttribute("2Service", "no");
            request.setAttribute("3Service", "no");
            request.setAttribute("4Service", "no");
            request.setAttribute("5Service", "no");
            request.setAttribute("6Service", "no");

            // Check which service IDs are in the list and set the corresponding attributes
            for (Integer id : serviceIds) {
                switch (id) {
                    case 1:
                        request.setAttribute("1Service", "yes");
                        break;
                    case 2:
                        request.setAttribute("2Service", "yes");
                        break;
                    case 3:
                        request.setAttribute("3Service", "yes");
                        break;
                    case 4:
                        request.setAttribute("4Service", "yes");
                        break;
                    case 5:
                        request.setAttribute("5Service", "yes");
                        break;
                    case 6:
                        request.setAttribute("6Service", "yes");
                        break;
                    default:
                        // Handle cases where the id is not between 1 and 6 if needed
                        break;
                }
            }
           
            System.out.println(roomId + userId);
          

            // Forward the request to the booking details JSP page
            request.getRequestDispatcher("/roomDetails").forward(request, response);
        } else {
            // If booking not found, set an error message and forward to an error page
            request.setAttribute("errorMessage", "Unknown error, sorry for the inconvenience");
            request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Call doGet method for handling POST requests
        doGet(request, response);
    }
}
