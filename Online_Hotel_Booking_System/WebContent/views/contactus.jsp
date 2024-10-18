
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Contact Us</title>
    <style>
        /* General Styling */
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f5f5f5;
        }

        nav {
            display: flex;
            justify-content: space-between;
            padding: 20px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .logo {
            font-size: 24px;
            font-weight: bold;
            color: #ff0077;
        }

        nav ul {
            display: flex;
            list-style: none;
            padding: 0;
        }

        nav ul li {
            margin: 0 15px;
        }

        nav ul li a {
            text-decoration: none;
            color: #000;
            font-weight: bold;
        }

        .user-info {
            display: flex;
            align-items: center;
        }

        .user-info span {
            margin-right: 10px;
            font-size: 18px;
        }

        .profile-icon {
            width: 35px;
            height: 35px;
            border-radius: 50%;
        }

        /* Hero Section */
        .hero {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 50px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            margin-bottom: 40px;
        }

        /* New styling for the paragraph */
        .hero-text h1 {
            font-size: 24px;
            color: #ff0077;
            max-width: 500px;
            margin-bottom: 20px;
        }

        .hero-text p {
            font-size: 18px;
            background-color: white; /* Pink background */
            color: #fff; /* White text */
            padding: 20px;
            border-radius: 10px; /* Rounded corners */
            max-width: 500px;
        }

        .hero-image img {
            width: 400px;
            height: auto;
            border-radius: 10px;
        }

        /* Contact Form Section */
        .contact-form-section {
            text-align: center;
            padding: 50px;
            background-color: #fff;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
        }

        .contact-form-section h2 {
            color: #ff0077;
            font-size: 32px;
            margin-bottom: 20px;
        }

        .contact-form-section p {
            font-size: 18px;
            color: #333;
            margin-bottom: 40px;
        }

        .contact-form-section form {
            display: inline-block;
            text-align: left;
        }

        .contact-form-section input {
            display: block;
            width: 300px;
            padding: 10px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        .contact-form-section button {
            width: 320px;
            padding: 12px;
            background-color: #ff0077;
            color: white;
            border: none;
            border-radius: 5px;
            font-size: 16px;
            cursor: pointer;
        }

        .contact-form-section button:hover {
            background-color: #ff3388;
        }
    </style>
</head>
<jsp:include page="userHeader.jsp" />

<body>

    <!-- Navigation Bar -->
    

    <!-- Hero Section with Description and Image -->
    <div class="hero">
        <div class="hero-text">
         
            <p><h3><b>ConsultUs provides consulting services that help business owners and leaders build a more valuable business.</b></h3>We worked with their founder to build a professional, modern site that follows the StoryBrand framework to clearly communicate the value it adds to potential clients.</p>
        </div>
        <!-- The image comes after the paragraph -->
        <div class="hero-image">
            <img src="https://athoughtfulplaceblog.com/wp-content/uploads/2016/06/summer-home-tour-2016-28.jpg" alt="Room">
        </div>
    </div>

    <!-- Contact Form Section -->
    <div class="contact-form-section">
        <h2>Get in touch</h2>
        <p>We'd love to hear from you. Please fill out this form.</p>
        <form action="submit_form.php" method="POST">
            <input type="text" name="name" placeholder="Name" required><br>
            <input type="email" name="email" placeholder="Email" required><br>
            <input type="tel" name="phone" placeholder="Phone Number" required><br>
            <input type="text" name="title" placeholder="Title/Position"><br>
            <input type="text" name="subject" placeholder="Subject"><br>
            <button type="submit">Connect with us</button>
        </form>
    </div>

</body>
<jsp:include page="footer.jsp" />

</html>