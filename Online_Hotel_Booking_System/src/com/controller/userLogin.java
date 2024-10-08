package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	            System.out.println("registered_user Name: " + user.getUsername());

	            // Invalidate any existing session
	            request.getSession().invalidate();
	            
	            // Start a new session
	            HttpSession session = request.getSession(true); // Create a new session
	            session.setAttribute("userId", user.getId());
	            session.setAttribute("username", user.getUsername());
	            session.setMaxInactiveInterval(30 * 60); // Set session timeout to 30 minutes
	            
	            // Check user role and navigate accordingly
	            String role = user.getRole(); // Assuming getRole() returns the user role

	            switch (role) {
	                case "User":
	                	// Retrieve ru_id from registered_user table
                        int ruId = user_util.getRuIdByUserId(user.getId());
                        System.out.println(ruId);
                        session.setAttribute("ru_id", ruId);
	                    // Navigate to home page for regular users
	                    request.getRequestDispatcher("views/home.jsp").forward(request, response);
	                    break;

	                case "Worker":
	                	// Retrieve w_id from worker table
                        int wId = user_util.getWIdByUserId(user.getId());
                        session.setAttribute("w_id", wId);
	                    // Navigate to worker dashboard
	                    request.getRequestDispatcher("views/workerDashboard.jsp").forward(request, response);
	                    break;

	                case "Manager":
	                	// Retrieve m_id from manager table
                        int mId = user_util.getMIdByUserId(user.getId());
                        session.setAttribute("m_id", mId);
	                    // Navigate to Manager dashboard
	                    request.getRequestDispatcher("views/ManagerDashboard.jsp").forward(request, response);
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
	            request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        request.setAttribute("errorMessage", "An error occurred while processing your request.");
	        request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
	    }
	}

}
