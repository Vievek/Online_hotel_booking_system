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
    <style>
        body{
        	display: grid;
        	grid-template-rows: 200px 100px 1fr 400px;
        	grid-template-columns: 100px 1fr 100px;        	
        	       	
        }
         body > .jsp-included-element{
        	grid-column: 1/ -1;
        	grid-row: 1 / 2; 
        }
        .filter{
        	grid-column: 1/ -1;
        	grid-row: 2 / 3; 
        }
        .room-container{
        	grid-column: 2/ 3;
        	grid-row: 3 / 4; 
        	display: grid;
        	grid-template-columns: repeat(3,1fr);
        	gap:30px 30px;
        	grid-auto-rows: 500px;
        	
        	
        }
        .room{
        	display: grid;
        	grid-template-rows: repeat(2,1fr) 3fr repeat(2,1fr); 
        	grid-template-columns:repeat(2,1fr);  
        	padding:5px;
        	border: 2px solid black;
        	border-radius: 10px;  
        	gap:2px 0; 
        }
        .favourite{
         	grid-column: 1/ -1;
        	grid-row: 1 / 2;   
        	justify-self: end;    
        }
        .room h3{
        	grid-column: 1/ -1;
        	grid-row: 2 / 3; 
        }
        .room img{
        	grid-column: 1/ -1;
        	grid-row: 3 / 4; 
        	border-radius: 10px;
        	width: 100%;
        	max-height: 100%;
        	object-fit: cover   
        }
        .Price{
        	grid-column: 1/ -1;
        	grid-row: 4/ 5; 
        }
        .AC{
        	grid-column: 1/ 2;
        	grid-row: 5/ 6;
        	justify-self:center; 
        }
        .Capacity{
        	grid-column: 2/ 3;
        	grid-row: 5/ 6;
        	justify-self:center; 
        }
        
        
        
        
        footer {
		    background-color: #333;
		    color: #fff;
		    padding: 20px 0;
		    text-align: center;
		    grid-column: 1/ -1;
        	grid-row: 4/5; 
		}
		
		.footer-content {
		    max-width: 800px;
		    margin: 0 auto;
		}
		
		.footer-content p {
		    margin: 0;
		    font-size: 14px;
		}
		
		.social-links {
		    list-style-type: none;
		    padding: 0;
		    margin: 10px 0 0;
		    display: flex;
		    justify-content: center;
		}
		
		.social-links li {
		    margin: 0 10px;
		}
		
		.social-links a {
		    color: #fff;
		    text-decoration: none;
		    font-size: 14px;
		}
		
		.social-links a:hover {
		    text-decoration: underline;
		}
    </style>
</head>
<body>
	<jsp:include page="userHeader.jsp" />
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
                <a href="${pageContext.request.contextPath}/roomDetails?roomId=${rom.roomId}&userId=${ru_id}" >	  
                <div class="room">
                
                    <h3>${rom.roomType}: ${rom.roomId}</h3>
                    <p class="Capacity">${rom.noOfPerson}</p>
                   <p class="Price"> $${rom.price}</p>
                    <p class="AC"> ${rom.ac_type}</p>
                    <img src="${rom.img1}" alt="Room Image 1" />
                    <% if (userId != null && username != null) { %>
                    <a  class="favourite" href="${pageContext.request.contextPath}/addFavouriteRoom?roomId=${rom.roomId}&userId=${ru_id}" >favourite</a>
                   		
                	  <% } %>
                    <hr/>
                </div>
                </a>
            </c:if>
        </c:forEach>
    </c:if>
    
    <c:if test="${empty room}">
        <p>No rooms available at the moment.</p>
    </c:if>
</div>
    <footer>
        <div class="footer-content">
            <p>&copy; 2024 Your Company. All rights reserved.</p>
            <ul class="social-links">
                <li><a href="#">Facebook</a></li>
                <li><a href="#">Twitter</a></li>
                <li><a href="#">Instagram</a></li>
            </ul>
        </div>
    </footer>
</body>
</html>
