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


@WebServlet("/AgetuserByrole")
public class AgetuserByrole extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
        String role = request.getParameter("role");

        List<registered_user> Users = user_util.getUsersByRole(role);
        request.setAttribute("Users", Users);
        
        if ("Worker".equalsIgnoreCase(role)) {
           
            request.getRequestDispatcher("views/AWorkerList.jsp").forward(request, response);
        } else if ("User".equalsIgnoreCase(role)) {
            
            request.getRequestDispatcher("views/AguestList.jsp").forward(request, response);}

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
