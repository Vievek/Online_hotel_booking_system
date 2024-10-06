package com.example.servelet;

import com.example.dao.RoomDAO;
import com.example.model.Room;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.*;
import java.io.File;
import java.io.IOException;

@MultipartConfig
public class RoomUploadServelet extends HttpServlet {
    private static final String UPLOAD_DIR = "images";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String roomName = request.getParameter("roomName");
        Part filePart = request.getPart("roomImage");

        // Directory to store uploaded images
        String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) uploadDir.mkdir();

        // Get file name and save the image
        String fileName = filePart.getSubmittedFileName();
        String filePath = uploadPath + File.separator + fileName;
        filePart.write(filePath);

        // Define the image path relative to the web app
        String imagePath = UPLOAD_DIR + File.separator + fileName;

        // Save room details to the database
        boolean isSaved = RoomDAO.saveRoom(roomName, imagePath);

        // Redirect to JSP with a status message
        if (isSaved) {
            response.sendRedirect("roomUpload.jsp?status=success");
        } else {
            response.sendRedirect("roomUpload.jsp?status=error");
        }
    }
}
