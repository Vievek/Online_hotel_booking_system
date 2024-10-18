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
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<style>
	body{
	padding:0;
	margin:0;
	}
	.nav{
		display:flex;
		flex-direction:row;
		gap:450px;
		align-items: center;
		background-color: #333; 
		padding: 10px 30px 10px; 
		color: white;
	}
	.normal{
		display:flex;
		flex-direction:row;
		align-items: center;
		gap:150px;
	}
	.user{
		display:flex;
		flex-direction:row;
		align-items: center;
		gap:40px;
	
	}
	.userprofile{
		display:flex;
		flex-direction:row;
		align-items: center;
		gap:10px;
	}
	
	a{
	 	text-decoration: none;
        color: #1a73e8;
        font-weight: bold;
	}
</style>
</head>
<body>
<section class="nav">
	<div class="name">ROOMS</div>
	<div class="normal">
		<a href="${pageContext.request.contextPath}/?userId=${ru_id}" >home</a>    	
		<a href="${pageContext.request.contextPath}/getAllrooms?page=user">Rooms</a>		
	</div>
	<% if (userId != null && username != null) { %>
	 <div class="user">
		<a href="${pageContext.request.contextPath}/favouriteRooms?uid=<%= userId %>">
			<i class="bi bi-heart favorite"></i>
		</a> 
	 	<a class="userprofile" href="${pageContext.request.contextPath}/ReadUserProfile?userId=${ru_id}" >
		   	<p>Welcome,<%= username %>! </p><i class="bi bi-person-circle"></i>
		 </a>
     </div>
		
		   <% }
	else { %>
	<div class="user">
    	<p>Welcome,Guest!</p>
         <a href="${pageContext.request.contextPath}/views/Login.jsp">Login</a>
     </div>
    <% } %>
  </section>
</body>
</html>