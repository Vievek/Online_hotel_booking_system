package com.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.util.rooms_util;

@MultipartConfig
@WebServlet("/AddRoom")
public class AddRoom extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Retrieve form fields
        String roomType = request.getParameter("roomType");
        String description = request.getParameter("description");
        int noOfPerson = Integer.parseInt(request.getParameter("noOfPerson"));
        double price = Double.parseDouble(request.getParameter("price"));
        String availabilityStatus = request.getParameter("availabilityStatus");

        // Handle file upload (optional)
        String[] imagePaths = handleFileUpload(request, new String[] {"img1", "img2", "img3", "img4"});

        // Insert room data
        boolean isInserted = rooms_util.insertRoom(roomType, description, noOfPerson, price, availabilityStatus, imagePaths);
        
        if (isInserted) {
            response.sendRedirect("success.jsp");
        } else {
            response.sendRedirect("error.jsp");
        }
    }

    private String[] handleFileUpload(HttpServletRequest request, String[] fileNames) throws IOException, ServletException {
        String[] imagePaths = new String[fileNames.length];
        for (int i = 0; i < fileNames.length; i++) {
            Part filePart = request.getPart(fileNames[i]);
            if (filePart != null && filePart.getSize() > 0) {
                String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
                String uploadPath = getServletContext().getRealPath("") + File.separator + "uploads";
                File uploadDir = new File(uploadPath);
                if (!uploadDir.exists()) uploadDir.mkdir();
                String filePath = uploadPath + File.separator + fileName;
                filePart.write(filePath);
                imagePaths[i] = "uploads/" + fileName;
            }
        }
        return imagePaths;
	}

}
