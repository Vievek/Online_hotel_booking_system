<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<style>
        .main {
            
            align-items: center;
            height: 100vh;
            margin: 0;
            font-family: Arial, sans-serif;
            text-align: center;
            background-color: #f9f9f9;
        }
        h1 {
            margin: 50px 0 0;
           justify-content: center;
            
        }
        .button {
            display: inline-block;
            text-decoration: none;
            background-color: #FF007B;
            color: white;
            padding: 10px 20px;
            border-radius: 5px;
            font-weight: bold;
            margin-top: 20px;
            justify-content: center;
            
        }
    </style>
</head>
<jsp:include page="userHeader.jsp" />

<body>
  <div class="main">
        <h1 style="color:#9A9A9A">Payment Successful</h1>
        <h1>${amount}</h1>
        <a href="${pageContext.request.contextPath}/ReadUserProfile?userId=${ru_id}" class="button">View Details</a>
  </div>
</body>
<jsp:include page="footer.jsp" />

</html>