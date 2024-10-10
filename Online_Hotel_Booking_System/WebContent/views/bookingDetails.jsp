<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Booking Details</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        table {
            width: 50%;
            border-collapse: collapse;
            margin: 20px 0;
        }
        table, th, td {
            border: 1px solid black;
        }
        th, td {
            padding: 10px;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        h1 {
            color: #333;
        }
    </style>
</head>
<body>

<h1>Booking Details</h1>

<!-- Check if booking object is available in request -->
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
            <th>Check-in Date</th>
            <td>${booking.checkin}</td>
        </tr>
        <tr>
            <th>Check-out Date</th>
            <td>${booking.checkout}</td>
        </tr>
        <tr>
            <th>Payment Status</th>
            <td>${booking.payment_status}</td>
        </tr>
        <tr>
            <th>User ID</th>
            <td>${booking.ru_id}</td>
        </tr>
        <tr>
            <th>Room ID</th>
            <td>${booking.r_id}</td>
        </tr>
    </table>
</c:if>

<!-- If booking object is not available -->
<c:if test="${empty booking}">
    <p>No booking details found for this ID.</p>
</c:if>

</body>
</html>
