<%@ page import="java.util.List" %>
<%@ page import="com.example.model.Room" %>
<%@ page import="com.example.dao.RoomDAO" %>
<%
    List<Room> roomList = RoomDAO.getRooms();
%>
<!DOCTYPE html>
<html>
<head>
    <title>Room Gallery</title>
</head>
<body>
    <h1>Room Gallery</h1>
    <div>
        <%
            for (Room room : roomList) {
        %>
            <div>
                <h2><%= room.getName() %></h2>
                <img src="<%= request.getContextPath() + "/" + room.getImagePath() %>" alt="<%= room.getName() %>" width="300">
            </div>
        <%
            }
        %>
    </div>
</body>
</html>
