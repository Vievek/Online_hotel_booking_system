<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Booking Payment</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
            background-color: #f9f9f9;
        }

        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
            background-color: #F0F0F0;
        }

        th {
            padding: 10px;
            border: 1px solid #ddd;
            text-align: left;
        }
         td{
          	padding: 20px;
            border: 1px solid #ddd;
            text-align: left;
            width: 200px;  /* Adjust the width as needed */
    		
         }

        th {
            background-color: #F0F0F0;
        }

        /* Flex container for horizontal layout */
        .container {
            display: flex;
            justify-content: space-between;
            gap: 20px;
        }

        .other-div {
            background-color: #F0F0F0;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            width: 20%;
        }
         .payment-form{
         	background-color: #ffffff;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 5px;
            width: 80%;
         
         
         }

        .payment-form h2 {
            margin-top: 0;
        }

        .payment-form label {
            display: block;
            margin-bottom: 5px;
        }

        .payment-form input[type="text"],
        .payment-form input[type="number"] {
            width: 100%;
            padding: 8px;
            margin-bottom: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
        }

        .payment-form button {
            background-color: #FF007B;
            color: white;
            padding: 10px 15px;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }

        .payment-form button:hover {
            background-color: #EC4B99;
        }
    </style>
</head>
<jsp:include page="userHeader.jsp" />

<body>
    <c:if test="${not empty booking}">
       
        <!-- Container holding two divs side by side -->
        <div class="container">
            <!-- Payment Form Div -->
            <div class="payment-form">
                <h2>Payment Form</h2>
                <form action="${pageContext.request.contextPath}/AddPayment" method="POST">
                    <label for="cardholder-name">Cardholder's Name:</label>
                    <input type="text" id="cardholder-name" name="cardholder_name" required  placeholder="<<Vievegan>>">
                    
                    <label for="card-number">Card Number:</label>
                    <input type="text" id="card-number" name="card_number" required maxlength="16" pattern="\d{16}" placeholder="1234 5678 9012 3456">
                    
                    <label for="expiry-date">Expiry Date:</label>
                    <input type="text" id="expiry-date" name="expiry_date" required placeholder="MM/YY" pattern="\d{2}/\d{2}">
                    
                    <label for="cvc">CVC:</label>
                    <input type="text" id="cvc" name="cvc" required maxlength="3" pattern="\d{3}" placeholder="123">
                    
                    <label for="amount">Amount:</label>
                    <input type="number" id="amount" name="amount" required min="1" step="0.01" placeholder="0.00">
                    
                    <input type="hidden" name="Remaining" value="${remainingamount}">
                    <input type="hidden" name="total" value="${booking.total_amount}">
                    <input type="hidden" name="bid" value="${booking.id}">
                    
                    <button type="submit">Pay</button>
                </form>
            </div>

            <!-- Another Div (you can customize this content) -->
     <div class="other-div">
     <p style = "color:#FF007B; font-weight: bold;">Let's make payment </p>
     
         <table>
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
                <td>${remainingamount}</td>
            </tr>
        </table>
        
      </div>
        </div>
    </c:if>
    <jsp:include page="footer.jsp" />
    
</body>
</html>