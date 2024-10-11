package com.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.util.user_util;

@WebServlet("/UpdateUserProfile")
@MultipartConfig
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
        

        // Handle file upload
        Part filePart = request.getPart("image"); // Retrieves the image file part

        String imagePath = null; // This will store the image path if uploaded

        if (filePart != null && filePart.getSize() > 0) {
            // File is uploaded
            String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString(); // Get the image file name

            // Define the path to save the uploaded file
            String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir(); // Create directory if not exists
            }

            // Save the file in the server's "uploads" folder
            String filePath = uploadPath + File.separator + fileName;
            filePart.write(filePath); // Write the file

            // Now store the relative path in the database (e.g., "uploads/imageName.jpg")
            imagePath = "uploads/" + fileName;
        }

        // You would typically update the user in the database with or without the image path
        boolean updated = user_util.updateUser(userid, name, email, phone, username, password, imagePath);

        if (updated) {
            request.setAttribute("UuserId", ruseridStr);
            System.out.println("User profile updated successfully.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/home.jsp");
            dispatcher.forward(request, response);
        } else {
            System.out.println("Failed to update user profile.");
            request.setAttribute("errorMessage", "Failed to update user profile");
			RequestDispatcher dis = request.getRequestDispatcher("views/errorPage.jsp");
			dis.forward(request, response);
        }
    }
}
