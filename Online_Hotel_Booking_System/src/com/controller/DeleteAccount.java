package com.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.user_util;

@WebServlet("/DeleteAccount")
public class DeleteAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String useridStr = request.getParameter("uId");
		int userid = Integer.parseInt(useridStr);
		
		boolean isDeleted = user_util.deleteUserByRuId(userid);
		if(isDeleted) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("logout");
            dispatcher.forward(request, response);
		}
		
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
