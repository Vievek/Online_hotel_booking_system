<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Payment List</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f9f9f9;
            margin: 0;
            padding: 0;
            display: flex;
            flex-direction: column;
            align-items: center;
        }
        h2 {
            color: #333;
            margin-top: 20px;
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        }
        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f4f4f4;
            color: #333;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
        a {
            text-decoration: none;
            color: #1a73e8;
            font-weight: bold;
        }
        a:hover {
            text-decoration: underline;
        }
        .actions {
            display: flex;
            justify-content: space-around;
        }
        .add-btn {
            display: block;
            margin: 20px 0;
            padding: 10px 20px;
            background-color: #28a745;
            color: white;
            text-align: center;
            border-radius: 5px;
        }
        .add-btn:hover {
            background-color: #218838;
        }
    </style>
</head>
<jsp:include page="AdminHeader.jsp" />

<body>
    <h2>Payment List</h2>

    <a href="<%=request.getContextPath() %>/new" class="add-btn">Add New Payment</a>

    <table>
        <tr>
            <th>Guest ID</th>
            <th>Guest Name</th>
            <th>Username</th>
            <th>Password</th>
            <th>Email ID</th>
            <th>Phone No</th>
            
        </tr>
        <c:if test="${not empty Users}">
            <c:forEach var="guest" items="${Users}">
                <tr>
                    <td>${guest.id}</td>
                    <td>${guest.name}</td>
                    <td>${guest.username}</td>
                    <td>${guest.password}</td>
                    <td>${guest.email}</td>
                    <td>${guest.phone}</td>
                    
                    <td class="actions">
                        <a href="edit?id=${payment.pId}">Edit</a>
                        <a href="delete?id=${payment.pId}">Delete</a>
                    </td>
                </tr>
            </c:forEach>
        </c:if>
        
        <c:if test="${empty Users}">
            <tr>
                <td colspan="7">No Users records found.</td>
            </tr>
        </c:if>
    </table>
</body>
</html>
    