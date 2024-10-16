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
@WebServlet("/EditRooms")
public class EditRooms extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		 // Retrieve form fields
	    String roomIdStr = request.getParameter("roomId");
	    System.out.println(roomIdStr);
	    int roomId = -1; // Default value if roomId is invalid

	    // Check if roomId is present and valid
	    if (roomIdStr != null && !roomIdStr.trim().isEmpty()) {
	        try {
	            roomId = Integer.parseInt(roomIdStr);
	        } catch (NumberFormatException e) {
	            response.sendRedirect("error.jsp?message=Invalid room ID");
	            return; // Stop further processing if roomId is invalid
	        }
	    }
        String roomType = request.getParameter("roomType");
        String description = request.getParameter("description");
        int noOfPerson = Integer.parseInt(request.getParameter("noOfPerson"));
        double price = Double.parseDouble(request.getParameter("price"));
        String availabilityStatus = request.getParameter("availabilityStatus");

        // Handle file upload (optional)
        String[] imagePaths = handleFileUpload(request, new String[] {"img1", "img2", "img3", "img4"});

        // Update room data
        boolean isUpdated = rooms_util.updateRoom(roomId, roomType, description, noOfPerson, price, availabilityStatus, imagePaths);
        
        if (isUpdated) {
            response.sendRedirect("getAllrooms?page=admin");
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
