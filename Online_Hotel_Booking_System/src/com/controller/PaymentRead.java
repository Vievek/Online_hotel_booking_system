package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Booking;
import com.model.Payment;
import com.util.Booking_util;
import com.util.Payment_util;


@WebServlet("/PaymentRead")
public class PaymentRead extends HttpServlet {
	private static final long serialVersionUID = 1L;
  

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bIdString = request.getParameter("bId"); // Retrieve bId as a String
        int bid = Integer.parseInt(bIdString); // Convert bId to an integer
        
        System.out.println("payment read booking ID: " + bid);

        Booking booking = Booking_util.getBookingDetails(bid);
        
        if (booking != null) {
            request.setAttribute("booking", booking);
            
            Payment paymentDetails = Payment_util.getPaymentDetails(bid); // Pass the b_id
            
            String total_amount=booking.getTotal_amount();  
            double totalAmountDouble = Double.parseDouble(total_amount);
            
            if (paymentDetails != null) {
                String remaining_amount = paymentDetails.getRemainingAmount();
                double remainingAmountDouble = Double.parseDouble(remaining_amount);
                System.out.println("remainingAmountDouble  "+ remainingAmountDouble);
                
                request.setAttribute("remaining amount",remainingAmountDouble );

            } else {
            	request.setAttribute("remaining amount", totalAmountDouble );
            }


            request.getRequestDispatcher("views/payment.jsp").forward(request, response);
        } else {
            // If booking not found, set an error message and forward to an error page
            request.setAttribute("errorMessage", "Unknown error, sorry for the inconvenience");
            request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
        }
        
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
