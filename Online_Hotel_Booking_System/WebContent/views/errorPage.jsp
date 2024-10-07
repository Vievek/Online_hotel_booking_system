<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Error Page</title>
    <link rel="stylesheet" href="styles.css"> <!-- Link to a CSS file if needed -->
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f2f2f2;
            text-align: center;
            padding: 50px;
        }
        .error-container {
            background-color: #fff;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 20px;
            max-width: 400px;
            margin: auto;
        }
        .error-message {
            color: #d9534f; /* Bootstrap Danger Color */
            font-size: 1.2em;
        }
        a {
            text-decoration: none;
            color: #0275d8; /* Bootstrap Primary Color */
        }
        a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>
    <div class="error-container">
        <h1>Error</h1>
        <p class="error-message">
            <%= request.getAttribute("errorMessage") != null ? request.getAttribute("errorMessage") : "An unexpected error occurred." %>
        </p>
        <p>
            <a href="<%= request.getHeader("Referer") != null ? request.getHeader("Referer") : "views/home.jsp" %>">Go back</a>
        </p>
    </div>
</body>
</html>
