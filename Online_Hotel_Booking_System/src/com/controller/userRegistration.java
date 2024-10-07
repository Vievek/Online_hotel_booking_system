package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.user_util;


@WebServlet("/userRegistration")
public class userRegistration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	
		String name = request.getParameter("name");
		String email = request.getParameter("email");
		String phone = request.getParameter("phone");
		String username = request.getParameter("uid");
		String password = request.getParameter("psw");
		
		boolean isTrue;
		
		isTrue = user_util.registerUser(name, email, phone, username, password);
				
				 if (isTrue) {
			            // Registration successful, pass a success message
			            request.setAttribute("message", "success");
			            RequestDispatcher dis = request.getRequestDispatcher("/views/Login.jsp");
			            dis.forward(request, response);
			        } else {
			        	// Registration failed, pass a failure message
			        	request.setAttribute("errorMessage", "Registration failed. Try diffrent Username."); // Set an error message
			        	RequestDispatcher dis = request.getRequestDispatcher("views/errorPage.jsp"); // Forward to errorPage
			        	dis.forward(request, response);

		}
	}

}
