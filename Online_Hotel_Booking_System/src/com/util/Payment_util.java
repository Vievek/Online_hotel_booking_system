package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import com.model.Payment;


public class Payment_util {
	
	private static Connection con=null;
	private static Statement stmt =null;
	private static ResultSet rs = null;
	private static boolean isSuccess = false;
	
	    // Method to get payment details by bId
	    public static Payment getPaymentDetails(int bId) {
	        Payment paymentDetails = null;
	        Connection con = null;

	        try {
	            // Get a connection from the database (assuming DBconnect is your connection class)
	            con = DBconnect.getConnection();

	            // SQL query to select payment details for a specific bId
	            String sql = "SELECT p_id, amount, payment_type, remaining_amount, b_id " +
	                         "FROM payment WHERE b_id = ?";

	            // Use PreparedStatement to prevent SQL injection
	            try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	                pstmt.setInt(1, bId);  // Set the bId parameter

	                // Execute the query
	                try (ResultSet rs = pstmt.executeQuery()) {
	                    if (rs.next()) {
	                        // If payment details are found, create a Payment object and set its properties
	                        paymentDetails = new Payment(
	                            rs.getInt("p_id"),
	                            rs.getString("amount"),
	                            rs.getString("payment_type"),
	                            rs.getString("remaining_amount"),
	                            rs.getInt("b_id")
	                        );

	                        // Optionally print details to verify
	                        System.out.println("Payment ID: " + paymentDetails.getpId());
	                        System.out.println("Amount: " + paymentDetails.getAmount());
	                        System.out.println("Payment Type: " + paymentDetails.getPaymentType());
	                    }
	                }
	            }
	        } catch (Exception e) {
	            e.printStackTrace(); // Handle exceptions by printing the stack trace
	        } finally {
	            try {
	                if (con != null) {
	                    con.close(); // Close connection
	                }
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }

	        return paymentDetails; // Return the payment details or null if not found
	    }
	


}
