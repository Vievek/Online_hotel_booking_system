<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
        // Check if the registration failed
        String message = (String) request.getAttribute("message");
        if ("fail".equals(message)) {
    %>
    <script type="text/javascript">
        alert("User name not available! try with different user name");
    </script>
    <% } %>
    
    
<form action="../userRegistration" method="post">
	    Name: <input type="text" name="name"><br>
	    Email: <input type="text" name="email"><br>
	    Phone: <input type="text" name="phone"><br>
	    User Name: <input type="text" name="uid"><br>
	    Password: <input type="password" name="psw"><br>
	    <input type="submit" value="Register" name="Submit" >
	</form>

</body>
</html>