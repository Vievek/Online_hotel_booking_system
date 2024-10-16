package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.Chat_util;


@WebServlet("/WCreateChat")
public class WCreateChat extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 String w_id = request.getParameter("Id");
		 String M_id = request.getParameter("m_id");


	        int WId = 0;
	        int MId = 0;

	        try {
	        	WId = Integer.parseInt(w_id);
	            System.out.println(WId);
	            MId = Integer.parseInt(M_id);
	            System.out.println(MId);
	        } catch (NumberFormatException e) {
	            request.setAttribute("errorMessage", "Invalid User ID ");
	            request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
	            return;
	        }
	        
	        int chatId = Chat_util.insertChat(MId, WId);
	     // Check if the chat was inserted successfully
	        if (chatId != -1) {
	            // If chat was inserted, pass the chatId as an attribute to the next servlet/page
	            request.setAttribute("chatId", chatId);

	            // Forward the request to the next servlet/page (e.g., ChatDetailsServlet)
	            request.getRequestDispatcher("/getChatList").forward(request, response);
	            
	        } else {
	            // If chat was not inserted, pass an error message
	            request.setAttribute("errorMessage", "Sorry, can't start chat");

	            // Forward the request to the error page (errorPage.jsp)
	            request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
	        }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
