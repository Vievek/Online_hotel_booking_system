
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Room Reservation Guide</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #fff;
        }

        /* Container for page */
        .container {
            width: 100%;
           
            margin: 0 auto;
            padding: 20px;
        }

        /* Bold heading */
        h1 {
            text-align: center;
            color: #ff0077;
            font-size: 32px;
            font-weight: bold;
            margin-bottom: 30px;
        }

        /* Description box */
        .description-box {
            background-color: #ff0077;
            color: #fff;
            border-radius: 10px;
            padding: 20px;
            margin-bottom: 30px;
            font-size: 18px;
        }

        .description-box p {
            margin: 0;
            text-align: center;
            line-height: 1.6;
        }

        /* Cover image */
        .cover-image {
            display: block;
            width: 100%;
           
            margin: 0 auto;
            border-radius: 10px;
            max-height: 300px; /* Decreased height */
            object-fit: cover; /* Ensures the image covers the area without distortion */
        }

        /* Small guides section */
        .guide-section {
            margin-top: 40px;
        }

        .guide-section h2 {
            text-align: center;
            color: #ff0077;
            font-size: 28px;
            margin-bottom: 20px;
        }

        .guide-list {
            list-style: none;
            padding: 0;
            margin: 0 auto;
            max-width: 800px;
        }

        .guide-list li {
            background-color: #f5f5f5;
            padding: 15px;
            border-radius: 5px;
            margin-bottom: 20px;
            font-size: 18px;
            color: #333;
        }

        .guide-list li span {
            font-weight: bold;
            color: #ff0077;
        }
    </style>
</head>
<jsp:include page="userHeader.jsp" />

<body>

    <div class="container">

        <!-- Welcome Heading -->
        <h1>Welcome to Our Room Reservation System</h1>

        <!-- Description Box -->
        <div class="description-box">
            <p>Our room reservation system allows you to easily book and manage rooms for your needs. To access the system, visit 
                <a href="#" style="color: white; text-decoration: underline;">[website link]</a> and log in using your credentials. Make sure you have a stable internet connection and a supported browser.
            </p>
        </div>

        <!-- Cover Image -->
        <div>
            <img class="cover-image" src="https://athoughtfulplaceblog.com/wp-content/uploads/2016/06/summer-home-tour-2016-28.jpg" alt="Cover Image">
        </div>

        <!-- Small Guide Section -->
        <div class="guide-section">
            <h2>How to Use the System</h2>
            <ul class="guide-list">
                <li><span>Step 1:</span> Visit the website and click on the login button.</li>
                <li><span>Step 2:</span> Enter your username and password, then press "Submit."</li>
                <li><span>Step 3:</span> Once logged in, you will be redirected to the dashboard where you can see available rooms.</li>
                <li><span>Step 4:</span> Select the room you want to book, and click on "Reserve Room."</li>
                <li><span>Step 5:</span> After selecting your booking dates and room, confirm your reservation by clicking "Confirm Booking."</li>
                <li><span>Step 6:</span> Check your reservation details anytime from the dashboard or your profile.</li>
            </ul>
        </div>

    </div>

</body>
<jsp:include page="footer.jsp" />

</html>