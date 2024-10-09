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
                    <p>Price: $<span id="roomPrice">${rom.price}</span></p> <!-- Display room price here -->
                    <p>AC Type: ${rom.ac_type}</p>
                    <img src="${rom.img1}" alt="Image of ${rom.roomType} (Room ID: ${rom.roomId})" />
                    <hr/>     
					 <input type="hidden" id="roomPriceHidden" value="${rom.price}"> <!-- Store room price in hidden input -->                            
                </div>
                 
            </c:forEach>
        </c:if>
        <c:if test="${empty room}">
            <p>No rooms available at the moment.</p> <!-- Message for no available rooms -->
        </c:if>
    </div>
    
    
    
    <% if (userId != null && username != null) { %>
    
  <!-- Total cost section -->
    <p>Total Services Cost: $<span id="totalCost">0</span></p>
    <p>Room Price: $<span id="roomPriceDisplay">0</span></p> <!-- Initialize room price display -->
    <p>Total Booking Cost: $<span id="totalBookingCost">0</span></p> <!-- Total booking cost displayed here -->
    
    <form action="service" method="post">
    

    
    <c:if test="${not empty services}">
            <c:forEach var="ser" items="${services}">
                <div class="room">           
                    <label>${ser.name}</label>
				    <input type="text" id="${ser.services_id}_date" name="${ser.services_id}Date" value="Jun 10, 2024">
				    <input type="time" id="${ser.services_id}_time_start" name="${ser.services_id}TimeStart" value="09:41">
				    <input type="time" id="${ser.services_id}_time_end" name="${ser.services_id}TimeEnd" value="09:41">
				    <label>${ser.price}</label>
					<input type="checkbox" id="${ser.services_id}_selected" name="${ser.services_id}Selected" value="1" 
                   onclick="updateTotal(${ser.price}, this)">				    
                   <br>
                    <hr/>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${empty services}">
            <p>No services available at the moment.</p> <!-- Message for no available rooms -->
        </c:if>

    <input type="submit" value="Submit">
</form>

    	   <% }
	else { %>
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
     <% } %>
     
 <script>
        // JavaScript to handle the checkbox selection and total cost calculation
        let totalCost = 0;
        let roomPrice = parseFloat(document.getElementById("roomPriceHidden").value) || 0; // Get room price from hidden input

        // Display initial room price
        document.getElementById("roomPriceDisplay").innerText = roomPrice.toFixed(2);

        function updateTotal(price, checkbox) {
            if (checkbox.checked) {
                // Add price to totalCost if the checkbox is selected
                totalCost += price;
            } else {
                // Subtract price from totalCost if the checkbox is deselected
                totalCost -= price;
            }
            // Update the displayed total cost
            document.getElementById("totalCost").innerText = totalCost.toFixed(2);

            // Update the total booking cost
            let totalBookingCost = roomPrice + totalCost;
            document.getElementById("totalBookingCost").innerText = totalBookingCost.toFixed(2);
        }
    </script>
</body>
</html>
