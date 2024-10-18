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
	
	  .login-btn {
        display: inline-block;
        padding: 10px 20px;
        background-color: #ff0066; /* Pink background */
        color: white;
        text-decoration: none;
        font-weight: bold;
        border-radius: 25px; /* Rounded corners */
        transition: background-color 0.3s ease, transform 0.3s ease; /* Smooth transition */
    }

    .login-btn:hover {
        background-color: #ff3385; /* Lighter pink on hover */
        transform: scale(1.05); /* Slight zoom effect */
    }

    .login-btn:active {
        background-color: #cc0052; /* Darker pink when clicked */
    }
	.nav{
		display:flex;
		flex-direction:row;
		gap:300px;
		align-items: center;
		background-color: #EFF0F2; 
		padding: 10px 30px 10px; 
		color: white;
	}
	.normal{
		display:flex;
		flex-direction:row;
		align-items: center;
		gap:50px;
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
	
	.rooms{
		color: #ff0066;
        font-size: 30px;
        margin: 0;
        font-weight: bold;
	}
</style>
</head>
<body>
<section class="nav">
	<div class="rooms"  >ROOMS</div>
	<div class="normal" >
		<a href="${pageContext.request.contextPath}/?userId=${ru_id}" style="color:#484848">home</a>    	
		<a href="${pageContext.request.contextPath}/getAllrooms?page=user" style="color:#484848">Find a property</a>
		<a href="#" style="color:#484848">Rental Guides</a>
        <a href="#" style="color:#484848">Contact us</a>
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
    	<p style="color:#484848;  font-weight: bold" >Welcome,Guest!</p>
         <a href="${pageContext.request.contextPath}/views/Login.jsp" style="color: white" class="login-btn">Login</a>
     </div>
    <% } %>
  </section>
</body>
</html>