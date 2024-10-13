<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.rooms" %>

<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>
</head>
<body>

<h1>Home</h1>

<%
    Integer userId = null; 
    String username = null; 
    // Example: userId = (Integer) session.getAttribute("userId");
    // Example: username = (String) session.getAttribute("username");
%>



<c:if test="${not empty room}">
    <c:forEach var="rom" items="${room}" varStatus="status">
        <c:if test="${status.index < 6 && rom.availabilityStatus == 'Available'}">
            <a href="${pageContext.request.contextPath}/roomDetails?roomId=${rom.roomId}&userId=${userId}">
                <div class="room">                
                    <h3>${rom.roomType} (Room ID: ${rom.roomId})</h3>
                    <p>Description: ${rom.description}</p>
                    <p>Capacity: ${rom.noOfPerson} Persons</p>
                    <p>Price: $${rom.price}</p>
                    <p>AC Type: ${rom.ac_type}</p>
                    <img src="${rom.img1}" alt="Room Image 1" />                    
                    <hr/>
                </div>
            </a>
        </c:if>
    </c:forEach>
</c:if>

<c:if test="${empty room}">
    <p>No rooms available at this time.</p>
</c:if>

<h1>highly booked</h1>

<c:if test="${not empty highroom}">
    <c:forEach var="rom" items="${highroom}" varStatus="status">
        <c:if test="${status.index < 6 && rom.availabilityStatus == 'Available'}">
            <a href="${pageContext.request.contextPath}/roomDetails?roomId=${rom.roomId}&userId=${userId}">
                <div class="room">                
                    <h3>${rom.roomType} (Room ID: ${rom.roomId})</h3>
                    <p>Description: ${rom.description}</p>
                    <p>Capacity: ${rom.noOfPerson} Persons</p>
                    <p>Price: $${rom.price}</p>
                    <p>AC Type: ${rom.ac_type}</p>
                    <img src="${rom.img1}" alt="Room Image 1" />                    
                    <hr/>
                </div>
            </a>
        </c:if>
    </c:forEach>
</c:if>

<c:if test="${empty highroom}">
    <p>No highroom available at this time.</p>
</c:if>
</body>
</html>
