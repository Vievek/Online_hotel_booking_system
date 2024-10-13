package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

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

	            String sql = "SELECT p_id, amount, payment_type, remaining_amount, b_id " +
	                    "FROM payment WHERE b_id = ? " + // Added a space before ORDER BY
	                    "ORDER BY p_id DESC LIMIT 1";

	            
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
	                if (rs != null) rs.close();
	                if (con != null) con.close();
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	        }

	        return paymentDetails; // Return the payment details or null if not found
	    }
	
	        // Method to insert payment details into the table using individual arguments
	        public static boolean insertPayment(double amount, double remainingAmount,String payment_date, int bId) {
	         

	            try {
	                // Get a connection from the database (assuming DBconnect is your connection class)
	                con = DBconnect.getConnection();

	                // SQL query to insert a new payment entry
	                String sql = "INSERT INTO payment (amount, payment_type, remaining_amount,payment_date, b_id) VALUES (?, ?, ?, ?,?)";
	                
	                String amountStr = Double.toString(amount);
	                String remainingAmountStr = Double.toString(remainingAmount);
	                
	                // Use PreparedStatement to prevent SQL injection
	                try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	                    // Set the values for each column
	                	 pstmt.setString(1, amountStr);               // Set amount as String
	                     pstmt.setString(2, "online");                // Set payment_type as "online"
	                     pstmt.setString(3, remainingAmountStr);      // Set remaining_amount as String
	                     pstmt.setString(4, payment_date);            // Set payment_date
	                     pstmt.setInt(5, bId);                        // Set b_id
                 

	                    // Execute the insert query
	                    int rowsInserted = pstmt.executeUpdate();

	                    // Check if the insertion was successful
	                    if (rowsInserted > 0) {
	                        isSuccess = true;
	                        System.out.println("Payment inserted successfully.");
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
	            return isSuccess; // Return whether the insertion was successful
	        }
	        
	        public static List<Payment> getAllPaymentDetails(int bId) {
	            List<Payment> paymentList = new ArrayList<>(); // List to store Payment objects
	            Connection con = null;

	            try {
	                // Get a connection from the database (assuming DBconnect is your connection class)
	                con = DBconnect.getConnection();

	                // SQL query to select all payment details for a specific bId, including payment_date
	                String sql = "SELECT p_id, amount, payment_type, remaining_amount, b_id, payment_date " +
	                             "FROM payment WHERE b_id = ? " +
	                             "ORDER BY p_id DESC"; 
	                
	                // Use PreparedStatement to prevent SQL injection
	                try (PreparedStatement pstmt = con.prepareStatement(sql)) {
	                    pstmt.setInt(1, bId);  // Set the bId parameter

	                    // Execute the query
	                    try (ResultSet rs = pstmt.executeQuery()) {
	                        // Iterate through the result set and create Payment objects
	                        while (rs.next()) {
	                            Payment payment = new Payment(
	                                rs.getInt("p_id"),
	                                rs.getString("amount"),
	                                rs.getString("payment_type"),
	                                rs.getString("remaining_amount"),
	                                rs.getInt("b_id"),
	                                rs.getString("payment_date") // Retrieve payment_date and set it in Payment object
	                            );
	                            paymentList.add(payment); // Add the Payment object to the list
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

	            return paymentList; // Return the list of payment details
	        }

}
