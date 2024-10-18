<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Footer Page</title>
    <style>
        body {
            margin: 0;
            font-family: 'Arial', sans-serif;
            background-color: #f7f7f7;
        }
        .footer {
            background-color: #f1f1f1;
            padding: 40px 0;
        }
        .footer .newsletter {
            text-align: center;
            margin-bottom: 30px;
        }
        .footer .newsletter p {
            font-weight: bold;
            font-size: 14px;
            margin-bottom: 10px;
        }
        .footer .newsletter input {
            padding: 10px;
            width: 250px;
            border: 1px solid #ccc;
            border-radius: 3px;
        }
        .footer .newsletter button {
            padding: 10px 15px;
            background-color: #333;
            color: white;
            border: none;
            border-radius: 3px;
            cursor: pointer;
        }
        .footer .columns {
            display: flex;
            justify-content: space-between;
            max-width: 1000px;
            margin: 0 auto;
            padding: 20px 0;
        }
        .footer .column {
            flex: 1;
            margin: 0 15px;
        }
        .footer .column h3 {
            color: #FF0080;
            font-size: 24px;
            margin-bottom: 15px;
        }
        .footer .column ul {
            list-style: none;
            padding: 0;
        }
        .footer .column ul li {
            margin-bottom: 10px;
            font-size: 14px;
        }
        .footer .column ul li a {
            text-decoration: none;
            color: #333;
        }
        .footer .column p {
            font-size: 14px;
            color: #777;
            margin-bottom: 10px;
        }
        .footer .playstore-buttons {
            margin-top: 15px;
        }
        .footer .playstore-buttons button {
            background-color: #ccc;
            color: black;
            border: none;
            padding: 10px 20px;
            margin-right: 10px;
            border-radius: 5px;
            cursor: pointer;
        }
        .footer .playstore-buttons button:hover {
            background-color: #b3b3b3;
        }
        .footer .social-icons {
            margin-top: 20px;
        }
        .footer .social-icons a {
            margin: 0 10px;
            font-size: 18px;
            color: #333;
        }
        .footer .social-icons a:hover {
            color: #FF0080;
        }
        .footer-bottom {
            margin-top: 20px;
            border-top: 1px solid #ccc;
            padding-top: 10px;
            text-align: center;
            font-size: 14px;
            color: #777;
        }
    </style>
</head>
<body>
<footer>
<div class="footer">
    <!-- Newsletter Section -->
    <div class="newsletter">
        <p style= "font-size:20px">NEWSLETTER</p>
        <p style = "color:#484848">Stay up to date<p>
        <input type="email" placeholder="Your Email...">
        <button>Subscribe</button>
    </div>
    
    <!-- Footer Columns -->
    <div class="columns">
        <!-- Rooms Section -->
        <div class="column">
            <h3>ROOMS</h3>
            <p>Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.</p>
           <%--  <div class="playstore-buttons">
                <button>PlayStore</button>
                <button>AppleStore</button>
            </div>
            --%>
        </div>
        <!-- Company Section -->
        <div class="column">
            <h5>COMPANY</h5>
            <ul>
                <li><a href="#">About Us</a></li>
                <li><a href="#">Legal Information</a></li>
                <li><a href="#">Contact Us</a></li>
                <li><a href="#">Blogs</a></li>
            </ul>
        </div>
        <!-- Help Center Section -->
        <div class="column">
            <h5>HELP CENTER</h5>
            <ul>
                <li><a href="#">Find a Property</a></li>
                <li><a href="#">How To Host?</a></li>
                <li><a href="#">Why Us?</a></li>
                <li><a href="#">FAQs</a></li>
                <li><a href="#">Rental Guides</a></li>
            </ul>
        </div>
        <!-- Contact Info Section -->
        <div class="column">
            <h5>CONTACT INFO</h5>
            <p>Phone: 1234567890</p>
            <p>Email: company@email.com</p>
            <p>Location: 100 Smart Street, LA, USA</p>
           <%--  <div class="social-icons">
                <a href="#"><i class="fab fa-facebook"></i></a>
                <a href="#"><i class="fab fa-twitter"></i></a>
                <a href="#"><i class="fab fa-instagram"></i></a>
                <a href="#"><i class="fab fa-linkedin"></i></a>
            </div>  --%>
           
        </div>
    </div>
    
    <!-- Footer Bottom -->
    <div class="footer-bottom">
        <p>© 2022 theroomrentalsystem | All rights reserved</p>
        <p>Created with love by thecreation.design</p>
    </div>
</div>
</footer>

</body>
</html>