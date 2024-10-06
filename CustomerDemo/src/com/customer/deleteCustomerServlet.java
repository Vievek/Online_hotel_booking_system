package com.customer;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class deleteCustomerServlet
 */
@WebServlet("/deleteCustomerServlet")
public class deleteCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
      
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("cusid");
		
		boolean isTrue;
		
		isTrue = CustomerDButil.deletecustomer(id);
		
		if(isTrue == true) {
			RequestDispatcher dis = request.getRequestDispatcher("success.jsp");
			dis.forward(request, response);
		}else {
			List<Customer> cusDetails = CustomerDButil.getCustomerDetails(id);
			request.setAttribute("cusDetails",cusDetails);
			
			RequestDispatcher dis = request.getRequestDispatcher("useraccount.jsp");
			dis.forward(request, response);
		}
	}

}
