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


@WebServlet("/AgetChatList")
public class AgetChatList extends HttpServlet {
	private static final long serialVersionUID = 1L;
		       
		    
			protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				// Try to get userId from the request parameter first
				String userid = request.getParameter("Id");

				// If the parameter is null or empty, try to get it as an attribute
				if (userid == null || userid.isEmpty()) {
				    Object userIdAttribute = request.getAttribute("Id");
				    
				    // Check if the attribute is not null and is of String or can be cast to String
				    if (userIdAttribute != null) {
				        userid = userIdAttribute.toString();
				    }
				}

				int userId = 0;

				try {
				    // Convert the string userId to an integer
				    userId = Integer.parseInt(userid);
				    System.out.println(userId);
				} catch (NumberFormatException e) {
				    // Handle invalid userId format
				    request.setAttribute("errorMessage", "Invalid User ID ");
				    request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
				    return;
				}

			        
			        List<Chat> chats = Chat_util.getChatsByManagerId(userId);
			        request.setAttribute("chats", chats); 
			        System.out.println("chat size"+chats.size());
			        request.setAttribute("origin", "Aservlet");
		            request.getRequestDispatcher("GetChatMessage").forward(request, response);
		         

			}

			
			protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				doGet(request, response);
			}

		}



