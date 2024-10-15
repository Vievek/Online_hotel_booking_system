package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Chat;
import com.util.Chat_util;


@WebServlet("/getChatList")
public class getChatList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String userid = request.getParameter("userId");

	        int userId = 0;

	        try {
	            userId = Integer.parseInt(userid);
	            System.out.println(userId);
	        } catch (NumberFormatException e) {
	            request.setAttribute("errorMessage", "Invalid User ID ");
	            request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
	            return;
	        }
	        
	        List<Chat> chats = Chat_util.getChatsByWorkerId(userId);
	        request.setAttribute("chats", chats);            
            request.getRequestDispatcher("views/Wchat.jsp").forward(request, response);

	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
