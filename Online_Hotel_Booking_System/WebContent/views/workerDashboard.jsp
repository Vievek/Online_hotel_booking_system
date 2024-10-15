<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
    <%
    // Retrieve user ID and username from session
    Integer WuserId = (Integer) session.getAttribute("w_id");
    Integer userId = (Integer) session.getAttribute("userId");
    String username = (String) session.getAttribute("username");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>workerDashboard</h1>
	<h1>Welcome, <%= username %>!</h1>
    	<p>Your Wuser ID is: <%= WuserId %></p>
    	<p>Your user ID is: <%= userId %></p>
    	    <a href="${pageContext.request.contextPath}/logout">Logout</a>
    	    <a href="${pageContext.request.contextPath}/getChatList?userId=${userId}">Chat</a>
    	    
    	
</body>
</html>