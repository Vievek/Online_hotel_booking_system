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
    <style >
    
    .new{
    	display:flex;
    	flex-direction:row;
    	justify-content:space-between;
    }
   
    
    </style>
</head>
<jsp:include page="userHeader.jsp" />
<body>
    <form action="${pageContext.request.contextPath}/AddBooking" method="post">

    <div class="room-listing">
        <c:if test="${not empty room}">
            <c:forEach var="rom" items="${room}">
                <div class="room" style="text-align: center; font-weight: bold; margin-bottom: 20px; border: 1px solid #ccc; padding: 10px;">
                    <h3 style="color: #FF007B; font-size: 24px;"  >${rom.roomType} (Room ID: ${rom.roomId})</h3>
                    <p>Description: ${rom.description}</p>
                    <p>Capacity: ${rom.noOfPerson} Persons</p>
                    <p>Price: $<span id="roomPrice">${rom.price}</span></p> <!-- Display room price here -->
                    <p>AC Type: ${rom.ac_type}</p>
                    <hr/>     
					 <input type="hidden"  name="roomPrice" id="roomPriceHidden" value="${rom.price}"> <!-- Store room price in hidden input -->  
					 <input type="hidden" name="roomId" value="${rom.roomId}"> <!-- Store room ID in hidden input -->	
					 				                           
                </div>
                 
            </c:forEach>
        </c:if>
        <c:if test="${empty room}">
            <p>No rooms available at the moment.</p> <!-- Message for no available rooms -->
        </c:if>
    </div>
    
    
    
    <% if (userId != null && username != null) { %>
    
  <!-- Total cost section -->
    <p style="text-align: left; font-weight: bold; margin-bottom: 20px; border: 1px solid #ccc; padding: 10px;">Total Services Cost: $<span id="totalCost">0</span></p>
    <p style="text-align: left; font-weight: bold; margin-bottom: 20px; border: 1px solid #ccc; padding: 10px;">Room Price: $<span id="roomPriceDisplay">0</span></p> <!-- Initialize room price display -->
    <p style="text-align: left; font-weight: bold; margin-bottom: 20px; border: 1px solid #ccc; padding: 10px;">Total Booking Cost: $<span id="totalBookingCost">0</span></p> <!-- Total booking cost displayed here -->
    
    <form action="${pageContext.request.contextPath}/AddBooking" method="post">
    
<br>
	     <input type="hidden" name="userId" value="${ru_id}"> <!-- Hidden input for user ID -->
        <input type="hidden" name="totalServiceCost" id="totalServiceCostHidden" value="${booking.service_price}"> <!-- Hidden input for total service cost -->
        <input type="hidden" name="totalCost" id="totalCostHidden" value="${booking.total_amount}"> <!-- Hidden input for total cost -->
    	<input type="hidden" name="roomPrice" id="roomPriceHidden" value="${booking.room_price}"> <!-- Room price to be passed -->
        
        <label>checkInDate</label>
        <input type="date" name="checkInDate"  value=${booking.checkin} required><br><br> <!-- Check-in date -->
        <label>checkOutDate</label>
        <input type="date" name="checkOutDate" value=${booking.checkout} required> <br><br><!-- Check-out date -->
    
    <c:if test="${not empty services}">
    <c:forEach var="ser" items="${services}">
        <c:set var="isServiceFound" value="false" /> 
        
        <c:forEach var="service" items="${Rservices}">
            <c:if test="${service.servicesId eq ser.services_id}">
                <div class="new">           
                    <label>${ser.name}</label>
                    <input type="date" id="${ser.services_id}_date" name="${ser.services_id}Date" value="${service.date}">
                    <input type="time" id="${ser.services_id}_time_start" name="${ser.services_id}TimeStart" value="${service.startTime}">
                    <input type="time" id="${ser.services_id}_time_end" name="${ser.services_id}TimeEnd" value="${service.endTime}">
                    <label>${ser.price}</label>
                    <c:set var="serviceKey" value="${ser.services_id}Service" />
					<input type="checkbox" 
			            id="${ser.services_id}_selected" 
			            name="${ser.services_id}Selected" 
			            value="yes"
			            <c:if test="${requestScope[serviceKey] eq 'yes'}">checked</c:if>
			            onclick="updateTotal(${ser.price}, this)">
                    <br>
                    <hr/>
                </div>
                <c:set var="isServiceFound" value="true" />
            </c:if>
        </c:forEach>

        <c:if test="${not isServiceFound}">
            <!-- Display form for services not found in Rservices -->
            <div class="room">           
                <label>${ser.name}</label>
                <input type="date" id="${ser.services_id}_date" name="${ser.services_id}Date">
                <input type="time" id="${ser.services_id}_time_start" name="${ser.services_id}TimeStart">
                <input type="time" id="${ser.services_id}_time_end" name="${ser.services_id}TimeEnd">
                <label>${ser.price}</label>
                <input type="checkbox" 
                    id="${ser.services_id}_selected" 
                    name="${ser.services_id}Selected" 
                    value="no"
                    onclick="updateTotal(${ser.price}, this)">
                <br>
                <hr/>
            </div>
        </c:if>
    </c:forEach>
</c:if>

        <c:if test="${empty services}">
            <p>No services available at the moment.</p> <!-- Message for no available rooms -->
        </c:if>

    <center><input type="submit" value="Submit" name="Submit" style="background-color: #FF007B; color: white; border: none; padding: 10px 20px; font-size: 16px; cursor: pointer; border-radius: 5px;"></center>
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
    window.onload = function () {
        let totalCost = 0;
        let roomPriceElement = document.getElementById("roomPriceHidden");

        // Ensure room price is fetched correctly from hidden input
        let roomPrice = parseFloat(roomPriceElement ? roomPriceElement.value : 0);

        // If the roomPrice is valid, display it, otherwise fallback to 0
        if (roomPrice > 0) {
            document.getElementById("roomPriceDisplay").innerText = roomPrice.toFixed(2);
        } else {
            document.getElementById("roomPriceDisplay").innerText = "0.00";
        }

        // Function to update total cost when services are selected/deselected
        window.updateTotal = function(price, checkbox) {
            if (checkbox.checked) {
                // Add the service price to totalCost
                totalCost += price;
            } else {
                // Subtract the service price from totalCost
                totalCost -= price;
            }

            // Update the total services cost display
            document.getElementById("totalCost").innerText = totalCost.toFixed(2);

            // Calculate total booking cost (room price + services cost)
            let totalBookingCost = roomPrice + totalCost;
            document.getElementById("totalBookingCost").innerText = totalBookingCost.toFixed(2);

            // Update hidden inputs to submit correct values with the form
            document.getElementById("totalServiceCostHidden").value = totalCost.toFixed(2);
            document.getElementById("totalCostHidden").value = totalBookingCost.toFixed(2);
            document.getElementById("roomPriceHidden").value = roomPrice.toFixed(2);
            
            console.log(totalCost);
            console.log(totalBookingCost);
            console.log(roomPrice);

        };
    };
</script>

</body>
<jsp:include page="footer.jsp" />

</html>
