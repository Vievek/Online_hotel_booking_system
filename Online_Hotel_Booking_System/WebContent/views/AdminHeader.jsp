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
<style>
.sidenav {
    height: 100%; /* Full-height */
    width: 200px; /* Set the width of the sidebar */
    position: fixed; /* Fixed sidebar */
    left: 0;
    
    background-color: #333; /* Background color */
    padding-top: 20px; /* Padding from the top */
    display: flex;
    flex-direction: column; /* Align items vertically */
    gap: 10px; /* Space between links */
}

.sidenav a {
    padding: 10px 15px;
    text-decoration: none;
    font-size: 18px;
    color: white; /* Link color */
    display: block;
}

.sidenav a:hover {
    background-color: #575757; /* Background color on hover */
    color: white;
}

</style>
</head>
<body>
		<div class="sidenav">
    	    <a href="${pageContext.request.contextPath}/listBookings">Booking</a>
    	    <a href="${pageContext.request.contextPath}/APaymentList">Payment</a> 
    	    <a href="${pageContext.request.contextPath}/AServiceList">service</a>    	    
       	    <a href="${pageContext.request.contextPath}/AgetuserByrole?role=Worker">Workers</a>
       	    <a href="${pageContext.request.contextPath}/AgetuserByrole?role=User">Guests</a> 
    	    <a href="${pageContext.request.contextPath}/getAllrooms?page=admin">Rooms</a>
    	    <a href="${pageContext.request.contextPath}/Managerhome">Dashboard</a>
    	  </div>  
    	    

</body>
</html>