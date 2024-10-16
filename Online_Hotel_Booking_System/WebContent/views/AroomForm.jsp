<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Room Form</title>
<style>
    body {
        font-family: Arial, sans-serif;
        background-color: #f5f5f5;
        margin: 0;
        padding: 0;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100vh;
    }
    .container {
        background-color: white;
        padding: 30px;
        border-radius: 10px;
        box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        width: 500px;
    }
    h2 {
        text-align: center;
        color: #090946;
        margin-bottom: 20px;
    }
    .input-group {
        margin-bottom: 15px;
    }
    label {
        display: block;
        color: #090946;
        font-weight: bold;
        margin-bottom: 5px;
    }
    input[type="text"], input[type="number"], input[type="file"] {
        width: 100%;
        padding: 10px;
        border-radius: 5px;
        border: 1px solid #ddd;
        font-size: 16px;
    }
    .btn {
        background-color: orange;
        color: white;
        padding: 12px;
        text-align: center;
        border: none;
        border-radius: 5px;
        width: 100%;
        font-size: 18px;
        cursor: pointer;
        margin-top: 20px;
    }
    .btn:hover {
        background-color: #e69500;
    }
</style>
</head>
<body>

<div class="container">
    <c:choose>
        <c:when test="${not empty room}">
            <form action="${pageContext.request.contextPath}/EditRooms" method="post" enctype="multipart/form-data">
                <h2>Edit Room</h2>
                <input type="hidden" name="roomId" value="${room[0].roomId}">
        </c:when>
        <c:otherwise>
            <form action="${pageContext.request.contextPath}/AddRoom" method="post" enctype="multipart/form-data">
                <h2>Add Room</h2>
        </c:otherwise>
    </c:choose>

    <div class="input-group">
        <label for="roomType">Room Type</label>
        <input type="text" id="roomType" name="roomType" value="${not empty room ? room[0].roomType : ''}" required>
    </div>

    <div class="input-group">
        <label for="description">Description</label>
        <input type="text" id="description" name="description" value="${not empty room ? room[0].description : ''}" required>
    </div>

    <div class="input-group">
        <label for="noOfPerson">Number of Persons</label>
        <input type="number" id="noOfPerson" name="noOfPerson" value="${not empty room ? room[0].noOfPerson : ''}" required>
    </div>

    <div class="input-group">
        <label for="price">Price</label>
        <input type="number" id="price" name="price" value="${not empty room ? room[0].price : ''}" step="0.01" required>
    </div>

    <div class="input-group">
        <label for="availabilityStatus">Availability Status</label>
        <input type="text" id="availabilityStatus" name="availabilityStatus" value="${not empty room ? room[0].availabilityStatus : ''}" required>
    </div>

    <div class="input-group">
        <label for="img1">Image 1</label>
        <input type="file" id="img1" name="img1">
    </div>

    <div class="input-group">
        <label for="img2">Image 2</label>
        <input type="file" id="img2" name="img2">
    </div>

    <div class="input-group">
        <label for="img3">Image 3</label>
        <input type="file" id="img3" name="img3">
    </div>

    <div class="input-group">
        <label for="img4">Image 4</label>
        <input type="file" id="img4" name="img4">
    </div>

    <button type="submit" class="btn">Submit</button>
    </form>
</div>
</body>
</html>
