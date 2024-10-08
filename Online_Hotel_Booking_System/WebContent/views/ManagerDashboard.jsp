<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    // Retrieve user ID and username from session
    Integer userId = (Integer) session.getAttribute("m_id");
    String username = (String) session.getAttribute("username");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
<h1>manager dashboard</h1>
	<h1>Welcome, <%= username %>!</h1>
    	<p>Your user ID is: <%= userId %></p>
    	    <a href="${pageContext.request.contextPath}/logout">Logout</a>
    	
</body>
</html>