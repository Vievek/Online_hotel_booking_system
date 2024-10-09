<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
 
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Available Rooms</title>
    <link rel="stylesheet" type="text/css" href="styles.css"> <!-- Link to your CSS file -->
</head>
<jsp:include page="userHeader.jsp" />
<body>
    <div class="room-listing">
        <c:if test="${not empty room}">
            <c:forEach var="rom" items="${room}">
                <div class="room">
                    <h3>${rom.roomType} (Room ID: ${rom.roomId})</h3>
                    <p>Description: ${rom.description}</p>
                    <p>Capacity: ${rom.noOfPerson} Persons</p>
                    <p>Price: $${rom.price}</p>
                    <p>AC Type: ${rom.ac_type}</p>
                    <img src="${rom.img1}" alt="Image of ${rom.roomType} (Room ID: ${rom.roomId})" />
                    <hr/>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${empty room}">
            <p>No rooms available at the moment.</p> <!-- Message for no available rooms -->
        </c:if>
    </div>
    
    <div class="room-listing">
        <c:if test="${not empty services}">
            <c:forEach var="ser" items="${services}">
                <div class="room">
                  <hr/>
                    <p>name: ${ser.name}   price: ${ser.price}</p>
                    <hr/>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${empty services}">
            <p>No services available at the moment.</p> <!-- Message for no available rooms -->
        </c:if>
    </div>
</body>
</html>
