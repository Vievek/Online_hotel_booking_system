<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit User Profile</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f4f4f4;
        margin: 0;
        padding: 0;
    }

    h2 {
        text-align: center;
        margin-top: 30px;
        color: #333;
    }

    form {
        background: #fff;
        max-width: 400px;
        margin: 20px auto;
        padding: 20px;
        border-radius: 8px;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    label {
        font-weight: bold;
        margin-bottom: 5px;
        display: block;
        color: #555;
    }

    input[type="text"], input[type="email"], input[type="password"], input[type="file"], input[type="tel"] {
        width: 100%;
        padding: 10px;
        margin: 5px 0 15px;
        border-radius: 4px;
        border: 1px solid #ddd;
        box-sizing: border-box;
        transition: border-color 0.3s;
    }

    input[type="text"]:focus, input[type="email"]:focus, input[type="password"]:focus, input[type="file"]:focus, input[type="tel"]:focus {
        border-color: #4CAF50;
        outline: none;
    }

    input[type="submit"] {
        background-color: #4CAF50;
        color: white;
        border: none;
        padding: 12px;
        font-size: 16px;
        cursor: pointer;
        border-radius: 4px;
        transition: background-color 0.3s, box-shadow 0.3s;
    }

    input[type="submit"]:hover {
        background-color: #45a049;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }

    @media (max-width: 500px) {
        form {
            width: 90%;
            padding: 15px;
        }

        h2 {
            font-size: 20px;
        }
    }
</style>
</head>
<jsp:include page="userHeader.jsp" />
<body>
<h2>User Edit Form</h2>

<form action="${pageContext.request.contextPath}/UpdateUserProfile" method="post" >
    <input type="hidden" name="uid" value="${user.id}">
    <input type="hidden" name="ruid" value="${ru_id}">

    <!-- Name -->
    <label for="name">Full Name:</label>
    <input type="text" id="name" name="name" placeholder="Enter your full name" value="${user.name}" required>

    <!-- Email -->
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" placeholder="Enter your email" value="${user.email}" required>

    <!-- Phone -->
    <label for="phone">Phone Number:</label>
    <input type="tel" id="phone" name="phone" placeholder="Enter your phone number" value="${user.phone}" required>

    <!-- Username -->
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" placeholder="Choose a username" value="${user.username}" required>

    <!-- Password -->
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" placeholder="Enter your password" value="${user.password}" required>

    <!-- Submit Button -->
    <input type="submit" value="Edit" name="Submit">
</form>
</body>
</html>
