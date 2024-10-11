package com.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/AddPayment")
public class AddPayment extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String bid = request.getParameter("bid");
		int bidInt = 0; // Initialize with a default value

		try {
		    bidInt = Integer.parseInt(bid);
		} catch (NumberFormatException e) {
		    e.printStackTrace(); // Handle invalid input, e.g., log or send an error message
		    // You can also return or throw an exception if the conversion fails
		}
		
		System.out.println("bidInt"+bidInt);

		
		String payedamount = request.getParameter("amount");
		String total = request.getParameter("total");
		String remaining = request.getParameter("Remaining");

		double payedAmountDouble = 0.0;
		double totalDouble = 0.0;
		double remainingDouble = 0.0;
		

		try {
		    payedAmountDouble = Double.parseDouble(payedamount);
		    totalDouble = Double.parseDouble(total);
		    remainingDouble = Double.parseDouble(remaining);
		} catch (NumberFormatException e) {
		    e.printStackTrace(); // Handle invalid input, e.g., log or send an error message
		    // Optionally, handle errors (set default values or throw an exception)
		}

		System.out.println("payedAmountDouble "+payedAmountDouble+" totalDouble  "+totalDouble+" remainingDouble  "+remainingDouble);

		remainingDouble -=payedAmountDouble;
		System.out.println(remainingDouble);
		
		Date currentDate = new Date();  // Get the current date
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");  // Format it as required
		String formattedDate = formatter.format(currentDate);  // Convert date to string
		
		System.out.println("formattedDate  "+ formattedDate);
	}

}
