<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>Room Photo Upload</title>
</head>
<body>
    <h1>Upload Room Photo</h1>
    <form action="uploadRoom" method="post" enctype="multipart/form-data">
        <label for="roomName">Room Name:</label>
        <input type="text" id="roomName" name="roomName" required><br>

        <label for="roomImage">Select Image:</label>
        <input type="file" id="roomImage" name="roomImage" accept="image/*" required><br>

        <input type="submit" value="Upload">
    </form>

    <%
        String status = request.getParameter("status");
        if ("success".equals(status)) {
            out.println("<p>Room photo uploaded successfully!</p>");
        }
    %>
    
    
    <a href="RoomDisplayServlet">View Rooms</a>
    
</body>
</html>
