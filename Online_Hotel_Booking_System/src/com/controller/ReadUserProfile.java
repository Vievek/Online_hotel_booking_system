package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Booking;
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
		
		int userId = Integer.parseInt(userIdStr);
		System.out.println(userId);
		
		ProfileUser user = user_util.getUserDetailsByRuId(userId);
		System.out.println("user name"+user.getName());
		
        Booking booking = Booking_util.getBookingDetailsByRuId(userId);
        int bid = booking.getId();
        System.out.println("bid  "+bid);
        
        Payment paymentDetails = Payment_util.getPaymentDetails(bid); // Pass the b_id
        System.out.println("paymentDetails remaining amount"+paymentDetails.getRemainingAmount());
        
        if (user != null && booking != null && paymentDetails != null) {
            // Set objects as attributes to be used in the JSP
            request.setAttribute("user", user);
            request.setAttribute("booking", booking);
            request.setAttribute("paymentDetails", paymentDetails);
            
            // Forward to the success page (replace 'successPage.jsp' with your actual JSP page)
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/userProfile.jsp");
            dispatcher.forward(request, response);
        } else {
        	request.setAttribute("errorMessage", "unknown error can't see the user profile now");
            request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
        }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
