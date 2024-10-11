<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<%@ page import="java.util.List" %>
<%@ page import="com.model.BookingWithPayments" %>
<%@ page import="com.model.Payment" %>
<%@ page import="com.model.Booking" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<jsp:include page="userHeader.jsp" />
<body>

<%
    List<BookingWithPayments> bookingsWithPayments = (List<BookingWithPayments>) request.getAttribute("bookingsWithPayments");
    if (bookingsWithPayments == null || bookingsWithPayments.isEmpty()) {
%>
        <div style="color: red; font-weight: bold;">
            No booking information available. Please check back later or try again.
        </div>
<%
        // No initialization of bookingsWithPayments as an empty list
    }
%>
 <h1>User Profile for ${user.name}</h1>
    
    <h2>Bookings</h2>
    <c:if test="${not empty bookingsWithPayments}">
        <c:forEach var="bookingWithPayments" items="${bookingsWithPayments}">
            <h3>Booking ID: ${bookingWithPayments.booking.id}</h3>
            <p>Room Price: ${bookingWithPayments.booking.room_price}</p>
            <p>Service Price: ${bookingWithPayments.booking.service_price}</p>
            <p>Total Amount: ${bookingWithPayments.booking.total_amount}</p>
            <p>Check-in: ${bookingWithPayments.booking.checkin}</p>
            <p>Check-out: ${bookingWithPayments.booking.checkout}</p>
            <p>Payment Status: ${bookingWithPayments.booking.payment_status}</p>

            <h4>Payments:</h4>
			<c:if test="${not empty bookingWithPayments.payments}">
			    <c:forEach var="payment" items="${bookingWithPayments.payments}">
			        <p>Payment ID: ${payment.pId}</p>
			        <p>Amount: ${payment.amount}</p>
			        <p>Payment Type: ${payment.paymentType}</p>
			        <p>Remaining Amount: ${payment.remainingAmount}</p>
			        <p>Payment Date: ${payment.paymentDate}</p>
			        <hr>
			    </c:forEach>
			</c:if>
			<c:if test="${empty bookingWithPayments.payments}">
			    <p>No payments found for this booking.</p>
			</c:if>
        </c:forEach>
    </c:if>
    
    <c:if test="${empty bookingsWithPayments}">
        <p>No bookings found for this user.</p>
    </c:if>

</body>
</html>