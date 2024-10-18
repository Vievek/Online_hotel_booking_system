<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
    // Retrieve user ID and username from session
    Integer userId = (Integer) session.getAttribute("ru_id");
    String username = (String) session.getAttribute("username");
%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<jsp:include page="userHeader.jsp" />
<body>

<c:if test="${not empty favouritesList}">
<div class="room-container" style="display: flex; flex-wrap: wrap; justify-content: center;">
        <c:forEach var="rom" items="${favouritesList}">
        <a href="${pageContext.request.contextPath}/roomDetails?roomId=${rom.roomId}&userId=${ru_id}" >
               <div class="room" style="display: flex; flex-direction: column; align-items: center; padding: 15px; background-color: #FFD79E; border-radius: 10px; margin: 10px; width: 300px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);">                
                    <a href="${pageContext.request.contextPath}/roomDetails?roomId=${rom.roomId}&userId=${ru_id}" class="room-link" style="text-decoration: none; color: inherit; text-align: center; display: block; width: 100%;">
                    <h3 style="font-size: 1.2rem; margin-bottom: 10px;">${rom.roomType}: ${rom.roomId}</h3>
                    <div class="roomimg" style="width: 100%; height: 200px; border-radius: 8px; overflow: hidden; margin-bottom: 10px;">
                        <img src="${rom.img1}" alt="Room Image 1" style="width: 100%; height: 100%; object-fit: cover;">
                    </div>
                    <p class="Price" style="font-size: 1rem; font-weight: bold; margin: 5px 0; color: #333;">$${rom.price}</p>
                    <p class="AC" style="font-size: 0.9rem; margin: 5px 0; color: #666;">${rom.ac_type}</p>
                    <p class="Capacity" style="font-size: 0.9rem; margin: 5px 0; color: #666;">Capacity: ${rom.noOfPerson}</p>
                </a>
                <a href="${pageContext.request.contextPath}/deleteFavouriteRoom?FId=${rom.favouriteID}&userId=${ru_id}" style="text-decoration: none; color: inherit;" >
                    Delete from Favourites
                	</a>
                </div>
                </a>
        </c:forEach>
        </div>
    </c:if>
 
</body>
</html>