<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> <!-- Importing JSTL functions -->

    
<%@ page import="java.util.List" %>
<%@ page import="com.model.BookingWithPayments" %>
<%@ page import="com.model.Payment" %>
<%@ page import="com.model.Booking" %>


<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.10.5/font/bootstrap-icons.css">
<style>
	main{
		  display: grid;
		grid-template-rows: repeat(3,100px) 100px repeat(4,50px) 1fr;
		grid-template-columns: 1fr 1.5fr 1fr;
		
	}
	.headimg{
		grid-row: 1 / 3; 
  		grid-column: 1 / -1;
  		display: flex;
	    justify-content: center;
	    align-items: center;
	    width: 100%;
	    background: #f0f0f0;
	}
	.headimg img{
		 display: block;
         width: 100%;
         margin: 0 auto;
         border-radius: 10px;
         max-height: 200px; /* Decreased height */
         object-fit: cover
	}
	.userimg{
		grid-row: 2 / 4; 
  		grid-column: 2 / 3;
  		display: flex;
	    justify-content: center;
	    align-items: center;
	    width: 100%;
	  
	}
	.userimg img{
		max-width: 100%;
	    height: 100%;
	    object-fit: cover;
	    border-radius: 15px;
	}
	.hi{
		grid-row: 4 / 5; 
  		grid-column: 2 / 3;
  		justify-self: center;
	}
	.name{
		grid-row: 5 / 6; 
  		grid-column: 2 / 3;
  		justify-self: center;
	}
	.email{
		grid-row: 6 / 7; 
  		grid-column: 2 / 3;
  		justify-self: center;
	}
	.phone{
		grid-row: 7 / 8; 
  		grid-column: 2 / 3;
  		justify-self: center;
	}
	.btns{
		grid-row: 8 / 9; 
  		grid-column: 2 / 3;
  		display:flex;
  		flex-direction:row;
  		justify-content: space-between;
    	align-items: center;
    	padding:20px;
  		
  	}
	.booking{
		grid-row: 9 / 10; 
  		grid-column: 1 / 4;
		margin:20px 100px 0;
		padding:20px 300px;
		border: 3px solid #ff6347; /* 3px solid tomato-colored border */
    	border-radius: 10px;
    	width: auto;
  		
  	}
	
</style>
</head>
<jsp:include page="userHeader.jsp" />
<body>

<%
    List<BookingWithPayments> bookingsWithPayments = (List<BookingWithPayments>) request.getAttribute("bookingsWithPayments");
    if (bookingsWithPayments == null || bookingsWithPayments.isEmpty()) {
%>
        
<%
        // No initialization of bookingsWithPayments as an empty list
    }
%>
<main>
	 <div class="headimg">
	 	<img src="https://i.pinimg.com/1200x/0b/ce/fc/0bcefcf6a4e16586dafde730b90e271d.jpg" />
	 </div>
	 <div class="userimg">
	 	<img src="https://www.pngall.com/wp-content/uploads/5/Profile-PNG-File.png" />
	 </div>		
    <div class="hi">
    	<h1>Good to see you again!</h1>
	 </div>	
	 <div class="name">
 		<h1>Name : ${user.name}</h1>
	 </div>	
	 <div class="email">
 		<h1>Email : ${user.email}</h1>
	 </div>
	 <div class="phone">
 		<h1>phone no : ${user.phone}</h1>
	 </div>		
	 
	 <div class="btns">
 		  <a href="${pageContext.request.contextPath}/ShowEditUser?uId=${ru_id}"> edit profile</a>
		  <a href="${pageContext.request.contextPath}/DeleteAccount?uId=${ru_id}"> delete account</a>
		  <a href="${pageContext.request.contextPath}/logout">Logout</a>
	 </div>
	
 
  
 <div class="booking">
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
					<table border="1" cellpadding="10" cellspacing="0">
					    <thead>
					        <tr>
					            <th>Payment ID</th>
					            <th>Amount</th>
					            <th>Payment Type</th>
					            <th>Remaining Amount</th>
					            <th>Payment Date</th>
					        </tr>
					    </thead>
					    <tbody>
					        <c:forEach var="payment" items="${bookingWithPayments.payments}">
					            <tr>
					                <td>${payment.pId}</td>
					                <td>${payment.amount}</td>
					                <td>${payment.paymentType}</td>
					                <td>${payment.remainingAmount}</td>
					                <td>${payment.paymentDate}</td>
					            </tr>
					        </c:forEach>
					    </tbody>
					</table>
				</c:if>
				<c:if test="${empty bookingWithPayments.payments}">
				    <p>No payments found for this booking.</p>
				</c:if>
				<c:if test="${payment.remainingAmount != '0'}">
				    <a href="${pageContext.request.contextPath}/PaymentRead?bId=${bookingWithPayments.booking.id}">Pay Remaining Amount</a>
				</c:if>
	
	        </c:forEach>
	    </c:if>
	    
	    <c:if test="${empty bookingsWithPayments}">
	        <p>No bookings found for this user.</p>
	    </c:if>
	 </div>
  
    
</main>
</body>
<jsp:include page="footer.jsp" />

</html>