package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Booking_util {

		private static Connection con=null;
		private static Statement stmt =null;
		private static ResultSet rs = null;
		private static boolean isSuccess = false;
		

	    public static int insertBooking(String room_price, String service_price, String total_amount, String checkin,
	                                     String checkout, int ru_id, int r_id) {
	        int lastInsertedId = -1;
	        
	        try {
	            con = DBconnect.getConnection();
	            stmt = con.createStatement();

	            // Use PreparedStatement to avoid SQL injection
	            String sql = "INSERT INTO booking (room_price, service_price, total_amount, checkin, checkout, payment_status, ru_id, r_id) " +
	                         "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

	            // Using PreparedStatement
	            try (PreparedStatement pstmt = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
	                pstmt.setString(1, room_price);
	                pstmt.setString(2, service_price);
	                pstmt.setString(3, total_amount);
	                pstmt.setString(4, checkin);
	                pstmt.setString(5, checkout);
	                pstmt.setString(6, "not done");
	                pstmt.setInt(7, ru_id);
	                pstmt.setInt(8, r_id);

	                // Execute the update and retrieve the last inserted ID
	                int affectedRows = pstmt.executeUpdate();
	                if (affectedRows > 0) {
	                    // If insert was successful, retrieve the last inserted ID
	                    try (ResultSet generatedKeys = pstmt.getGeneratedKeys()) {
	                        if (generatedKeys.next()) {
	                            lastInsertedId = generatedKeys.getInt(1); // Get the ID of the newly inserted booking
	                        }
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            // Close the resources if necessary
	            try {
	                if (stmt != null) stmt.close();
	                if (con != null) con.close();
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }

	        return lastInsertedId; // Return the last inserted ID or -1 if failed
	    }
	}

	
	

