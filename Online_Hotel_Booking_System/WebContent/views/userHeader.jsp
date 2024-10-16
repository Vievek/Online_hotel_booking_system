<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%
    // Retrieve user ID and username from session
    Integer userId = (Integer) session.getAttribute("ru_id");
    String username = (String) session.getAttribute("username");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<% if (userId != null && username != null) { %>
	   	<h1>Welcome, <%= username %>!</h1>
    	<p>Your user ID is: <%= userId %></p>
        <a href="${pageContext.request.contextPath}/?userId=${ru_id}" >home</a>    	
		<a href="${pageContext.request.contextPath}/favouriteRooms?uid=<%= userId %>">Favourite</a> 
		 <a href="${pageContext.request.contextPath}/logout">Logout</a>
		 <a href="${pageContext.request.contextPath}/ReadUserProfile?userId=${ru_id}" >user profile</a>
		   <% }
	else { %>
    	<h1>Welcome, Guest!</h1>
        <p>You are not logged in. Please log in to access your favorites.</p>
            <a href="${pageContext.request.contextPath}/views/Login.jsp">Login</a>
        
    <% } %>
    		 <a href="${pageContext.request.contextPath}/getAllrooms?page=user">Rooms</a>
  
</body>
</html>