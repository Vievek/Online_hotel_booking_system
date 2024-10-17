package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.model.Services;
import com.util.Services_util;


@WebServlet("/ShowServiceEdit")
public class ShowServiceEdit extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		System.out.println(id);
		  try {
		        // Parse the id to an integer
		        int servicesId = Integer.parseInt(id);
		        System.out.println("in show"+servicesId);
		        
		        // Retrieve the service using the id
		        Services service = Services_util.getServiceById(servicesId); // Assuming this method exists

		        if (service != null) {
		            // Forward to the service list if the service is found
		            System.out.println("Service in show: " + service.getName()); // Debugging line

		            request.setAttribute("service", service);
		            request.getRequestDispatcher("/views/addServices.jsp").forward(request, response);
		        } else {
		            // If the service is not found, set an error message
		            request.setAttribute("errorMessage", "Service not found.");
		            request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
		        }
		    } catch (NumberFormatException e) {
		        // Handle invalid ID format
		        request.setAttribute("errorMessage", "Invalid service ID.");
		        request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
		    } catch (Exception e) {
		        // Handle other exceptions
		        request.setAttribute("errorMessage", "An error occurred while retrieving the service.");
		        request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
		    }
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
