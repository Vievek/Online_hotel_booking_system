<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Customer Details</title>
</head>
<body>
 
    <c:if test="${not empty cusDetails}">
        <c:forEach var="cus" items="${cusDetails}">
            <p>Customer ID: ${cus.id}</p>
            <p>Customer Name: ${cus.name}</p>
            <p>Customer Email: ${cus.email}</p>
            <p>Customer Phone: ${cus.phone}</p>
            <p>Customer Username: ${cus.username}</p>
            <p>Customer Password: ${cus.password}</p>

            <!-- Create URL for update action -->
            <c:url value="updatecustomer.jsp" var="cusupdate">
                <c:param name="id" value="${cus.id}"/>
                <c:param name="name" value="${cus.name}"/>
                <c:param name="email" value="${cus.email}"/>
                <c:param name="phone" value="${cus.phone}"/>
                <c:param name="username" value="${cus.username}"/>
                <c:param name="password" value="${cus.password}"/>
            </c:url>
            
            <a href="${cusupdate}">
                <input type="button" name="update" value="Update My Data"/>
            </a>
            <br>
            
            <c:url value = "deletecustomer.jsp" var="cusdelete">
              	<c:param name="id" value="${cus.id}"/>
                <c:param name="name" value="${cus.name}"/>
                <c:param name="email" value="${cus.email}"/>
                <c:param name="phone" value="${cus.phone}"/>
                <c:param name="username" value="${cus.username}"/>
                <c:param name="password" value="${cus.password}"/>
            </c:url>
            <a href="${cusdelete}">
                <input type="button" name="delete" value="Delete My Account"/>
            </a>
            
            <hr/>
        </c:forEach>
    </c:if>

    <c:if test="${empty cusDetails}">
        <p>No customer details available.</p>
    </c:if>

</body>
</html>
