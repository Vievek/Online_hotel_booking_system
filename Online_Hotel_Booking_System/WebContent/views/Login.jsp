
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>   
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <style>
            body, html {
                height: 100%;
                margin: 0;
                font-family: 'Poppins', sans-serif;
                justify-content: center;
                align-items: center;
                padding: 0;
            }
            
            .container {
            
                display:flex;
                width: 100%; /* Full width */
                height: 100%; /* Full height */
                border: 1px solid #ccc;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }
    
            .left {
                flex: 2;
                padding: 40px;
                background-color: white;
                display: flex;
                flex-direction: column;
                justify-content: center;
            }
    
            h2 {
                margin-bottom: 20px;
                font-weight: bold;
            }
    
            .welcome-text {
                font-size: 24px;
                font-weight: bold;
            }
    
            form {
                display: flex;
                flex-direction: column;
            }
    
            input[type="text"], input[type="password"] {
                padding: 10px;
                margin-bottom: 20px;
                border: 1px solid #ccc;
                border-radius: 4px;
            }
    
            input[type="submit"] {
                background-color: #FF007B;
                color: white;
                border: none;
                padding: 10px;
                border-radius: 4px;
                cursor: pointer;
            }
    
            input[type="submit"]:hover {
                background-color: #e6006f;
            }
    
            .signup {
                margin-top: 20px;
                text-align: center;
            }
    
            .signup a {
                color: #FF007B;
                text-decoration: none;
            }
    
            .signup a:hover {
                text-decoration: underline;
            }
        </style>
        <script>
            function validateLogin() {
                const username = document.forms["loginForm"]["uid"].value;
                const password = document.forms["loginForm"]["psw"].value;
                const alphanumericPattern = /^[a-zA-Z0-9]+$/; // Allow only letters and numbers
    
                if (username === "" || password === "") {
                    alert("Username and password must be filled out.");
                    return false;
                } else if (!alphanumericPattern.test(username)) {
                    alert("Username must contain only letters and numbers.");
                    return false;
                } else if (!alphanumericPattern.test(password)) {
                    alert("Password must contain only letters and numbers.");
                    return false;
                }
                return true;
            }
        </script>
    </head>
    <body>
        <div class="container">
            <div class="left">
                <h2 style="color:#ff0066">Login</h2>
                    <%
                    String message = (String) request.getAttribute("message");
                    if ("success".equals(message)) {
                %>
                <script type="text/javascript">
                    alert("Registration Successful! You can now log in.");
                </script>
                <% } 
    
                    String errorMessage = (String) request.getAttribute("errorMessage");
                    if (errorMessage != null) {
                %>
                <div style="color: red;">
                    <strong>Error: </strong> <%= errorMessage %>
                </div>
                <% } %>
    
               
    
                <div class="welcome-text">Welcome Back!</div>
                <p>Enter your credentials to access your account</p>
                <form name="loginForm" action="${pageContext.request.contextPath}/userLogin" method="post" onsubmit="return validateLogin();">
                    User Name: <input type="text" name="uid" placeholder="Enter username"><br>
                    Password: <input type="password" name="psw" placeholder="Enter password"><br>
                    <input type="submit" value="Login">
                </form>
                <div class="signup">
                    Don't have an account? <a href="${pageContext.request.contextPath}/views/Register.jsp">Sign Up</a>
                </div>
            </div> 
    
            <!-- Right Side: Image -->
            <div class="right" style="flex: 3; background-image: url('https://www.oakfurnitureland.co.uk/blog/wp-content/uploads/2018/11/PARQUET-BEDSIDE-TABLE-our_hanbury_hideaway-image-1666099085353-1.jpeg'); background-size: cover; background-position: center;">
                <!-- Background image applied inline here -->
            </div>
        </div>
    </body>
    </html>