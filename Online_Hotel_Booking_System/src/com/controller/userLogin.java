package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.registered_user;
import com.util.user_util;


@WebServlet("/userLogin")
public class userLogin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    String username = request.getParameter("uid");
	    String password = request.getParameter("psw");

	    try {
	        // Validate user credentials
	        List<registered_user> userDetails = user_util.validate(username, password);
	        System.out.println("userDetails size: " + userDetails.size());
	        
	        // Assuming you are getting only one user for the username and password
	        if (!userDetails.isEmpty()) {
	            registered_user user = userDetails.get(0); // Get the first user detail

	            // Print user details for debugging
	            System.out.println("registered_user ID: " + user.getId());
	            System.out.println("registered_user Name: " + user.getName());

	            // Set user details in request attributes
	            request.setAttribute("userDetails", user);
	            
	            // Check user role and navigate accordingly
	            String role = user.getRole(); // Assuming getRole() returns the user role

	            switch (role) {
	                case "User":
	                    // Navigate to home page for regular users
	                    request.getRequestDispatcher("views/home.jsp").forward(request, response);
	                    break;

	                case "Worker":
	                    // Navigate to worker dashboard
	                    request.getRequestDispatcher("views/workerDashboard.jsp").forward(request, response);
	                    break;

	                case "Admin":
	                    // Navigate to admin dashboard
	                    request.getRequestDispatcher("views/adminDashboard.jsp").forward(request, response);
	                    break;

	                default:
	                    // Handle unknown roles if necessary
	                    request.setAttribute("errorMessage", "Unknown role: " + role);
	                    request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
	                    break;
	            }
	        } else {
	            // If userDetails is empty, handle invalid credentials
	            request.setAttribute("errorMessage", "Invalid username or password");
	            request.getRequestDispatcher("views/Login.jsp").forward(request, response);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "An error occurred while processing your request.");
	        request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
	    }
	}

}
