package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.Message_util;
import com.util.Services_util;


@WebServlet("/AddService")
public class AddService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String serviceName = request.getParameter("serviceName");
        String description = request.getParameter("description");
        double price = Double.parseDouble(request.getParameter("price"));
        String midStr = request.getParameter("m_id");
        int m_id = Integer.parseInt(midStr);




        boolean success = Services_util.insertService(serviceName, description, price,m_id);
        
        if (success) {           
	            request.getRequestDispatcher("/AServiceList").forward(request, response);            
            
        } else {
            // If chat was not inserted, pass an error message
            request.setAttribute("errorMessage", "Sorry, can't send message");

            // Forward the request to the error page (errorPage.jsp)
            request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
        }
	}

}
