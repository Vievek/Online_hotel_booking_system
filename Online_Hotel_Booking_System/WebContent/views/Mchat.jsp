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
            background-color: #f4f4f4;
            padding: 20px;
        }
        .message-container {
            display: flex;
            flex-direction: column;
            margin: 10px 0;
        }
        .message {
            padding: 10px;
            border-radius: 5px;
            margin: 5px 0;
            max-width: 60%;
        }
        .message.sender-1 {
            align-self: flex-end; /* Align right for sender_id == 1 */
            background-color: #007bff; /* Blue background for sender_id == 1 */
            color: white;
        }
        .message.sender-other {
            align-self: flex-start; /* Align left for other senders */
            background-color: #e2e2e2; /* Gray background for other senders */
            color: black;
        }
        .no-messages {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>
<p>Your user ID is: <%= userId %></p>


<!-- Iterate over the chats list -->
<c:forEach var="chat" items="${chats}">
        <div class="chat">
           <a href=${pageContext.request.contextPath}/AgetChatList?chatId=${chat.chat_id}&Id=${userId}>    
	            <p>Worker Name: ${chat.workerName}</p>
            </a>   
            <hr>
        </div>
</c:forEach>



<hr>
<hr>

<h2>Chat Messages</h2>

<div class="message-container">
    <c:if test="${not empty messages}">
        <c:forEach var="message" items="${messages}">
			<div class="message ${message.sender_id == userId ? 'sender-1' : 'sender-other'}">
                <strong>Sender ID: ${message.sender_id}</strong><br>
                ${message.message}
            </div>
        </c:forEach>       
    </c:if>
</div>
 <c:if test="${not empty IchatId}">
    <form action="${pageContext.request.contextPath}/SendMessage" method="post" class="message-form">
        <input type="hidden" name="chat_id" value="${IchatId}"> 
        <input type="hidden" name="sender_id" value="${userId}">
         <input type="hidden" name="origin" value="Mjsp"> 
        <textarea name="message" placeholder="Type your message here..." required></textarea><br>
        <button type="submit">Send Message</button>
    </form>
</c:if>

 


</body>
</html>