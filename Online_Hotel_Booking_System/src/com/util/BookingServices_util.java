package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class BookingServices_util {

	private static Connection con=null;
	private static Statement stmt =null;
	private static ResultSet rs = null;
	private static PreparedStatement pstmt = null;
	private static boolean isSuccess = false;
	

    public static int insertBookingServices(String date, String start_time, String end_time, int services_id,
                                      int b_id) {
        int lastInsertedId = -1;
        
        try {
            con = DBconnect.getConnection();
            
            String selectWorkerSql = "SELECT w_id FROM worker WHERE s_id = ? AND status = 'Available' LIMIT 1";
            pstmt = con.prepareStatement(selectWorkerSql);
            pstmt.setInt(1, services_id);  // Bind the services_id to the query

            rs = pstmt.executeQuery();
            
            if (rs.next()) {
                int w_id = rs.getInt("w_id");  // Get the worker ID

                String insertBookingSql = "INSERT INTO booking_services (w_id, date, start_time, end_time, services_id, b_id) " +
                                          "VALUES (?, ?, ?, ?, ?, ?)";
                pstmt = con.prepareStatement(insertBookingSql, Statement.RETURN_GENERATED_KEYS);
                pstmt.setInt(1, w_id);        // Bind the w_id to the insert query
                pstmt.setString(2, date);     // Bind date
                pstmt.setString(3, start_time);  // Bind start_time
                pstmt.setString(4, end_time);    // Bind end_time
                pstmt.setInt(5, services_id);    // Bind services_id
                pstmt.setInt(6, b_id);           // Bind b_id

                int rowsAffected = pstmt.executeUpdate();
                
                if (rowsAffected > 0) {
                    rs = pstmt.getGeneratedKeys();
                    if (rs.next()) {
                        lastInsertedId = rs.getInt(1);  // Get the generated ID
                    }
                }
            } else {
                System.out.println("No available worker found for the specified service.");
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Handle any SQL exceptions
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (con != null) con.close();  // Always close the connection to avoid resource leaks
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return lastInsertedId;  // Return the ID of the inserted booking or -1 if it failed
    }

}
