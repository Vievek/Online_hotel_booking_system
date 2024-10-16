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
<body>
<h1>Welcome, <%= username %>!</h1>
    	<p>Your user ID is: <%= userId %></p>
    	    <a href="${pageContext.request.contextPath}/logout">Logout</a>
    	    <a href="${pageContext.request.contextPath}/listBookings">Booking</a>
    	    <a href="${pageContext.request.contextPath}/APaymentList">Payment</a> 
    	    <a href="${pageContext.request.contextPath}/AServiceList">service</a>    	    
       	    <a href="${pageContext.request.contextPath}/AgetuserByrole?role=Worker">Workers</a>
       	    <a href="${pageContext.request.contextPath}/AgetuserByrole?role=User">Guests</a>
    	    
    	    <a href="${pageContext.request.contextPath}/getAllrooms?page=admin">Rooms</a>
    	    <a href="${pageContext.request.contextPath}/views/ManagerDashboard.jsp">Dashboard</a>
    	    
    	    

</body>
</html>