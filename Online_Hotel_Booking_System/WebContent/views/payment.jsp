<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<c:if test="${not empty booking}">
    <table>
        <tr>
            <th>Booking ID</th>
            <td>${booking.id}</td>
        </tr>
        <tr>
            <th>Room Price</th>
            <td>${booking.room_price}</td>
        </tr>
        <tr>
            <th>Service Price</th>
            <td>${booking.service_price}</td>
        </tr>
        <tr>
            <th>Total Amount</th>
            <td>${booking.total_amount}</td>
        </tr>
       <tr>
            <th>Remaining Amount</th>
            <td>${remaining amount}</td>
        </tr>
      
</table>
<div class="payment-form">
    <h2>Payment Form</h2>
    <form action="/process_payment" method="POST">
        <!-- Cardholder's Name -->
        <label for="cardholder-name">Cardholder's Name:</label>
        <input type="text" id="cardholder-name" name="cardholder_name" required>
        
        <!-- Card Number -->
        <label for="card-number">Card Number:</label>
        <input type="text" id="card-number" name="card_number" required maxlength="16" pattern="\d{16}" placeholder="1234 5678 9012 3456">
        
        <!-- Expiry Date -->
        <label for="expiry-date">Expiry Date:</label>
        <input type="text" id="expiry-date" name="expiry_date" required placeholder="MM/YY" pattern="\d{2}/\d{2}">
        
        <!-- CVC -->
        <label for="cvc">CVC:</label>
        <input type="text" id="cvc" name="cvc" required maxlength="3" pattern="\d{3}" placeholder="123">
        
        <!-- Amount -->
        <label for="amount">Amount:</label>
        <input type="number" id="amount" name="amount" required min="1" step="0.01" placeholder="0.00">
        
        <input type="hidden" name="Remaining" value="${remaining amount}">
        <input type="hidden" name="total" value="${booking.total_amount}">
        <input type="hidden" name="bid" value="${booking.id}">
        
        <!-- Submit Button -->
        <button type="submit">Pay</button>
    </form>
</div>


    
            <a href="${pageContext.request.contextPath}/DeleteBooking?bId=${booking.id}" >Cancel order</a>
            <a href="${pageContext.request.contextPath}/UpdateBookingRead?bId=${booking.id}" >Edit booking</a>
            <a href="${pageContext.request.contextPath}/PaymentRead?bId=${booking.id}" >Confirm order</a>
                
	</c:if>
</body>
</html>