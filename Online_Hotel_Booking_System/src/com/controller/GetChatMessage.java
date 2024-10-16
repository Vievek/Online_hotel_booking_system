package com.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Message;
import com.util.Message_util;


@WebServlet("/GetChatMessage")
public class GetChatMessage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Step 1: Retrieve chatId as an attribute (set by another servlet or JSP)
	    Object chatIdAttr = request.getAttribute("chatId");
	    
	    // Step 2: Retrieve chatId as a parameter (passed from a form or query string)
	    String chatIdParam = request.getParameter("chatId");

	    // Step 3: Initialize a variable to store the final chatId as an Integer
	    Integer IchatId = null;

	    try {
	        // Step 4: Check if the parameter is present and can be converted to Integer
	        if (chatIdParam != null) {
	            IchatId = Integer.parseInt(chatIdParam);  // Convert String to Integer
	        }
	        
	        // Step 5: If the attribute is an Integer and no valid parameter was provided, use the attribute
	        if (IchatId == null && chatIdAttr instanceof Integer) {
	            IchatId = (Integer) chatIdAttr;  // Safely cast the attribute if it's an Integer
	        }
	    } catch (NumberFormatException e) {
	        // Handle the case where chatIdParam could not be parsed to an integer
	        e.printStackTrace();
	    }

	    // Log or process the final chatId
	    System.out.println("Final chatId: " + IchatId);

	    // Continue processing with the IchatId, or handle the case where it's still null
	    if (IchatId != null) {
	    	List<Message> messages = Message_util.getAllMessagesByChatId(IchatId);
	    	request.setAttribute("messages", messages);  
	    	request.setAttribute("IchatId", IchatId);            
	    } 
	    request.getRequestDispatcher("views/Wchat.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
