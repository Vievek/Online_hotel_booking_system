<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 <%  
    String username = (String) session.getAttribute("username");
%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
            display: grid;
            grid-template-rows: 50px 50px  1fr;
            grid-template-columns: 200px 2fr 1fr 2fr;
        }
         body > .jsp-included-element{
        	grid-column: 1/ 2;
        	grid-row: 2 / -1; 
        }
         nav{
        	grid-row: 1 / 2; 
  			grid-column: 2/ -1;
  			background-color: #333; 
  			padding: 10px 30px; 
  			color: white;
        }
        nav ul{
            display: flex;                  
		    flex-direction: row;            
		    justify-content: space-between;
		    list-style: none;
		    allign-items:center;
        }
        nav ul li a{
        	text-decoration: none;
        	color: white;
        }
        
        .navsub{
        	display: flex;                   
		    flex-direction: row;            
		    justify-content: space-around;
		    gap:50px;
        }
        h2 {
            color: #333;
            margin-top: 20px;
            grid-column: 3/ 4;
            grid-row: 2 / 3; 
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            grid-column: 2/ -1;
            grid-row: 3 / 4; 
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
            grid-column: 4/ 5;
            grid-row: 3 / 4; 
            justify-self: center; 
  			align-self: start;
        }
        .add-btn:hover {
            background-color: #218838;
        }
    </style>
</head>

<body>
<jsp:include page="AdminHeader.jsp" />
<nav>
	<ul>
		<li>Rooms</li>
		<div class="navsub">
			<li><%= username %></li>
			<li><a href="${pageContext.request.contextPath}/AgetChatList?Id=${userId}">Chat</a></li>			
			<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
		</div>
	</ul>
</nav>
    <h2>Worker List</h2>


    <table>
        <tr>
            <th>Worker ID</th>
            <th>Name</th>
            <th>Email</th>
            <th>Phone</th>
            <th>User name</th>
            <th>Password</th>
            
        </tr>
        <c:if test="${not empty Users}">
            <c:forEach var="work" items="${Users}">
                <tr>
                    <td>${work.id}</td>
                    <td>${work.name}</td>
                    <td>${work.email}</td>
                    <td>${work.phone}</td>
                    <td>${work.username}</td>
                    <td>${work.password}</td>
                  
                    
                </tr>
            </c:forEach>
        </c:if>
        
        <c:if test="${empty Users}">
            <tr>
                <td colspan="7">No Users records found.</td>
            </tr>
        </c:if>
    </table>
</body>
</html>
    