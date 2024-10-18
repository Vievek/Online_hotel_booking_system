<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.rooms" %>

<!DOCTYPE html>
<html>
<head>
    <title>Home Page</title>
   
          <style>
        /* General Styling */
        body {
            font-family: 'Poppins', sans-serif; /* Applying Poppins font */
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }

        .container {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 50px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin: 40px auto;
            max-width: 1200px;
            border-radius: 10px;
        }
        .phead {
            font-size: 30px; /* Change font size as needed */
            color: #333; /* Change text color */
            padding: 10px; /* Add padding around the text */
            text-align: left;
            font-weight: bold; 
            margin-left: 50px;/* Center align the text (optional) */
        }

        /* Text Section */
        .text-section {
            max-width: 600px;
        }

        .text-section h1 {
            font-size: 28px;
            font-weight: 600;
            color: #333;
            margin-bottom: 10px;
        }

        .text-section p {
            font-size: 16px;
            color: #666;
            margin-bottom: 20px;
        }

        .text-section .links {
            display: flex;
            gap: 15px;
            margin-bottom: 20px;
        }

        .text-section .links a {
            font-size: 14px;
            text-decoration: none;
            color: #333;
        }

        /* Button Styling */
        .discover-button {
            display: inline-block;
            background-color: #ff0077;
            color: #fff;
            padding: 12px 30px;
            font-size: 16px;
            text-decoration: none;
            border-radius: 50px;
            transition: background-color 0.3s ease;
        }

        .discover-button:hover {
            background-color: #e6006f;
        }

        /* Image Section */
        .image-section img {
            width: 400px;
            height: auto;
            border-radius: 20px;
        }

    </style>
</head>
<jsp:include page="userHeader.jsp" />
<body>


<%
    Integer userId = null; 
    String username = null; 
     userId = (Integer) session.getAttribute("ru_id");
     username = (String) session.getAttribute("username");
%>



<c:if test="${not empty recentroom}">
<p class="phead">Recently visited<br>
rooms</p>
    <div class="room-container" style="display: flex; flex-wrap: wrap; justify-content: center;">

    <c:forEach var="rom" items="${recentroom}" varStatus="status">
        <c:if test="${status.index < 6 && rom.availabilityStatus == 'Available'}">
            <a href="${pageContext.request.contextPath}/roomDetails?roomId=${rom.roomId}&userId=${ru_id}">
                <div class="room" style="display: flex; flex-direction: column; align-items: center; padding: 15px;  border-radius: 10px; margin: 10px; width: 300px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1); background-color: #FFD79E;">                
                    <a href="${pageContext.request.contextPath}/roomDetails?roomId=${rom.roomId}&userId=${ru_id}" class="room-link" style="text-decoration: none; color: inherit; text-align: center; display: block; width: 100%;">
                    <h3 style="font-size: 1.2rem; margin-bottom: 10px;">${rom.roomType}: ${rom.roomId}</h3>
                    <div class="roomimg" style="width: 100%; height: 200px; border-radius: 8px; overflow: hidden; margin-bottom: 10px;">
                        <img src="${rom.img1}" alt="Room Image 1" style="width: 100%; height: 100%; object-fit: cover;">
                    </div>
                    <p class="Price" style="font-size: 1rem; font-weight: bold; margin: 5px 0; color: #333;">$${rom.price}</p>
                    <p class="AC" style="font-size: 0.9rem; margin: 5px 0; color: #666;">${rom.ac_type}</p>
                    <p class="Capacity" style="font-size: 0.9rem; margin: 5px 0; color: #666;">Capacity: ${rom.noOfPerson}</p>
                </a>
                </div>
            </a>
        </c:if>
    </c:forEach>
    </div>
    <hr><hr>
</c:if>






<p class="phead">Featured Properties <br>
on our listing</p>
<c:if test="${not empty room}">
<div class="room-container" style="display: flex; flex-wrap: wrap; justify-content: center;">
    <c:forEach var="rom" items="${room}" varStatus="status">
        <c:if test="${status.index < 8 && rom.availabilityStatus == 'Available'}">
            <a href="${pageContext.request.contextPath}/roomDetails?roomId=${rom.roomId}&userId=${ru_id}">
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
                </div>
            </a>
        </c:if>
    </c:forEach>
    </div>
</c:if>

<c:if test="${empty room}">
    <p>No rooms available at this time.</p>
</c:if>

<hr><hr>
<div class="container">
        <!-- Text Section -->
        <div class="text-section">
            <h1>Browse For More Properties</h1>
            <p>Explore properties by their categories/types...</p>

            <!-- Find Property Button -->
            <a href="${pageContext.request.contextPath}/getAllrooms?page=user" class="discover-button">Find A Property</a>
        </div>

        <!-- Image Section -->
        <div class="image-section">
            <img src="https://ak7.picdn.net/shutterstock/videos/30324217/thumb/1.jpg?i10c=img.resize(height:160)" alt="Property Image">
        </div>
    </div>

<p class="phead">Top Booked <br>
Rooms</p>

<c:if test="${not empty highroom}">
<div class="room-container" style="display: flex; flex-wrap: wrap; justify-content: center;">

    <c:forEach var="rom" items="${highroom}" varStatus="status">
        <c:if test="${status.index < 6 && rom.availabilityStatus == 'Available'}">
            <a href="${pageContext.request.contextPath}/roomDetails?roomId=${rom.roomId}&userId=${ru_id}">
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
                </div>
            </a>
        </c:if>
    </c:forEach>
    </div>
</c:if>

<c:if test="${empty highroom}">
    <p>No highroom available at this time.</p>
</c:if>
<div class="container">
        <!-- Text Section -->
        <div class="text-section">
            <h1>Discover More About Property Rental</h1>
            <p>At vero eos et accusamus et iusto odio dignissimos ducimus qui blanditiis praesentium voluptatum deleniti atque corrupti quos dolores et quas molestias excepturi sint occaecati cupiditate non provident, similique sunt in culpa qui officia deserunt mollitia animi.</p>
            
            <!-- Links Section -->
            <div class="links">
                <a href="#">Ask A Question</a>
                <a href="#">Find A Property</a>
            </div>

            <!-- Discover More Button -->
            <a href="${pageContext.request.contextPath}/getAllrooms?page=user" class="discover-button">Discover More</a>
        </div>

        <!-- Image Section -->
        <div class="image-section">
            <img src="https://c.wallhere.com/photos/ce/69/3840x2506_px_Beauty_design_happy_house_interior_living_luxury-1748955.jpg!d" alt="Property Image">
        </div>
    </div>



</body>
<jsp:include page="footer.jsp" />
</html>
