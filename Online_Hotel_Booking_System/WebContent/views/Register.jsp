<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <!DOCTYPE html>
    <html>
    <head>
        <meta charset="ISO-8859-1">
        <title>Get Started Now!</title>
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
                display: flex;
                width: 100%; /* Full width */
                height: 100%; /* Full height */
                margin: 0; /* Remove margin */
            }
    
            .form-container {
                flex: 1; /* Half of the container */
                padding: 40px; /* Add padding for aesthetics */
                background-color: white; /* Background color for the form */
                display: flex;
                flex-direction: column;
                justify-content: center;
            }
    
            h2 {
                margin-bottom: 20px;
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
    
            .signin {
                margin-top: 20px;
                text-align: center;
            }
    
            .signin a {
                color: #FF007B;
                text-decoration: none;
            }
    
            .signin a:hover {
                text-decoration: underline;
            }
    
            .image-container {
                flex: 1; /* Half of the container */
                background-image: url('https://www.oakfurnitureland.co.uk/blog/wp-content/uploads/2018/11/PARQUET-BEDSIDE-TABLE-our_hanbury_hideaway-image-1666099085353-1.jpeg'); /* Replace with your image */
                background-size: cover; /* Cover the entire area */
                background-position: center; /* Center the image */
                height: 100%; /* Ensure it takes full height */
            }
        </style>
        <script>
            function validateSignup() {
                const email = document.forms["signupForm"]["email"].value;
                const username = document.forms["signupForm"]["uid"].value;
                const password = document.forms["signupForm"]["psw"].value;
                const alphanumericPattern = /^[a-zA-Z0-9]+$/; // Allow only letters and numbers
                const emailPattern = /^[^\s@]+@[^\s@]+\.[^\s@]+$/; // Basic email pattern
    
                if (email === "") {
                    alert("Email must be filled out.");
                    return false;
                } else if (!emailPattern.test(email)) {
                    alert("Please enter a valid email address.");
                    return false;
                } else if (username === "") {
                    alert("Username must be filled out.");
                    return false;
                } else if (!alphanumericPattern.test(username)) {
                    alert("Username must contain only letters and numbers.");
                    return false;
                } else if (password === "") {
                    alert("Password must be filled out.");
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
            <!-- Left Side: Form -->
            <div class="form-container">
                <h2>Get Started Now!</h2>
    
                <%
                    // Check if the registration failed
                    String message = (String) request.getAttribute("message");
                    if ("fail".equals(message)) {
                %>
                <script type="text/javascript">
                    alert("User name not available! Try with a different user name.");
                </script>
                <% } %>
    
                <form name="signupForm" action="../userRegistration" method="post" onsubmit="return validateSignup();">
                    Name: <input type="text" name="name" placeholder="Enter your name"><br>
                    Email: <input type="text" name="email" placeholder="Enter your email"><br>
                    Phone: <input type="text" name="phone" placeholder="Enter Phone No"><br>
                    User Name: <input type="text" name="uid" placeholder="Enter username"><br>
                    Password: <input type="password" name="psw" placeholder="Enter password"><br>
                    
                    <input type="submit" value="Sign Up">
                </form>
                <div class="signin">
                    Already have an account? <a href="${pageContext.request.contextPath}/views/Login.jsp">Log In</a>
                </div>
            </div>
    
            <!-- Right Side: Image -->
            <div class="image-container"></div>
        </div>
    
    </body>
    </html>