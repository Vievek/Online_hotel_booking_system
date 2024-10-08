<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.rooms" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Room and AC Type Selection</title>
    <script>
        let selectedRoomType = '1'; // Variable to hold the selected room type
        let selectedACType = '1';    // Variable to hold the selected AC type

        function roomType(event) {
        	
        	event.preventDefault();
            // Get the text content of the clicked button
            const roomButtonText = event.target.textContent;

            // Check if the button text is "All"
            if (roomButtonText === 'All') {
                selectedRoomType = '1';  // Reset the room type
                selectedACType = '1';     // Reset the AC type
            } else {
                selectedRoomType = roomButtonText; // Set the room type
            }

            // Update the hidden form fields and submit
            updateAndSubmitForm();
        }

        function ac_type(event) {
        	
        	event.preventDefault();

            // Get the text content of the clicked button
            selectedACType = event.target.textContent; 
            // Update the hidden form fields and submit
            updateAndSubmitForm();
        }

        function updateAndSubmitForm() {
  
            // Set the values of the hidden form fields
            document.getElementById('roomTypeInput').value = selectedRoomType;
            document.getElementById('acTypeInput').value = selectedACType;

            // Submit the hidden form
            document.getElementById('hiddenForm').submit();
            
        }
    </script>
</head>
<jsp:include page="userHeader.jsp" />
<body>
    <button class="button" onclick="roomType(event)">All</button>
    <button class="button" onclick="roomType(event)">Deluxe</button>
    <button class="button" onclick="roomType(event)">Single</button>
    <button class="button" onclick="roomType(event)">Double</button>
    <button class="button" onclick="ac_type(event)">AC</button>
    <button class="button" onclick="ac_type(event)">Non-AC</button>

    <!-- Hidden form to submit the values -->
    <form id="hiddenForm" action="${pageContext.request.contextPath}/getFilteredRoom" method="POST" style="display: none;">
        <input type="hidden" id="roomTypeInput" name="roomType" value="">
        <input type="hidden" id="acTypeInput" name="acType" value="">
    </form>
    
    <c:if test="${not empty room}">
        <c:forEach var="rom" items="${room}">
            <c:if test="${rom.availabilityStatus == 'Available'}">
                <div class="room">
                    <h3>${rom.roomType} (Room ID: ${rom.roomId})</h3>
                    <p>Description: ${rom.description}</p>
                    <p>Capacity: ${rom.noOfPerson} Persons</p>
                    <p>Price: $${rom.price}</p>
                    <p>AC Type: ${rom.ac_type}</p>
                    <img src="${rom.img1}" alt="Room Image 1" />
                    <hr/>
                </div>
            </c:if>
        </c:forEach>
    </c:if>
    
    <c:if test="${empty room}">
        <p>No rooms available at the moment.</p>
    </c:if>
</body>
</html>
