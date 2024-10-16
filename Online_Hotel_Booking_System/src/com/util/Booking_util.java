package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.model.Booking;

public class Booking_util {

		private static Connection con=null;
		private static ResultSet rs = null;
		public static int insertBooking(String room_price, String service_price, String total_amount, String checkin,
	                                     String checkout, int ru_id, int r_id) {
	        int lastInsertedId = -1;
	        System.out.println("Inserting booking for ru_id: " + ru_id);

	        
	        try {
	            con = DBconnect.getConnection();
	            con.createStatement();

	            // Use PreparedStatement to avoid SQL injection
	            String sql = "INSERT INTO booking (room_price, service_price, total_amount, check_in, check_out, payment_status, ru_id, r_id) " +
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
	            try {
	                if (rs != null) rs.close();
	                if (con != null) con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return lastInsertedId; // Return the last inserted ID or -1 if failed
	    }
	    
	    public static Booking getBookingDetails(int bid) {
	        Booking bookingDetails = null;

	        try {
	            // Get a connection from the database
	            con = DBconnect.getConnection();

	            // SQL query to select booking details for a specific bid
	            String sql = "SELECT b_id, room_price, service_price, total_amount, check_in, check_out, payment_status, ru_id, r_id " +
	                         "FROM booking WHERE b_id = ?";

	            // Use PreparedStatement to prevent SQL injection
	            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	                pstmt.setInt(1, bid);  // Set the bid parameter

	                // Execute the query
	                try (ResultSet rs = pstmt.executeQuery()) {
	                    if (rs.next()) {
	                        // If booking details are found, create a Booking object and set its properties
	                        bookingDetails = new Booking(
	                            rs.getInt("b_id"),
	                            rs.getString("room_price"),
	                            rs.getString("service_price"),
	                            rs.getString("total_amount"),
	                            rs.getString("check_in"),
	                            rs.getString("check_out"),
	                            rs.getString("payment_status"),
	                            rs.getInt("ru_id"),
	                            rs.getInt("r_id")
	                        );
	                        
	                        System.out.println(bookingDetails.getR_id());
	                        System.out.println(bookingDetails.getRu_id());
	                        
	                      
	                        
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace(); // Handle exceptions by printing the stack trace
	        }finally {
	            try {
	                if (rs != null) rs.close();
	                if (con != null) con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }


	        return bookingDetails; // Return the booking details or null if not found
	    }

	    public static boolean updateBookingSts(String status, int bid) {
	        boolean isUpdated = false;

	        try {
	            // Get a connection from the database
	            con = DBconnect.getConnection();

	            // SQL query to update the booking status for a specific bid
	            String sql = "UPDATE booking SET payment_status = ? WHERE b_id = ?";

	            // Use PreparedStatement to prevent SQL injection
	            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	                pstmt.setString(1, status);  // Set the payment_status parameter
	                pstmt.setInt(2, bid);        // Set the b_id parameter

	                // Execute the update
	                int rowsAffected = pstmt.executeUpdate();
	                
	                // If one or more rows are affected, the update was successful
	                if (rowsAffected > 0) {
	                    isUpdated = true;
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace(); // Handle exceptions by printing the stack trace
	        }finally {
	            try {
	                if (rs != null) rs.close();
	                if (con != null) con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }


	        return isUpdated; // Return true if the update was successful, otherwise false
	    }
	    
	    public static List<Booking> getBookingDetailsByRuId(int ruId) {
	        List<Booking> bookingList = new ArrayList<>(); // Initialize the list to hold Booking objects

	        try {
	            // Get a connection from the database
	            con = DBconnect.getConnection();

	            // SQL query to select booking details for a specific ru_id
	            String sql = "SELECT b_id, room_price, service_price, total_amount, check_in, check_out, payment_status, ru_id, r_id " +
	                         "FROM booking WHERE ru_id = ?"; // Query to find all bookings for the given ru_id

	            // Use PreparedStatement to prevent SQL injection
	            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	                pstmt.setInt(1, ruId);  // Set the ru_id parameter

	                // Execute the query
	                try (ResultSet rs = pstmt.executeQuery()) {
	                    while (rs.next()) { // Iterate through all results
	                        // Create a new Booking object and set its properties
	                        Booking bookingDetails = new Booking(
	                            rs.getInt("b_id"),
	                            rs.getString("room_price"),
	                            rs.getString("service_price"),
	                            rs.getString("total_amount"),
	                            rs.getString("check_in"),
	                            rs.getString("check_out"),
	                            rs.getString("payment_status"),
	                            rs.getInt("ru_id"),
	                            rs.getInt("r_id")
	                        );

	                        // Optional: Print the details for debugging
	                        System.out.println("Booking ID: " + bookingDetails.getId());
	                        System.out.println("RU ID: " + bookingDetails.getRu_id());

	                        // Add the Booking object to the list
	                        bookingList.add(bookingDetails);
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace(); // Handle exceptions by printing the stack trace
	        }finally {
	            try {
	                if (rs != null) rs.close();
	                if (con != null) con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }


	        return bookingList; // Return the list of Booking objects (can be empty if no bookings found)
	    }
	    
	    public static List<Booking> getAllBookingDetails() {
	        List<Booking> bookingList = new ArrayList<>(); // Initialize the list to hold Booking objects

	        try {
	            // Get a connection from the database
	            con = DBconnect.getConnection();

	            // SQL query to select all booking details
	            String sql = "SELECT b_id, room_price, service_price, total_amount, check_in, check_out, payment_status, ru_id, r_id " +
	                         "FROM booking"; // Query to select all records from the booking table

	            // Use PreparedStatement to prevent SQL injection (even though no parameters here)
	            try (PreparedStatement pstmt = con.prepareStatement(sql)) {

	                // Execute the query
	                try (ResultSet rs = pstmt.executeQuery()) {
	                    while (rs.next()) { // Iterate through all results
	                        // Create a new Booking object and set its properties
	                        Booking bookingDetails = new Booking(
	                            rs.getInt("b_id"),
	                            rs.getString("room_price"),
	                            rs.getString("service_price"),
	                            rs.getString("total_amount"),
	                            rs.getString("check_in"),
	                            rs.getString("check_out"),
	                            rs.getString("payment_status"),
	                            rs.getInt("ru_id"),
	                            rs.getInt("r_id")
	                        );

	                        // Optional: Print the details for debugging
	                        System.out.println("Booking ID: " + bookingDetails.getId());
	                        System.out.println("RU ID: " + bookingDetails.getRu_id());

	                        // Add the Booking object to the list
	                        bookingList.add(bookingDetails);
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace(); // Handle exceptions by printing the stack trace
	        } finally {
	            try {
	                if (rs != null) rs.close();
	                if (con != null) con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }
	        return bookingList; // Return the list of Booking objects (can be empty if no bookings found)
	    }

	   
	}

	
	

