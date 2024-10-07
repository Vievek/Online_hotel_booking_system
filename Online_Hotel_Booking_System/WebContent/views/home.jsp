<%
    // Retrieve user ID and username from session
    Integer userId = (Integer) session.getAttribute("userId");
    String username = (String) session.getAttribute("username");
%>

<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>
</head>
<body>
    <h1>Welcome, <%= username %>!</h1>
    <p>Your user ID is: <%= userId %></p>

	<% if (userId != null && username != null) { %>
        <a href="${pageContext.request.contextPath}/views/Favourite.jsp"> Favourite</a>
    <% } else { %>
        <p>You are not logged in. Please log in to access your favorites.</p>
    <% } %>
    
</body>
</html>
