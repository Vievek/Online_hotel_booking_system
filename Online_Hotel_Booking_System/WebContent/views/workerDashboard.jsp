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
<style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
           
        }
        nav{
        	grid-row: 1 / 2; 
  			grid-column: 1/ 3;
  			border: 2px solid black;
  			background-color: #333; /* Background color for the navbar */
  			padding: 10px 30px; /* Padding around the navbar */
  			color: white;
        }
        nav ul{
            display: flex;                   /* Enables flexbox */
		    flex-direction: row;            /* Sets the direction of the flex items to row */
		    justify-content: space-between;
		    list-style: none;
		    allign-items:center;
        }
        nav ul li a{
        	text-decoration: none;
        	color: white;
        }
        
        .navsub{
        	display: flex;                   /* Enables flexbox */
		    flex-direction: row;            /* Sets the direction of the flex items to row */
		    justify-content: space-around;
		    gap:50px;
        }
        
        h2 {
            color: #333;
            margin-top: 20px;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f4f4f4;
            color: #333;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        a {
            text-decoration: none;
            color: #1a73e8;
            font-weight: bold;
        }
        a:hover {
            text-decoration: underline;
        }
        .actions {
            display: flex;
            justify-content: space-around;
        }
        .add-btn {
            display: block;
            margin: 20px 0;
            padding: 10px 20px;
            background-color: #28a745;
            color: white;
            text-align: center;
            border-radius: 5px;
        }
        .add-btn:hover {
            background-color: #218838;
        }
    </style>
</head>
<body>

<section class="body">
<nav>
	<ul>
		<li>Rooms</li>
		<li><a href="${pageContext.request.contextPath}/Wtasks">Dashboard</a></li>
		<div class="navsub">
			<li><a href="${pageContext.request.contextPath}/getChatList?Id=${userId}">Chat</a></li>
			<li><%= username %></li>
			<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
		</div>
	</ul>
</nav>
	<div class="welcome">
		<h1>Welcome, <%= username %>!</h1>
	</div>
    	
    	<table>
        <tr>
            
            <th>Service ID</th>
            <th>Date</th>
            <th>Start Time</th>
            <th>End Time</th>
            
            
        </tr>
        <c:if test="${not empty bookingServicesList}">
            <c:forEach var="bookingService" items="${bookingServicesList}">
                <tr>
                    <td>${bookingService.servicesId}</td>
                    <td>${bookingService.date}</td>
                    <td>${bookingService.startTime}</td>
                    <td>${bookingService.endTime}</td>
                  
                    <td class="actions">
                        <a href="edit?id=${payment.pId}">Edit</a>
                        <a href="delete?id=${payment.pId}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        
        <c:if test="${empty bookingServicesList}">
            <tr>
                <td colspan="7">No bookingServicesList records found.</td>
            </tr>
        </c:if>
    </table>
    </section>
</body>
</html>