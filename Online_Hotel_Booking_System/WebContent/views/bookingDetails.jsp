<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title >Booking Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 100px;
            background-color: #f9f9f9;
            color: #333;
        }
        .booking-container {
            background-color:#F0F0F0;
            padding: 20px;
            max-width: 600px;
            margin: 0 auto;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .booking-details {
            display: flex;
            flex-direction: column;
            gap: 10px;
            padding: 10px 0;
        }
        .booking-details .item {
            display: flex;
            justify-content: space-between;
        }
        .booking-details .item label {
            font-weight: bold;
        }
        .actions {
            margin-top: 20px;
            display: flex;
            gap: 20px;
            justify-content: center;
        }
        .actions a {
            text-decoration: none;
            color: white;
            background-color: #FF007B;
            padding: 10px 20px;
            border-radius: 5px;
        }
        h1 {
            text-align: center;
            color: #333;
        }
        
        .item{
        	color: #9A9A9A;
        	padding:2px;
        	margin:4px;
        	
        	
        
        
        }
    </style>
</head>
<jsp:include page="userHeader.jsp" />

<body>

<div class="booking-container">
    <h1 style="color:#FF007B">Booking Details</h1>

    <!-- Check if booking object is available in request -->
    <c:if test="${not empty booking}">
        <div class="booking-details">
            <div class="item">
                <label>Booking ID:</label> <span>${booking.id}</span>
            </div>
            <div class="item">
                <label>Room Price:</label> <span>${booking.room_price}</span>
            </div>
            <div class="item">
                <label>Service Price:</label> <span>${booking.service_price}</span>
            </div>
            <div class="item">
                <label>Total Amount:</label> <span>${booking.total_amount}</span>
            </div>
            <div class="item">
                <label>Check-in Date:</label> <span>${booking.checkin}</span>
            </div>
            <div class="item">
                <label>Check-out Date:</label> <span>${booking.checkout}</span>
            </div>
            <div class="item">
                <label>Payment Status:</label> <span>${booking.payment_status}</span>
            </div>
            <div class="item">
                <label>User ID:</label> <span>${booking.ru_id}</span>
            </div>
            <div class="item">
                <label>Room ID:</label> <span>${booking.r_id}</span>
            </div>
        </div>

        <div class="actions">
            <a href="${pageContext.request.contextPath}/DeleteBooking?bId=${booking.id}" >Cancel order</a>
            <a href="${pageContext.request.contextPath}/UpdateBookingRead?bId=${booking.id}" >Edit booking</a>
            <a href="${pageContext.request.contextPath}/PaymentRead?bId=${booking.id}" >Confirm order</a>
        </div>
    </c:if>

    <!-- If booking object is not available -->
   <c:if test="${empty booking}">
        <p style="color:#9A9A9A">No booking details found for this ID.</p>
    </c:if> 
</div>

</body>
<jsp:include page="footer.jsp" />

</html>