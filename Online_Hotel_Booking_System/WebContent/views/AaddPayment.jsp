<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 
   <style>
       body {
    font-family: Arial, sans-serif;
    background-color: #f5f5f5;
    margin: 0;
    padding: 0;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.container {
    background-color: white;
    padding: 30px;
    border-radius: 10px;
    box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    width: 400px;
}

h2 {
    text-align: center;
    color: #090946;
    margin-bottom: 20px;
}

.input-group {
    margin-bottom: 15px;
}

label {
    display: block;
    color: #090946;
    font-weight: bold;
    margin-bottom: 5px;
}

input {
    width: 100%;
    padding: 10px;
    border-radius: 5px;
    border: 1px solid #ddd;
    font-size: 16px;
}

.input-group-row {
    display: flex;
    justify-content: space-between; 
}

.input-group-half {
    flex: 2; 
}

.input-group-half:not(:last-child) {
    margin-right: 30px; 
}


.btn {
    background-color: orange;
    color: white;
    padding: 12px;
    padding-right: 50px;
    text-align: center;
    border: none;
    border-radius: 5px;
    width: 105%;
    font-size: 18px;
    cursor: pointer;
    margin-top: 20px;
}

.btn:hover {
    background-color: #e69500;
}

.card-logos {
    text-align: center;
    margin-bottom: 20px;
}

.card-logos img {
    width: 50px;
    margin-right: 10px;
}

.footer-note {
    font-size: 12px;
    color: #555;
    text-align: center;
    margin-top: 15px;
}

.footer-note a {
    color: orange;
    text-decoration: none;
}

.footer-note a:hover {
    text-decoration: underline;
}
       
   </style>
</head>
<jsp:include page="AdminHeader.jsp" />
<body>
<c:if test="${payment != null }">
        
        <form action="<%=request.getContextPath() %>/update" method="post">
        
        </c:if>
        
         <c:if test="${payment == null }">
        	<form action="${pageContext.request.contextPath}/AddPayment" method="post">
        </c:if>
        
        <caption>
        	<h2>
        		<c:if test="${payment != null }">
        				Edit Payment
        		 </c:if>
        		 
        		 <c:if test="${payment == null }">
        				Add Payment
        		 </c:if>
        	</h2>
        </caption>

        	<c:if test="${payment != null }">
        		<input type="hidden" name="id" value="${payment != null ? payment.id : ''}">
        	</c:if>
            
            <input type="hidden" name="page" value="manager">
            
    <!-- Booking ID -->
    <div class="input-group">
        <label for="booking-id">Booking ID</label>
        <input type="text" id="booking-id" value="${payment != null ? payment.bookingId : ''}" name="bid" placeholder="Booking ID" required>
    </div>

   


    <!-- Paid Amount -->
    <div class="input-group">
        <label for="paid-amount">Paid Amount</label>
        <input type="number" id="paid-amount" value="${payment != null ? payment.paidAmount : ''}" name="amount" placeholder="Enter Paid Amount" required>
    </div>

    <!-- Remaining Amount -->
    <div class="input-group">
        <label for="remaining-amount">Remaining Amount</label>
        <input type="number" id="remaining-amount" value="${payment != null ? payment.remainingAmount : ''}" name="Remaining" placeholder="Enter Remaining Amount" required>
    </div>

   

    <button type="submit">Submit Payment</button>
</form>



</body>
</html>