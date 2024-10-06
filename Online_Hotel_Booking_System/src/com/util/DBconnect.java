package com.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBconnect {

	private static String url = "jdbc:mysql://localhost:3306/online_hotel_reservation_system?useSSL=false";
	private static String user ="root";
	private static String pass = "Passw0rd!!!";
	private static Connection con;
	
	public static Connection getConnection() {
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			con = DriverManager.getConnection(url,user,pass);
		}
		catch(Exception e) {
			System.out.println("Database connection is not successful");
            e.printStackTrace();  // This will help identify the root cause

		}
		return con;
	}
}
