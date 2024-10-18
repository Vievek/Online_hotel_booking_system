<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%
    // Retrieve user ID and username from session
    Integer MuserId = (Integer) session.getAttribute("m_id");
    Integer userId = (Integer) session.getAttribute("userId");
    String username = (String) session.getAttribute("username");
%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Admin Dashboard</title>
  <style>
    * {
      margin: 0;
      padding: 0;
      box-sizing: border-box;
      font-family: Arial, sans-serif;
    }

    body {
      display: flex;
      height: 100vh;
      background-color: #f4f6f9;
    }

    /* Sidebar */
    .sidebar {
      width: 250px;
      background-color: #343a40;
      color: white;
      padding: 20px;
    }

    .sidebar h2 {
      text-align: center;
      margin-bottom: 40px;
      font-size: 24px;
    }

    .sidebar ul {
      list-style: none;
    }

    .sidebar ul li {
      padding: 15px;
      font-size: 18px;
      cursor: pointer;
    }

    .sidebar ul li:hover {
      background-color: #495057;
    }

    /* Header */
    .main {
      flex: 1;
      padding: 20px;
    }

    .header {
      display: flex;
      justify-content: space-between;
      background-color: #fff;
      padding: 20px;
      box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
    }

    .header h1 {
      font-size: 28px;
    }

    .header .user-info {
      font-size: 18px;
      color: #343a40;
    }

    /* Cards */
    .card {
      background-color: #fff;
      padding: 20px;
      border-radius: 8px;
      box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
      margin-bottom: 20px;
      flex: 1;
      text-align: center;
    }

    .cards-container {
      display: flex;
      gap: 20px;
      margin-top: 20px;
    }

    .card h3 {
      margin-bottom: 10px;
      font-size: 22px;
    }

    .card p {
      font-size: 18px;
      color: #666;
    }
     h2 {
            color: #333;
            margin-top: 20px;
            grid-column: 3/ 4;
            grid-row: 2 / 3; 
        }
        table {
            width: 80%;
            margin: 20px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
            grid-column: 2/ -1;
            grid-row: 3 / 4; 
        }
        th, td {
            padding: 12px 15px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f4f4f4;
            color: #333;
        }
        tr:nth-child(even) {
            background-color: #f9f9f9;
        }
        tr:hover {
            background-color: #f1f1f1;
        }
  </style>
</head>
<jsp:include page="AdminHeader.jsp" />

<body>

  <div class="sidebar">
    <h2>Manager Dashboard</h2>
    <ul>
      <li><a href="${pageContext.request.contextPath}/Managerhome">Dashboard</a></li>
      <li></li>
      <li>Orders</li>
      <li>Reports</li>
      <li>Settings</li>
    </ul>
  </div>

  <!-- Main Content -->
  <div class="main">
    <!-- Header -->
    <div class="header">
      <h1>Welcome, Admin</h1>
      <div class="user-info">Logged in as: <strong>admin</strong></div>
    </div>

    <!-- Dashboard Cards -->
    <div class="cards-container">
      <div class="card">
        <h3>Total Users</h3>
        <p>1,200</p>
      </div>
      <div class="card">
        <h3>Total Orders</h3>
        <p>450</p>
      </div>
      <div class="card">
        <h3>Revenue</h3>
        <p>$24,500</p>
      </div>
    </div>
  </div>

	
    
    



</body>
</html>