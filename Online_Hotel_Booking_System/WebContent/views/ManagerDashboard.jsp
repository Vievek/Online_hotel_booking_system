<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    // Retrieve user ID and username from session
    Integer MuserId = (Integer) session.getAttribute("m_id");
    Integer userId = (Integer) session.getAttribute("userId");
    String username = (String) session.getAttribute("username");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<jsp:include page="AdminHeader.jsp" />
<body>
<h1>manager dashboard</h1>
	
    	    
    	
</body>
</html>