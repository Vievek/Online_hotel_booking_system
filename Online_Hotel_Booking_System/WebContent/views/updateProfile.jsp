<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
        form {
            width: 300px;
            margin: auto;
        }

        label {
            display: block;
            margin: 10px 0 5px;
        }

        input[type="text"], input[type="email"], input[type="password"], input[type="file"], input[type="tel"] {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }

        button {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #4CAF50;
            color: white;
            border: none;
            margin-top: 20px;
            cursor: pointer;
        }

        button:hover {
            background-color: #45a049;
        }
    </style>
</head>
<jsp:include page="userHeader.jsp" />
<body>
<h2 style="text-align: center;">User Edit Form</h2>

<form action="${pageContext.request.contextPath}/UpdateUserProfile" method="post" enctype="multipart/form-data">
    <label>Current Profile Image:</label>
    <img src="${user.profile}" alt="Current Profile Image">
    
	<input type="hidden" name="uid" value="${user.id}"> <!-- Store room ID in hidden input -->	

    <!-- Image Upload -->
    <label for="image">Upload New Profile Image:</label>
    <input type="file" id="image" name="image" accept="image/*">

    <!-- Name -->
    <label for="name">Full Name:</label>
    <input type="text" id="name" name="name" placeholder="Enter your full name" value="${user.name}" required>

    <!-- Email -->
    <label for="email">Email:</label>
    <input type="email" id="email" name="email" placeholder="Enter your email" value="${user.email}"required>

    <!-- Phone -->
    <label for="phone">Phone Number:</label>
    <input type="tel" id="phone" name="phone" placeholder="Enter your phone number" value="${user.phone}"required>

    <!-- Username -->
    <label for="username">Username:</label>
    <input type="text" id="username" name="username" placeholder="Choose a username"value="${user.username}" required>

    <!-- Password -->
    <label for="password">Password:</label>
    <input type="password" id="password" name="password" placeholder="Enter your password"value="${user.password}" required>

    <!-- Submit Button -->
	    <input type="submit" value="Edit" name="Submit" >
</form>
</body>
</html>