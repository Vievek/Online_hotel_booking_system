package com.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class userRoom_interaction_util {
	private static Connection con=null;
	private static Statement stmt =null;
	private static ResultSet rs = null;
	private static PreparedStatement pstmt = null;
	private static boolean isSuccess = false;

	
	public static boolean addUserInteraction(int user_id,int rooms_id) {
		
		 try {
		        con = DBconnect.getConnection();
		        stmt = con.createStatement();
		        
		        // Inserting into 'user' table and using PreparedStatement to prevent SQL injection
		        String userSql = "INSERT INTO user_room_interactions (user_id,rooms_id) VALUES (?, ?)";
		        pstmt = con.prepareStatement(userSql, Statement.RETURN_GENERATED_KEYS);
		        pstmt.setInt(1, user_id);
		        pstmt.setInt(2, rooms_id);
		       
		        
		        int userResult = pstmt.executeUpdate();
		        
		        if (userResult > 0) {
		            // Retrieving the generated ID (primary key) from 'user' table
		            rs = pstmt.getGeneratedKeys();
		            if (rs.next()) {
		                int userId = rs.getInt(1); // ID of the inserted user
		                
		                // Inserting into 'registered_user' table using the userId as the foreign key
		                String registeredUserSql = "INSERT INTO registered_user (id) VALUES (?)";
		                pstmt = con.prepareStatement(registeredUserSql);
		                pstmt.setInt(1, userId);
		                int UserInteractionResult = pstmt.executeUpdate();
		                
		                // Check if the row was successfully inserted into 'registered_user'
		                if (UserInteractionResult > 0) {
		                    isSuccess = true;
		                } else {
		                    isSuccess = false;
		                }
		            }
		        } else {
		            isSuccess = false;
		        }
		        
		        System.out.println(isSuccess);
		    } catch (Exception e) {
		        e.printStackTrace();
		    } finally {
		        try {
		            if (rs != null) rs.close();
		            if (pstmt != null) pstmt.close();
		            if (stmt != null) stmt.close();
		            if (con != null) con.close();
		        } catch (Exception e) {
		            e.printStackTrace();
		        }
		    }
		    
		    return isSuccess;
		}
}
