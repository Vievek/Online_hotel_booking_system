<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    
          <%
    // Retrieve user ID and username from session
    Integer MuserId = (Integer) session.getAttribute("m_id");
    Integer userId = (Integer) session.getAttribute("userId");
    String username = (String) session.getAttribute("username");
%>
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
<c:if test="${service != null}">
    <form action="${pageContext.request.contextPath}/updateService" method="post">
</c:if>

<c:if test="${service == null}">
    <form action="${pageContext.request.contextPath}/AddService" method="post">
</c:if>

<caption>
    <h2>
        <c:if test="${service != null}">
            Edit Service
        </c:if>
        <c:if test="${service == null}">
            Add Service
        </c:if>
    </h2>
</caption>

<c:if test="${service != null}">
    <input type="hidden" name="id" value="${service != null ? service.services_id : ''}">
</c:if>


<!-- Service Name -->
<div class="input-group">
    <label for="service-name">Service Name</label>
    <input type="text" id="service-name" value="${service != null ? service.name : ''}" name="serviceName" placeholder="Enter Service Name" required>
</div>

<!-- Description -->
<div class="input-group">
    <label for="service-description">Description</label>
    <textarea id="service-description" name="description" placeholder="Enter Description" required>${service != null ? service.description : ''}</textarea>
</div>

<!-- Price -->
<div class="input-group">
    <label for="service-price">Price</label>
    <input type="number" id="service-price" value="${service != null ? service.price : ''}" name="price" placeholder="Enter Price" required>
</div>

 <input type="hidden" name="m_id" value="<%= MuserId %>">

<button type="submit">Submit Service</button>
</form>

</body>
</html>