<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<jsp:include page="userHeader.jsp" />
<body>

<c:if test="${not empty favouritesList}">
        <c:forEach var="rom" items="${favouritesList}">
                <div class="room">
                    <h3>${rom.roomType} (Room ID: ${rom.roomId})</h3>
                    <p>Description: ${rom.description}</p>
                    <p>Capacity: ${rom.noOfPerson} Persons</p>
                    <p>Price: $${rom.price}</p>
                    <p>AC Type: ${rom.ac_type}</p>
                    <p>Availablity sts: ${rom.availabilityStatus}</p>
                    <img src="${rom.img1}" alt="Room Image 1" />
                    <a href="${pageContext.request.contextPath}/deleteFavouriteRoom?FId=${rom.favouriteID}&userId=${ru_id}" >
                    Delete from Favourites
                	</a>
                    <hr/>
                </div>
        </c:forEach>
    </c:if>

</body>
</html>