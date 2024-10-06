<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
</head>
<body>
    <h2>Login Page</h2>

    <%
        // Check if the registration was successful
        String message = (String) request.getAttribute("message");
        if ("success".equals(message)) {
    %>
    <script type="text/javascript">
        alert("Registration Successful! You can now log in.");
    </script>
    <% 
        } 

        // Check for login error message
        String errorMessage = (String) request.getAttribute("errorMessage");
        if (errorMessage != null) {
    %>
    <div style="color: red;">
        <strong>Error: </strong> <%= errorMessage %>
    </div>
    <%
        }
    %>

<form action="${pageContext.request.contextPath}/userLogin" method="post">
	    User Name: <input type="text" name="uid"><br>
	    Password: <input type="password" name="psw"><br>
	    <input type="submit" value="Login" name="Submit">
	</form>
</body>
</html>
