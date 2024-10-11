package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.ProfileUser;
import com.util.user_util;

/**
 * Servlet implementation class ShowEditUser
 */
@WebServlet("/ShowEditUser")
public class ShowEditUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String useridStr = request.getParameter("uId");
		int userid = Integer.parseInt(useridStr);
		
		System.out.println(userid);
		
		ProfileUser user = user_util.getUserDetailsByRuId(userid);
        System.out.println("User name: " + (user != null ? user.getName() : "User not found"));
        
        if (user != null ) {
            request.setAttribute("user", user);
            
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/updateProfile.jsp");
            dispatcher.forward(request, response);
        } else {
            request.setAttribute("errorMessage", "Unable to edit the user profile at this time.");
            request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
        }
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
