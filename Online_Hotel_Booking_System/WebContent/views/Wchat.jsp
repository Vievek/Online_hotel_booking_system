<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

    <%
    // Retrieve user ID and username from session
    Integer WuserId = (Integer) session.getAttribute("w_id");
    Integer userId = (Integer) session.getAttribute("userId");
    String username = (String) session.getAttribute("username");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
 <style>
        .body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            padding:0;
            margin:0;
            display:grid;
            grid-template-rows: 50px 1fr;
            grid-template-columns: 1fr 5fr;
            min-height: 100vh;
            
        }
        nav{
        	grid-row: 1 / 2; 
  			grid-column: 1/ 3;
  			border: 2px solid black;
  			background-color: #333; 
  			padding: 10px 30px; 
  			color: white;
        }
        nav ul{
            display: flex;                  
		    flex-direction: row;            
		    justify-content: space-between;
		    list-style: none;
		    allign-items:center;
        }
        nav ul li a{
        	text-decoration: none;
        	color: white;
        }
        
        .navsub{
        	display: flex;                   
		    flex-direction: row;            
		    justify-content: space-around;
		    gap:50px;
        }
        
       
        .chats{
        	grid-row: 2 / 3; 
  			grid-column: 1 / 2;
  			border: 2px solid black;
  			display: flex;
		    flex-direction: column; /* Align items in a column */
		    justify-content: flex-start; /* Align items to the start */
		    gap: 20px; /* Space between items */
		    padding:50px 5px;
  			
        }
        .chat{
        	border: 2px solid black;
        	padding 25px
        }
        .messages{
        	grid-row: 2 / 3; 
  			grid-column: 2 / 3;
  			border: 2px solid black;
  			display: grid;
		    grid-template-rows: 1fr 50px;
		    padding:50px 5px;
        }
        .message-container {
            display: flex;
            flex-direction: column;
            padding:20px;
            border: 2px solid black;
            grid-row: 1 / 2;
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
        .msgform{
        	grid-row: 2 / 3;
			padding:10px 5px;   
			
        }
        .msgform-items{
          display:grid;
          gap:10px;
          grid-template-columns: 1fr 200px;
          grid-template-rows: 1fr ;
        }
        .msgform-items textarea{
        grid-column: 1 / 2;
        grid-row: 1 / 2;
        border: 2px solid black;
        }
        .msgform-items button{
        grid-column: 2 / 3;
         grid-row: 1 / 2;
        border: 2px solid black;
        }
        
    </style>
</head>
<body>

<!-- Flag to track if m_id == 1 exists -->
<c:set var="m1Found" value="false" />
<!-- Flag to track if m_id == 26 exists -->
<c:set var="m26Found" value="false" />

<section class="body">
<nav>
	<ul>
		<li>Rooms</li>
		<li><a href="${pageContext.request.contextPath}/Wtasks">Dashboard</a></li>
		<div class="navsub">
			<li><a href="${pageContext.request.contextPath}/getChatList?Id=${userId}">Chat</a></li>
			<li><%= username %></li>
			<li><a href="${pageContext.request.contextPath}/logout">Logout</a></li>
		</div>
	</ul>
</nav>

<section class="chats">
<!-- Iterate over the chats list -->
<c:forEach var="chat" items="${chats}">
    <!-- Check if the current chat's m_id is 1 -->
    <c:if test="${chat.m_id == 1}">
        <c:set var="m1Found" value="true" /> <!-- Set the flag if found -->
        <div class="chat">
           <a href=${pageContext.request.contextPath}/getChatList?chatId=${chat.chat_id}&Id=${userId}>    
	            <p>${chat.managerName}</p>
            </a>   
            
        </div>
    </c:if>

    <!-- Check if the current chat's m_id is 26 -->
    <c:if test="${chat.m_id == 26}">
        <c:set var="m26Found" value="true" /> <!-- Set the flag if found -->
        <div class="chat">
            <a href=${pageContext.request.contextPath}/getChatList?chatId=${chat.chat_id}&Id=${userId}>       
            <p> ${chat.managerName}</p>
            </a>
            
        </div>
    </c:if>
</c:forEach>

<!-- If m_id 1 was not found in the entire list -->
<c:if test="${not m1Found}">
    <div class="chat">
        <p> No chat with Manager John Doe </p>
        <a href=${pageContext.request.contextPath}/WCreateChat?Id=${userId}&m_id=1>Click to chat</a>
    </div>
</c:if>

<!-- If m_id 26 was not found in the entire list -->
<c:if test="${not m26Found}">
    <div class="chat">
        <p>No chat with Manager Thiruverakan</p>
        <a href=${pageContext.request.contextPath}/WCreateChat?Id=${userId}&m_id=26>Click to chat</a>
    </div>
</c:if>

</section>



<section class="messages">

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
<div class="msgform">
 <c:if test="${not empty IchatId}">
    <form action="${pageContext.request.contextPath}/SendMessage" method="post" class="message-form">
        <input type="hidden" name="chat_id" value="${IchatId}"> 
        <input type="hidden" name="sender_id" value="${userId}"> 
         <input type="hidden" name="origin" value="Wjsp">
         <div class="msgform-items">
        <textarea name="message" placeholder="Type your message here..." required></textarea><br>
        <button type="submit">Send Message</button>
        </div>
    </form>
</c:if>
</div>

</section>
 
</section>

</body>
</html>