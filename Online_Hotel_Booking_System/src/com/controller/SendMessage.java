package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.Message_util;


@WebServlet("/SendMessage")
public class SendMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String c_id = request.getParameter("chat_id");
		String s_id = request.getParameter("sender_id");
		String message = request.getParameter("message");
        String origin = request.getParameter("origin");



	        int chat_id = 0;
	        int sender_id = 0;

	        try {
	        	chat_id = Integer.parseInt(c_id);
	            System.out.println(chat_id);
	            sender_id = Integer.parseInt(s_id);
	            System.out.println(sender_id);
	        } catch (NumberFormatException e) {
	            request.setAttribute("errorMessage", "Invalid User ID ");
	            request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
	            return;
	        }
	        
	        boolean success = Message_util.insertMessage(chat_id, sender_id, message);
	        
	        if (success) {
	            // If chat was inserted, pass the chatId as an attribute to the next servlet/page
	            request.setAttribute("chatId", chat_id);
	            request.setAttribute("userId", sender_id);
	            System.out.println("chatId and userId after send msg "+chat_id+"  "+sender_id );
	            if ("Wjsp".equals(origin)) {
		            request.getRequestDispatcher("/getChatList").forward(request, response);
	            } else if ("Mjsp".equals(origin)) {
	                request.getRequestDispatcher("/AgetChatList").forward(request, response);
	            }
	            
	        } else {
	            // If chat was not inserted, pass an error message
	            request.setAttribute("errorMessage", "Sorry, can't send message");

	            // Forward the request to the error page (errorPage.jsp)
	            request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
	        }

	}

}
