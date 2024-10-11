<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<jsp:include page="userHeader.jsp" />

<body>
	<h1>Payment Sucessful</h1>
	<h1>${amount}</h1>
     <a href="${pageContext.request.contextPath}/ReadUserProfile?userId=${ru_id}" >View Details</a>
</body>
</html>