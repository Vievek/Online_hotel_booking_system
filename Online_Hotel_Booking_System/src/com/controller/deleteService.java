package com.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.Services_util;


@WebServlet("/deleteService")
public class deleteService extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");

        if (id != null) {
            try {
                int services_id = Integer.parseInt(id);
                // Call the deleteService method
                boolean success = Services_util.deleteService(services_id);
                
                // Redirect or forward based on success
                if (success) {
                    // Redirect to a service list or success page
                    response.sendRedirect(request.getContextPath() + "/AServiceList");
                } else {
                    // Set an error message and forward to an error page
                    request.setAttribute("errorMessage", "Failed to delete the service.");
                    request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
                }
            } catch (NumberFormatException e) {
                // Handle case where the id is not a valid integer
                request.setAttribute("errorMessage", "Invalid service ID.");
                request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
            }
        } else {
            // Handle case where services_id is not provided
            request.setAttribute("errorMessage", "Service ID is required.");
            request.getRequestDispatcher("views/errorPage.jsp").forward(request, response);
        }

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
