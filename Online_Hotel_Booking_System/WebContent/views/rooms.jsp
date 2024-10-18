<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.util.List" %>
<%@ page import="com.model.rooms" %>

 <%
    // Retrieve user ID and username from session
    Integer userId = (Integer) session.getAttribute("ru_id");
    String username = (String) session.getAttribute("username");
%>
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
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
    
    <style>
       
        .room-container{
        	
        	 display: flex;
		     flex-wrap: wrap;
		     gap: 30px;
		     justify-content: space-around;
        	
        	
        }
        .room{
        	flex-basis: 30%;
        	display: grid;
        	grid-template-rows:  auto 1fr auto auto;
        	grid-template-columns: 1fr 1fr;
        	padding:5px;
        	border: 2px solid black;
        	border-radius: 10px;  
        	 align-items: center;
        	height: auto; /* Adjusted height to auto to fit the content */
		    max-height: 500px; /* Optionally set a max-height */
		    box-sizing: border-box; 
        }
        .favourite{
         	 grid-row: 1 / 2;
         	 grid-column: 2 / 3;
        	justify-self: end;    
        }
        .room h3{
        	 grid-row: 1 / 2;
         	 grid-column: 1 / 2;
        	justify-self: start;
        	margin: 0; 
        }
        .roomimg{
        	 grid-row: 2 / 3;
         	 grid-column: 1 / 3;
        	display: flex;
		    justify-content: center;
		    align-items: center;
		    width: 100%;
		   
		  
        }
        .roomimg img{
	        max-width: 75%;
		    height: 75%;
		    object-fit: cover;
		    border-radius: 15px;
        
        }
        .Price{
         	grid-row: 3 / 4;
         	grid-column: 1 / 3;
        	justify-self: center; 
        	 
        }
        .AC{
        	grid-row: 4 / 5;
         	grid-column: 1 / 2;
        	justify-self: center; 
        	 
        }
        .Capacity{
        	grid-row: 4 / 5;
         	grid-column: 2 / 3;
        	justify-self: center; 
        }
        
 
    </style>
</head>
<jsp:include page="userHeader.jsp" />
<body>
	
	<div class="filter">
	    <button class="button" onclick="roomType(event)">All</button>
	    <button class="button" onclick="roomType(event)">Deluxe</button>
	    <button class="button" onclick="roomType(event)">Single</button>
	    <button class="button" onclick="roomType(event)">Double</button>
	    <button class="button" onclick="ac_type(event)">AC</button>
	    <button class="button" onclick="ac_type(event)">Non-AC</button>
    </div>

<div class="room-container">
    <!-- Hidden form to submit the values -->
    <form id="hiddenForm" action="${pageContext.request.contextPath}/getFilteredRoom" method="POST" style="display: none;">
        <input type="hidden" id="roomTypeInput" name="roomType" value="">
        <input type="hidden" id="acTypeInput" name="acType" value="">
    </form>
    
    <c:if test="${not empty room}">
    <c:forEach var="rom" items="${room}">
        <c:if test="${rom.availabilityStatus == 'Available'}">
            <div class="room" style="display: flex; flex-direction: column; align-items: center; padding: 15px; border: 2px solid #000; border-radius: 10px; margin: 10px; width: 300px; box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);">
                
                <% if (userId != null && username != null) { %>
                    <a class="favourite" href="${pageContext.request.contextPath}/addFavouriteRoom?roomId=${rom.roomId}&userId=${ru_id}" style="align-self: flex-end; margin-bottom: 10px; text-decoration: none;">
                        <i class="bi bi-heart favorite" style="font-size: 1.5rem; color: #007BFF;"></i>
                    </a>
                <% } %>

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
        </c:if>
    </c:forEach>
</c:if>

<c:if test="${empty room}">
    <p style="text-align: center; margin-top: 20px; font-size: 1.1rem; color: #666;">No rooms available at the moment.</p>
</c:if>

</div>
    
</body>
<jsp:include page="footer.jsp" />

</html>
