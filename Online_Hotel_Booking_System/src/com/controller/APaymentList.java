package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Booking;
import com.model.Payment;
import com.util.Booking_util;
import com.util.Payment_util;

@WebServlet("/APaymentList")
public class APaymentList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Payment> paymentList  = null;
		paymentList  = Payment_util.getAllPaymentDetails();
		for (Payment payment : paymentList) {
            System.out.println("Payment ID: " + payment.getpId());
           
        }

        // Set the bookings list as an attribute in the request
        request.setAttribute("paymentList", paymentList );

        // Forward to JSP page for display
        request.getRequestDispatcher("views/APaymentList.jsp").forward(request, response);
	}

}
