package com.controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.util.user_util;

@WebServlet("/UpdateUserProfile")
public class UpdateUserProfile extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        // Retrieve form fields
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        System.out.println("phone: " + phone);

        String useridStr = request.getParameter("uid");
        System.out.println(useridStr);
        int userid = Integer.parseInt(useridStr);
        
        String ruseridStr = request.getParameter("ruid");

        // Update the user in the database without an image path
        boolean updated = user_util.updateUser(userid, name, email, phone, username, password);

        if (updated) {
            request.setAttribute("UuserId", ruseridStr);
            System.out.println("User profile updated successfully.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("/");
            dispatcher.forward(request, response);
        } else {
            System.out.println("Failed to update user profile.");
            request.setAttribute("errorMessage", "Failed to update user profile");
            RequestDispatcher dis = request.getRequestDispatcher("views/errorPage.jsp");
            dis.forward(request, response);
        }
    }
}
